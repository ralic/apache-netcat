/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2005 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.projectpackager.importer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;
import javax.swing.SwingUtilities;
import org.apache.tools.ant.module.api.support.ActionUtils;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectManager;
import org.netbeans.api.project.ui.OpenProjects;
import org.netbeans.modules.projectpackager.tools.Constants;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.execution.ExecutorTask;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 * A thread which executes ant targets and shows result of the actions to user.
 * @author Roman "Roumen" Strobl
 */
public class ImportExecutorThread extends Thread {
    private Vector scripts;
    private Vector targets;
    private Vector properties;
    private int count;
    private int scheduledCount;
    private boolean silent = false;
    
    private HashMap status;
    private HashMap scheduledTasks;
    
    private ProgressHandle handle;
    
    /**
     * Creates a new instance of ImportExecutorThread
     */
    public ImportExecutorThread() {
        scripts = new Vector();
        targets = new Vector();
        properties = new Vector();
        status = new HashMap();
        scheduledTasks = new HashMap();
        count = 0;
        scheduledCount = 0;
    }
    
    /**
     * Run method, overrides run() from Thread
     */
    public void run() {
        if (!isSilent()) {
            handle = ProgressHandleFactory.createHandle(java.util.ResourceBundle.getBundle(Constants.BUNDLE).getString("Processing_zips"));
            handle.start();
        }
        while (count<scheduledCount) {
            FileObject script = (FileObject) scripts.get(count);
            String[] target = (String[]) targets.get(count);
            Properties props = (Properties) properties.get(count);
            try {
                ExecutorTask et = ActionUtils.runTarget(script, target, props);
                if (et.result()!=0) {
                    System.err.println(java.util.ResourceBundle.getBundle(Constants.BUNDLE).getString("Task_execution_error_during_")+target[0]+".");
                    status.put(target[0], new Boolean(false));
                } else {
                    if (status.get(target[0])==null) {
                        status.put(target[0], new Boolean(true));
                    }
                }
            } catch (IOException e) {
                System.err.println(java.util.ResourceBundle.getBundle(Constants.BUNDLE).getString("IO_error:_")+e);
            }
            count++;
        }
        
        // we're done
        ImportPackageInfo.setProcessed(false);
        
        // open imported project, has to be done in AWT thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                File projectDir = new File(ImportPackageInfo.getUnzipDir() + File.separator + ImportPackageInfo.getProjectName());
                try {
                    Project project = (Project) ProjectManager.getDefault().findProject(FileUtil.toFileObject(FileUtil.normalizeFile(projectDir)));
                    
                    // project is null for external source roots
                    if (project!=null) {
                        OpenProjects.getDefault().open(new Project[] { project },false);
                    }
                } catch (IOException e) {
                    System.err.println(java.util.ResourceBundle.getBundle(Constants.BUNDLE).getString("IO_error:_")+e);
                }
            }
        });
        
        if (!isSilent()) {
            handle.finish();
        } else {
            return;
        }
        
        String errorMsg = "";
        if (scheduledTasks.get("unzip-project")!=null && !((Boolean) status.get("unzip-project")).booleanValue()) {
            errorMsg+=java.util.ResourceBundle.getBundle(Constants.BUNDLE).getString("Could_not_unzip_project._Disk_error?");
        }
        if (scheduledTasks.get("delete-zip")!=null && !((Boolean) status.get("delete-zip")).booleanValue()) {
            errorMsg+=java.util.ResourceBundle.getBundle(Constants.BUNDLE).getString("Could_not_delete_zip.__Disk_not_writable?");
        }
        if (!errorMsg.equals("")) {
            NotifyDescriptor d = new NotifyDescriptor.Message(errorMsg, NotifyDescriptor.ERROR_MESSAGE);
            d.setTitle(java.util.ResourceBundle.getBundle(Constants.BUNDLE).getString("Error_during_processing_zips"));
            DialogDisplayer.getDefault().notify(d);
        } else {
            if (scheduledTasks.get("delete-zip")==null && ((Boolean) scheduledTasks.get("unzip-project")).booleanValue()) {
                NotifyDescriptor d = new NotifyDescriptor.Message(java.util.ResourceBundle.getBundle(Constants.BUNDLE).getString("Project_imported_successfully."), NotifyDescriptor.INFORMATION_MESSAGE);
                d.setTitle(java.util.ResourceBundle.getBundle(Constants.BUNDLE).getString("Zip_importer_finished"));
                DialogDisplayer.getDefault().notify(d);
                return;
            }
            if (((Boolean) scheduledTasks.get("delete-zip")).booleanValue()) {
                NotifyDescriptor d = new NotifyDescriptor.Message(java.util.ResourceBundle.getBundle(Constants.BUNDLE).getString("Project_imported_successfully,_original_zip_deleted."), NotifyDescriptor.INFORMATION_MESSAGE);
                d.setTitle(java.util.ResourceBundle.getBundle(Constants.BUNDLE).getString("Zip_importer_finished"));
                DialogDisplayer.getDefault().notify(d);
            }
        }
    }
    
    /**
     * Schedules a task for import
     * @param script ant script
     * @param target targets for ant script
     * @param props ant script properties
     */
    public void schedule(FileObject script, String[] target, Properties props) {
        scripts.add(script);
        targets.add(target);
        properties.add(props);
        scheduledCount++;
        scheduledTasks.put(target[0], new Boolean(true));
    }
    
    /**
     * Show messages to user?
     * @return true if yes
     */
    public boolean isSilent() {
        return silent;
    }
    
    /**
     * Set showing messages to user
     * @param silent true if yes
     */
    public void setSilent(boolean silent) {
        this.silent = silent;
    }
    
    
}
