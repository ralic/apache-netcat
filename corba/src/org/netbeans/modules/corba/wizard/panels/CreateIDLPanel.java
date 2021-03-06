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

package org.netbeans.modules.corba.wizard.panels;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;
import java.beans.PropertyVetoException;
import java.beans.PropertyChangeEvent;
import org.openide.TopManager;
import org.openide.nodes.Node;
import org.openide.util.actions.NodeAction;
import org.openide.util.actions.SystemAction;
import org.openide.explorer.view.BeanTreeView;
import org.openide.loaders.TemplateWizard;
import org.netbeans.modules.corba.wizard.nodes.IdlFileNode;
import org.netbeans.modules.corba.wizard.nodes.AbstractMutableIDLNode;
import org.netbeans.modules.corba.wizard.IDLWizardData;
import org.netbeans.modules.corba.wizard.CorbaWizard;

/**
 *
 * @author  tzezula
 * @version 
 */
public class CreateIDLPanel extends AbstractIDLWizardPanel implements PropertyChangeListener, VetoableChangeListener {

    //private static final boolean DEBUG = true;
    private static final boolean DEBUG = false;

    private BeanTreeView bv;
    private IdlFileNode root;
    private SystemAction removeAction;
    private SystemAction editAction;
    private IDLWizardData data;


    /** Creates new form IDLWizardPanel */
    public CreateIDLPanel(IDLWizardData data) {
        this.data = data;
        initComponents ();
        postInitComponents ();
        this.setName(bundle.getString("TXT_CreateIDL"));
        putClientProperty(CorbaWizard.PROP_CONTENT_SELECTED_INDEX, new Integer(2));
    }

    public void postInitComponents () {
        this.bv = new BeanTreeView ();
        this.bv.setPopupAllowed (false);
        this.bv.setDefaultActionAllowed (false);
        this.tree.add (bv);
        this.root = new IdlFileNode ();
        this.tree.getExplorerManager().setRootContext (this.root);
        this.tree.getExplorerManager().addPropertyChangeListener (this);
        this.tree.getExplorerManager().addVetoableChangeListener (this);
        this.jLabel1.setDisplayedMnemonic (this.bundle.getString ("TXT_IDLFileContent_MNE").charAt(0));
        this.types.getAccessibleContext().setAccessibleName (this.bundle.getString("AN_IDLTypes"));
        this.types.getAccessibleContext().setAccessibleDescription (this.bundle.getString("AD_IDLTypes"));
        this.tree.getAccessibleContext().setAccessibleDescription (this.bundle.getString("AD_IDLTree"));
        this.create.setMnemonic (this.bundle.getString("TXT_CreateButton_MNE").charAt(0));
        this.remove.setMnemonic (this.bundle.getString("TXT_RemoveButton_MNE").charAt(0));
        this.edit.setMnemonic (this.bundle.getString("TXT_EditButton_MNE").charAt(0));
        this.up.setMnemonic (this.bundle.getString("TXT_UpButton_MNE").charAt(0));
        this.down.setMnemonic (this.bundle.getString("TXT_DownButton_MNE").charAt(0));
        this.create.getAccessibleContext().setAccessibleDescription (this.bundle.getString("AD_CreateButton"));
        this.remove.getAccessibleContext().setAccessibleDescription (this.bundle.getString("AD_RemoveButton"));
        this.edit.getAccessibleContext().setAccessibleDescription(this.bundle.getString("AD_EditButton"));
        this.up.getAccessibleContext().setAccessibleDescription(this.bundle.getString("AD_UpButton"));
        this.down.getAccessibleContext().setAccessibleDescription(this.bundle.getString("AD_DownButton"));
        this.getAccessibleContext().setAccessibleDescription(this.bundle.getString ("AD_CreateIDLPanel"));
       try {
            this.tree.getExplorerManager().setSelectedNodes (new Node[]{this.root});
            this.createCommandPanel (new Node[]{this.root});
        }catch (PropertyVetoException veto) {/*Never happens, hopefully*/}
    }

    public void readIDLSettings (TemplateWizard data) {
    }

    public void storeIDLSettings (TemplateWizard data) {
        this.data.setIdlSource (this.root.generate());
    }
    
    public void cleanUp () {
        if (this.root != null) {
            ((org.netbeans.modules.corba.wizard.nodes.MutableChildren)this.root.getChildren()).removeAllKeys(true);
	}
    }

    public boolean isValid () {
        return true;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        tree = new org.openide.explorer.ExplorerPanel();
        types = new javax.swing.JComboBox();
        create = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        up = new javax.swing.JButton();
        down = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        setPreferredSize(new java.awt.Dimension(500, 300));
        setMinimumSize(new java.awt.Dimension(100, 100));
        tree.setBorder(new javax.swing.border.EtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 12, 12);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(tree, gridBagConstraints);

        types.setPreferredSize(new java.awt.Dimension(120, 23));
        types.setMinimumSize(new java.awt.Dimension(120, 23));
        types.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typesActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 6, 12);
        add(types, gridBagConstraints);

        create.setText(bundle.getString("TXT_CreateButton"));
        create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 12);
        add(create, gridBagConstraints);

        edit.setText(bundle.getString("TXT_EditButton"));
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 12);
        add(edit, gridBagConstraints);

        up.setText(bundle.getString("TXT_UpButton"));
        up.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 12);
        add(up, gridBagConstraints);

        down.setText(bundle.getString("TXT_DownButton"));
        down.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 12);
        add(down, gridBagConstraints);

        remove.setText(bundle.getString("TXT_RemoveButton"));
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 12);
        add(remove, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 12, 12);
        gridBagConstraints.weighty = 1.0;
        add(jPanel2, gridBagConstraints);

        jLabel1.setText(java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/wizard/panels/Bundle").getString("TXT_IDLFileContent"));
        jLabel1.setLabelFor(tree);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 0, 12);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(jLabel1, gridBagConstraints);

    }//GEN-END:initComponents

    private void typesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typesActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_typesActionPerformed

    private void downActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downActionPerformed
        // Add your handling code here:
        Node[] nodes = this.tree.getExplorerManager().getSelectedNodes();
        ((AbstractMutableIDLNode)nodes[0]).moveDown();
        try {
            this.tree.getExplorerManager().setSelectedNodes (nodes);
        }catch (java.beans.PropertyVetoException ve) {
        }
    }//GEN-LAST:event_downActionPerformed

    private void upActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upActionPerformed
        // Add your handling code here:
        Node[] nodes = this.tree.getExplorerManager().getSelectedNodes();
        ((AbstractMutableIDLNode)nodes[0]).moveUp();
        try {
            this.tree.getExplorerManager().setSelectedNodes (nodes);
        }catch (java.beans.PropertyVetoException ve) {
        }
    }//GEN-LAST:event_upActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // Add your handling code here:
        Node[] nodes = this.tree.getExplorerManager().getSelectedNodes();
        this.editAction.actionPerformed (new ActionEvent (nodes,0,""));
    }//GEN-LAST:event_editActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        // Add your handling code here:
        Node[] nodes = this.tree.getExplorerManager().getSelectedNodes();
        this.removeAction.actionPerformed (new ActionEvent(nodes,0,""));
    }//GEN-LAST:event_removeActionPerformed

    private void createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createActionPerformed
        // Add your handling code here
        Object obj = this.types.getSelectedItem();
        if (obj == null || !(obj instanceof NodeAction))
            return;
        NodeAction action = (NodeAction) obj;
        Node[] nodes = this.tree.getExplorerManager().getSelectedNodes();
        action.actionPerformed (new ActionEvent( nodes ,0,""));
    }//GEN-LAST:event_createActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openide.explorer.ExplorerPanel tree;
    private javax.swing.JButton up;
    private javax.swing.JButton remove;
    private javax.swing.JComboBox types;
    private javax.swing.JButton down;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton edit;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton create;
    // End of variables declaration//GEN-END:variables

    private static final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/wizard/panels/Bundle");    

    public void propertyChange(final java.beans.PropertyChangeEvent event) {
        Object newValue = event.getNewValue ();
        if (newValue != null && newValue instanceof Node[] ) {
            Node[] nodes = (Node[]) newValue;
            if (nodes.length == 1) {
                this.tree.setActivatedNodes (nodes);
                this.createCommandPanel (nodes);
            }
        }
    }

  
    public void vetoableChange(final java.beans.PropertyChangeEvent event) throws java.beans.PropertyVetoException {
        Object newValue = event.getNewValue ();
        if (newValue != null && newValue instanceof Node[] ) {
            Node[] nodes = (Node[]) newValue;
            if (nodes.length != 1)
                throw new PropertyVetoException ("",event);
        }
    }
    
    private void enableUp () {
        this.up.setEnabled (true);
    }
    
    private void disableUp () {
        this.up.setEnabled (false);
    }
    
    private void enableDown () {
        this.down.setEnabled (true);
    }
    
    private void disableDown () {
        this.down.setEnabled (false);
    }

    /** Creates command panel content
     *  @param Node node, the node for which the panel should be created
     */
    private void createCommandPanel (Node[] node) {
        this.types.removeAllItems ();
        SystemAction[] actions = node[0].getActions();
        this.remove.setEnabled (false);
        this.edit.setEnabled (false);
        this.up.setEnabled (false);
        this.down.setEnabled (false);
        for (int i=0; i<actions.length; i++) {
            if (actions[i]==null)
                continue;
            if (actions[i] instanceof org.netbeans.modules.corba.wizard.nodes.utils.Create) {
                if (((org.netbeans.modules.corba.wizard.nodes.utils.Create)actions[i]).isEnabled (node))
                    types.addItem (actions[i]);
            }
            else if (actions[i] instanceof org.netbeans.modules.corba.wizard.nodes.actions.DestroyAction) {
                this.removeAction = actions[i];
                this.remove.setEnabled (true);
            }
            else if (actions[i] instanceof org.netbeans.modules.corba.wizard.nodes.actions.EditAction) {
                this.editAction = actions[i];
                this.edit.setEnabled (true);
            }
        }
        if (types.getItemCount() == 0)
            this.create.setEnabled (false);
        else
            this.create.setEnabled (true);
        if (node[0] instanceof AbstractMutableIDLNode) {
            this.up.setEnabled (((AbstractMutableIDLNode)node[0]).canMoveUp());
            this.down.setEnabled (((AbstractMutableIDLNode)node[0]).canMoveDown());
        }
    }

}
