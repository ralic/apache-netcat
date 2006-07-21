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
package test.general;

import org.netbeans.api.visual.action.MouseHoverAction;
import org.netbeans.api.visual.action.MoveAction;
import org.netbeans.api.visual.action.PopupMenuAction;
import org.netbeans.api.visual.anchor.RectangularAnchor;
import org.netbeans.api.visual.uml.UMLClassWidget;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.graph.GraphScene;

import javax.swing.*;
import java.awt.*;

/**
 * @author David Kaspar
 */
public class StringGraphScene extends GraphScene.StringGraph {

    private LayerWidget mainLayer;
    private LayerWidget connectionLayer;

    private MoveAction moveAction = new MoveAction ();
    private MouseHoverAction mouseHoverAction = new MyMouseHoverAction ();
    private PopupMenuAction popupMenuAction = new MyPopupMenuAction ();

    public StringGraphScene () {
        mainLayer = new LayerWidget (this);
        connectionLayer = new LayerWidget (this);
        addChild (mainLayer);
        addChild (connectionLayer);

        getActions ().addAction (mouseHoverAction);
    }

    protected Widget attachNodeWidget (String node) {
        UMLClassWidget widget = new UMLClassWidget (this);
        widget.setClassName ("Class" + node);
        widget.addMember (widget.createMember ("x: double"));
        widget.addMember (widget.createMember ("y: double"));
        widget.addMember (widget.createMember ("radius: double"));
        widget.addOperation (widget.createOperation ("move(x,y): void"));
        widget.addOperation (widget.createOperation ("paint(): void"));
        widget.addOperation (widget.createOperation ("isInside(x,y): boolean"));
        mainLayer.addChild (widget);

        widget.getActions ().addAction (moveAction);
        widget.getActions ().addAction (mouseHoverAction);
        widget.getActions ().addAction (popupMenuAction);

        return widget;
    }

    protected Widget attachEdgeWidget (String edge) {
        ConnectionWidget connectionWidget = new ConnectionWidget (this);
        connectionLayer.addChild (connectionWidget);
        return connectionWidget;
    }

    protected void attachEdgeSourceAnchor (String edge, String oldSourceNode, String sourceNode) {
        ((ConnectionWidget) findWidget (edge)).setSourceAnchor (RectangularAnchor.create (findWidget (sourceNode)));
    }

    protected void attachEdgeTargetAnchor (String edge, String oldTargetNode, String targetNode) {
        ((ConnectionWidget) findWidget (edge)).setTargetAnchor (RectangularAnchor.create (findWidget (targetNode)));
    }

    public LayerWidget getMainLayer () {
        return mainLayer;
    }

    public LayerWidget getConnectionLayer () {
        return connectionLayer;
    }

    private static class MyMouseHoverAction extends MouseHoverAction.TwoStated {

        protected void unsetHovering (Widget widget) {
            widget.setBackground (Color.WHITE);
        }

        protected void setHovering (Widget widget) {
            widget.setBackground (Color.CYAN);
        }

    }

    private static class MyPopupMenuAction extends PopupMenuAction {

        public JPopupMenu getPopupMenu (Widget widget) {
            JPopupMenu popupMenu = new JPopupMenu ();
            popupMenu.add (new JMenuItem ("Open " + ((UMLClassWidget) widget).getClassName ()));
            return popupMenu;
        }

    }

}
