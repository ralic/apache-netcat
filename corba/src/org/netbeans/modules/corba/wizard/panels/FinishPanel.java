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

package org.netbeans.modules.corba.wizard.panels;

import org.netbeans.modules.corba.wizard.CorbaWizardData;
import java.util.ResourceBundle;
import java.text.MessageFormat;

/** 
 *
 * @author  tzezula
 * @version 
 */
public class FinishPanel extends  AbstractWizardPanel {

    /** Creates new form FinishPanel */
    public FinishPanel() {
        initComponents ();
        postInitComponents ();
    }
  
    public void readCorbaSettings (CorbaWizardData data) {
        ResourceBundle bundle = ResourceBundle.getBundle("org/netbeans/modules/corba/wizard/panels/Bundle");
        Object[] msgs = new Object[7];
        int genMask = data.getGenerate ();
        if ((genMask & CorbaWizardData.IDL) == CorbaWizardData.IDL) {
            msgs[0] = bundle.getString ("TXT_GenIdl");
        }
        else {
            msgs[0] = bundle.getString ("TXT_ImpIdl");
        }
        if ((genMask & CorbaWizardData.IMPL) == CorbaWizardData.IMPL) {
            msgs[1]= ", ";
            msgs[2]= bundle.getString ("TXT_GenImpl");
        }
        else {
            msgs[1]="";
            msgs[2]="";
        }
        if ((genMask & CorbaWizardData.CLIENT) == CorbaWizardData.CLIENT) {
            msgs[3]=", ";
            msgs[4]= bundle.getString("TXT_GenClient");
        }
        else {
            msgs[3]="";
            msgs[4]="";
        }
        if ((genMask & CorbaWizardData.SERVER) == CorbaWizardData.SERVER) {
            msgs[5]=", ";
            msgs[6]= bundle.getString("TXT_GenServer");
        }
        else {
            msgs[5]="";
            msgs[6]="";
        }
        jTextArea1.setText(MessageFormat.format (bundle.getString("TXT_Generate"),msgs));
    }
  
    public void storeCorbaSettings (CorbaWizardData data) {
    }
  
    private void postInitComponents () {
        this.jTextArea1.setBackground ( this.getBackground ());
        this.jTextArea1.setEditable (false);
        this.jTextArea1.setLineWrap (true);
        this.jTextArea1.setWrapStyleWord (true);
        this.jTextArea1.setEditable (false);
    }

    public boolean isValid () {
        return true;
    }
  
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jLabel1 = new javax.swing.JLabel();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        setPreferredSize(new java.awt.Dimension(500, 340));

        jLabel1.setText(java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/wizard/panels/Bundle").getString("TXT_CodeGenerator"));
        jLabel1.setFont(new java.awt.Font ("Dialog", 0, 18));


        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(8, 8, 4, 8);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints1.weightx = 1.0;
        add(jLabel1, gridBagConstraints1);

        jTextArea1.setPreferredSize(new java.awt.Dimension(400, 50));
        jTextArea1.setMinimumSize(new java.awt.Dimension(400, 50));
        jTextArea1.setText(java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/wizard/panels/Bundle").getString("TXT_Generate"));


        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(4, 8, 4, 8);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints1.weightx = 1.0;
        add(jTextArea1, gridBagConstraints1);



        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 4;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(4, 8, 4, 8);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        add(jPanel1, gridBagConstraints1);

    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
