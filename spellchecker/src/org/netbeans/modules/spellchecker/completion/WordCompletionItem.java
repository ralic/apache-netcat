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
package org.netbeans.modules.spellchecker.completion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.netbeans.api.editor.completion.Completion;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.CompletionUtilities;
import org.openide.ErrorManager;
import org.openide.text.NbDocument;

/**
 *
 * @author Jan Lahoda
 */
public class WordCompletionItem implements CompletionItem {
    
    private int substituteOffset;
    private String word;
    
    /** Creates a new instance of WordCompletionItem */
    public WordCompletionItem(int substituteOffset, String word) {
        this.substituteOffset = substituteOffset;
        this.word = word;
    }
    
    public void setSubstituteOffset(int substituteOffset) {
        this.substituteOffset = substituteOffset;
    }
    
    public int getSubstituteOffset() {
        return substituteOffset;
    }

    public void defaultAction(final JTextComponent component) {
        Completion.get().hideCompletion();
        Completion.get().hideDocumentation();
        NbDocument.runAtomic((StyledDocument) component.getDocument(), new Runnable() {
            public void run() {
                Document doc = component.getDocument();
                
                try {
                    doc.remove(substituteOffset, component.getCaretPosition() - substituteOffset);
                    doc.insertString(substituteOffset, getText(), null);
                } catch (BadLocationException e) {
                    ErrorManager.getDefault().notify(e);
                }
            }
        });
    }
    
    public void processKeyEvent(KeyEvent evt) {
    }
    
    public int getPreferredWidth(Graphics g, Font defaultFont) {
        return CompletionUtilities.getPreferredWidth(getText(), null, g, defaultFont);
    }
    
    public void render(Graphics g, Font defaultFont, Color defaultColor, Color backgroundColor, int width, int height, boolean selected) {
        if (selected) {
            g.setColor(backgroundColor);
            g.fillRect(0, 0, width, height);
            g.setColor(defaultColor);
        }
        CompletionUtilities.renderHtml(null, getText(), null, g, defaultFont, defaultColor, width, height, selected);
    }
    
    public CompletionTask createDocumentationTask() {
        return null;
    }
    
    public CompletionTask createToolTipTask() {
        return null;
    }
    
    public boolean instantSubstitution(JTextComponent component) {
        return true;
    }
    
    public int getSortPriority() {
        return 100;
    }
    
    public CharSequence getSortText() {
        return getText();
    }
    
    protected String getText() {
        return word;
    }
    
    public CharSequence getInsertPrefix() {
        return getText();
    }
}
