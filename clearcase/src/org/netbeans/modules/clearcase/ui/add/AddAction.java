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

package org.netbeans.modules.clearcase.ui.add;

import org.netbeans.modules.clearcase.util.ProgressSupport;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.Dialog;
import java.io.File;
import java.util.*;
import javax.swing.*;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.netbeans.modules.clearcase.FileStatusCache;
import org.netbeans.modules.versioning.spi.VCSContext;
import org.netbeans.modules.versioning.util.VersioningOutputManager;
import org.netbeans.modules.versioning.util.Utils;
import org.netbeans.modules.versioning.util.DialogBoundsPreserver;
import org.netbeans.modules.clearcase.ClearcaseModuleConfig;
import org.netbeans.modules.clearcase.ClearcaseFileNode;
import org.netbeans.modules.clearcase.Clearcase;
import org.netbeans.modules.clearcase.FileInformation;
import org.netbeans.modules.clearcase.ui.checkin.CheckinOptions;
import org.netbeans.modules.clearcase.client.*;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.NbBundle;
import org.openide.util.HelpCtx;
import org.openide.util.RequestProcessor;

/**
 * Sample Update action.
 * 
 * @author Maros Sandor
 */
public class AddAction extends AbstractAction {
    
    static final String RECENT_ADD_MESSAGES = "add.messages";    
    
    private final VCSContext context;
    protected final VersioningOutputManager voutput;

    public AddAction(String name, VCSContext context) {
        this.context = context;
        putValue(Action.NAME, name);
        voutput = VersioningOutputManager.getInstance();
    }
    
    @Override
    public boolean isEnabled() {
        FileStatusCache cache = Clearcase.getInstance().getFileStatusCache();
        Set<File> roots = context.getRootFiles();        
        for (File root : roots) {
            if(root.isDirectory()) {
                return true;
            }
            FileInformation info = cache.getCachedInfo(root);            
            if(info != null && info.getStatus() == FileInformation.STATUS_NOTVERSIONED_NEWLOCALLY) {
                return true;
            }
        }
        return false;
    }
    
    public void actionPerformed(ActionEvent ev) {
        String contextTitle = Utils.getContextDisplayName(context);
        final JButton addButton = new JButton(); 
        addButton.setEnabled(false);
        JButton cancelButton = new JButton("Cancel"); 
        AddPanel panel = new AddPanel();        
                
        DialogDescriptor dd = new DialogDescriptor(panel, NbBundle.getMessage(AddAction.class, "CTL_AddDialog_Title", contextTitle)); // NOI18N
        dd.setModal(true);        
        org.openide.awt.Mnemonics.setLocalizedText(addButton, org.openide.util.NbBundle.getMessage(AddAction.class, "CTL_AddDialog_Add"));
        
        dd.setOptions(new Object[] {addButton, cancelButton}); // NOI18N
        dd.setHelpCtx(new HelpCtx(AddAction.class));

        panel.cbSuppressCheckout.setSelected(ClearcaseModuleConfig.getCheckInAddedFiles());
        final AddTable addTable = new AddTable(panel.jLabel2, AddTable.ADD_COLUMNS, new String [] { AddTableModel.COLUMN_NAME_NAME });
        addTable.getTableModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                addButton.setEnabled(addTable.getTableModel().getRowCount() > 0);
            }
        });
        computeNodes(addTable, cancelButton, panel);        
        panel.setAddTable(addTable);
        
        panel.putClientProperty("contentTitle", contextTitle);  // NOI18N
        panel.putClientProperty("DialogDescriptor", dd); // NOI18N
        final Dialog dialog = DialogDisplayer.getDefault().createDialog(dd);        
        dialog.addWindowListener(new DialogBoundsPreserver(ClearcaseModuleConfig.getPreferences(), "add.dialog")); // NOI18N       
        dialog.pack();        
        dialog.setVisible(true);
        
        Object value = dd.getValue();
        if (value != addButton) return;
        
        String message = panel.taMessage.getText();
        boolean checkInAddedFiles = panel.cbSuppressCheckout.isSelected();
        ClearcaseModuleConfig.setCheckInAddedFiles(checkInAddedFiles);
        Utils.insert(ClearcaseModuleConfig.getPreferences(), RECENT_ADD_MESSAGES, message, 20);
        
        Map<ClearcaseFileNode, CheckinOptions> filesToAdd = addTable.getAddFiles();
        
        addFiles(message, checkInAddedFiles, filesToAdd);
    }

    /**
     * Invokes "mkelem" on supplied files.
     * 
     * @param message message from the mkelem command or null
     * @param checkInAddedFiles
     * @param filesToAdd set of files to add - only files that have the ADD_XXXXXX checkin option set will be added
     * @return CommandRunnable that is adding the files or NULL of there are no files to add and no command was executed
     */
    public static ClearcaseClient.CommandRunnable addFiles(final String message, boolean checkInAddedFiles, Map<ClearcaseFileNode, CheckinOptions> filesToAdd) {
        // TODO: process options
        Set<File> tmpFiles = new HashSet<File>();
        for (Map.Entry<ClearcaseFileNode, CheckinOptions> entry : filesToAdd.entrySet()) {
            if (entry.getValue() == CheckinOptions.ADD_BINARY || entry.getValue() == CheckinOptions.ADD_TEXT || entry.getValue() == CheckinOptions.ADD_DIRECTORY) {
                tmpFiles.add(entry.getKey().getFile());
            }
        }
        
        if (tmpFiles.size() == 0) return null;
        
        // make sure that ancestors are also added if they are not already under source control
        addAncestors(tmpFiles);
        List<File> addFiles = new ArrayList<File>(tmpFiles);
        
        // sort files - parents first, to avoid unnecessary warnings
        Collections.sort(addFiles);        
        final File[] files = addFiles.toArray(new File[addFiles.size()]);
        return addFiles(files, message, checkInAddedFiles);
    }

    /**
     * Invokes "mkelem" on supplied files.
     * 
     * @param files arrya of files to add 
     * @param message message from the mkelem command or null
     * @param checkInAddedFiles     
     * @return CommandRunnable that is adding the files or NULL of there are no files to add and no command was executed
     */    
    public static ClearcaseClient.CommandRunnable addFiles(final File[] files, final String message, boolean checkInAddedFiles) {        
        HashSet<File> refreshSet = new HashSet<File>();
        for (File file : files) {
            refreshSet.add(file);
            File parent = file.getParentFile();
            if(parent != null) {
                refreshSet.add(parent);
            }    
        }                    
        return Clearcase.getInstance().getClient().post(
                new ExecutionUnit(
                    "Adding...", 
                    new MkElemCommand(files, message, checkInAddedFiles ? MkElemCommand.Checkout.Checkin : MkElemCommand.Checkout.Default, 
                    false, 
                    new OutputWindowNotificationListener(), 
                    new AfterCommandRefreshListener(refreshSet.toArray(new File[refreshSet.size()])))));
    }

    private static void addAncestors(Set<File> addFiles) {
        Set<File> ancestorsToAdd = new HashSet<File>(10);
        for (File file : addFiles) {
            addAncestors(ancestorsToAdd, file);
        }
        addFiles.addAll(ancestorsToAdd);
    }

    private static void addAncestors(Set<File> ancestorsToAdd, File file) {
        File parent = file.getParentFile();
        if (parent != null) {
            int status = Clearcase.getInstance().getFileStatusCache().getInfo(parent).getStatus();
            if (status == FileInformation.STATUS_NOTVERSIONED_NEWLOCALLY) {
                ancestorsToAdd.add(parent);
                addAncestors(ancestorsToAdd, parent);
            }
        }
    }

    // XXX temporary solution...
    private void computeNodes(final AddTable table, JButton cancel, final AddPanel addPanel) {
        final ProgressSupport ps = new FileStatusCache.RefreshSupport(new RequestProcessor("Clearcase-AddTo"), context, "Preparing Add To...", cancel) {
            @Override
            protected void perform() {
                try {
                    addPanel.progressPanel.setVisible(true);
                    
                    FileStatusCache cache = Clearcase.getInstance().getFileStatusCache();

                    // refresh the cache first so we will
                    // know all checkin candidates
                    refresh();
                    
                    // get all files to be added
                    File [] files = cache.listFiles(context, FileInformation.STATUS_NOTVERSIONED_NEWLOCALLY);
                    List<ClearcaseFileNode> nodes = new ArrayList<ClearcaseFileNode>(files.length);
                    for (File file : files) {
                        nodes.add(new ClearcaseFileNode(file));
                    }                        
                    ClearcaseFileNode[] fileNodes = nodes.toArray(new ClearcaseFileNode[nodes.size()]);
                    table.setNodes(fileNodes);
                } finally {
                    addPanel.progressPanel.setVisible(false);                    
                }
            }           
        };
        addPanel.barPanel.add(ps.getProgressComponent(), BorderLayout.CENTER);                                
        ps.start();        
    }
    
}
