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

package org.netbeans.modules.refactoring.vcs;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import javax.swing.Action;
import org.netbeans.api.vcs.commands.Command;
import org.netbeans.api.vcs.commands.CommandTask;
import org.netbeans.modules.refactoring.spi.ProblemDetailsImplementation;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.filesystems.FileObject;
import org.openide.util.Cancellable;
import org.openide.util.NbBundle;

/**
 *
 * @author Jan Becicka
 */
public class CheckoutFiles implements ProblemDetailsImplementation {
    
    private Collection files;
    private Command editCmd;
    private static String CHECKOUT_OPTION;
    private static final String CANCEL_OPTION = NbBundle.getMessage(CheckoutFiles.class, "LBL_Cancel");
    private Cancellable parent;
    /** Creates a new instance of CheckoutFiles */
    public CheckoutFiles(Collection files, Command editCmd) {
        this.setFiles(files);
        this.setEditCmd(editCmd);
    }
    
    public void showDetails(Action rerunRefactoringAction, Cancellable parent) {
        this.parent = parent;
        CHECKOUT_OPTION = MessageFormat.format(NbBundle.getMessage(CheckoutFiles.class, "LBL_Checkout_And_Rerun"), new Object[]{rerunRefactoringAction.getValue(Action.NAME)});
        DialogDescriptor desc = new DialogDescriptor(new CheckoutPanel(getFiles()), NbBundle.getMessage(CheckoutFiles.class, "LBL_Title_Update_Files"), true, new String[]{CHECKOUT_OPTION, CANCEL_OPTION}, CHECKOUT_OPTION, DialogDescriptor.DEFAULT_ALIGN, null, null);
        Object retval = DialogDisplayer.getDefault().notify(desc);
        if (retval == CHECKOUT_OPTION) {
            checkoutFiles();
            rerunRefactoring(rerunRefactoringAction);
        }
    }
    
    public String getDetailsHint() {
        return NbBundle.getMessage(CheckoutFiles.class, "LBL_Update_Files");
    }

    private void checkoutFiles() {
        CommandTask task = getEditCmd().execute();
        try {
            task.waitFinished(0);
        } catch (InterruptedException iex) {
            Thread.currentThread().interrupt();
        }
    }
    
    private void rerunRefactoring(Action rerunRefactoringAction) {
        parent.cancel();
        rerunRefactoringAction.actionPerformed(null);
    }

    void setFiles(Collection files) {
        this.files = files;
    }

    void setEditCmd(Command editCmd) {
        this.editCmd = editCmd;
    }

    public Collection getFiles() {
        return files;
    }

    public Command getEditCmd() {
        return editCmd;
    }
    
    
}
