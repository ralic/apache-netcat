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

package org.netbeans.modules.corba.idl.node;

import java.util.Vector;

import org.openide.nodes.Children;
import org.openide.nodes.CookieSet;
import org.openide.nodes.Sheet;
import org.openide.nodes.PropertySupport;

import org.openide.util.actions.SystemAction;
import org.openide.actions.OpenAction;

import org.netbeans.modules.corba.idl.src.IDLElement;
import org.netbeans.modules.corba.idl.src.InitDclElement;
import org.netbeans.modules.corba.idl.src.InitParamDeclElement;
import org.netbeans.modules.corba.idl.src.DeclaratorElement;

/**
 * Class IDLInitDclNode
 *
 * @author Karel Gardas
 */
public class IDLInitDclNode extends IDLAbstractNode {

    InitDclElement _M_init_dcl_element;
    private static final String OPERATION_ICON_BASE =
        "org/netbeans/modules/corba/idl/node/factory"; // NOI18N

    public IDLInitDclNode (InitDclElement value) {
        //super (new IDLDocumentChildren ((SimpleNode)value));
        super (Children.LEAF);
        setIconBase (OPERATION_ICON_BASE);
        _M_init_dcl_element = value;
        setCookieForDataObject (_M_init_dcl_element.getDataObject ());
    }

    public IDLElement getIDLElement () {
        return _M_init_dcl_element;
    }
    /*
      public String getDisplayName () {
      if (_M_init_dcl_element != null)
      return _M_init_dcl_element.getName();
      //	 return ((Identifier)_M_init_dcl_element.jjtGetChild (0)).getName ();
      else
      return "NoName :)";
      }
    */
    public String getName () {
        return "factory"; // NOI18N
    }

    public SystemAction getDefaultAction () {
        SystemAction result = super.getDefaultAction();
        return result == null ? SystemAction.get(OpenAction.class) : result;
    }

    protected Sheet createSheet () {
        Sheet s = Sheet.createDefault ();
        Sheet.Set ss = s.get (Sheet.PROPERTIES);
        ss.put (new PropertySupport.ReadOnly ("name", String.class, IDLNodeBundle.NAME, IDLNodeBundle.NAME_OF_FACTORY) { // NOI18N
		public Object getValue () {
		    return _M_init_dcl_element.getName ();
		}
	    });
        ss.put (new PropertySupport.ReadOnly ("params", String.class, IDLNodeBundle.PARAMETERS, // NOI18N
                                              IDLNodeBundle.PARAMETERS_OF_FACTORY) {
		public Object getValue () {
		    try {
			if (_M_init_dcl_element != null) {
			    String __params = ""; // NOI18N
			    Vector __members = _M_init_dcl_element.getMembers ();
			    for (int __i=1; __i<__members.size (); __i++) {
				InitParamDeclElement __param 
				    = (InitParamDeclElement)__members.elementAt (__i);
				int __index_of_declarator = 0;
				if (!(__param.getMembers ().get (0)
				      instanceof DeclaratorElement))
				    __index_of_declarator = 1;
				DeclaratorElement __declarator
				    = (DeclaratorElement)__param.getMembers ().get
				    (__index_of_declarator);
				__params = __params + "in "
				    + __param.getType ().getName () + " " // NOI18N
				    + __declarator.getName () + ", "; // NOI18N
			    }
			    // if operation has some parameters we will destroy last ", " // NOI18N
			    if (!__params.equals ("")) // NOI18N
				__params = __params.substring (0, __params.length () - 2);
			    return __params;
			}
			else
			    return ""; // NOI18N
		    } catch (Exception __ex) {
			__ex.printStackTrace ();
		    }
		    return ""; // NOI18N
		}
	    });
        return s;
    }


}

/*
 * $Log
 * $
 */
