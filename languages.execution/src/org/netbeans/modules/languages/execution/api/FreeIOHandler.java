/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2008 Sun Microsystems, Inc. All rights reserved.
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
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2008 Sun
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

package org.netbeans.modules.languages.execution.api;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;
import org.openide.util.Exceptions;
import org.openide.windows.InputOutput;

final class FreeIOHandler {
    
    /**
     * All tabs which were used for some process which has now ended.
     * These are closed when you start a fresh process.
     * Map from tab to tab display name.
     * @see "#43001"
     */
    private static final Map<InputOutput, Data> FREE_IOS =
            new WeakHashMap<InputOutput, Data>();

    private final InputOutput io;
    private final Data data;
    
    private FreeIOHandler(final InputOutput io, final Data data) {
        this.io = io;
        this.data = data;
    }
    
    InputOutput getIO() {
        return io;
    }

    String getDisplayName() {
        return data.displayName;
    }

    StopAction getStopAction() {
        return data.stopAction;
    }

    RerunAction getRerunAction() {
        return data.rerunAction;
    }
    
    static void addFreeIO(InputOutput io, String displayName, StopAction stopAction, RerunAction rerunAction) {
        synchronized (FREE_IOS) {
            FREE_IOS.put(io, new Data(displayName, stopAction, rerunAction));
        }
    }

    /**
     * Tries to find free Output Window tab for the given name.
     * 
     * @param name the name of the free tab. Other free tabs are ignored.
     * @return free tab and its current display name or <tt>null</tt>
     */
    static FreeIOHandler findFreeIO(final String name, boolean actions) {
        FreeIOHandler result = null;
        synchronized (FREE_IOS) {
            for (Iterator<Entry<InputOutput, Data>> it = FREE_IOS.entrySet().iterator(); it.hasNext();) {
                Entry<InputOutput, Data> entry = it.next();
                final InputOutput freeIO = entry.getKey();
                final Data data = entry.getValue();
                if (freeIO.isClosed()) {
                    it.remove();
                    continue;
                }

                if (result == null && ExecutionService.isAppropriateName(name, data.displayName)) {
                    if ((actions && data.rerunAction != null && data.stopAction != null)
                            || !actions && data.rerunAction == null && data.stopAction == null) {
                        // Reuse it.
                        result = new FreeIOHandler(freeIO, data);
                        try {
                            freeIO.getOut().reset();
                        } catch (IOException ioe) {
                            Exceptions.printStackTrace(ioe);
                        }
                        it.remove();
                    }
                    // continue to remove all closed tabs
                }// else {
            // if ('auto close tabs' options implemented and checked) { // see #47753
            //   free.io.closeInputOutput();
            // }
            //}
            }
        }
        return result;
    }

    private static class Data {
        
        final String displayName;
        final StopAction stopAction;
        final RerunAction rerunAction;
        
        Data(final String displayName, final StopAction stopAction, final RerunAction rerunAction) {
            this.displayName = displayName;
            this.stopAction = stopAction;
            this.rerunAction = rerunAction;
        }
    }
}    
