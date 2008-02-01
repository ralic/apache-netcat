/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2008 Sun Microsystems, Inc. All rights reserved.
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
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2008 Sun
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
package org.netbeans.modules.clearcase.ui.checkout;

import org.netbeans.modules.clearcase.client.NotificationListener;
import org.netbeans.modules.clearcase.client.OutputWindowNotificationListener;
import org.netbeans.modules.clearcase.client.UnCheckoutCommand;
import org.netbeans.modules.clearcase.client.ExecutionUnit;
import org.netbeans.modules.clearcase.Clearcase;
import org.netbeans.modules.clearcase.ClearcaseModuleConfig;
import org.netbeans.modules.clearcase.util.ClearcaseUtils;
import org.netbeans.modules.versioning.spi.VCSContext;
import org.netbeans.modules.versioning.util.Utils;
import org.netbeans.modules.versioning.util.DialogBoundsPreserver;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.filesystems.FileUtil;
import org.openide.awt.Mnemonics;
import org.openide.util.NbBundle;
import org.openide.util.HelpCtx;

import javax.swing.*;
import java.io.File;
import java.io.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.Dialog;
import java.util.Set;
import org.netbeans.modules.clearcase.FileInformation;
import org.netbeans.modules.clearcase.FileStatusCache;

/**
 * Checks all files/folders in the context out, making them editable by the user.
 * 
 * @author Maros Sandor
 */
public class UncheckoutAction extends AbstractAction implements NotificationListener {
    
    private final VCSContext    context;
    private File[]              files;

    private static int ALLOW_UNCO = 
            FileInformation.STATUS_VERSIONED_CHECKEDOUT_RESERVED | 
            FileInformation.STATUS_VERSIONED_CHECKEDOUT_UNRESERVED;
    
    public UncheckoutAction(String name, VCSContext context) {
        super(name);
        this.context = context;
    }
    
    @Override
    public boolean isEnabled() {
        FileStatusCache cache = Clearcase.getInstance().getFileStatusCache();
        Set<File> roots = context.getRootFiles();
        for (File file : roots) {
            if( (cache.getInfo(file).getStatus() & ALLOW_UNCO) == 0 ) {
                return false;
            }                
        }
        return true;
    }
    
    public void actionPerformed(ActionEvent ev) {
        
        String contextTitle = Utils.getContextDisplayName(context);
        JButton unCheckoutButton = new JButton(); 
        UncheckoutPanel panel = new UncheckoutPanel();

        Set<File> roots = context.getFiles(); // XXX this won't work if some of the roots is a parent from another one
        files = roots.toArray(new File[roots.size()]);
        panel.cbKeep.setEnabled(false);
        for (File file : files) {
            if(file.isFile()) {
                panel.cbKeep.setEnabled(true);        
                break;
            }
        }
        
        DialogDescriptor dd = new DialogDescriptor(panel, NbBundle.getMessage(UncheckoutAction.class, "CTL_UncheckoutDialog_Title", contextTitle)); // NOI18N
        dd.setModal(true);
        dd.setMessageType(DialogDescriptor.WARNING_MESSAGE);
        Mnemonics.setLocalizedText(unCheckoutButton, NbBundle.getMessage(UncheckoutAction.class, "CTL_UncheckoutDialog_Unheckout"));
        
        dd.setOptions(new Object[] {unCheckoutButton, DialogDescriptor.CANCEL_OPTION}); // NOI18N
        dd.setHelpCtx(new HelpCtx(UncheckoutAction.class));
                
        panel.putClientProperty("contentTitle", contextTitle);  // NOI18N
        panel.putClientProperty("DialogDescriptor", dd); // NOI18N
        final Dialog dialog = DialogDisplayer.getDefault().createDialog(dd);        
        dialog.addWindowListener(new DialogBoundsPreserver(ClearcaseModuleConfig.getPreferences(), "uncheckout.dialog")); // NOI18N       
        dialog.pack();        
        dialog.setVisible(true);
        
        Object value = dd.getValue();
        if (value != unCheckoutButton) return;
        
        boolean keepFiles = panel.cbKeep.isSelected();
        
        Clearcase.getInstance().getClient().post(new ExecutionUnit(
                "Undoing Checkout...",
                new UnCheckoutCommand(files, keepFiles, this, new OutputWindowNotificationListener())));
    }

    public void commandStarted() {
    }

    public void outputText(String line) {
    }

    public void errorText(String line) {
    }

    public void commandFinished() {   
        org.netbeans.modules.clearcase.util.Utils.afterCommandRefresh(files, false);        
    }

    private static final FileFilter checkoutFileFilter = new FileFilter() {
        public boolean accept(File pathname) {
            return true;
        }
    };
}