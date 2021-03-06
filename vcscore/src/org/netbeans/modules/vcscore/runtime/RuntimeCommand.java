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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import org.netbeans.modules.vcscore.commands.VcsCommandExecutor;
import org.openide.nodes.Node;
import org.openide.nodes.Sheet;
import org.openide.util.actions.SystemAction;

/**
 * An abstract class that encapsulates the module dependant features that are needed
 * when creating a runtime tab node for a command.
 * If your module is using the CommandsPool and VcsCommandExecutor classes form vcscore, 
 * you don't have to do anything and your commands will be automatically added.
 * You need an subclasses' instance when creating the RuntimeCommandNode.
 * See VcsRuntimeCommand for default implementation.
 * @author  Milos Kleint
 */
public abstract class RuntimeCommand implements Node.Cookie {

    /**
     * Constant that means a command was successfully exited.
     * It should be returned by the getExitStatus() method.
     * 
     */
    public static final int SUCCEEDED = VcsCommandExecutor.SUCCEEDED;
    /**
     * Constant that means a command failed.
     * It should be returned by the getExitStatus() method.
     * 
     */
    public static final int FAILED = VcsCommandExecutor.FAILED;

    /**
     * Constant that means a command was interrupted.
     * It should be returned by the getExitStatus() method.
     * 
     */
    
    public static final int INTERRUPTED = VcsCommandExecutor.INTERRUPTED;
    
    public static final int STATE_WAITING = RuntimeCommandNode.STATE_WAITING;

    public static final int STATE_RUNNING = RuntimeCommandNode.STATE_RUNNING;
    
    public static final int STATE_DONE = RuntimeCommandNode.STATE_DONE;
    
    public static final int STATE_CANCELLED = RuntimeCommandNode.STATE_CANCELLED;
    
    public static final int STATE_KILLED_BUT_RUNNING = RuntimeCommandNode.STATE_KILLED_BUT_RUNNING;
    
    public static final String PROP_STATE = "state";
    public static final String PROP_DISPLAY_NAME = "displayName";
    
    private Reference nodeDelegate = new WeakReference(null);
    
    private PropertyChangeSupport changeSupport;
    
    /** Creates new RuntimeCommand */
    public RuntimeCommand() {
        changeSupport = new PropertyChangeSupport(this);
    }
    
    /**
     * Subclasses should return a name of the command. 
     */
    public abstract String getName();
    
    /**
     * Subclasses should return a display name of the command. 
     * If not defined, the getName()'s result is used instead.
     */
    
    public abstract String getDisplayName();
    
    /**
     * When the command finishes this method should return the exit atatus of the vcs command.
     * See constants in this class.
     */
    public abstract int getExitStatus();
    
    /**
     * this method returns the set of actions that should be available for the runtime tab node of the command.
     */
    public abstract SystemAction[] getActions();
    
    /**
     * this method returns the default action that should be available on the runtime tab node of the command.
     */
    public abstract SystemAction getDefaultAction();
    
    /**
     * If you returned the CommandOutputViewAction among the other actions in getActions() method,
     * you should implement this method and when <code>gui</code> argument is <code>true</code>,
     * display the output in a custom GUI panel.<p>
     * If you returned the CommandOutputTextViewAction among the other actions in getActions() method,
     * you should implement this method and when <code>gui</code> argument is <code>false</code>,
     * display the output in the CommandOutputPanel.
     * Otherwise just leave it blank.
     */
    public abstract void openCommandOutputDisplay(boolean gui);
    
    /**
     * Create a property sheet for the node.
     */
    public abstract Sheet createSheet();
    
    /**
     * provide a unique id for the node.
     */
    public abstract String getId();
    
    /**
     * If you returned the KillRunningCommandAction among the other actions in getActions() method,
     * this method will be called and should attempt  to stop the running command.
     */
    public abstract void killCommand();
    
    public abstract int getState();
    
    public abstract void setState(int state);
    
    /** Called when this command is removed from the Runtime tab. Can be used by subclasses
     *  to do some cleanup when this command was restroyed. */
    public void notifyRemoved() {
    }
    
    public final synchronized Node getNodeDelegate() {
        Node node = (Node) nodeDelegate.get();
        if (node == null) {
            node = createNodeDelegate();
            nodeDelegate = new WeakReference(node);
        }
        return node;
    }
    
    protected Node createNodeDelegate() {
        return new RuntimeCommandNode(this);
    }

    /**
     * Get the node delegate if exists.
     * @return The node delegate or <code>null</code> if the node delegate is not created.
     */
    protected final Node getExistingNodeDelegate() {
        return (Node) nodeDelegate.get();
    }
    
    public final void addPropertyChangeListener(PropertyChangeListener propertyListener) {
        changeSupport.addPropertyChangeListener(propertyListener);
    }
    
    public final void addPropertyChangeListener(String propertyName, PropertyChangeListener propertyListener) {
        changeSupport.addPropertyChangeListener(propertyName, propertyListener);
    }
    
    public final void removePropertyChangeListener(PropertyChangeListener propertyListener) {
        changeSupport.removePropertyChangeListener(propertyListener);
    }
    
    public final void removePropertyChangeListener(String propertyName, PropertyChangeListener propertyListener) {
        changeSupport.removePropertyChangeListener(propertyName, propertyListener);
    }
    
    protected final void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        changeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
}
