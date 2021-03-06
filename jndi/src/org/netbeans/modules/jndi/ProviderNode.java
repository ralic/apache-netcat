/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */

package org.netbeans.modules.jndi;

import java.security.AllPermission;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.util.Vector;
import java.io.IOException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.event.ListDataListener;
import javax.naming.Context;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.execution.NbClassLoader;
import org.openide.util.actions.SystemAction;
import org.openide.actions.PropertiesAction;
import org.openide.actions.DeleteAction;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node.Cookie;
import org.openide.nodes.Sheet;
import org.openide.util.HelpCtx;
import org.netbeans.modules.jndi.settings.JndiSystemOption;
import org.openide.nodes.Node.Property;


/** This class represents a provider (factory)
 */
public class ProviderNode extends AbstractNode implements Cookie{

    /** Name used for JndiIcons*/
    public static final String DRIVER = "DRIVER";
    public static final String DISABLED_DRIVER="DEADDRIVER";

    /** key in Hashtable of providers, for which this node is inserted*/
    private String name;
    /** System actions of this node*/
    private SystemAction[] nodeActions;
    
    /** Cached JNDI system option */
    JndiSystemOption settings;

    /** Creates new ProviderNode
     *  @param String name key in Hashtable of providers
     */
    public ProviderNode (String name) {
        super (Children.LEAF);
        this.getCookieSet().add(this);
        this.name=name;
        String label;
        int index = name.lastIndexOf ('.');
        if (index < 0) label = name;
        else label = name.substring (index+1);
        this.setName (label);
        try{
            NbClassLoader nbcl = new NbClassLoader();
            nbcl.setDefaultPermissions(getAllPermissions());
            Class.forName(name, true, nbcl);
            this.setIconBase (JndiIcons.ICON_BASE + JndiIcons.getIconName(ProviderNode.DRIVER));
        }catch (ClassNotFoundException cnf){
            this.setIconBase (JndiIcons.ICON_BASE + JndiIcons.getIconName(ProviderNode.DISABLED_DRIVER));
        }
    }
    
    private static PermissionCollection allPerimssions;
    static synchronized PermissionCollection getAllPermissions() {
        if (allPerimssions == null) {
            allPerimssions = new Permissions();
            allPerimssions.add(new AllPermission());
        }
        return allPerimssions;
    }


    /** Sets name of this node
     *  @param Object name
     */
    public void setValue (Object name) {
        if (name instanceof String) {
            this.setName( (String) name);
        }
    }

    /** Returns the name of this node
     *  @return Object name of node
     */
    public Object getValue () {
        return this.getName();
    }

    /** Returns true if the node can copy
     *  @param boolean can / can not copy
     */
    public boolean canCopy () {
        return false;
    }

    /** Returns true if the node can destroy
     *  @param boolean can / can not destroy
     */
    public boolean canDestroy () {
        return true;
    }

    /** Returns true if the node can cut
     *  @param boolean can / can not cut
     */
    public boolean canCut () {
        return false;
    }

    /** Returns true if the node can rename
     *  @param boolean can / can not rename
     */
    public boolean canRename () {
        return false;
    }


    /** Creates SystemActions
     *  @return SystemAction[] actions
     */
    public SystemAction[] createActions () {
        return new SystemAction[] {
                   SystemAction.get(ProviderTestAction.class),
                   SystemAction.get(ProviderConnectAction.class),
                   null,
                   SystemAction.get(DeleteAction.class),
                   null,
                   SystemAction.get(PropertiesAction.class),
               };
    }

    /** Returns actions of this node
     *  @return SystemAction[] actions
     */
    public SystemAction[] getActions () {
        if (this.nodeActions == null) {
            this.nodeActions = this.createActions();
        }
        return this.nodeActions;
    }


    /** Creates property sheet of this node
     *  @return Sheet created sheet
     */
    public Sheet createSheet () {
        Sheet sheet = Sheet.createDefault ();
        Sheet.Set set = sheet.get (Sheet.PROPERTIES);
        ProviderProperties properties = (ProviderProperties) this.getSettings().getProviders(false).get (this.name);
        if (properties != null) {
            Property property = new ProviderProperty (Context.INITIAL_CONTEXT_FACTORY, String.class,JndiRootNode.getLocalizedString("TXT_Factory"),JndiRootNode.getLocalizedString("TIP_Factory"),properties,false);
            set.put (property);
            property = new ProviderProperty (Context.PROVIDER_URL,String.class,JndiRootNode.getLocalizedString("TXT_InitialContext"),JndiRootNode.getLocalizedString("TIP_InitialContext"),properties,true);
            set.put (property);
            property = new ProviderProperty (JndiRootNode.NB_ROOT,String.class,JndiRootNode.getLocalizedString("TXT_Root"),JndiRootNode.getLocalizedString("TIP_Root"),properties,true);
            set.put (property);
            property = new ProviderProperty (Context.SECURITY_AUTHENTICATION,String.class,JndiRootNode.getLocalizedString("TXT_Auth"),JndiRootNode.getLocalizedString("TIP_Auth"),properties,true);
            set.put (property);
            property = new ProviderProperty (Context.SECURITY_PRINCIPAL,String.class,JndiRootNode.getLocalizedString("TXT_Principal"),JndiRootNode.getLocalizedString("TIP_Principal"),properties,true);
            set.put (property);
            property = new ProviderProperty (Context.SECURITY_CREDENTIALS,String.class,JndiRootNode.getLocalizedString("TXT_Credentials"),JndiRootNode.getLocalizedString("TIP_Credentials"),properties,true);
            set.put (property);
            property = new ProviderProperty (ProviderProperties.ADDITIONAL,String.class,JndiRootNode.getLocalizedString("TXT_OtherProps"), JndiRootNode.getLocalizedString("TIP_Additional"),properties,true);
            set.put (property);
        }
        return sheet;
    }

    /** Callback for DeleteAction, disposes this node
     *  @exception IOException
     */
    public void destroy () throws IOException {
        JndiSystemOption settings = this.getSettings();
        if (settings != null) {
            settings.destroyProvider (this.name);
        }
        super.destroy ();
    }

    /** Callback for ProviderTestAction, tests if the provider class is accessible
     */
    public void testProvider () {
        try{
            Class.forName(this.name, true, new org.openide.execution.NbClassLoader());
            this.setIconBase (JndiIcons.ICON_BASE + JndiIcons.getIconName(ProviderNode.DRIVER));
            this.fireIconChange ();
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(JndiRootNode.getLocalizedString("MSG_CLASS_FOUND"), NotifyDescriptor.Message.INFORMATION_MESSAGE));
        }catch(ClassNotFoundException cnfe){
            this.setIconBase (JndiIcons.ICON_BASE + JndiIcons.getIconName(ProviderNode.DISABLED_DRIVER));
            this.fireIconChange ();
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(JndiRootNode.getLocalizedString("MSG_CLASS_NOT_FOUND"), NotifyDescriptor.Message.INFORMATION_MESSAGE));
        }
    }


    /** Opens the add context dialog with prefilled fields
     */
    public void connectUsing() {
        try{
            ((JndiDataType)JndiRootNode.getDefault().getNewTypes()[0]).create(name);
        }catch(java.io.IOException ioe){/** Should never happend*/}
    }


    /** Returns the customizer of this node
     *  @return Component customizer
     */
    public java.awt.Component getCustomizer(){
        JndiSystemOption settings = this.getSettings();
        if (settings != null) {
            Customizer p = new Customizer(settings.getProviders(false).get (this.name));
            return p;
        }
        else
            return null;
    }


    /** Returns true, this node support its own customizer
     *  @return boolean trye
     */
    public boolean hasCustomizer(){
        return true;
    }

    /** Fires the propertyChangeEvent to notify about chages
     *  @param String name, name of changed property 
     *  @param Object oldv, old value of property
     *  @param Object newv, new value of property
     */
    public void updateData(String name, Object oldv, Object newv){
        this.firePropertyChange(name,oldv,newv);
    }

    /** Customizer for this node*/
    class Customizer extends NewProviderPanel implements FocusListener, ListDataListener {
        private ProviderProperties target;

        Customizer (final java.lang.Object target){
            super();
            this.target = (ProviderProperties)target;
            this.factory.setText(this.target.getFactory());
            this.context.setText(this.target.getContext());
            this.context.addFocusListener(this);
            this.root.setText(this.target.getRoot());
            this.root.addFocusListener(this);
            this.authentification.setText(this.target.getAuthentification());
            this.authentification.addFocusListener(this);
            this.principal.setText(this.target.getPrincipal());
            this.principal.addFocusListener(this);
            this.credentials.setText(this.target.getCredentials());
            this.credentials.addFocusListener(this);
            this.properties.setData(this.target.getAdditional());
            this.properties.addListDataListener(this);
            this.factory.setEditable(false);
            this.factory.addFocusListener (this);
        }

        /** Handles action fired when field is changed in customizer
         *  @param FocusEvent event
         */
        public void focusLost(FocusEvent event){
            String newv;
            String oldv;

            if (event.getSource()==this.context){
                newv = this.context.getText();
                oldv = this.target.getContext();
                if (!newv.equals(oldv)){
                    this.target.setContext(this.context.getText());
                    ProviderNode.this.updateData(Context.PROVIDER_URL,oldv,newv);
                }
            }
            else if (event.getSource()==this.authentification){
                newv = this.authentification.getText();
                oldv = this.target.getAuthentification();
                if (!newv.equals(oldv)){
                    this.target.setAuthentification(this.authentification.getText());
                    ProviderNode.this.updateData(Context.SECURITY_AUTHENTICATION,oldv,newv);
                }
            }
            else if (event.getSource()==this.credentials){
                newv = this.credentials.getText();
                oldv = this.target.getCredentials();
                if (!newv.equals(oldv)){
                    this.target.setCredentials(this.credentials.getText());
                    ProviderNode.this.updateData(Context.SECURITY_CREDENTIALS,oldv,newv);
                }
            }
            else if (event.getSource()==this.principal){
                newv = this.principal.getText();
                oldv = this.target.getPrincipal();
                if (!newv.equals(oldv)){
                    this.target.setPrincipal(this.principal.getText());
                    ProviderNode.this.updateData(Context.SECURITY_PRINCIPAL,oldv,newv);
                }
            }
            else if (event.getSource()==this.root){
                newv = this.root.getText();
                oldv = this.target.getRoot();
                if (!newv.equals(oldv)){
                    this.target.setRoot(this.root.getText());
                    ProviderNode.this.updateData(JndiRootNode.NB_ROOT,oldv,newv);
                }
            }
        }

        public void focusGained(final java.awt.event.FocusEvent event) {
            if (event.getSource() == this.factory) {
                this.factory.selectAll();
            }
        }


        /** Handles action fired when additional properties are changed in customizer
         *  @param ListDataEvent event
         */
        public void intervalAdded(final javax.swing.event.ListDataEvent event) {
            if (event.getSource()==this.properties){
                Vector newv = properties.asVector();
                this.target.setAdditional(newv);
                ProviderNode.this.updateData(ProviderProperties.ADDITIONAL,newv,null);
            }
        }

        /** Handles action fired when additional properties are changed in customizer
         *  @param ListDataEvent event
         */
        public void intervalRemoved(final javax.swing.event.ListDataEvent event) {
            if (event.getSource()==this.properties){
                Vector newv = properties.asVector();
                this.target.setAdditional(newv);
                ProviderNode.this.updateData(ProviderProperties.ADDITIONAL,newv,null);
            }
        }

        /** Handles action fired when additional properties are changed in customizer
         *  @param ListDataEvent event
         */
        public void contentsChanged(final javax.swing.event.ListDataEvent event) {
            if (event.getSource()==this.properties){
                Vector newv = properties.asVector();
                this.target.setAdditional(newv);
                ProviderNode.this.updateData(ProviderProperties.ADDITIONAL,newv,null);
            }
        }
    }
    
    /** Returns the help context for
     *  provider node
     */
    public HelpCtx getHelpCtx () {
        return new HelpCtx (ProviderNode.class.getName());
    }
    
    
    private JndiSystemOption getSettings () {
        if (this.settings == null) {
            this.settings = (JndiSystemOption) JndiSystemOption.findObject (JndiSystemOption.class);
        }
        return this.settings;
    }


}
