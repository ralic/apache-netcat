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

import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

/** Expand all tasks containing subtasks in the tasklist
 * @author Tor Norbye */
public class ExpandAllAction extends CallableSystemAction {
    
    /** Do the actual expansion
	@todo Make this thing work from a context menu (such that
	      it acts on the right list. Alternatively, make it
	      expand ALL lists.
     */    
    public void performAction() {
        TaskListView view = TaskListView.getCurrent();
        if (view != null) {
	    view.expandAll();
	}
    }
    
    /** Return name of the action, as shown in menus etc. */    
    public String getName() {
        return NbBundle.getMessage(ExpandAllAction.class, 
                                   "LBL_ExpandAll"); // NOI18N
    }

    /*
    protected String iconResource() {
        return "org/netbeans/modules/tasklist/core/task.gif"; // NOI18N
    }
    */
    
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
        // If you will provide context help then use:
        // return new HelpCtx (MyAction.class);
    }
}
