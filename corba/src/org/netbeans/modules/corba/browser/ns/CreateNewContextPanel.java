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

package com.netbeans.enterprise.modules.corba.browser.ns;


/** 
 *
 * @author  kgardas
 * @version 
 */
public class CreateNewContextPanel extends javax.swing.JPanel {

  /** Creates new form CreateNewContextPanel */
  public CreateNewContextPanel() {
    initComponents ();
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the FormEditor.
   */
    private void initComponents () {//GEN-BEGIN:initComponents
      setLayout (new java.awt.GridBagLayout ());
      java.awt.GridBagConstraints gridBagConstraints1;
      setBorder (new javax.swing.border.EmptyBorder(new java.awt.Insets(8, 8, 8, 8)));

      nameLabel = new javax.swing.JLabel ();
      nameLabel.setText ("Name:");


      gridBagConstraints1 = new java.awt.GridBagConstraints ();
      gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
      add (nameLabel, gridBagConstraints1);

      nameField = new javax.swing.JTextField ();


      gridBagConstraints1 = new java.awt.GridBagConstraints ();
      gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
      gridBagConstraints1.insets = new java.awt.Insets (0, 8, 8, 0);
      gridBagConstraints1.weightx = 1.0;
      add (nameField, gridBagConstraints1);

      kindLabel = new javax.swing.JLabel ();
      kindLabel.setText ("Kind:");


      gridBagConstraints1 = new java.awt.GridBagConstraints ();
      gridBagConstraints1.gridy = 1;
      gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
      add (kindLabel, gridBagConstraints1);

      kindField = new javax.swing.JTextField ();


      gridBagConstraints1 = new java.awt.GridBagConstraints ();
      gridBagConstraints1.gridy = 1;
      gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
      gridBagConstraints1.insets = new java.awt.Insets (0, 8, 8, 0);
      gridBagConstraints1.weightx = 1.0;
      add (kindField, gridBagConstraints1);

    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel kindLabel;
    private javax.swing.JTextField kindField;
    // End of variables declaration//GEN-END:variables

   
   public String getName () {
      return nameField.getText ();
   }

   public String getKind () {
      return kindField.getText ();
   }

}
