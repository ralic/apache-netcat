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
package org.netbeans.modules.codetemplatetools.ui.view;

import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.AbstractListModel;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.Document;
import org.netbeans.lib.editor.codetemplates.api.CodeTemplate;
import org.netbeans.lib.editor.codetemplates.api.CodeTemplateManager;
import org.netbeans.modules.editor.options.BaseOptions;
import org.openide.ErrorManager;
import org.openide.windows.WindowManager;
import org.openide.xml.XMLUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Sandip V. Chitale (Sandip.Chitale@Sun.Com)
 */
public class CodeTemplatesPanel extends javax.swing.JPanel {

    public static void promptAndInsertCodeTemplate(JEditorPane editorPane) {
        JDialog dialog = new JDialog(WindowManager.getDefault().getMainWindow(),
        "Templates",
        true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setContentPane(new CodeTemplatesPanel(editorPane));
        dialog.setBounds(200,200, 600, 450);
        dialog.setVisible(true);

    }

    private JEditorPane editorPane;

    /** Creates new form CodeTemplatesPanel */
    public CodeTemplatesPanel(JEditorPane editorPane) {
        initComponents();
        this.editorPane = editorPane;
        loadModel();
        mimeTypeLabel.setText(editorPane.getContentType());
        templatesList.setCellRenderer(new CodeTemplateListCellRenderer());

        templateTextEditorPane.setContentType(editorPane.getContentType());

        newButton.setIcon(Icons.NEW_TEMPLATE_ICON);

        adjustButtonState();
    }

    public void addNotify() {
        super.addNotify();

        SwingUtilities.getRootPane(this).setDefaultButton(insertButon);
    }

    private void loadModel() {
        Document doc = editorPane.getDocument();
        CodeTemplateManager codeTemplateManager = CodeTemplateManager.get(doc);
        Collection codeTemplatesCollection = codeTemplateManager.getCodeTemplates();
        CodeTemplate[] codeTemplates = (CodeTemplate[]) codeTemplatesCollection.toArray(new CodeTemplate[0]);
        CodeTemplateListModel codeTemplateListModel = new CodeTemplateListModel(codeTemplates);
        templatesList.setModel(codeTemplateListModel);
    }

    private void insertTemplate() {
        CodeTemplate selectedCodeTemplate = (CodeTemplate) templatesList.getSelectedValue();
        if (selectedCodeTemplate != null) {
            selectedCodeTemplate.insert(editorPane);
        }
        done();
    }

    private void deleteTemplate(CodeTemplate template) {
        if (template == null) {
            return;
        }
        String templateName = template.getAbbreviation();
        if (templateName == null || templateName.length() == 0) {
            return;
        }
        if  (JOptionPane.showConfirmDialog(WindowManager.getDefault().getMainWindow(),
                    "Delete Code Template : " + templateName + " ?",
                    "Delete Code Template",
                    JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) {
            return;
        }

        Class kitClass = editorPane.getEditorKit().getClass();
        BaseOptions baseOptions = (BaseOptions) BaseOptions.getOptions(kitClass);
        Map abbreviationsMap = baseOptions.getAbbrevMap();
        if (abbreviationsMap == null) {
            // ?
            return;
        }
        abbreviationsMap.remove(templateName);
        baseOptions.setAbbrevMap(abbreviationsMap);
        loadModel();
    }

    private void close() {
        done();
    }

    private void done() {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.setVisible(false);
            window.dispose();
        }
    }

    private void adjustButtonState() {
        insertButon.setEnabled(editorPane.isEditable() && templatesList.getSelectedIndex() != -1);
        deleteButton.setEnabled(templatesList.getSelectedIndex() != -1);
        modifyButton.setEnabled(templatesList.getSelectedIndex() != -1);
    }

    private void showCodeTemplate(CodeTemplate codeTemplate) {
        if (codeTemplate == null) {
            templateTextEditorPane.setText("");
        } else {
            templateTextEditorPane.setText(codeTemplate.getParametrizedText());
        }
    }

    /** Elements */
    private static final String TAG_ROOT = "abbrevs"; //NOI18N
    private static final String TAG_ABBREV = "abbrev"; //NOI18N
    
    /** Attributes */
    private static final String ATTR_KEY = "key"; //NOI18N
    private static final String ATTR_ACTION = "action"; //NOI18N
    private static final String ATTR_REMOVE = "remove"; //NOI18N
    private static final String ATTR_XML_SPACE = "xml:space"; //NOI18N    
    private static final String VALUE_XML_SPACE = "preserve"; //NOI18N
    
    private void importTemplates() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.addChoosableFileFilter(new FileFilter() {
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                if (f.getName().toLowerCase().endsWith(".xml")) {
                    return true;
                }
                return false;
            }
            public String getDescription() {
                return "Abbreviations file";
            }
        });
        if (jFileChooser.showOpenDialog(WindowManager.getDefault().getMainWindow()) == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            try {
                InputSource inputSource = new InputSource(new FileReader(file));
                org.w3c.dom.Document doc = XMLUtil.parse(inputSource, false, false, null, null);
                Element rootElement = doc.getDocumentElement();

                if (!TAG_ROOT.equals(rootElement.getTagName())) {
                    // Wrong root element
                    return;
                }
                Map properties = new HashMap();
                Map mapa = new HashMap();

                NodeList abbr = rootElement.getElementsByTagName(TAG_ABBREV);
                int len = abbr.getLength();
                for (int i=0; i < len; i++){
                    Node node = abbr.item(i);
                    Element FCElement = (Element)node;

                    if (FCElement == null){
                        continue;
                    }

                    String key       = FCElement.getAttribute(ATTR_KEY);
                    String delete    = FCElement.getAttribute(ATTR_REMOVE);
                    String expanded  = "";

                    if (! Boolean.valueOf(delete).booleanValue()){
                        NodeList textList = FCElement.getChildNodes();
                        if (textList.getLength() > 0) {
                            Node subNode = textList.item(0);
                            if (subNode instanceof Text) {
                                Text textNode = (Text) subNode;
                                expanded = textNode.getData();
                            }
                        }
                    }

                    properties.put(key, expanded);
                }

                if (properties.size()>0){
                    // create updated map
                    mapa.putAll(properties);

                    // remove all deleted values
                    for( Iterator i = properties.keySet().iterator(); i.hasNext(); ) {
                        String key = (String)i.next();
                        if(((String)properties.get(key)).length() == 0){
                            mapa.remove(key);
                        }
                    }
                }
                
                // remove all deleted values
                for( Iterator i = mapa.keySet().iterator(); i.hasNext(); ) {
                    String key = (String)i.next();
                    String value = (String) mapa.get(key);
                    CreateCodeTemplatePanel.saveTemplate(editorPane, key, value, true);
                }
                loadModel();
            } catch (FileNotFoundException ex) {
                ErrorManager.getDefault().notify(ex);
            } catch (IOException ex) {
                ErrorManager.getDefault().notify(ex);
            } catch (SAXException ex) {
                ErrorManager.getDefault().notify(ex);
            } 
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        templatesLabel = new javax.swing.JLabel();
        mimeTypeLabelLabel = new javax.swing.JLabel();
        mimeTypeLabel = new javax.swing.JLabel();
        templatesScrollPane = new javax.swing.JScrollPane();
        templatesList = new javax.swing.JList();
        newButton = new javax.swing.JButton();
        modifyButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        importButton = new javax.swing.JButton();
        templateTextLabel = new javax.swing.JLabel();
        templateTextScrollPane = new javax.swing.JScrollPane();
        templateTextEditorPane = new javax.swing.JEditorPane();
        buttonsPanel = new javax.swing.JPanel();
        insertButon = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        templatesLabel.setDisplayedMnemonic('s');
        templatesLabel.setText("Templates");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(templatesLabel, gridBagConstraints);

        mimeTypeLabelLabel.setDisplayedMnemonic('y');
        mimeTypeLabelLabel.setText("Mime Type:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(mimeTypeLabelLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(mimeTypeLabel, gridBagConstraints);

        templatesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        templatesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                templatesListValueChanged(evt);
            }
        });
        templatesList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                templatesListKeyTyped(evt);
            }
        });

        templatesScrollPane.setViewportView(templatesList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        add(templatesScrollPane, gridBagConstraints);

        newButton.setMnemonic('N');
        newButton.setText("New...");
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(newButton, gridBagConstraints);

        modifyButton.setMnemonic('M');
        modifyButton.setText("Modify...");
        modifyButton.setToolTipText("Modify selected template");
        modifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(modifyButton, gridBagConstraints);

        deleteButton.setMnemonic('D');
        deleteButton.setText("Delete...");
        deleteButton.setToolTipText("Delete selected template");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(deleteButton, gridBagConstraints);

        importButton.setMnemonic('p');
        importButton.setText("Import...");
        importButton.setToolTipText("IMport Code Templates");
        importButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(importButton, gridBagConstraints);

        templateTextLabel.setDisplayedMnemonic('T');
        templateTextLabel.setText("Template Text");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(templateTextLabel, gridBagConstraints);

        templateTextEditorPane.setEditable(false);
        templateTextScrollPane.setViewportView(templateTextEditorPane);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(templateTextScrollPane, gridBagConstraints);

        buttonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        insertButon.setMnemonic('I');
        insertButon.setText("Insert");
        insertButon.setToolTipText("Insert selected template");
        insertButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButonActionPerformed(evt);
            }
        });

        buttonsPanel.add(insertButon);

        closeButton.setMnemonic('C');
        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        buttonsPanel.add(closeButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonsPanel, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    private void templatesListKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_templatesListKeyTyped
        ListModel listModel = templatesList.getModel();
        if (listModel instanceof CodeTemplateListModel) {
            CodeTemplateListModel codeTemplateListModel = (CodeTemplateListModel) listModel;
            if (evt.getModifiers() == 0 || evt.getModifiers() == InputEvent.SHIFT_MASK) {
                char typedChar = Character.toLowerCase(evt.getKeyChar());
                int selectedIndex = templatesList.getSelectedIndex();
                int size = codeTemplateListModel.getSize();
                int select = -1;
                for (int i = ++selectedIndex; i < size; i++) {
                    CodeTemplate codeTemplate = (CodeTemplate) codeTemplateListModel.getElementAt(i);
                    char firstChar = codeTemplate.getAbbreviation().toLowerCase().charAt(0);
                    if (firstChar < typedChar) {
                        continue;
                    } else if (codeTemplate.getAbbreviation().toLowerCase().charAt(0) == typedChar) {
                        select = i;
                        break;
                    } else {
                        break;
                    }
                }
                if (select == -1) {
                    for (int i = 0; i < selectedIndex; i++) {
                        CodeTemplate codeTemplate = (CodeTemplate) codeTemplateListModel.getElementAt(i);
                        char firstChar = codeTemplate.getAbbreviation().toLowerCase().charAt(0);
                        if (firstChar < typedChar) {
                            continue;
                        } else if (codeTemplate.getAbbreviation().toLowerCase().charAt(0) == typedChar) {
                            select = i;
                            break;
                        } else {
                            break;
                        }
                    }
                }
                if (select != -1) {
                    templatesList.setSelectedIndex(select);
                    templatesList.scrollRectToVisible(templatesList.getCellBounds(select, select));
                    return;
                }
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }//GEN-LAST:event_templatesListKeyTyped

    private void templatesListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_templatesListValueChanged
        CodeTemplate selectedCodeTemplate = (CodeTemplate) templatesList.getSelectedValue();
        showCodeTemplate(selectedCodeTemplate);
        adjustButtonState();
    }//GEN-LAST:event_templatesListValueChanged

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        close();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void insertButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButonActionPerformed
        insertTemplate();
    }//GEN-LAST:event_insertButonActionPerformed

    private void importButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importButtonActionPerformed
        importTemplates();
    }//GEN-LAST:event_importButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        deleteTemplate((CodeTemplate) templatesList.getSelectedValue());
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyButtonActionPerformed
        CreateCodeTemplatePanel.modifyCodeTemplate(CodeTemplatesPanel.this.editorPane, (CodeTemplate) templatesList.getSelectedValue());
        // Templates may have been modified.
        loadModel();
    }//GEN-LAST:event_modifyButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        CreateCodeTemplatePanel.createCodeTemplate(CodeTemplatesPanel.this.editorPane);
        // New templates may have been added.
        loadModel();
    }//GEN-LAST:event_newButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton importButton;
    private javax.swing.JButton insertButon;
    private javax.swing.JLabel mimeTypeLabel;
    private javax.swing.JLabel mimeTypeLabelLabel;
    private javax.swing.JButton modifyButton;
    private javax.swing.JButton newButton;
    private javax.swing.JEditorPane templateTextEditorPane;
    private javax.swing.JLabel templateTextLabel;
    private javax.swing.JScrollPane templateTextScrollPane;
    private javax.swing.JLabel templatesLabel;
    private javax.swing.JList templatesList;
    private javax.swing.JScrollPane templatesScrollPane;
    // End of variables declaration//GEN-END:variables

    private static class CodeTemplateListModel extends AbstractListModel {
        private CodeTemplate[] codeTemplates;

        CodeTemplateListModel(CodeTemplate[] codeTemplates) {
            this.codeTemplates = codeTemplates;
        }

        public int getSize() {
            return codeTemplates.length;
        }

        public Object getElementAt(int index) {
            return codeTemplates[index];
        }
    }
}
