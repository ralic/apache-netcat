/*
 * ReturnElementPanel.java
 *
 * Created on February 5, 2008, 2:48 PM
 */

package org.netbeans.modules.hibernate.loaders.mapping.multiview;

import javax.swing.JComponent;
import org.netbeans.modules.hibernate.loaders.mapping.HibernateMappingDataObject;
import org.netbeans.modules.hibernate.mapping.model.Return;
import org.netbeans.modules.xml.multiview.ui.SectionInnerPanel;
import org.netbeans.modules.xml.multiview.ui.SectionView;

/**
 *
 * @author  dc151887
 */
public class ReturnElementPanel extends SectionInnerPanel {
    
    /** Creates new form ReturnElementPanel */
    public ReturnElementPanel(SectionView view, final HibernateMappingDataObject dObj, final Return theReturn ) {
        super(view);
        initComponents();
    }
    
    @Override
    public void setValue(JComponent source, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void linkButtonPressed(Object ddBean, String ddProperty) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public JComponent getErrorComponent(String errorId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        aliasLabel = new javax.swing.JLabel();
        classLabel = new javax.swing.JLabel();
        entityNameLabel = new javax.swing.JLabel();
        lockModeLabel = new javax.swing.JLabel();
        discriminatorLabel = new javax.swing.JLabel();
        rtnPropLabel = new javax.swing.JLabel();
        aliasTextField = new javax.swing.JTextField();
        entityNameTextField = new javax.swing.JTextField();
        classTextField = new javax.swing.JTextField();
        discriminatorTextField = new javax.swing.JTextField();
        lockModeComboBox = new javax.swing.JComboBox();
        rtnPropPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        aliasLabel.setText(org.openide.util.NbBundle.getMessage(ReturnElementPanel.class, "ReturnElementPanel.aliasLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 0);
        add(aliasLabel, gridBagConstraints);

        classLabel.setText(org.openide.util.NbBundle.getMessage(ReturnElementPanel.class, "ReturnElementPanel.classLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 0);
        add(classLabel, gridBagConstraints);

        entityNameLabel.setText(org.openide.util.NbBundle.getMessage(ReturnElementPanel.class, "ReturnElementPanel.entityNameLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 0);
        add(entityNameLabel, gridBagConstraints);

        lockModeLabel.setText(org.openide.util.NbBundle.getMessage(ReturnElementPanel.class, "ReturnElementPanel.lockModeLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 0);
        add(lockModeLabel, gridBagConstraints);

        discriminatorLabel.setText(org.openide.util.NbBundle.getMessage(ReturnElementPanel.class, "ReturnElementPanel.discriminatorLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 0);
        add(discriminatorLabel, gridBagConstraints);

        rtnPropLabel.setText(org.openide.util.NbBundle.getMessage(ReturnElementPanel.class, "ReturnElementPanel.rtnPropLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 0);
        add(rtnPropLabel, gridBagConstraints);

        aliasTextField.setText(org.openide.util.NbBundle.getMessage(ReturnElementPanel.class, "ReturnElementPanel.aliasTextField.text")); // NOI18N
        aliasTextField.setPreferredSize(new java.awt.Dimension(200, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 12);
        add(aliasTextField, gridBagConstraints);

        entityNameTextField.setText(org.openide.util.NbBundle.getMessage(ReturnElementPanel.class, "ReturnElementPanel.entityNameTextField.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 12);
        add(entityNameTextField, gridBagConstraints);

        classTextField.setText(org.openide.util.NbBundle.getMessage(ReturnElementPanel.class, "ReturnElementPanel.classTextField.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 12);
        add(classTextField, gridBagConstraints);

        discriminatorTextField.setText(org.openide.util.NbBundle.getMessage(ReturnElementPanel.class, "ReturnElementPanel.discriminatorTextField.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 12);
        add(discriminatorTextField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 12);
        add(lockModeComboBox, gridBagConstraints);

        org.jdesktop.layout.GroupLayout rtnPropPanelLayout = new org.jdesktop.layout.GroupLayout(rtnPropPanel);
        rtnPropPanel.setLayout(rtnPropPanelLayout);
        rtnPropPanelLayout.setHorizontalGroup(
            rtnPropPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        rtnPropPanelLayout.setVerticalGroup(
            rtnPropPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 12);
        add(rtnPropPanel, gridBagConstraints);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        add(jPanel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aliasLabel;
    private javax.swing.JTextField aliasTextField;
    private javax.swing.JLabel classLabel;
    private javax.swing.JTextField classTextField;
    private javax.swing.JLabel discriminatorLabel;
    private javax.swing.JTextField discriminatorTextField;
    private javax.swing.JLabel entityNameLabel;
    private javax.swing.JTextField entityNameTextField;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox lockModeComboBox;
    private javax.swing.JLabel lockModeLabel;
    private javax.swing.JLabel rtnPropLabel;
    private javax.swing.JPanel rtnPropPanel;
    // End of variables declaration//GEN-END:variables
    
}