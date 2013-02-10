/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2009 Sun Microsystems, Inc. All rights reserved.
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
 * Contributor(s):
 *
 * Portions Copyrighted 2009 Sun Microsystems, Inc.
 */
package org.netbeans.modules.licensechanger.wizard;

import java.awt.Component;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.modules.licensechanger.api.LicenseHeader;
import org.netbeans.modules.licensechanger.wizard.utils.WizardProperties;
import org.openide.filesystems.FileChooserBuilder;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;

/**
 * Panel displaying available license templates. Upon selection, the 
 * raw license template text is displayed. Users can add custom licenses 
 * from the file system. Users can set the currently selected project's default
 * license.
 * 
 * @author Tim Boudreau
 * @author Nils Hoffmann (Refactoring)
 */
public class LicenseChooserPanel extends javax.swing.JPanel implements Runnable, DocumentListener {

    private volatile boolean licensesLoaded;
    private volatile boolean loadingLicenses;

    /**
     * Creates new form LicenseChooser
     */
    public LicenseChooserPanel() {
        initComponents();
        enabled(false);
        licenseText.getDocument().addDocumentListener(this);
        licenseText.setText(WizardProperties.VALUE_DEFAULT_LICENSE_TEXT);
        setName("Choose License Header");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chooseLabel = new javax.swing.JLabel();
        selectLicense = new javax.swing.JComboBox();
        browseButton = new javax.swing.JButton();
        updateDefaultProjectLicense = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        licenseText = new javax.swing.JEditorPane();

        chooseLabel.setLabelFor(selectLicense);
        chooseLabel.setText(org.openide.util.NbBundle.getMessage(LicenseChooserPanel.class, "LicenseChooserPanel.chooseLabel.text")); // NOI18N

        selectLicense.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[loading licenses]" }));
        selectLicense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                licenseSelected(evt);
            }
        });

        browseButton.setText(org.openide.util.NbBundle.getMessage(LicenseChooserPanel.class, "LicenseChooserPanel.browseButton.text")); // NOI18N
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseForLicense(evt);
            }
        });

        updateDefaultProjectLicense.setText(org.openide.util.NbBundle.getMessage(LicenseChooserPanel.class, "LicenseChooserPanel.updateDefaultProjectLicense.text")); // NOI18N

        jScrollPane2.setMaximumSize(getSize());

        licenseText.setEditable(false);
        jScrollPane2.setViewportView(licenseText);
        licenseText.setContentType("text/plain");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chooseLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(selectLicense, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(updateDefaultProjectLicense)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(browseButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chooseLabel)
                    .addComponent(selectLicense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(browseButton)
                    .addComponent(updateDefaultProjectLicense))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void licenseSelected(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_licenseSelected
        if (loadingLicenses || !selectLicense.isEnabled()) {
            return;
        }
        loadLicenseFromResource((LicenseHeader) selectLicense.getSelectedItem());
    }//GEN-LAST:event_licenseSelected

    private void browseForLicense(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseForLicense
        File f;
        if ((f = new FileChooserBuilder(LicenseChooserPanel.class).setTitle(
                NbBundle.getMessage(LicenseChooserPanel.class, "BROWSE_LICENSE_TITLE")) //NOI18N
                .setFilesOnly(true).showOpenDialog()) != null) {
            updateDefaultProjectLicense.setSelected(false);
            DefaultComboBoxModel dcbm = (DefaultComboBoxModel) selectLicense.getModel();
            LicenseHeader lh = LicenseHeader.fromFile(f);
            LicenseHeader.addAsNetBeansTemplate(lh);
            for (LicenseHeader header : LicenseHeader.fromTemplates()) {
                if (header.getName().equals(lh.getName())) {
//                    System.out.println("Found newly added template!");
                    dcbm.addElement(header);
                    dcbm.setSelectedItem(header);
                    loadLicenseFromResource(header);
                }
            }
        }
    }//GEN-LAST:event_browseForLicense

    @Override
    public void addNotify() {
        super.addNotify();
        if (!licensesLoaded && !loadingLicenses) {
            loadingLicenses = true;
            enabled(false);
            RequestProcessor.getDefault().post(this);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private javax.swing.JLabel chooseLabel;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JEditorPane licenseText;
    private javax.swing.JComboBox selectLicense;
    private javax.swing.JCheckBox updateDefaultProjectLicense;
    // End of variables declaration//GEN-END:variables

    private void enabled(boolean val) {
        for (Component c : getComponents()) {
            c.setEnabled(val);
        }
    }

    private void initLicenses() throws IOException {
        assert !EventQueue.isDispatchThread();
        final DefaultComboBoxModel mdl = new DefaultComboBoxModel();
        for (LicenseHeader header : LicenseHeader.fromTemplates()) {
            mdl.addElement(header);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                mdl.setSelectedItem(NbBundle.getMessage(LicenseChooserPanel.class,
                        "MSG_SELECT_A_LICENSE")); //NOI18N
                selectLicense.setModel(mdl);
                enabled(true);
                licensesLoaded = true;
                loadingLicenses = false;
            }
        });
    }

    @Override
    public void run() {
        try {
            initLicenses();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private void loadLicenseFromResource(LicenseHeader header) {
        enabled(false);
        SwingUtilities.invokeLater(new ResourceLoader(header));
    }

    public String getLicenseText() {
        return licenseText.getText();
    }

    public String getLicenseName() {
        Object obj = selectLicense.getSelectedItem();
        if (obj != null && obj instanceof LicenseHeader) {
            LicenseHeader header = (LicenseHeader) obj;
            return header.getName();
        }
        return null;
    }

    public boolean isUpdateDefaultProjectLicense() {
        return updateDefaultProjectLicense.isSelected();
    }

    public void setUpdateDefaultProjectLicense(boolean b) {
        updateDefaultProjectLicense.setSelected(b);
    }

    private void updateLicense() {
        licenseText.setEditable(false);
        String txt = licenseText.getText();
        if (!txt.endsWith("\n")) {
            txt += "\n";
        }
        firePropertyChange(WizardProperties.KEY_LICENSE_TEXT, null, txt);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateLicense();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateLicense();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateLicense();
    }

    private class ResourceLoader implements Runnable {

        private LicenseHeader header;

        ResourceLoader(LicenseHeader header) {
            this.header = header;
        }

        @Override
        public void run() {
            licenseText.setText(header.getLicenseHeader());
            String mimeType = header.getFileObject().getMIMEType();
            licenseText.setContentType(mimeType);
            enabled(true);
        }
    }

    public void setLicenseText(String text) {
        licenseText.setText(text);
    }
}
