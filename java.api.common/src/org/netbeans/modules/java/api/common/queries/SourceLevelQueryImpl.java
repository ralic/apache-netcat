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
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
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
package org.netbeans.modules.java.api.common.queries;

import org.netbeans.api.java.platform.JavaPlatform;
import org.netbeans.api.java.platform.JavaPlatformManager;
import org.netbeans.api.java.platform.Specification;
import org.netbeans.spi.java.queries.SourceLevelQueryImplementation;
import org.netbeans.spi.project.support.ant.PropertyEvaluator;
import org.netbeans.spi.project.support.ant.PropertyUtils;
import org.netbeans.spi.project.support.ant.EditableProperties;
import org.openide.filesystems.FileObject;

/**
 * Returns source level of project Java source files.
 * @author David Konecny
 * @since org.netbeans.modules.java.api.common/0 1.0
 */
class SourceLevelQueryImpl implements SourceLevelQueryImplementation {

    private final PropertyEvaluator evaluator;

    public SourceLevelQueryImpl(PropertyEvaluator evaluator) {
        assert evaluator != null;
        
        this.evaluator = evaluator;
    }
    
    public String getSourceLevel(FileObject javaFile) {
        final String activePlatform = evaluator.getProperty("platform.active"); //NOI18N
        if (getActivePlatform(activePlatform) != null) {
            String sl = evaluator.getProperty("javac.source"); //NOI18N
            if (sl != null && sl.length() > 0) {
                return sl;
            }
            return null;
        }
        
        EditableProperties props = PropertyUtils.getGlobalProperties();
        String sl = props.get("default.javac.source"); //NOI18N
        if (sl != null && sl.length() > 0) {
            return sl;
        }
        return null;
    }
    
    // XXX copied from J2SEProjectUtilities, should be part of some API probably (JavaPlatformManager?)
    private static JavaPlatform getActivePlatform(final String activePlatformId) {
        final JavaPlatformManager pm = JavaPlatformManager.getDefault();
        if (activePlatformId == null) {
            return pm.getDefaultPlatform();
        }
        
        JavaPlatform[] installedPlatforms = pm.getPlatforms(null, new Specification("j2se", null)); //NOI18N
        for (JavaPlatform javaPlatform : installedPlatforms) {
            String antName = javaPlatform.getProperties().get("platform.ant.name"); //NOI18N
            if (antName != null && antName.equals(activePlatformId)) {
                return javaPlatform;
            }
        }
        return null;
    }
}