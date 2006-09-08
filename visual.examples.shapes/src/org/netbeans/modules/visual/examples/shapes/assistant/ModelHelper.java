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
package org.netbeans.modules.visual.examples.shapes.assistant;

/**
 *
 * @author Geertjan Wielenga
 */
public class ModelHelper {
    
    public static AssistantModel model = new AssistantModel();
    
    /** Creates a new instance of Model */
    public ModelHelper() {
    }
    
    /** Returns a model */
    public static AssistantModel returnAssistantModel() {
        return model;
    }
    
}
