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

package com.netbeans.enterprise.modules.corba.browser.ns;

import org.openide.nodes.*;
import org.openide.util.actions.*;
import org.openide.util.*;
import org.openide.*;


import com.netbeans.enterprise.modules.corba.*;

/*
 * @author Karel Gardas
 */
 
public class UnbindObject extends NodeAction {

   public static final boolean DEBUG = false;

  static final long serialVersionUID =3721620137072712461L;
   public UnbindObject () {
      super ();
   }

   protected boolean enable (org.openide.nodes.Node[] nodes) {
      if (nodes == null || nodes.length != 1)
	 return false;
      return (nodes[0].getCookie (ObjectNode.class) != null);
   }

   public String getName() {
      return NbBundle.getBundle (ContextNode.class).getString ("CTL_UnbindObject");
   }

   public HelpCtx getHelpCtx() {
      return HelpCtx.DEFAULT_HELP; // [PENDING]
   }

   protected void performAction (final Node[] activatedNodes) {
      if (DEBUG)
	 System.out.println ("UnbindObject.java");
      if (enable (activatedNodes)) {
	 try {
	    ((ObjectNode) activatedNodes[0].getCookie(ObjectNode.class)).unbind ();
	 } catch (Exception e) {
	    if (DEBUG)
	       e.printStackTrace ();
	    TopManager.getDefault ().notify (new NotifyDescriptor.Exception 
					     ((java.lang.Throwable) e));
	 }
      }
   }
   
}


/*
 * $Log
 * $
 */
