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
package org.netbeans.modules.portalpack.portlets.genericportlets.storyboard.ipc.actions;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.netbeans.api.visual.action.PopupMenuProvider;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.modules.portalpack.portlets.genericportlets.storyboard.ipc.IPCGraphScene;
import org.netbeans.modules.portalpack.portlets.genericportlets.storyboard.widgets.CustomPinWidget;
import org.openide.util.NbBundle;

/**
 *
 * @author Satyaranjan
 */
public class EventPinPopUpMenuProvider implements PopupMenuProvider, ActionListener {

    private IPCGraphScene scene;
    private static final String ACTION_GENERATE_SOURCE = "Generate_Source"; //NOI18N

    private static final String ACTION_REMOVE_EVENT = "Remove_Event"; //NOI18N

    private static final String ACTION_ADD_ALIAS = "Add_Alias"; //NOI18N

    private JPopupMenu menu;
    private CustomPinWidget widget;

    /** Creates a new instance of NodePopUpMenuProvider */
    public EventPinPopUpMenuProvider(IPCGraphScene scene) {
        this.scene = scene;
        menu = new JPopupMenu(NbBundle.getMessage(EventPinPopUpMenuProvider.class, "MENU_POP_UP"));
        JMenuItem item;

        item = new JMenuItem(NbBundle.getMessage(EventPinPopUpMenuProvider.class, "MENU_GENERATE_PUBLISH_EVENT_SOURCE"));
        item.setActionCommand(ACTION_GENERATE_SOURCE);
        item.addActionListener(this);
        item.setBackground(Color.WHITE);
        menu.add(item);

        JMenuItem addAliasItem;
        addAliasItem = new JMenuItem(NbBundle.getMessage(EventPinPopUpMenuProvider.class, "MENU_ADD_ALIAS"));
        addAliasItem.setActionCommand(ACTION_ADD_ALIAS);
        addAliasItem.addActionListener(this);
        addAliasItem.setBackground(Color.WHITE);
        menu.add(addAliasItem);

        JMenuItem removeEventItem;
        removeEventItem = new JMenuItem(NbBundle.getMessage(EventPinPopUpMenuProvider.class, "MENU_REMOVE_EVENT"));
        removeEventItem.setActionCommand(ACTION_REMOVE_EVENT);
        removeEventItem.addActionListener(this);
        removeEventItem.setBackground(Color.WHITE);
        menu.add(removeEventItem);

    }

    public JPopupMenu getPopupMenu(Widget widget, Point localLocation) {
        if (widget instanceof CustomPinWidget) {
            this.widget = (CustomPinWidget) widget;
        }
        widget = null;
        return menu;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ACTION_GENERATE_SOURCE)) {
            if (widget == null) {
                return;
            }
            scene.getTaskHandler().generatePublishEventSource(widget.getNodeKey(), widget.getEvent());
        } else if (e.getActionCommand().equals(ACTION_REMOVE_EVENT)) {
            if (widget == null) {
                return;
            }
            scene.getTaskHandler().removePublishEventPinFromNode(widget);
        } else if (e.getActionCommand().equals(ACTION_ADD_ALIAS)) {
            if (widget == null) {
                return;
            }
            scene.getTaskHandler().addAliasForEvent(widget);
        }
    }
}
