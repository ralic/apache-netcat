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
package org.netbeans.modules.clearcase.ui.update;

import org.netbeans.modules.versioning.spi.VCSContext;
import org.netbeans.modules.clearcase.Clearcase;
import org.netbeans.modules.clearcase.client.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileFilter;
import java.util.*;

/**
 * Updates selected files/folders in the snapshot view.
 * 
 * @author Maros Sandor
 */
public class UpdateAction extends AbstractAction implements NotificationListener {
    
    private final VCSContext context;

    public UpdateAction(String name, VCSContext context) {
        this.context = context;
        putValue(Action.NAME, name);
    }
    
    @Override
    public boolean isEnabled() {
        Set<File> roots = context.getRootFiles();
        for (File file : roots) {
            // TODSO consider this as a HACK - cache the info if file in shapshot or not 
            if( Clearcase.getInstance().getTopmostSnapshotViewAncestor(file) == null ) {
                return false;
            }                
        }
        return true;
    }    
    
    public void actionPerformed(ActionEvent e) {
        Set<File> files = context.computeFiles(updateFileFilter);
        Clearcase.getInstance().getClient().post(new ExecutionUnit(
                "Updating...",
                new UpdateCommand(files.toArray(new File[files.size()]), this, new OutputWindowNotificationListener())));
    }

    private static final FileFilter updateFileFilter = new FileFilter() {
        public boolean accept(File pathname) {
            return true;
        }
    };

    public void commandStarted() {
    }

    public void outputText(String line) {
    }

    public void errorText(String line) {
    }

    public void commandFinished() {
    }
}