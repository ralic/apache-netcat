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

package org.netbeans.modules.corba.wizard.nodes.keys;

/** 
 *
 * @author  root
 * @version 
 */
public class EnumKey extends NamedKey {
  
    private String initialValues;

    /** Creates new EnumKey */
    public EnumKey (int type, String name, String values) {
        super (type, name);
        this.initialValues = values;
    }
  
    public String getValues () {
        return this.initialValues;
    }
    
    /** getValuesAndClear
     * Does the same job as getValues but gives a chance to GC
     * to free initialValues
     */
    public String getValuesAndClear () {
        String tmp = this.initialValues;
        this.initialValues = null;
        return tmp;
    }
  
    public String toString () {
        return "EnumKey: "+ name; // No I18N
    }
  
}
