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

package org.netbeans.modules.debugger.callstackviewenhancements.ui.models;

import com.sun.jdi.StackFrame;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.netbeans.api.debugger.jpda.CallStackFrame;
import org.netbeans.api.debugger.jpda.This;
import org.netbeans.spi.viewmodel.ModelListener;
import org.netbeans.spi.viewmodel.NodeModel;
import org.netbeans.spi.viewmodel.NodeModelFilter;
import org.netbeans.spi.viewmodel.UnknownTypeException;

/**
 *
 * @author Sandip V.Chitale (Sandip.Chitale@Sun.Com)
 */
public class CallStackViewNodeModelFilter implements NodeModelFilter {
    private static boolean dontShowCustomIcons = Boolean.getBoolean("org.netbeans.modules.debugger.callstackviewenhancements.dontShowCustomIcons");

    public CallStackViewNodeModelFilter() {

    }

    public String getDisplayName(NodeModel original, Object node) throws UnknownTypeException {
        return original.getDisplayName(node);        
    }

    public String getIconBase(NodeModel original, Object node) throws UnknownTypeException {
        if (!dontShowCustomIcons) {
            if (node instanceof CallStackFrame) {
                com.sun.jdi.Method method = Utils.getMethod((CallStackFrame) node);
                if (method != null) {
                    if (method.isSynthetic()) {
                        return "org/netbeans/modules/java/navigation/resources/methods";
                    } else if (method.isStaticInitializer()) {
                        return "org/netbeans/modules/java/navigation/resources/initializerSt";
                    } else if (method.isStaticInitializer()) {
                        return "org/netbeans/modules/java/navigation/resources/initializer";
                    } else if (method.isConstructor()) {
                        if (method.isPrivate()) {
                            return "org/netbeans/modules/java/navigation/resources/constructorPrivate";
                        } else if (method.isProtected()) {
                            return "org/netbeans/modules/java/navigation/resources/constructorProtected";
                        } else if (method.isPublic()) {
                            return "org/netbeans/modules/java/navigation/resources/constructorPublic";
                        } else {
                            return "org/netbeans/modules/java/navigation/resources/constructorPackage";
                        }
                    } else if (method.isStatic()) {
                        if (method.isPrivate()) {
                            return "org/netbeans/modules/java/navigation/resources/methodStPrivate";
                        } else if (method.isProtected()) {
                            return "org/netbeans/modules/java/navigation/resources/methodStProtected";
                        } else if (method.isPublic()) {
                            return "org/netbeans/modules/java/navigation/resources/methodStPublic";
                        } else {
                            return "org/netbeans/modules/java/navigation/resources/methodStPackage";
                        }
                    } else {
                        if (method.isPrivate()) {
                            return "org/netbeans/modules/java/navigation/resources/methodPrivate";
                        } else if (method.isProtected()) {
                            return "org/netbeans/modules/java/navigation/resources/methodProtected";
                        } else if (method.isPublic()) {
                            return "org/netbeans/modules/java/navigation/resources/methodPublic";
                        } else {
                            return "org/netbeans/modules/java/navigation/resources/methodPackage";
                        }
                    }
                }
            }
        }
        return original.getIconBase(node);
    }

    public String getShortDescription(NodeModel original, Object node) throws UnknownTypeException {
        return original.getShortDescription(node);
    }

    public void addModelListener(ModelListener l) {
    }

    public void removeModelListener(ModelListener l) {
    }   
}
