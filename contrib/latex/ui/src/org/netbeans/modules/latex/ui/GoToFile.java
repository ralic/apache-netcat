/*
 *                          Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License Version
 * 1.0 (the "License"). You may not use this file except in compliance with
 * the License. A copy of the License is available at http://www.sun.com/
 *
 * The Original Code is the DocSup module.
 * The Initial Developer of the Original Code is Jan Lahoda.
 * Portions created by Jan Lahoda_ are Copyright (C) 2002,2003.
 * All Rights Reserved.
 *
 * Contributor(s): Jan Lahoda.
 */
package org.netbeans.modules.latex.ui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import org.netbeans.modules.latex.model.command.LaTeXSourceFactory;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;

/**
 *
 * @author Jan Lahoda
 */
public class GoToFile extends JPanel implements DocumentListener {
    
    /** Creates new form GoToFile */
    public GoToFile() {
        initComponents();
//        jTextField1.getDocument().addDocumentListener(this);
        jTextField1.addKeyListener(
            new KeyListener() {
                public void keyPressed(KeyEvent evt) {
                    if (!checkArrows(evt)) {
                        change();
                    }
                }
                public void keyReleased(KeyEvent evt) {
                    if (!isArrows(evt)) {
                        change();
                    }
                }
                public void keyTyped(KeyEvent evt) {
                    if (!isArrows(evt)) {
                        change();
                    }
                }
                
                private boolean isArrows(KeyEvent evt) {
                    return boundScrollingKey(evt);
                }
                
                private boolean checkArrows(KeyEvent evt) {
                    if (isArrows(evt)) {
                        delegateScrollingKey(evt);
                        evt.consume();
                        return true;
                    } else {
                        return false;
                    }
               }

                private void change() {
                    GoToFile.this.update();
                }
                
                boolean boundScrollingKey(KeyEvent ev) {
                    String action = listActionFor(ev);
                    // See BasicListUI, MetalLookAndFeel:
                    return "selectPreviousRow".equals(action) || // NOI18N
                    "selectNextRow".equals(action) || // NOI18N
                    "selectFirstRow".equals(action) || // NOI18N
                    "selectLastRow".equals(action) || // NOI18N
                    "scrollUp".equals(action) || // NOI18N
                    "scrollDown".equals(action); // NOI18N
                }
                
                void delegateScrollingKey(KeyEvent ev) {
                    String action = listActionFor(ev);
                    Action a = jList1.getActionMap().get(action);
                    if (a != null) {
                        a.actionPerformed(new ActionEvent(jList1, 0, action));
                    }
                }
                
                private String listActionFor(KeyEvent ev) {
                    InputMap map = jList1.getInputMap();
                    Object o = map.get(KeyStroke.getKeyStrokeForEvent(ev));
                    if (o instanceof String) {
                        return (String)o;
                    } else {
                        return null;
                    }
                }
        });
        update();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jTextField1.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanel1.add(jTextField1, gridBagConstraints);

        jLabel1.setLabelFor(jTextField1);
        jLabel1.setText(java.util.ResourceBundle.getBundle("org/netbeans/modules/latex/ui/Bundle").getString("LBL_documentName"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        jPanel1.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 6, 11);
        add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setLabelFor(jList1);
        jLabel2.setText(java.util.ResourceBundle.getBundle("org/netbeans/modules/latex/ui/Bundle").getString("LBL_matchingDocuments"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        jPanel2.add(jLabel2, gridBagConstraints);

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 0, 11);
        add(jPanel2, gridBagConstraints);

    }//GEN-END:initComponents

    public void changedUpdate(DocumentEvent e) {
        //should be safe to ignore...
    }    
    
    public void insertUpdate(DocumentEvent e) {
        update();
    }    
    
    public void removeUpdate(DocumentEvent e) {
        update();
    }
    
    private String lastPrefix = null;
    
    private void update() {
        String prefix = jTextField1.getText();
        
        if (prefix.equals(lastPrefix))
            return ;
        
        lastPrefix = prefix;
        
        Lookup.Result factories = Lookup.getDefault().lookup(new Lookup.Template(LaTeXSourceFactory.class));
        
        List result = new ArrayList();
        
        for (Iterator factoryIterator = factories.allInstances().iterator(); factoryIterator.hasNext(); ) {
            LaTeXSourceFactory factImpl = (LaTeXSourceFactory) factoryIterator.next();
            Collection files = factImpl.getAllKnownFiles();
            Iterator iter = files.iterator();
            
            while (iter.hasNext()) {
                ListEntry entry = new ListEntry((FileObject) iter.next());
                
                if (entry.getName().startsWith(prefix))
                    result.add(entry);
            }
        }
        
        Collections.sort(result);
        
        jList1.setListData(result.toArray());
        
        if (result.size() > 0) {
            jList1.setSelectedIndex(0);
        }
    }
    
    private static class ListEntry implements Comparable {
        private FileObject file; //!!!
        
        public ListEntry(FileObject file) {
            this.file = file;
        }
        
        public FileObject getFile() {
            return file;
        }
        
        public String getName() {
            return file.getName();
        }
        
        public String toString() {
            return getName();
        }
        
        public int compareTo(Object o) {
            return getName().compareTo(((ListEntry) o).getName());
        }
        
    }
    
    public FileObject getSelectedFile() {
        ListEntry entry = (ListEntry) jList1.getSelectedValue();
        
        if (entry == null)
            return null;
        
        return entry.getFile();
    }
    
    public void addListSelectionListener(ListSelectionListener l) {
        jList1.addListSelectionListener(l);
    }
    
    public void removeListSelectionListener(ListSelectionListener l) {
        jList1.removeListSelectionListener(l);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    
}
