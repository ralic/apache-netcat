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

import java.util.Vector;

/**
 * Import zip dialog
 * @author Roman "Roumen" Strobl
 */
public class ImportZipDialog extends javax.swing.JFrame {
    private Vector listData;
    
    /** Creates new form ZipProjectDialog */
    public ImportZipDialog() {
        initComponents();
        setLocationRelativeTo(null);      
        ImportZipUITools.setZipProjectDialog(this);        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        ZipProjectPanel = new javax.swing.JPanel();
        projectZip = new javax.swing.JLabel();
        zipChooseButton = new javax.swing.JButton();
        projectZipField = new javax.swing.JTextField();
        projectNameField = new javax.swing.JTextField();
        deleteCheckBox = new javax.swing.JCheckBox();
        projectName = new javax.swing.JLabel();
        unzipToDirectory = new javax.swing.JLabel();
        unzipToDirectoryField = new javax.swing.JTextField();
        dirChooseButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Import Project from Zip");
        setResizable(false);
        ZipProjectPanel.setLayout(new java.awt.GridBagLayout());

        ZipProjectPanel.setPreferredSize(new java.awt.Dimension(305, 180));
        ZipProjectPanel.setRequestFocusEnabled(false);
        projectZip.setText("Project Zip:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        ZipProjectPanel.add(projectZip, gridBagConstraints);

        zipChooseButton.setText("...");
        zipChooseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zipChooseButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        ZipProjectPanel.add(zipChooseButton, gridBagConstraints);

        projectZipField.setPreferredSize(new java.awt.Dimension(230, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 20, 3, 3);
        ZipProjectPanel.add(projectZipField, gridBagConstraints);

        projectNameField.setColumns(18);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 20, 3, 3);
        ZipProjectPanel.add(projectNameField, gridBagConstraints);

        deleteCheckBox.setText("Delete Zip after Import");
        deleteCheckBox.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(0, 0, 0, 0)));
        deleteCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 3, 3, 3);
        ZipProjectPanel.add(deleteCheckBox, gridBagConstraints);

        projectName.setText("Project Folder Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        ZipProjectPanel.add(projectName, gridBagConstraints);

        unzipToDirectory.setText("Unzip to Directory:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        ZipProjectPanel.add(unzipToDirectory, gridBagConstraints);

        unzipToDirectoryField.setPreferredSize(new java.awt.Dimension(230, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 20, 3, 3);
        ZipProjectPanel.add(unzipToDirectoryField, gridBagConstraints);

        dirChooseButton.setText("...");
        dirChooseButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        dirChooseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dirChooseButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 3, 3, 3);
        ZipProjectPanel.add(dirChooseButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(ZipProjectPanel, gridBagConstraints);

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 10, 3);
        getContentPane().add(okButton, gridBagConstraints);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 10, 10);
        getContentPane().add(cancelButton, gridBagConstraints);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

    private void dirChooseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dirChooseButtonActionPerformed
        ImportZipUITools.showDirectoryChooser();

    }//GEN-LAST:event_dirChooseButtonActionPerformed
    
    private void zipChooseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zipChooseButtonActionPerformed
        ImportZipUITools.showFileChooser();
    }//GEN-LAST:event_zipChooseButtonActionPerformed
        
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        ImportZipUITools.processCancelButton();
    }//GEN-LAST:event_cancelButtonActionPerformed
    
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        ImportZipUITools.processOkButton();
    }//GEN-LAST:event_okButtonActionPerformed

    /**
     * Set zip file
     * @param zip zip file
     */
    public void setZip(String zip) {
        projectZipField.setText(zip);
    }
    
    /**
     * Set unzip directory
     * @param zipDirectory directory
     */
    public void setUnZipDir(String zipDirectory) {
        unzipToDirectoryField.setText(zipDirectory);
    }
    
    /**
     * Set project name
     * @param name project name
     */
    public void setProjectName(String name) {
        projectNameField.setText(name);
    }
    
    /**
     * Return zip file
     * @return zip file
     */
    public String getZipFile() {
        return projectZipField.getText();
    }
    
    /**
     * Return unzip directory
     * @return directory
     */
    public String getUnzipDir() {
        return unzipToDirectoryField.getText();
    }
    
    /**
     * Is delete selected?
     * @return true if selected
     */
    public boolean isDeleteSelected() {
        return deleteCheckBox.isSelected();
    }
    
    /**
     * Return project name
     * @return project name
     */
    public String getProjectName() {
        return projectNameField.getText();
    }
    
    /**
     * Set project name label (used if it's a source root)
     */
    public void setProjectNameLabel(String label) {
        projectName.setText(label);
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ZipProjectPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JCheckBox deleteCheckBox;
    private javax.swing.JButton dirChooseButton;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel projectName;
    private javax.swing.JTextField projectNameField;
    private javax.swing.JLabel projectZip;
    private javax.swing.JTextField projectZipField;
    private javax.swing.JLabel unzipToDirectory;
    private javax.swing.JTextField unzipToDirectoryField;
    private javax.swing.JButton zipChooseButton;
    // End of variables declaration//GEN-END:variables
    
}
