/*
*                 Sun Public License Notice
*
* The contents of this file are subject to the Sun Public License
* Version 1.0 (the "License"). You may not use this file except in
* compliance with the License. A copy of the License is available at
* http://www.sun.com/
*
* The Original Code is NetBeans. The Initial Developer of the Original
* Code is Sun Microsystems, Inc. Portions Copyright 1997-2004 Sun
* Microsystems, Inc. All Rights Reserved.
*/
/*
 * BundlizerPanel.java
 *
 * Created on April 30, 2004, 1:43 PM
 */

package org.netbeans.modules.bundlizer;

import java.awt.Cursor;
import java.awt.Dimension;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
//import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * A quick'n'dirty resource bundle management tool
 *
 * @author  Tim Boudreau
 */
public class BundlizerPanel extends javax.swing.JPanel {
    
    /** Creates new form BundlizerPanel */
    public BundlizerPanel() {
        initComponents();
        jSplitPane1.setDividerLocation(0.5f);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        loadButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        statusbar = new javax.swing.JPanel();
        status = new javax.swing.JLabel();
        progress = new javax.swing.JProgressBar();
        jSplitPane1 = new javax.swing.JSplitPane();
        keysScrollPane = new javax.swing.JScrollPane();
        keysTable = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        content = new javax.swing.JTextArea();
        jCheckBox1 = new javax.swing.JCheckBox();

        setLayout(new java.awt.GridBagLayout());

        loadButton.setText("Find a bundle");
        loadButton.setToolTipText("<html>Open a file chooser to select a <code>.properties</code> file.  All java files in <br>the directory will be scanned for matches against the keys in the properties file.</html>");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 37;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(loadButton, gridBagConstraints);

        saveButton.setText("Save changes");
        saveButton.setEnabled(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 68;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(saveButton, gridBagConstraints);

        statusbar.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        statusbar.add(status, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 480;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        statusbar.add(progress, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(statusbar, gridBagConstraints);

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        keysTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        keysScrollPane.setViewportView(keysTable);

        jSplitPane1.setLeftComponent(keysScrollPane);

        jScrollPane1.setViewportView(content);

        jSplitPane1.setRightComponent(jScrollPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jSplitPane1, gridBagConstraints);

        jCheckBox1.setText("Sort when saving");
        jCheckBox1.setToolTipText("<html>Properties will be sorted alphabetically when saved. Comments appearing above<br>a line will remain grouped with that line.  If the comment applies to a group or<br>properties, this will ungroup them.</html>");
        add(jCheckBox1, new java.awt.GridBagConstraints());

    }//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        if (props == null || file == null) {
            return;
        }
        
        props.sorted = jCheckBox1.isSelected(); //XXX
        
        Set toRemove = ((PropsTableModel) keysTable.getModel()).getKeysToRemove();
        StringBuffer sb = new StringBuffer();
        for (Iterator i=toRemove.iterator();i.hasNext();) {
            sb.append (i.next());
            if (i.hasNext()) {
                sb.append (", ");
            }
        }
        
        JTextArea message = new JTextArea(sb.toString());
        message.setBorder(BorderFactory.createEmptyBorder());
        message.setEditable(false);
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        message.setMinimumSize (new Dimension (400,400));
        message.setPreferredSize (new Dimension(400,400));
        JScrollPane jsp = new JScrollPane (message);
        jsp.setViewportBorder (BorderFactory.createEmptyBorder());
        jsp.setBorder (BorderFactory.createEmptyBorder());
        String backupFileName = null;
        
        if (JOptionPane.showConfirmDialog(this, new JScrollPane(message), 
            "Delete keys and save?", JOptionPane.OK_OPTION | JOptionPane.CANCEL_OPTION) ==
            JOptionPane.OK_OPTION) {
                
            try {
                Properties backup = new Properties (props, toRemove);
                backupFileName = file.getPath() + ".deleted";
                File backupFile = new File (backupFileName);
                int ext = 0;
                while (backupFile.exists()) {
                    backupFile = new File (backupFileName + "_" + ext);
                    ext++;
                }
                backupFile.createNewFile();
                FileOutputStream bos = new FileOutputStream (backupFile);
                backup.store (bos, "#Bundlizer backup file of deleted " +
                    "properties - " + new Date());

                for (Iterator i = toRemove.iterator(); i.hasNext();) {
                    props.remove(i.next());
                }
                FileOutputStream fos = new FileOutputStream (file);
                StringBuffer lic = new StringBuffer (LICENSE);
                String year = Integer.toString(1900 + new Date().getYear());
                int idx = lic.indexOf("@DATE@");
                lic.replace(idx, idx+6, year);

                props.store(fos, lic.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            setFile(file);
            if (backupFileName != null) {
                setStatus ("Deleted props saved in " + backupFileName);
            }
        }
        
    }//GEN-LAST:event_saveButtonActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        // TODO add your handling code here:
        if (jfc == null) {
            jfc = new JFileChooser();
            jfc.setFileFilter(new Filter());
            jfc.setFileHidingEnabled(true);
            jfc.setCurrentDirectory(
                new File ("/space/nbsrc/openide/src/org/openide/explorer/propertysheet"));
        }
        if (jfc.showOpenDialog(this) == jfc.APPROVE_OPTION) {
            setFile (jfc.getSelectedFile());
        }
        
    }//GEN-LAST:event_loadButtonActionPerformed
    
    private void setFile (File file) {
        if (file.isDirectory()) {
            return;
        }
        Properties props = new Properties ();
        try {
            prepareToScan();
            keysToFiles.clear();
//            props.load(new FileInputStream (file));
            props.load(file);
            setStatus ("Loading " + file.getName());
            setProperties (props);
            setName (file.toString());
            File dir = file.getParentFile();
            scan (dir);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        this.file = file;
    }
    
    private File file = null;
    
    private void startScanning() {
        setStatus ("Scanning...");
        ScanTask task = (ScanTask) scanTaskQueue.pop();
        if (task != null) {
            setStatus ("Scanning " + task.getFile().getName(), task.getProg());
            SwingUtilities.invokeLater (task);
        } else {
            setStatus ("No java files found");
        }
    }
    
    private Stack scanTaskQueue = new Stack();
    private class ScanTask implements Runnable {
        private File f;
        private int prog;
        public ScanTask (File f, int prog) {
            this.f = f;
            this.prog = prog;
        }
        
        public void run() {
            try {
                scanFile (f);
            } finally {
                if (scanTaskQueue.isEmpty()) {
                    scanCompleted();
                } else {
                    ScanTask task = (ScanTask) scanTaskQueue.pop();
                    setStatus ("Scanning " + task.getFile().getName(), task.getProg());
                    SwingUtilities.invokeLater (task);
                }
            }
        }
        
        public int getProg() {
            return prog;
        }
        
        public File getFile() {
            return f;
        }
        
    }
    
    private void prepareToScan() {
        getRootPane().getGlassPane().setVisible(true);
        getRootPane().getGlassPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        setEnabled (false);
    }
    
    private void scanCompleted() {
        setEnabled(true);
        getRootPane().getGlassPane().setVisible(false);
        getRootPane().getGlassPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        if (props != null) {
            setStatus ("Scan completed, found " + props.size() + " properties");
        } else {
            setStatus ("Scan aborted");
        }
        buildTableModel();
        jSplitPane1.setDividerLocation(0.5f);
    }
    
    private void buildTableModel() {
        if (props != null) {
            PropsTableModel mdl = new PropsTableModel (props, keysToFiles);
            keysTable.setModel (mdl);
            saveButton.setEnabled(mdl.hasChanges());
            mdl.addTableModelListener(new MdlListener());
        }
    }
    
    private class MdlListener implements TableModelListener {
        public void tableChanged(TableModelEvent e) {
            saveButton.setEnabled (((PropsTableModel) e.getSource()).hasChanges());
        }
    }
    
    private void setStatus (String s) {
        status.setText (s);
        status.paintImmediately(0,0,status.getWidth(),status.getHeight());
    }
    
    private void setStatus (String s, int prog) {
        if (prog != progress.getValue()) {
            progress.setValue (prog);
            progress.paintImmediately (0,0,progress.getWidth(), progress.getHeight());
        }
        status.setText (s);
        status.paintImmediately(0,0,status.getWidth(), status.getHeight());
    }
    
    private void setProperties (Properties props) {
        StringBuffer sb = new StringBuffer();
        Enumeration en = props.keys();
        while (en.hasMoreElements()) {
            String key = (String) en.nextElement();
            String val = (String) props.get(key);
            sb.append (key + "=" + val + "\n");
        }
        content.setText (sb.toString());
        this.props = props;
    }
    
    private Properties props = null;
    private Properties getProperties() {
        return props;
    }
    
    private Map keysToFiles = new TreeMap();
    
    private void scan (File dir) {
        setStatus ("Finding files to scan...");
        File[] f = dir.listFiles(new JavaFilenameFilter());
        for (int i=0; i < f.length; i++) {
            float size = f.length;
            float pos = size - i;
            float progress = (pos / size ) * 100;
            ScanTask task = new ScanTask (f[i], Math.round(progress));
            scanTaskQueue.push(task);
        }
        startScanning();
    }
    
    
    
    private void scanFile (File f) {
        //brute force and ugly, but I'm no regex expert
        try {
            FileInputStream fis = new FileInputStream (f);
            ByteBuffer bb = fis.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, f.length());
            
            byte[] b = new byte[(int) f.length()];
            bb.get (b);
            String search = new String (b);

            Enumeration en = props.keys();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
//                setStatus ("Scanning " + f.getName() + " for " + key);
                String s = "\"" + key + "\"";
                if (search.indexOf(s) != -1) {
                    String curr = (String) keysToFiles.get(key);
                    if (curr == null) {
                        curr = f.getName();
                    } else {
                        curr += ',' + f.getName();
                    }
                    keysToFiles.put (key, curr);
                } else if (s.indexOf("OpenIDE") != -1 || s.indexOf("/") != -1) {
                    keysToFiles.put (key, "RETAINED");
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
    }
    
    private class JavaFilenameFilter implements java.io.FilenameFilter {
        public boolean accept(java.io.File file, String str) {
            return str.endsWith ("java");
        }
    }
    
    private JFileChooser jfc = null;
    
    private class Filter extends javax.swing.filechooser.FileFilter {
        
        public boolean accept(java.io.File file) {
            return file.isDirectory() || file.toString().endsWith(".properties");
        }
        
        public String getDescription() {
            return "Properties files";
        }
        
    }
    
    private static final String LICENSE = 
    "#                 Sun Public License Notice\n" +
    "#\n" + 
    "# The contents of this file are subject to the Sun Public License\n" +
    "# Version 1.0 (the \"License\"). You may not use this file except in\n" +
    "# compliance with the License. A copy of the License is available at\n"+
    "# http://www.sun.com/\n"+
    "# \n"+
    "# The Original Code is NetBeans. The Initial Developer of the Original\n"+
    "# Code is Sun Microsystems, Inc. Portions Copyright 1997-@DATE@ Sun\n"+
    "# Microsystems, Inc. All Rights Reserved.\n\n";
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea content;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JScrollPane keysScrollPane;
    private javax.swing.JTable keysTable;
    private javax.swing.JButton loadButton;
    private javax.swing.JProgressBar progress;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel status;
    private javax.swing.JPanel statusbar;
    // End of variables declaration//GEN-END:variables
    
}
