/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2001 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.corba.wizard.nodes.actions;

import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.NodeAction;
import org.netbeans.modules.corba.wizard.nodes.utils.UnionCreator;

/** Action sensitive to the node selection that does something useful.
 *
 * @author  root
 */
public class CreateUnionAction extends NodeAction implements org.netbeans.modules.corba.wizard.nodes.utils.Create {

    protected void performAction (Node[] nodes) {
        if (enable (nodes)) {
            ((UnionCreator)nodes[0].getCookie(UnionCreator.class)).createUnion();
        }
    }
  
    protected boolean enable (Node[] nodes) {
        return nodes.length == 1 && nodes[0].getCookie (UnionCreator.class) != null;
    }

    public String getName () {
        return java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/wizard/nodes/actions/Bundle").getString("TXT_CreateUnion");
    }
    
    public String toString () {
        return java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/wizard/nodes/actions/Bundle").getString("TXT_Union");
    }

    public HelpCtx getHelpCtx () {
        return HelpCtx.DEFAULT_HELP;
    }
    
    public boolean isEnabled(org.openide.nodes.Node[] nodes) {
        return enable (nodes);
    }
  
}
