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

package com.netbeans.enterprise.modules.corba.idl.generator;


/*
 * @author Karel Gardas
 */
 
public class SymbolNotFoundException extends Exception {

  String name;

  static final long serialVersionUID =-5758146660118026855L;
  public SymbolNotFoundException (String symbol) {
    super (symbol);
    name = symbol;
  }

  public String getSymbolName () {
    return name;
  }
}


/*
 * $Log
 * $
 */
