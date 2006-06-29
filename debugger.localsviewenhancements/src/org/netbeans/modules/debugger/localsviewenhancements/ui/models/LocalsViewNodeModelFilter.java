/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.debugger.localsviewenhancements.ui.models;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.netbeans.api.debugger.jpda.Field;
import org.netbeans.api.debugger.jpda.This;
import org.netbeans.api.debugger.jpda.Variable;
import org.netbeans.spi.viewmodel.ModelListener;
import org.netbeans.spi.viewmodel.NodeModel;
import org.netbeans.spi.viewmodel.NodeModelFilter;
import org.netbeans.spi.viewmodel.UnknownTypeException;

/**
 *
 * @author Sandip V. Chitale (Sandip.Chitale@Sun.Com)
 */
public class LocalsViewNodeModelFilter implements NodeModelFilter {
    private static boolean dontShowCustomIcons = Boolean.getBoolean("org.netbeans.modules.debugger.localsviewenhancements.dontShowCustomIcons");
    public LocalsViewNodeModelFilter() {
        
    }
    
    public String getDisplayName(NodeModel original, Object node) throws UnknownTypeException {
        return original.getDisplayName(node);
    }
    
    public String getIconBase(NodeModel original, Object node) throws UnknownTypeException {
        if (!dontShowCustomIcons) {
            if (node instanceof This) {
                return "org/netbeans/modules/java/navigation/resources/class";
            } else if (node instanceof Field) {
                int modifiers = getModifiers((Field) node);
                if (Modifier.isStatic(modifiers)) {
                    if (Modifier.isPrivate(modifiers)){
                        return "org/netbeans/modules/java/navigation/resources/variableStPrivate";
                    } else if (Modifier.isProtected(modifiers)){
                        return "org/netbeans/modules/java/navigation/resources/variableStProtected";
                    } else if (Modifier.isPublic(modifiers)){
                        return "org/netbeans/modules/java/navigation/resources/variableStPublic";
                    } else {
                        return "org/netbeans/modules/java/navigation/resources/variableStPackage";
                    }
                } else {
                    if (Modifier.isPrivate(modifiers)){
                        return "org/netbeans/modules/java/navigation/resources/variablePrivate";
                    } else if (Modifier.isProtected(modifiers)){
                        return "org/netbeans/modules/java/navigation/resources/variableProtected";
                    } else if (Modifier.isPublic(modifiers)){
                        return "org/netbeans/modules/java/navigation/resources/variablePublic";
                    } else {
                        return "org/netbeans/modules/java/navigation/resources/variablePackage";
                    }
                }
            } else if (node instanceof Variable) {
                return "org/netbeans/modules/java/navigation/resources/variables";
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
    
    private int getModifiers(Field node) {
        Class nodeClass = node.getClass();
        java.lang.reflect.Field fieldField = null;
        
        while (nodeClass != null) {
            try {
                fieldField = nodeClass.getDeclaredField("field");
            } catch (SecurityException ex) {
                break;
            } catch (NoSuchFieldException ex) {
            }
            nodeClass = nodeClass.getSuperclass();
        }
        
        if (fieldField != null) {
            fieldField.setAccessible(true);
            try {
                Object fieldFieldValue = fieldField.get(node);
                Class fieldFieldValueClass = fieldFieldValue.getClass();
                try {
                    Method method = fieldFieldValueClass.getMethod("modifiers", new Class[0]);
                    try {
                        Integer modifiersInteger = (Integer) method.invoke(fieldFieldValue, new Object[0]);
                        return modifiersInteger.intValue();
                    } catch (IllegalArgumentException ex) {
                    } catch (InvocationTargetException ex) {
                    } catch (IllegalAccessException ex) {
                    }
                } catch (SecurityException ex) {
                } catch (NoSuchMethodException ex) {
                }
            } catch (IllegalArgumentException ex) {
            } catch (IllegalAccessException ex) {
            }
        }
        return 0;
    }
}
