/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2000 Sun
 * Microsystems, Inc. All Rights Reserved.
 */


package org.netbeans.modules.vcscore.ui;

/**
 *@author Milos Kleint
 */

import javax.swing.*;

public class VerifyGroupPanel extends javax.swing.JPanel {

    /** Creates new form VerifyGroupPanel */
    public VerifyGroupPanel() {
        initComponents();
        setPreferredSize(new java.awt.Dimension(450, 450));
        setMinimumSize(new java.awt.Dimension(350, 300));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        lblResult = new javax.swing.JLabel();
        tblResults = new javax.swing.JTabbedPane();
        
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        lblResult.setText("jLabel1");
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.insets = new java.awt.Insets(12, 12, 0, 11);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        add(lblResult, gridBagConstraints1);
        
        tblResults.setPreferredSize(new java.awt.Dimension(200, 100));
        tblResults.setMinimumSize(new java.awt.Dimension(200, 100));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(2, 12, 11, 11);
        gridBagConstraints1.weightx = 0.9;
        gridBagConstraints1.weighty = 0.9;
        add(tblResults, gridBagConstraints1);
        
    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblResult;
    private javax.swing.JTabbedPane tblResults;
    // End of variables declaration//GEN-END:variables

    
    public void addPanel(JPanel panel, String title) {
        tblResults.add(title, panel);
    }
    
    public void setDescription(String desc) {
        lblResult.setText(desc);
    }
}
