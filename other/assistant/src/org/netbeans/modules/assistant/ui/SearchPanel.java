/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2002 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.assistant.ui;

import org.netbeans.modules.assistant.search.SearchResultComponent;

import java.util.*;
/*
 * SearchPanel.java
 *
 * Created on October 18, 2002, 9:41 AM
 *
 * @author  Richard Gregor
 */
public class SearchPanel extends javax.swing.JPanel {
    private ResourceBundle bundle;
    
    /** Creates new form SearchPanel */
    public SearchPanel() {
        bundle = ResourceBundle.getBundle("org/netbeans/modules/assistant/ui/Bundle");
        initComponents();
        initAccessibility();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        label = new javax.swing.JLabel();
        field = new javax.swing.JTextField();

        setLayout(new java.awt.GridBagLayout());

        label.setDisplayedMnemonic(java.util.ResourceBundle.getBundle("org/netbeans/modules/assistant/ui/Bundle").getString("ASC_Find_MNC").charAt(0));
        label.setLabelFor(field);
        label.setText(java.util.ResourceBundle.getBundle("org/netbeans/modules/assistant/ui/Bundle").getString("LBL_Find"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 5);
        add(label, gridBagConstraints);

        field.setColumns(10);
        field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        add(field, gridBagConstraints);

    }//GEN-END:initComponents

    private void fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldActionPerformed
        SearchResultComponent srcomp = SearchResultComponent.getDefault();
        srcomp.find(field.getText());      
        
    }//GEN-LAST:event_fieldActionPerformed
    
    private void initAccessibility(){
        getAccessibleContext().setAccessibleDescription(bundle.getString("ACS_Search_Panel_DESC"));
        field.getAccessibleContext().setAccessibleDescription(bundle.getString("ACS_Search_Field_DESC"));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel label;
    private javax.swing.JTextField field;
    // End of variables declaration//GEN-END:variables
    
}
