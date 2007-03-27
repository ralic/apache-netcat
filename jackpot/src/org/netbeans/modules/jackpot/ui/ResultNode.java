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

package org.netbeans.modules.jackpot.ui;

import java.io.IOException;
import java.util.Enumeration;
import javax.swing.Icon;
import javax.swing.tree.*;
import org.netbeans.api.java.source.ModificationResult;
import org.netbeans.modules.jackpot.engine.Result;
import org.openide.text.PositionBounds;

/**
 * Copied from the refactoring module's CheckNode.
 */
public class ResultNode extends DefaultMutableTreeNode {

    public final static int SINGLE_SELECTION = 0;
    public final static int DIG_IN_SELECTION = 4;
  
    private int selectionMode;
    private boolean isSelected;

    private String nodeLabel;
    private Icon icon;
    
    private boolean disabled = false;
    private boolean needsRefresh = false;
    private boolean isRefactoring = false;
    
    private PositionBounds bounds = null;
    private String resourceName;
    
    public ResultNode(Object result, boolean isRefactoring, Icon icon) {
        super(result);
        this.isRefactoring = isRefactoring;
        this.isSelected = true;
        setSelectionMode(DIG_IN_SELECTION);
        this.icon = icon;
    }
    
    String getLabel() {
        return nodeLabel;
    }
    
    void setLabel(String label) {
        nodeLabel = label;
    }
    
    Icon getIcon() {
        return icon;
    }
    
    public void setDisabled() {
        disabled = true;
        isSelected = false;
        removeAllChildren();
    }
    
    boolean isDisabled() {
        return disabled;
    }

    void setNeedsRefresh() {
        needsRefresh = true;
        setDisabled();
    }
    
    boolean needsRefresh() {
        return needsRefresh;
    }
    
    public void setSelectionMode(int mode) {
        selectionMode = mode;
    }

    public int getSelectionMode() {
        return selectionMode;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        Result r = (Result)getUserObject();
        ResultNode root = (ResultNode)getRoot();
        ModificationResult mods = (ModificationResult)root.getUserObject();
        selectResults(isSelected, r, mods);
        if ((selectionMode == DIG_IN_SELECTION) && (children != null)) {
            Enumeration e = children.elements();      
            while (e.hasMoreElements()) {
                ResultNode node = (ResultNode)e.nextElement();
                node.setSelected(isSelected);
            }
        }
    }
    
    private void selectResults(boolean selected, Result r, ModificationResult mods) {
        int start = r.getStartPos();
        int end = r.getEndPos();
         for (ModificationResult.Difference diff : mods.getDifferences(r.getFileObject()))
             if (overlaps(start, end, diff.getStartPosition().getOffset()) ||
                 overlaps(start, end, diff.getEndPosition().getOffset()))
                 diff.exclude(!selected);
    }
    
    private boolean overlaps(int start, int end, int offset) {
        return start >= offset && end <= offset;
    }
    
    public boolean isRefactoring() {
        return isRefactoring;
    }

    public boolean isSelected() {
        return isSelected;
    }
    
    public PositionBounds getPosition() {
        return null;
    }
    
    private String tooltip;
    public String getToolTip() {
        if (tooltip==null) {
            PositionBounds bounds = getPosition();
            if (bounds != null) {
                int line;
                try {
                    line = bounds.getBegin().getLine() + 1;
                } catch (IOException ioe) {
                    return null;
                }
                tooltip = resourceName + ':' + line;
            }
        }
        return tooltip;
    }
}
