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

import javax.swing.event.DocumentListener;
import org.netbeans.modules.corba.wizard.nodes.utils.IdlUtilities;
/** 
 *
 * @author  root
 * @version 
 */
public class ModulePanel extends ExPanel implements DocumentListener {

    /** Creates new form ModulePanel */
    public ModulePanel() {
        initComponents ();
        postInitComponents ();
    }
  
    public String getName () {
        return this.name.getText().trim();
    }
    
    public void setName (String name) {
	this.name.setText (name);
    }


    public void postInitComponents () {
        this.name.getDocument().addDocumentListener(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jLabel1 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        setPreferredSize(new java.awt.Dimension(250, 32));

        jLabel1.setText(java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/wizard/nodes/gui/Bundle").getString("TXT_ModuleName"));

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.insets = new java.awt.Insets(8, 8, 8, 8);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        add(jLabel1, gridBagConstraints1);


        name.setToolTipText(java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/wizard/nodes/gui/Bundle").getString("TIP_Name"));
        name.setPreferredSize(new java.awt.Dimension(100, 16));

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(8, 0, 8, 8);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints1.weightx = 1.0;
        add(name, gridBagConstraints1);

    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField name;
    // End of variables declaration//GEN-END:variables


    private void checkState () {
        if (IdlUtilities.isValidIDLIdentifier(this.name.getText())) {
            enableOk();
        }
        else {
            disableOk();
        }
    }

    public void removeUpdate(final javax.swing.event.DocumentEvent p1) {
        checkState ();
    }

    public void changedUpdate(final javax.swing.event.DocumentEvent p1) {
        checkState ();
    }

    public void insertUpdate(final javax.swing.event.DocumentEvent p1) {
        checkState ();
    }
}
