/*
 * ConfigurationPanel.java
 *
 * Created on December 12, 2007, 1:22 PM
 */

package org.netbeans.modules.websvc.axis2.actions;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author  mkuchtiak
 */
public class ConfigurationPanel extends javax.swing.JPanel {
    
    private String previousDirectory;
    
    /** Creates new form ConfigurationPanel */
    public ConfigurationPanel(String axisHome) {
        previousDirectory = axisHome;
        initComponents();
        axisHomeTf.setText(axisHome);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        axisHomeLabel = new javax.swing.JLabel();
        axisHomeTf = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        axisHomeTf1 = new javax.swing.JTextField();
        axisHomeLabel1 = new javax.swing.JLabel();
        browseButton1 = new javax.swing.JButton();

        axisHomeLabel.setText(org.openide.util.NbBundle.getMessage(ConfigurationPanel.class, "ConfigurationPanel.axisHomeLabel.text")); // NOI18N

        axisHomeTf.setText(org.openide.util.NbBundle.getMessage(ConfigurationPanel.class, "ConfigurationPanel.axisHomeTf.text")); // NOI18N

        browseButton.setText(org.openide.util.NbBundle.getMessage(ConfigurationPanel.class, "ConfigurationPanel.browseButton.text")); // NOI18N
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        axisHomeTf1.setText(org.openide.util.NbBundle.getMessage(ConfigurationPanel.class, "ConfigurationPanel.axisHomeTf1.text")); // NOI18N

        axisHomeLabel1.setText(org.openide.util.NbBundle.getMessage(ConfigurationPanel.class, "ConfigurationPanel.axisHomeLabel1.text")); // NOI18N

        browseButton1.setText(org.openide.util.NbBundle.getMessage(ConfigurationPanel.class, "ConfigurationPanel.browseButton1.text")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(axisHomeTf, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(browseButton))
                    .add(axisHomeLabel)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(axisHomeTf1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(browseButton1))
                    .add(axisHomeLabel1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(axisHomeLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(browseButton)
                    .add(axisHomeTf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(axisHomeLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(browseButton1)
                    .add(axisHomeTf1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(188, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        // TODO add your handling code here:        
        JFileChooser chooser = new JFileChooser(previousDirectory);
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File axisDir = chooser.getSelectedFile();
            axisHomeTf.setText(axisDir.getAbsolutePath());
            previousDirectory = axisDir.getPath();
        } 
    }//GEN-LAST:event_browseButtonActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel axisHomeLabel;
    private javax.swing.JLabel axisHomeLabel1;
    private javax.swing.JTextField axisHomeTf;
    private javax.swing.JTextField axisHomeTf1;
    private javax.swing.JButton browseButton;
    private javax.swing.JButton browseButton1;
    // End of variables declaration//GEN-END:variables
    
    
    public String getAxisHome() {
        return axisHomeTf.getText().trim();
    }
    
    public String getAxisApplicationDir() {
        return axisHomeTf1.getText().trim();
    }
}
