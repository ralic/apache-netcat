/*
 * Propertypanel.java
 *
 * Created on 13. květen 2008, 2:26
 */
package org.netbeans.modules.properties.rbe.ui;

import java.util.Locale;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.modules.properties.rbe.model.Bundle;
import org.netbeans.modules.properties.rbe.model.BundlePropertyValue;
import org.openide.util.NbBundle;

/**
 *
 * @author  Denis Stepanov <denis.stepanov at gmail.com>
 */
public class UIPropertyPanel extends javax.swing.JPanel {

    /** Creates new form Propertypanel */
    public UIPropertyPanel(final BundlePropertyValue value) {
        initComponents();
        if (Bundle.DEFAULT_LOCALE.equals(value.getLocale())) {
            titleLabel.setText(NbBundle.getMessage(ResourceBundleEditorComponent.class, "DefaultLocale"));
        } else {
            String title = String.format("%s (%s)%s", value.getLocale().getDisplayLanguage(),
                value.getLocale().getLanguage(), value.getLocale().getDisplayCountry().length() > 0 ? " - " + value.getLocale().getDisplayCountry() : "");
            titleLabel.setText(title);
        }
        textArea.setText(value.getValue());

        textArea.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                value.setValue(textArea.getText());
            }

            public void removeUpdate(DocumentEvent e) {
                value.setValue(textArea.getText());
            }

            public void changedUpdate(DocumentEvent e) {
                value.setValue(textArea.getText());
            }
        });

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBar = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();

        toolBar.setFloatable(false);
        toolBar.setOrientation(1);

        jPanel1.setOpaque(false);

        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText(org.openide.util.NbBundle.getMessage(UIPropertyPanel.class, "UIPropertyPanel.titleLabel.text")); // NOI18N
        titleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        toolBar.add(jPanel1);

        textArea.setColumns(20);
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(textArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textArea;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
}
