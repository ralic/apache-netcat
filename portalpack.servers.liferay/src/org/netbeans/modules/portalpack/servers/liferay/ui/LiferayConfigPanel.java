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

package org.netbeans.modules.portalpack.servers.liferay.ui;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.modules.portalpack.servers.core.WizardPropertyReader;
import org.netbeans.modules.portalpack.servers.core.api.ConfigPanel;
import org.netbeans.modules.portalpack.servers.core.common.ServerConstants;
import org.netbeans.modules.portalpack.servers.core.impl.j2eeservers.tomcat.TomcatConstant;
import org.netbeans.modules.portalpack.servers.core.util.PSConfigObject;
import org.netbeans.modules.portalpack.servers.liferay.common.LiferayConstants;
import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;

/**
 *
 * @author  Satya
 */
public class LiferayConfigPanel extends ConfigPanel implements DocumentListener{
    
    private String psVersion;
    private WizardDescriptor wd;
    
    /** Creates new form LifeRayConfigPanel */
    public LiferayConfigPanel(String psVersion) {
        this.psVersion = psVersion;
        initComponents();
        initData();
        
        portalUri.getDocument().addDocumentListener(this);
        //adminConsoleUriTf.getDocument().addDocumentListener(this);
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        portalUri = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        hostTf = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        portletUriTf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        autoDeployTf = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();

        setFont(new java.awt.Font("Tahoma", 1, 11));

        jLabel1.setText(org.openide.util.NbBundle.getMessage(LiferayConfigPanel.class, "LBL_LIFERAY_PORTAL_SERVER")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(LiferayConfigPanel.class, "LBL_PORTAL_URI")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(LiferayConfigPanel.class, "LBL_HOST")); // NOI18N

        hostTf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                hostTfFocusLost(evt);
            }
        });

        jLabel6.setLabelFor(portletUriTf);
        jLabel6.setText(org.openide.util.NbBundle.getBundle(LiferayConfigPanel.class).getString("LBL_PORTLET_URI")); // NOI18N

        jLabel3.setLabelFor(autoDeployTf);
        jLabel3.setText(org.openide.util.NbBundle.getMessage(LiferayConfigPanel.class, "LBL_Auto_Deploy_Dir")); // NOI18N

        browseButton.setText(org.openide.util.NbBundle.getMessage(LiferayConfigPanel.class, "LBL_BrowseButton")); // NOI18N
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(169, 169, 169)
                .add(jLabel1)
                .addContainerGap(173, Short.MAX_VALUE))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                        .add(27, 27, 27))
                    .add(jLabel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                    .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(portletUriTf, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .add(portalUri, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .add(hostTf, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .add(autoDeployTf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 291, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(browseButton)
                .add(41, 41, 41))
            .add(layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addContainerGap(454, Short.MAX_VALUE))
        );

        layout.linkSize(new java.awt.Component[] {hostTf, portalUri, portletUriTf}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(11, 11, 11)
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(hostTf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(portalUri, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(portletUriTf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(autoDeployTf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(browseButton))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        browseButton.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(LiferayConfigPanel.class, "ACD_BROWSE")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    private void hostTfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hostTfFocusLost
// TODO add your handling code here:
        fireChangeEvent();
    }//GEN-LAST:event_hostTfFocusLost

private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
// TODO add your handling code here:
    String autoDeployLoc = browseAutoDeployLocation();
    if(autoDeployLoc != null)
        autoDeployTf.setText(autoDeployLoc);
}//GEN-LAST:event_browseButtonActionPerformed

    private boolean validatePCHome()
    {
//      
//        if(!config.exists() || !lib.exists())
//        {           
//            setErrorMessage(NbBundle.getMessage(LiferayConfigPanel.class,"MSG_INVALID_PC_HOME"));
//            return false;
//        }else{
//            
//            setErrorMessage("");
//            return true;
//        }
        return true;
    }
        
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField autoDeployTf;
    private javax.swing.JButton browseButton;
    private javax.swing.JTextField hostTf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField portalUri;
    private javax.swing.JTextField portletUriTf;
    // End of variables declaration//GEN-END:variables

    public void initData()
    {
       
       autoDeployTf.setText(System.getProperty("user.home")+File.separator + "liferay" + 
               File.separator + "deploy");
       portalUri.setText("/portal");
       portletUriTf.setText("/portal");
       hostTf.setText("localhost");
        
    }
    public void populateDataForCustomizer(PSConfigObject object) {
        
        //hostTf.setText(object.getHost());
        //portTf.setText(object.getPort());
        portalUri.setText(object.getPortalUri());

        hostTf.setText(object.getHost());
       // adminConsoleUriTf.setText(object.getProperty(LifeRayConstants.ADMIN_CONSOLE_URI));
        portletUriTf.setText(object.getProperty(LiferayConstants.PORTLET_URI));
        
        hostTf.setEnabled(false);
        browseButton.setEnabled(false);
        
    }

    public void read(org.openide.WizardDescriptor wizardDescriptor) {
        if(wd == null)
            this.wd = wizardDescriptor;
        
        WizardPropertyReader reader = new WizardPropertyReader(wizardDescriptor);
        
    }

    public void store(org.openide.WizardDescriptor d) {
             
        WizardPropertyReader wr = new WizardPropertyReader(d);                
        wr.setAdminUser("admin");
        wr.setAdminPassWord("adminadmin");
        //wr.setPort(portTf.getText());
        //wr.setAdminPort(portTf.getText());
        wr.setRemote(false);
        wr.setPortalUri(portalUri.getText());
        wr.setHost(hostTf.getText());
//        wr.setProperty(LifeRayConstants.ADMIN_CONSOLE_URI,adminConsoleUriTf.getText());
        wr.setProperty(LiferayConstants.PORTLET_URI,portletUriTf.getText());
        wr.setProperty(LiferayConstants.AUTO_DEPLOY_DIR, autoDeployTf.getText());
      //  wr.setPortalUri("/pcdriver");
    }

    public boolean validate(Object wizardDescriptor) {
        if(wd == null)
            return true;
        WizardPropertyReader wr = new WizardPropertyReader(((WizardDescriptor)wd));                
        //String serverHome = wr.getServerHome();
        String domainDir = wr.getDomainDir();
        String serverType = wr.getServerType();
        if(serverType.equals(ServerConstants.SUN_APP_SERVER_9)) {
            
            File file = new File(domainDir + File.separator + "lib" + File.separator + "portal-service.jar");
            if(!file.exists())
            {
                setErrorMessage(NbBundle.getMessage(LiferayConfigPanel.class, "MSG_NO_LIFERAY_INSTALLATION_FOUND"));
                return false;
            }
        } else if(serverType.equals(ServerConstants.TOMCAT_5_X)) {
            
            File file = new File(wr.getProperty(TomcatConstant.CATALINA_HOME) + File.separator +
                                        "common" + File.separator +
                                        "lib" + File.separator +
                                        "ext" + File.separator +
                                        "portal-service.jar");
            if(!file.exists())
            {
                setErrorMessage(NbBundle.getMessage(LiferayConfigPanel.class, "MSG_NO_LIFERAY_INSTALLATION_FOUND"));
                return false;
            }
        }
       
        setErrorMessage("");
        return true;
    }
    
    private String browseAutoDeployLocation(){
        String autoDeployLocation = null;
        JFileChooser chooser = getJFileChooser();
        int returnValue = chooser.showDialog(SwingUtilities.getWindowAncestor(this),
                NbBundle.getMessage(LiferayConfigPanel.class, "LBL_BrowseButton")); //NOI18N
        
        if(returnValue == JFileChooser.APPROVE_OPTION){
            autoDeployLocation = chooser.getSelectedFile().getAbsolutePath();
        }
        return autoDeployLocation;
    }
    
    private JFileChooser getJFileChooser(){
        
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(NbBundle.getMessage(LiferayConfigPanel.class, "LBL_ChooserName")); //NOI18N
        chooser.setDialogType(JFileChooser.CUSTOM_DIALOG);

        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setApproveButtonMnemonic("Choose_Button_Mnemonic".charAt(0)); //NOI18N
        chooser.setMultiSelectionEnabled(false);
        chooser.setApproveButtonToolTipText(NbBundle.getMessage(LiferayConfigPanel.class, "LBL_ChooserName")); //NOI18N

        chooser.getAccessibleContext().setAccessibleName(NbBundle.getMessage(LiferayConfigPanel.class, "LBL_ChooserName")); //NOI18N
        chooser.getAccessibleContext().setAccessibleDescription(NbBundle.getMessage(LiferayConfigPanel.class, "LBL_ChooserName")); //NOI18N

        // set the current directory
        String dir = System.getProperty("user.home");
        if(dir != null)
            chooser.setSelectedFile(new File(dir));

        return chooser;
    }


    public String getDescription() {
        return NbBundle.getMessage(LiferayConfigPanel.class, "DESC_LIFE_RAY");
    }

    public void insertUpdate(DocumentEvent e) {
        updateText();
    }

    public void removeUpdate(DocumentEvent e) {
        updateText();
    }

    public void changedUpdate(DocumentEvent e) {
        updateText();
    }
    
    public void updateText()
    {
        fireChangeEvent();
    }
    
}
