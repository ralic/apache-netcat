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

package org.netbeans.modules.docbook;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import org.netbeans.api.docbook.ContentHandlerCallback;
import org.netbeans.api.docbook.ParseJob;
import org.netbeans.api.docbook.ParsingService;

import org.openide.cookies.*;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.text.Annotation;
import org.openide.text.CloneableEditor;
import org.openide.text.DataEditorSupport;
import org.openide.text.Line;
import org.openide.windows.CloneableOpenSupport;
import org.xml.sax.Attributes;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class DocBookEditorSupport extends DataEditorSupport implements EditorCookie, OpenCookie, CloseCookie, PrintCookie {

    public DocBookEditorSupport(DocBookDataObject obj) {
        super(obj, new DocBookEnv(obj));
        setMIMEType("text/xml");
    }

    protected boolean notifyModified() {
        if (!super.notifyModified()) {
            return false;
        }
        DocBookDataObject obj = (DocBookDataObject)getDataObject();
        if (obj.getCookie(SaveCookie.class) == null) {
            obj.setModified(true);
            obj.addSaveCookie(new Save());
        }
        return true;
    }

    protected void notifyUnmodified() {
        DocBookDataObject obj = (DocBookDataObject)getDataObject();
        SaveCookie save = obj.getCookie(SaveCookie.class);
        if (save != null) {
            obj.removeSaveCookie(save);
            obj.setModified(false);
        }
        super.notifyUnmodified();
    }

    private class Save implements SaveCookie {
        public void save() throws IOException {
            saveDocument();
            getDataObject().setModified(false);
            refreshAnnotations();
        }
    }

    private void refreshAnnotations() {
        ParsingService serv = getDataObject().getNodeDelegate().getLookup().lookup(ParsingService.class);
        if (serv != null) {
            serv.enqueue(new AnnotationCallback(this,
                    (DocBookDataObject) getDataObject()));
        }
    }

    private static class DocBookEnv extends DataEditorSupport.Env {

        private static final long serialVersionUID = 1L;

        public DocBookEnv(DocBookDataObject obj) {
            super(obj);
        }

        protected FileObject getFile() {
            return getDataObject().getPrimaryFile();
        }

        protected FileLock takeLock() throws IOException {
            return ((DocBookDataObject)getDataObject()).getPrimaryEntry().takeLock();
        }

        public CloneableOpenSupport findCloneableOpenSupport() {
            return getDataObject().getCookie(DocBookEditorSupport.class);
        }
    }

    protected void initializeCloneableEditor(CloneableEditor editor) {
        try {
            super.initializeCloneableEditor(editor);
            editor.getEditorPane().setTransferHandler(new TextAndImageTransferHandler(
                    editor.getEditorPane()));
            refreshAnnotations();
        } catch (IllegalStateException ise) {
            //Normal during restart if module has created a dataobject, then
            //immediately been unloaded and reloaded.  The editor toolbar
            //tries to get info from a node whose dataobject was destroyed
            //when the module was unloaded.  Bug is in the Ant Debugger module,
            //which is receiving property changes and not checking validity.

            //No worries about initialization not having completed - this
            //editor is going to be replaced completely anyway, it's being
            //initialized for nothing.
        }
    }

    public boolean close() {
        boolean result = super.close();
        if (result) {
            ParseJob j = getJob();
            if (j != null) {
                j.cancel();
            }
        }
        return result;
    }

    private ParseJob job;
    private synchronized void setJob (ParseJob job) {
        this.job = job;
    }

    private synchronized ParseJob getJob() {
        return job;
    }

    Set <Ann> annotations = Collections.synchronizedSet(new HashSet <Ann> ());

    private JList list;
    private JSplitPane split;
    private JScrollPane scroll;
    @Override
    protected Component wrapEditorComponent(final Component editorComponent) {
        list = new JList();
        list.setModel (new DefaultListModel());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer (new Ren());
        list.setPrototypeCellValue(new Ann ("xyz", true, null));
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked (MouseEvent me) {
                if (!me.isPopupTrigger()) {
                    int ix = list.locationToIndex(me.getPoint());
                    if (ix >= 0) {
                        Ann ann = (Ann) list.getModel().getElementAt(ix);
                        ann.line.show(Line.SHOW_GOTO);
                        getOpenedPanes()[0].requestFocus();
                    }
                }
            }
        });
        scroll = new JScrollPane(list);
        scroll.setBorder (BorderFactory.createEmptyBorder());
        scroll.setViewportBorder(BorderFactory.createEmptyBorder());
        split = new JSplitPane();
        split.setTopComponent(editorComponent);
        split.setBottomComponent(scroll);
        split.setOrientation(JSplitPane.VERTICAL_SPLIT);
        split.setDividerLocation (500);
        return split;
    }

    private static final class Ren extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Color fg;
            if (isSelected) {
                fg = list.getForeground();
            } else {
                Ann ann = (Ann) value;
                Line line = ann.getLine();
                fg = ann.isWarning() ? new Color (25, 52, 180) :
                    new Color (180, 0, 0);
            }
            Component result = super.getListCellRendererComponent(list,
                    value, index, isSelected, cellHasFocus);

            result.setForeground(fg);
            return result;
        }

        public void firePropertyChange (String s, Object o, Object n) {
            //do nothing - performance
        }
    }

    private static final class AnnotationCallback extends ContentHandlerCallback <H> implements Runnable {
        private final DocBookEditorSupport editor;

        public AnnotationCallback (DocBookEditorSupport editor, DocBookDataObject dob) {
            super (new H(dob, editor.annotations));
            this.editor = editor;
        }

        protected void start(FileObject f, ParseJob job) {
            editor.annotations.clear();
            editor.setJob (job);
        }

        protected void done(FileObject f, ParseJob job) {
            super.done(f, job);
            EventQueue.invokeLater (this);
        }

        public void run() {
            Ann[] anns = editor.annotations.toArray(new Ann[0]);
            DefaultListModel mdl = (DefaultListModel) editor.list.getModel();
            mdl.clear();
            System.err.println("ADD " + anns.length + " to list model ");
            Arrays.sort (anns);
            for (int i = 0; i < anns.length; i++) {
                System.err.println("Add to model " + anns[i]);
                mdl.addElement(anns[i]);
            }
        }
    }

    private static final class H extends DefaultHandler implements ErrorHandler {
        private final DocBookDataObject dob;
        private Locator loc;
        private final Set <Ann> annotations;
        H (DocBookDataObject dob, Set <Ann> annotations) {
            this.dob = dob;
            this.annotations = annotations;
        }

        public void setDocumentLocator (Locator locator) {
            this.loc = locator;
        }

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (loc != null && shouldHaveId (localName) && attributes.getValue("id") == null) {
                createAnnotation ("Chapters, figures and sections should have an ID " +
                        "assigned", loc.getLineNumber(), loc.getColumnNumber(),
                        true);
            }
        }

        private Ann createAnnotation (String text, int lineNumber, int col, boolean warn) {
            LineCookie ck = dob.getCookie (LineCookie.class);
            if (ck != null) {
                Line l = ck.getLineSet().getCurrent(lineNumber);
                Ann ann = new Ann (text, warn, l);
                annotations.add (ann);
                ann.attach (l);
                return ann;
            }
            return null;
        }

        private boolean shouldHaveId (String localName) {
            return "section".equals(localName) ||
                    "chapter".equals(localName) ||
                    "appendix".equals(localName) ||
                    "figure".equals(localName);
        }

        public void endElement(String uri, String localName, String qName) throws SAXException {
        }

        public void warning(SAXParseException e) throws SAXException {
            createAnnotation (e.getLocalizedMessage(), e.getLineNumber(),
                    e.getColumnNumber(), true);
        }

        public void error(SAXParseException e) throws SAXException {
            createAnnotation (e.getLocalizedMessage(), e.getLineNumber(),
                    e.getColumnNumber(), true);
        }

        public void fatalError(SAXParseException e) throws SAXException {
            createAnnotation (e.getLocalizedMessage(), e.getLineNumber(),
                    e.getColumnNumber(), false);
        }

        private void createAnnotation (SAXParseException e, boolean warn) {
            String s = e.getLocalizedMessage();
            if (s == null) s = e.getMessage();
            if (s == null) s = "Parse error at line " + e.getLineNumber();
            createAnnotation (s, e.getLineNumber(),
                    e.getColumnNumber(), warn);
        }

        public boolean equals (Object o) {
            return o instanceof H && ((H) o).dob ==
                    dob;
        }

        public int hashCode() {
            return dob.hashCode() * 17;
        }
    }

    private static final class Ann extends Annotation implements Comparable {
        private final String desc;
        private final boolean warn;
        private final Line line;
        Ann (String desc, boolean warn, Line line) {
            assert desc != null;
            this.desc = desc;
            this.warn = warn;
            this.line = line;
        }

        boolean isWarning() {
            return warn;
        }

        Line getLine() {
            return line;
        }

        public String getAnnotationType() {
            return warn ? "org-netbeans-modules-docbook-warning" : //NOI18N
                "org-netbeans-modules-docbook-error"; //NOI18N
        }

        public String getShortDescription() {
            return desc;
        }

        public String toString() {
            return "[" + (line == null ? 20 : line.getLineNumber())
                    + "] " + desc;
        }

        public boolean equals (Object o) {
            if (o instanceof Ann && line != null && ((Ann)o).line != null) {
                return ((Ann) o).line.equals(line)
                    && ((Ann) o).getShortDescription().equals(
                    getShortDescription());
            } else {
                return false;
            }
        }

        public int hashCode() {
            return (line == null ? 1 : line.hashCode()) * desc.hashCode();
        }

        int getLineNumber() {
            return line == null ? 0 : line.getLineNumber();
        }

        public int compareTo (Object o) {
            if (o instanceof Ann) {
                Ann other = (Ann) o;
                boolean w = other.warn;
                int amt = warn == w ? 0 : warn ? 100000 : -100000;
                return (getLineNumber() - ((Ann) o).getLineNumber())
                        + amt;
            }
            return 0;
        }
    }
}
