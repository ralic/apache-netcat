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

/*
 * SwitchAction.java
 */

package org.netbeans.modules.perspective.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.netbeans.modules.perspective.ui.ToolbarStyleSwitchUI;
import org.netbeans.modules.perspective.utils.ModeController;
import org.netbeans.modules.perspective.views.Perspective;

/**
 *
 * @author Anuradha G
 */
public class SwitchAction extends AbstractAction {

    private static final long serialVersionUID = 1l;
    private Perspective perspective;
    private boolean fireToolbarUpdate;

    public SwitchAction(Perspective perspective) {
        putValue(NAME, perspective.getAlias());
        String path = perspective.getImagePath();
        if (path != null) {
            putValue(SMALL_ICON, new javax.swing.ImageIcon(getClass().getResource(path)));
        }
        this.perspective = perspective;
    }

    public SwitchAction(Perspective perspective, boolean fireToolbarUpdate) {
        this(perspective);
        this.fireToolbarUpdate = fireToolbarUpdate;
    }

    public void actionPerformed(ActionEvent e) {
        ModeController.getInstance().switchView(perspective);

        if (fireToolbarUpdate) {
            ToolbarStyleSwitchUI.getInstance().loadQuickPerspectives();
        }
    }
}