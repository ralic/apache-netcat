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
 * The Original Software is the LaTeX module.
 * The Initial Developer of the Original Software is Jan Lahoda.
 * Portions created by Jan Lahoda_ are Copyright (C) 2002-2007.
 * All Rights Reserved.
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
 * Contributor(s): Jan Lahoda.
 */
package org.netbeans.modules.latex.guiproject;

import org.netbeans.spi.project.ui.ProjectOpenedHook;
import org.openide.ErrorManager;
import org.openide.util.Lookup;
import org.openide.util.RequestProcessor;

/**
 *
 * @author Jan Lahoda
 */
public class LaTeXGUIProjectOpenedHookImpl extends ProjectOpenedHook {

    private LaTeXGUIProject project;

    /** Creates a new instance of LaTeXGUIProjectOpenedHookImpl */
    public LaTeXGUIProjectOpenedHookImpl(LaTeXGUIProject project) {
        this.project = project;
    }

    protected void projectOpened() {
        LaTeXGUIProjectUpgrader.getUpgrader().upgrade(project);
        ProjectReparsedTaskFactory.get().registerFile(project.getMainFile());
        assureParsed();
    }

    protected void projectClosed() {
        ProjectReparsedTaskFactory.get().registerFile(project.getMainFile());
    }
    
    private void assureParsed() {
//        RequestProcessor.getDefault().post(new Runnable() {
//	    public void run() {
//                //an attempt to prevent deadlock between TexKit.<clinit> and TexSettingsInitializer:
//                try {
//                    ClassLoader cl = (ClassLoader) Lookup.getDefault().lookup(ClassLoader.class);
//                    
//                    cl.loadClass("org.netbeans.modules.latex.editor.TexKit");
//                } catch (Exception e) {
//                    ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, e);
//                }
//                
//                LaTeXSource      source = project.getSource();
//	        LaTeXSource.Lock lock = null;
//		
////                System.err.println("source=" + source);
//		try {
////                    System.err.println("LaTeXGUIProject.assureParsed trying to obtain lock");
//		    lock = source.lock(true);
////                    System.err.println("LaTeXGUIProject.assureParsed trying lock obtained=" + lock);
//		} finally {
//                    if (lock != null) {
////                        System.err.println("LaTeXGUIProject.assureParsed unlock the lock");
//                        source.unlock(lock);
////                        System.err.println("LaTeXGUIProject.assureParsed unlocking done");
//                    } else {
////                        System.err.println("LaTeXGUIProject.assureParsed no unlocking (lock == null)");
//                    }
//		}
//	    }
//	});
    }
    
}
