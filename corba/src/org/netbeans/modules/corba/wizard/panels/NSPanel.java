/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2001 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.corba.wizard.panels;

import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeListener;
import org.openide.explorer.*;
import org.openide.explorer.view.*;
import org.openide.*;
import org.openide.nodes.*;
import org.netbeans.modules.corba.wizard.*;
/**
 *
 * @author  tzezula
 */
public class NSPanel extends AbstractWizardPanel implements PropertyChangeListener, VetoableChangeListener {

    BeanTreeView bt;
    
    /** Creates new form NSPanel */
    public NSPanel() {
        initComponents();
        this.bt = new BeanTreeView();
        this.explorer.add (bt);
        ExplorerManager manager = this.explorer.getExplorerManager();
        Node node = org.netbeans.modules.corba.browser.ns.ContextNode.getDefault();
        manager.setRootContext (node);
        manager.addPropertyChangeListener (this);
        manager.addVetoableChangeListener (this);
        this.contextName.setEditable (false);
    }
    
    
    public void readCorbaSettings (CorbaWizardData data) {
        try {
            Object node = data.getBindingDetails();
            if (node != null && node instanceof org.netbeans.modules.corba.browser.ns.ContextNode)
                this.explorer.getExplorerManager().setSelectedNodes(new Node[]{(Node)node});
        }catch (Exception e){}
    }
    
    public void storeCorbaSettings (CorbaWizardData data) {
        ExplorerManager manager = this.explorer.getExplorerManager();
        Node[] nodes = manager.getSelectedNodes();
        if (nodes != null && nodes.length == 1 && nodes[0] instanceof org.netbeans.modules.corba.browser.ns.ContextNode)
            data.setBindingDetails (nodes[0]);
    }

    public boolean isValid () {
	ExplorerManager manager = this.explorer.getExplorerManager();
	Node[] nodes = manager.getSelectedNodes();
	if (nodes == null)
	    return false;
	if (nodes.length != 1)
	    return false;
	return ((nodes[0] instanceof org.netbeans.modules.corba.browser.ns.ContextNode) && nodes[0] != org.netbeans.modules.corba.browser.ns.ContextNode.getDefault());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jLabel1 = new javax.swing.JLabel();
        explorer = new org.openide.explorer.ExplorerPanel();
        jLabel3 = new javax.swing.JLabel();
        contextName = new javax.swing.JTextField();
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        setPreferredSize(new java.awt.Dimension(500, 340));
        
        jLabel1.setText(bundle.getString("TXT_BindingMethodDetails"));
        jLabel1.setFont(new java.awt.Font ("Dialog", 0, 18));
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.insets = new java.awt.Insets(8, 8, 4, 8);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jLabel1, gridBagConstraints1);
        
        
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(4, 8, 4, 8);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        add(explorer, gridBagConstraints1);
        
        
        jLabel3.setText(bundle.getString("TXT_NamingContext"));
        jLabel3.setLabelFor(contextName);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(4, 8, 8, 4);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jLabel3, gridBagConstraints1);
        
        
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(4, 4, 8, 8);
        gridBagConstraints1.weightx = 1.0;
        add(contextName, gridBagConstraints1);
        
    }//GEN-END:initComponents

    public void propertyChange(java.beans.PropertyChangeEvent event) {
        Object newValue = event.getNewValue();
        if (newValue == null || ! (newValue instanceof Node[]))
            return;
        if (((Node[])newValue).length != 1)
            return;
        this.contextName.setText(((Node[])newValue)[0].getName());
        this.fireChange (this);
    }    

    public void vetoableChange(java.beans.PropertyChangeEvent event) throws java.beans.PropertyVetoException {
        Object newValue = event.getNewValue();
        if (newValue == null || ! (newValue instanceof Node[]))
            return;
        Node[] nodes = (Node[]) newValue;
        if (nodes.length != 1)
            throw new java.beans.PropertyVetoException ("",event);
        if (!(nodes[0] instanceof org.netbeans.modules.corba.browser.ns.ContextNode) || nodes[0]==org.netbeans.modules.corba.browser.ns.ContextNode.getDefault())
            throw new java.beans.PropertyVetoException ("",event);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private org.openide.explorer.ExplorerPanel explorer;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField contextName;
    // End of variables declaration//GEN-END:variables

    private static final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/wizard/panels/Bundle");    

}
