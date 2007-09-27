/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */

package org.netbeans.modules.corba.idl.node;

import org.openide.nodes.Children;
import org.openide.nodes.Sheet;
import org.openide.nodes.PropertySupport;

import org.openide.util.actions.SystemAction;
import org.openide.actions.OpenAction;

import org.netbeans.modules.corba.idl.src.IDLElement;
import org.netbeans.modules.corba.idl.src.ValueBoxElement;
import org.netbeans.modules.corba.idl.src.IDLType;
import org.netbeans.modules.corba.idl.src.TypeElement;

/**
 * Class IDLValueNode
 *
 * @author Karel Gardas
 */
public class IDLValueBoxNode extends IDLAbstractNode {

    ValueBoxElement _M_value;

    private static final String VALUE_ICON_BASE =
        "org/netbeans/modules/corba/idl/node/value"; // NOI18N

    public IDLValueBoxNode (ValueBoxElement value) {
        //super (new IDLDocumentChildren ((IDLElement)value));
        super (Children.LEAF);
        setIconBase (VALUE_ICON_BASE);
        _M_value = value;
        setCookieForDataObject (_M_value.getDataObject ());
    }

    public IDLElement getIDLElement () {
        return _M_value;
    }
    /*
      public String getDisplayName () {
      if (_M_value != null)
      return _M_value.getName ();
      else
      return "NoName :)";
      }
    */
    public String getName () {
        return "value box"; // NOI18N
    }

    public SystemAction getDefaultAction () {
        SystemAction result = super.getDefaultAction();
        return result == null ? SystemAction.get(OpenAction.class) : result;
    }

    protected Sheet createSheet () {
        Sheet s = Sheet.createDefault ();
        Sheet.Set ss = s.get (Sheet.PROPERTIES);
        ss.put (new PropertySupport.ReadOnly ("name", String.class, IDLNodeBundle.NAME, IDLNodeBundle.NAME_OF_VALUEBOX) { // NOI18N
                    public Object getValue () {
                        return _M_value.getName ();
                    }
                });
        ss.put (new PropertySupport.ReadOnly ("type", String.class, IDLNodeBundle.TYPE, IDLNodeBundle.TYPE_OF_VALUEBOX) { // NOI18N
		public Object getValue () {
		    IDLType __type = _M_value.getType ();
		    int __type_value = __type.getType ();
		    //System.out.println ("type: " + __type +  " value: " + __type_value); // NOI18N
		    if (__type.getType () != IDLType.STRUCT && __type.getType () != IDLType.UNION 
			&& __type.getType () != IDLType.ENUM) {
			//System.out.println ("simple type"); // NOI18N
			return __type.getName ();
		    }
		    else {
			//System.out.println ("constr type"); // NOI18N
			TypeElement __real_type 
			    = (TypeElement)_M_value.getMembers ().elementAt (1);
			return __type.getName () + " " + __real_type.getName (); // NOI18N
		    }
		}
	    });
	return s;
    }

}

/*
 * $Log
 * $
 */

