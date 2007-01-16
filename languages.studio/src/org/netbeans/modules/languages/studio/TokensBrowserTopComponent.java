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

package org.netbeans.modules.languages.studio;

import javax.swing.SwingUtilities;
import org.netbeans.api.lexer.Token;
import org.netbeans.api.lexer.TokenHierarchy;
import org.netbeans.api.lexer.TokenSequence;
import org.openide.ErrorManager;
import org.openide.cookies.EditorCookie;
import org.openide.nodes.Node;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import java.util.Enumeration;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.border.Border;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.UIManager;

/**
 * Top component which displays something.
 */
final class TokensBrowserTopComponent extends TopComponent {
    
    private static final String PREFERRED_ID = "TokensBrowserTopComponent";
    private static final long   serialVersionUID = 1L;
    private static TokensBrowserTopComponent instance;
    
    private JList               list;
    private Listener            listener;
    private HighlighterSupport  highlighting = new HighlighterSupport (Color.yellow);
    private boolean             listen = true;
    private CaretListener       caretListener;
    private JEditorPane         lastPane;
    private DocumentListener    documentListener;
    private AbstractDocument    lastDocument;
    
    
    private TokensBrowserTopComponent () {
        initComponents ();
        setLayout (new BorderLayout ());
        list = new JList ();
        list.setCellRenderer (new Renderer ());
        list.addListSelectionListener (new ListSelectionListener () {
            public void valueChanged (ListSelectionEvent e) {
                if (!listen) return;
                mark ();
            }
        });
        list.addFocusListener (new FocusListener () {
            public void focusGained (FocusEvent e) {
                mark ();
            }
            public void focusLost (FocusEvent e) {
                mark ();
            }
        });
        add (new JScrollPane (list), BorderLayout.CENTER);
        setName (NbBundle.getMessage (TokensBrowserTopComponent.class, "CTL_TokensBrowserTopComponent"));
        setToolTipText (NbBundle.getMessage (TokensBrowserTopComponent.class, "HINT_TokensBrowserTopComponent"));
//        setIcon(Utilities.loadImage(ICON_PATH, true));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link findInstance}.
     */
    public static synchronized TokensBrowserTopComponent getDefault () {
        if (instance == null) {
            instance = new TokensBrowserTopComponent ();
        }
        return instance;
    }
    
    /**
     * Obtain the TokensBrowserTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized TokensBrowserTopComponent findInstance () {
        TopComponent win = WindowManager.getDefault ().findTopComponent (PREFERRED_ID);
        if (win == null) {
            ErrorManager.getDefault ().log (ErrorManager.WARNING, "Cannot find TokensBrowser component. It will not be located properly in the window system.");
            return getDefault ();
        }
        if (win instanceof TokensBrowserTopComponent) {
            return (TokensBrowserTopComponent)win;
        }
        ErrorManager.getDefault ().log (ErrorManager.WARNING, "There seem to be multiple components with the '" + PREFERRED_ID + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault ();
    }
    
    public int getPersistenceType () {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    protected void componentShowing () {
        refresh ();
        if (listener == null)
            listener = new Listener (this);
    }

    protected void componentHidden () {
        if (listener != null) {
            listener.remove ();
            listener = null;
        }
        if (lastPane != null)
            lastPane.removeCaretListener (caretListener);
        lastPane = null;
        if (lastDocument != null)
            lastDocument.removeDocumentListener (documentListener);
        lastDocument = null;
        highlighting.removeHighlight ();
    }
    
    /** replaces this in object stream */
    public Object writeReplace () {
        return new ResolvableHelper ();
    }
    
    protected String preferredID () {
        return PREFERRED_ID;
    }
    
    private void mark () {
        Node[] ns = TopComponent.getRegistry ().getActivatedNodes ();
        if (ns.length == 1 && list.isFocusOwner ()) {
            EditorCookie editorCookie = (EditorCookie) ns [0].getLookup ().
                lookup (EditorCookie.class);
            if (editorCookie != null) {
                MToken t = (MToken) list.getSelectedValue ();
                if (t != null) {
                    highlighting.highlight (
                        editorCookie.getDocument (), 
                        t.offset
                    );
                    return;
                }
            }
        }
        highlighting.removeHighlight ();
    }
    
    
    private AbstractDocument getCurrentDocument () {
        Node[] ns = TopComponent.getRegistry ().getActivatedNodes ();
        if (ns.length != 1) return null;
        EditorCookie editorCookie = (EditorCookie) ns [0].getLookup ().
            lookup (EditorCookie.class);
        if (editorCookie == null) return null;
        if (editorCookie.getOpenedPanes () == null) return null;
        if (editorCookie.getOpenedPanes ().length < 1) return null;
        JEditorPane pane = editorCookie.getOpenedPanes () [0];
        
        if (caretListener == null)
            caretListener = new CListener ();
        if (lastPane != null && lastPane != pane) {
            lastPane.removeCaretListener (caretListener);
            lastPane = null;
        }
        if (lastPane == null) {
            pane.addCaretListener (caretListener);
            lastPane = pane;
        }

        AbstractDocument doc = (AbstractDocument) editorCookie.getDocument ();
        if (documentListener == null)
            documentListener = new CDocumentListener ();
        if (lastDocument != null && lastDocument != doc) {
            lastDocument.removeDocumentListener (documentListener);
            lastDocument = null;
        }
        if (lastDocument == null) {
            doc.addDocumentListener (documentListener);
            lastDocument = doc;
        }
        return doc;
    }
    
    private RequestProcessor.Task task;
    
    private void refreshLater () {
        if (task != null) task.cancel ();
        task = RequestProcessor.getDefault ().post (
            new Runnable () {
                public void run () {
                    System.out.println("refresh");
                    refresh ();
                    task = null;
                }
            }, 
            1000
        );
    }
    
    private void refresh () {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AbstractDocument doc = getCurrentDocument ();
                DefaultListModel model = new DefaultListModel ();
                if (doc != null)
                    try {
                        doc.readLock ();
                        TokenHierarchy tokenHierarchy = TokenHierarchy.get (doc);
                        TokenSequence ts = tokenHierarchy.tokenSequence ();
                        while (ts.moveNext ())
                            model.addElement (new MToken (ts));
                    } finally {
                        doc.readUnlock ();
                    }
                list.setModel (model);
            }
        });
    }
    
    
    // innerclasses ............................................................
    
    private static class MToken {
        String type;
        String identifier;
        int offset;
        
        MToken (TokenSequence ts) {
            Token t = ts.token ();
            type = t.id ().name ();
            identifier = t.text ().toString ();
            offset = ts.offset ();
        }
    }
    
    class CDocumentListener implements DocumentListener {
        public void insertUpdate (DocumentEvent e) {
            refreshLater ();
        }

        public void removeUpdate (DocumentEvent e) {
            refreshLater ();
        }

        public void changedUpdate (DocumentEvent e) {
            refreshLater ();
        }
    }
    
    class CListener implements CaretListener {
        public void caretUpdate (CaretEvent e) {
            int position = e.getDot ();
            ListModel m = list.getModel ();
            if (!(m instanceof DefaultListModel)) return;
            DefaultListModel model = (DefaultListModel) m;
            MToken last = null;
            listen = false;
            Enumeration en = model.elements ();
            while (en.hasMoreElements ()) {
                MToken t = (MToken) en.nextElement ();
                if (t.offset > position) {
                    list.setSelectedValue (last, true);
                    listen = true;
                    return;
                }
                last = t;
            }
            listen = true;
        }
    }

    private static class Renderer extends DefaultListCellRenderer {
        
        private String e (String t) {
            StringBuilder sb = new StringBuilder ();
            int i, k = t.length ();
            for (i = 0; i < k; i++) {
                if (t.charAt (i) == '\t')
                    sb.append ("\\t");
                else
                if (t.charAt (i) == '\r')
                    sb.append ("\\r");
                else
                if (t.charAt (i) == '\n')
                    sb.append ("\\n");
                else
                    sb.append (t.charAt (i));
            }
            return sb.toString ();
        }

        public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus)
        {
            setComponentOrientation(list.getComponentOrientation());
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            }
            else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            MToken t = (MToken) value;
            StringBuilder sb = new StringBuilder ().
                append ('<').
                append (t.type).
                append (",\"").
                append (e (t.identifier)).
                append ("\">");
            setText (sb.toString ());

            setEnabled(list.isEnabled());
            setFont(list.getFont());

            Border border = null;
            if (cellHasFocus) {
                if (isSelected) {
                    border = UIManager.getBorder("List.focusSelectedCellHighlightBorder");
                }
                if (border == null) {
                    border = UIManager.getBorder("List.focusCellHighlightBorder");
                }
            } else {
                border = noFocusBorder;
            }
            setBorder(border);

            return this;
        }
    }
    
    final static class ResolvableHelper implements Serializable {
        private static final long serialVersionUID = 1L;
        public Object readResolve () {
            return TokensBrowserTopComponent.getDefault ();
        }
    }
    
    private static class Listener implements PropertyChangeListener {
        
        private WeakReference component;
        
        
        Listener (TokensBrowserTopComponent c) {
            component = new WeakReference (c);
            TopComponent.getRegistry ().addPropertyChangeListener (this);
        }

        TokensBrowserTopComponent getComponent () {
            TokensBrowserTopComponent c = (TokensBrowserTopComponent) component.get ();
            if (c != null) return c;
            remove ();
            return null;
        }
        
        void remove () {
            TopComponent.getRegistry ().removePropertyChangeListener (this);
        }
        
        public void propertyChange (PropertyChangeEvent evt) {
            TokensBrowserTopComponent c = getComponent ();
            if (c == null) return;
            c.refresh ();
        }
    }
}
