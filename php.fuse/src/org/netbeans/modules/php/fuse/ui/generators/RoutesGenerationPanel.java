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

/*
 * RoutesGenerationPanel.java
 *
 * Created on Nov 16, 2009, 11:25:04 PM
 */
package org.netbeans.modules.php.fuse.ui.generators;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import org.openide.util.NbBundle;

/**
 *
 * @author cawe
 */
public class RoutesGenerationPanel extends JPanel {

    private final String PREVIEW_BEGIN = "FuseURIRouter::route_connect( '";
    private final String PREVIEW_MIDDLE = "', array(\n\t";
    private final String PREVIEW_END = ")\n);";
    String previewText = "";
    private String urlPattern = "";
    private Map<String, String> routeOptions = new HashMap<String, String>();

    /** Creates new form RoutesGenerationPanel */
    public RoutesGenerationPanel() {
        initComponents();
        routeOptionTable.getModel().addTableModelListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (column != 0) {
                    TableModel model = (TableModel) e.getSource();
                    String value = (String) model.getValueAt(row, column);
                    String key = (String) model.getValueAt(row, column - 1);
                    routeOptions.put(key, value);
                }
            }
        });

        TableColumn col = routeOptionTable.getColumnModel().getColumn(1);
        col.setCellEditor(new MyTableCellEditor());

        routeOptionTable.setDefaultRenderer(String.class, new OptionsTableRenderer());
        setRowsInTable();

        updateDialog();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        routeOptionTable = new javax.swing.JTable();
        urlPatternLabel = new javax.swing.JLabel();
        urlPatternTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        previewTextArea = new javax.swing.JTextArea();
        previewLabel = new javax.swing.JLabel();
        errorLabel = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setAutoscrolls(true);

        routeOptionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Route option", "User input"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        routeOptionTable.setRowHeight(22);
        routeOptionTable.setRowSelectionAllowed(false);
        routeOptionTable.getTableHeader().setReorderingAllowed(false);
        routeOptionTable.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                routeOptionTableCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jScrollPane2.setViewportView(routeOptionTable);
        routeOptionTable.getColumnModel().getColumn(0).setResizable(false);
        routeOptionTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        routeOptionTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(RoutesGenerationPanel.class, "RoutesGenerationPanel.routeOptionTable.columnModel.title0_1")); // NOI18N
        routeOptionTable.getColumnModel().getColumn(1).setResizable(false);
        routeOptionTable.getColumnModel().getColumn(1).setPreferredWidth(250);
        routeOptionTable.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(RoutesGenerationPanel.class, "RoutesGenerationPanel.routeOptionTable.columnModel.title1_1")); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 162, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        urlPatternLabel.setText(org.openide.util.NbBundle.getMessage(RoutesGenerationPanel.class, "RoutesGenerationPanel.urlPatternLabel.text")); // NOI18N

        urlPatternTextField.setText(org.openide.util.NbBundle.getMessage(RoutesGenerationPanel.class, "RoutesGenerationPanel.urlPatternTextField.text")); // NOI18N
        urlPatternTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                urlPatternTextFieldKeyReleased(evt);
            }
        });

        previewTextArea.setColumns(20);
        previewTextArea.setRows(5);
        jScrollPane1.setViewportView(previewTextArea);

        previewLabel.setText(org.openide.util.NbBundle.getMessage(RoutesGenerationPanel.class, "RoutesGenerationPanel.previewLabel.text")); // NOI18N

        errorLabel.setForeground(new java.awt.Color(255, 146, 0));
        errorLabel.setText(org.openide.util.NbBundle.getMessage(RoutesGenerationPanel.class, "RoutesGenerationPanel.errorLabel.text")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(urlPatternLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(urlPatternTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
                    .add(previewLabel)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .add(errorLabel))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(urlPatternLabel)
                    .add(urlPatternTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(previewLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(errorLabel))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void urlPatternTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_urlPatternTextFieldKeyReleased
        urlPattern = urlPatternTextField.getText();
        updateDialog();
    }//GEN-LAST:event_urlPatternTextFieldKeyReleased

    private void routeOptionTableCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_routeOptionTableCaretPositionChanged
        updateDialog();
    }//GEN-LAST:event_routeOptionTableCaretPositionChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errorLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel previewLabel;
    private javax.swing.JTextArea previewTextArea;
    private javax.swing.JTable routeOptionTable;
    private javax.swing.JLabel urlPatternLabel;
    private javax.swing.JTextField urlPatternTextField;
    // End of variables declaration//GEN-END:variables

    private void setRowsInTable() {
        String[] labels = {"controller", "controller_class", "action", "method",
            "case_sensitive", "requirements", "redirect", "params", "static_cache", "headers", "layout"};
        for (int i = 0; i < labels.length; i++) {
            routeOptionTable.setValueAt(labels[i], i, 0);
        }
    }

    private void updateDialog() {
        rewritePreview();
        updateErrorMessage();
    }

    private void rewritePreview() {
        previewText = PREVIEW_BEGIN + urlPattern + PREVIEW_MIDDLE;
        Iterator it = routeOptions.entrySet().iterator();
        String comma = "";
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            if (((String)pairs.getValue()).length() != 0) {
                previewText += comma + "'" + pairs.getKey() + "' => '" + pairs.getValue() + "'";
                comma = ",\n\t";
            }
        }
        previewText += PREVIEW_END;
        previewTextArea.setText(previewText);
    }

    private void updateErrorMessage() {
        String errorMessage = " ";
        if (urlPattern.trim().length() == 0) {
            errorMessage = NbBundle.getMessage(RoutesGenerationPanel.class, "ERR_NoURLPattern");
        } else {
            String redirect = routeOptions.get("redirect");
            String action = routeOptions.get("action");
            String controller = routeOptions.get("controller");

            if ((action == null || action.length() == 0) && (controller == null || controller.length() == 0) && (redirect == null || redirect.length() == 0)) {
                errorMessage = NbBundle.getMessage(RoutesGenerationPanel.class, "ERR_NoRedirectOrActionAndController");
            } else {
                if (redirect != null && (action == null || action.length() == 0) && (controller == null || controller.length() == 0)) {
                    if (routeOptions.get("redirect").trim().length() == 0) {
                        errorMessage = NbBundle.getMessage(RoutesGenerationPanel.class, "ERR_NoRedirectOrActionAndController");
                    }
                } else if ((action != null && action.length() > 0) && (controller == null || controller.length() == 0)) {
                        errorMessage = NbBundle.getMessage(RoutesGenerationPanel.class, "ERR_NoControllerProvided");
                } else if ((controller != null && controller.length() > 0) && (action == null || action.length() == 0)) {
                        errorMessage = NbBundle.getMessage(RoutesGenerationPanel.class, "ERR_NoActionProvided");
                }
            }
        }

        errorLabel.setText(errorMessage);
    }

    private class MyTableCellEditor extends AbstractCellEditor implements TableCellEditor {

        JComponent component = new JTextField();

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int rowIndex, int vColIndex) {
            ((JTextField) component).setText((String) value);
            ((JTextField) component).addFocusListener(new java.awt.event.FocusAdapter() {

                @Override
                public void focusLost(FocusEvent evt) {
                    super.focusLost(evt);
                    updateDialog();
                }
            });
            ((JTextField) component).addKeyListener(new java.awt.event.KeyAdapter() {

                @Override
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    super.keyPressed(evt);
                    updateDialog();
                }
            });

            return component;
        }

        public Object getCellEditorValue() {
            return ((JTextField) component).getText();
        }
    }

    public String getPreviewText() {
        return previewText;
    }

}



