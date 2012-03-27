/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2011 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
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
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
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
 * Contributor(s):
 *
 * Portions Copyrighted 2011 Sun Microsystems, Inc.
 */
package org.netbeans.modules.autoproject.profiler;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.lang.model.element.TypeElement;
import org.netbeans.api.java.classpath.ClassPath;
import org.netbeans.api.java.source.ClasspathInfo;
import org.netbeans.api.java.source.CompilationController;
import org.netbeans.api.java.source.JavaSource;
import org.netbeans.api.java.source.Task;
import org.netbeans.api.project.Project;
import org.netbeans.modules.profiler.api.JavaPlatform;
import org.netbeans.modules.profiler.nbimpl.project.JavaProjectProfilingSupportProvider;
import org.netbeans.modules.profiler.spi.project.ProjectProfilingSupportProvider;
import org.netbeans.spi.project.ProjectServiceProvider;
import org.openide.filesystems.FileObject;
import java.util.Map;

/**
 *
 * @author Jiri Sedlacek
 */
@ProjectServiceProvider(service=ProjectProfilingSupportProvider.class, projectType="org-netbeans-modules-autoproject") // NOI18N
public final class AutomaticProjectProfilingSupportProvider extends JavaProjectProfilingSupportProvider {

    @Override
    public boolean isFileObjectSupported(FileObject fo) {
        return fo.hasExt("java"); // NOI18N
    }

    protected JavaPlatform resolveProjectJavaPlatform() {
        return null;
    }
    
    @Override
    public boolean checkProjectCanBeProfiled(FileObject profiledClassFile) {
        return profiledClassFile != null &&
                ClassPath.getClassPath(profiledClassFile, ClassPath.SOURCE) != null &&
                ClassPath.getClassPath(profiledClassFile, ClassPath.EXECUTE) != null;
    }
    
    public void configurePropertiesForProfiling(Properties props, FileObject profilerClassFile) {
        // transitional placeholder for API change
    }

    public void configurePropertiesForProfiling(Map<String, String> props, FileObject profiledClassFile) {
        ClassPath sourcepath = ClassPath.getClassPath(profiledClassFile, ClassPath.SOURCE);
        props.put("classpath", ClassPath.getClassPath(profiledClassFile, ClassPath.EXECUTE).toString(ClassPath.PathConversionMode.FAIL)); // NOI18N
    }
    
    public AutomaticProjectProfilingSupportProvider(Project project) {
        super(project);
    }
}
