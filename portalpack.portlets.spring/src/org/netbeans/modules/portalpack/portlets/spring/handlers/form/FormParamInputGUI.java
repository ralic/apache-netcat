/*
 * FormParamInputGUI.java
 *
 * Created on September 11, 2008, 9:17 PM
 */
package org.netbeans.modules.portalpack.portlets.spring.handlers.form;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.util.NbBundle;

/**
 *
 * @author  satyaranjan
 */
public class FormParamInputGUI extends javax.swing.JDialog implements DocumentListener {

    private DataType[] types = TypesHelper.getDefaultDataTypes();
    private String[] componentTypes = TypesHelper.getDefaultComponentTypes();
    private JTable table;
    private InputParamTableModel tm;
    private Vector valueData;
    private int selectedRow;

    /** Creates new form FormParamInputGUI */
    public FormParamInputGUI(java.awt.Frame parent, JTable table, InputParamTableModel tm) {
        super(parent, true);
        valueData = new Vector();
        initComponents();
        //initData();
        this.table = table;
        this.tm = tm;
        setLocation(parent.getX() + (parent.getWidth() - getWidth()) / 2, parent.getY() + (parent.getHeight() - getHeight()) / 2);
        setTitle(NbBundle.getMessage(FormParamInputGUI.class, "FORM_DIALOG_TITLE"));
        changeButton.setEnabled(false);
        setVisible(true);
    }

    public FormParamInputGUI(java.awt.Frame parent, JTable table, InputParamTableModel tm, InputParam ip, int row) {
        super(parent, true);
        valueData = new Vector();
        initComponents();
        initData(ip);
        this.table = table;
        this.tm = tm;
        this.selectedRow = row;
        setLocation(parent.getX() + (parent.getWidth() - getWidth()) / 2, parent.getY() + (parent.getHeight() - getHeight()) / 2);
        setTitle(NbBundle.getMessage(FormParamInputGUI.class, "FORM_DIALOG_TITLE"));
        addButton.setEnabled(false);
        setVisible(true);
    }

    private void addValuesToList() {
// TODO add your handling code here:
        String v = valueTf.getText();
        if (v == null || v.trim().length() == 0) {
            return;
        }

        model.addElement(v);
        valueTf.setText("");
    }

    private void initData(InputParam ip) {

        nameTf.setText(ip.getName());
        labelTf.setText(ip.getLabel());
        dataTypeCombo.setSelectedItem(ip.getDataType());
        compTypeCombo.setSelectedItem(ip.getComponentType());

        String[] values = ip.getValues();

        for (String value : values) {
            model.addElement(value);
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        nameTf = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        labelTf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        compTypeCombo = new javax.swing.JComboBox(componentTypes);
        jLabel4 = new javax.swing.JLabel();
        dataTypeCombo = new javax.swing.JComboBox(types);
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        valueList = new javax.swing.JList();
        valueTf = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        changeButton = new javax.swing.JButton();
        addValueButton = new javax.swing.JButton();
        removeValueButton = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();

        jButton4.setText(org.openide.util.NbBundle.getMessage(FormParamInputGUI.class, "FormParamInputGUI.jButton4.text")); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText(org.openide.util.NbBundle.getMessage(FormParamInputGUI.class, "FormParamInputGUI.jLabel1.text")); // NOI18N

        nameTf.setText(org.openide.util.NbBundle.getMessage(FormParamInputGUI.class, "FormParamInputGUI.nameTf.text")); // NOI18N
        nameTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTfActionPerformed(evt);
            }
        });
        nameTf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nameTfKeyTyped(evt);
            }
        });

        jLabel2.setText(org.openide.util.NbBundle.getMessage(FormParamInputGUI.class, "FormParamInputGUI.jLabel2.text")); // NOI18N

        labelTf.setText(org.openide.util.NbBundle.getMessage(FormParamInputGUI.class, "FormParamInputGUI.labelTf.text")); // NOI18N
        labelTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labelTfActionPerformed(evt);
            }
        });

        jLabel3.setText(org.openide.util.NbBundle.getMessage(FormParamInputGUI.class, "FormParamInputGUI.jLabel3.text")); // NOI18N

        compTypeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compTypeComboActionPerformed(evt);
            }
        });

        jLabel4.setText(org.openide.util.NbBundle.getMessage(FormParamInputGUI.class, "FormParamInputGUI.jLabel4.text")); // NOI18N

        dataTypeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTypeComboActionPerformed(evt);
            }
        });

        jLabel5.setText(org.openide.util.NbBundle.getMessage(FormParamInputGUI.class, "FormParamInputGUI.jLabel5.text")); // NOI18N

        model = new DefaultListModel();
        valueList.setModel(model);
        valueList.setEnabled(false);
        jScrollPane1.setViewportView(valueList);

        valueTf.setText(org.openide.util.NbBundle.getMessage(FormParamInputGUI.class, "FormParamInputGUI.valueTf.text")); // NOI18N
        valueTf.setEnabled(false);
        valueTf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                valueTfKeyPressed(evt);
            }
        });

        addButton.setText(org.openide.util.NbBundle.getMessage(FormParamInputGUI.class, "FormParamInputGUI.addButton.text")); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        closeButton.setText(org.openide.util.NbBundle.getMessage(FormParamInputGUI.class, "FormParamInputGUI.closeButton.text")); // NOI18N
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        changeButton.setText(org.openide.util.NbBundle.getMessage(FormParamInputGUI.class, "FormParamInputGUI.changeButton.text")); // NOI18N
        changeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeButtonActionPerformed(evt);
            }
        });

        addValueButton.setText(org.openide.util.NbBundle.getMessage(FormParamInputGUI.class, "FormParamInputGUI.addValueButton.text")); // NOI18N
        addValueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addValueButtonActionPerformed(evt);
            }
        });

        removeValueButton.setText(org.openide.util.NbBundle.getMessage(FormParamInputGUI.class, "FormParamInputGUI.removeValueButton.text")); // NOI18N
        removeValueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeValueButtonActionPerformed(evt);
            }
        });

        errorLabel.setText(org.openide.util.NbBundle.getMessage(FormParamInputGUI.class, "FormParamInputGUI.errorLabel.text")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel4)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel2)
                                    .add(jLabel3)
                                    .add(jLabel1))
                                .add(18, 18, 18)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(dataTypeCombo, 0, 323, Short.MAX_VALUE)
                                            .add(valueTf, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)))
                                    .add(compTypeCombo, 0, 323, Short.MAX_VALUE)
                                    .add(labelTf, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                                    .add(nameTf, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                            .add(layout.createSequentialGroup()
                                .add(jLabel5)
                                .add(345, 345, 345)))
                        .add(6, 6, 6)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(addValueButton)
                            .add(removeValueButton)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(errorLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 254, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(addButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(changeButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(closeButton)))
                .addContainerGap())
        );

        layout.linkSize(new java.awt.Component[] {addButton, changeButton, closeButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.linkSize(new java.awt.Component[] {addValueButton, removeValueButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(nameTf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel2)
                    .add(labelTf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(compTypeCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(dataTypeCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(addValueButton)
                    .add(valueTf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(9, 9, 9)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(removeValueButton)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 116, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(addButton)
                    .add(changeButton)
                    .add(closeButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(errorLabel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void nameTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTfActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_nameTfActionPerformed

private void labelTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labelTfActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_labelTfActionPerformed

private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
// TODO add your handling code here:
    this.dispose();
}//GEN-LAST:event_closeButtonActionPerformed

private void addValueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addValueButtonActionPerformed
    addValuesToList();
}//GEN-LAST:event_addValueButtonActionPerformed

private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
// TODO add your handling code here:

    String name = nameTf.getText();
    String label = labelTf.getText();
    String componentType = (String) compTypeCombo.getSelectedItem();
    DataType dataType = (DataType) dataTypeCombo.getSelectedItem();

    ListModel model = valueList.getModel();
    String[] values = new String[model.getSize()];

    for (int i = 0; i < values.length; i++) {
        values[i] = (String) model.getElementAt(i);
    }

    InputParam ip = new InputParam(name, label, values, dataType, componentType);
    tm.addRow(ip);
    dispose();
}//GEN-LAST:event_addButtonActionPerformed

private void removeValueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeValueButtonActionPerformed
// TODO add your handling code here:
    int index = valueList.getSelectedIndex();
    if (index == -1) {
        return;
    }
    if (index > -1) {
        model.removeElementAt(index);
    }
}//GEN-LAST:event_removeValueButtonActionPerformed

private void valueTfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valueTfKeyPressed
// TODO add your handling code here:

    char c = evt.getKeyChar();
    if (c == '\n') {
        addValuesToList();
    }
}//GEN-LAST:event_valueTfKeyPressed

private void changeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeButtonActionPerformed
// TODO add your handling code here:
    String name = nameTf.getText();
    String label = labelTf.getText();
    String componentType = (String) compTypeCombo.getSelectedItem();
    DataType dataType = (DataType) dataTypeCombo.getSelectedItem();

    ListModel model = valueList.getModel();
    String[] values = new String[model.getSize()];

    for (int i = 0; i < values.length; i++) {
        values[i] = (String) model.getElementAt(i);
    }


    InputParam ip = tm.getRowValue(selectedRow);
    ip.setName(name);
    ip.setLabel(label);
    ip.setComponentType(componentType);
    ip.setDataType(dataType);
    ip.setValues(values);

    tm.fireRowChange();
    dispose();
}//GEN-LAST:event_changeButtonActionPerformed

private void dataTypeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataTypeComboActionPerformed
// TODO add your handling code here:

    DataType dataType = (DataType) dataTypeCombo.getSelectedItem();
    if (dataType.equals(TypesHelper.FILE_TYPE)) {

        compTypeCombo.setSelectedItem(TypesHelper.TEXT_COMP);
        compTypeCombo.setEnabled(false);
    } else {
        compTypeCombo.setEnabled(true);
    }
}//GEN-LAST:event_dataTypeComboActionPerformed

private void nameTfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameTfKeyTyped
// TODO add your handling code here:
    char c = evt.getKeyChar();

    if (((c == KeyEvent.VK_ENTER) || (c == KeyEvent.VK_TAB) || (c == KeyEvent.VK_SPACE))) {
        evt.consume();
    }

}//GEN-LAST:event_nameTfKeyTyped

private void compTypeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compTypeComboActionPerformed
// TODO add your handling code here:
    
    String selectedItem = (String)compTypeCombo.getSelectedItem();
    if(selectedItem.equals(TypesHelper.CHECKBOX_COMP)
            || selectedItem.equals(TypesHelper.RADIO_COMP)
            || selectedItem.equals(TypesHelper.SELECT_COMP)) {
        
        valueTf.setEnabled(true);
        valueList.setEnabled(true);
        
    } else {
        
        valueTf.setText("");
        model.clear();
        valueTf.setEnabled(false);
        valueList.setEnabled(false);
    }            
            
}//GEN-LAST:event_compTypeComboActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FormParamInputGUI dialog = new FormParamInputGUI(new javax.swing.JFrame(), null, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton addValueButton;
    private javax.swing.JButton changeButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JComboBox compTypeCombo;
    private javax.swing.JComboBox dataTypeCombo;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField labelTf;
    private javax.swing.JTextField nameTf;
    private javax.swing.JButton removeValueButton;
    private javax.swing.JList valueList;
    private javax.swing.JTextField valueTf;
    // End of variables declaration//GEN-END:variables
    private DefaultListModel model;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Form GUI disposed........................");
    }

    private void setErroMessage(String msg) {
        errorLabel.setForeground(Color.RED);
        errorLabel.setText(msg);
    }

    public void insertUpdate(DocumentEvent e) {
        updateText(e);
    }

    public void removeUpdate(DocumentEvent e) {
        updateText(e);
    }

    public void changedUpdate(DocumentEvent e) {
        updateText(e);
    }

    private void updateText(DocumentEvent e) {
    }

    public void validate(String validate) {
    }
}
