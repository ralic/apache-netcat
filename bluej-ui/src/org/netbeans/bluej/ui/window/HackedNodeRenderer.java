/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2005 Sun
 * Microsystems, Inc. All Rights Reserved.
 */
package org.netbeans.bluej.ui.window;

import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.beans.BeanInfo;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;
import org.openide.awt.HtmlRenderer;
import org.openide.awt.ListPane;
import org.openide.explorer.view.NodeListModel;
import org.openide.explorer.view.NodeRenderer;
import org.openide.explorer.view.Visualizer;
import org.openide.nodes.Node;

/**
 *
 * @author mkleint
 */
public class HackedNodeRenderer extends NodeRenderer {

    private HtmlRenderer.Renderer renderer = HtmlRenderer.createRenderer();
    
    /** Creates a new instance of HackedNodeRenderer */
    public HackedNodeRenderer() {
    }
    
    /** This is the only method defined by <code>ListCellRenderer</code>.  We just
     * reconfigure the <code>Jlabel</code> each time we're called.
     */
    public Component getListCellRendererComponent(
        JList list, Object value, int index, boolean sel, boolean cellHasFocus
    ) {
        Node vis = findVisualizerNode(value);

        String text = vis.getHtmlDisplayName();
        boolean isHtml = text != null;

        if (!isHtml) {
            text = vis.getDisplayName();
        }

        //Get our result value - really it is ren, but this call causes
        //it to configure itself with the passed values
        Component result = renderer.getListCellRendererComponent(
                list, text, index, sel, cellHasFocus
            );
        renderer.setHtml(isHtml);
        result.setEnabled(list.isEnabled());

        //Do our additional configuration - set up the icon and possibly
        //do some hacks to make it look focused for TreeTableView
        int iconWidth = configureFrom(renderer, list, false, sel, vis);
//
//            //Indent elements in a ListView/ChoiceView relative to their position
//            //in the node tree.  Only does anything if you've subclassed and
//            //overridden createModel().  Does anybody do that?
//            if (list.getModel() instanceof NodeListModel && (((NodeListModel) list.getModel()).getDepth() > 1)) {
//                int indent = iconWidth * NodeListModel.findVisualizerDepth(list.getModel(), vis);
//
//                renderer.setIndent(indent);
//            }

        return result;
    }
    
    /** Utility method which performs configuration which is common to all of the renderer
     * implementations - sets the icon and focus properties on the renderer
     * from the VisualizerNode.
     *
     */
    private int configureFrom(
        HtmlRenderer.Renderer ren, Container target, boolean useOpenedIcon, boolean sel, Node vis
    ) {
        int iconType = BeanInfo.ICON_COLOR_16x16;//large ? BeanInfo.ICON_COLOR_32x32 : BeanInfo.ICON_COLOR_16x16;

        Image image = useOpenedIcon ? vis.getOpenedIcon(iconType) : vis.getIcon(iconType);
        Icon icon = new ImageIcon(image);
        if (icon.getIconWidth() > 24) {
            ren.setIconTextGap(4);
        } else if (icon.getIconWidth() > 0) {
            //Max annotated icon width is 24, so to have all the text and all
            //the icons come out aligned, set the icon text gap to the difference
            //plus a two pixel margin
            ren.setIconTextGap(24 - icon.getIconWidth());
        } else {
            //If the icon width is 0, fill the space and add in
            //the extra two pixels so the node names are aligned (btw, this
            //does seem to waste a frightful amount of horizontal space in
            //a tree that can use all it can get)
            ren.setIndent(26);
        }

        ren.setIcon(icon);

        return (icon.getIconWidth() == 0) ? 24 : icon.getIconWidth();
    }

    /** Utility method to find a visualizer node for the object passed to
     * any of the cell renderer methods as the value */
    private static final Node findVisualizerNode(Object value) {
        return Visualizer.findNode(value);
    }
    
}
