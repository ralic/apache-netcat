/*
 * AddChapterPanel.java
 *
 * Created on October 14, 2006, 8:42 PM
 */

package org.netbeans.modules.docbook;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.DialogDescriptor;

/**
 *
 * @author  Tim Boudreau
 */
public class AddChapterPanel extends javax.swing.JPanel implements DocumentListener, FocusListener {

    /** Creates new form AddChapterPanel */
    public AddChapterPanel() {
        initComponents();
        jTextField1.getDocument().addDocumentListener (this);
        jTextField1.addFocusListener(this);
        jTextField2.addFocusListener(this);
    }

    private DialogDescriptor dlg;
    void setDlg (DialogDescriptor dlg) {
        dlg.setValid (false);
        this.dlg = dlg;
    }

    private void change() {
        String s = jTextField1.getText();
        s = s.toLowerCase();
        s = s.replace (' ', '_'); //NOI18N
        s = s.replace('\\', '_'); //NOI18N
        s = s.replace ('/', '_'); //NOI18N
        jTextField2.setText (s);
        if (dlg != null) {
            dlg.setValid (jTextField1.getText().trim().length() > 0);
        }
    }

    public String getEntityName() {
        char[] c = jTextField1.getText().toCharArray();
        StringBuilder b = new StringBuilder(c.length);
        for (int i = 0; i < c.length; i++) {
            if (Character.isLetter(c[i])) {
                b.append (c[i]);
            }
        }
        return b.length() == 0 ? jTextField2.getText() : b.toString();
    }

    public void requestFocus() {
        jTextField1.requestFocus();
    }

    public String getTitle() {
        return jTextField1.getText();
    }

    public String getName() {
        return jTextField2.getText();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();

        jLabel1.setText(org.openide.util.NbBundle.getMessage(AddChapterPanel.class, "AddChapterPanel.jLabel1.text")); // NOI18N

        jTextField1.setText(org.openide.util.NbBundle.getMessage(AddChapterPanel.class, "AddChapterPanel.jTextField1.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(AddChapterPanel.class, "AddChapterPanel.jLabel2.text")); // NOI18N

        jTextField2.setText(org.openide.util.NbBundle.getMessage(AddChapterPanel.class, "AddChapterPanel.jTextField2.text")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void insertUpdate(DocumentEvent e) {
        change();
    }

    public void removeUpdate(DocumentEvent e) {
        change();
    }

    public void changedUpdate(DocumentEvent e) {
        change();
    }

    public void focusGained(FocusEvent e) {
        JTextField f = (JTextField) e.getComponent();
        f.selectAll();
    }

    public void focusLost(FocusEvent e) {
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

}
