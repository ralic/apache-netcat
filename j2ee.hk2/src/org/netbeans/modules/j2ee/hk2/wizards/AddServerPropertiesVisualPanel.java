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
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
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

package org.netbeans.modules.j2ee.hk2.wizards;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.openide.util.NbBundle;

/**
 *
 * @author  pblaha
 */
public class AddServerPropertiesVisualPanel extends javax.swing.JPanel {
    
    private String j2eeLocalHome;
    private final List <ChangeListener> listeners = new ArrayList<ChangeListener>();
    
    /** Creates new form AddServerPropertiesVisualPanel1 */
    public AddServerPropertiesVisualPanel(String j2eeLocalHome) {
        this.j2eeLocalHome = j2eeLocalHome;
        
        setName(NbBundle.getMessage(AddServerLocationVisualPanel.class, "TITLE_Properties"));
        
        initComponents();
        
        DocumentListener changeListener = new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                fireChange();
            }
            
            public void removeUpdate(DocumentEvent e) {
                fireChange();
            }
            
            public void insertUpdate(DocumentEvent e) {
                fireChange();
            }
        };
        
        adminPortTxt.getDocument().addDocumentListener(changeListener);
        portTxt.getDocument().addDocumentListener(changeListener);
        passwordTxt.getDocument().addDocumentListener(changeListener);
        
        setInitValues();
    }
    

    
    public void addChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.add(l);
        }
    }
    
    public void removeChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.remove(l);
        }
    }
    
    private void fireChange() {
        Iterator it;
        synchronized (listeners) {
            it = new HashSet<ChangeListener>(listeners).iterator();
        }
        ChangeEvent ev = new ChangeEvent(this);
        while (it.hasNext()) {
            ((ChangeListener)it.next()).stateChanged(ev);
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        userLbl = new javax.swing.JLabel();
        passwordLbl = new javax.swing.JLabel();
        passwordTxt = new javax.swing.JPasswordField();
        adminPortLbl = new javax.swing.JLabel();
        adminPortTxt = new javax.swing.JTextField();
        portLbl = new javax.swing.JLabel();
        portTxt = new javax.swing.JTextField();
        userName = new javax.swing.JTextField();

        getAccessibleContext().setAccessibleName(null);
        getAccessibleContext().setAccessibleDescription(null);
        userLbl.setLabelFor(userName);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/netbeans/modules/j2ee/hk2/wizards/Bundle"); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(userLbl, bundle.getString("LBL_USER")); // NOI18N

        passwordLbl.setLabelFor(passwordTxt);
        org.openide.awt.Mnemonics.setLocalizedText(passwordLbl, bundle.getString("LBL")); // NOI18N

        passwordTxt.getAccessibleContext().setAccessibleName(null);
        passwordTxt.getAccessibleContext().setAccessibleDescription(null);

        adminPortLbl.setLabelFor(adminPortTxt);
        org.openide.awt.Mnemonics.setLocalizedText(adminPortLbl, bundle.getString("LBL_ADMIN_PORT")); // NOI18N

        adminPortTxt.getAccessibleContext().setAccessibleName(null);
        adminPortTxt.getAccessibleContext().setAccessibleDescription(null);

        portLbl.setLabelFor(portTxt);
        org.openide.awt.Mnemonics.setLocalizedText(portLbl, bundle.getString("LBL_HTTP_PORT")); // NOI18N

        portTxt.getAccessibleContext().setAccessibleName(null);
        portTxt.getAccessibleContext().setAccessibleDescription(null);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(userLbl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 73, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(passwordLbl)
                    .add(adminPortLbl)
                    .add(portLbl))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(portTxt)
                    .add(adminPortTxt)
                    .add(passwordTxt)
                    .add(userName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(26, 26, 26)
                        .add(userLbl))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(userName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(passwordLbl)
                    .add(passwordTxt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(adminPortLbl)
                    .add(adminPortTxt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(portLbl)
                    .add(portTxt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(102, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
public String getAdminPort() {
    return adminPortTxt.getText().trim();
}

public String getPassword() {
    return new String(passwordTxt.getPassword());
}

public String getUser() {
    return userName.getText().trim();
}

public String getPort() {
    return portTxt.getText().trim();
}



private void setInitValues() {
    portTxt.setText("8080");
    adminPortTxt.setText("4848");
    userName.setText("admin");
    passwordTxt.setText("adminadmin");
    

    
 }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adminPortLbl;
    private javax.swing.JTextField adminPortTxt;
    private javax.swing.JLabel passwordLbl;
    private javax.swing.JPasswordField passwordTxt;
    private javax.swing.JLabel portLbl;
    private javax.swing.JTextField portTxt;
    private javax.swing.JLabel userLbl;
    private javax.swing.JTextField userName;
    // End of variables declaration//GEN-END:variables
    
}