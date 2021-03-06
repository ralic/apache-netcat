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

package org.netbeans.modules.vcscore.runtime;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.ArrayList;

import org.openide.filesystems.FileSystem;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.WeakListeners;

/**
 * The provider of commands for the representation on the Runtime Tab.
 *
 * @author  Martin Entlicher
 */
public abstract class RuntimeCommandsProvider {
    
    /**
     * This property is fired, when the children commands changed.
     */
    public static final String PROP_CHILDREN = "children"; // NOI18N
    
    /**
     * The name of FileObject attribute, that contains instance of RuntimeCommandsProvider
     * on VCS filesystems.
     */
    private static final String FO_ATTRIBUTE = "org.netbeans.modules.vcscore.runtime.RuntimeCommandsProvider"; // NOI18N
    
    private static List registeredProviders;
    private static List registeredListenersWeak;

    private Reference nodeDelegate = new WeakReference(null);
    private PropertyChangeSupport listenerSupport = new PropertyChangeSupport(this);
    
    /**
     * Find the runtime commands provider for a FileSystem.
     */
    public static RuntimeCommandsProvider findProvider(FileSystem fs) {
        try {
        return (RuntimeCommandsProvider) fs.getRoot().getAttribute(FO_ATTRIBUTE);
        } catch (NullPointerException npe) {
            throw (NullPointerException) org.openide.ErrorManager.getDefault().annotate(npe, "fs = "+fs+"\n"+
                "fs.getRoot() = "+((fs != null) ? ""+fs.getRoot() : "null"));
        }
    }
    
    public void register() {
        synchronized (RuntimeCommandsProvider.class) {
            if (registeredProviders == null) {
                registeredProviders = new ArrayList();
            }
            registeredProviders.add(this);
        }
        fireRegisteredListeners(null, this);
    }
    
    public void unregister() {
        boolean fire = false;
        synchronized (RuntimeCommandsProvider.class) {
            if (registeredProviders != null) {
                registeredProviders.remove(this);
                if (registeredProviders.size() == 0) registeredProviders = null;
                fire = true;
            }
        }
        if (fire) fireRegisteredListeners(this, null);
    }
    
    static synchronized RuntimeCommandsProvider[] getRegistered() {
        if (registeredProviders == null) return null;
        else return (RuntimeCommandsProvider[])
            registeredProviders.toArray(new RuntimeCommandsProvider[registeredProviders.size()]);
    }
    
    static synchronized void addRegisteredListenerWeakly(PropertyChangeListener l) {
        if (registeredListenersWeak == null) {
            registeredListenersWeak = new ArrayList();
        }
        registeredListenersWeak.add(new WeakReference(l));
    }
    
    private static synchronized void fireRegisteredListeners(Object oldValue, Object newValue) {
        if (registeredListenersWeak != null) {
            for (int i = 0; i < registeredListenersWeak.size(); i++) {
                WeakReference ref = (WeakReference) registeredListenersWeak.get(i);
                PropertyChangeListener l = (PropertyChangeListener) ref.get();
                if (l != null) {
                    l.propertyChange(new PropertyChangeEvent(RuntimeCommandsProvider.class, "", oldValue, newValue));
                } else {
                    registeredListenersWeak.remove(i--);
                }
            }
        }
    }
    
    public final synchronized Node getNodeDelegate() {
        Node node = (Node) nodeDelegate.get();
        if (node == null) {
            node = createNodeDelegate();
            nodeDelegate = new WeakReference(node);
        }
        return node;
    }
    
    protected abstract Node createNodeDelegate();
    
    /**
     * Get the node delegate if exists.
     * @return The node delegate or <code>null</code> if the node delegate is not created.
     */
    protected final Node getExistingNodeDelegate() {
        return (Node) nodeDelegate.get();
    }
    
    public abstract RuntimeCommand[] children();
    
    public final void addPropertyChangeListener(PropertyChangeListener l) {
        listenerSupport.addPropertyChangeListener(l);
    }
    
    public final void removePropertyChangeListener(PropertyChangeListener l) {
        listenerSupport.removePropertyChangeListener(l);
    }
    
    protected final void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        listenerSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
    
    /**
     * Notify, that this runtime commands provider was added into the Runtime tab.
     * Subclasses can use this method to do various inicialization here.
     */
    protected void notifyAdded() {
    }
    
    /**
     * Notify, that this runtime commands provider was removed from the Runtime tab.
     * Subclasses can use this method to do various cleanup here.
     */
    protected void notifyRemoved() {
    }
    
    public static final class RuntimeFolderChildren extends Children.Keys implements PropertyChangeListener {
        
        private RuntimeCommandsProvider provider;
        
        public RuntimeFolderChildren(RuntimeCommandsProvider provider) {
            this.provider = provider;
            setKeys(provider.children());
            provider.addPropertyChangeListener(WeakListeners.propertyChange(this, provider));
        }
        
        protected Node[] createNodes(Object obj) {
            RuntimeCommand cmd = (RuntimeCommand) obj;
            return new Node[] { cmd.getNodeDelegate() };
        }
        
        public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
            if (PROP_CHILDREN.equals(propertyChangeEvent.getPropertyName())) {
                setKeys(provider.children());
            }
        }
        
    }

}
