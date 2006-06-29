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
package org.netbeans.modules.fixtabs;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;

import org.netbeans.editor.Analyzer;
import org.netbeans.editor.BaseDocument;
import org.netbeans.editor.GuardedDocument;
import org.netbeans.editor.MarkBlock;
import org.netbeans.editor.Registry;
import org.netbeans.editor.Utilities;
import org.openide.ErrorManager;
import org.openide.util.NbBundle;
import org.openide.util.WeakListeners;


/**
 * Convert Tab characters in the current file into spaces.
 * (This was based on the stripwhitespace module by Tim Boudreau and Andrei Badea)
 *
 * @author Tor Norbye
 */
public final class ConvertTabsAction extends AbstractAction implements ChangeListener {
    private static final ErrorManager LOGGER =
        ErrorManager.getDefault().getInstance("org.netbeans.modules.fixtabs.ConvertTabsAction"); // NOI18N
    private static final boolean LOG = LOGGER.isLoggable(ErrorManager.INFORMATIONAL);

    public ConvertTabsAction() {
        super(NbBundle.getMessage(ConvertTabsAction.class, "LBL_ConvertTabsAction"),
            new ImageIcon(org.openide.util.Utilities.loadImage(
                    "org/netbeans/modules/fixtabs/convertTabs.png")));

        // see org.openide.util.actions.SystemAction.iconResource() javadoc for more details
        //putValue("noIconInMenu", Boolean.TRUE);
        Registry.addChangeListener(WeakListeners.change(this, Registry.class));
    }

    public void actionPerformed(ActionEvent e) {
        BaseDocument d = getCurrentDocument();

        if (d != null) {
            d.runAtomicAsUser(new Converter(d));
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    private BaseDocument getCurrentDocument() {
        JTextComponent nue = Registry.getMostActiveComponent();

        if (nue == null) {
            return null;
        }

        Document d = nue.getDocument();

        if (d instanceof BaseDocument) {
            return (BaseDocument)d;
        } else {
            return null;
        }
    }

    public void stateChanged(ChangeEvent e) {
        setEnabled(getCurrentDocument() != null);
    }

    private static final class Converter implements Runnable {
        private final BaseDocument d;

        public Converter(BaseDocument d) {
            this.d = d;
        }

        public void run() {
            // Go backwards so I don't have to update offset positions
            int ct = d.getDefaultRootElement().getElementCount();
            int spacesPerTab = d.getTabSize(); // Not necessarily == d.getFormatter().getTabSize()

            if (LOG) {
                LOGGER.log(ErrorManager.INFORMATIONAL,
                    ct + " elements to convert, document length " + d.getLength()); // NOI18N
            }

            try {
                for (int i = ct - 1; i >= 0; i--) {
                    Element curr = d.getDefaultRootElement().getElement(i);
                    String s =
                        d.getText(curr.getStartOffset(), curr.getEndOffset() -
                            curr.getStartOffset());

                    for (int offset = curr.getEndOffset() - 1, index = s.length() - 1, begin =
                            curr.getStartOffset(); offset >= begin; offset--, index--) {
                        if (s.charAt(index) == '\t') {
                            boolean remove = true;

                            if (d instanceof GuardedDocument) {
                                GuardedDocument gd = (GuardedDocument)d;
                                int comp =
                                    gd.getGuardedBlockChain().compareBlock(offset, offset + 1);

                                if ((comp & MarkBlock.OVERLAP) != 0) {
                                    remove = false;
                                }
                            }

                            if (remove) {
                                int startLinePos = Utilities.getRowStart(d, offset);
                                int colBeforeTab =
                                    getExpandedOffset(s, offset - curr.getStartOffset(),
                                        startLinePos - curr.getStartOffset(), spacesPerTab);
                                int colAfterTab =
                                    getExpandedOffset(s, offset - curr.getStartOffset() + 1,
                                        startLinePos - curr.getStartOffset(), spacesPerTab);
                                int len = colAfterTab - colBeforeTab;
                                String spaces = new String(Analyzer.getSpacesBuffer(len), 0, len);

                                if (LOG) {
                                    LOGGER.log(ErrorManager.INFORMATIONAL,
                                        "Remove from " + offset + " " + 1 +
                                        " chars and replace with " + spaces.length() + " spaces"); // NOI18N
                                }

                                d.remove(offset, 1);
                                d.insertString(offset, spaces, null);
                            }
                        }
                    }
                }
            } catch (BadLocationException e) {
                //rollback somehow?
                ErrorManager.getDefault().notify(e);
            }
        }

        /** Compute the visual column for a given offset in a line, starting from startLinePos */
        private int getExpandedOffset(String s, int offset, int startLinePos, int tabSize) {
            int col = 0;

            for (int i = startLinePos; i < offset; i++) {
                if (s.charAt(i) == '\t') {
                    // Compute the tab size at this point
                    int len = ((col + tabSize) / tabSize * tabSize) - col;
                    col += len;
                } else {
                    // All other characters contribute only one space
                    col++;
                }
            }

            return col;
        }
    }
}
