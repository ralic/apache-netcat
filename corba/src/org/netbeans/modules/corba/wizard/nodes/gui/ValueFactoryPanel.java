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

package org.netbeans.modules.corba.wizard.nodes.gui;

import java.util.StringTokenizer;
import org.netbeans.modules.corba.wizard.nodes.utils.IdlUtilities;
/**
 *
 * @author  tzezula
 */
public class ValueFactoryPanel extends ExPanel implements javax.swing.event.DocumentListener {

    /** Creates new form ValueFactoryPanel */
    public ValueFactoryPanel() {
        initComponents();
        this.name.getDocument().addDocumentListener (this);
        this.params.getDocument().addDocumentListener (this);
    }
    
    public String getName () {
        return this.name.getText().trim();
    }
    
    public void setName (String name) {
        this.name.setText(name);
    }
    
    public String getParams () {
        return this.params.getText().trim();
    }
    
    public void setParams (String params) {
        this.params.setText (params);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jLabel1 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        params = new javax.swing.JTextField();
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        setPreferredSize(new java.awt.Dimension(250, 60));
        
        jLabel1.setText(bundle.getString("TXT_ValueFactoryName"));
        jLabel1.setLabelFor(name);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.insets = new java.awt.Insets(8, 8, 4, 4);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jLabel1, gridBagConstraints1);
        
        
        name.setToolTipText(bundle.getString("TIP_ValueFactoryName"));
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(8, 4, 4, 8);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(name, gridBagConstraints1);
        
        
        jLabel2.setText(bundle.getString("TXT_ValueParams"));
        jLabel2.setLabelFor(params);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.insets = new java.awt.Insets(4, 8, 8, 4);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jLabel2, gridBagConstraints1);
        
        
        params.setToolTipText(bundle.getString("TIP_ValueFactoryParam"));
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(4, 4, 8, 8);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        add(params, gridBagConstraints1);
        
    }//GEN-END:initComponents

    
    public void changedUpdate(javax.swing.event.DocumentEvent event) {
        this.checkState();
    }
    
    public void removeUpdate(javax.swing.event.DocumentEvent event) {
        this.checkState();
    }
    
    public void insertUpdate(javax.swing.event.DocumentEvent event) {
        this.checkState();
    }
    
    private void checkState () {
        if (IdlUtilities.isValidIDLIdentifier(this.name.getText()) && isArgValid())
            enableOk();
        else 
            disableOk();
    }
    
    private boolean isArgValid () {
        String arg = this.params.getText();
        if (arg.length() == 0)
            return true;
        if (arg.endsWith(","))
            return false;
        StringTokenizer tk = new StringTokenizer (arg,",");
        while (tk.hasMoreTokens()) {
            String token = tk.nextToken().trim();
            int state = 0;
            int startIndex = 0;
            String modifier = null;
            String type = null;
            String name = null;
            int i;
            for (i=0; i< token.length(); i++) {
                switch (state) {
                    case 0:  // modifier
                        if (token.charAt(i)== ' ' || token.charAt(i) == '\t') {
                            modifier = token.substring (startIndex, i);
                            state++;
                        }
                        break;
                    case 1: // skeep spaces
                        if (token.charAt(i)!= ' ' && token.charAt(i) != '\t') {
                            startIndex = i;
                            state++;
                        }
                        break;
                    case 2: // parameter type
                        if (token.charAt(i) == ' ' || token.charAt(i) == '\t') {
                            type = token.substring (startIndex, i);
                            state++;
                        }
                        break;
                    case 3: // Skeep spaces
                        if (token.charAt(i) != ' ' && token.charAt(i) != '\t') {
                            startIndex = i;
                            state++;
                        }
                        break;
                    case 4: // parameter name
                        if (token.charAt (i) == ' ' || token.charAt(i) == '\t') {
                            return false;
                        }
                        break;
                }
            }
            if (state != 4)
                return false;
            name = token.substring (startIndex,i);
            if (!modifier.equals("in")) // NO I18N
                return false;
        }
        return true;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField name;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField params;
    // End of variables declaration//GEN-END:variables

    private static final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/wizard/nodes/gui/Bundle");    

}
