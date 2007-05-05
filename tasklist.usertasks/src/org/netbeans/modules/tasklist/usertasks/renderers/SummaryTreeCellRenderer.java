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

package org.netbeans.modules.tasklist.usertasks.renderers;

import org.netbeans.modules.tasklist.usertasks.table.UTTreeTableNode;
import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import org.netbeans.modules.tasklist.usertasks.model.UserTask;
import org.netbeans.modules.tasklist.usertasks.table.UTBasicTreeTableNode;
import org.netbeans.modules.tasklist.usertasks.table.UTTreeTableNode;
import org.netbeans.modules.tasklist.usertasks.treetable.AdvancedTreeTableNode;

/**
 * Cell renderer for the summary attribute
 * 
 * @author tl
 */
public class SummaryTreeCellRenderer extends DefaultTreeCellRenderer {
    private static final long serialVersionUID = 1;

    private Font boldFont, normalFont;
    private ImageIcon icon = new ImageIcon();
    
    public SummaryTreeCellRenderer() {
        ImageIcon icon = new ImageIcon();
        
        // see TreeTable.TreeTableCellEditor.getTableCellEditorComponent
        setLeafIcon(icon);
        setOpenIcon(icon);
        setClosedIcon(icon);
    }
    
    public Component getTreeCellRendererComponent(JTree tree, Object value,
				   boolean selected, boolean expanded,
				   boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selected, expanded,
            leaf, row, hasFocus);
        if (normalFont == null || !normalFont.equals(tree.getFont())) {
            normalFont = tree.getFont();
            boldFont = normalFont.deriveFont(Font.BOLD);
        }
        if (value instanceof UTBasicTreeTableNode) {
            UTBasicTreeTableNode utl = (UTBasicTreeTableNode) value;
            boolean unmatched = utl instanceof UTTreeTableNode &&
                    ((UTTreeTableNode) utl).isUnmatched();
            UserTask ut = utl.getUserTask();
            setFont(ut.isStarted() ? boldFont : normalFont);
            setText(ut.getSummary());
            setIcon(UserTaskIconProvider.getUserTaskImage(ut, 
                    unmatched));
        } else if (value instanceof AdvancedTreeTableNode) {
            setFont(normalFont);
            if (expanded)
                setIcon(((AdvancedTreeTableNode) value).getOpenIcon());
            else
                setIcon(((AdvancedTreeTableNode) value).getClosedIcon());                
        } else {
            setFont(normalFont);
            setIcon(null);
        }
        return this;
    }
}
