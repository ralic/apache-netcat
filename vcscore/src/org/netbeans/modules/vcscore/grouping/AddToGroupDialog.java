/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2000 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.vcscore.grouping;

/**
 *
 * @author  Milos Kleint
 */

import org.openide.loaders.*;
import org.openide.*;
import org.openide.util.*;
import org.openide.nodes.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class AddToGroupDialog extends javax.swing.JPanel {

    private DataObject dataObject;
    /** Creates new form AddToGroupDialog */
    public AddToGroupDialog() {
        initComponents();
        lblGroups.setDisplayedMnemonic(NbBundle.getBundle(AddToGroupDialog.class)
             .getString("AddToGroupDialog.lblGroup.mnemonic").charAt(0));   //NOI18N
        lblGroups.setLabelFor(lstGroups);
        cbDontShow.setMnemonic(NbBundle.getBundle(AddToGroupDialog.class)
             .getString("AddToGroupDialog.cbDontShow.mnemonic").charAt(0));   //NOI18N
        Enumeration en = GroupUtils.getMainVcsGroupNodeInstance().getChildren().nodes();
        DefaultListModel model = new DefaultListModel();
        while (en.hasMoreElements()) {
            VcsGroupNode node = (VcsGroupNode)en.nextElement();
            model.addElement(node.getDisplayName());
        }
        lstGroups.setModel(model);
        lstGroups.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public AddToGroupDialog(DataObject obj) {
        this();
        setDataObject(obj);
    }

    public void setDataObject(DataObject obj) {
        dataObject = obj;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        lblGroups = new javax.swing.JLabel();
        spGroups = new javax.swing.JScrollPane();
        lstGroups = new javax.swing.JList();
        cbDontShow = new javax.swing.JCheckBox();

        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;

        lblGroups.setText(org.openide.util.NbBundle.getBundle(AddToGroupDialog.class).getString("AddToGroupDialog.lblGroup.text"));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.insets = new java.awt.Insets(12, 12, 0, 12);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        add(lblGroups, gridBagConstraints1);

        spGroups.setPreferredSize(new java.awt.Dimension(360, 132));
        spGroups.setMinimumSize(new java.awt.Dimension(100, 50));
        spGroups.setViewportView(lstGroups);

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(6, 12, 0, 12);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints1.weightx = 0.5;
        add(spGroups, gridBagConstraints1);

        cbDontShow.setText(org.openide.util.NbBundle.getBundle(AddToGroupDialog.class).getString("AddToGroupDialog.cbDontShow.text"));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.insets = new java.awt.Insets(6, 12, 12, 12);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        add(cbDontShow, gridBagConstraints1);

    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblGroups;
    private javax.swing.JScrollPane spGroups;
    private javax.swing.JList lstGroups;
    private javax.swing.JCheckBox cbDontShow;
    // End of variables declaration//GEN-END:variables

    public static void openChooseDialog(DataObject dataObject) {
        final AddToGroupDialog dialog = new AddToGroupDialog();
        dialog.setDataObject(dataObject);
        DialogDescriptor dd = new DialogDescriptor(dialog, 
             NbBundle.getBundle(AddToGroupDialog.class).getString("AddToGroupDialog.dialogTitle"));
        dd.setOptionType(DialogDescriptor.YES_NO_OPTION);
        dd.setModal(true);
        dd.setButtonListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if (evt.getSource().equals(DialogDescriptor.NO_OPTION)) {
                        dialog.checkDontShow();
                        return;
                    }
                    if (evt.getSource().equals(DialogDescriptor.YES_OPTION)) {
                        dialog.checkDontShow();
                        dialog.addToSelectedGroup();
                    }
                }
            });
         final java.awt.Dialog dial = TopManager.getDefault().createDialog(dd);
         SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    dial.show();
                }
         });        
    }
    
    private void checkDontShow() {
        if (cbDontShow.isSelected()) {
             VcsGroupSettings grSettings = (VcsGroupSettings)SharedClassObject.findObject(VcsGroupSettings.class, true);
             grSettings.setAutoAddition(VcsGroupSettings.ADDITION_TO_DEFAULT);
        }
    }
    
    private void addToSelectedGroup() {
        Object obj = lstGroups.getSelectedValue();
        if (obj != null) {
            String grString = obj.toString();
            Node grFolder = GroupUtils.getMainVcsGroupNodeInstance();
            Node[] dobjs = grFolder.getChildren().getNodes();
            DataFolder group = null;
            if (dobjs == null) return;
            for (int i = 0; i < dobjs.length; i++) {
                if (dobjs[i].getName().equals(grString)) {
                    DataFolder fold = (DataFolder)dobjs[i].getCookie(DataObject.class);
                    group = fold;
                    break;
                }
            }
            if (group == null) return;
            Node[] dobjNode = new Node[] {dataObject.getNodeDelegate()};
            GroupUtils.addToGroup(group, dobjNode);
        }
    }
        
}
