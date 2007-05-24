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
 * Portions Copyrighted 2007 Sun Microsystems, Inc.
 */
package org.netbeans.modules.editor.bracesmatching;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyleConstants;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.settings.AttributesUtilities;
import org.netbeans.api.editor.settings.EditorStyleConstants;
import org.netbeans.api.lexer.TokenHierarchy;
import org.netbeans.api.lexer.TokenId;
import org.netbeans.api.lexer.TokenSequence;
import org.netbeans.lib.editor.util.swing.DocumentUtilities;
import org.netbeans.spi.editor.bracesmatching.BracesMatcher;
import org.netbeans.spi.editor.bracesmatching.BracesMatcherFactory;
import org.netbeans.spi.editor.bracesmatching.MatcherContext;
import org.netbeans.spi.editor.highlighting.support.OffsetsBag;
import org.openide.util.RequestProcessor;

/**
 *
 * @author Vita Stejskal
 */
public final class MasterMatcher {

    private static final Logger LOG = Logger.getLogger(MasterMatcher.class.getName());
    
    public static final String PROP_ALLOWED_SEARCH_DIRECTION = "nbeditor-bracesMatching-allowedSearchDirection"; //NOI18N
    public static final String D_BACKWARD = "backward"; //NOI18N
    public static final String D_FORWARD = "forward"; //NOI18N

    public static final String PROP_CARET_BIAS = "nbeditor-bracesMatching-caretBias"; //NOI18N
    public static final String B_BACKWARD = "backward"; //NOI18N
    public static final String B_FORWARD = "forward"; //NOI18N
    
    public static final String PROP_MAX_BACKWARD_LOOKAHEAD = "nbeditor-bracesMatching-maxBackwardLookahead"; //NOI18N
    public static final String PROP_MAX_FORWARD_LOOKAHEAD = "nbeditor-bracesMatching-maxForwardLookahead"; //NOI18N
    private static final int DEFAULT_MAX_LOOKAHEAD = 1;
    private static final int MAX_MAX_LOOKAHEAD = 256;

    // Just for debugging
    public static final String PROP_SHOW_SEARCH_PARAMETERS = "debug-showSearchParameters-dont-ever-use-it-or-you-will-die"; //NOI18N
    private static final AttributeSet CARET_BIAS_HIGHLIGHT = AttributesUtilities.createImmutable(StyleConstants.Underline, Color.BLACK);
    private static final AttributeSet MAX_LOOKAHEAD_HIGHLIGHT = AttributesUtilities.createImmutable(EditorStyleConstants.WaveUnderlineColor, Color.BLUE);
    
    public static synchronized MasterMatcher get(JTextComponent component) {
        MasterMatcher mm = (MasterMatcher) component.getClientProperty(MasterMatcher.class);
        if (mm == null) {
            mm = new MasterMatcher(component);
            component.putClientProperty(MasterMatcher.class, mm);
        }
        return mm;
    }
    
    public void highlight(
        Document document,
        int caretOffset, 
        OffsetsBag highlights, 
        AttributeSet matchedColoring, 
        AttributeSet mismatchedColoring
    ) {
        synchronized (LOCK) {
            Object allowedSearchDirection = getAllowedDirection();
            Object caretBias = getCaretBias();
            int maxBwdLookahead = getMaxLookahead(true);
            int maxFwdLookahead = getMaxLookahead(false);
            
            if (task != null) {
                // a task is running, perhaps just add a new job to it
                if (lastResult.getCaretOffset() == caretOffset && 
                    lastResult.getAllowedDirection() == allowedSearchDirection &&
                    lastResult.getCaretBias() == caretBias &&
                    lastResult.getMaxBwdLookahead() == maxBwdLookahead &&
                    lastResult.getMaxFwdLookahead() == maxBwdLookahead
                ) {
                    lastResult.addHighlightingJob(highlights, matchedColoring, mismatchedColoring);
                } else {
                    // Different request, cancel the current task
                    task.cancel();
                    task = null;
                }
            }

            if (task == null) {
                // Remember the last request
                lastResult = new Result(document, caretOffset, allowedSearchDirection, caretBias, maxBwdLookahead, maxFwdLookahead);
                lastResult.addHighlightingJob(highlights, matchedColoring, mismatchedColoring);

                // Fire up a new task
                task = PR.post(lastResult);
            }
        }
    }
    
    public void navigate(
        Document document,
        int caretOffset, 
        Caret caret,
        boolean select
    ) {
        RequestProcessor.Task waitFor = null;
        
        synchronized (LOCK) {
            Object allowedSearchDirection = getAllowedDirection();
            Object caretBias = getCaretBias();
            int maxBwdLookahead = getMaxLookahead(true);
            int maxFwdLookahead = getMaxLookahead(false);

            if (task != null) {
                // a task is running, perhaps just add a new job to it
                if (lastResult.getCaretOffset() == caretOffset && 
                    lastResult.getAllowedDirection() == allowedSearchDirection &&
                    lastResult.getCaretBias() == caretBias &&
                    lastResult.getMaxBwdLookahead() == maxBwdLookahead &&
                    lastResult.getMaxFwdLookahead() == maxBwdLookahead
                ) {
                    lastResult.addNavigationJob(caret, select);
                    waitFor = task;
                } else {
                    // Different request, cancel the current task
                    task.cancel();
                    task = null;
                }
            }

            if (task == null) {
                // Remember the last request
                lastResult = new Result(document, caretOffset, allowedSearchDirection, caretBias, maxBwdLookahead, maxFwdLookahead);
                lastResult.addNavigationJob(caret, select);

                // Fire up a new task
                task = PR.post(lastResult);
                waitFor = task;
            }
        }
        
        if (waitFor != null) {
            waitFor.waitFinished();
        }
    }
    
    private static final RequestProcessor PR = new RequestProcessor("EditorBracesMatching", 5, true); //NOI18N

    private final String LOCK = new String("MasterMatcher.LOCK"); //NOI18N

    private final JTextComponent component;
    
    private RequestProcessor.Task task = null;
    private Result lastResult = null;
    
    private MasterMatcher(JTextComponent component) {
        this.component = component;
    }

    private Object getAllowedDirection() {
        Object allowedDirection = component.getClientProperty(PROP_ALLOWED_SEARCH_DIRECTION);
        return allowedDirection != null ? allowedDirection : D_BACKWARD;
    }

    private Object getCaretBias() {
        Object caretBias = component.getClientProperty(PROP_CARET_BIAS);
        return caretBias != null ? caretBias : B_BACKWARD;
    }

    private int getMaxLookahead(boolean backward) {
        String propName = backward ? PROP_MAX_BACKWARD_LOOKAHEAD : PROP_MAX_FORWARD_LOOKAHEAD;
        int maxLookahead = DEFAULT_MAX_LOOKAHEAD;
        Object value = component.getClientProperty(propName);
        if (value instanceof Integer) {
            maxLookahead = ((Integer) value).intValue();
        } else if (value != null) {
            try {
                maxLookahead = Integer.valueOf(value.toString());
            } catch (NumberFormatException nfe) {
                LOG.log(Level.WARNING, "Can't parse the value of " + propName + ": '" + value + "'", nfe); //NOI18N
            }
        }
        
        if (maxLookahead >= 0 && maxLookahead <= MAX_MAX_LOOKAHEAD) {
            return maxLookahead;
        } else {
            LOG.warning("Invalid value of " + propName + ": " + maxLookahead); //NOI18N
            return MAX_MAX_LOOKAHEAD;
        }
    }
    
    private static void highlightAreas(
        int [] origin, 
        int [] matches,
        OffsetsBag highlights, 
        AttributeSet matchedColoring, 
        AttributeSet mismatchedColoring
    ) {
        // Remove all existing highlights
        highlights.clear();

        if (matches != null && matches.length >= 2) {
            // Highlight the matched origin
            highlights.addHighlight(origin[0], origin[1], matchedColoring);

            // Highlight all the matches
            for(int i = 0; i < matches.length / 2; i++) {
                highlights.addHighlight(matches[i * 2], matches[i * 2 + 1], matchedColoring);
            }
        } else if (origin != null && origin.length >= 2) {
            // Highlight the mismatched origin
            highlights.addHighlight(origin[0], origin[1], mismatchedColoring);
        }
    }

    // when navigating: always set the dot after a matching area
    // when selecting: always select the inside between original and matching areas
    //                 do not select the areas themselvs
    private static void navigateAreas(
        int [] origin, 
        int [] matches,
        Object caretBias,
        Caret caret,
        boolean select
    ) {
        if (matches != null && matches.length >= 2) {
            int newDotBackwardIdx = -1;
            int newDotForwardIdx = -1;
            
            for(int i = 0; i < matches.length / 2; i++) {
                if (matches[i * 2] < origin[0] && 
                    (newDotBackwardIdx == -1 || matches[i * 2] > matches[newDotBackwardIdx * 2])
                ) {
                    newDotBackwardIdx = i;
                }
                
                if (matches[i * 2] > origin[1] && 
                    (newDotForwardIdx == -1 || matches[i * 2] < matches[newDotForwardIdx * 2])
                ) {
                    newDotForwardIdx = i * 2;
                }
            }
            
            if (newDotBackwardIdx != -1) {
                if (select) {
                    caret.setDot(origin[0]);
                    caret.moveDot(matches[2 * newDotBackwardIdx + 1]);
                } else {
                    if (B_BACKWARD.equalsIgnoreCase(caretBias.toString())) {
                        caret.setDot(matches[2 * newDotBackwardIdx + 1]);
                    } else {
                        caret.setDot(matches[2 * newDotBackwardIdx]);
                    }
                }
            } else if (newDotForwardIdx != -1) {
                if (select) {
                    caret.setDot(origin[1]);
                    caret.moveDot(matches[2 * newDotForwardIdx]);
                } else {
                    if (B_BACKWARD.equalsIgnoreCase(caretBias.toString())) {
                        caret.setDot(matches[2 * newDotForwardIdx + 1]);
                    } else {
                        caret.setDot(matches[2 * newDotForwardIdx]);
                    }
                }
            }
        }
    }
    
    private final class Result implements Runnable {

        private final Document document;
        private final int caretOffset;
        private final Object allowedDirection;
        private final Object caretBias;
        private final int maxBwdLookahead;
        private final int maxFwdLookahead;

        private boolean inDocumentRender = false;
        private boolean interrupted = false;
        private int [] origin = null;
        private int [] matches = null;

        private final List<Object []> highlightingJobs = new ArrayList<Object []>();
        private final List<Object []> navigationJobs = new ArrayList<Object []>();
        
        public Result(
            Document document, 
            int caretOffset, 
            Object allowedDirection,
            Object caretBias,
            int maxBwdLookahead,
            int maxFwdLookahead
        ) {
            this.document = document;
            this.caretOffset = caretOffset;
            this.allowedDirection = allowedDirection;
            this.caretBias = caretBias;
            this.maxBwdLookahead = maxBwdLookahead;
            this.maxFwdLookahead = maxFwdLookahead;
        }
        
        // Must be called under the MasterMatcher.LOCK
        public void addHighlightingJob(
            OffsetsBag highlights,
            AttributeSet matchedColoring,
            AttributeSet mismatchedColoring
        ) {
            highlightingJobs.add(new Object[] {
                highlights,
                matchedColoring,
                mismatchedColoring
            });
        }

        // Must be called under the MasterMatcher.LOCK
        public void addNavigationJob(Caret caret, boolean select) {
            navigationJobs.add(new Object [] { caret, select });
        }
        
        public int getCaretOffset() {
            return caretOffset;
        }
        
        public Object getAllowedDirection() {
            return allowedDirection;
        }
        
        public Object getCaretBias() {
            return allowedDirection;
        }
        
        public int getMaxBwdLookahead() {
            return maxBwdLookahead;
        }
        
        public int getMaxFwdLookahead() {
            return maxFwdLookahead;
        }
        
        // Must be called under the MasterMatcher.LOCK
        public int [] getOrigin() {
            return origin;
        }
        
        // Must be called under the MasterMatcher.LOCK
        public int [] getMatches() {
            return matches;
        }
        
        // ------------------------------------------------
        // Runnable implementation
        // ------------------------------------------------
        
        public void run() {
            // Read lock the document
            if (!inDocumentRender) {
                inDocumentRender = true;
                document.render(this);
                
                synchronized (LOCK) {
                    // If the task was cancelled, we must exit without storing results
                    if (interrupted || Thread.currentThread().isInterrupted()) {
                        return;
                    }

                    for (Object[] job : highlightingJobs) {
                        highlightAreas(origin, matches, (OffsetsBag) job[0], (AttributeSet) job[1], (AttributeSet) job[2]);
                        if (Boolean.valueOf((String) component.getClientProperty(PROP_SHOW_SEARCH_PARAMETERS))) {
                            showSearchParameters((OffsetsBag) job[0]);
                        }
                    }
                    
                    for(Object [] job : navigationJobs) {
                        navigateAreas(origin, matches, caretBias, (Caret) job[0], (Boolean) job[1]);
                    }
                    
                    // Signal that the task is done.
                    MasterMatcher.this.task = null;
                }
                
                return;
            }

            Collection<? extends BracesMatcherFactory> factories = findFactories();
            if (factories.isEmpty() || Thread.currentThread().isInterrupted()) {
                // no provider, no matcher, nothing to do
                return;
            }
            
            try {
                BracesMatcher [] matcher = new BracesMatcher[1];

                if (D_BACKWARD.equalsIgnoreCase(allowedDirection.toString())) {
                    origin = findOrigin(factories, true, matcher);
                    if (origin == null) {
                        origin = findOrigin(factories, false, matcher);
                    }
                } else if (D_FORWARD.equalsIgnoreCase(allowedDirection.toString())) {
                    origin = findOrigin(factories, false, matcher);
                    if (origin == null) {
                        origin = findOrigin(factories, true, matcher);
                    }
                }
                
                if (origin == null || Thread.currentThread().isInterrupted()) {
                    // no original area, nothing to search for
                    return;
                }
            
                matches = matcher[0].findMatches();
            } catch (BadLocationException ble) {
                LOG.log(Level.WARNING, null, ble);
            } catch (InterruptedException e) {
                // We were interrupted, no results
                interrupted = true;
            }
        }

        private Collection<? extends BracesMatcherFactory> findFactories() {
            MimePath mimePath = null;

            TokenHierarchy<? extends Document> th = TokenHierarchy.get(document);
            if (th != null) {
                TokenSequence<? extends TokenId> embedded = th.tokenSequence();
                TokenSequence<? extends TokenId> seq = null;

                do {
                    seq = embedded;
                    embedded = null;

                    // Find the token at the caret's position
                    seq.move(caretOffset);
                    if (seq.moveNext()) {
                        // Drill down to the embedded sequence
                        embedded = seq.embedded();
                    }

                } while (embedded != null);

                String path = seq.languagePath().mimePath();
                mimePath = MimePath.parse(path);
            } else {
                String mimeType = (String) document.getProperty("mimeType"); //NOI18N
                mimePath = mimeType != null ? MimePath.parse(mimeType) : null;
            }

            if (mimePath == null) {
                mimePath = MimePath.EMPTY;
            }

            return MimeLookup.getLookup(mimePath).lookupAll(BracesMatcherFactory.class);
        }
        
        private int [] findOrigin(
            Collection<? extends BracesMatcherFactory> factories, 
            boolean backward, 
            BracesMatcher [] matcher
        ) throws InterruptedException {
            Element paragraph = DocumentUtilities.getParagraphElement(document, caretOffset);
            
            int adjustedCaretOffset = caretOffset;
            int lookahead = 0;
            if (backward) {
                int maxLookahead = maxBwdLookahead;
                if (B_FORWARD.equalsIgnoreCase(caretBias.toString())) {
                    adjustedCaretOffset++;
                    maxLookahead++;
                    if (adjustedCaretOffset > paragraph.getEndOffset()) {
                        adjustedCaretOffset = paragraph.getEndOffset();
                    }
                } else {
                    if (maxLookahead == 0) {
                        maxLookahead = 1;
                    }
                }

                lookahead = caretOffset - paragraph.getStartOffset();
                if (lookahead > maxLookahead) {
                    lookahead = maxLookahead;
                }
            } else {
                int maxLookahead = maxFwdLookahead;
                if (B_BACKWARD.equalsIgnoreCase(caretBias.toString())) {
                    adjustedCaretOffset--;
                    maxLookahead++;
                    if (adjustedCaretOffset < paragraph.getStartOffset()) {
                        adjustedCaretOffset = paragraph.getStartOffset();
                    }
                } else {
                    if (maxLookahead == 0) {
                        maxLookahead = 1;
                    }
                }
                
                lookahead = paragraph.getEndOffset() - caretOffset;
                if (lookahead > maxLookahead) {
                    lookahead = maxLookahead;
                }
            }
            
            if (lookahead > 0) {
                MatcherContext context = SpiAccessor.get().createCaretContext(
                    document, 
                    adjustedCaretOffset, 
                    backward, 
                    lookahead
                );

                // Find the first provider that accepts the context
                for(BracesMatcherFactory factory : factories) {
                    matcher[0] = factory.createMatcher(context);
                    if (matcher[0] != null) {
                        break;
                    }
                }

                // Find the original area
                int [] origin = null;
                try {
                    origin = matcher[0].findOrigin();
                } catch (BadLocationException ble) {
                    LOG.log(Level.WARNING, null, ble);
                }
                
                // Check the original area for consistency
                if (origin != null) {
                    if (origin.length == 0) {
                        origin = null;
                    } else if (origin.length != 2) {
                        if (LOG.isLoggable(Level.WARNING)) {
                            LOG.warning("Invalid BracesMatcher implementation, " + //NOI18N
                                "findOrigin() can only return two offsets. " + //NOI18N
                                "Offending BracesMatcher: " + matcher); //NOI18N
                        }
                        origin = null;
                    } else if (origin[0] < 0 || origin[1] > document.getLength() || origin[0] > origin[1]) {
                        if (LOG.isLoggable(Level.WARNING)) {
                            LOG.warning("Invalid origin offsets [" + origin[0] + ", " + origin[1] + "]. " + //NOI18N
                                "Offending BracesMatcher: " + matcher); //NOI18N
                        }
                        origin = null;
                    } else {
                        if (backward) {
                            if (origin[1] < caretOffset - lookahead || origin[0] > caretOffset) {
                                if (LOG.isLoggable(Level.WARNING)) {
                                    LOG.warning("Origin offsets out of range, " + //NOI18N
                                        "origin = [" + origin[0] + ", " + origin[1] + "], " + //NOI18N
                                        "caretOffset = " + caretOffset + //NOI18N
                                        ", lookahead = " + lookahead + //NOI18N
                                        ", searching backwards. " + //NOI18N
                                        "Offending BracesMatcher: " + matcher); //NOI18N
                                }
                                origin = null;
                            }
                        } else {
                            if ((origin[1] < caretOffset || origin[0] > caretOffset + lookahead)) {
                                if (LOG.isLoggable(Level.WARNING)) {
                                    LOG.warning("Origin offsets out of range, " + //NOI18N
                                        "origin = [" + origin[0] + ", " + origin[1] + "], " + //NOI18N
                                        "caretOffset = " + caretOffset + //NOI18N
                                        ", lookahead = " + lookahead + //NOI18N
                                        ", searching forward. " + //NOI18N
                                        "Offending BracesMatcher: " + matcher); //NOI18N
                                }
                                origin = null;
                            }
                        }

                    }
                }

                if (origin != null) {
                    LOG.fine("[" + origin[0] + ", " + origin[1] + "] for caret = " + caretOffset + ", lookahead = " + (backward ? "-" : "") + lookahead); //NOI18N
                } else {
                    LOG.fine("[null] for caret = " + caretOffset + ", lookahead = " + (backward ? "-" : "") + lookahead); //NOI18N
                }
                
                return origin;
            } else {
                return null;
            }
        }
        
        private void showSearchParameters(OffsetsBag bag) {
            Element paragraph = DocumentUtilities.getParagraphElement(document, caretOffset);
            
            // Show caret bias
            if (B_BACKWARD.equalsIgnoreCase(caretBias.toString())) {
                if (caretOffset > paragraph.getStartOffset()) {
                    bag.addHighlight(caretOffset - 1, caretOffset, CARET_BIAS_HIGHLIGHT);
                }
            } else {
                if (caretOffset < paragraph.getEndOffset()) {
                    bag.addHighlight(caretOffset, caretOffset + 1, CARET_BIAS_HIGHLIGHT);
                }
            }
            
            // Show lookahead
            int bwdLookahead = Math.min(maxBwdLookahead, caretOffset - paragraph.getStartOffset());
            int fwdLookahead = Math.min(maxFwdLookahead, paragraph.getEndOffset() - caretOffset);
            bag.addHighlight(caretOffset - bwdLookahead, caretOffset, MAX_LOOKAHEAD_HIGHLIGHT);
            bag.addHighlight(caretOffset, caretOffset + fwdLookahead, MAX_LOOKAHEAD_HIGHLIGHT);
        }
    } // End of Result class
}
