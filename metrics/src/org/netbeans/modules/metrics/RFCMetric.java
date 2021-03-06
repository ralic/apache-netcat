/*
 * RFCMetric.java
 *
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
 *
 * Contributor(s): Thomas Ball
 *
 * Version: $Revision$
 */

package org.netbeans.modules.metrics;

import org.netbeans.modules.metrics.options.*;

import java.util.*;

/**
 *
 * @author  tball
 * @version
 */
public class RFCMetric extends AbstractMetric {

    static final String displayName =
        MetricsNode.bundle.getString ("LBL_RFCMetric");

    static final String shortDescription =
	MetricsNode.bundle.getString ("HINT_RFCMetric");

    /** Creates new RFCMetric */
    protected RFCMetric(ClassMetrics classMetrics) {
        super(classMetrics);
    }

    public String getName() {
        return "RFCMetric";
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public String getShortDescription() {
        return shortDescription;
    }
    
    public MetricSettings getSettings() {
	return RFCMetricSettings.getDefault();
    }

    private void buildMetric() {
        if (metric == null) {
            Set methods = new TreeSet();
            Set referencedMethods = new TreeSet();
            Iterator iter = classMetrics.getMethods().iterator();
            while (iter.hasNext()) {
                MethodMetrics mm = (MethodMetrics)iter.next();
                if (mm.isSynthetic())  // skip synthetic methods
                    continue;
                methods.add(mm.getFullName());
                Iterator iter2 = mm.getMethodReferences();
                while (iter2.hasNext()) {
                    String ref = (String)iter2.next();
                    referencedMethods.add(ref);
                }
            }
            metric = new Integer(methods.size() + referencedMethods.size());
            
            StringBuffer sb = new StringBuffer();
            sb.append(MetricsNode.bundle.getString ("STR_ClassMethods"));
            iter = methods.iterator();
            while (iter.hasNext()) {
                sb.append("\n   ");
                sb.append((String)iter.next());
            }
            sb.append("\n");
            sb.append(MetricsNode.bundle.getString ("STR_ReferencedMethods"));
            iter = referencedMethods.iterator();
            while (iter.hasNext()) {
                sb.append("\n   ");
                sb.append((String)iter.next());
            }
            details = sb.toString();
        }
    }
    
    public Integer getMetricValue() {
        buildMetric();
        return metric;
    }
    
    public String getDetails() {
        buildMetric();
        return details;
    }

    public boolean needsOtherClasses() {
        return false;
    }

    public boolean isMethodMetric() {
	return true;
    }

    public Integer getMetricValue(MethodMetrics mm) throws NoSuchMetricException {
	return new Integer(mm.getMethodReferencesCount());
    }

    /**
     * Actually a private class used by the MetricsLoader, but
     * must be public since its instance is created by the XML
     * filesystem.
     */
    public static class Factory implements MetricFactory {
	public Metric createMetric(ClassMetrics cm) {
	    return new RFCMetric(cm);
	}
	public Class getMetricClass() {
	    return RFCMetric.class;
	}
    }
}
