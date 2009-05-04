/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2007-2009 Michel Graciano. All rights reserved.
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
 * nbbuild/licenses/CDDL-GPL-2-CP. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is Save Automatically Project. The Initial Developer of
 * the Original Software is Michel Graciano. Portions Copyright 2007-2009 Michel
 * Graciano. All Rights Reserved.
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
package org.netbeans.modules.autosave;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.ref.WeakReference;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;
import org.openide.awt.Mnemonics;
import org.openide.util.NbBundle;

final class AutoSavePanel extends javax.swing.JPanel {
   private final AutoSaveOptionsPanelController controller;

   AutoSavePanel(final AutoSaveOptionsPanelController controller) {
      this.controller = controller;

      Integer value = new Integer(10);
      Integer min = new Integer(0);
      Integer max = new Integer(999);
      Integer step = new Integer(1);
      spnModel = new SpinnerNumberModel(value, min, max, step);

      initComponents();

      Preferences.userNodeForPackage(AutoSavePanel.class).
            addPreferenceChangeListener(new WeakReference<PreferenceChangeListener>(
            new PreferenceChangeListener() {
               public void preferenceChange(PreferenceChangeEvent evt) {
                  controller.update();
                  controller.changed();
               }
            }).get());
   }

   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      chkUseFeature = new JCheckBox();
      chkSaveOnFocusLost = new JCheckBox();
      jLabel1 = new JLabel();
      spnMinutes = new JSpinner();
      jLabel2 = new JLabel();

      setBackground(Color.white);
      Mnemonics.setLocalizedText(chkUseFeature, NbBundle.getMessage(AutoSavePanel.class,
            "AutoSavePanel.chkUseFeature.text"));
      chkUseFeature.setActionCommand(NbBundle.getMessage(AutoSavePanel.class, "AutoSavePanel.chkUseFeature.actionCommand")); // NOI18N
      chkUseFeature.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
      chkUseFeature.setMargin(new Insets(0, 0, 0, 0));
      chkUseFeature.setOpaque(false);
      chkUseFeature.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent evt) {
            chkUseFeatureItemStateChanged(evt);
         }
      });

      Mnemonics.setLocalizedText(chkSaveOnFocusLost, NbBundle.getMessage(AutoSavePanel.class,"AutoSavePanel.chkSaveOnFocusLost.text")); // NOI18N
      chkSaveOnFocusLost.setOpaque(false);


      Mnemonics.setLocalizedText(jLabel1, NbBundle.getMessage(AutoSavePanel.class, "AutoSavePanel.jLabel1.text")); // NOI18N
      spnMinutes.setModel(this.spnModel);


      spnMinutes.setToolTipText(NbBundle.getMessage(AutoSavePanel.class, "AutoSavePanel.spnMinutes.toolTipText")); // NOI18N
      Mnemonics.setLocalizedText(jLabel2,
            NbBundle.getMessage(AutoSavePanel.class,
            "AutoSavePanel.jLabel2.text"));
      GroupLayout layout = new GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(GroupLayout.LEADING)
         .add(layout.createSequentialGroup()
            .add(layout.createParallelGroup(GroupLayout.LEADING)
               .add(chkUseFeature)
               .add(layout.createSequentialGroup()
                  .add(17, 17, 17)
                  .add(layout.createParallelGroup(GroupLayout.LEADING)
                     .add(layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(spnMinutes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(jLabel2))
                     .add(chkSaveOnFocusLost))))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(GroupLayout.LEADING)
         .add(layout.createSequentialGroup()
            .add(chkUseFeature)
            .addPreferredGap(LayoutStyle.RELATED)
            .add(chkSaveOnFocusLost)
            .addPreferredGap(LayoutStyle.RELATED)
            .add(layout.createParallelGroup(GroupLayout.BASELINE)
               .add(jLabel1)
               .add(spnMinutes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
               .add(jLabel2)))
      );
   }// </editor-fold>//GEN-END:initComponents

    private void chkUseFeatureItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkUseFeatureItemStateChanged
       this.chkSaveOnFocusLost.setEnabled(this.chkUseFeature.isSelected());
       this.spnMinutes.setEnabled(this.chkUseFeature.isSelected());
    }//GEN-LAST:event_chkUseFeatureItemStateChanged

   void load() {
      this.chkUseFeature.setSelected(Preferences.userNodeForPackage(
            AutoSavePanel.class).getBoolean(AutoSaveAdvancedOption.KEY_ACTIVE,
            false));
      this.chkSaveOnFocusLost.setSelected(Preferences.userNodeForPackage(
            AutoSavePanel.class).getBoolean(
            AutoSaveAdvancedOption.KEY_SAVE_ON_FOCUS_LOST, false));
      this.spnModel.setValue(
            Integer.valueOf(Preferences.userNodeForPackage(AutoSavePanel.class).
            getInt(AutoSaveAdvancedOption.KEY_INTERVAL, 10)));
   }

   void store() {
      Preferences.userNodeForPackage(AutoSavePanel.class).putBoolean(
            AutoSaveAdvancedOption.KEY_ACTIVE, this.chkUseFeature.isSelected());
      Preferences.userNodeForPackage(AutoSavePanel.class).putBoolean(
            AutoSaveAdvancedOption.KEY_SAVE_ON_FOCUS_LOST,
            this.chkSaveOnFocusLost.isSelected());
      Preferences.userNodeForPackage(AutoSavePanel.class).putInt(
            AutoSaveAdvancedOption.KEY_INTERVAL, this.spnModel.getNumber().
            intValue());
   }

   boolean valid() {
      return true;
   }
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private JCheckBox chkSaveOnFocusLost;
   private JCheckBox chkUseFeature;
   private JLabel jLabel1;
   private JLabel jLabel2;
   private JSpinner spnMinutes;
   // End of variables declaration//GEN-END:variables
   private SpinnerNumberModel spnModel;
}