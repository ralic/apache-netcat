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

/*
 * NAME_SUBSTITUTION.java -- synopsis.
 *
 *
 * Date: 15.6.1998 12:22:29$
 * <<Revision>>
 *
 * SUN PROPRIETARY/CONFIDENTIAL:  INTERNAL USE ONLY.
 *
 * Copyright � 1997-1999 Sun Microsystems, Inc. All rights reserved.
 * Use is subject to license terms.
 */

package com.netbeans.enterprise.modules.corba.idl.src;

import java.util.Vector;

public class OperationElement extends IDLElement {

   private String op_attribute;
   //private Element op_type_spec;
   private IDLType op_type_spec;
   //private Element name;
   private Vector params;
   private Vector exceptions;
   private Vector contexts;

   public OperationElement(int id) {
      super(id);
      params = new Vector ();
      exceptions = new Vector ();
      contexts = new Vector ();
   }

   public OperationElement(IDLParser p, int id) {
      super(p, id);
      params = new Vector ();
      exceptions = new Vector ();
      contexts = new Vector ();
   }

   public void setAttribute (String attr) {
      op_attribute = attr;
   }

   public String getAttribute () {
      return op_attribute;
   }

   /*
   public void setReturnType (Element type) {
      op_type_spec = type;
   }

   public Element getReturnType () {
      return op_type_spec;
   }
   */

   public void setReturnType (IDLType type) {
      op_type_spec = type;
   }

   public IDLType getReturnType () {
      return op_type_spec;
   }

   public void setParameters (Vector ps) {
      params = ps;
   }

   public Vector getParameters () {
      return params;
   }

   public void setExceptions (Vector es) {
      exceptions = es;
   }

   public Vector getExceptions () {
      return exceptions;
   }


   public void setContexts (Vector cs) {
      contexts = cs;
   }

   public Vector getContexts () {
      return contexts;
   }

   public void jjtClose () {
      super.jjtClose ();
      for (int i=0; i<getMembers ().size (); i++) {
	 if (getMember (i) instanceof ParameterElement)
	    params.addElement ((ParameterElement)getMember (i));
      }
   }
   

}





/*
 * <<Log>>
 *  4    Gandalf   1.3         10/23/99 Ian Formanek    NO SEMANTIC CHANGE - Sun
 *       Microsystems Copyright in File Comment
 *  3    Gandalf   1.2         10/5/99  Karel Gardas    
 *  2    Gandalf   1.1         8/3/99   Karel Gardas    
 *  1    Gandalf   1.0         7/10/99  Karel Gardas    initial revision
 * $
 */
