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

import java.util.Vector;

public class ArrayDeclarator extends DeclaratorElement { // IDLElement {
  
  Vector _dimension;
  
  public ArrayDeclarator(int id) {
    super(id);
    _dimension = new Vector ();
  }

  public ArrayDeclarator(IDLParser p, int id) {
    super(p, id);
    _dimension = new Vector ();
  }

  public void setDimension (Vector dim) {
    _dimension = dim;
  }

  public Vector getDimension () {
    return _dimension;
  }

}
