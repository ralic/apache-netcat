/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is the Jabber module.
 * The Initial Developer of the Original Software is Petr Nejedly
 * Portions created by Petr Nejedly are Copyright (c) 2004.
 * All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Contributor(s): Petr Nejedly
 */

package org.netbeans.modules.jabber.ui;

import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import javax.swing.SwingUtilities;
import org.netbeans.modules.jabber.Contact;
import org.netbeans.modules.jabber.Manager;
import org.netbeans.modules.jabber.Result;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;

/**
 *
 * @author  nenik
 */
public class AddContactUI extends javax.swing.JPanel {


    public static void addContact(Manager man) {
        new AddContactUI(man).showUI();
    }
    
    
    private Manager manager;
    private Result result;
    Dialog d;
    Lst listener = new Lst();
    
    /** Creates new form RemoveContactUI */
    private AddContactUI(Manager man) {
        manager = man;        
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jidLabel = new javax.swing.JLabel();
        jidField = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        presenceCheck = new javax.swing.JCheckBox();

        okButton.setLabel("OK");
        cancelButton.setLabel("Cancel");

        setLayout(new java.awt.GridBagLayout());

        jidLabel.setLabelFor(jidField);
        jidLabel.setText("Jabber ID: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        add(jidLabel, gridBagConstraints);

        jidField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jidFieldActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 6);
        gridBagConstraints.weightx = 1.0;
        add(jidField, gridBagConstraints);

        nameLabel.setLabelFor(nameField);
        nameLabel.setText("Name: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 0);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(nameLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 6);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        add(nameField, gridBagConstraints);

        presenceCheck.setText("Ask for presence notifications");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 6, 6);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        add(presenceCheck, gridBagConstraints);

    }//GEN-END:initComponents

    private void jidFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jidFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jidFieldActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField jidField;
    private javax.swing.JLabel jidLabel;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JCheckBox presenceCheck;
    // End of variables declaration//GEN-END:variables


    
    private void showUI() {
        DialogDescriptor desc = new DialogDescriptor(this, "Add ...", true,
            new Object[] {okButton, cancelButton}, okButton,
            DialogDescriptor.BOTTOM_ALIGN, null, listener);
        
        d = DialogDisplayer.getDefault().createDialog(desc);
        d.addWindowListener(listener);

        d.show();
    }
    
    void doAdd() {
        // prepare the UI
        this.setEnabled(false);
        okButton.setEnabled(false);
        d.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        // take over the properties
        String jid = jidField.getText();
        String name = nameField.getText();
        if (name.length() < 1) name = null;
        boolean ask = presenceCheck.isSelected();
        
        // send the request
        Contact.List cList = manager.getContactList();
        result = cList.addContact(jid, name, listener);
        if (ask) cList.askForPresence(jid);

    }

    private class Lst extends WindowAdapter implements ActionListener, Result.Callback, Runnable {
        boolean done;
        
        // cancel
        public void actionPerformed(java.awt.event.ActionEvent e) {
            Object src = e.getSource();
            if (src == okButton) { // OK pressed, start the addition
                doAdd();
            } else if (src == cancelButton) {
                checkCancel();
            }
        }
        
        // either cancel pressed or window closed by Esc key
        private void checkCancel() {
            done = true;
            if (result == null) { // before post
                d.dispose();
            } else { // already posted, have to cancel the result
                result.cancel(); // will fire callback
            }
            
        }
        // result came or cancelled, replan to AWT
        public void resultFinished(Result result) {
            done = true;
            SwingUtilities.invokeLater(this);
        }    
        
        // either finished removing or cancelled
        public void run() {
            d.dispose();
        }
        
        public void windowClosed(java.awt.event.WindowEvent e) {
            if (!done) checkCancel();
        }

        
    }
    
}
