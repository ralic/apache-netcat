/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2003 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.tasklist.docscan;

import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import org.netbeans.api.tasklist.SuggestionPriority;
import org.netbeans.modules.tasklist.core.Task;
import org.netbeans.modules.tasklist.core.PriorityListCellRenderer;
import org.openide.explorer.propertysheet.editors.EnhancedCustomPropertyEditor;
import org.openide.awt.Mnemonics;
import org.openide.util.NbBundle;

/**
 * Customizer panel for the set of tags scanned from source.
 * <p>
 * Please read comment at the beginning of initA11y before editing
 * this file using the form builder.
 * <p>
 *
 * @todo Set single list selection?
 *
 * @author  Tor Norbye
 */
public class TaskTagsPanel extends javax.swing.JPanel
    implements EnhancedCustomPropertyEditor, ActionListener, 
               ListSelectionListener, DocumentListener {

    private DefaultListModel model = null;

    /** Creates new form TaskTagsPanel */
    public TaskTagsPanel(TaskTags tags) {
        initComponents();
        initA11y();
        setPreferredSize(new Dimension(400, 200));
        this.tags = tags;

        ListCellRenderer priorityRenderer = new PriorityListCellRenderer();
        ComboBoxModel prioritiesModel = 
            new DefaultComboBoxModel(Task.getPriorityNames());
        prioCombo.setModel(prioritiesModel);
        prioCombo.setRenderer(priorityRenderer);

        tokenList.setCellRenderer(new TaskTagRenderer());
        TaskTag[] t = tags.getTags();
        model = new DefaultListModel();
        for (int i = 0; i < t.length; i++) {
            model.addElement(t[i]);
        }
        tokenList.setModel(model);

        addButton.addActionListener(this);
        changeButton.addActionListener(this);
        deleteButton.addActionListener(this);

        tokenList.addListSelectionListener(this);
        tokenList.setSelectionInterval(0, 0);

        updateSensitivity();
        nameField.getDocument().addDocumentListener(this);
        prioCombo.addActionListener(this);
    }

    private TaskTags tags = null;
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        tagLabel = new javax.swing.JLabel();
        prioLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        tokenList = new javax.swing.JList();
        prioCombo = new javax.swing.JComboBox();
        nameField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        changeButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(12, 12, 11, 11)));
        /*
        tagLabel.setText("Tag List:");
        */
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(tagLabel, gridBagConstraints);

        /*
        prioLabel.setText("Priority:");
        */
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(prioLabel, gridBagConstraints);

        /*
        nameLabel.setText("Name:");
        */
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        add(nameLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        add(tokenList, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        add(prioCombo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        add(nameField, gridBagConstraints);

        /*
        addButton.setText("Add");
        */
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(addButton, gridBagConstraints);

        /*
        changeButton.setText("Change");
        */
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(changeButton, gridBagConstraints);

        /*
        deleteButton.setText("Delete");
        */
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(deleteButton, gridBagConstraints);

    }//GEN-END:initComponents
    
    /** Initialize accessibility settings on the panel */
    private void initA11y() {
        /*
          I couldn't figure out how to use Mnemonics.setLocalizedText
          to set labels and checkboxes with a mnemonic using the
          form builder, so the closest I got was to use "/*" and "* /
          as code pre-init/post-init blocks, such that I don't actually
          execute the bundle lookup code - and then call it explicitly
          below. (I wanted to keep the text on the components so that
          I can see them when visually editing the GUI.
        */

        Mnemonics.setLocalizedText(addButton, NbBundle.getMessage(
                 TaskTagsPanel.class, "AddTag")); // NOI18N
        Mnemonics.setLocalizedText(changeButton, NbBundle.getMessage(
                    TaskTagsPanel.class, "ChangeTag")); // NOI18N
        Mnemonics.setLocalizedText(deleteButton, NbBundle.getMessage(
                    TaskTagsPanel.class, "DeleteTag")); // NOI18N
        Mnemonics.setLocalizedText(nameLabel, NbBundle.getMessage(
                 TaskTagsPanel.class, "TagName")); // NOI18N
        Mnemonics.setLocalizedText(prioLabel, NbBundle.getMessage(
                 TaskTagsPanel.class, "TagPrio")); // NOI18N
        Mnemonics.setLocalizedText(tagLabel, NbBundle.getMessage(
                     TaskTagsPanel.class, "TagList")); // NOI18N

        prioLabel.setLabelFor(prioCombo);
        tagLabel.setLabelFor(tokenList);
        nameLabel.setLabelFor(nameField);

        this.getAccessibleContext().setAccessibleDescription(
                NbBundle.getMessage(TaskTagsPanel.class, "ACSD_Tags")); // NOI18N
        prioCombo.getAccessibleContext().setAccessibleDescription(
                NbBundle.getMessage(TaskTagsPanel.class, "ACSD_Prio")); // NOI18N
        tokenList.getAccessibleContext().setAccessibleDescription(
                NbBundle.getMessage(TaskTagsPanel.class, "ACSD_List")); // NOI18N
        nameField.getAccessibleContext().setAccessibleDescription(
                NbBundle.getMessage(TaskTagsPanel.class, "ACSD_Name")); // NOI18N
        // Buttons too?
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton changeButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JComboBox prioCombo;
    private javax.swing.JLabel prioLabel;
    private javax.swing.JLabel tagLabel;
    private javax.swing.JList tokenList;
    // End of variables declaration//GEN-END:variables
    

    // When used as a property customizer
    public Object getPropertyValue() throws IllegalStateException {
        TaskTag[] ts = new TaskTag[model.getSize()];
        for (int i = 0; i < model.getSize(); i++) {
            TaskTag tag = (TaskTag)model.getElementAt(i);
            ts[i] = tag;
        }
        tags = new TaskTags();
        tags.setTags(ts);
        return tags;
    }

    class TaskTagRenderer extends DefaultListCellRenderer {
        //private Icon taskIcon = null;
        
	public TaskTagRenderer() {
            super();
            //taskIcon = new ImageIcon(Task.class.getResource (
            //  "/org/netbeans/modules/tasklist/core/task.gif")); // NOI18N
            
	}
     
	public Component getListCellRendererComponent(JList list, Object value,
                                                      int index,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value,
                                                             index, isSelected,
                                                             cellHasFocus);
            if (value instanceof TaskTag) {
                TaskTag tag = (TaskTag)value;
                String desc = tag.getToken();
                setText(desc);
                //setIcon(taskIcon);
            } else {
                setText(value.toString());
            }
            return c;
        }
    }

    private void updateSensitivity() {
        int[] selected = tokenList.getSelectedIndices();
        int count = (selected != null) ? selected.length : 0;
        deleteButton.setEnabled(count > 0);

        // Change is enabled whenever the selected item is different
        // from what is in the Name field
        String token = getToken();
        if (count == 1) {
            TaskTag selectedTag = (TaskTag)model.getElementAt(selected[0]);
            changeButton.setEnabled(!selectedTag.getToken().equals(token) ||
                                 getPriority() != selectedTag.getPriority());
        } else {
            changeButton.setEnabled(false);
        }

        // Add is enabled whenever what is in the selected is different
        // from all the items in the list
        int n = model.getSize();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            TaskTag tag = (TaskTag)model.getElementAt(i);
            if (tag.getToken().equals(token)) {
                found = true;
                break;
            }
        }
        addButton.setEnabled(!found);
    }

    private SuggestionPriority getPriority() {
        int p = prioCombo.getSelectedIndex() + 1;
        SuggestionPriority priority = Task.getPriority(p);
        return priority;
    }

    private String getToken() {
        return nameField.getText().trim();
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == prioCombo) {
            updateSensitivity();
        } else if (source == addButton) {
            TaskTag tag = new TaskTag(getToken(), getPriority());
            model.addElement(tag);
            int at = model.getSize()-1;
            tokenList.setSelectionInterval(at, at);
        } else if (source == changeButton) {
            TaskTag tag = new TaskTag(getToken(), getPriority());
            int[] selected = tokenList.getSelectedIndices();
            model.removeElementAt(selected[0]);
            model.insertElementAt(tag, selected[0]);
            tokenList.setSelectionInterval(selected[0], selected[0]);
        } else if (source == deleteButton) {
            int[] selected = tokenList.getSelectedIndices();
            Arrays.sort(selected);
	    int min = 0;
            if (selected.length == model.size()) {
                min = 1;
            }
            for (int i = selected.length-1; i >= min; i--) {
                model.removeElementAt(selected[i]);
            }
            tokenList.setSelectionInterval(0, 0);
        }
        updateSensitivity();
    }

    public void valueChanged(javax.swing.event.ListSelectionEvent event) {
        int[] selected = tokenList.getSelectedIndices();
        if ((selected != null) && (selected.length == 1)) {
            TaskTag tag = (TaskTag)tokenList.getModel().
                getElementAt(selected[0]);
            int p = tag.getPriority().intValue() - 1;
            prioCombo.setSelectedIndex(p);
            nameField.setText(tag.getToken());
        } else {
            nameField.setText("");
            prioCombo.setSelectedIndex(2);
        }
        updateSensitivity();
    }

    public void changedUpdate(DocumentEvent e) {
        updateSensitivity();
    }
    public void insertUpdate(DocumentEvent e) {
        updateSensitivity();
    }
    public void removeUpdate(DocumentEvent e) {
        updateSensitivity();
    }
}
