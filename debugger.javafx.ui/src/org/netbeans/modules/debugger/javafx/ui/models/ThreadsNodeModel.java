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

package org.netbeans.modules.debugger.javafx.ui.models;

import com.sun.jdi.AbsentInformationException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.netbeans.api.debugger.DebuggerEngine;
import org.netbeans.spi.debugger.ContextProvider;
import org.netbeans.api.debugger.Session;
import org.netbeans.api.debugger.javafx.CallStackFrame;
import org.netbeans.api.debugger.javafx.InvalidExpressionException;
import org.netbeans.api.debugger.javafx.JavaFXDebugger;
import org.netbeans.api.debugger.javafx.JavaFXThread;
import org.netbeans.api.debugger.javafx.JavaFXThreadGroup;
import org.netbeans.api.debugger.javafx.ObjectVariable;
import org.netbeans.spi.viewmodel.ModelEvent;
import org.netbeans.spi.viewmodel.NodeModel;
import org.netbeans.spi.viewmodel.TreeModel;
import org.netbeans.spi.viewmodel.ModelListener;
import org.netbeans.spi.viewmodel.UnknownTypeException;
import org.openide.util.NbBundle;


/**
 * @author   Jan Jancura
 */
public class ThreadsNodeModel implements NodeModel {
    
    public static final String CURRENT_THREAD =
        "org/netbeans/modules/debugger/resources/threadsView/CurrentThread"; // NOI18N
    public static final String RUNNING_THREAD =
        "org/netbeans/modules/debugger/resources/threadsView/RunningThread"; // NOI18N
    public static final String SUSPENDED_THREAD =
        "org/netbeans/modules/debugger/resources/threadsView/SuspendedThread"; // NOI18N
    public static final String THREAD_GROUP =
        "org/netbeans/modules/debugger/resources/threadsView/ThreadGroup"; // NOI18N
    public static final String CURRENT_THREAD_GROUP =
        "org/netbeans/modules/debugger/resources/threadsView/CurrentThreadGroup"; // NOI18N
    
    
    private JavaFXDebugger debugger;
    private Session session;
    private Vector listeners = new Vector ();
    private Set<Object> currentNodes = new HashSet<Object>();
    
    
    public ThreadsNodeModel (ContextProvider lookupProvider) {
        debugger = lookupProvider.lookupFirst(null, JavaFXDebugger.class);
        session = lookupProvider.lookupFirst(null, Session.class);
        new Listener (this, debugger, currentNodes);
    }
    
    public String getDisplayName (Object o) throws UnknownTypeException {
        if (o == TreeModel.ROOT) {
            return NbBundle.getBundle (ThreadsNodeModel.class).getString
                ("CTL_ThreadsModel_Column_Name_Name");
        } else
        if (o instanceof JavaFXThread) {
            if (debugger.getCurrentThread () == o) {
                synchronized(currentNodes) {
                    currentNodes.add(o);
                }
                return BoldVariablesTableModelFilterFirst.toHTML (
                    ((JavaFXThread) o).getName (),
                    true,
                    false,
                    null
                );
            } else {
                synchronized(currentNodes) {
                    currentNodes.remove(o);
                }
                return ((JavaFXThread) o).getName ();
            }
        } else
        if (o instanceof JavaFXThreadGroup) {
            if (isCurrent ((JavaFXThreadGroup) o)) {
                synchronized(currentNodes) {
                    currentNodes.add(o);
                }
                return BoldVariablesTableModelFilterFirst.toHTML (
                    ((JavaFXThreadGroup) o).getName (),
                    true,
                    false,
                    null
                );
            } else {
                synchronized(currentNodes) {
                    currentNodes.remove(o);
                }
                return ((JavaFXThreadGroup) o).getName ();
            }
        } else 
        throw new UnknownTypeException (o);
    }
    
    public String getShortDescription (Object o) throws UnknownTypeException {
        if (o == TreeModel.ROOT) {
            return NbBundle.getBundle (ThreadsNodeModel.class).getString
                ("CTL_ThreadsModel_Column_Name_Desc");
        } else
        if (o instanceof JavaFXThread) {
            JavaFXThread t = (JavaFXThread) o;
            int i = t.getState ();
            String s = "";
            switch (i) {
                case JavaFXThread.STATE_UNKNOWN:
                    s = NbBundle.getBundle (ThreadsNodeModel.class).getString
                        ("CTL_ThreadsModel_State_Unknown");
                    break;
                case JavaFXThread.STATE_MONITOR:
                    ObjectVariable ov = t.getContendedMonitor ();
                    if (ov == null)
                        s = NbBundle.getBundle (ThreadsNodeModel.class).
                            getString ("CTL_ThreadsModel_State_Monitor");
                    else
                        try {
                            s = java.text.MessageFormat.
                                format (
                                    NbBundle.getBundle (ThreadsNodeModel.class).
                                        getString (
                                    "CTL_ThreadsModel_State_ConcreteMonitor"), 
                                    new Object [] { ov.getToStringValue () });
                        } catch (InvalidExpressionException ex) {
                            s = ex.getLocalizedMessage ();
                        }
                    break;
                case JavaFXThread.STATE_NOT_STARTED:
                    s = NbBundle.getBundle (ThreadsNodeModel.class).getString
                        ("CTL_ThreadsModel_State_NotStarted");
                    break;
                case JavaFXThread.STATE_RUNNING:
                    s = NbBundle.getBundle (ThreadsNodeModel.class).getString
                        ("CTL_ThreadsModel_State_Running");
                    break;
                case JavaFXThread.STATE_SLEEPING:
                    s = NbBundle.getBundle (ThreadsNodeModel.class).getString
                        ("CTL_ThreadsModel_State_Sleeping");
                    break;
                case JavaFXThread.STATE_WAIT:
                    ov = t.getContendedMonitor ();
                    if (ov == null)
                        s = NbBundle.getBundle (ThreadsNodeModel.class).
                            getString ("CTL_ThreadsModel_State_Waiting");
                    else
                        try {
                            s = java.text.MessageFormat.format
                                (NbBundle.getBundle (ThreadsNodeModel.class).
                                getString ("CTL_ThreadsModel_State_WaitingOn"), 
                                new Object [] { ov.getToStringValue () });
                        } catch (InvalidExpressionException ex) {
                            s = ex.getLocalizedMessage ();
                        }
                    break;
                case JavaFXThread.STATE_ZOMBIE:
                    s = NbBundle.getBundle (ThreadsNodeModel.class).getString
                        ("CTL_ThreadsModel_State_Zombie");
                    break;
            }
            if (t.isSuspended () && (t.getStackDepth () > 0)) {
                try { 
                    CallStackFrame sf = t.getCallStack (0, 1) [0];
                    s += " " + CallStackNodeModel.getCSFName (session, sf, true);
                } catch (AbsentInformationException e) {
                }
            }
            return s;
        } else
        if (o instanceof JavaFXThreadGroup) {
            return ((JavaFXThreadGroup) o).getName ();
        } else 
        throw new UnknownTypeException (o);
    }
    
    public String getIconBase (Object o) throws UnknownTypeException {
        if (o instanceof String) {
            return THREAD_GROUP;
        } else
        if (o instanceof JavaFXThread) {
            if (o == debugger.getCurrentThread ())
                return CURRENT_THREAD;
            return ((JavaFXThread) o).isSuspended () ? 
                SUSPENDED_THREAD : RUNNING_THREAD;
            
        } else
        if (o instanceof JavaFXThreadGroup) {
            if (isCurrent ((JavaFXThreadGroup) o))
                return CURRENT_THREAD_GROUP;
            else
                return THREAD_GROUP;
        } else 
        throw new UnknownTypeException (o);
    }

    /** 
     *
     * @param l the listener to add
     */
    public void addModelListener (ModelListener l) {
        listeners.add (l);
    }

    /** 
     *
     * @param l the listener to remove
     */
    public void removeModelListener (ModelListener l) {
        listeners.remove (l);
    }
    
    private void fireTreeChanged () {
        Vector v = (Vector) listeners.clone ();
        int i, k = v.size ();
        for (i = 0; i < k; i++)
            ((ModelListener) v.get (i)).modelChanged (null);
    }
    
    private void fireNodeChanged (Object node) {
        Vector v = (Vector) listeners.clone ();
        int i, k = v.size ();
        ModelEvent event = new ModelEvent.NodeChanged(this, node,
                ModelEvent.NodeChanged.DISPLAY_NAME_MASK |
                ModelEvent.NodeChanged.ICON_MASK |
                ModelEvent.NodeChanged.SHORT_DESCRIPTION_MASK);
        for (i = 0; i < k; i++)
            ((ModelListener) v.get (i)).modelChanged (event);
    }
    
    private boolean isCurrent (JavaFXThreadGroup tg) {
        JavaFXThread t = debugger.getCurrentThread ();
        if (t == null)
            return false;
        JavaFXThreadGroup ctg = t.getParentThreadGroup ();
        while (ctg != null) {
            if (ctg == tg) return true;
            ctg = ctg.getParentThreadGroup ();
        }
        return false;
    }

    
    // innerclasses ............................................................
    
    /**
     * Listens on DebuggerManager on PROP_CURRENT_ENGINE, and on 
     * currentTreeModel.
     */
    private static class Listener implements PropertyChangeListener {
        
        private WeakReference ref;
        private JavaFXDebugger debugger;
        Set<Object> currentNodes;
        
        private Listener (
            ThreadsNodeModel rm,
            JavaFXDebugger debugger,
            Set<Object> currentNodes
        ) {
            ref = new WeakReference (rm);
            this.debugger = debugger;
            this.currentNodes = currentNodes;
            debugger.addPropertyChangeListener (
                debugger.PROP_CURRENT_THREAD,
                this
            );
        }
        
        private ThreadsNodeModel getModel () {
            ThreadsNodeModel rm = (ThreadsNodeModel) ref.get ();
            if (rm == null) {
                debugger.removePropertyChangeListener (
                    debugger.PROP_CURRENT_THREAD,
                    this
                );
            }
            return rm;
        }
        
        public void propertyChange (PropertyChangeEvent e) {
            ThreadsNodeModel rm = getModel ();
            if (rm == null) return;
            List nodes;
            synchronized(currentNodes) {
                nodes = new ArrayList(currentNodes);
            }
            JavaFXThread t = debugger.getCurrentThread();
            if (t != null) {
                nodes.add(t);
                JavaFXThreadGroup tg = t.getParentThreadGroup();
                while (tg != null) {
                    nodes.add(tg);
                    tg = tg.getParentThreadGroup();
                }
            }
            for (Object node : nodes) {
                rm.fireNodeChanged (node);
            }
        }
    }
}