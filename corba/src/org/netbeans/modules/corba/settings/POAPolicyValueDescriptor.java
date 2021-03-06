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

package org.netbeans.modules.corba.settings;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

/*
 * @author Karel Gardas
 * @version 0.01, Jan 8 2001
 */

public class POAPolicyValueDescriptor {

    private String _M_name;
    private List _M_required_policies;
    private List _M_conflicts_with_policies;
    private List _M_disabled_actions;

    public POAPolicyValueDescriptor () {
	_M_required_policies = new LinkedList ();
	_M_conflicts_with_policies = new LinkedList ();
	_M_disabled_actions = new LinkedList ();
    }

    public String getName () {
	return _M_name;
    }

    public void setName (String __value) {
	_M_name = __value;
    }

    public List getConflictsPolicies () {
	return _M_conflicts_with_policies;
    }

    public void addConflictsPolicy (POAPolicySimpleDescriptor __value) {
	_M_conflicts_with_policies.add (__value);
    }

    public List getRequiredPolicies () {
	return _M_required_policies;
    }

    public void addRequiredPolicy (POAPolicySimpleDescriptor __value) {
	_M_required_policies.add (__value);
    }

    public List getDisabledActions () {
	return _M_disabled_actions;
    }

    public void addDisabledAction (String __value) {
	_M_disabled_actions.add (__value);
    }

    public String toString () {
	StringBuffer __buf = new StringBuffer ();
	__buf.append ("POAPolicyValueDescriptor: name: ");
	__buf.append (_M_name);
	__buf.append ("\n requires: ");
	Iterator __iter = this.getRequiredPolicies ().iterator ();
	while (__iter.hasNext ()) {
	    __buf.append ("`");
	    __buf.append ((POAPolicySimpleDescriptor)__iter.next ());
	    __buf.append ("', ");
	}
	__buf.append ("\n conflicts with: ");
	__iter = this.getConflictsPolicies ().iterator ();
	while (__iter.hasNext ()) {
	    __buf.append ("`");
	    __buf.append ((POAPolicySimpleDescriptor)__iter.next ());
	    __buf.append ("', ");
	}
	__buf.append ("\n disable actions: ");
	__iter = this.getDisabledActions ().iterator ();
	while (__iter.hasNext ()) {
	    __buf.append ((String)__iter.next ());
	    __buf.append ("', ");
	}
	return __buf.toString ();

    }
}

