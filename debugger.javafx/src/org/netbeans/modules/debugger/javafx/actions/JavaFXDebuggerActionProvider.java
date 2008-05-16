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

package org.netbeans.modules.debugger.javafx.actions;

import com.sun.jdi.ThreadReference;
import com.sun.jdi.VMDisconnectedException;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.request.EventRequestManager;
import com.sun.jdi.request.InvalidRequestStateException;
import com.sun.jdi.request.StepRequest;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.netbeans.modules.debugger.javafx.JavaFXDebuggerImpl;
import org.netbeans.modules.debugger.javafx.JavaFXStepImpl.SingleThreadedStepWatch;
import org.netbeans.spi.debugger.ActionsProviderSupport;
import org.openide.util.RequestProcessor;
import org.openide.util.WeakSet;


/**
* Representation of a debugging session.
*
* @author   Jan Jancura
* @author  Marian Petras
*/
abstract class JavaFXDebuggerActionProvider extends ActionsProviderSupport 
implements PropertyChangeListener {
    
    private JavaFXDebuggerImpl debugger;
    
    /** The ReqeustProcessor used by action performers. */
    private static RequestProcessor actionsRequestProcessor;
    
    private static Set<JavaFXDebuggerActionProvider> providersToDisableOnLazyActions = new WeakSet<JavaFXDebuggerActionProvider>();
    
    private volatile boolean disabled;
    
    JavaFXDebuggerActionProvider (JavaFXDebuggerImpl debugger) {
        this.debugger = debugger;
        debugger.addPropertyChangeListener (debugger.PROP_STATE, this);
    }
    
    public void propertyChange (PropertyChangeEvent evt) {
        if (debugger.getState() == JavaFXDebuggerImpl.STATE_DISCONNECTED) {
            synchronized (JavaFXDebuggerActionProvider.class) {
                if (actionsRequestProcessor != null) {
                    actionsRequestProcessor.stop();
                    actionsRequestProcessor = null;
                }
            }
        }
        checkEnabled (debugger.getState ());
    }
    
    protected abstract void checkEnabled (int debuggerState);
    
    public boolean isEnabled (Object action) {
        if (!disabled) {
            checkEnabled (debugger.getState ());
        }
        return super.isEnabled (action);
    }
    
    JavaFXDebuggerImpl getDebuggerImpl () {
        return debugger;
    }
    
    protected void removeStepRequests (ThreadReference tr) {
        //S ystem.out.println ("removeStepRequests");
        try {
            VirtualMachine vm = getDebuggerImpl ().getVirtualMachine ();
            if (vm == null) return;
            EventRequestManager erm = vm.eventRequestManager ();
            List<StepRequest> l = erm.stepRequests ();
            Iterator<StepRequest> it = l.iterator ();
            while (it.hasNext ()) {
                StepRequest stepRequest = it.next ();
                if (stepRequest.thread ().equals (tr)) {
                    //S ystem.out.println("  remove request " + stepRequest);
                    erm.deleteEventRequest (stepRequest);
                    SingleThreadedStepWatch.stepRequestDeleted(stepRequest);
                    getDebuggerImpl().getOperator().unregister(stepRequest);
                    break;
                }
                //S ystem.out.println("  do not remove " + stepRequest + " : " + stepRequest.thread ());
            }
        } catch (VMDisconnectedException e) {
        } catch (IllegalThreadStateException e) {
            e.printStackTrace();
        } catch (InvalidRequestStateException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Mark the provided action provider to be disabled when a lazy action is to be performed.
     */
    protected final void setProviderToDisableOnLazyAction(JavaFXDebuggerActionProvider provider) {
        synchronized (JavaFXDebuggerActionProvider.class) {
            providersToDisableOnLazyActions.add(provider);
        }
    }
    
    /**
     * Do the action lazily in a RequestProcessor.
     * @param run The action to perform.
     */
    protected final void doLazyAction(final Runnable run) {
        final Set<JavaFXDebuggerActionProvider> disabledActions;
        synchronized (JavaFXDebuggerActionProvider.class) {
            if (actionsRequestProcessor == null) {
                actionsRequestProcessor = new RequestProcessor("JavaFX Processor", 1); // NOI18N
            }
            disabledActions = new HashSet<JavaFXDebuggerActionProvider>(providersToDisableOnLazyActions);
        }
        for (Iterator<JavaFXDebuggerActionProvider> it = disabledActions.iterator(); it.hasNext(); ) {
            JavaFXDebuggerActionProvider ap = it.next();
            Set actions = ap.getActions();
            ap.disabled = true;
            for (Iterator ait = actions.iterator(); ait.hasNext(); ) {
                Object action = ait.next();
                ap.setEnabled (action, false);
                //System.out.println(ap+".setEnabled("+action+", "+false+")");
            }
        }
        actionsRequestProcessor.post(new Runnable() {
            public void run() {
                try {
                    run.run();
                    for (Iterator<JavaFXDebuggerActionProvider> it = disabledActions.iterator(); it.hasNext(); ) {
                        JavaFXDebuggerActionProvider ap = it.next();
                        Set actions = ap.getActions();
                        ap.disabled = false;
                        ap.checkEnabled (debugger.getState ());
                    }
                } catch (com.sun.jdi.VMDisconnectedException e) {
                    // Causes kill action when something is being evaluated
                }
            }
        });
    }
}
