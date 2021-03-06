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

package org.netbeans.modules.vcscore.cmdline;

import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.tree.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import org.openide.WizardDescriptor;
import org.openide.filesystems.AbstractFileSystem;
import org.openide.util.*;

import org.netbeans.modules.vcscore.*;
/**
 * The tree selection of a mount point relative to the working directory.
 * @author  Milos Kleint, Martin Entlicher
 */
public class RelativeMountPanel extends javax.swing.JPanel implements TreeSelectionListener {//, javax.swing.event.TreeExpansionListener {
    
    private String[] selectedMounts = null;
    
    static final long serialVersionUID =-6389940806020132699L;
    //DefaultMutableTreeNode rootNode;
    //String cvsRoot;
    /** Whether the tree is initially expanded. Since it is a special expansion,
     * we will ignore tree expansion events while this variable is true. */
    private volatile boolean initiallyExpanding = true;
    
    /** A special list of children. If <code>null</code>, File.list() is used
     * instead. */
    private AbstractFileSystem.List childrenList = null;
    private boolean resultRelativePaths;
    
    private boolean mouseEnter = false;

    private Set alreadyExpandedNodes;

    public RelativeMountPanel() {
        this(null, (char) 0, null, (char) 0);
    }
    
    public RelativeMountPanel(String label, char labelMnc, String moduleLabel, char moduleMnc) {
        initComponents ();
        if (System.getProperty("java.version").startsWith("1.3")
            && UIManager.getSystemLookAndFeelClassName().equals("com.sun.java.swing.plaf.windows.WindowsLookAndFeel")) {
            //workaround for WindowsLookAndFeel error.  see issue 29753. dnoyeb@users.sourceforge.net
            trRelMount.setLargeModel(false);
        }
        resultRelativePaths = true;
        if (label != null) {
            lbRmpSelect.setText(label);
        }
        if (labelMnc == 0) {
            labelMnc = NbBundle.getMessage(RelativeMountPanel.class, "RMPanel.relMountLabel_Mnemonic").charAt(0);
        }
        lbRmpSelect.setDisplayedMnemonic(labelMnc);
        if (moduleLabel != null) {
            lbRelMount.setText(moduleLabel);
        }
        if (moduleMnc == 0) {
            moduleMnc = NbBundle.getMessage(RelativeMountPanel.class, "NewCvsCustomizer.lbRelMount.mnem").charAt(0);
        }
        lbRelMount.setDisplayedMnemonic(moduleMnc);
        //isValid = false;
        addTreeListeners();
        //trRelMount.addTreeSelectionListener(this);
        //trRelMount.addTreeExpansionListener(this);
        //cvsRoot = "";
        txRelMount.selectAll();
        txRelMount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { mouseEnter = true; }
            public void mousePressed(java.awt.event.MouseEvent e) { mouseEnter = true; }
            public void mouseReleased(java.awt.event.MouseEvent e) { mouseEnter = false; }
            public void mouseClicked(java.awt.event.MouseEvent e) { mouseEnter = false; }
            public void mouseExited(java.awt.event.MouseEvent e) { mouseEnter = false; }
        });
        txRelMount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (!mouseEnter) txRelMount.selectAll();
            }
        }); 
        initAccessibility();
        alreadyExpandedNodes = new HashSet();
    }

    public void requestFocus () {
        trRelMount.requestFocus();
    }
    
    public void setOuterSpace() {
        this.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(6, 12, 12, 12)));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        lbRmpSelect = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        trRelMount = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        lbRelMount = new javax.swing.JLabel();
        txRelMount = new javax.swing.JTextField();
        lbWaiting = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(12, 12, 12, 12)));
        setDoubleBuffered(false);
        setMinimumSize(new java.awt.Dimension(263, 176));
        setPreferredSize(new java.awt.Dimension(419, 176));
        lbRmpSelect.setLabelFor(trRelMount);
        lbRmpSelect.setText(java.util.ResourceBundle.getBundle("org/netbeans/modules/vcscore/cmdline/Bundle").getString("RMPanel.relMountLabel"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        add(lbRmpSelect, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridLayout(1, 1, 10, 1));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(101, 340));
        trRelMount.setLargeModel(true);
        trRelMount.setMaximumSize(new java.awt.Dimension(9999, 9999));
        trRelMount.setShowsRootHandles(true);
        jScrollPane1.setViewportView(trRelMount);

        jPanel2.add(jScrollPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanel2, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        lbRelMount.setText(java.util.ResourceBundle.getBundle("org/netbeans/modules/vcscore/cmdline/Bundle").getString("NewCvsCustomizer.lbRelMount.text"));
        lbRelMount.setLabelFor(txRelMount);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        jPanel1.add(lbRelMount, gridBagConstraints);

        txRelMount.setEditable(false);
        txRelMount.setPreferredSize(new java.awt.Dimension(120, 20));
        txRelMount.setMinimumSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 37;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 0, 0);
        jPanel1.add(txRelMount, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(lbWaiting, gridBagConstraints);

    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbRelMount;
    private javax.swing.JLabel lbRmpSelect;
    private javax.swing.JLabel lbWaiting;
    private javax.swing.JTree trRelMount;
    private javax.swing.JTextField txRelMount;
    // End of variables declaration//GEN-END:variables
    
    private void initAccessibility() {
        getAccessibleContext().setAccessibleDescription(
            NbBundle.getMessage(RelativeMountPanel.class, "ACSD_RMPanel"));
        trRelMount.getAccessibleContext().setAccessibleDescription(
            NbBundle.getMessage(RelativeMountPanel.class, "ACSD_RMPanel.relMountTree"));
        txRelMount.getAccessibleContext().setAccessibleDescription(
            NbBundle.getMessage(RelativeMountPanel.class, "ACSD_RMPanel.relMountTextField"));
    }
    
    public String getName() {
        return NbBundle.getMessage(RelativeMountPanel.class, "RelativeMountPanel.mountDialogLabel");
    }
    
    public void setInfoLabel(String text) {
        lbWaiting.setText(text);
    }
    
    //public void setLast(boolean last) {
    //    isLastPanel = last;
    //}
/** last panel check.
 * @return true if last else false.
 */    
    
    //public boolean isLast() {
    //    D("is last");
    //    return isLastPanel;
    //}
    
    //public int getNext() {
    //    return 2;
    //}
/** Get the component displayed in this panel.
 * @return the component
 */
    public java.awt.Component getComponent() {
        return this;
    }
/** Help for this panel.
 * When the panel is active, this is used as the help for the wizard dialog.
 * @return the help or <code>null</code> if no help is supplied
 */
    public HelpCtx getHelp() {
        return null;
    }
    
    protected void setEnabledTree(boolean enabled) {
        trRelMount.setEnabled(enabled);
    }
    
    protected void setTreeModel(TreeModel model) {
        trRelMount.setModel(model);
    }
    
    public String getRelMount() {
/*  TODO - return null if nothing selected      
        DefaultMutableTreeNode nd = (DefaultMutableTreeNode)trRelMount.getSelectionPath().getLastPathComponent();
        MyFile fl = (MyFile)nd.getUserObject();
        MyFile rootFl = (MyFile)cvsRoot.getUserObject();
        String toWrite = fl.getAbsolutePath().substring(.getAbsolutePath().length());
            if (toWrite.startsWith(File.separator)) {
                toWrite = toWrite.substring(File.separator.length());
            }
 */
        if (selectedMounts == null || selectedMounts.length == 0)
            return txRelMount.getText();
        else return selectedMounts[0];
    }
    
    public String[] getRelMounts() {
        return selectedMounts;
    }

    /*
    private void doMarkParent(DefaultMutableTreeNode parent, File current) {
       MyFile fl = (MyFile)parent.getUserObject(); 
       if (!parent.equals(rootNode)) {
         fl.setLocal(false);
       }
       else { // special handling of root node..
          if (fl.isLocal()) return; // already failed to be cvs dir
          if (current.getName().equalsIgnoreCase("CVS")) return; // not a real subdir of root
          File[] files = current.listFiles(new FileFilter()  {
              public boolean accept(File f) {
                 if (f.isDirectory() && f.getName().equalsIgnoreCase("CVS")) return true;
                 return false;
              }
              
          });
          if (files.length > 0) { //found cvs dir in subdircetory
             File cvsDir = files[0];
             String rootStr = returnCvsRoot(new File(cvsDir.getAbsolutePath() + File.separator + "Root"));
             if (rootStr.equals("")) {
                fl.setLocal(true);
                return;
             }
             if (cvsRoot.equals("")) { cvsRoot = rootStr;}
             else { 
                 if (!cvsRoot.equals(rootStr)) { // subdirs checked out from different cvs servers
                     fl.setLocal(true);
                 }
             }
          }
          else { // one subdir of root is not a cvs dir..
              fl.setLocal(true);
          }
       }
    }
    
    private String returnCvsRoot(File fileName) {
       String toReturn = "";
       if (fileName.exists() && fileName.canRead() ){
            BufferedReader in = null;
            try {
              in= new BufferedReader
                   (new InputStreamReader
                    (new BufferedInputStream
                     (new FileInputStream(fileName))));
                toReturn =in.readLine();
                in.close();
            }catch (IOException e){
                toReturn = "";
            } finally {
                try {
                  if (in != null) { in.close(); in = null;}
                }
                catch (IOException exc) {}
            }
        }
      return toReturn;
    }
     */

    private void addTreeListeners() {
        trRelMount.addTreeSelectionListener(this);
        trRelMount.addTreeWillExpandListener(new TreeWillExpandListener() {
            public void treeWillExpand(TreeExpansionEvent evt) {
                if (initiallyExpanding) return ;
                TreePath path = evt.getPath();
                final MyTreeNode node = (MyTreeNode) path.getLastPathComponent();
                Runnable treeBuild = new Runnable() {
                    public void run() {
                        folderTreeNodes(node);
                    }
                };
                //new Thread(treeBuild, "Mount Panel Tree Build").start();
                RequestProcessor.postRequest(treeBuild);
            }
            public void treeWillCollapse(TreeExpansionEvent evt) {
            }
        });
    }

    private void createTree(String rootString) {
        File rootDir = new File(rootString);
        final MyTreeNode root = new MyTreeNode(rootString);
        setTreeModel(new DefaultTreeModel(root));
        Runnable treeBuild = new Runnable() {
            public void run() {
                //recursiveTreeNodes(root);
                folderTreeNodes(root);
            }
        };
        //new Thread(treeBuild, "Mount Panel Tree Build").start();
        RequestProcessor.postRequest(treeBuild);
        trRelMount.setModel(new DefaultTreeModel(root));
        trRelMount.setCellRenderer(new MyTreeCellRenderer());
    }
    
    private void folderTreeNodes(final MyTreeNode parent) {
        //boolean hasChild = false;
        final ArrayList children = new ArrayList();
		
        synchronized (trRelMount) {
            if (alreadyExpandedNodes.contains(parent)) {
                return;
            }
            // Remember this node as expanded.
            alreadyExpandedNodes.add(parent);

            try {
                SwingUtilities.invokeAndWait(new Runnable() {
                    public void run() {
                        parent.removeAllChildren();
                    }
                });
            } catch (InterruptedException intrexc) {
                // Ignored
            } catch (java.lang.reflect.InvocationTargetException itexc) {
                // Ignored
            }

            String parentFileStr = (String) parent.getUserObject(); 
            //File childFile;
            String[] list;
            if (childrenList == null) {
                File parentFile = new File(parentFileStr);
                list = parentFile.list(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        if ("CVS".equals(name)) return false;
                        File file = new File(dir, name);
                        return file.isDirectory() && file.exists();
                    }
                });
            } else {
                list = childrenList.children(parentFileStr);
                /*
                String[] childrenL = childrenList.children(parentFileStr);
                list = new File[childrenL.length];
                for (int i = 0; i < childrenL.length; i++) {
                    list[i] = new File(parentFile, childrenL[i]);
                }
                 */
            }
            String separator = (childrenList == null) ? File.separator : "/";
            if (!parentFileStr.endsWith(separator)) parentFileStr = parentFileStr + separator;
            if (list != null) {
                Arrays.sort(list);
                for (int index = 0; index < list.length; index++) {
                    String filePath = parentFileStr + list[index];
                    MyTreeNode child = new MyTreeNode(filePath);
                    child.setAllowsChildren(true);
                    children.add(child);
                }
            }
            SwingUtilities.invokeLater (new Runnable() {
                public void run() {
                    if (children.size() == 0) {
                        parent.setAllowsChildren(false);
                        trRelMount.collapsePath(new TreePath(parent.getPath()));
                    } else {
                        for (Iterator it = children.iterator(); it.hasNext(); ) {
                            parent.add((MyTreeNode) it.next());
                        }
                    }
                }
            });
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ((DefaultTreeModel) trRelMount.getModel()).nodeStructureChanged(parent);
                if (children.size() > 0) {
                    trRelMount.scrollPathToVisible(new TreePath(((MyTreeNode) parent.getLastChild()).getPath()));
                    trRelMount.scrollPathToVisible(new TreePath(((MyTreeNode) parent.getFirstChild()).getPath()));
                }
            }
        });
    }
    
    /*
    private void recursiveTreeNodes(DefaultMutableTreeNode parent) {
        File parentFile = (File)parent.getUserObject();
        DefaultMutableTreeNode child;
        File childFile;
        boolean hasChild = false;
        File[] list = parentFile.listFiles();
        if (list == null) return;
        for (int index = 0; index < list.length; index++) {
            if (list[index].isDirectory() && list[index].exists()) {
                childFile = list[index];
                if (!childFile.getName().equals("CVS")) { //CVS dirs go out..
                    hasChild = true;
                    child = new DefaultMutableTreeNode(new MyFile(childFile.getAbsolutePath()));
                    parent.add(child);
                    recursiveTreeNodes(child);
                }
                doMarkParent(parent, childFile);
            }
        }
    }
     */
    
    public void valueChanged(TreeSelectionEvent e) {
        TreePath[] paths = trRelMount.getSelectionPaths();
        if (paths != null) {
            selectedMounts = new String[paths.length];
            for (int i = 0; i < paths.length; i++) {
                MyTreeNode node = (MyTreeNode) paths[i].getLastPathComponent();
                String selFile = (String) node.getUserObject();
                if (resultRelativePaths) {
                    MyTreeNode rootNode = (MyTreeNode) node.getRoot();
                    String rootFile = (String) rootNode.getUserObject();
                    if (rootFile.equals(selFile)) {
                        selectedMounts[i] = "";
                        continue;
                        //txRelMount.setText("");
                        //return;
                    }
                    String toWrite = selFile.substring(rootFile.length());
                    if (toWrite.startsWith(File.separator)) {
                        toWrite = toWrite.substring(File.separator.length());
                    }
                    selectedMounts[i] = toWrite;
                    //txRelMount.setText(toWrite);
                } else {
                    selectedMounts[i] = selFile;
                }
            }
            setRelMountText();
        } else {
            txRelMount.setText("");  
        }
    }
    
    private void setRelMountText() {
        if (selectedMounts.length == 0) {
            txRelMount.setText("");
            return ;
        }
        StringBuffer relMounts = new StringBuffer();
        for (int i = 0; i < selectedMounts.length - 1; i++) {
            relMounts.append(selectedMounts[i]);
            relMounts.append(", ");
        }
        relMounts.append(selectedMounts[selectedMounts.length - 1]);
        txRelMount.setText(relMounts.toString());
    }
    
    /**
     * Initialize the tree. Only a single relative mount point can be selected.
     * @param rootDir the root directory of the tree
     * @param relMount the initial relative mount point
     */
    public void initTree(String rootDir, final String relMount) {
        initTree(rootDir, new String[] { relMount }, false);
    }
    
    /**
     * Initialize the tree. Multiple relative mount points can be selected.
     * @param rootDir the root directory of the tree
     * @param relMounts the array of initial relative mount points
     */
    public void initTree(String rootDir, final String[] relMounts) {
        initTree(rootDir, relMounts, true);
    }
    
    /**
     * Initialize the tree.
     * @param rootDir the root directory of the tree
     * @param relMounts the initial relative mount points
     * @param multipleSelections whether multiple nodes can be selected
     */
    public void initTree(String rootDir, final String[] relMounts, boolean multipleSelections) {
        initTree(rootDir, relMounts, multipleSelections, null);
    }
    
    /**
     * Initialize the tree.
     * @param rootDir the root directory of the tree
     * @param relMounts the initial relative mount points
     * @param multipleSelections whether multiple nodes can be selected
     */
    public void initTree(String rootDir, final String[] relMounts, boolean multipleSelections,
                         AbstractFileSystem.List childrenList) {
        this.initiallyExpanding = true;
        this.childrenList = childrenList;
        resultRelativePaths = childrenList == null;
        createTree(rootDir);
        //trRelMount.setSelectionModel(new MySelectionModel()); // because of not allowing to select local dirs
        if (multipleSelections) {
            trRelMount.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        } else {
            trRelMount.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        }
        RequestProcessor.postRequest(new Runnable() {
            public void run() {
                setInitSelect(relMounts);
            }
        });
        selectedMounts = relMounts;
        setRelMountText();
        //txRelMount.setText(relMount);
    }
    
    private void setInitSelect(String[] pathsToNode) {
        /*
        for (int i = 0; i < pathsToNode.length; i++) {
            setInitSelect(pathsToNode[i]);
        }
         */
        final TreePath[] paths = new TreePath[pathsToNode.length];
        for (int i = 0; i < pathsToNode.length; i++) {
            pathsToNode[i] = pathsToNode[i].replace(File.separatorChar, '/');
            StringTokenizer token = new StringTokenizer(pathsToNode[i], "/", false);
            MyTreeNode parent = (MyTreeNode) trRelMount.getModel().getRoot();
            TreePath path = new TreePath(parent);
            MyTreeNode child;
            String directoryName;
            outerWhile:
                while (token.hasMoreTokens()) {
                    directoryName = token.nextToken();
                    try {
                        //trRelMount.expandPath(path);
                        if (parent.getChildCount() == 0) {
                            folderTreeNodes(parent);
                            try {
                                SwingUtilities.invokeAndWait(new Runnable() {
                                    public void run() {
                                        // Just wait for AWT to finish its work
                                    }
                                });
                            } catch (InterruptedException intrexc) {
                                // Ignored
                            } catch (java.lang.reflect.InvocationTargetException itexc) {
                                // Ignored
                            }
                        }
                        child = (MyTreeNode) parent.getFirstChild();
                        do {
                            if (child == null) break;
                            String childFile = (String) child.getUserObject();
                            childFile = childFile.replace(File.separatorChar, '/');
                            int nameIndex = childFile.lastIndexOf('/');
                            if (nameIndex < 0) nameIndex = 0;
                            else nameIndex++;
                            if (childFile.substring(nameIndex).equals(directoryName)) {
                                parent = child;
                                path = path.pathByAddingChild(child);
                                continue outerWhile;
                            }
                            child = (MyTreeNode) parent.getChildAfter(child);
                        } while (true);
                    } catch (NoSuchElementException exc){
                        // Ignore
                    }
                }
            paths[i] = path;
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                trRelMount.setSelectionPaths(paths);
            }
        });
        initiallyExpanding = false;
    }
    
    /*
    private void setInitSelect(String pathToNode) {
        pathToNode = pathToNode.replace(File.separatorChar, '/');
        StringTokenizer token = new StringTokenizer(pathToNode, "/", false);
        MyTreeNode parent = (MyTreeNode) trRelMount.getModel().getRoot();
        TreePath path = new TreePath(parent);
        MyTreeNode child;
        File childFile;
        String directoryName;
        outerWhile:
            while (token.hasMoreTokens()) {
                directoryName = token.nextToken();
                try {
                    //trRelMount.expandPath(path);
                    folderTreeNodes(parent);
                    try {
                        SwingUtilities.invokeAndWait(new Runnable() {
                            public void run() {
                                // Just wait for AWT to finish its work
                            }
                        });
                    } catch (InterruptedException intrexc) {
                        // Ignored
                    } catch (java.lang.reflect.InvocationTargetException itexc) {
                        // Ignored
                    }
                    child = (MyTreeNode) parent.getFirstChild();
                    do {
                        if (child == null) break;
                        childFile = (File) child.getUserObject();
                        if (childFile.getName().equals(directoryName)) {
                            parent = child;
                            path = path.pathByAddingChild(child);
                            continue outerWhile;
                        }
                        child = (MyTreeNode) parent.getChildAfter(child);
                    } while (true);
                } catch (NoSuchElementException exc){
                    // Ignore
                }
            }
        final TreePath selectedPath = path;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                trRelMount.setSelectionPath(selectedPath);
            }
        });
        initiallyExpanding = false;
    }
     */
    
    private static void D(String debug) {
//        System.out.println("Cust4MountPanel(): "+debug);
    }

    /*
    public void treeCollapsed(final javax.swing.event.TreeExpansionEvent p1) {
       //for root node.. if not enabled, unselect everything upon colapsing
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)p1.getPath().getLastPathComponent();
        MyFile fl = (MyFile)node.getUserObject();
        if (fl.isLocal()) {
            trRelMount.clearSelection();
        }
    }

    public void treeExpanded(final javax.swing.event.TreeExpansionEvent p1) {
        //DO nothing
    }
     */
    
    private class MyTreeNode extends DefaultMutableTreeNode {
        
        static final long serialVersionUID = 2478352100056378993L;
        
        public MyTreeNode() {
            super();
        }
        
        public MyTreeNode(Object userObject) {
            super(userObject);
        }

        public boolean isLeaf() {
            return !getAllowsChildren();
        }
    }
    
    private class MyTreeCellRenderer extends DefaultTreeCellRenderer {
        
        private String DEFAULT_FOLDER = "/org/openide/loaders/defaultFolder.gif"; // NOI18N
        private String DEFAULT_OPEN_FOLDER = "/org/openide/loaders/defaultFolderOpen.gif"; // NOI18N
        
        private Image FOLDER_ICON = (Image) UIManager.get("Nb.Explorer.Folder.icon"); // NOI18N
        private Image OPEN_FOLDER_ICON = (Image) UIManager.get("Nb.Explorer.Folder.openedIcon"); // NOI18N

        static final long serialVersionUID = -1075722862531035018L;
        
        
        /** Finds the component that is capable of drawing the cell in a tree.
        * @param value value can be either Node or a value produced by models (like
        *   NodeTreeModel, etc.)
        * @return component to draw the value
        */
        public Component getTreeCellRendererComponent(
            JTree tree, Object value,
            boolean sel, boolean expanded,
            boolean leaf, int row, boolean hasFocus 
        ) { 
            Component comp = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
                if (node != null) {
                    String file = (String) node.getUserObject();
                    if (file != null) {
                        file = file.replace(File.separatorChar, '/');
                        int nameIndex = file.lastIndexOf('/');
                        if (nameIndex < 0) nameIndex = 0;
                        else nameIndex++;
                        label.setText(file.substring(nameIndex));
                    }
                }
                if (!expanded) {
                    if (FOLDER_ICON != null) {
                        label.setIcon(new ImageIcon(FOLDER_ICON));
                    } else {
                        java.net.URL url1 = this.getClass().getResource(DEFAULT_FOLDER);
                        label.setIcon(new ImageIcon(url1));
                    }
                } else {
                    if (OPEN_FOLDER_ICON != null) {
                        label.setIcon(new ImageIcon(OPEN_FOLDER_ICON));
                    } else {
                        java.net.URL url2 = this.getClass().getResource(DEFAULT_OPEN_FOLDER);
                        label.setIcon(new ImageIcon(url2));
                    }
                }

            }
            return comp;
        }
        
    }

    public void addTreePropertyChangeListener (java.beans.PropertyChangeListener listener) {
    	trRelMount.addPropertyChangeListener (listener);
    }

    public void removeTreePropertyChangeListener (java.beans.PropertyChangeListener listener) {
    	trRelMount.removePropertyChangeListener (listener);
    }
    
    /*
    class MyFile extends java.io.File {
        private boolean local;

        public MyFile(String absolutePath) {
          super(absolutePath);
          local = true;
        }
        public void setLocal(boolean loc) {
          local = loc;  
        }
        public boolean isLocal() {
          return local;  
        }
    }
    
    class MySelectionModel extends DefaultTreeSelectionModel {
       public MySelectionModel() {
         super();
         setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
       }
       
       public void setSelectionPath(TreePath path) {
         DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
         MyFile userObj = (MyFile)node.getUserObject();
         if (userObj.isLocal()) {
             super.clearSelection();
             return;
         }
         super.setSelectionPath(path);
       }
    }
     */
}
