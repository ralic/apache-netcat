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

import com.sun.source.tree.CompilationUnitTree;
import com.sun.tools.javac.api.JavacTaskImpl;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.prefs.Preferences;
import javax.swing.CellRendererPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.TreePath;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

/**
 *
 * @author  Tim Boudreau
 */
public class SyntaxTreeVisualizer extends javax.swing.JFrame implements Runnable, DiagnosticListener {
    JFileChooser jfc = new JFileChooser();
    SyntaxTreePanel pnl = new SyntaxTreePanel();
    
    /** Creates new form SyntaxTreeVisualizer */
    public SyntaxTreeVisualizer() {
        setTitle ("Syntax Tree Visualizer");
        initComponents();
        CellRendererPane pane = new CellRendererPane();
        pane.add (jfc);
        pane.doLayout();
        add (pnl, BorderLayout.CENTER);
    }
    
    public void addNotify() {
        super.addNotify();
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

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                pnl.setStatusText ("Opening " + jfc.getSelectedFile().getName());
                openFile (jfc.getSelectedFile());
                lastFile = jfc.getSelectedFile();
                pnl.setStatusText ("Opened " + jfc.getSelectedFile().getName());
                pnl.expandPath(new TreePath(pnl.getTree().getModel().getRoot()));
                setTitle ("Syntax Tree Visualizer - " + lastFile.getName());
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
    
    void openFile (File file) throws IOException {
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
        
        Iterator <? extends CompilationUnitTree> it = task.parse().iterator();
        pnl.init (it);
        pnl.getFilenameLabel().setText(file.getPath());
    }
    
    public void report(Diagnostic diagnostic) {
        pnl.report (diagnostic);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem openMenuItem;
    // End of variables declaration//GEN-END:variables
    
}
