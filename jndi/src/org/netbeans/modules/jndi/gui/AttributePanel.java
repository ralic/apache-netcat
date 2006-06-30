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

package org.netbeans.modules.jndi.gui;

import java.awt.Dialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.StringTokenizer;
import javax.naming.directory.DirContext;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.CompositeName;
import javax.naming.NamingException;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import org.openide.TopManager;
import org.openide.DialogDescriptor;
import org.netbeans.modules.jndi.JndiRootNode;
import org.netbeans.modules.jndi.JndiNode;
import org.netbeans.modules.jndi.utils.SimpleListModel;
import org.netbeans.modules.jndi.utils.ExtAttribute;
/**
 * This class represents the Customizer for properties of jndi objects
 * @author  tzezula
 * @version
 */
public class AttributePanel extends javax.swing.JPanel implements ListSelectionListener, ActionListener {
    
    private Dialog dlg;
    private DirContext ctx;
    private CompositeName offset;
    private SimpleListModel model;
    private JndiNode owner;
    
    /** Creates new form AttributePanel */
    public AttributePanel(DirContext ctx, CompositeName offset, JndiNode owner) {
        this.ctx=ctx;
        this.offset = offset;
        this.owner = owner;
        initComponents();
        this.attrList.setCellRenderer ( new AttributeCellRenderer ());
        this.getAccessibleContext().setAccessibleDescription(JndiRootNode.getLocalizedString("AD_AttributePanel"));
        jLabel1.setDisplayedMnemonic(JndiRootNode.getLocalizedString("TXT_AttributeList_MNEM").charAt(0));
        addButton.addActionListener(this);
        addButton.getAccessibleContext().setAccessibleDescription(JndiRootNode.getLocalizedString("AD_AddAttribute"));
        removeButton.setEnabled(false);
        removeButton.addActionListener(this);
        removeButton.getAccessibleContext().setAccessibleDescription(JndiRootNode.getLocalizedString("AD_RemoveAttribute"));
        editButton.setEnabled(false);
        editButton.addActionListener(this);
        editButton.getAccessibleContext().setAccessibleDescription(JndiRootNode.getLocalizedString("AD_ModifyAttribute"));
        attrList.addListSelectionListener(this);
        model = new SimpleListModel();
        attrList.setPrototypeCellValue("012345678901234567890123456789");
        attrList.setModel(model);
        attrList.getAccessibleContext().setAccessibleDescription(JndiRootNode.getLocalizedString("AD_AttributeList"));
        this.attrList.requestFocus();
        initData();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        attrList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        addButton.setMnemonic(JndiRootNode.getLocalizedString("TXT_AddAttribute_MNEM").charAt(0));
        addButton.setText(JndiRootNode.getLocalizedString("TXT_AddAttribute"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 4, 8);
        jPanel1.add(addButton, gridBagConstraints);

        removeButton.setMnemonic(JndiRootNode.getLocalizedString ("TXT_RemoveAttribute_MNEM").charAt(0));
        removeButton.setText(JndiRootNode.getLocalizedString("TXT_RemoveAttribute"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 8, 4, 8);
        jPanel1.add(removeButton, gridBagConstraints);

        editButton.setMnemonic(JndiRootNode.getLocalizedString("TXT_ModifyAttribute_MNEM").charAt(0));
        editButton.setText(JndiRootNode.getLocalizedString("TXT_ModifyAttribute"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 8, 8, 8);
        jPanel1.add(editButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jPanel1, gridBagConstraints);

        attrList.setValueIsAdjusting(true);
        jScrollPane1.setViewportView(attrList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 0.8;
        gridBagConstraints.insets = new java.awt.Insets(4, 8, 8, 0);
        add(jScrollPane1, gridBagConstraints);

        jLabel1.setText(JndiRootNode.getLocalizedString("TXT_AttributeList"));
        jLabel1.setLabelFor(attrList);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 4, 8);
        add(jLabel1, gridBagConstraints);

    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList attrList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton editButton;
    private javax.swing.JButton removeButton;
    // End of variables declaration//GEN-END:variables
    
    /** Sets the data
     */
    private void initData(){
        try{
            Attributes attrs = this.ctx.getAttributes(this.offset);
            java.util.Enumeration enum = attrs.getAll();
            while (enum.hasMoreElements()) {
                this.model.addElement(enum.nextElement());
            }
        }catch(NamingException ne){}
    }
    
    /** Context sensitive button handling
     *  @param ListSelectionEvent event
     */
    public void valueChanged(final javax.swing.event.ListSelectionEvent event) {
        if (this.attrList.getSelectedIndex()!=-1){
            this.removeButton.setEnabled(true);
            this.editButton.setEnabled(true);
        }
        else{
            this.removeButton.setEnabled(false);
            this.editButton.setEnabled(false);
        }
    }
    
    /** Action performed
     *  @param ActionEvent event
     */
    public void actionPerformed(final ActionEvent event){
        if (event.getSource() == this.addButton){
            final CreateAttributePanel p = new CreateAttributePanel();
            ExtAttribute attr = new ExtAttribute();
            p.setModel(attr);
            DialogDescriptor dd = new DialogDescriptor(p,JndiRootNode.getLocalizedString("TITLE_CreateAttribute"));
            dlg = TopManager.getDefault().createDialog(dd);
            dlg.setVisible(true);
            if (dd.getValue() == DialogDescriptor.OK_OPTION) {
                try{
                    p.updateData ();
                    BasicAttributes attrs = new BasicAttributes();
                    attrs.put(attr);
                    AttributePanel.this.ctx.modifyAttributes(AttributePanel.this.offset,DirContext.ADD_ATTRIBUTE,attrs);
                    AttributePanel.this.model.addElement(attr);
                    AttributePanel.this.owner.updateData();
                }catch(NamingException ne){
                    JndiRootNode.notifyForeignException(ne);
                }
            }
            dlg.dispose ();
        }
        else if (event.getSource() == this.removeButton) {
            Attribute attr = (Attribute) this.attrList.getSelectedValue();
            if (attr != null) {
                try {
                    BasicAttributes attrs = new BasicAttributes();
                    attrs.put(attr);
                    this.ctx.modifyAttributes(this.offset,DirContext.REMOVE_ATTRIBUTE,attrs);
                    AttributePanel.this.model.removeElementAt(AttributePanel.this.attrList.getSelectedIndex());
                    AttributePanel.this.owner.updateData();
                } catch(NamingException ne) {
                    JndiRootNode.notifyForeignException(ne);
                }
            }
        }
        else if (event.getSource() == this.editButton) {
            final CreateAttributePanel p = new CreateAttributePanel();
            
            Attribute oldAttr = (Attribute) AttributePanel.this.attrList.getSelectedValue();
            if (oldAttr != null){
                ExtAttribute attr = null;
		try {
		    attr = new ExtAttribute(oldAttr);
		} catch (NamingException ne) {
		}
                p.setModel(attr);
                DialogDescriptor dd = new DialogDescriptor(p,JndiRootNode.getLocalizedString("TITLE_ModifyAttribute"));
                dlg = TopManager.getDefault().createDialog(dd);
                dlg.setVisible(true);
                if (dd.getValue() == DialogDescriptor.OK_OPTION) {
                    try{
                        p.updateData ();
                        BasicAttributes attrs;
                        if (!oldAttr.getID().equals(attr.getID())){
                            attrs = new BasicAttributes();
                            attrs.put(oldAttr);
                            AttributePanel.this.ctx.modifyAttributes(AttributePanel.this.offset,DirContext.REMOVE_ATTRIBUTE,attrs);
                        }
                        attrs = new BasicAttributes();
                        attrs.put(attr);
                        AttributePanel.this.ctx.modifyAttributes (AttributePanel.this.offset,DirContext.REPLACE_ATTRIBUTE,attrs);
                        AttributePanel.this.model.changeElementAt (AttributePanel.this.attrList.getSelectedIndex(),attr);
                        AttributePanel.this.owner.updateData ();
                    }catch (NamingException ne) {
                        JndiRootNode.notifyForeignException (ne);
                    }
                }
                dlg.dispose ();
            }
            
        }
    }
}
