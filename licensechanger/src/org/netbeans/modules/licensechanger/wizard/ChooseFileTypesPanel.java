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
 * NewJPanel.java
 *
 * Created on Mar 20, 2009, 1:14:43 AM
 */
package org.netbeans.modules.licensechanger.wizard;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.netbeans.modules.licensechanger.api.Customizable;
import org.netbeans.modules.licensechanger.api.FileHandler;
import org.netbeans.modules.licensechanger.wizard.utils.CheckableNodeCapability;
import org.netbeans.modules.licensechanger.wizard.utils.WizardProperties;
import org.netbeans.swing.outline.DefaultOutlineModel;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.CheckableNode;
import org.openide.explorer.view.OutlineView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.NbPreferences;
import org.openide.util.lookup.Lookups;

/**
 * Panel for selection of file types for license header update.
 * 
 * @author Tim Boudreau
 * @author Nils Hoffmann (Refactoring)
 */
public class ChooseFileTypesPanel extends javax.swing.JPanel implements ExplorerManager.Provider, PropertyChangeListener {

    private final ExplorerManager mgr = new ExplorerManager();

    public ChooseFileTypesPanel() {
        initComponents();
        String copyrightHolder =
                NbPreferences.forModule(ChooseFileTypesWizardPanel.class)
                .get(WizardProperties.KEY_COPYRIGHT_HOLDER, null);
        if (copyrightHolder != null && !copyrightHolder.trim().isEmpty()) {
            copyrightHolderField.setText(copyrightHolder);
        }
        mgr.setRootContext(new AbstractNode(Children.create(new FileHandlerChildren(), false)));
        mgr.addPropertyChangeListener(this);
        updateMap();
        updateView();
    }

    private void updateMap() {
        Set<FileHandler> handlers = getFileHandlers();
        firePropertyChange(WizardProperties.KEY_FILE_HANDLERS, null, handlers);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new OutlineView();
        customizerPanel = new javax.swing.JPanel();
        noCustomizerLabel = new javax.swing.JLabel();
        lineEndingsPanel1 = new org.netbeans.modules.licensechanger.wizard.LineEndingsPanel();
        licensePropertiesPanel = new javax.swing.JPanel();
        copyrightHolderLabel = new javax.swing.JLabel();
        copyrightHolderField = new javax.swing.JTextField();
        storeInUserProperties = new javax.swing.JCheckBox();

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("controlShadow")));

        customizerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(ChooseFileTypesPanel.class, "ChooseFileTypesPanel.customizerPanel.border.title"))); // NOI18N
        customizerPanel.setLayout(new java.awt.BorderLayout());

        noCustomizerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noCustomizerLabel.setText(org.openide.util.NbBundle.getMessage(ChooseFileTypesPanel.class, "ChooseFileTypesPanel.noCustomizerLabel.text")); // NOI18N
        noCustomizerLabel.setEnabled(false);
        customizerPanel.add(noCustomizerLabel, java.awt.BorderLayout.CENTER);
        noCustomizerLabel.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(ChooseFileTypesPanel.class, "ChooseFileTypesPanel.noCustomizerLabel.text")); // NOI18N

        licensePropertiesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(ChooseFileTypesPanel.class, "ChooseFileTypesPanel.licensePropertiesPanel.border.title"))); // NOI18N

        copyrightHolderLabel.setLabelFor(copyrightHolderField);
        copyrightHolderLabel.setText(org.openide.util.NbBundle.getMessage(ChooseFileTypesPanel.class, "ChooseFileTypesPanel.copyrightHolderLabel.text")); // NOI18N

        copyrightHolderField.setText(System.getProperty("user.name") == null ? "" : System.getProperty("user.name"));
        copyrightHolderField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                copyrightHolderFieldFocusGained(evt);
            }
        });

        storeInUserProperties.setText(org.openide.util.NbBundle.getMessage(ChooseFileTypesPanel.class, "ChooseFileTypesPanel.storeInUserProperties.text")); // NOI18N

        javax.swing.GroupLayout licensePropertiesPanelLayout = new javax.swing.GroupLayout(licensePropertiesPanel);
        licensePropertiesPanel.setLayout(licensePropertiesPanelLayout);
        licensePropertiesPanelLayout.setHorizontalGroup(
            licensePropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(licensePropertiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(licensePropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(copyrightHolderField)
                    .addGroup(licensePropertiesPanelLayout.createSequentialGroup()
                        .addComponent(copyrightHolderLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(storeInUserProperties, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        licensePropertiesPanelLayout.setVerticalGroup(
            licensePropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(licensePropertiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(copyrightHolderLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(copyrightHolderField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(storeInUserProperties)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(customizerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lineEndingsPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                            .addComponent(licensePropertiesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customizerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lineEndingsPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(licensePropertiesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        customizerPanel.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(ChooseFileTypesPanel.class, "ChooseFileTypesPanel.customizerPanel.border.title")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    private void copyrightHolderFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_copyrightHolderFieldFocusGained
        copyrightHolderField.selectAll();
    }//GEN-LAST:event_copyrightHolderFieldFocusGained
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField copyrightHolderField;
    private javax.swing.JLabel copyrightHolderLabel;
    private javax.swing.JPanel customizerPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel licensePropertiesPanel;
    private org.netbeans.modules.licensechanger.wizard.LineEndingsPanel lineEndingsPanel1;
    private javax.swing.JLabel noCustomizerLabel;
    private javax.swing.JCheckBox storeInUserProperties;
    // End of variables declaration//GEN-END:variables

    @Override
    public ExplorerManager getExplorerManager() {
        return mgr;
    }

    public Set<FileHandler> getFileHandlers() {
        Set<FileHandler> result = new HashSet<FileHandler>();
        for (Node n : mgr.getRootContext().getChildren().getNodes(true)) {
            CheckableNode cn = n.getLookup().lookup(CheckableNode.class);
            if (cn != null && cn.isSelected()) {
                result.addAll(n.getLookup().lookupAll(FileHandler.class));
            }
        }
        return result;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (ExplorerManager.PROP_SELECTED_NODES.equals(evt.getPropertyName())) {
            Node[] n = mgr.getSelectedNodes();
            updateCustomizer(n.length == 1 ? n[0].getLookup().lookup(Customizable.class) : null);
        }
    }

    public String getCopyrightHolder() {
        return copyrightHolderField.getText();
    }

    public void setCopyrightHolder(String copyrightHolder) {
        copyrightHolderField.setText(copyrightHolder);
    }

    public boolean isStoreInUserProperties() {
        return storeInUserProperties.isSelected();
    }

    public void setStoreInUserProperties(boolean b) {
        storeInUserProperties.setSelected(b);
    }

    private void updateView() {
        OutlineView ov = (OutlineView) jScrollPane1;
        ov.getOutline().setRootVisible(false);
        ov.setPopupAllowed(false);
        ov.setTreeSortable(false);
        ov.getOutline().setShowGrid(false);
        String headerName = org.openide.util.NbBundle.getMessage(ChooseFileTypesPanel.class, "ChooseFileTypesPanel.nodesLabel.text"); // NOI18N
        ((DefaultOutlineModel) ov.getOutline().getOutlineModel()).setNodesColumnLabel(headerName);
        setName("Choose File Types");
    }

    private static final class FileHandlerChildren extends ChildFactory<FileHandler> {

        @Override
        protected boolean createKeys(List<FileHandler> toPopulate) {
            toPopulate.addAll(Lookup.getDefault().lookupAll(FileHandler.class));
            Collections.sort(toPopulate, new Comparator<FileHandler>() {
                @Override
                public int compare(FileHandler t, FileHandler t1) {
                    return t.getDisplayName().compareTo(t1.getDisplayName());
                }
            });
            return true;
        }

        @Override
        protected Node createNodeForKey(FileHandler key) {
            AbstractNode result = new AbstractNode(Children.LEAF, Lookups.fixed(key, new CheckableNodeCapability()));
            result.setDisplayName(key.getDisplayName());
            return result;
        }
    }

    private void updateCustomizer(Customizable c) {
        customizerPanel.removeAll();
        customizerPanel.add(c == null ? noCustomizerLabel : c.getCustomizer());
        invalidate();
        revalidate();
        repaint();
    }
}
