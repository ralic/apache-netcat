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

import org.netbeans.api.visual.graph.GraphScene;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.anchor.Anchor;
import org.netbeans.api.visual.vmd.VMDNodeAnchor;
import org.netbeans.api.visual.router.RouterFactory;
import org.netbeans.api.visual.router.Router;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.WidgetAction;
import test.SceneSupport;

import java.awt.*;
import java.util.HashMap;

/**
 * @author David Kaspar
 */
public class LoopEdgeTest extends GraphScene.StringGraph {

    private LayerWidget mainLayer;
    private LayerWidget connectionLayer;
    private Router router;
    private WidgetAction moveAction;
    private HashMap<String, Anchor> anchors;

    public LoopEdgeTest () {
        mainLayer = new LayerWidget (this);
        addChild (mainLayer);
        connectionLayer = new LayerWidget (this);
        addChild (connectionLayer);
        router = RouterFactory.createOrthogonalSearchRouter (mainLayer, connectionLayer);
        moveAction = ActionFactory.createMoveAction ();
        anchors = new HashMap<String, Anchor> ();
    }

    public static void main (String[] args) {
        LoopEdgeTest scene = new LoopEdgeTest ();

        scene.addNode ("n1");
        scene.addEdge ("e1");
        scene.setEdgeSource ("e1", "n1");
        scene.setEdgeTarget ("e1", "n1");

        SceneSupport.show (scene);
    }

    protected Widget attachNodeWidget (String node) {
        LabelWidget widget = new LabelWidget (this, "| " + node + " |");
        widget.setOpaque (true);
        widget.setBackground (Color.YELLOW);
        widget.setPreferredLocation (new Point (100, 100));
        widget.getActions ().addAction (moveAction);
        mainLayer.addChild (widget);
        anchors.put (node, new VMDNodeAnchor (widget));
        return widget;
    }

    protected void detachNodeWidget (String node, Widget widget) {
        super.detachNodeWidget (node, widget);
        anchors.remove (node);
    }

    protected Widget attachEdgeWidget (String edge) {
        ConnectionWidget widget = new ConnectionWidget (this);
        widget.setRouter (router);
        widget.setTargetAnchorShape (AnchorShape.TRIANGLE_FILLED);
        connectionLayer.addChild (widget);
        return widget;
    }

    protected void attachEdgeSourceAnchor (String edge, String oldSourceNode, String sourceNode) {
        ((ConnectionWidget) findWidget (edge)).setSourceAnchor (anchors.get (sourceNode));
    }

    protected void attachEdgeTargetAnchor (String edge, String oldTargetNode, String targetNode) {
        ((ConnectionWidget) findWidget (edge)).setTargetAnchor (anchors.get (targetNode));
    }

}
