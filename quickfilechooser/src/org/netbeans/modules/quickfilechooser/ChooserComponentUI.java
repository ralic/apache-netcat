/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2005 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.quickfilechooser;

import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.prefs.Preferences;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicFileChooserUI;

/**
 * The UI for the {@link JFileChooser}.
 * @author Jesse Glick, Tim Boudreau
 */
public class ChooserComponentUI extends BasicFileChooserUI {
    
    private JTextField text;
    private DefaultListModel completionsModel;
    private JList completions;
    private String maximalCompletion;
    private boolean currentDirectoryChanging;
    private JButton approve;
    private JPanel buttons;
    
    public ChooserComponentUI(JFileChooser jfc) {
        super(jfc);
    }
    
    public static ComponentUI createUI(JComponent c) {
        return new ChooserComponentUI((JFileChooser) c);
    }
    
    private JFileChooser filechooser = null;
    private JComboBox box = null;
    public void installComponents(JFileChooser fc) {
        super.installComponents(fc);
        fc.setLayout(new BorderLayout());
        filechooser = fc;
        
        String[] hist = getHistory();
        JPanel histPanel = new JPanel();
        histPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        box = new JComboBox(hist);
        
        box.addActionListener(new HAL());
        histPanel.setLayout(new BorderLayout());
        JLabel histInstructions = new JLabel(getBundle().getString("LBL_History"));
        histInstructions.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        histInstructions.setLabelFor(box);
        histPanel.add(box, BorderLayout.CENTER);
        histPanel.add(histInstructions, BorderLayout.WEST);
        if (getHistory().length == 0) {
            box.setEnabled(false);
        }
        
        text = new JTextField(100);
        text.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
                Collections.singleton(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_TAB, KeyEvent.CTRL_DOWN_MASK)));
        text.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "complete");
        text.getActionMap().put("complete", new CompleteAction());
        text.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, InputEvent.CTRL_DOWN_MASK), "delete-path-component");
        text.getActionMap().put("delete-path-component", new DeletePathComponentAction());
        text.setFont(new Font("Monospaced", Font.PLAIN, text.getFont().getSize())); // NOI18N
        Action up = new UpDownAction(true);
        Action down = new UpDownAction(false);
        text.getActionMap().put("up", up);
        text.getActionMap().put("down", down);
        text.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up"); //NOI18N
        text.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0),
                "down"); //NOI18N
        
        text.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                refreshCompletions();
            }
            public void removeUpdate(DocumentEvent e) {
                refreshCompletions();
            }
            public void changedUpdate(DocumentEvent e) {}
        });
        JPanel pnl = new JPanel();
        pnl.setLayout(new BorderLayout());
        pnl.add(text, BorderLayout.CENTER);
        JLabel instructions = new JLabel(getBundle().getString("LBL_TextField"));
        instructions.setLabelFor(text);
        instructions.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 0));
        histPanel.add(instructions, BorderLayout.SOUTH); //NOI18N
        pnl.setBorder(BorderFactory.createEmptyBorder(12, 12, 0, 12));
        pnl.add(histPanel, BorderLayout.NORTH);
        
        fc.add(pnl, BorderLayout.PAGE_START);
        completionsModel = new DefaultListModel();
        completions = new JList(completionsModel);
        completions.addMouseListener(new CML());
        
        completions.setVisibleRowCount(25);
        completions.setEnabled(false);
        completions.setCellRenderer(new FileCellRenderer());
        JScrollPane jsc = new JScrollPane(completions);
        JPanel pnl2 = new JPanel();
        pnl2.setLayout(new BorderLayout());
        pnl2.add(jsc, BorderLayout.CENTER);
        pnl2.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 0));
        
        fc.add(pnl2, BorderLayout.CENTER);
        approve = new JButton(getApproveButtonText(fc));
        approve.addActionListener(getApproveSelectionAction());
        approve.setDefaultCapable(true);
        JButton cancel = new JButton(cancelButtonText);
        cancel.addActionListener(getCancelSelectionAction());
        buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));
        buttons.add(approve);
        buttons.add(cancel);
        fc.add(buttons, BorderLayout.SOUTH);
        getAccessoryPanel().setBorder(
                BorderFactory.createEmptyBorder(12,0,12,12));
        fc.add(getAccessoryPanel(), BorderLayout.EAST);
        JComponent x = fc.getAccessory();
        if (x != null) {
            getAccessoryPanel().add(x);
        }
        box.setFocusable(false);
        updateButtons();
    }
    
    private static ResourceBundle getBundle() {
        // Avoid using NbBundle, so we can work as a library in any app.
        return ResourceBundle.getBundle("org.netbeans.modules.quickfilechooser.Bundle");
    }
    
    private void updateButtons() {
        File f = getFileChooser().getSelectedFile();
        
        if (getFileChooser() != null && getFileChooser().getFileFilter() != null && f != null) {
            boolean accepted = getFileChooser().getFileFilter().accept(f);
            getApproveSelectionAction().setEnabled(accepted);
            approve.setEnabled(accepted);
            if (accepted && approve.isShowing() && approve.getTopLevelAncestor() instanceof JDialog) {
                JDialog dlg = (JDialog) approve.getTopLevelAncestor();
                dlg.getRootPane().setDefaultButton(approve);
            } else {
                approve.setEnabled(false);
            }
        } else {
            getApproveSelectionAction().setEnabled(false);
            approve.setEnabled(false);
        }
    }
    
    
    protected JButton getApproveButton(JFileChooser fc) {
        return approve;
    }
    
    
    private class HAL implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            updateFromHistory();
        }
    }
    
    private void updateFromHistory() {
        String name = box.getSelectedItem().toString();
        if (!name.endsWith(File.separator)) {
            name += File.separatorChar;
        }
        text.setText(name);
        refreshCompletions();
        text.requestFocus();
    }
    
    public void uninstallComponents(JFileChooser fc) {
        fc.removeAll();
        text = null;
        completions = null;
        completionsModel = null;
        super.uninstallComponents(fc);
    }
    
    public String getFileName() {
        return normalizeFile(text.getText());
    }
    
    private static String normalizeFile(String text) {
        if (text.equals("~")) {
            return System.getProperty("user.home");
        } else if (text.startsWith("~" + File.separatorChar)) {
            return System.getProperty("user.home") + text.substring(1);
        } else {
            int i = text.lastIndexOf("//");
            if (i != -1) {
                // Treat /home/me//usr/local as /usr/local
                // (so that you can use "//" to start a new path, without selecting & deleting)
                return text.substring(i + 1);
            }
            i = text.lastIndexOf(File.separatorChar + "~" + File.separatorChar);
            if (i != -1) {
                // Treat /usr/local/~/stuff as /home/me/stuff
                return System.getProperty("user.home") + text.substring(i + 2);
            }
            return text;
        }
    }
    
    public PropertyChangeListener createPropertyChangeListener(JFileChooser fc) {
        return new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                String name = e.getPropertyName();
                if (JFileChooser.ACCESSORY_CHANGED_PROPERTY.equals(name)) {
                    JComponent x = (JComponent) e.getOldValue();
                    if (x != null) {
                        getAccessoryPanel().remove(x);
                    }
                    x = (JComponent) e.getNewValue();
                    if (x != null) {
                        getAccessoryPanel().add(x);
                    }
                } else if (JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(name) && !currentDirectoryChanging) {
                    currentDirectoryChanging = true;
                    try {
                        String t = getFileChooser().getCurrentDirectory().getAbsolutePath() + File.separatorChar;
                        text.setText(t);
                        text.setCaretPosition(t.length());
                    } finally {
                        currentDirectoryChanging = false;
                    }
                } else if (JFileChooser.CONTROL_BUTTONS_ARE_SHOWN_CHANGED_PROPERTY.equals(name)) {
                    buttons.setVisible(getFileChooser().getControlButtonsAreShown());
                }
                // XXX may have to handle JFileChooser.FILE_CHANGED_PROPERTY too
            }
        };
    }
    
    private void refreshCompletions() {
        completionsModel.clear();
        maximalCompletion = null;
        String name = getFileName();
        int slash = name.lastIndexOf(File.separatorChar);
        if (slash != -1) {
            String prefix = name.substring(0, slash + 1);
            String suffix = name.substring(slash + 1);
            int suffixLen = suffix.length();
            File d = new File(prefix);
            if (d.isDirectory()) {
                String[] kids = d.list();
                if (kids != null) {
                    Arrays.sort(kids, Collator.getInstance());
                    for (int i = 0; i < kids.length; i++) {
                        File kid = new File(d, kids[i]);
                        if (getFileChooser().getFileSelectionMode() == JFileChooser.DIRECTORIES_ONLY && !kid.isDirectory()) {
                            continue;
                        }
                        if (kids[i].regionMatches(true, 0, suffix, 0, suffixLen)) {
                            completionsModel.addElement(kid);
                            if (maximalCompletion == null) {
                                maximalCompletion = kids[i];
                            } else {
                                int p = maximalCompletion.length();
                                while (p > 0) {
                                    if (kids[i].regionMatches(true, 0, maximalCompletion, 0, p)) {
                                        break;
                                    }
                                    p--;
                                }
                                maximalCompletion = maximalCompletion.substring(0, p);
                            }
                        }
                    }
                }
            }
        }
        // Fire changes to interested listeners. Note that we only support a single
        // file selection, but some listeners may be asking for getSelectedFiles, so humor them.
        if (!currentDirectoryChanging) {
            currentDirectoryChanging = true;
            try {
                File file = new File(getFileName());
                getFileChooser().setSelectedFiles(new File[] {file});
                setDirectorySelected(file.exists() && file.isDirectory());
            } finally {
                currentDirectoryChanging = false;
            }
        }
        updateButtons();
    }
    
    private final class CompleteAction extends AbstractAction {
        
        public void actionPerformed(ActionEvent e) {
            String name = getFileName();
            int slash = name.lastIndexOf(File.separatorChar);
            assert slash != -1;
            String newname = maximalCompletion != null ? name.substring(0, slash + 1) + maximalCompletion : null;
            File newnameF = newname != null ? new File(newname) : null;
            if (newnameF != null && newnameF.isDirectory() && !newname.endsWith(File.separator)) {
                // Also check that there is no non-dir completion (e.g. .../nb_all/nbbuild/build{,.xml,.properties})
                String[] siblings = newnameF.getParentFile().list();
                boolean complete = true;
                String me = newnameF.getName();
                for (int i = 0; i < siblings.length; i++) {
                    if (siblings[i].startsWith(me) && !siblings[i].equals(me)) {
                        complete = false;
                        break;
                    }
                }
                if (complete && !newname.endsWith(File.separator)) {
                    newname += File.separatorChar;
                }
            }
            if (maximalCompletion != null && !newname.equals(name)) {
                text.setText(newname);
            } else {
                // Just scroll completions pane if necessary.
                int start = completions.getFirstVisibleIndex();
                int end = completions.getLastVisibleIndex();
                if (start != -1 && end != -1) { // something visible now
                    if (end < completionsModel.size() - 1) {
                        // Scroll down some. Keep two overlap lines, since lines can be half-visible.
                        completions.ensureIndexIsVisible(Math.min(2 * end - start - 1, completionsModel.size() - 1));
                    } else if (start > 0) {
                        // Scroll back up to the top.
                        completions.ensureIndexIsVisible(0);
                    }
                }
            }
        }
    }
    
    private final class DeletePathComponentAction extends AbstractAction {
        
        public void actionPerformed(ActionEvent e) {
            String t = text.getText();
            int cut = Math.max(t.lastIndexOf(File.separatorChar), t.lastIndexOf('.') - 1);
            String newText;
            if (cut == -1) {
                newText = "";
            } else if (cut == t.length() - 1) {
                // XXX when running in JDK 1.4 (but not 1.6, didn't check 1.5)
                // it seems that DefaultEditorKit.DeletePrevCharAction is run after
                // this action (bound to unmodified BACK_SPACE), for no obvious reason
                newText = t.substring(0, cut);
            } else {
                newText = t.substring(0, cut + 1);
            }
            text.setText(newText);
        }
        
    }
    
    private final class FileCellRenderer extends DefaultListCellRenderer/*<File>*/ {
        
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            File f = (File) value;
            Component soop = super.getListCellRendererComponent(list, f.getName(), index, isSelected, cellHasFocus);
            setIcon(getFileChooser().getIcon(f));
            setEnabled(true); // don't draw text or icons grayed out
            return soop;
        }
        
    }
    
    public Action getApproveSelectionAction() {
        return new ProxyApproveSelectionAction(super.getApproveSelectionAction());
    }
    
    private static void updateHistory(JFileChooser jfc) {
        File f = jfc.getSelectedFile();
        String pth = f.getParent();
        if (history == null) {
            history = new ArrayList();
        }
        if (!history.contains(pth)) {
            history.add(pth);
            StringBuffer buf = new StringBuffer();
            for (Iterator i=new HashSet(history).iterator(); i.hasNext();) {
                buf.append(i.next());
                if (i.hasNext()) {
                    buf.append(File.pathSeparatorChar);
                }
            }
            Preferences prefs = Preferences.userNodeForPackage(ChooserComponentUI.class);
            prefs.put(KEY, buf.toString());
        }
    }
    
    private static final String KEY = "recentFolders";
    
    private static List/*<File>*/ history = null;
    private static String[] getHistory() {
        if (history == null) {
            loadHistory();
        }
        if (history != null) {
            String[] result = (String[]) history.toArray(new String[history.size()]);
            Arrays.sort(result);
            return result;
        } else {
            return new String[0];
        }
    }
    
    private static void loadHistory() {
        Preferences prefs = Preferences.userNodeForPackage(ChooserComponentUI.class);
        String hist = prefs.get(KEY, "");
        List h = new ArrayList();
        if (hist.length() > 0) {
            for (StringTokenizer tok = new StringTokenizer(hist, File.pathSeparator); tok.hasMoreTokens();) {
                String f = tok.nextToken();
                if ((new File(f)).exists()) {
                    h.add(f);
                }
            }
        }
        history = h;
    }
    
    private class ProxyApproveSelectionAction implements Action, PropertyChangeListener {
        private final Action delegate;
        public ProxyApproveSelectionAction(Action delegate) {
            this.delegate = delegate;
        }
        
        public Object getValue(String key) {
            return delegate.getValue(key);
        }
        
        public void putValue(String key, Object value) {
            delegate.putValue(key, value);
        }
        
        public void setEnabled(boolean b) {
            delegate.setEnabled(b);
        }
        
        public boolean isEnabled() {
            return delegate.isEnabled();
        }
        
        private List l = new ArrayList();
        public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
            l.add(listener);
            if (l.size() == 1) {
                delegate.addPropertyChangeListener(this);
            }
        }
        
        public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
            l.remove(listener);
            if (l.isEmpty()) {
                delegate.removePropertyChangeListener(this);
            }
        }
        
        public void actionPerformed(ActionEvent e) {
            delegate.actionPerformed(e);
            updateHistory(filechooser);
        }
        
        public void propertyChange(PropertyChangeEvent old) {
            PropertyChangeListener[] pcl;
            synchronized (this) {
                pcl = (PropertyChangeListener[]) l.toArray(new PropertyChangeListener[l.size()]);
            }
            if (pcl.length > 0) {
                PropertyChangeEvent nue = new PropertyChangeEvent(this,
                        old.getPropertyName(), old.getOldValue(),
                        old.getNewValue());
                for (int i=0; i < pcl.length; i++) {
                    pcl[i].propertyChange(nue);
                }
            }
        }
    }
    
    private class CML extends MouseAdapter {
        public void mousePressed(MouseEvent me) {
            JList jl = (JList) me.getSource();
            int idx = jl.locationToIndex(me.getPoint());
            if (idx != -1) {
                text.setText(jl.getModel().getElementAt(idx).toString());
            }
        }
    }
    
    private class UpDownAction extends AbstractAction {
        private final boolean up;
        UpDownAction(boolean up) {
            this.up = up;
        }
        public void actionPerformed(ActionEvent ae) {
            int sz = box.getModel().getSize();
            int sel = box.getSelectedIndex();
            if (up) {
                sel++;
            } else {
                sel--;
            }
            if (sel < 0) {
                sel = box.getModel().getSize() -1;
            } else if (sel >= box.getModel().getSize()) {
                sel = 0;
            }
            box.setSelectedIndex(sel);
            updateFromHistory();
        }
        public boolean isEnabled() {
            return box.getModel().getSize() > 0;
        }
    }
}
