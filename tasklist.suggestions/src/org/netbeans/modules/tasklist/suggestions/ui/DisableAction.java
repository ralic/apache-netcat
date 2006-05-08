/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2000 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.tasklist.suggestions.ui;

import java.util.Iterator;
import java.util.HashSet;
import org.netbeans.modules.tasklist.client.SuggestionManager;
import org.netbeans.modules.tasklist.core.TaskNode;
import org.netbeans.modules.tasklist.suggestions.*;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.NodeAction;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;


/**
 * Disables selected category.
 *
 * @author Tor Norbye
 */

public final class DisableAction extends NodeAction {

    private static final long serialVersionUID = 1;

    protected boolean asynchronous() {
        return false;
    }

    protected boolean enable(Node[] node) {
        return true;
    }
    
    protected void performAction(Node[] node) {
        if (node == null) {
            return;
        }

        // Remove suggestion when we've performed it
        SuggestionManagerImpl manager =
            (SuggestionManagerImpl)SuggestionManager.getDefault();

        HashSet set = new HashSet(10);

        // Compute the set of types we're disabling
        for (int i = 0; i < node.length; i++) {
            SuggestionImpl s = (SuggestionImpl)TaskNode.getTask(node[i]);
            SuggestionType type = s.getSType();
            String message = NbBundle.getMessage(DisableAction.class, 
                "ConfirmDisable", type.getLocalizedName()); // NOI18N
            String title = NbBundle.getMessage(DisableAction.class, 
                "ConfirmDisableTitle"); // NOI18N
            NotifyDescriptor desc = new NotifyDescriptor.Confirmation(
                 message, title, NotifyDescriptor.YES_NO_OPTION);
            if (!NotifyDescriptor.YES_OPTION.equals(
               DialogDisplayer.getDefault().notify(desc))) {
                continue;
            }
            set.add(s.getSType());
        }
        // Disable each type
        Iterator it = set.iterator();
        while (it.hasNext()) {
            SuggestionType type = (SuggestionType)it.next();
            manager.setEnabled(type.getName(), false, false);
        }
    }
    
    public String getName() {
        return NbBundle.getMessage(DisableAction.class, "LBL_Disable"); // NOI18N
    }

    protected String iconResource() {
        return "org/netbeans/modules/tasklist/suggestions/disableAction.gif"; // NOI18N
    }

    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
        // If you will provide context help then use:
        // return new HelpCtx(DisableAction.class);
    }
    
}
