/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
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
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
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
 */

package org.netbeans.modules.java.addproperty.ui;

import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.lib.editor.codetemplates.api.CodeTemplate;
import org.netbeans.lib.editor.codetemplates.api.CodeTemplateManager;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.Repository;
import org.openide.util.Exceptions;

/**
 * A simple GUI for Add Property action.
 *
 * @author  Sandip V. Chitale (Sandip.Chitale@Sun.Com)
 */
public class AddPropertyPanel extends javax.swing.JPanel {

    private static AddPropertyPanel INSTANCE;

    private static final String TEMPLATE_PATH = "Templates/org.netbeans.modules.java.addproperty/AddProperty.freemarker"; // NOI18N

    private static FileObject templateFileObject;

    private CodeTemplateManager codeTemplateManager;
    
    private static boolean propNameModified = false;    
    private DocumentListener propNameTextFieldDocumentListener;

    public static AddPropertyPanel getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new AddPropertyPanel();
        }
        return INSTANCE;
    }

    /** Creates new form AddPropertyPanel */
    private AddPropertyPanel() {
        initComponents();
        previewScrollPane.putClientProperty(
            "HighlightsLayerExcludes", // NOI18N
            "^org\\.netbeans\\.modules\\.editor\\.lib2\\.highlighting\\.CaretRowHighlighting$" // NOI18N
        );

        nameTextField.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                showPreview();
            }

            public void removeUpdate(DocumentEvent e) {
                showPreview();
            }

            public void changedUpdate(DocumentEvent e) {
            }
        });
        
        propNameTextFieldDocumentListener = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                propNameModified = true;                
                showPreview();
            }

            public void removeUpdate(DocumentEvent e) {
                propNameModified = true;
                showPreview();
            }

            public void changedUpdate(DocumentEvent e) {
            }
        };
        propNameTextField.getDocument().addDocumentListener(propNameTextFieldDocumentListener);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        previewEditorPane.setText("");
        codeTemplateManager = CodeTemplateManager.get(previewEditorPane.getDocument());
        propNameModified = false;
        showPreview();
    }

    private void showPreview() {
        if (!propNameModified) {
            propNameTextField.getDocument().removeDocumentListener(propNameTextFieldDocumentListener);
            propNameTextField.setText("PROP_" + nameTextField.getText().toUpperCase());
            propNameTextField.getDocument().addDocumentListener(propNameTextFieldDocumentListener);
        }
        
        final String previewTemplate = generatePreview();
        CodeTemplate codeTemplate = codeTemplateManager.createTemporary(previewTemplate);
//        previewEditorPane.setText("");
//        codeTemplate.insert(previewEditorPane);
        previewEditorPane.setText(previewTemplate);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                previewEditorPane.setCaretPosition(0);
                previewEditorPane.scrollRectToVisible(new Rectangle(0,0,1,1));
            }
        });
    }
    
    public String getAddProperty() {
        return generatePreview();
    }
    
    private static final String INDENT = "       ";
    private String generatePreview() {
        ScriptEngine scriptEngine = getScriptEngine();
        if (scriptEngine != null) {
            FileObject template = getTemplateFileObject();
            if (template != null && template.isValid()) {
                final String type = typeComboBox.getSelectedItem().toString().trim();
                final String name = nameTextField.getText().trim();
                String access = "";
                if (privateRadioButton.isSelected()) {
                    access = "private ";
                } else if (protectedRadioButton.isSelected()) {
                    access = "protected ";
                } else if (publicRadioButton.isSelected()) {
                    access =  "public ";
                }
                ScriptContext scriptContext = scriptEngine.getContext();
                StringWriter writer = new StringWriter();
                scriptContext.setWriter(writer);
                scriptContext.setAttribute(FileObject.class.getName(), template, ScriptContext.ENGINE_SCOPE);
                scriptContext.setAttribute(ScriptEngine.FILENAME, template.getNameExt(), ScriptContext.ENGINE_SCOPE);
                scriptContext.setAttribute("access", access, ScriptContext.ENGINE_SCOPE);
                scriptContext.setAttribute("type", type, ScriptContext.ENGINE_SCOPE);
                scriptContext.setAttribute("name", name, ScriptContext.ENGINE_SCOPE);
                scriptContext.setAttribute("capitalizedName", capitalize(name), ScriptContext.ENGINE_SCOPE);
                scriptContext.setAttribute("static", Boolean.valueOf(staticCheckBox.isSelected()), ScriptContext.ENGINE_SCOPE);
                scriptContext.setAttribute("final", Boolean.valueOf(finalCheckBox.isSelected()), ScriptContext.ENGINE_SCOPE);
                scriptContext.setAttribute("generateGetter", Boolean.valueOf(generateGetterAndSetterRadioButton.isSelected() || generateGetterRadioButton.isSelected()), ScriptContext.ENGINE_SCOPE);
                scriptContext.setAttribute("generateSetter", Boolean.valueOf(generateGetterAndSetterRadioButton.isSelected() || generateSetterRadioButton.isSelected()), ScriptContext.ENGINE_SCOPE);
                scriptContext.setAttribute("generateJavadoc", Boolean.valueOf(generateJavadocCheckBox.isSelected()), ScriptContext.ENGINE_SCOPE);
                scriptContext.setAttribute("bound", Boolean.valueOf(boundCheckBox.isSelected()), ScriptContext.ENGINE_SCOPE);
                scriptContext.setAttribute("PROP_NAME", propNameTextField.getText().trim(), ScriptContext.ENGINE_SCOPE);
                scriptContext.setAttribute("vetoable", Boolean.valueOf(vetoableCheckBox.isSelected()), ScriptContext.ENGINE_SCOPE);
                scriptContext.setAttribute("indexed", Boolean.valueOf(indexedCheckBox.isSelected()), ScriptContext.ENGINE_SCOPE);
                scriptContext.setAttribute("generatePropertyChangeSupport", Boolean.valueOf(generatePropertyChangeSupportCheckBox.isSelected()), ScriptContext.ENGINE_SCOPE);
                scriptContext.setAttribute("generateVetoablePropertyChangeSupport", Boolean.valueOf(generateVetoablePropertyChangeSupportCheckBox.isSelected()), ScriptContext.ENGINE_SCOPE);

                Reader templateReader = null;
                try {
                    templateReader = new InputStreamReader(template.getInputStream());
                    scriptEngine.eval(templateReader);
                    return writer.toString();
                } catch (ScriptException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (IOException ioe) {
                    Exceptions.printStackTrace(ioe);
                } finally {
                    if (writer != null) {
                        try {
                            writer.close();
                        } catch (IOException ex) {
                            Exceptions.printStackTrace(ex);
                        }
                    }
                    if (templateReader != null) {
                        try {
                            templateReader.close();
                        } catch (IOException ex) {
                            Exceptions.printStackTrace(ex);
                        }

                    }
                }
            }
        }
        
        return "/*Error*/";
    }

    private static FileObject getTemplateFileObject() {
        if (templateFileObject == null) {
            templateFileObject = Repository.getDefault().getDefaultFileSystem().getRoot().getFileObject(TEMPLATE_PATH);
        }
        return templateFileObject;
    }

    private static ScriptEngine getScriptEngine() {
        return new ScriptEngineManager().getEngineByName("freemarker"); // NOI18N
    }

    private static String capitalize(String string) {
        if (string == null) {
            return null;
        }
        if (string.length() > 0) {
            final char charAtZero = string.charAt(0);
            if (Character.isLowerCase(charAtZero)) {
                string = Character.toUpperCase(charAtZero) + string.substring(1);
            }
        }
        return string;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        accessGroup = new javax.swing.ButtonGroup();
        getterSetterGroup = new javax.swing.ButtonGroup();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        typeLabel = new javax.swing.JLabel();
        typeComboBox = new javax.swing.JComboBox();
        browseTypeButton = new javax.swing.JButton();
        privateRadioButton = new javax.swing.JRadioButton();
        packageRadioButton = new javax.swing.JRadioButton();
        protectedRadioButton = new javax.swing.JRadioButton();
        publicRadioButton = new javax.swing.JRadioButton();
        staticCheckBox = new javax.swing.JCheckBox();
        finalCheckBox = new javax.swing.JCheckBox();
        generateGetterAndSetterRadioButton = new javax.swing.JRadioButton();
        generateGetterRadioButton = new javax.swing.JRadioButton();
        generateSetterRadioButton = new javax.swing.JRadioButton();
        generateJavadocCheckBox = new javax.swing.JCheckBox();
        boundCheckBox = new javax.swing.JCheckBox();
        vetoableCheckBox = new javax.swing.JCheckBox();
        propNameTextField = new javax.swing.JTextField();
        indexedCheckBox = new javax.swing.JCheckBox();
        generatePropertyChangeSupportCheckBox = new javax.swing.JCheckBox();
        generateVetoablePropertyChangeSupportCheckBox = new javax.swing.JCheckBox();
        previewLabel = new javax.swing.JLabel();
        previewScrollPane = new javax.swing.JScrollPane();
        previewEditorPane = new javax.swing.JEditorPane();

        nameLabel.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.nameLabel.text")); // NOI18N

        nameTextField.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.nameTextField.text")); // NOI18N

        typeLabel.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.typeLabel.text")); // NOI18N

        typeComboBox.setEditable(true);
        typeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "String", "int", "boolean", "long", "double", "long", "char", "short", "float" }));
        typeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeComboBoxActionPerformed(evt);
            }
        });

        browseTypeButton.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.browseTypeButton.text")); // NOI18N
        browseTypeButton.setEnabled(false);

        accessGroup.add(privateRadioButton);
        privateRadioButton.setSelected(true);
        privateRadioButton.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.privateRadioButton.text")); // NOI18N
        privateRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                privateRadioButtonActionPerformed(evt);
            }
        });

        accessGroup.add(packageRadioButton);
        packageRadioButton.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.packageRadioButton.text")); // NOI18N
        packageRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packageRadioButtonActionPerformed(evt);
            }
        });

        accessGroup.add(protectedRadioButton);
        protectedRadioButton.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.protectedRadioButton.text")); // NOI18N
        protectedRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                protectedRadioButtonActionPerformed(evt);
            }
        });

        accessGroup.add(publicRadioButton);
        publicRadioButton.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.publicRadioButton.text")); // NOI18N
        publicRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                publicRadioButtonActionPerformed(evt);
            }
        });

        staticCheckBox.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.staticCheckBox.text")); // NOI18N
        staticCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staticCheckBoxActionPerformed(evt);
            }
        });

        finalCheckBox.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.finalCheckBox.text")); // NOI18N
        finalCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalCheckBoxActionPerformed(evt);
            }
        });

        getterSetterGroup.add(generateGetterAndSetterRadioButton);
        generateGetterAndSetterRadioButton.setSelected(true);
        generateGetterAndSetterRadioButton.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.generateGetterAndSetterRadioButton.text")); // NOI18N
        generateGetterAndSetterRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateGetterAndSetterRadioButtonActionPerformed(evt);
            }
        });

        getterSetterGroup.add(generateGetterRadioButton);
        generateGetterRadioButton.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.generateGetterRadioButton.text")); // NOI18N
        generateGetterRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateGetterRadioButtonActionPerformed(evt);
            }
        });

        getterSetterGroup.add(generateSetterRadioButton);
        generateSetterRadioButton.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.generateSetterRadioButton.text")); // NOI18N
        generateSetterRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateSetterRadioButtonActionPerformed(evt);
            }
        });

        generateJavadocCheckBox.setSelected(true);
        generateJavadocCheckBox.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.generateJavadocCheckBox.text")); // NOI18N
        generateJavadocCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateJavadocCheckBoxActionPerformed(evt);
            }
        });

        boundCheckBox.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.boundCheckBox.text")); // NOI18N
        boundCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boundCheckBoxActionPerformed(evt);
            }
        });

        vetoableCheckBox.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.vetoableCheckBox.text")); // NOI18N
        vetoableCheckBox.setEnabled(false);
        vetoableCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vetoableCheckBoxActionPerformed(evt);
            }
        });

        propNameTextField.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.propNameTextField.text")); // NOI18N

        indexedCheckBox.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.indexedCheckBox.text")); // NOI18N
        indexedCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indexedCheckBoxActionPerformed(evt);
            }
        });

        generatePropertyChangeSupportCheckBox.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.generatePropertyChangeSupportCheckBox.text")); // NOI18N
        generatePropertyChangeSupportCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generatePropertyChangeSupportCheckBoxActionPerformed(evt);
            }
        });

        generateVetoablePropertyChangeSupportCheckBox.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.generateVetoablePropertyChangeSupportCheckBox.text")); // NOI18N
        generateVetoablePropertyChangeSupportCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateVetoablePropertyChangeSupportCheckBoxActionPerformed(evt);
            }
        });

        previewLabel.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.previewLabel.text")); // NOI18N

        previewEditorPane.setContentType(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.previewEditorPane.contentType")); // NOI18N
        previewEditorPane.setEditable(false);
        previewEditorPane.setText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.previewEditorPane.text")); // NOI18N
        previewEditorPane.setToolTipText(org.openide.util.NbBundle.getMessage(AddPropertyPanel.class, "AddPropertyPanel.previewEditorPane.toolTipText")); // NOI18N
        previewScrollPane.setViewportView(previewEditorPane);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(nameLabel)
                            .add(typeLabel))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(boundCheckBox)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(propNameTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE))
                                    .add(layout.createSequentialGroup()
                                        .add(21, 21, 21)
                                        .add(vetoableCheckBox)
                                        .add(160, 160, 160))
                                    .add(layout.createSequentialGroup()
                                        .add(generateGetterAndSetterRadioButton)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(generateGetterRadioButton)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(generateSetterRadioButton))
                                    .add(generateJavadocCheckBox)
                                    .add(indexedCheckBox)
                                    .add(layout.createSequentialGroup()
                                        .add(generatePropertyChangeSupportCheckBox)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(generateVetoablePropertyChangeSupportCheckBox))))
                            .add(nameTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(typeComboBox, 0, 579, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(staticCheckBox)
                                            .add(privateRadioButton))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(finalCheckBox)
                                            .add(layout.createSequentialGroup()
                                                .add(packageRadioButton)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(protectedRadioButton)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(publicRadioButton)))))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(browseTypeButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 86, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(previewLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(previewScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(new java.awt.Component[] {nameLabel, previewLabel, typeLabel}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.linkSize(new java.awt.Component[] {finalCheckBox, packageRadioButton, privateRadioButton, protectedRadioButton, publicRadioButton, staticCheckBox}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(nameLabel)
                    .add(nameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(typeLabel)
                    .add(typeComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(browseTypeButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(packageRadioButton)
                            .add(protectedRadioButton)
                            .add(publicRadioButton)
                            .add(privateRadioButton))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(staticCheckBox))
                    .add(finalCheckBox))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(generateGetterAndSetterRadioButton)
                    .add(generateSetterRadioButton)
                    .add(generateGetterRadioButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(generateJavadocCheckBox)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(boundCheckBox)
                    .add(propNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(vetoableCheckBox)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(indexedCheckBox)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(generatePropertyChangeSupportCheckBox)
                    .add(generateVetoablePropertyChangeSupportCheckBox))
                .add(8, 8, 8)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(previewScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                    .add(previewLabel))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void finalCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalCheckBoxActionPerformed
        generateGetterAndSetterRadioButton.setEnabled(!finalCheckBox.isSelected());
        generateSetterRadioButton.setEnabled(!finalCheckBox.isSelected());
        if (finalCheckBox.isSelected()) {
            generateGetterRadioButton.setSelected(true);
        }
        showPreview();
    }//GEN-LAST:event_finalCheckBoxActionPerformed

    private void privateRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_privateRadioButtonActionPerformed
        showPreview();
    }//GEN-LAST:event_privateRadioButtonActionPerformed

    private void packageRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packageRadioButtonActionPerformed
        showPreview();
}//GEN-LAST:event_packageRadioButtonActionPerformed

    private void protectedRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_protectedRadioButtonActionPerformed
        showPreview();
    }//GEN-LAST:event_protectedRadioButtonActionPerformed

    private void publicRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_publicRadioButtonActionPerformed
        showPreview();
}//GEN-LAST:event_publicRadioButtonActionPerformed

    private void staticCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staticCheckBoxActionPerformed
        showPreview();
    }//GEN-LAST:event_staticCheckBoxActionPerformed

    private void indexedCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indexedCheckBoxActionPerformed
        showPreview();
    }//GEN-LAST:event_indexedCheckBoxActionPerformed

    private void typeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeComboBoxActionPerformed
        showPreview();
    }//GEN-LAST:event_typeComboBoxActionPerformed

    private void generateJavadocCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateJavadocCheckBoxActionPerformed
        showPreview();
    }//GEN-LAST:event_generateJavadocCheckBoxActionPerformed

    private void boundCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boundCheckBoxActionPerformed
        vetoableCheckBox.setEnabled(boundCheckBox.isSelected());
        showPreview();
    }//GEN-LAST:event_boundCheckBoxActionPerformed

    private void vetoableCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vetoableCheckBoxActionPerformed
        showPreview();
    }//GEN-LAST:event_vetoableCheckBoxActionPerformed

    private void generateGetterAndSetterRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateGetterAndSetterRadioButtonActionPerformed
        showPreview();
    }//GEN-LAST:event_generateGetterAndSetterRadioButtonActionPerformed

    private void generateSetterRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateSetterRadioButtonActionPerformed
        showPreview();
}//GEN-LAST:event_generateSetterRadioButtonActionPerformed

    private void generateGetterRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateGetterRadioButtonActionPerformed
        showPreview();
}//GEN-LAST:event_generateGetterRadioButtonActionPerformed

    private void generateVetoablePropertyChangeSupportCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateVetoablePropertyChangeSupportCheckBoxActionPerformed
        showPreview();
    }//GEN-LAST:event_generateVetoablePropertyChangeSupportCheckBoxActionPerformed

    private void generatePropertyChangeSupportCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generatePropertyChangeSupportCheckBoxActionPerformed
        showPreview();
    }//GEN-LAST:event_generatePropertyChangeSupportCheckBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup accessGroup;
    private javax.swing.JCheckBox boundCheckBox;
    private javax.swing.JButton browseTypeButton;
    private javax.swing.JCheckBox finalCheckBox;
    private javax.swing.JRadioButton generateGetterAndSetterRadioButton;
    private javax.swing.JRadioButton generateGetterRadioButton;
    private javax.swing.JCheckBox generateJavadocCheckBox;
    private javax.swing.JCheckBox generatePropertyChangeSupportCheckBox;
    private javax.swing.JRadioButton generateSetterRadioButton;
    private javax.swing.JCheckBox generateVetoablePropertyChangeSupportCheckBox;
    private javax.swing.ButtonGroup getterSetterGroup;
    private javax.swing.JCheckBox indexedCheckBox;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JRadioButton packageRadioButton;
    private javax.swing.JEditorPane previewEditorPane;
    private javax.swing.JLabel previewLabel;
    private javax.swing.JScrollPane previewScrollPane;
    private javax.swing.JRadioButton privateRadioButton;
    private javax.swing.JTextField propNameTextField;
    private javax.swing.JRadioButton protectedRadioButton;
    private javax.swing.JRadioButton publicRadioButton;
    private javax.swing.JCheckBox staticCheckBox;
    private javax.swing.JComboBox typeComboBox;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JCheckBox vetoableCheckBox;
    // End of variables declaration//GEN-END:variables

}
