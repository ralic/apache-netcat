/* XXXX Add SPL here!
 * TypesCustomizer.java
 *
 * Created on December 31, 2002, 2:46 PM
 */

package org.netbeans.modules.tasklist.suggestions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import org.netbeans.api.tasklist.SuggestionManager;
import org.openide.util.NbBundle;

/**
 *
 * @author  tornorbye
 */
public class TypesCustomizer extends javax.swing.JPanel 
    implements ActionListener, ListSelectionListener {
    
    /** Creates new form TypesCustomizer */
    public TypesCustomizer() {
        initComponents();
        populateLists();
        updateSensitivity();
    }

    private DefaultListModel enabledModel = null;
    private DefaultListModel disabledModel = null;
    private DefaultListModel confirmModel = null;

    
    /** Populate the lists with the current types */
    private void populateLists() {
        Collection cl = SuggestionTypes.getTypes().getAllTypes();
        Iterator it = cl.iterator();
        SuggestionManagerImpl manager = 
            (SuggestionManagerImpl)SuggestionManager.getDefault();
        enabledModel = new DefaultListModel();
        disabledModel = new DefaultListModel();
        confirmModel = new DefaultListModel();
        while (it.hasNext()) {
            SuggestionType type = (SuggestionType)it.next();
            if (manager.isEnabled(type.getName())) {
                enabledModel.addElement(type.getLocalizedName());
                if (!manager.isConfirm(type)) {
                    confirmModel.addElement(type.getLocalizedName());
                }
            } else {
                disabledModel.addElement(type.getLocalizedName());
            }
        }
        disabledList.setModel(disabledModel);
        enabledList.setModel(enabledModel);
        confirmationList.setModel(confirmModel);
        
        addActiveButton.addActionListener(this);
        removeActiveButton.addActionListener(this);
        removeConfButton.addActionListener(this);
        addConfButton.addActionListener(this);
        enabledList.addListSelectionListener(this);
        disabledList.addListSelectionListener(this);
        confirmationList.addListSelectionListener(this);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        enabledList = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        removeActiveButton = new javax.swing.JButton();
        addActiveButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        disabledList = new javax.swing.JList();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        confirmationList = new javax.swing.JList();
        jPanel4 = new javax.swing.JPanel();
        addConfButton = new javax.swing.JButton();
        removeConfButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.BorderLayout(0, 6));

        jLabel1.setText(NbBundle.getMessage(TypesCustomizer.class, "ActiveTypes")); // NOI18N();
        jPanel1.add(jLabel1, java.awt.BorderLayout.NORTH);

        jPanel1.add(enabledList, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 11, 11);
        add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridLayout(2, 1, 0, 6));

        removeActiveButton.setText(NbBundle.getMessage(TypesCustomizer.class, "RemoveType")); // NOI18N();
        jPanel2.add(removeActiveButton);

        addActiveButton.setText(NbBundle.getMessage(TypesCustomizer.class, "AddType")); // NOI18N();
        jPanel2.add(addActiveButton);

        add(jPanel2, new java.awt.GridBagConstraints());

        jPanel3.setLayout(new java.awt.BorderLayout(0, 6));

        jLabel2.setText(NbBundle.getMessage(TypesCustomizer.class, "DisabledTypes")); // NOI18N();
        jPanel3.add(jLabel2, java.awt.BorderLayout.NORTH);

        jPanel3.add(disabledList, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 11, 11);
        add(jPanel3, gridBagConstraints);

        jPanel5.setLayout(new java.awt.BorderLayout(0, 6));

        jLabel3.setText(NbBundle.getMessage(TypesCustomizer.class, "NoConfirmation")); // NOI18N();
        jPanel5.add(jLabel3, java.awt.BorderLayout.NORTH);

        jPanel5.add(confirmationList, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 11, 11);
        add(jPanel5, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridLayout(2, 1, 0, 6));

        addConfButton.setText(NbBundle.getMessage(TypesCustomizer.class, "Add")); // NOI18N();
        jPanel4.add(addConfButton);

        removeConfButton.setText(NbBundle.getMessage(TypesCustomizer.class, "Remove")); // NOI18N();
        jPanel4.add(removeConfButton);

        add(jPanel4, new java.awt.GridBagConstraints());

    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList disabledList;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton addActiveButton;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton removeConfButton;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JList enabledList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton addConfButton;
    private javax.swing.JButton removeActiveButton;
    private javax.swing.JList confirmationList;
    // End of variables declaration//GEN-END:variables
    
    /** Apply changes in the dialog */
    void apply() {
        ListModel model = enabledList.getModel();
        int n = model.getSize();
        SuggestionTypes types = SuggestionTypes.getTypes();
        ArrayList enabled = new ArrayList(n);
        for (int i = 0; i < n; i++) {
            SuggestionType t = types.findTypeByDesc(
                                     model.getElementAt(i).toString());
            enabled.add(t);
        }
        
        model = disabledList.getModel();
        n = model.getSize();
        ArrayList disabled = new ArrayList(n);
        for (int i = 0; i < n; i++) {
            SuggestionType t = types.findTypeByDesc(
                                     model.getElementAt(i).toString());
            disabled.add(t);
        }
        
        model = confirmationList.getModel();
        n = model.getSize();
        ArrayList confirmation = new ArrayList(n);
        for (int i = 0; i < n; i++) {
            SuggestionType t = types.findTypeByDesc(
                                     model.getElementAt(i).toString());
            confirmation.add(t);
        }

        SuggestionManagerImpl manager = 
            (SuggestionManagerImpl)SuggestionManager.getDefault();
        manager.editTypes(enabled, disabled, confirmation);
    }
    
    private void updateSensitivity() {
        int[] selected = enabledList.getSelectedIndices();
        if ((selected == null) || (selected.length == 0)) {
            removeActiveButton.setEnabled(false);
            addConfButton.setEnabled(false);
        } else {
            removeActiveButton.setEnabled(true);
            addConfButton.setEnabled(true);
        }
        selected = disabledList.getSelectedIndices();
        if ((selected == null) || (selected.length == 0)) {
            addActiveButton.setEnabled(false);
        } else {
            addActiveButton.setEnabled(true);
        }
        selected = confirmationList.getSelectedIndices();
        if ((selected == null) || (selected.length == 0)) {
            removeConfButton.setEnabled(false);
        } else {
            removeConfButton.setEnabled(true);
        }
    }
    
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == addActiveButton) {
            // Undisable
            int[] selected = disabledList.getSelectedIndices();
            Arrays.sort(selected);
            for (int i = selected.length-1; i >= 0; i--) {
                enabledModel.addElement(disabledModel.getElementAt(selected[i]));
                disabledModel.removeElementAt(selected[i]);
            }
        } else if (ev.getSource() == removeActiveButton) {
            // Disable
            int[] selected = enabledList.getSelectedIndices();
            Arrays.sort(selected);
            for (int i = selected.length-1; i >= 0; i--) {
                disabledModel.addElement(enabledModel.getElementAt(selected[i]));
                enabledModel.removeElementAt(selected[i]);
            }
        } else if (ev.getSource() == removeConfButton) {
            // Remove from no-confirmation
            int[] selected = confirmationList.getSelectedIndices();
            Arrays.sort(selected);
            for (int i = selected.length-1; i >= 0; i--) {
                confirmModel.removeElementAt(selected[i]);
            }
        } else if (ev.getSource() == addConfButton) {
            // Add to no-confirmation
            int[] selected = enabledList.getSelectedIndices();
            for (int i = selected.length-1; i >= 0; i--) {
                confirmModel.addElement(enabledModel.getElementAt(selected[i]));
            }
        }
    }
    
    public void valueChanged(javax.swing.event.ListSelectionEvent event) {
        if (event.getValueIsAdjusting()) {
            return;
        }
        updateSensitivity();
        JList list = (JList)event.getSource();
        if (list.getSelectedIndex() != -1) {
            if (list != enabledList) {
                enabledList.clearSelection();
            }
            if (list != disabledList) {
                disabledList.clearSelection();
            }
            if (list != confirmationList) {
                confirmationList.clearSelection();
            }
        }
    }
    
}
