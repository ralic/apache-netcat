/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2008 Sun Microsystems, Inc. All rights reserved.
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
 * Portions Copyrighted 2008 Sun Microsystems, Inc.
 */
package org.netbeans.modules.ada.project.ui.actions;

import org.netbeans.api.ada.platform.AdaPlatform;
import org.netbeans.modules.ada.platform.compiler.gnat.GnatCompiler;
import org.netbeans.modules.ada.project.AdaActionProvider;
import org.netbeans.modules.ada.project.AdaProject;
import org.netbeans.modules.ada.project.AdaProjectUtil;
import org.netbeans.modules.ada.project.ui.properties.AdaProjectProperties;
import org.openide.filesystems.FileUtil;
import org.openide.util.Lookup;

/**
 *
 * @author Andrea Lucarelli
 */
public class RunCommand extends Command {

    private static final String COMMAND_ID = AdaActionProvider.COMMAND_RUN;

    public RunCommand(AdaProject project) {
        super(project);
    }

    @Override
    public String getCommandId() {
        return COMMAND_ID;
    }

    @Override
    public void invokeAction(Lookup context) throws IllegalArgumentException {
        final AdaProject project = getProject();
        AdaPlatform platform = AdaProjectUtil.getActivePlatform(project);
        assert platform != null;

        // Retrieve main file
        String mainFile = project.getEvaluator().getProperty(AdaProjectProperties.MAIN_FILE);
        assert mainFile != null;

        // Init compiler factory
        GnatCompiler comp = new GnatCompiler(
                platform,
                project.getName(),                       // project name
                FileUtil.toFile(project.getProjectDirectory()).getAbsolutePath(),  // project location
                FileUtil.toFile(project.getSourcesDirectory()).getAbsolutePath(),  // sources location
                mainFile,                                // main file
                project.getName(),                        // executable file
                COMMAND_ID);                              // display name

        // Start run
        comp.Run();
    }

    @Override
    public boolean isActionEnabled(Lookup context) throws IllegalArgumentException {
        final AdaProject project = getProject();
        AdaPlatform platform = AdaProjectUtil.getActivePlatform(project);
        if (platform == null) {
            return false;
        }
        return true;
    }
}
