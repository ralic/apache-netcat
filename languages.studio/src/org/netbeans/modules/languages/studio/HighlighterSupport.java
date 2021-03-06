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

import org.netbeans.api.languages.ASTItem;
import org.netbeans.api.languages.Highlighting;
import org.netbeans.api.languages.Highlighting.Highlight;
import org.openide.ErrorManager;
import org.openide.cookies.EditorCookie;
import org.openide.windows.TopComponent;
import java.awt.Color;
import java.util.Iterator;
import javax.swing.JEditorPane;
import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


/**
 */
public class HighlighterSupport {
    
    private Color       color;
    private Highlight   highlight;
    
    
    public HighlighterSupport (Color c) {
        color = c;
    }
    
    public void highlight (Document doc, ASTItem item) {
        removeHighlight ();
        highlight = Highlighting.getHighlighting (doc).highlight (
            item.getOffset(),
            item.getEndOffset(),
            getHighlightAS ()
        );
        refresh (doc, item.getOffset ());
    }
    
    private static void refresh (final Document doc, final int offset) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Iterator it = TopComponent.getRegistry ().getOpened ().iterator ();
                while (it.hasNext ()) {
                    TopComponent tc = (TopComponent) it.next ();
                    EditorCookie ec = tc.getLookup ().lookup (EditorCookie.class);
                    if (ec == null) continue;
                    JEditorPane[] eps = ec.getOpenedPanes ();
                    if (eps == null) continue;
                    int i, k = eps.length;
                    for (i = 0; i < k; i++) {
                        if (eps [i].getDocument () == doc) {
                            final JEditorPane ep = eps [i];
                            try {
                                ep.scrollRectToVisible (ep.modelToView (offset));
                            } catch (BadLocationException ex) {
                                ErrorManager.getDefault ().notify (ex);
                            }
                        }
                    }
                }
            }
        });
    }
    
    public void removeHighlight () {
        if (highlight == null) return;
        highlight.remove ();
        highlight = null;
    }
    
    private AttributeSet highlightAS = null;
    
    private AttributeSet getHighlightAS () {
        if (highlightAS == null) {
            SimpleAttributeSet as = new SimpleAttributeSet ();
            as.addAttribute (StyleConstants.Background, color);
            highlightAS = as;
        }
        return highlightAS;
    }
    
}
