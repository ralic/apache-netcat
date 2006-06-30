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

package org.netbeans.modules.corba.wizard.nodes.gui;

import javax.swing.event.*;
import org.netbeans.modules.corba.wizard.nodes.utils.IdlUtilities;
/**
 *
 * @author  root
 * @version
 */
public class AliasPanel extends ExPanel implements DocumentListener {

    /** Creates new form AliasPanel */
    public AliasPanel() {
        initComponents ();
        postInitComponents ();
    }

    public String getName () {
        return this.name.getText().trim();
    }

    public void setName (String name) {
	this.name.setText (name);
    }
  
    public String getType () {
        return this.type.getText().trim();
    }
    
    public void setType (String type) {
	this.type.setText (type);
    }
  
    public String getLength () {
        return this.length.getText();
    }
    
    public void setLength (String length) {
	this.length.setText (length);
    }

    private void postInitComponents () {
        this.name.getDocument().addDocumentListener (this);
        this.type.getDocument().addDocumentListener (this);
        this.length.getDocument().addDocumentListener (this);
        this.jLabel1.setDisplayedMnemonic (this.bundle.getString("TXT_ModuleName_MNE").charAt(0));
        this.jLabel2.setDisplayedMnemonic (this.bundle.getString("TXT_Type_MNE").charAt(0));
        this.jLabel3.setDisplayedMnemonic (this.bundle.getString("TXT_Length_MNE").charAt(0));
        this.getAccessibleContext().setAccessibleDescription (this.bundle.getString ("AD_AliasPanel"));
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        type = new javax.swing.JTextField();
        length = new javax.swing.JTextField();

        setLayout(new java.awt.GridBagLayout());

        setPreferredSize(new java.awt.Dimension(250, 80));
        jLabel1.setText(bundle.getString("TXT_ModuleName"));
        jLabel1.setLabelFor(name);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 4, 4);
        add(jLabel1, gridBagConstraints);

        jLabel2.setText(bundle.getString("TXT_Type"));
        jLabel2.setLabelFor(type);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 8, 4, 4);
        add(jLabel2, gridBagConstraints);

        jLabel3.setText(bundle.getString("TXT_Length"));
        jLabel3.setLabelFor(length);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 8, 8, 4);
        add(jLabel3, gridBagConstraints);

        name.setToolTipText(bundle.getString("TIP_AliasName"));
        name.setPreferredSize(new java.awt.Dimension(100, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 4, 4, 8);
        add(name, gridBagConstraints);

        type.setToolTipText(bundle.getString("TIP_AliasType"));
        type.setPreferredSize(new java.awt.Dimension(100, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 8);
        add(type, gridBagConstraints);

        length.setToolTipText(bundle.getString("TIP_AliasLength"));
        length.setPreferredSize(new java.awt.Dimension(100, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 8, 8);
        add(length, gridBagConstraints);

    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField length;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField type;
    private javax.swing.JTextField name;
    // End of variables declaration//GEN-END:variables

    private static final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/wizard/nodes/gui/Bundle");    

    public void removeUpdate(final javax.swing.event.DocumentEvent p1) {
        checkState ();
    }

    public void changedUpdate(final javax.swing.event.DocumentEvent p1) {
        checkState ();
    }

    public void insertUpdate(final javax.swing.event.DocumentEvent p1) {
        checkState ();
    }

    private void checkState () {
        if (this.type.getText().length() > 0 && 
            IdlUtilities.isValidIDLIdentifier (this.name.getText()) &&
            IdlUtilities.validLength(this.length.getText())) {
            enableOk();
        }
        else {
            disableOk();
        }
    }
}
