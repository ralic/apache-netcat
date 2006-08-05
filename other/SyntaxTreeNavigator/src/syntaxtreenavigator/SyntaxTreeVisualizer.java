/*The contents of this file are subject to the terms of the Common Development
and Distribution License (the License). You may not use this file except in
compliance with the License. You can obtain a copy of the License at 
http://www.netbeans.org/cddl.html or http://www.netbeans.org/cddl.txt.
When distributing Covered Code, include this CDDL Header Notice in each file
and include the License file at http://www.netbeans.org/cddl.txt.
If applicable, add the following below the CDDL Header, with the fields
enclosed by brackets [] replaced by your own identifying information:
"Portions Copyrighted [year] [name of copyright owner]"  */
package syntaxtreenavigator;

import com.sun.tools.javac.api.JavacTaskImpl;
import com.sun.tools.javac.tree.JCTree;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.prefs.Preferences;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

/**
 *
 * @author  Tim Boudreau
 */
public class SyntaxTreeVisualizer extends javax.swing.JFrame implements DiagnosticListener, TreeSelectionListener, Runnable {
    JFileChooser jfc = new JFileChooser();
    
    /** Creates new form SyntaxTreeVisualizer */
    public SyntaxTreeVisualizer() {
        initComponents();
        Font f = filenameLabel.getFont();
        f = f.deriveFont(Font.BOLD);
        filenameLabel.setFont(f);
//        status.setText (" ");
        filenameLabel.setText (" ");
        //try to warm up jfilechooser
        jfc.getPreferredSize();
        tree.addTreeSelectionListener(this);
        TreeSelectionModel smdl = new DefaultTreeSelectionModel();
        smdl.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setSelectionModel(smdl);
        smdl.addTreeSelectionListener(this);
        tree.setRootVisible(false);
        tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode()));
    }
    
    public void addNotify() {
        super.addNotify();
        jSplitPane1.setDividerLocation(0.5D);
        EventQueue.invokeLater(this);
    }
    
    public void run() {
        super.setState(Frame.MAXIMIZED_BOTH);
        openMenuItemActionPerformed(null);
    }
    
    private static final class FF extends FileFilter {
        public boolean accept(File f) {
            return f.isDirectory() || f.getName().endsWith(".java");
        }

        public String getDescription() {
            return "Java Source Files";
        }
        
    }
    static final FF ff = new FF();
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        status = new javax.swing.JLabel();
        statussep = new javax.swing.JSeparator();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        filenameLabel = new javax.swing.JLabel();
        treepane = new javax.swing.JScrollPane();
        tree = new javax.swing.JTree();
        jCheckBox1 = new javax.swing.JCheckBox();
        vp = new ViewPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        status.setText("Use File | Open to open a Java source file");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(status, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        getContentPane().add(statussep, gridBagConstraints);

        jSplitPane1.setDividerLocation(50);
        jPanel2.setLayout(new java.awt.GridBagLayout());

        filenameLabel.setText("[no file loaded]");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(filenameLabel, gridBagConstraints);

        treepane.setViewportView(tree);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.weightx = 0.75;
        gridBagConstraints.weighty = 0.75;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(treepane, gridBagConstraints);

        jCheckBox1.setText("Show empty lists");
        jCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(jCheckBox1, gridBagConstraints);

        jSplitPane1.setLeftComponent(jPanel2);

        javax.swing.GroupLayout vpLayout = new javax.swing.GroupLayout(vp);
        vp.setLayout(vpLayout);
        vpLayout.setHorizontalGroup(
            vpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 365, Short.MAX_VALUE)
        );
        vpLayout.setVerticalGroup(
            vpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 313, Short.MAX_VALUE)
        );
        jSplitPane1.setRightComponent(vp);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jSplitPane1, gridBagConstraints);

        jMenu1.setText("File");
        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openMenuItem.setMnemonic('O');
        openMenuItem.setText("Open...");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });

        jMenu1.add(openMenuItem);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });

        jMenu1.add(exitMenuItem);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        V.showLists = jCheckBox1.isSelected();
        if (lastFile != null && lastFile.isFile()) {
            try {
                openFile (lastFile);
            } catch (IOException ex) {
                status.setText(ex.getLocalizedMessage());
                ex.printStackTrace();
            }
        }
        
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    File lastDir = null;
    
    private File getLastDir() {
        if (lastDir == null) {
            String fname = Preferences.userNodeForPackage(SyntaxTreeVisualizer.class).get("dir", null);
            if (fname == null) {
                fname = System.getProperty ("user.home");
            }
            if (fname != null) {
                lastDir = new File (fname); //NOI18N
                if (!lastDir.exists() || !lastDir.isDirectory()) {
                    lastDir = null;
                }
            }
        }
        return lastDir;
    }
    
    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        jfc.setFileHidingEnabled(true);
        jfc.setFileFilter(ff);
        File lastDir = getLastDir();
        jfc.setFileSelectionMode(jfc.FILES_ONLY);
        if (lastDir != null) {
            jfc.setCurrentDirectory(lastDir);
        }
        if (jfc.showOpenDialog(this) == jfc.APPROVE_OPTION) {
            File dir = jfc.getSelectedFile().getParentFile();
            if (!dir.equals(lastDir)) {
                setLastDir (dir);
            }
            try {
                status.setText ("Opening " + jfc.getSelectedFile().getName());
                openFile (jfc.getSelectedFile());
                lastFile = jfc.getSelectedFile();
                status.setText ("Opened " + jfc.getSelectedFile().getName());
                tree.expandPath(new TreePath(tree.getModel().getRoot()));
            } catch (IOException ioe) {
                ioe.printStackTrace();
                JOptionPane.showMessageDialog(this, ioe.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_openMenuItemActionPerformed

    
    private File lastFile = null;
    private void setLastDir (File dir) {
        Preferences.userNodeForPackage(SyntaxTreeVisualizer.class).put("dir", dir.getPath()); //NOI18N
        lastDir = dir;
    }
    
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit (0);
    }//GEN-LAST:event_exitMenuItemActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SyntaxTreeVisualizer().setVisible(true);
            }
        });
    }
    
    DefaultMutableTreeNode root;
    private void openFile (File file) throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager m = compiler.getStandardFileManager(this, null, 
                null);
        Iterable <? extends JavaFileObject> toCompile = 
                m.getJavaFileObjects(file);
        Iterable <? extends File> outdir = 
                Collections.singleton(new File(
                System.getProperty("java.io.tmpdir"))); //NOI18N
        m.setLocation(StandardLocation.CLASS_OUTPUT, outdir);
        JavacTaskImpl task = (JavacTaskImpl) compiler.getTask(null, 
                m, null, null, null, toCompile);
        
        root = new DefaultMutableTreeNode ("Root");
        DefaultTreeModel mdl = new DefaultTreeModel (root);
        
        V first = null;
        for (Iterator it = task.parse().iterator(); it.hasNext();) {
            JCTree.JCCompilationUnit unit = (JCTree.JCCompilationUnit) it.next();
            V v = new V(unit);
            if (first == null) {
                first = v;
            }
            root.add (v);
        }
        tree.setModel(mdl);
        tree.expandRow(0);
        if (first != null) {
            tree.getSelectionModel().setSelectionPath(new TreePath(new Object[] {mdl.getRoot(),
            first}));
        }
        filenameLabel.setText(file.getPath());
    }

    public void report(Diagnostic diagnostic) {
        status.setText(diagnostic.toString());
    }

    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode nd = (DefaultMutableTreeNode)e.getPath().getLastPathComponent();
        status.setText(nd.getUserObject() == null ? "[null]" : nd.getUserObject().toString());
        Object o = nd.getUserObject();
        ((ViewPanel) vp).setObject(o);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JLabel filenameLabel;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JLabel status;
    private javax.swing.JSeparator statussep;
    private javax.swing.JTree tree;
    private javax.swing.JScrollPane treepane;
    private javax.swing.JPanel vp;
    // End of variables declaration//GEN-END:variables
    
}
