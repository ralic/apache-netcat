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



package org.netbeans.modules.projectpackager.exporter;



import java.util.Vector;

import org.netbeans.modules.projectpackager.tools.ProjectPackagerSettings;



/**

 * E-mail settings dialog

 * @author Roman "Roumen" Strobl

 */

public class EmailSettingsDialog extends javax.swing.JFrame {

    

    /**

     * Constructor of the dialog

     */

    public EmailSettingsDialog() {

        initComponents();

        setLocationRelativeTo(null);        

        EmailSettingsUITools.setEmailSettingsDialog(this);

        final ProjectPackagerSettings pps = ProjectPackagerSettings.getDefault();

        smtpServerField.setText(pps.getSmtpServer());

        smtpUsernameField.setText(pps.getSmtpUsername());

        smtpPasswordField.setText(pps.getSmtpPassword());

        useSSL.setSelected(pps.getSmtpUseSSL().booleanValue());

    }

    

    /** This method is called from within the constructor to

     * initialize the form.

     * WARNING: Do NOT modify this code. The content of this method is

     * always regenerated by the Form Editor.

     */

    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        smtpServer = new javax.swing.JLabel();
        smtpServerField = new javax.swing.JTextField();
        smtpUsername = new javax.swing.JLabel();
        smtpUsernameField = new javax.swing.JTextField();
        smtpPassword = new javax.swing.JLabel();
        useSSL = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        smtpPasswordField = new javax.swing.JPasswordField();

        jLabel3.setText("jLabel3");
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("E-mail Settings");
        setResizable(false);
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        smtpServer.setText("SMTP Server:");

        smtpUsername.setText("SMTP Username:");

        smtpPassword.setText("SMTP Password:");

        useSSL.setText("Use SSL for SMTP");
        useSSL.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        useSSL.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel5.setText("Storing of passwords is not recommended.");

        jLabel6.setText("SMTP server is required, username and password is optional.");

        smtpPasswordField.setPreferredSize(new java.awt.Dimension(11, 18));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(useSSL)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(okButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cancelButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                    .add(jLabel6)
                    .add(jLabel5)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(smtpServer)
                            .add(smtpUsername)
                            .add(smtpPassword))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 13, Short.MAX_VALUE)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, smtpServerField)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(smtpPasswordField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 178, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(smtpUsernameField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 178, Short.MAX_VALUE))
                                .add(87, 87, 87)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(smtpServer)
                    .add(smtpServerField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(smtpUsername)
                    .add(smtpUsernameField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(smtpPassword)
                    .add(smtpPasswordField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(useSSL)
                .add(17, 17, 17)
                .add(jLabel5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel6)
                .add(43, 43, 43))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(176, 176, 176)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(okButton)
                    .add(cancelButton))
                .addContainerGap())
        );
        pack();
    }
    // </editor-fold>//GEN-END:initComponents



    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed

        EmailSettingsUITools.processOkButton();

    }//GEN-LAST:event_okButtonActionPerformed



    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed

        EmailSettingsUITools.processCancelButton();

    }//GEN-LAST:event_cancelButtonActionPerformed

                       

    /**

     * Returns filled in SMTP server

     * @return SMTP server

     */

    public String getSmtpServer() {

        return smtpServerField.getText();

    }



    /**

     * Returns filled in SMTP username

     * @return SMTP username

     */

    public String getSmtpUsername() {

        return smtpUsernameField.getText();

    }

    

    /**

     * Returns filled in SMTP password

     * @return SMTP password

     */

    public String getSmtpPassword() {

        return new String(smtpPasswordField.getPassword());

    }



    /**

     * Returns filled in Use SSL

     * @return Use SSL

     */

    public boolean getSmtpUseSSL() {

        return useSSL.isSelected();

    }

        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel smtpPassword;
    private javax.swing.JPasswordField smtpPasswordField;
    private javax.swing.JLabel smtpServer;
    private javax.swing.JTextField smtpServerField;
    private javax.swing.JLabel smtpUsername;
    private javax.swing.JTextField smtpUsernameField;
    private javax.swing.JCheckBox useSSL;
    // End of variables declaration//GEN-END:variables

    

}

