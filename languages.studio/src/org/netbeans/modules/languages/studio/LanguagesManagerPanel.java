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

package org.netbeans.modules.languages.studio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultListModel;

import org.openide.ErrorManager;
import org.openide.cookies.OpenCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.Repository;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;

import org.netbeans.modules.languages.LanguagesManager;


/**
 *
 * @author  Jan Jancura
 */
public class LanguagesManagerPanel extends javax.swing.JPanel {
    
    private Map nameToMimeType = new HashMap ();
    
    
    /** Creates new form LanguagesManagerPanel */
    public LanguagesManagerPanel () {
        initComponents ();
        LanguagesManager lm = LanguagesManager.getDefault ();
        Set mimeTypes = lm.getSupportedMimeTypes ();
        List names = new ArrayList ();
        Iterator it = mimeTypes.iterator ();
        while (it.hasNext ()) {
            String mimeType = (String) it.next ();
            String name = mimeType;
            names.add (name);
            nameToMimeType.put (name, mimeType);
        }
        Collections.sort (names);
        DefaultListModel model = new DefaultListModel ();
        it = names.iterator ();
        while (it.hasNext ()) {
            String name = (String) it.next ();
            model.addElement (name);
        }
        ltLanguages.setModel (model);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        spLanguages = new javax.swing.JScrollPane();
        ltLanguages = new javax.swing.JList();
        lLanguages = new javax.swing.JLabel();
        bEdit = new javax.swing.JButton();
        lMimeType = new javax.swing.JLabel();
        tfMimeType = new javax.swing.JTextField();
        lExtensions = new javax.swing.JLabel();
        tfExtensions = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        ltLanguages.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ltLanguagesValueChanged(evt);
            }
        });

        spLanguages.setViewportView(ltLanguages);

        lLanguages.setText("Languages:");

        bEdit.setText("Edit...");
        bEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditActionPerformed(evt);
            }
        });

        lMimeType.setText("Mime Type:");

        tfMimeType.setEditable(false);

        lExtensions.setText("Extensions:");

        jButton1.setText("Create...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lLanguages)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .add(lExtensions)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(tfExtensions, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                            .add(layout.createSequentialGroup()
                                .add(lMimeType)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(tfMimeType, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                            .add(spLanguages, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, bEdit, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(lLanguages)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(bEdit)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton2))
                    .add(spLanguages, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lMimeType)
                    .add(tfMimeType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lExtensions)
                    .add(tfExtensions, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ltLanguagesValueChanged (javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ltLanguagesValueChanged
        String name = (String) ltLanguages.getSelectedValue ();
        if (name == null) return;
        String mimeType = (String) nameToMimeType.get (name);
        tfMimeType.setText (mimeType);
        List ext = Collections.singletonList (mimeType);
        StringBuilder sb = new StringBuilder ();
        Iterator it = ext.iterator ();
        while (it.hasNext ()) {
            sb.append (it.next ());
            if (it.hasNext ()) sb.append (", ");
        }
        tfExtensions.setText (sb.toString ());
    }//GEN-LAST:event_ltLanguagesValueChanged

    private void jButton2ActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bEditActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditActionPerformed
        String name = (String) ltLanguages.getSelectedValue ();
        if (name == null) return;
        String mimeType = (String) nameToMimeType.get (name);
        FileSystem fs = Repository.getDefault ().getDefaultFileSystem ();
        FileObject fo = fs.findResource ("Editors/" + mimeType + "/language.nbs");
        try {
            DataObject dob = DataObject.find (fo);
            OpenCookie oc = (OpenCookie) dob.getCookie (OpenCookie.class);
            oc.open ();
        } catch (DataObjectNotFoundException ex) {
            ErrorManager.getDefault ().notify (ex);
        }
    }//GEN-LAST:event_bEditActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bEdit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel lExtensions;
    private javax.swing.JLabel lLanguages;
    private javax.swing.JLabel lMimeType;
    private javax.swing.JList ltLanguages;
    private javax.swing.JScrollPane spLanguages;
    private javax.swing.JTextField tfExtensions;
    private javax.swing.JTextField tfMimeType;
    // End of variables declaration//GEN-END:variables
    
}
