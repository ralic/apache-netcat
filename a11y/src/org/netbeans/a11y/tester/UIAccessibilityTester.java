/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2002 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.a11y.tester;

import org.netbeans.a11y.*;
import org.netbeans.a11y.ui.AccessibilityPanel;
import org.netbeans.modules.a11y.AccessibilityTesterRunnable;
import javax.swing.SwingUtilities;

/** A dialog to customize and run the accessibility test.
 *  @author  Marian.Mirilovic@sun.com
 */
public class UIAccessibilityTester extends javax.swing.JFrame {
    
    /** Version of UIAccessibilityTester. */
    public String versionID = "2.0";
    
    
    private static UIAccessibilityTester instance;                                                                                                                                
    
    /** Accessibility Panel that contains UI for set test settings. */
    private static AccessibilityPanel accPanel;
    
    /** Settings for testing. */ 
    private TestSettings testSettings;
    
    /** Test thread. */
    private Thread thread;
    
    
    /** Creates new form UIAccessibilityTester */
    public UIAccessibilityTester() {
        super();
        
        initComponents();
        
        getAccessibleContext().setAccessibleDescription("Tool for testing Accessibility of focused Window {Frame or Dialog}.");
        
        accPanel = new AccessibilityPanel();
        getContentPane().add(accPanel, java.awt.BorderLayout.CENTER);
        pack();
        
        this.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent e){
                if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE ){
                    closeDialog();
                }
            }
        });
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                show();
            }
        });
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        results = new javax.swing.ButtonGroup();
        uiTestPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        runTestToggleButton = new javax.swing.JToggleButton();
        cancelButton = new javax.swing.JButton();
        closePanel = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.BorderLayout(10, 0));

        setTitle("UI Accessibility Tester v. " + versionID);
        uiTestPanel.setLayout(new java.awt.GridLayout(1, 1));

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.X_AXIS));

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 5));

        runTestToggleButton.setMnemonic('r');
        runTestToggleButton.setText("Run test");
        runTestToggleButton.setToolTipText("Run test");
        runTestToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runTestToggleButtonActionPerformed(evt);
            }
        });

        jPanel1.add(runTestToggleButton);

        cancelButton.setMnemonic('c');
        cancelButton.setText("Cancel");
        cancelButton.setToolTipText("Cancel test");
        cancelButton.setEnabled(false);
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jPanel1.add(cancelButton);

        jPanel2.add(jPanel1);

        closePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 5));

        closeButton.setText("Close");
        closeButton.setToolTipText("Closing UIAccessibilityTester");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        closePanel.add(closeButton);

        jPanel2.add(closePanel);

        uiTestPanel.add(jPanel2);

        getContentPane().add(uiTestPanel, java.awt.BorderLayout.NORTH);

    }//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        closeDialog();
    }//GEN-LAST:event_closeButtonActionPerformed
    
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        threadInterrupt();
        runTestToggleButton.setSelected(false);
    }//GEN-LAST:event_cancelButtonActionPerformed
    
    private void runTestToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runTestToggleButtonActionPerformed
        if(runTestToggleButton.isSelected()) {
            threadInterrupt();            
            cancelButton.setEnabled(true);
            accPanel.setStatusText("Use Ctrl-F11 key to test current focused component (Frame, Dialog).");
            thread = new Thread(new AccessibilityTesterRunnable(accPanel));
            thread.start();
        }else {
            cancelButton.setEnabled(false);
            threadInterrupt();        
        }
    }//GEN-LAST:event_runTestToggleButtonActionPerformed
    
    
    /** Interrupt thread */
    private void threadInterrupt() {
        if (thread!=null) {
            thread.interrupt();
            thread=null;
        }
    }
    
    /** Closes the dialog */
    private void closeDialog() {
        threadInterrupt();
        setVisible(false);
        dispose();
    }

    
    /** Return instance, if it doesn't exist create it.
     * @return instance of A11YTesterTopComponent */    
    public static UIAccessibilityTester getInstance() {                                                                                                                           
        if (instance == null)
            instance = new UIAccessibilityTester();
        return instance;
    }

    
    
    /** Test could be executed internaly in Forte, with mounted jemmy.jar, jelly.jar and a11y.jar
     * @param args arguments from command line */
    public static void main(String[] args) {
        
        System.setProperty("a11ytest.log", "false");
        
        if(args.length==1) {
            String ar0 = args[0];
            if(ar0.equals("-log"))
                System.setProperty("a11ytest.log", "true");
            else
                System.out.println("Argument :"+args[0]+" isn't correct [-log] - if you want see debug messages.");
        }
        
        new UIAccessibilityTester().show();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.ButtonGroup results;
    private javax.swing.JPanel closePanel;
    private javax.swing.JPanel uiTestPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JToggleButton runTestToggleButton;
    // End of variables declaration//GEN-END:variables
    
}
