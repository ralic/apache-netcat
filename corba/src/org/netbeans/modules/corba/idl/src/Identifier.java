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

package com.netbeans.enterprise.modules.corba.idl.src;

public class Identifier extends IDLElement {

   public Identifier(int id) {
      super(id);
   }
   
   public Identifier(IDLParser p, int id) {
      super(p, id);
   }
   
   public String toString () {
      //return "id: " + getName ();
      return getName ();
   }

}
