/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 */
package org.netbeans.modules.spellchecker;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.Position;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.editor.Coloring;
import org.netbeans.modules.editor.highlights.spi.DefaultHighlight;
import org.netbeans.modules.editor.highlights.spi.Highlight;
import org.netbeans.modules.editor.highlights.spi.Highlighter;
import org.netbeans.modules.spellchecker.hints.DictionaryBasedHintsProvider;
import org.netbeans.modules.spellchecker.spi.dictionary.Dictionary;
import org.netbeans.modules.spellchecker.api.LocaleQuery;
import org.netbeans.modules.spellchecker.spi.dictionary.DictionaryProvider;
import org.netbeans.modules.spellchecker.spi.language.TokenList;
import org.netbeans.modules.spellchecker.spi.language.TokenListProvider;
import org.openide.ErrorManager;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.text.NbDocument;
import org.openide.util.Lookup;
import org.openide.util.Lookup.Template;
import org.openide.util.RequestProcessor;
import org.openide.util.WeakListeners;

/**
 *
 * @author Jan Lahoda
 */
public class ComponentPeer implements PropertyChangeListener, DocumentListener, ChangeListener {

    public static void assureInstalled(JTextComponent pane) {
        if (pane.getClientProperty(ComponentPeer.class) == null) {
            pane.putClientProperty(ComponentPeer.class, new ComponentPeer(pane));
        }
    }

    public synchronized void propertyChange(PropertyChangeEvent evt) {
        if (doc != pane.getDocument()) {
            if (doc != null) {
                doc.removeDocumentListener(this);
            }
            doc = pane.getDocument();
            doc.addDocumentListener(this);
            doc = pane.getDocument();
            doUpdateCurrentVisibleSpan();
        }
    }

    private JTextComponent pane;
    private Document doc;
    private List<Highlight> currentHighlights;

    private static Map<Document, List<Highlight>> doc2Highlights = new WeakHashMap<Document, List<Highlight>>();

    private RequestProcessor.Task checker = RequestProcessor.getDefault().create(new Runnable() {
        public void run() {
            try {
                process();
            } catch (BadLocationException e) {
                ErrorManager.getDefault().notify(e);
            }
        }
    });

    private void reschedule() {
        cancel();
        checker.schedule(100);
    }
    
    private synchronized Document getDocument() {
        return doc;
    }

    /** Creates a new instance of ComponentPeer */
    private ComponentPeer(JTextComponent pane) {
        this.pane = pane;
//        reschedule();
        pane.addPropertyChangeListener(this);
        doc = pane.getDocument();
        doc.addDocumentListener(this);
    }
    
    private Component parentWithListener;

    private int[] computeVisibleSpan() {
        Component parent = pane.getParent();

        if (parent instanceof JViewport) {
            JViewport vp = (JViewport) parent;

            Point start = vp.getViewPosition();
            Dimension size = vp.getExtentSize();
            Point end = new Point((int) (start.getX() + size.getWidth()), (int) (start.getY() + size.getHeight()));

            int startPosition = pane.viewToModel(start);
            int endPosition = pane.viewToModel(end);

            if (parentWithListener != vp) {
                vp.addChangeListener(WeakListeners.change(this, vp));
                parentWithListener = vp;
            }
            return new int[] {startPosition, endPosition};
        }

        return new int[] {0, pane.getDocument().getLength()};
    }

    private void updateCurrentVisibleSpan() {
        //check possible change in visible rect:
        int[] newSpan = computeVisibleSpan();
        
        synchronized (this) {
            if (currentVisibleRange == null || currentVisibleRange[0] != newSpan[0] || currentVisibleRange[1] != newSpan[1]) {
                currentVisibleRange = newSpan;
                reschedule();
            }
        }
    }

    private int[] currentVisibleRange;

    private synchronized int[] getCurrentVisibleSpan() {
        return currentVisibleRange;
    }

    private TokenList l;
    
    private synchronized TokenList getTokenList() {
        if (l == null) {
            l = ACCESSOR.lookupTokenList(getDocument());
            
            if (l != null)
                l.addChangeListener(this);
        }
        
        return l;
    }
    
    private void process() throws BadLocationException {
        final Document doc = getDocument();
        
        if (doc.getLength() == 0)
            return ;
        
        FileObject file = getFile(doc);
        
        if (file == null) {
            return ;
        }
        
        List<Highlight> localHighlights = new ArrayList<Highlight>();
        
        try {
            long startTime = System.currentTimeMillis();
            
            resume();
            
            final TokenList l = getTokenList();
            
            if (l == null) {
                //nothing to do:
                return ;
            }

            Dictionary d = getDictionary(doc);

            if (d == null)
                return ;
            
            final int[] span = getCurrentVisibleSpan();

//            System.err.println("span=" + span[0] + "-" + span[1]);
//
//            DefaultHighlight spanHL = new DefaultHighlight(new Coloring(null, null, Color.ORANGE), doc.createPosition(span[0]), doc.createPosition(span[1]));
//
//            Highlighter.getDefault().setHighlights(file, "spellchecker-span", Collections.singletonList(spanHL));

            if (span == null || span[0] == (-1)) {
                //not initialized yet:
                doUpdateCurrentVisibleSpan();
                return ;
            }

            l.setStartOffset(span[0]);

            final boolean[] cont = new boolean [1];
            final Position[] bounds = new Position[2];
            final CharSequence[] word = new CharSequence[1];
            
            while (!isCanceled()) {
                doc.render(new Runnable() {
                    public void run() {
                        if (isCanceled()) {
                            cont[0] = false;
                            return ;
                        }
                        
                        if (cont[0] = l.nextWord()) {
                            if (l.getCurrentWordStartOffset() > span[1]) {
                                cont[0] = false;
                                return ;
                            }
                            try {
                                word[0] = l.getCurrentWordText();
                                //XXX: maybe very slow, creating positions for all words:
                                bounds[0] = NbDocument.createPosition(doc, l.getCurrentWordStartOffset(), Position.Bias.Forward);
                                bounds[1] = NbDocument.createPosition(doc, l.getCurrentWordStartOffset() + word[0].length(), Position.Bias.Backward);
                            } catch (BadLocationException e) {
                                ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, e);
                                cont[0] = false;
                            }
                        }
                    }
                });
                
                if (!cont[0])
                    break;
                
                if (word[0].length() < 2) {
                    //ignore single letter words
                    continue;
                }
                
                Highlight h = null;

//                System.err.println("word=" + word[0]);
                switch (d.validateWord(word[0])) {
                    case PREFIX_OF_VALID:
                    case BLACKLISTED:
                    case INVALID:
                        h = new DefaultHighlight(ERROR, bounds[0], bounds[1]);
                }
                
                if (h != null) {
                    localHighlights.add(h);
                }
            }
        } finally {
            setHighlights(file, localHighlights);
//            System.err.println("Spellchecker time: " + (System.currentTimeMillis() - startTime));
        }
    }

    private void setHighlights(FileObject file, List<Highlight> newHighlights) {
        Document doc = getDocument();
        
        synchronized (ComponentPeer.class) {
            List<Highlight> all = doc2Highlights.get(doc);
            
            if (all == null) {
                doc2Highlights.put(doc, all = new ArrayList<Highlight>());
            } else {
                if (currentHighlights != null)
                    all.removeAll(currentHighlights);
            }
            
            all.addAll(newHighlights);

            currentHighlights =  newHighlights;
            
            Highlighter.getDefault().setHighlights(file, "spellchecker", all);
            DictionaryBasedHintsProvider.create().modified(doc);
        }
    }

    public static synchronized List<Highlight> getHighlightsCopy(Document doc) {
        List<Highlight> all = doc2Highlights.get(doc);

        if (all == null)
            return Collections.emptyList();

        return new ArrayList(all);
    }

    public static Dictionary getDictionary(Document doc) {
        FileObject file = getFile(doc);

        if (file == null)
            return null;

        Locale locale = LocaleQuery.findLocale(file);
        
        return ACCESSOR.lookupDictionary(locale);
    }

    private synchronized boolean isCanceled() {
        return cancel;
    }

    private synchronized void cancel() {
        cancel = true;
    }

    private synchronized void resume() {
        cancel = false;
    }

    private boolean cancel = false;

    private static final Coloring ERROR = new Coloring(null, 0, null, null, null, null, Color.RED);

    private static FileObject getFile(Document doc) {
        DataObject file = (DataObject) doc.getProperty(Document.StreamDescriptionProperty);

        if (file == null)
            return null;

        return file.getPrimaryFile();
    }

    public void insertUpdate(DocumentEvent e) {
        documentUpdate();
    }

    public void removeUpdate(DocumentEvent e) {
        documentUpdate();
    }

    public void changedUpdate(DocumentEvent e) {
    }

    private void documentUpdate() {
        doUpdateCurrentVisibleSpan();
        cancel();
    }
    
    private void doUpdateCurrentVisibleSpan() {
        if (SwingUtilities.isEventDispatchThread()) {
            updateCurrentVisibleSpan();
            reschedule();
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    updateCurrentVisibleSpan();
                    reschedule();
                }
            });
        }
    }

    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == l) {
            reschedule();
        } else {
            updateCurrentVisibleSpan();
        }
    }
    
    public static LookupAccessor ACCESSOR = new LookupAccessor() {
        public Dictionary lookupDictionary(Locale locale) {
            for (DictionaryProvider p : (Collection<DictionaryProvider>)Lookup.getDefault().lookup(new Template(DictionaryProvider.class)).allInstances()) {
                Dictionary d = p.getDictionary(locale);
                
                if (d != null)
                    return d;
            }
            
            return null;
        }
        public TokenList lookupTokenList(Document doc) {
            Object mimeTypeObj = doc.getProperty("mimeType");
            String mimeType = "text/plain";
            
            if (mimeTypeObj instanceof String) {
                mimeType = (String) mimeTypeObj;
            }
            
            for (TokenListProvider p : (Collection<TokenListProvider>) MimeLookup.getMimeLookup(mimeType).lookup(new Template(TokenListProvider.class)).allInstances()) {
                TokenList l = p.findTokenList(doc);
                
                if (l != null)
                    return l;
            }
            
            return null;
            
        }
    };

}
