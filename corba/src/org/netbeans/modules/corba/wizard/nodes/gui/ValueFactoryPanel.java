/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
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

package org.netbeans.modules.corba.wizard.nodes.gui;

import java.util.StringTokenizer;
import org.netbeans.modules.corba.wizard.nodes.utils.IdlUtilities;
/**
 *
 * @author  Tomas Zezula
 */
public class ValueFactoryPanel extends ExPanel implements javax.swing.event.DocumentListener {

    /** Creates new form ValueFactoryPanel */
    public ValueFactoryPanel() {
        initComponents();
        this.name.getDocument().addDocumentListener (this);
        this.params.getDocument().addDocumentListener (this);
        this.jLabel1.setDisplayedMnemonic (this.bundle.getString("TXT_ValueFactoryName_MNE").charAt(0));
        this.jLabel2.setDisplayedMnemonic (this.bundle.getString("TXT_ValueParams_MNE").charAt(0));
        this.getAccessibleContext().setAccessibleDescription(this.bundle.getString("AD_ValueFactoryPanel"));
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
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        params = new javax.swing.JTextField();

        setLayout(new java.awt.GridBagLayout());

        setPreferredSize(new java.awt.Dimension(250, 60));
        jLabel1.setText(bundle.getString("TXT_ValueFactoryName"));
        jLabel1.setLabelFor(name);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 4, 4);
        add(jLabel1, gridBagConstraints);

        name.setToolTipText(bundle.getString("TIP_ValueFactoryName"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 4, 4, 8);
        add(name, gridBagConstraints);

        jLabel2.setText(bundle.getString("TXT_ValueParams"));
        jLabel2.setLabelFor(params);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 8, 8, 4);
        add(jLabel2, gridBagConstraints);

        params.setToolTipText(bundle.getString("TIP_ValueFactoryParam"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 8, 8);
        add(params, gridBagConstraints);

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
    private javax.swing.JTextField params;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField name;
    // End of variables declaration//GEN-END:variables

    private static final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/netbeans/modules/corba/wizard/nodes/gui/Bundle");    

}
