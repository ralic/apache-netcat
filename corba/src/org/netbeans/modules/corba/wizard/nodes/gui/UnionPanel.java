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

package org.netbeans.modules.corba.wizard.nodes.gui;

/** 
 *
 * @author  root
 * @version 
 */
public class UnionPanel extends javax.swing.JPanel {

  /** Creates new form UnionPanel */
  public UnionPanel() {
    super ();
    initComponents ();
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the FormEditor.
   */
  private void initComponents () {//GEN-BEGIN:initComponents
    jLabel1 = new javax.swing.JLabel ();
    jLabel2 = new javax.swing.JLabel ();
    name = new javax.swing.JTextField ();
    name.setPreferredSize (new java.awt.Dimension (100,16));
    name.setToolTipText (java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/wizard/nodes/gui/Bundle").getString("TIP_UnionName"));
    type = new javax.swing.JTextField ();
    type.setPreferredSize (new java.awt.Dimension (100,16));
    type.setToolTipText (java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/wizard/nodes/gui/Bundle").getString("TIP_UnionDiscriminator"));
    setLayout (new java.awt.GridBagLayout ());
    java.awt.GridBagConstraints gridBagConstraints1;

    jLabel1.setText (java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/wizard/nodes/gui/Bundle").getString("TXT_ModuleName"));


    gridBagConstraints1 = new java.awt.GridBagConstraints ();
    gridBagConstraints1.insets = new java.awt.Insets (8, 8, 4, 4);
    gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
    add (jLabel1, gridBagConstraints1);

    jLabel2.setText (java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/wizard/nodes/gui/Bundle").getString("TXT_DiscriminatorType"));


    gridBagConstraints1 = new java.awt.GridBagConstraints ();
    gridBagConstraints1.gridx = 0;
    gridBagConstraints1.gridy = 1;
    gridBagConstraints1.insets = new java.awt.Insets (4, 8, 8, 4);
    gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
    add (jLabel2, gridBagConstraints1);



    gridBagConstraints1 = new java.awt.GridBagConstraints ();
    gridBagConstraints1.gridwidth = 0;
    gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints1.insets = new java.awt.Insets (8, 4, 4, 8);
    gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints1.weightx = 1.0;
    add (name, gridBagConstraints1);



    gridBagConstraints1 = new java.awt.GridBagConstraints ();
    gridBagConstraints1.gridx = 1;
    gridBagConstraints1.gridy = 1;
    gridBagConstraints1.gridwidth = 0;
    gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints1.insets = new java.awt.Insets (4, 4, 8, 8);
    gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints1.weightx = 1.0;
    add (type, gridBagConstraints1);

  }//GEN-END:initComponents

  public String getName () {
    return this.name.getText();
  }
  
  public String getType () {
    return this.type.getText();
  }
  


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JTextField name;
  private javax.swing.JTextField type;
  // End of variables declaration//GEN-END:variables

}
