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
package beans2nbm.ui;

import java.awt.Component;
import java.io.File;
import java.util.prefs.Preferences;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.JTextComponent;
import org.netbeans.spi.wizard.WizardPage;

/**
 *
 * @author  Tim Boudreau
 */
public class LocateJarPage extends WizardPage {

    /** Creates new form LocateJarPanel */
    public LocateJarPage() {
        super (true);
        initComponents();
    }

public static String getStep() {
        return "locateJar";
    }
    
    public static String getDescription() {
        return "Locate JAR";
    }
    
    public void addNotify() {
        super.addNotify();
        userInputReceived(jTextField1, null);
        JDialog dlg = (JDialog) SwingUtilities.getAncestorOfClass(JDialog.class, this);
        if (dlg != null) {
            dlg.setTitle ("BeanNetter - the NetBeans Module Generator");
        }
    }

    protected String validateContents(Component component, Object event) {
        String s = jTextField1.getText();
        if (s.trim().length() == 0) {
            return "Type path to or browse for a JAR file";
        }
        File f = new File (s);
        if (!f.exists()) {
            return s + " does not exist";
        } else if (!f.getName().endsWith(".jar")) {
            return s + " is not a jar file";
        }
        if (!f.canRead()) {
            return "Cannot read " + f.getName();
        }
        s = jTextField2.getText();
        f = new File (s);
        if (!"".equals(s) && f.exists() && !f.canRead()) {
            return "Cannot read " + f.getName();
        }
        if (!"".equals(s) && !f.isFile()) {
            return f.getName() + " is not a file";
        }
        s = jTextField3.getText();
        if (!"".equals(s) && f.exists() && !f.canRead()) {
            return "Cannot read " + f.getName();
        }
        if (!"".equals(s) && !f.isFile()) {
            return f.getName() + " is not a file";
        }
        return null;
    }
    
    private JFileChooser jfc;
    public static final String KEY_PATH = "LastKnownPath";
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        browseForJarButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        browseForSourcesButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        browseForJavadocButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        jLabel1.setText("Jar File");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 5);
        add(jLabel1, gridBagConstraints);

        jTextField1.setName("jarFileName");
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.weightx = 1.0;
        add(jTextField1, gridBagConstraints);

        browseForJarButton.setText("Browse");
        browseForJarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseForJarButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        add(browseForJarButton, gridBagConstraints);

        jLabel2.setText("Sources JAR/Zip (optional)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 5);
        add(jLabel2, gridBagConstraints);

        jTextField2.setName("sourceJar");
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.weightx = 1.0;
        add(jTextField2, gridBagConstraints);

        browseForSourcesButton.setText("Browse");
        browseForSourcesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseForJarButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        add(browseForSourcesButton, gridBagConstraints);

        jLabel3.setText("Javadoc JAR/Zip (optional)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 5);
        add(jLabel3, gridBagConstraints);

        jTextField3.setName("docsJar");
        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.weightx = 1.0;
        add(jTextField3, gridBagConstraints);

        browseForJavadocButton.setText("Browse");
        browseForJavadocButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseForJarButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        add(browseForJavadocButton, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        JTextComponent jtc = (JTextComponent) evt.getSource();
        jtc.selectAll();
    }//GEN-LAST:event_jTextField1FocusGained

    private void browseForJarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseForJarButtonActionPerformed
        if (jfc == null) {
            jfc = new JFileChooser();
            String dir = Preferences.userNodeForPackage(getClass()).get(
                    KEY_PATH, null);
            jfc.setFileHidingEnabled(true);
            jfc.setFileFilter(new FF());
            if (dir != null) {
                File f = new File (dir);
                if (f.exists() && f.isDirectory()) {
                    jfc.setCurrentDirectory(f);
                }
            }
        }
        if (jfc.showOpenDialog(this) == jfc.APPROVE_OPTION) {
            File sel = jfc.getSelectedFile();
            if (evt.getSource() == browseForJarButton) {
                setFile (sel);
            } else if (evt.getSource() == browseForSourcesButton) {
                setSourceFile (sel);
            } else {
                setDocsFile (sel);
            }
            if (sel.getParent() != null) {
                Preferences.userNodeForPackage(getClass()).put(
                        KEY_PATH, sel.getParent());
            }
        }
    }//GEN-LAST:event_browseForJarButtonActionPerformed

    private void setFile (File f) {
        jTextField1.setText (f.getPath());
    }
    
    private void setSourceFile (File f) {
        jTextField2.setText (f.getPath());
    }
    
    private void setDocsFile (File f) {
        jTextField3.setText (f.getPath());
    }
    
    private static final class FF extends FileFilter {
        public String getDescription() {
            return "JAR Files";
        }
        
        public boolean accept (File f) {
            return f.isDirectory() || (f.exists() && f.isFile() && (f.getName().endsWith(".jar") 
                || f.getName().endsWith(".JAR") || f.getName().endsWith(".zip") || f.getName().endsWith(".ZIP")));
        }
    }
    
    public void requestFocus() {
        browseForJarButton.requestFocus();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseForJarButton;
    private javax.swing.JButton browseForJavadocButton;
    private javax.swing.JButton browseForSourcesButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
    
}
