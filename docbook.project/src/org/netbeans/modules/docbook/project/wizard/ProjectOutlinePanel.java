/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2009 Sun Microsystems, Inc. All rights reserved.
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
package org.netbeans.modules.docbook.project.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.AbstractButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.validation.adapters.WizardDescriptorAdapter;
import org.netbeans.validation.api.AbstractValidator;
import org.netbeans.validation.api.Problem;
import org.netbeans.validation.api.Problems;
import org.netbeans.validation.api.Severity;
import org.netbeans.validation.api.ui.ValidationGroup;
import org.netbeans.validation.api.ui.ValidationUI;
import org.netbeans.validation.api.ui.swing.SwingValidationGroup;
import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;

/**
 *
 * @author  Tim Boudreau
 */
public class ProjectOutlinePanel extends javax.swing.JPanel implements ValidationUI {
    private ValidationGroup grp = ValidationGroup.create(this);
    public static final String PROP_GENERATION_STYLE = "generationStyle";
    public ProjectOutlinePanel() {
        initComponents();
        inline.putClientProperty(PROP_GENERATION_STYLE, ChapterGenerationStyle.INLINE);
        files.putClientProperty(PROP_GENERATION_STYLE, ChapterGenerationStyle.FILE_PER_CHAPTER);
        dirs.putClientProperty(PROP_GENERATION_STYLE, ChapterGenerationStyle.DIRECTORY_PER_CHAPTER);
        SwingValidationGroup.setComponentName(outline, NbBundle.getMessage(ProjectOutlinePanel.class, "NAME_OUTLINE"));
        grp.add(outline, new OLValidator());
    }

    private static class OLValidator extends AbstractValidator<String> {
        OLValidator() {
            super(String.class);
        }
        @Override public void validate(Problems prblms, String string, String t) {
            if (t == null || t.trim().length() == 0) {
                prblms.add(NbBundle.getMessage(ProjectOutlinePanel.class, "MSG_OUTLINE_EMPTY"), Severity.INFO); //NOI18N
            }
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btns = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        outline = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        inline = new javax.swing.JRadioButton();
        dirs = new javax.swing.JRadioButton();
        files = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();

        outline.setColumns(20);
        outline.setRows(5);
        jScrollPane2.setViewportView(outline);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        btns.add(inline);
        org.openide.awt.Mnemonics.setLocalizedText(inline, org.openide.util.NbBundle.getMessage(ProjectOutlinePanel.class, "ProjectOutlinePanel.inline.text")); // NOI18N
        inline.setToolTipText(org.openide.util.NbBundle.getMessage(ProjectOutlinePanel.class, "ProjectOutlinePanel.inline.toolTipText")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel1.add(inline, gridBagConstraints);

        btns.add(dirs);
        dirs.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(dirs, org.openide.util.NbBundle.getMessage(ProjectOutlinePanel.class, "ProjectOutlinePanel.dirs.text")); // NOI18N
        dirs.setToolTipText(org.openide.util.NbBundle.getMessage(ProjectOutlinePanel.class, "ProjectOutlinePanel.dirs.toolTipText")); // NOI18N
        dirs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(dirs, gridBagConstraints);

        btns.add(files);
        org.openide.awt.Mnemonics.setLocalizedText(files, org.openide.util.NbBundle.getMessage(ProjectOutlinePanel.class, "ProjectOutlinePanel.files.text")); // NOI18N
        files.setToolTipText(org.openide.util.NbBundle.getMessage(ProjectOutlinePanel.class, "ProjectOutlinePanel.files.toolTipText")); // NOI18N
        files.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel1.add(files, gridBagConstraints);

        jLabel1.setLabelFor(outline);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ProjectOutlinePanel.class, "ProjectOutlinePanel.jLabel1.text")); // NOI18N
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 645, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void addNotify() {
        super.addNotify();
        grp.performValidation();
    }

    WizardDescriptorAdapter adap;
    public void load (final WizardDescriptor wiz) {
        if (adap != null) {
            grp.removeUI(adap);
        }
        adap = new WizardDescriptorAdapter(wiz);
        grp.addUI(adap);
        grp.runWithValidationSuspended(new Runnable() {
            public void run() {
                ChapterGenerationStyle style = (ChapterGenerationStyle) wiz.getProperty(PROP_GENERATION_STYLE);
                ProjectKind kind = (ProjectKind) wiz.getProperty("kind");
                if (kind == ProjectKind.Book) {
                    if (style != null) {
                        inline.setEnabled(true);
                        files.setEnabled(true);
                        dirs.setEnabled(true);
                        for (AbstractButton b : new AbstractButton[] { files, dirs, inline}) {
                            if (style.equals(b.getClientProperty(PROP_GENERATION_STYLE))) {
                                b.setSelected(true);
                            }
                        }
                    }
                } else {
                    inline.setSelected(true);
                    inline.setEnabled(false);
                    files.setEnabled(false);
                    dirs.setEnabled(false);
                }
                String outl = (String) wiz.getProperty("outline"); //NOI18N
                if (outl != null) {
                    outline.setText(outl);
                }
            }
        });
    }

    public void save (WizardDescriptor wiz) {
        wiz.putProperty(PROP_GENERATION_STYLE, getGenerationStyle());
        wiz.putProperty ("outline", outline.getText()); //NOI18N
        grp.removeUI(adap);
    }



    public boolean check() {
        return grp.performValidation() == null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btns;
    private javax.swing.JRadioButton dirs;
    private javax.swing.JRadioButton files;
    private javax.swing.JRadioButton inline;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea outline;
    // End of variables declaration//GEN-END:variables

    @Override public void clearProblem() {
        if (adap != null) {
            adap.clearProblem();
        }
        if (cl != null) {
            cl.stateChanged (new ChangeEvent(this));
        }
    }

    @Override public void showProblem(Problem prblm) {
        if (adap != null) {
            adap.showProblem(prblm);
        }
        if (cl != null) {
            cl.stateChanged (new ChangeEvent(this));
        }
    }

    private ChangeListener cl;
    void addChangeListener (ChangeListener cl) {
        this.cl = cl;
    }

    public boolean validate(Problems prblms, String compName, String model) {
        String[] split = model.split("\n");
        List<String> list = Arrays.asList(split);
        Set <String> set = new HashSet<String>(list);
        if (set.size() != list.size()) {
            list = new ArrayList<String>(list);
            for (String s : set) {
                list.remove(s);
            }
            prblms.add("Duplicate entries '" + list + "'");
            return false;
        }
        return true;
    }

    private ChapterGenerationStyle getGenerationStyle() {
        for (AbstractButton b : new AbstractButton[] { files, dirs, inline}) {
            if (b.isSelected()) {
                return (ChapterGenerationStyle) b.getClientProperty(PROP_GENERATION_STYLE);
            }
        }
        return null;
    }
    
}
