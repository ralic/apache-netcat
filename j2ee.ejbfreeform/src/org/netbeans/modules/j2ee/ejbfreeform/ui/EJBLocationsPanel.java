/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2004 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.j2ee.ejbfreeform.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import org.netbeans.api.project.Project;
import org.netbeans.api.queries.CollocationQuery;
import org.netbeans.modules.ant.freeform.spi.ProjectPropertiesPanel;
import org.netbeans.modules.ant.freeform.spi.support.Util;
import org.netbeans.modules.j2ee.ejbfreeform.EJBProjectGenerator;
import org.openide.filesystems.FileUtil;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.netbeans.spi.project.AuxiliaryConfiguration;
import org.netbeans.spi.project.support.ant.AntProjectHelper;
import org.netbeans.spi.project.support.ant.PropertyUtils;
import org.netbeans.spi.project.support.ant.PropertyEvaluator;


/**
 *
 * @author  Radko Najman
 */
public class EJBLocationsPanel extends javax.swing.JPanel implements HelpCtx.Provider {
    
    /** Original project base folder */
    private File baseFolder;
    /** Freeform Project base folder */
    private File nbProjectFolder;

    private AntProjectHelper projectHelper;
    
    /** Creates new form EJBLocations */
    public EJBLocationsPanel() {
        initComponents();
        
        jComboBoxJ2eeLevel.addItem(NbBundle.getMessage(EJBLocationsPanel.class, "TXT_J2EESpecLevel_0"));
        jComboBoxJ2eeLevel.addItem(NbBundle.getMessage(EJBLocationsPanel.class, "TXT_J2EESpecLevel_1"));
        jComboBoxJ2eeLevel.setSelectedIndex(0);
    }
    
    public EJBLocationsPanel(Project project, AntProjectHelper projectHelper, PropertyEvaluator projectEvaluator, AuxiliaryConfiguration aux) {
        this();
        this.projectHelper = projectHelper;
        setFolders(Util.getProjectLocation(projectHelper, projectEvaluator), FileUtil.toFile(projectHelper.getProjectDirectory()));
        
        List l = EJBProjectGenerator.getEJBmodules(projectHelper, aux);
        if (l != null) {
            EJBProjectGenerator.EJBModule wm = (EJBProjectGenerator.EJBModule)l.get(0);
            String configFiles = getLocationDisplayName(projectEvaluator, baseFolder, wm.configFiles);
            String classpath = getLocationDisplayName(projectEvaluator, baseFolder, wm.classpath);
            jTextFieldConfigFiles.setText(configFiles);
            jTextFieldSrc.setText(classpath);
//            jTextFieldContextPath.setText(wm.contextPath);

            if (wm.j2eeSpecLevel.equals("1.4"))
                jComboBoxJ2eeLevel.setSelectedItem(NbBundle.getMessage(EJBLocationsPanel.class, "TXT_J2EESpecLevel_0"));
            else
                jComboBoxJ2eeLevel.setSelectedItem(NbBundle.getMessage(EJBLocationsPanel.class, "TXT_J2EESpecLevel_1"));
        }
    }

    /**
     * Convert given string value (e.g. "${project.dir}/src" to a file
     * and try to relativize it.
     */
    // XXX: copied from java/freeform:SourceFoldersPanel.getLocationDisplayName
    public static String getLocationDisplayName(PropertyEvaluator evaluator, File base, String val) {
        File f = Util.resolveFile(evaluator, base, val);
        if (f == null) {
            return val;
        }
        String location = f.getAbsolutePath();
        if (CollocationQuery.areCollocated(base, f)) {
            location = PropertyUtils.relativizeFile(base, f).replace('/', File.separatorChar); // NOI18N
        }
        return location;
    }
    
    
    public HelpCtx getHelpCtx() {
        return new HelpCtx( EJBLocationsPanel.class );
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldConfigFiles = new javax.swing.JTextField();
        jButtonEJB = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldSrc = new javax.swing.JTextField();
        jButtonSrc = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldContextPath = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxJ2eeLevel = new javax.swing.JComboBox();

        setLayout(new java.awt.GridBagLayout());

        setPreferredSize(new java.awt.Dimension(375, 135));
        jLabel1.setText(org.openide.util.NbBundle.getMessage(EJBLocationsPanel.class, "LBL_ConfigFilesPanel_Description"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 11, 0);
        add(jLabel1, gridBagConstraints);

        jLabel2.setLabelFor(jTextFieldConfigFiles);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(EJBLocationsPanel.class, "LBL_ConfigFilesPanel_ConfigFilesLocation_Label"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 11);
        add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 11);
        add(jTextFieldConfigFiles, gridBagConstraints);
        jTextFieldConfigFiles.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(EJBLocationsPanel.class, "ACS_LBL_ConfigFilesPanel_ConfigFilesLocation_A11YDesc"));

        org.openide.awt.Mnemonics.setLocalizedText(jButtonEJB, org.openide.util.NbBundle.getMessage(EJBLocationsPanel.class, "BTN_BasicProjectInfoPanel_browseAntScript"));
        jButtonEJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEJBActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        add(jButtonEJB, gridBagConstraints);
        jButtonEJB.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(EJBLocationsPanel.class, "ACS_LBL_ConfigFilesPanel_ConfigFilesLocationBrowse_A11YDesc"));

        jLabel3.setLabelFor(jTextFieldSrc);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(EJBLocationsPanel.class, "LBL_ConfigFilesPanel_JavaSourcesLocation_Label"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 11, 11);
        add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 11, 11);
        add(jTextFieldSrc, gridBagConstraints);
        jTextFieldSrc.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(EJBLocationsPanel.class, "ACS_LBL_ConfigFilesPanel_JavaSourcesLocation_A11YDesc"));

        org.openide.awt.Mnemonics.setLocalizedText(jButtonSrc, org.openide.util.NbBundle.getMessage(EJBLocationsPanel.class, "BTN_BasicProjectInfoPanel_browseProjectFolder"));
        jButtonSrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSrcActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 11, 0);
        add(jButtonSrc, gridBagConstraints);
        jButtonSrc.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(EJBLocationsPanel.class, "ACS_LBL_ConfigFilesPanel_JavaSourcesLocationBrowse_A11YDesc"));

        jLabel4.setLabelFor(jTextFieldContextPath);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 11);
        add(jLabel4, gridBagConstraints);

        jTextFieldContextPath.setEditable(false);
        jTextFieldContextPath.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        add(jTextFieldContextPath, gridBagConstraints);
        jTextFieldContextPath.getAccessibleContext().setAccessibleDescription("");

        jLabel5.setLabelFor(jComboBoxJ2eeLevel);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(EJBLocationsPanel.class, "LBL_ConfigFilesPanel_J2EESpecLevel_Label"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 11);
        add(jLabel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        add(jComboBoxJ2eeLevel, gridBagConstraints);
        jComboBoxJ2eeLevel.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(EJBLocationsPanel.class, "ACS_LBL_ConfigFilesPanel_J2EESpecLevel_A11YDesc"));

    }//GEN-END:initComponents

    private void jButtonSrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSrcActionPerformed
        JFileChooser chooser = createChooser(getSrcPackagesLocation().getAbsolutePath());
        if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(this)) {
            setSrcPackages(chooser.getSelectedFile());
        }
    }//GEN-LAST:event_jButtonSrcActionPerformed

    private void jButtonEJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEJBActionPerformed
        JFileChooser chooser = createChooser(getConfigFilesLocation().getAbsolutePath());
        if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(this)) {
            setConfigFiles(chooser.getSelectedFile());
        }
    }//GEN-LAST:event_jButtonEJBActionPerformed
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEJB;
    private javax.swing.JButton jButtonSrc;
    private javax.swing.JComboBox jComboBoxJ2eeLevel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextFieldConfigFiles;
    private javax.swing.JTextField jTextFieldContextPath;
    private javax.swing.JTextField jTextFieldSrc;
    // End of variables declaration//GEN-END:variables
    
    private static JFileChooser createChooser(String path) {
        JFileChooser chooser = new JFileChooser();
        FileUtil.preventFileChooserSymlinkTraversal(chooser, new File(path));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        
        return chooser;
    }

    protected List getEJBModules() {
        ArrayList l = new ArrayList();

        EJBProjectGenerator.EJBModule wm = new EJBProjectGenerator.EJBModule ();
        wm.configFiles = getRelativeLocation(getConfigFilesLocation());
//        wm.contextPath = jTextFieldContextPath.getText().trim();
        
        String j2eeLevel = (String) jComboBoxJ2eeLevel.getSelectedItem();
        if (j2eeLevel.equals(NbBundle.getMessage(EJBLocationsPanel.class, "TXT_J2EESpecLevel_0")))
            wm.j2eeSpecLevel = "1.4";
        else
            wm.j2eeSpecLevel = "1.3";
        
        wm.classpath = getRelativeLocation(getSrcPackagesLocation());
        l.add (wm);
        
        return l;
    }

    protected List getJavaSrcFolder() {
        ArrayList l = new ArrayList();
        File sourceLoc = getSrcPackagesLocation();
        l.add(getRelativeLocation(sourceLoc));
        l.add(sourceLoc.getName());
        return l;
    }

    /**
     * @return list of pairs [relative path, display name]
     */
    protected List getEJBSrcFolder() {
        ArrayList l = new ArrayList();
        final File webLocation = getConfigFilesLocation();
        l.add(getRelativeLocation(webLocation));
        l.add(webLocation.getName());
        return l;
    }

    private File getAsFile(String filename) {
        final String s = filename.trim();
        final File f = new File(s);
        return f.isAbsolute() ? f : new File(baseFolder, s).getAbsoluteFile();
    }

    /** Called from WizardDescriptor.Panel and ProjectCustomizer.Panel
     * to set base folder. Panel will use this for default position of JFileChooser.
     * @param baseFolder original project base folder
     * @param nbProjectFolder Freeform Project base folder
     */
    public void setFolders(File baseFolder, File nbProjectFolder) {
        this.baseFolder = baseFolder;
        this.nbProjectFolder = nbProjectFolder;
    }
    
    protected void setConfigFiles(String path) {
        setConfigFiles(getAsFile(path));
    }

    protected void setSrcPackages(String path) {
        setSrcPackages(getAsFile(path));
    }

    private void setConfigFiles(final File file) {
        jTextFieldConfigFiles.setText(relativizeFile(file));
    }

    protected File getConfigFilesLocation() {
        return getAsFile(jTextFieldConfigFiles.getText()).getAbsoluteFile();

    }

    private void setSrcPackages(final File file) {
        jTextFieldSrc.setText(relativizeFile(file));
    }

    protected File getSrcPackagesLocation() {
        return getAsFile(jTextFieldSrc.getText());

    }

    private String relativizeFile(final File file) {
        String filePath = FileUtil.normalizeFile(file).getAbsolutePath();
        String parentPath = FileUtil.normalizeFile(baseFolder).getAbsolutePath() + File.pathSeparator;
        return PropertyUtils.relativizeFile(baseFolder, FileUtil.normalizeFile(file));
    }

    private String getRelativeLocation(final File location) {
        final File normalizedLocation = FileUtil.normalizeFile(location);
        return Util.relativizeLocation(baseFolder, nbProjectFolder, normalizedLocation);
    }

    public static class Panel implements ProjectPropertiesPanel {
        
        private Project project;
        private AntProjectHelper projectHelper;
        private PropertyEvaluator projectEvaluator;
        private AuxiliaryConfiguration aux;
        private EJBLocationsPanel panel;
        
        public Panel(Project project, AntProjectHelper projectHelper, PropertyEvaluator projectEvaluator, AuxiliaryConfiguration aux) {
            this.project = project;
            this.projectHelper = projectHelper;
            this.projectEvaluator = projectEvaluator;
            this.aux = aux;
        }
    
        public void storeValues() {
            if (panel == null) {
                return;
            }
            AuxiliaryConfiguration aux = Util.getAuxiliaryConfiguration(projectHelper);
            EJBProjectGenerator.putEJBModules(projectHelper, aux, panel.getEJBModules());
        }

        public String getDisplayName() {
            return NbBundle.getMessage(EJBLocationsPanel.class, "LBL_ProjectCustomizer_Category_EJB");
        }

        public JComponent getComponent() {
            if (panel == null) {
                panel = new EJBLocationsPanel(project, projectHelper, projectEvaluator, aux);
            }
            return panel;
        }

        public int getPreferredPosition() {
            return 250; // before Java sources panel
        }
    }
    
}
