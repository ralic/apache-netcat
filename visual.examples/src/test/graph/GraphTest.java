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
package test.graph;

import test.general.StringGraphPinScene;
import test.SceneSupport;
import org.netbeans.api.visual.widget.LabelWidget;

/**
 * @author David Kaspar
 */
public class GraphTest {

    public static void main (String[] args) {
        StringGraphPinScene scene = new StringGraphPinScene ();

        scene.addNode ("n1");
        scene.addPin ("n1", "p1");
        scene.addPin ("n1", "p2");
        scene.removeNode ("n1");

        scene.addChild (new LabelWidget (scene, "This scene has to be empty (except this label)."));

        SceneSupport.show (scene);
    }

}
