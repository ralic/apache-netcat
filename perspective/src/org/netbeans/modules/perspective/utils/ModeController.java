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
 */ /*
 * ModeController.java
 *
 */

package org.netbeans.modules.perspective.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.netbeans.modules.perspective.persistence.PerspectivePreferences;
import org.netbeans.modules.perspective.views.Perspective;
import org.netbeans.modules.perspective.views.PerspectiveListener;
import org.netbeans.modules.perspective.views.View;
import org.openide.windows.Mode;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author Anuradha G
 */
class ModeController {

    private WindowManager windowManager;
    private static ModeController instance;

    static synchronized ModeController getInstance() {
        if (instance == null) {
            instance = new ModeController();
        }
        return instance;
    }

    private ModeController() {
        windowManager = WindowManager.getDefault();
    }

    private void dock(String mode, String id, boolean open, boolean active) {
        Mode windowMode = windowManager.findMode(mode);

        TopComponent topComponent = windowManager.findTopComponent(id);

        if (windowMode == null || topComponent == null) {
            return;
        }
        windowMode.dockInto(topComponent);

        if (open) {
            topComponent.open();
            if (active) {
                topComponent.requestVisible();
            }
        } else {
            topComponent.close();
        }
    }
    Perspective selected;

    public void switchView(Perspective perspective) {
        List<PerspectiveListener> perspetiveListners = null;
        if (perspective == null) {
            return;
        }
        if (selected != null) {
            //Notify closing
            perspetiveListners = selected.getPerspectiveListeners();
            for (PerspectiveListener perspetiveListner : perspetiveListners) {
                perspetiveListner.perspetiveOpening();
            }
            if (PerspectivePreferences.getInstance().isTrackOpened()) {
                //track opened Tc to perspective
                new OpenedViewTracker(selected);
            }
        }
        perspetiveListners = perspective.getPerspectiveListeners();
        for (PerspectiveListener perspetiveListner : perspetiveListners) {
            perspetiveListner.perspetiveOpening();
        }

        selected = perspective;
        if (PerspectivePreferences.getInstance().isCloseOpened()) {
            //close opened TC's
            closeAll();
        }
        //begin switch
        List<View> views = perspective.getViews();
        Map<String, String> activeTCs = perspective.getActiveTCs();
        for (View view : views) {
            dock(view.getMode(), view.getTopcomponentID(), view.isOpen(), activeTCs.containsValue(view.getTopcomponentID()));
        }
        //end switch
    }

    private void closeAll() {
        Set<TopComponent> opened = windowManager.getRegistry().getOpened();
        List<TopComponent> tcs = new ArrayList<TopComponent>(opened);
        for (TopComponent tc : tcs) {

            if (windowManager.isEditorTopComponent(tc)) {
                continue;
            }
            tc.close();
        }
    }
}