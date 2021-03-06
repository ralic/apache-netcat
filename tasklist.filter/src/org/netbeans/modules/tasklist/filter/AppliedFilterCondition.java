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

package org.netbeans.modules.tasklist.filter;

/**
 * This class represents a filter condition applied to a property.
 */
public final class AppliedFilterCondition {

    private SuggestionProperty prop;
    private FilterCondition cond;

    public AppliedFilterCondition(SuggestionProperty property, FilterCondition condition) {
        this.prop = property;
        this.cond = condition;
    }

    public Object clone() {
        return new AppliedFilterCondition(prop, (FilterCondition)cond.clone());
    }

    public SuggestionProperty getProperty() { return prop;}
    public FilterCondition getCondition() { return cond;}
    
    /**
     * Tests if the condition is true on the property of task.
     * @param task the object to filter
     * @return true, if value of the property of <code>task</code>
     * defined by getProperty() passed the condition getCondition()
     */
    public boolean isTrue(Object task) {
        return cond.isTrue(prop.getValue(task));
    }
    
    public String toString() {
        return cond.toString() + " applied to " + prop.toString();
    }
    
    /**
     * Checks whether afc is of the same type.
     * This method will be used to replace a condition created with
     * Filter.getConditionsFor(Node.Property) with one contained in a filter.
     * This method should return true also if this and fc have different
     * constants for comparing with property values.
     *
     * @param fc another condition
     * @return true fc is of the same type as this
     */
    public boolean sameType(AppliedFilterCondition afc) {
        return getCondition().sameType(afc.getCondition()) && getProperty().equals(afc.getProperty());
    }
    
}
