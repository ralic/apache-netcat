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

package org.netbeans.modules.spellchecker.options;

import java.awt.Dialog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import org.netbeans.modules.spellchecker.DefaultLocaleQueryImplementation;
import org.netbeans.modules.spellchecker.DictionaryProviderImpl;
import org.netbeans.modules.spellchecker.options.DictionaryInstallerPanel.DictionaryDescription;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;

/**
 *
 * @author Jan Lahoda
 */
public class SpellcheckerOptionsPanel extends javax.swing.JPanel {
    
    private List<Locale> removedDictionaries = new ArrayList<Locale>();
    private List<DictionaryDescription> addedDictionaries = new ArrayList<DictionaryDescription>();

    /**
     * Creates new form SpellcheckerOptionsPanel
     */
    public SpellcheckerOptionsPanel() {
        initComponents();
    }

    public void update() {
        removedDictionaries.clear();
        addedDictionaries.clear();

        updateLocales();
        
        defaultLocale.setSelectedItem(DefaultLocaleQueryImplementation.getDefaultLocale());
    }

    private void updateLocales() {
        DefaultListModel model = new DefaultListModel();
        List<Locale> locales = new ArrayList(Arrays.asList(DictionaryProviderImpl.getInstalledDictionariesLocales()));

        for (DictionaryDescription desc : addedDictionaries) {
            locales.add(desc.getLocale());
        }
        
        locales.removeAll(removedDictionaries);

        for (Locale l : locales) {
            model.addElement(l);
        }
        
        installedLocalesList.setModel(model);
    }
    
    public void commit() {
        //Add dictionaries:
        for (DictionaryDescription desc : addedDictionaries) {
            DictionaryInstallerPanel.doInstall(desc);
        }

        //Remove dictionaries:
        for (Locale remove : removedDictionaries) {
            DictionaryInstallerPanel.removeDictionary(remove);
        }
        
        DefaultLocaleQueryImplementation.setDefaultLocale((Locale) defaultLocale.getSelectedItem());
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        installedLocalesList = new javax.swing.JList();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        defaultLocale = new javax.swing.JComboBox();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Dictionaries"));
        installedLocalesList.setModel(getInstalledDictionariesModel());
        installedLocalesList.setVisibleRowCount(4);
        jScrollPane1.setViewportView(installedLocalesList);

        org.openide.awt.Mnemonics.setLocalizedText(jButton4, "Add...");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton5, "Remove");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(jButton5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jButton4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(118, 118, 118))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jButton4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton5))
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(SpellcheckerOptionsPanel.class, "LBL_Default_Locale_Panel", new Object[] {})));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(SpellcheckerOptionsPanel.class, "LBL_Default_Locale", new Object[] {}));

        defaultLocale.setEditable(true);
        defaultLocale.setModel(getLocaleModel());

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(defaultLocale, 0, 244, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(defaultLocale, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        for (Object o : installedLocalesList.getSelectedValues()) {
            removedDictionaries.add((Locale) o);
        }
        updateLocales();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        DictionaryInstallerPanel panel = new DictionaryInstallerPanel();
        DialogDescriptor dd = new DialogDescriptor(panel, "Add Dictionary");
        Dialog d = DialogDisplayer.getDefault().createDialog(dd);

        d.setVisible(true);

        if (dd.getValue() == DialogDescriptor.OK_OPTION) {
            DictionaryDescription desc = panel.createDescription();

            addedDictionaries.add(desc);
            removedDictionaries.remove(desc.getLocale());
            updateLocales();
        }
    }//GEN-LAST:event_jButton4ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox defaultLocale;
    private javax.swing.JList installedLocalesList;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    
    private ListModel getInstalledDictionariesModel() {
        DefaultListModel dlm = new DefaultListModel();

        for (Locale l : DictionaryProviderImpl.getInstalledDictionariesLocales()) {
            dlm.addElement(l);
        }

        return dlm;
    }

    private ComboBoxModel getLocaleModel() {
        DefaultComboBoxModel dlm = new DefaultComboBoxModel();
        List<Locale> locales = new ArrayList<Locale>(Arrays.asList(Locale.getAvailableLocales()));
        
        Collections.sort(locales, new LocaleComparator());
        
        for (Locale l : locales) {
            dlm.addElement(l);
        }
        
        return dlm;
    }
    
    private static class LocaleComparator implements Comparator<Locale> {
        
        public int compare(Locale o1, Locale o2) {
            return o1.toString().compareTo(o2.toString());
        }
        
    }
}
