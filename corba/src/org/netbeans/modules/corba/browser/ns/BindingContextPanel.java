/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2001 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

/*

 * BindingContextPanel.java

 *

 * Created on July 22, 1999, 9:31 PM

 */





package org.netbeans.modules.corba.browser.ns;

import org.openide.util.NbBundle;



/**

 *

 * @author  jformanek

 * @version 

 */

public class BindingContextPanel extends javax.swing.JPanel {



    static final long serialVersionUID =8322471659336275904L;
    /** Creates new form BindingContextPanel */

    public BindingContextPanel() {

        initComponents ();
        
        java.util.ResourceBundle b = org.openide.util.NbBundle.getBundle("org/netbeans/modules/corba/browser/ns/Bundle"); //NOI18N
        nameLabel.setDisplayedMnemonic(b.getString("CTL_LabelName_MNE").charAt(0));    //NOI18N
        urlLabel.setDisplayedMnemonic(b.getString("CTL_LabelURL_MNE").charAt(0));      //NOI18N
        kindLabel.setDisplayedMnemonic(b.getString("CTL_LabelKind_MNE").charAt(0));    //NOI18N
        iorLabel.setDisplayedMnemonic(b.getString("CTL_LabelIOR_MNE").charAt(0));      //NOI18N

    }



    /** This method is called from within the constructor to

     * initialize the form.

     * WARNING: Do NOT modify this code. The content of this method is

     * always regenerated by the FormEditor.

     */

    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        kindLabel = new javax.swing.JLabel();
        kindField = new javax.swing.JTextField();
        urlLabel = new javax.swing.JLabel();
        urlField = new javax.swing.JTextField();
        iorLabel = new javax.swing.JLabel();
        iorField = new javax.swing.JTextField();
        fillPanel = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        nameLabel.setText(java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/browser/ns/Bundle").getString("CTL_LabelName"));
        nameLabel.setLabelFor(nameField);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 0, 0);
        add(nameLabel, gridBagConstraints);

        nameField.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(12, 10, 0, 11);
        add(nameField, gridBagConstraints);

        kindLabel.setText(java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/browser/ns/Bundle").getString("CTL_LabelKind"));
        kindLabel.setLabelFor(kindField);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 12, 0, 0);
        add(kindLabel, gridBagConstraints);

        kindField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kindFieldActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 11);
        add(kindField, gridBagConstraints);

        urlLabel.setText(java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/browser/ns/Bundle").getString("CTL_LabelURL"));
        urlLabel.setLabelFor(urlField);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 12, 0, 0);
        add(urlLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 11);
        add(urlField, gridBagConstraints);

        iorLabel.setText(java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/browser/ns/Bundle").getString("CTL_LabelIOR"));
        iorLabel.setLabelFor(iorField);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 12, 0, 0);
        add(iorLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 11);
        add(iorField, gridBagConstraints);

        fillPanel.setPreferredSize(new java.awt.Dimension(0, 0));
        fillPanel.setMinimumSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(fillPanel, gridBagConstraints);

    }//GEN-END:initComponents

    private void kindFieldActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kindFieldActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_kindFieldActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel iorLabel;
    private javax.swing.JTextField iorField;
    private javax.swing.JLabel kindLabel;
    private javax.swing.JLabel urlLabel;
    private javax.swing.JTextField kindField;
    private javax.swing.JTextField urlField;
    private javax.swing.JPanel fillPanel;
    // End of variables declaration//GEN-END:variables



    public String getName () {

        return nameField.getText ();

    }


    public String getKind () {

        return kindField.getText ();

    }


    public String getUrl () {

        return urlField.getText ();

    }


    public String getIOR () {

        return iorField.getText ();

    }

}

