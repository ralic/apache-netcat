/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2003 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.tasklist.core;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 * ListCellRenderer for priorities
 *
 * @author Tim Lebedkov
 */
public class PriorityListCellRenderer extends DefaultListCellRenderer {
    private static final String[] TAGS = Task.getPriorityNames();
    
    /**
     * Default colors for diferent priorities
     * [0] - high, [1] - medium-high, ...
     */
    public static final Color[] COLORS = {
        new Color(221, 0, 0),
        new Color(255, 128, 0),
        Color.black,
        new Color(0, 187, 0),
        new Color(0, 128, 0)
    };
    
    public Component getListCellRendererComponent(JList list, Object value,
    int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(
            list, value, index, isSelected, cellHasFocus);
        if (index >= 0) {
            setText(TAGS[index]);
            setForeground(COLORS[index]);
        } else {
            setText((String) value);
        }
        return this;
    }
}
