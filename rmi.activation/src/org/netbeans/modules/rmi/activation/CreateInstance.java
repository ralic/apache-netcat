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
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
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
 */

package org.netbeans.modules.rmi.activation;

import java.io.File;

import javax.swing.JFileChooser;

import org.openide.*;
import org.openide.util.UserCancelException;

/**
 * Panel to find out created object instance source.
 * @author  vzboril, Jan Pokorsky
 */
public class CreateInstance extends javax.swing.JPanel {

    /** Creates new form Activation2 */
    public CreateInstance() {
        initComponents();

        internalization();
        this.getAccessibleContext().setAccessibleDescription(getString("AD_CreateInstance"));
        
        javax.swing.ButtonGroup group = new javax.swing.ButtonGroup();
        group.add(jRadioButton1);
        group.add(jRadioButton2);
        group.add(jRadioButton3);
    }
    
    private void internalization() {
        jRadioButton1.setText(getString("LBL_CreateInstance.Class")); // NOI18N
        jRadioButton2.setText(getString("LBL_CreateInstance.File")); // NOI18N
        jRadioButton3.setText(getString("LBL_CreateInstance.Empty")); // NOI18N
        jRadioButton1.setMnemonic(getMnemonic("LBL_CreateInstance.Class")); // NOI18N
        jRadioButton2.setMnemonic(getMnemonic("LBL_CreateInstance.File")); // NOI18N
        jRadioButton3.setMnemonic(getMnemonic("LBL_CreateInstance.Empty")); // NOI18N
        jButtonClass.setText(getString("LBL_CreateInstance.ClassButton")); // NOI18N
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jRadioButton1 = new javax.swing.JRadioButton();
        jTextField1 = new javax.swing.JTextField();
        jButtonClass = new javax.swing.JButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jTextField2 = new javax.swing.JTextField();
        jButtonFile = new javax.swing.JButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("jRadioButton1");
        jRadioButton1.getAccessibleContext().setAccessibleDescription(getString("AD_CreateInstance.ClassRB"));
        jRadioButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton1StateChanged(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.insets = new java.awt.Insets(12, 12, 0, 0);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        add(jRadioButton1, gridBagConstraints1);
        
        jTextField1.setColumns(30);
        jTextField1.getAccessibleContext().setAccessibleName(getString("AN_CreateInstance.Class"));
        jTextField1.getAccessibleContext().setAccessibleDescription(getString("AD_CreateInstance.Class"));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(12, 5, 0, 0);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints1.weightx = 1.0;
        add(jTextField1, gridBagConstraints1);
        
        jButtonClass.setText("...");
        jButtonClass.setEnabled(false);
        jButtonClass.getAccessibleContext().setAccessibleDescription(getString("AD_CreateInstance.ClassButton"));
        jButtonClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClassActionPerformed(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints1.insets = new java.awt.Insets(12, 5, 0, 12);
        add(jButtonClass, gridBagConstraints1);
        
        jRadioButton2.setText("jRadioButton2");
        jRadioButton2.getAccessibleContext().setAccessibleDescription(getString("AD_CreateInstance.FileRB"));
        jRadioButton2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton2StateChanged(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.insets = new java.awt.Insets(12, 12, 0, 0);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        add(jRadioButton2, gridBagConstraints1);
        
        jTextField2.setEditable(false);
        jTextField2.setColumns(20);
        jTextField2.getAccessibleContext().setAccessibleName(getString("AN_CreateInstance.File"));
        jTextField2.getAccessibleContext().setAccessibleDescription(getString("AD_CreateInstance.File"));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(11, 5, 0, 0);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints1.weightx = 1.0;
        add(jTextField2, gridBagConstraints1);
        
        jButtonFile.setText("...");
        jButtonFile.setEnabled(false);
        jButtonFile.getAccessibleContext().setAccessibleDescription(getString("AD_CreateInstance.FileButton"));
        jButtonFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFileActionPerformed(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints1.insets = new java.awt.Insets(11, 5, 0, 12);
        add(jButtonFile, gridBagConstraints1);
        
        jRadioButton3.setText("jRadioButton3");
        jRadioButton3.getAccessibleContext().setAccessibleDescription(getString("AD_CreateInstance.EmptyRB"));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.insets = new java.awt.Insets(12, 12, 17, 0);
        add(jRadioButton3, gridBagConstraints1);
        
    }//GEN-END:initComponents

    private void jButtonClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClassActionPerformed
        // Add your handling code here:
        ClassChooser chooser = ClassChooser.getInstance();
        try {
            chooser.show();
            jTextField1.setText(chooser.getFullClassName());
        } catch (UserCancelException ex) {
            // nothing to do
        }
    }//GEN-LAST:event_jButtonClassActionPerformed

    private void jRadioButton2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton2StateChanged
        // Add your handling code here:
        boolean tmp = jRadioButton2.isSelected();
        jButtonFile.setEnabled(tmp);
        jTextField2.setEditable(tmp);
        if (jTextField2.isEnabled()) jTextField2.requestFocus();
    }//GEN-LAST:event_jRadioButton2StateChanged

    private void jRadioButton1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton1StateChanged
        // Add your handling code here:
        boolean tmp = jRadioButton1.isSelected();
        jButtonClass.setEnabled(tmp);
        jTextField1.setEditable(tmp);
        if (jTextField1.isEnabled()) jTextField1.requestFocus();
    }//GEN-LAST:event_jRadioButton1StateChanged

    private void jButtonFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFileActionPerformed
        // Add your handling code here:
        JFileChooser chooser = getFileChooser();
        if (org.openide.util.Utilities.showJFileChooser(chooser, this, null) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            jTextField2.setText(f.getAbsolutePath());
        }
    }//GEN-LAST:event_jButtonFileActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jButtonClass;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton jButtonFile;
    private javax.swing.JRadioButton jRadioButton3;
    // End of variables declaration//GEN-END:variables

    /** Localization. */
    private static String getString(java.lang.String key) {
       return org.openide.util.NbBundle.getBundle (CreateInstance.class).getString(key);
    }
  
    private static final String mnemonic_suffix = ".mnemonic"; // NOI18N
    
    private static JFileChooser fileChooser = null;
   
    private char getMnemonic(java.lang.String key) {
       return org.openide.util.NbBundle.getBundle (CreateInstance.class).getString(key + mnemonic_suffix).charAt(0);
    }
    
    private JFileChooser getFileChooser() {
        if (fileChooser == null) {
            fileChooser = new JFileChooser();
            fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new FileExtChooserFilter(
                                        getString("LBL_CreateInstance.Filter.Description"), // NOI18N
                                        getString("FMT_CreateInstance.Filter") // NOI18N
            ));
        }
        return fileChooser;
    }
    
    public boolean isFromFile() {
        return jRadioButton2.isSelected();
    }
    
    public boolean isEmty() {
        return jRadioButton3.isSelected();
    }
    
    public String getClassName() {
        return jTextField1.getText();
    }
    
    public String getFileName() {
        return jTextField2.getText();
    }
    
}
