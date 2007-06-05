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

import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.netbeans.api.java.lexer.JavadocTokenId;
import org.netbeans.api.lexer.Token;
import org.netbeans.api.lexer.TokenHierarchy;
import org.netbeans.api.lexer.TokenId;
import org.netbeans.api.lexer.TokenSequence;
import org.netbeans.api.lexer.TokenUtilities;
import org.netbeans.spi.editor.bracesmatching.BracesMatcher;
import org.netbeans.spi.editor.bracesmatching.BracesMatcherFactory;
import org.netbeans.spi.editor.bracesmatching.MatcherContext;
import org.netbeans.spi.editor.bracesmatching.support.BracesMatcherSupport;

/**
 *
 * @author Vita Stejskal
 */
public final class JavadocMatcher implements BracesMatcher, BracesMatcherFactory {

    private final MatcherContext context;
    
    private TokenSequence<? extends TokenId> jdocSeq;
    private int jdocStart;
    private int jdocEnd;

//    private int [] matchingArea;
    
    private BracesMatcher defaultMatcher;
    
    public JavadocMatcher() {
        this(null);
    }

    private JavadocMatcher(MatcherContext context) {
        this.context = context;
    }
    
    // -----------------------------------------------------
    // BracesMatcher implementation
    // -----------------------------------------------------
    
    public int[] findOrigin() throws BadLocationException, InterruptedException {
        int caretOffset = context.getSearchOffset();
        boolean backward = context.isSearchingBackward();
        
        TokenHierarchy<Document> th = TokenHierarchy.get(context.getDocument());
        List<TokenSequence<? extends TokenId>> sequences = th.embeddedTokenSequences(caretOffset, backward);

        for(int i = sequences.size() - 1; i >= 0; i--) {
            TokenSequence<? extends TokenId> seq = sequences.get(i);
            if (seq.language() == JavadocTokenId.language()) {
                jdocSeq = seq;
                if (i > 0) {
                    TokenSequence<? extends TokenId> javaSeq = sequences.get(i - 1);
                    jdocStart = javaSeq.offset();
                    jdocEnd = javaSeq.offset() + javaSeq.token().length();
                } else {
                    // jdocSeq is the top level sequence, ie the whole document is just javadoc
                    jdocStart = 0;
                    jdocEnd = context.getDocument().getLength();
                }
                break;
            }
        }

        assert jdocSeq != null : "Not in javadoc"; //NOI18N
        
//        if (caretOffset >= jdocStart && 
//            ((backward && caretOffset <= jdocStart + 3) ||
//            (!backward && caretOffset < jdocStart + 3))
//        ) {
//            matchingArea = new int [] { jdocEnd - 2, jdocEnd };
//            return new int [] { jdocStart, jdocStart + 3 };
//        }
//
//        if (caretOffset <= jdocEnd && 
//            ((backward && caretOffset > jdocEnd - 2) ||
//            (!backward && caretOffset >= jdocEnd - 2))
//        ) {
//            matchingArea = new int [] { jdocStart, jdocStart + 3 };
//            return new int [] { jdocEnd - 2, jdocEnd };
//        }
        
        // look for tags first
        jdocSeq.move(caretOffset);
        if (jdocSeq.moveNext()) {
            if (isTag(jdocSeq.token())) {
                int s = jdocSeq.offset();
                int e = jdocSeq.offset() + jdocSeq.token().length();
                if (s < caretOffset || !backward) {
                    return new int [] { s, e };
                }
            }

            while(moveTheSequence(jdocSeq, backward, context.getLimitOffset())) {
                if (isTag(jdocSeq.token())) {
                    int s = jdocSeq.offset();
                    int e = jdocSeq.offset() + jdocSeq.token().length();
                    return new int [] { s, e };
                }
            }
        }

        defaultMatcher = BracesMatcherSupport.defaultMatcher(context, jdocStart, jdocEnd);
        return defaultMatcher.findOrigin();
    }

    public int[] findMatches() throws InterruptedException, BadLocationException {
        if (defaultMatcher != null) {
            return defaultMatcher.findMatches();
        }
    
//        if (matchingArea != null) {
//            return matchingArea;
//        }
        
        assert jdocSeq != null : "No javadoc token sequence"; //NOI18N
        
        Token<? extends TokenId> tag = jdocSeq.token();
        assert tag.id() == JavadocTokenId.HTML_TAG : "Wrong token"; //NOI18N
        
        if (isSingleTag(tag)) {
            return new int [] { jdocSeq.offset(), jdocSeq.offset() + jdocSeq.token().length() };
        }
        
        boolean backward = !isOpeningTag(tag);
        int cnt = 0;
        
        while(moveTheSequence(jdocSeq, backward, -1)) {
            if (!isTag(jdocSeq.token())) {
                continue;
            }
            
            if (matchTags(tag, jdocSeq.token())) {
                if ((backward && !isOpeningTag(jdocSeq.token())) ||
                    (!backward && isOpeningTag(jdocSeq.token()))
                ) {
                    cnt++;
                } else {
                    if (cnt == 0) {
                        return new int [] { jdocSeq.offset(), jdocSeq.offset() + jdocSeq.token().length() };
                    } else {
                        cnt--;
                    }
                }
            }
        }
        
        return null;
    }

    // -----------------------------------------------------
    // private implementation
    // -----------------------------------------------------

    private boolean moveTheSequence(TokenSequence<? extends TokenId> seq, boolean backward, int offsetLimit) {
        if (backward) {
            if (seq.movePrevious()) {
                int e = seq.offset() + seq.token().length();
                return offsetLimit == -1 ? true : e > offsetLimit;
            }
        } else {
            if (seq.moveNext()) {
                int s = seq.offset();
                return offsetLimit == -1 ? true : s < offsetLimit;
            }
        }
        return false;
    }

    private static boolean isTag(Token<? extends TokenId> tag) {
        CharSequence s = tag.text();
        int l = s.length();
        
        boolean b = tag.id() == JavadocTokenId.HTML_TAG &&
            l >= 3 &&
            s.charAt(0) == '<' && //NOI18N
            s.charAt(l - 1) == '>'; //NOI18N
        
        if (b) {
            if (s.charAt(1) == '/') { //NOI18N
                b = l >= 4 && Character.isLetterOrDigit(s.charAt(2));
            } else {
                b = Character.isLetterOrDigit(s.charAt(1));
            }
        }
        
        return b;
    }
    
    private static boolean isSingleTag(Token<? extends TokenId> tag) {
        return TokenUtilities.endsWith(tag.text(), "/>"); //NOI18N
    }
    
    private static boolean isOpeningTag(Token<? extends TokenId> tag) {
        return !TokenUtilities.startsWith(tag.text(), "</"); //NOI18N
    }
    
    private static boolean matchTags(Token<? extends TokenId> t1, Token<? extends TokenId> t2) {
        assert t1.length() >= 2 && t1.text().charAt(0) == '<' : t1 + " is not a tag."; //NOI18N
        assert t2.length() >= 2 && t2.text().charAt(0) == '<' : t2 + " is not a tag."; //NOI18N
        
        int idx1 = 1;
        int idx2 = 1;
        
        if (t1.text().charAt(1) == '/') {
            idx1++;
        } 
        
        if (t2.text().charAt(1) == '/') {
            idx2++;
        }
        
        for( ; idx1 < t1.length() && idx2 < t2.length(); idx1++, idx2++) {
            char ch1 = t1.text().charAt(idx1);
            char ch2 = t2.text().charAt(idx2);
            
            if (ch1 != ch2) {
                return false;
            }
            
            if (!Character.isLetterOrDigit(ch1)) {
                return true;
            }
        }
        
        return false;
    }
    
    // -----------------------------------------------------
    // BracesMatcherFactory implementation
    // -----------------------------------------------------
    
    /** */
    public BracesMatcher createMatcher(MatcherContext context) {
        return new JavadocMatcher(context);
    }

}
