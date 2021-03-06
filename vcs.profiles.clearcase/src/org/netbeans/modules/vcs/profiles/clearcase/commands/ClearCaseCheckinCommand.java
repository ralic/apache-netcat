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

package org.netbeans.modules.vcs.profiles.clearcase.commands;

import org.openide.util.NbBundle;
import org.netbeans.modules.vcscore.VcsFileSystem;
import org.netbeans.modules.vcscore.commands.*;

/**
 * This command is used to create a ClearCase directory.
 *
 * @author  Peter Liu
 */
public class ClearCaseCheckinCommand extends ClearCaseAbstractCommand {
    public void setFileSystem(VcsFileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

	protected boolean process() {
		final CommandOutputListener defaultErrorListener = stderrNRListener;
		stderrNRListener = new CommandOutputListener() {
				boolean appendExtraText = false;

				public void outputLine(String line) {
					if (line != null) {
						if (line.indexOf("is not the predecessor of this version") != -1) { //NOI18N
							appendExtraText = true;
							defaultErrorListener.outputLine(line);							
						} else if (line.indexOf("Unable to check in") != -1) {   //NOI18N
							defaultErrorListener.outputLine(line);							
							if (appendExtraText) {
								defaultErrorListener.outputLine(NbBundle.getMessage(ClearCaseCheckinCommand.class, "MSG_RUN_MERGE_TOOL"));
								appendExtraText = false;
							}
						}
					}
				}
			};
		vars.put("COMMENT", vars.get("REASON"));  //NOI18N
		return executeCommand(args[0]);
    }

	
}
