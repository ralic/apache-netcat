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
package org.netbeans.modules.portalpack.portlets.spring.ui;

import org.netbeans.modules.portalpack.portlets.genericportlets.core.PortletContext;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import org.netbeans.modules.portalpack.portlets.genericportlets.core.util.CoreUtil;
import org.openide.WizardDescriptor;
import javax.swing.JPanel;
import org.netbeans.api.project.Project;
import org.netbeans.modules.portalpack.portlets.genericportlets.core.actions.util.PortletProjectUtils;
import org.openide.util.NbBundle;

public final class PortletDetailsPanelGUI extends JPanel implements DocumentListener {

    private PortletDetailsPanel panel;

    /** Creates new form NetbeansNewPortletClassVisualPanel1 */
    public PortletDetailsPanelGUI(PortletDetailsPanel panel) {
        this.panel = panel;
        initComponents();

        pnameTf.getDocument().addDocumentListener(this);
        portletTitleTf.getDocument().addDocumentListener(this);
        portletShortTitleTf.getDocument().addDocumentListener(this);
        portletDisplayNameTf.getDocument().addDocumentListener(this);
        portletDescTf.getDocument().addDocumentListener(this);

    }

    public void initValues(Project project) {
        // String packageName = PortletProjectUtils.getProjectProperty(project, RubyProjectConstants.PROP_JSF_PAGEBEAN_PACKAGE);
        /*if(project == null)
        packageName = "com.ruby.portlet";
        if (packageName == null || packageName.length() == 0) {
        packageName = PortletProjectUtils.deriveSafeName(project.getProjectDirectory().getName());
        }*/

        //packageTextField.setEditable(!JsfProjectUtils.isJsfProject(project));
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(PortletDetailsPanelGUI.class, "TTL_NEW_PORTLET");
    }

    public void enableComponents(boolean status) {
        pnameTf.setEnabled(status);
        portletTitleTf.setEnabled(status);
        portletShortTitleTf.setEnabled(status);
        portletDisplayNameTf.setEnabled(status);
        portletDescTf.setEditable(status);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pnameTf = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        portletDisplayNameTf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        portletDescTf = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        portletTitleTf = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        portletShortTitleTf = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        viewCB = new javax.swing.JCheckBox();
        editCB = new javax.swing.JCheckBox();
        helpCB = new javax.swing.JCheckBox();

        jLabel1.setLabelFor(pnameTf);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "LBL_PORTLET_NAME")); // NOI18N

        jLabel2.setLabelFor(portletDisplayNameTf);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "LBL_PORTLET_DISPLAY_NAME")); // NOI18N

        jLabel3.setLabelFor(portletDescTf);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "LBL_PORTLET_DESC")); // NOI18N

        jLabel4.setLabelFor(portletTitleTf);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "LBL_PORTLET_TITLE")); // NOI18N

        jLabel5.setLabelFor(portletShortTitleTf);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "LBL_PORTLET_SHORT_TITLE")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "LBL_Portlet_Mode")); // NOI18N

        viewCB.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(viewCB, org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "LBL_VIEW")); // NOI18N
        viewCB.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(editCB, org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "LBL_EDIT")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(helpCB, org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "LBL_HELP")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(44, 44, 44)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(11, 11, 11))
                            .add(layout.createSequentialGroup()
                                .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(44, 44, 44))
                            .add(layout.createSequentialGroup()
                                .add(jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(15, 15, 15))
                            .add(layout.createSequentialGroup()
                                .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(37, 37, 37))
                            .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(60, 60, 60))
                    .add(layout.createSequentialGroup()
                        .add(jLabel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(98, 98, 98)))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(pnameTf, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .add(portletShortTitleTf, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .add(portletDescTf, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .add(portletDisplayNameTf, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, portletTitleTf, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(viewCB, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, Short.MAX_VALUE)
                        .add(72, 72, 72)
                        .add(editCB, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(64, 64, 64)
                        .add(helpCB, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .add(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(41, 41, 41)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(pnameTf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(portletDisplayNameTf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(portletDescTf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(portletTitleTf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(portletShortTitleTf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(helpCB)
                    .add(viewCB)
                    .add(editCB))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jLabel1.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "ACSN_Portlet_Name")); // NOI18N
        jLabel1.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "ACSD_Portlet_Name")); // NOI18N
        pnameTf.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "LBL_PORTLET_NAME")); // NOI18N
        pnameTf.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "ACSD_Portlet_Name")); // NOI18N
        jLabel2.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "ACSD_Portlet_Display_Name")); // NOI18N
        portletDisplayNameTf.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "LBL_PORTLET_DISPLAY_NAME")); // NOI18N
        portletDisplayNameTf.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "ACSD_Portlet_Display_Name")); // NOI18N
        jLabel3.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "LBL_PORTLET_DESC")); // NOI18N
        jLabel3.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "ACSD_Portlet_Description")); // NOI18N
        portletDescTf.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "LBL_PORTLET_DESC")); // NOI18N
        portletDescTf.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "ACSD_Portlet_Description")); // NOI18N
        jLabel4.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "LBL_PORTLET_TITLE")); // NOI18N
        jLabel4.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "ACSD_Portlet_Title")); // NOI18N
        jLabel5.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "LBL_PORTLET_SHORT_TITLE")); // NOI18N
        jLabel5.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "ACSD_Portlet_Short_Title")); // NOI18N
        portletShortTitleTf.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "LBL_PORTLET_SHORT_TITLE")); // NOI18N
        portletShortTitleTf.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "ACSD_Portlet_Short_Title")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox editCB;
    private javax.swing.JCheckBox helpCB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField pnameTf;
    private javax.swing.JTextField portletDescTf;
    private javax.swing.JTextField portletDisplayNameTf;
    private javax.swing.JTextField portletShortTitleTf;
    private javax.swing.JTextField portletTitleTf;
    private javax.swing.JCheckBox viewCB;
    // End of variables declaration//GEN-END:variables

    boolean valid(WizardDescriptor wizardDescriptor) {

        String portalName = pnameTf.getText();
        if (!CoreUtil.validateString(portalName, false)) {
            wizardDescriptor.putProperty("WizardPanel_errorMessage",
                    org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "MSG_INVALID_PORTLET_NAME"));
            return false;
        } else if (panel.getAvailablePortlets().contains(portalName)) {
            wizardDescriptor.putProperty("WizardPanel_errorMessage",
                    org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "MSG_PORTLET_ALREADY_PRESENT"));
            return false;
        } else if (!CoreUtil.validateString(portletTitleTf.getText(), true)) {
            wizardDescriptor.putProperty("WizardPanel_errorMessage",
                    org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "MSG_INVALID_PORTLET_TITLE"));
            return false;
        } else if (portletShortTitleTf.getText() != null &&
                portletShortTitleTf.getText().trim().length() != 0 &&
                !CoreUtil.validateString(portletShortTitleTf.getText(), true)) {
            wizardDescriptor.putProperty("WizardPanel_errorMessage",
                    org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "MSG_INVALID_PORTLET_SHORT_TITLE"));
            return false;
        } else if (!CoreUtil.validateXmlString(portletDisplayNameTf.getText().trim())) {
            wizardDescriptor.putProperty("WizardPanel_errorMessage",
                    org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "MSG_INVALID_PORTLET_DISPLAY_NAME"));
            return false;
        } else if (!CoreUtil.validateXmlString(portletDescTf.getText().trim())) {
            wizardDescriptor.putProperty("WizardPanel_errorMessage",
                    org.openide.util.NbBundle.getMessage(PortletDetailsPanelGUI.class, "MSG_INVALID_PORTLET_DESC"));
            return false;
        }


        wizardDescriptor.putProperty("WizardPanel_errorMessage", "");

        return true;
    }

    public void store(WizardDescriptor d) {

        PortletContext context = (PortletContext) d.getProperty("context");

        if (context == null) {
            context = new PortletContext();
        }
        context.setPortletName(pnameTf.getText().trim());
        context.setPortletDescription(portletDescTf.getText().trim());
        context.setPortletDisplayName(portletDisplayNameTf.getText().trim());
        context.setPortletTitle(portletTitleTf.getText().trim());
        context.setPortletShortTitle(portletShortTitleTf.getText().trim());

        List modeList = new ArrayList();

        if (viewCB.isSelected()) {
            modeList.add("VIEW");
        }
        if (editCB.isSelected()) {
            modeList.add("EDIT");
        }
        if (helpCB.isSelected()) {
            modeList.add("HELP");
        }
        context.setModes((String[]) modeList.toArray(new String[0]));

        d.putProperty("context", context);

    }

    public void readSettings(WizardDescriptor settings) {
    }

    // Implementation of DocumentListener --------------------------------------
    public void changedUpdate(DocumentEvent e) {
        updateTexts(e);
    // if (this.pnameTf.getDocument() == e.getDocument()) {
    //firePropertyChang
    //}
    }

    public void insertUpdate(DocumentEvent e) {
        updateTexts(e);
    //  if (this.pnameTf.getDocument() == e.getDocument()) {
    //firePropertyChange(PROP_PROJECT_NAME,null,this.projectNameTextField.getText());
    //  }
    }

    public void removeUpdate(DocumentEvent e) {
        updateTexts(e);
    //  if (this.pnameTf.getDocument() == e.getDocument()) {
    //firePropertyChange(PROP_PROJECT_NAME,null,this.projectNameTextField.getText());
    //  }
    }

    /** Handles changes in the Project name and project directory, */
    private void updateTexts(DocumentEvent e) {

        Document doc = e.getDocument();

        if (doc == pnameTf.getDocument()) {
            // Change in the project name

            String portletName = pnameTf.getText();
            portletDescTf.setText(portletName);
            portletDisplayNameTf.setText(portletName);
            portletTitleTf.setText(portletName);
            portletShortTitleTf.setText(portletName);



        }
        panel.fireChangeEvent(); // Notify that the panel changed

    }
}

