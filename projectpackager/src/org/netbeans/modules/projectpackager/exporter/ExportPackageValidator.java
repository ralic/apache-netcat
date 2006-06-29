/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.projectpackager.exporter;

import java.io.File;
import org.netbeans.modules.projectpackager.tools.Constants;
import org.netbeans.modules.projectpackager.tools.NotifyDescriptorInputPassword;
import org.netbeans.modules.projectpackager.tools.ProjectPackagerSettings;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;

/**
 * Validator of export package
 * @author Roman "Roumen" Strobl
 */
public class ExportPackageValidator {

    /** Creates a new instance of PackageValidator */
    private ExportPackageValidator() {
    }
    
    /**
     * Validates the whole package which will be exported
     * @return true if all ok
     */
    public static boolean validate() {
        boolean selected = false;
        for (int i = 0; i<ProjectInfo.getProjectCount(); i++) {
            if (ProjectInfo.isSelected(i)) selected = true;
        }
        if (!selected) {
            NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getBundle(Constants.BUNDLE).getString("No_projects_were_chosen._Please_choose_at_least_one_project_in_the_dialog."), NotifyDescriptor.ERROR_MESSAGE);
            d.setTitle(NbBundle.getBundle(Constants.BUNDLE).getString("Error:_no_project_selected"));
            DialogDisplayer.getDefault().notify(d);
            return false;
        }
        
        if (ExportPackageInfo.getTargetDir().equals("")) {
            NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getBundle(Constants.BUNDLE).getString("Please_specify_a_directory_where_to_store_zip_files."), NotifyDescriptor.ERROR_MESSAGE);
            d.setTitle(NbBundle.getBundle(Constants.BUNDLE).getString("Error:_target_directory_not_specified"));
            DialogDisplayer.getDefault().notify(d);
            return false;            
        }
        
        File target = new File(ExportPackageInfo.getTargetDir());
        if (!target.canWrite()) {
            NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getBundle(Constants.BUNDLE).getString("Target_directory_is_not_writable._Please_choose_a_different_directory."), NotifyDescriptor.ERROR_MESSAGE);
            d.setTitle(NbBundle.getBundle(Constants.BUNDLE).getString("Error:_target_directory_not_writable"));
            DialogDisplayer.getDefault().notify(d);
            return false;            
        }
        
        if (ExportPackageInfo.isSendMail() && ExportPackageInfo.getEmail().equals("")) {
            NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getBundle(Constants.BUNDLE).getString("No_e-mail_address_specified._Please_fill_in_your_e-mail_address_in_the_dialog."), NotifyDescriptor.ERROR_MESSAGE);
            d.setTitle(NbBundle.getBundle(Constants.BUNDLE).getString("Error:_no_e-mail_address"));
            DialogDisplayer.getDefault().notify(d);
            return false;                        
        }

        ProjectPackagerSettings pps = ProjectPackagerSettings.getDefault();                
        
        if (ExportPackageInfo.isSendMail()) {
            if (ExportPackageInfo.getSmtpServer().equals("")) {
                NotifyDescriptor.InputLine d = new NotifyDescriptor.InputLine(NbBundle.getBundle(Constants.BUNDLE).getString("SMTP_server:"), NbBundle.getBundle(Constants.BUNDLE).getString("Please_specify_a_SMTP_server"));
                DialogDisplayer.getDefault().notify(d);
                if (d.getInputText().equals("")) {
                    return false;
                } else {
                    pps.setSmtpServer(d.getInputText());
                }
                ExportPackageInfo.setSmtpServer(d.getInputText());
                NotifyDescriptor.InputLine d2 = new NotifyDescriptor.InputLine(NbBundle.getBundle(Constants.BUNDLE).getString("Username_(optional):"), NbBundle.getBundle(Constants.BUNDLE).getString("Please_enter_username_for_SMTP_server"));
                DialogDisplayer.getDefault().notify(d2);
                ExportPackageInfo.setSmtpUsername(d2.getInputText());
                pps.setSmtpUsername(d2.getInputText());
                NotifyDescriptorInputPassword d3 = new NotifyDescriptorInputPassword(NbBundle.getBundle(Constants.BUNDLE).getString("Password_(optional):"), NbBundle.getBundle(Constants.BUNDLE).getString("Please_enter_password_for_SMTP_server"));
                DialogDisplayer.getDefault().notify(d3);
                ExportPackageInfo.setSmtpPassword(d3.getInputText());
                // do not save password to settings from security reasons
            }        

            if (ExportPackageInfo.getSmtpUsername().equals("") && ExportPackageInfo.getSmtpPassword().equals("")) {
                NotifyDescriptor.InputLine d2 = new NotifyDescriptor.InputLine(NbBundle.getBundle(Constants.BUNDLE).getString("Username_(optional):"), NbBundle.getBundle(Constants.BUNDLE).getString("Please_enter_username_for_SMTP_server"));
                DialogDisplayer.getDefault().notify(d2);
                ExportPackageInfo.setSmtpUsername(d2.getInputText());
                pps.setSmtpUsername(d2.getInputText());
                NotifyDescriptorInputPassword d = new NotifyDescriptorInputPassword(NbBundle.getBundle(Constants.BUNDLE).getString("SMTP_password:"), NbBundle.getBundle(Constants.BUNDLE).getString("Please_enter_password_for_SMTP_server"));
                DialogDisplayer.getDefault().notify(d);
                ExportPackageInfo.setSmtpPassword(d.getInputText());
                // do not save password to settings from security reasons
            } else if (!ExportPackageInfo.getSmtpUsername().equals("") && ExportPackageInfo.getSmtpPassword().equals("")) {
                NotifyDescriptorInputPassword d = new NotifyDescriptorInputPassword(NbBundle.getBundle(Constants.BUNDLE).getString("SMTP_password:"), NbBundle.getBundle(Constants.BUNDLE).getString("Please_enter_password_for_SMTP_server"));
                DialogDisplayer.getDefault().notify(d);
                ExportPackageInfo.setSmtpPassword(d.getInputText());
            }
        }

        return true;
    }
    
}
