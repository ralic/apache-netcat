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

package org.netbeans.modules.corba.wizard.nodes.actions;

import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.NodeAction;
import org.netbeans.modules.corba.wizard.nodes.utils.EnumEntryCreator;

/** Action sensitive to the node selection that does something useful.
 *
 * @author  root
 */
public class CreateEnumEntryAction extends NodeAction implements org.netbeans.modules.corba.wizard.nodes.utils.Create {

    protected void performAction (Node[] nodes) {
        if ( enable ( nodes)) {
            ((EnumEntryCreator) nodes[0].getCookie (EnumEntryCreator.class)).createEnumEntry ();
        }
    }
  
    protected boolean enable (Node[] nodes) {
        return nodes.length == 1 && nodes[0].getCookie (EnumEntryCreator.class) != null;
    }

    public String getName () {
        return java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/wizard/nodes/actions/Bundle").getString("TXT_CreateMember");
    }
    
    public String toString () {
        return java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/wizard/nodes/actions/Bundle").getString("TXT_EnumEntry");
    }

    public HelpCtx getHelpCtx () {
        return HelpCtx.DEFAULT_HELP;
    }
    
    public boolean isEnabled(org.openide.nodes.Node[] nodes) {
        return enable (nodes);
    }
  
}
