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

import com.sun.jdi.AbsentInformationException;
import java.util.Collections;
import java.util.Set;
import org.netbeans.api.debugger.ActionsManager;

import org.netbeans.modules.debugger.javafx.JavaFXDebuggerImpl;
import org.netbeans.api.debugger.javafx.JavaFXDebugger;
import org.netbeans.api.debugger.javafx.JavaFXThread;
import org.netbeans.spi.debugger.ContextProvider;


/**
* Representation of a debugging session.
*
* @author   Jan Jancura
*/
public class PopToHereActionProvider extends JavaFXDebuggerActionProvider {
    
    private ContextProvider lookupProvider;

    
    public PopToHereActionProvider (ContextProvider lookupProvider) {
        super (
            (JavaFXDebuggerImpl) lookupProvider.lookupFirst 
                (null, JavaFXDebugger.class) 
        );
        this.lookupProvider = lookupProvider;
        setProviderToDisableOnLazyAction(this);
    }
    
    public Set getActions () {
        return Collections.singleton (ActionsManager.ACTION_POP_TOPMOST_CALL);
    }

    public void doAction (Object action) {
        runAction();
    }
    
    public void postAction(Object action, final Runnable actionPerformedNotifier) {
        doLazyAction(new Runnable() {
            public void run() {
                try {
                    runAction();
                } finally {
                    actionPerformedNotifier.run();
                }
            }
        });
    }
    
    public void runAction() {
        try {
            JavaFXThread t = getDebuggerImpl ().getCurrentThread ();
            t.getCallStack (0, 1) [0].popFrame ();
        } catch (AbsentInformationException ex) {
        }
    }
    
    protected void checkEnabled (int debuggerState) {
        if (!getDebuggerImpl().canPopFrames()) {
            setEnabled (
                ActionsManager.ACTION_POP_TOPMOST_CALL,
                false
            );
            return;
        }
        JavaFXThread t;
        synchronized (getDebuggerImpl().LOCK) {
            if (debuggerState == getDebuggerImpl ().STATE_STOPPED) {
                t = getDebuggerImpl ().getCurrentThread ();
            } else {
                t = null;
            }
        }
        boolean enabled;
        if (t == null) {
            enabled = false;
        } else {
            synchronized (t) {
                if (!t.isSuspended()) {
                    enabled = false;
                } else {
                    enabled = t.getStackDepth () > 1;
                }
            }
        }
        setEnabled (
            ActionsManager.ACTION_POP_TOPMOST_CALL,
            enabled
        );
    }
}