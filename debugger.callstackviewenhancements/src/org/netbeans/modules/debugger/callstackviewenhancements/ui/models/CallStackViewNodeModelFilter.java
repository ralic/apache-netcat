/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
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
 * @author Sandip V. Chitale (Sandip.Chitale@Sun.Com)
 */
public class CallStackViewNodeModelFilter implements NodeModelFilter {
    private static boolean dontShowCustomIcons = Boolean.getBoolean("org.netbeans.modules.debugger.callstackviewenhancements.dontShowCustomIcons");

    public CallStackViewNodeModelFilter() {

    }

    public String getDisplayName(NodeModel original, Object node) throws UnknownTypeException {
        String displayName = original.getDisplayName(node);
        if (node instanceof CallStackFrame) {
            CallStackFrame callStackFrame = (CallStackFrame) node;
            This thisOfCallStackFrame = callStackFrame.getThisVariable();
            if (thisOfCallStackFrame != null) {
                if (!callStackFrame.getClassName().equals(thisOfCallStackFrame.getType())) {
                    if (displayName.endsWith("<html>")) {
                        return displayName.substring(0, displayName.length() - 6)
                        + " [ "
                                + thisOfCallStackFrame.getType()
                                + " subclass of "
                                + callStackFrame.getClassName()
                                + " ]</html>";
                    } else {
                        return displayName
                                + " [ "
                                + thisOfCallStackFrame.getType()
                                + " subclass of "
                                + callStackFrame.getClassName()
                                + " ]";
                    }
                }
            }
            displayName = displayName
                    + " [ "
                    + callStackFrame.getClassName()
                    + " ]";
        }
        return displayName;
    }

    public String getIconBase(NodeModel original, Object node) throws UnknownTypeException {
        if (!dontShowCustomIcons) {
            if (node instanceof CallStackFrame) {
                com.sun.jdi.Method method = getModifiers((CallStackFrame) node);
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

    private com.sun.jdi.Method getModifiers(CallStackFrame callStackFrame) {
        Class callStackFrameClass = callStackFrame.getClass();
        try {
            Method method = callStackFrameClass.getMethod("getStackFrame", new Class[0]);
            try {
                Object stackFrameObject = method.invoke(callStackFrame, new Object[0]);
                if (stackFrameObject instanceof StackFrame) {
                    StackFrame stackFrame = (StackFrame) stackFrameObject;
                    com.sun.jdi.Method jdiMethod = stackFrame.location().method();
                    return jdiMethod;
                }
            } catch (IllegalArgumentException ex) {
            } catch (InvocationTargetException ex) {
            } catch (IllegalAccessException ex) {
            }
        } catch (SecurityException ex) {
        } catch (NoSuchMethodException ex) {
        }

        return null;
    }
}
