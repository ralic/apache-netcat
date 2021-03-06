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

package org.netbeans.modules.portalpack.portlets.genericportlets.ddapi.impl.sun.ui;

import org.netbeans.modules.portalpack.portlets.genericportlets.ddapi.eventing.EventObject;

/**
 *
 * @author  Satyaranjan
 */
public class ConsumeEventDialog extends javax.swing.JDialog {

    private boolean isCancelled = false;
    private EventObject event;
    /** Creates new form ConsumeEventDialog */
    public ConsumeEventDialog(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        setLocation(parent.getX()+(parent.getWidth()-getWidth())/2,parent.getY()+(parent.getHeight()-getHeight())/2);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        methodNameLabel = new javax.swing.JLabel();
        methodNameTf = new javax.swing.JTextField();
        eventNameLabel = new javax.swing.JLabel();
        eventNameTf = new javax.swing.JTextField();
        portletNameLabel = new javax.swing.JLabel();
        portletNameTf = new javax.swing.JTextField();
        modifiedJavaLabel = new javax.swing.JLabel();
        modifiedJavaTf = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(ConsumeEventDialog.class, "ConsumeEventDialog.title")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(ConsumeEventDialog.class, "ConsumeEventDialog.generateSourcePanel.border.title"))); // NOI18N

        methodNameLabel.setText(org.openide.util.NbBundle.getMessage(ConsumeEventDialog.class, "LBL_MethodName")); // NOI18N

        eventNameLabel.setText(org.openide.util.NbBundle.getMessage(ConsumeEventDialog.class, "LBL_Event_Name")); // NOI18N

        eventNameTf.setEnabled(false);

        portletNameLabel.setText(org.openide.util.NbBundle.getMessage(ConsumeEventDialog.class, "LBL_Portlet_Name")); // NOI18N

        portletNameTf.setEnabled(false);

        modifiedJavaLabel.setText(org.openide.util.NbBundle.getMessage(ConsumeEventDialog.class, "LBL_Modified_Java_File")); // NOI18N

        modifiedJavaTf.setEnabled(false);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(methodNameLabel)
                    .add(eventNameLabel)
                    .add(portletNameLabel)
                    .add(modifiedJavaLabel))
                .add(19, 19, 19)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, portletNameTf, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .add(eventNameTf, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .add(methodNameTf, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .add(modifiedJavaTf, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(methodNameLabel)
                    .add(methodNameTf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(eventNameLabel)
                    .add(eventNameTf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(25, 25, 25)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(portletNameLabel)
                    .add(portletNameTf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(24, 24, 24)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(modifiedJavaLabel)
                    .add(modifiedJavaTf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        okButton.setText(org.openide.util.NbBundle.getMessage(ConsumeEventDialog.class, "LBL_Ok")); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText(org.openide.util.NbBundle.getMessage(ConsumeEventDialog.class, "LBL_Cancel")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(okButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 65, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cancelButton)
                        .add(13, 13, 13))))
        );

        layout.linkSize(new java.awt.Component[] {cancelButton, okButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cancelButton)
                    .add(okButton))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(ConsumeEventDialog.class, "ACC_Generate_Code")); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
// TODO add your handling code here:
    isCancelled = true;
    setVisible(false);
    dispose();
}//GEN-LAST:event_cancelButtonActionPerformed

private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
// TODO add your handling code here:
    setVisible(false);
    dispose();
}//GEN-LAST:event_okButtonActionPerformed

    public boolean isCancelled()
    {
        return isCancelled;
    }
    public void setPortletName(String portletName)
    {
        portletNameTf.setText(portletName);
    }
    public void setJavaSourceName(String javafile)
    {
        modifiedJavaTf.setText(javafile);
        modifiedJavaTf.setToolTipText(javafile);
    }
    public void setEvent(EventObject event)
    {
        this.event = event;
        if(event.getQName() != null)
            eventNameTf.setText(event.getQName().toString());
        else
            eventNameTf.setText(event.getName());
    }
    public void setSuggestedMethodName(String suggestedMethod)
    {
        methodNameTf.setText(suggestedMethod);
    }

    public String getPortletName()
    {
        return portletNameTf.getText();
    }
    public String getJavaSourceName()
    {
        return modifiedJavaTf.getText();
    }
    public EventObject getEvent()
    {
        return event;
    }
    public String getSuggestedMethodName()
    {
        return methodNameTf.getText();
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel eventNameLabel;
    private javax.swing.JTextField eventNameTf;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel methodNameLabel;
    private javax.swing.JTextField methodNameTf;
    private javax.swing.JLabel modifiedJavaLabel;
    private javax.swing.JTextField modifiedJavaTf;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel portletNameLabel;
    private javax.swing.JTextField portletNameTf;
    // End of variables declaration//GEN-END:variables

}
