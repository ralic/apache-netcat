/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2002 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.tasklist.pmd;

import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.Report;
import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.RuleSet;
import net.sourceforge.pmd.RuleViolation;
import pmd.*;
import pmd.config.ConfigUtils;
import pmd.config.PMDOptionsSettings;
import org.netbeans.api.tasklist.*;
import org.netbeans.spi.tasklist.LineSuggestionPerformer;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.text.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import org.openide.cookies.SourceCookie;
import org.openide.TopManager;
import org.openide.cookies.EditorCookie;
import org.openide.explorer.view.*;
import org.openide.nodes.*;
import org.openide.ErrorManager;
import org.openide.cookies.LineCookie;
import org.openide.loaders.DataObject;
import org.openide.text.Line;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;

import org.netbeans.modules.tasklist.core.TLUtils;
import org.netbeans.modules.tasklist.core.ConfPanel;

/**
 * This class uses the PMD rule checker to provide rule violation
 * suggestions.
 * <p>
 * @todo Add more automatic fixers for rules. For example, add one
 *   to remove an unused method.
 * @todo Include javadoc sections in method and variable removals
 *   (be sure to show it in the preview dialog as well)
 * <p>
 * @author Tor Norbye
 */


public class ViolationProvider extends SuggestionProvider
    implements DocumentSuggestionProvider {

    final private static String TYPE = "pmd-violations"; // NOI18N

    /**
     * Return the typenames of the suggestions that this provider
     * will create.
     * @return An array of string names. Should never be null. Most
     *  providers will create Suggestions of a single type, so it will
     *  be an array with one element.
     */
    public String[] getTypes() {
        return new String[] { TYPE };
    }
    
    private boolean scanning = false;

    /**
     * Start creating suggestions when you think of them.
     * (This is typically called when the Suggestions window is shown,
     * for example because the Suggestions window tab is moved to the front,
     * or the user has moved to a workspace containing a Suggestions Window.)
     */
    public void notifyRun() {
        super.notifyRun();
        scanning = true;
    }

    /**
     * (Temporarily) stop creating suggestions.
     * (This is typically called when the Suggestions window is hidden,
     * for example because a different tab is moved to the front or because
     * the user has moved to another workspace.)
     */
    public void notifyStop() {
        super.notifyStop();
        scanning = false;
    }

    /**
     * The given document has been edited, and a time interval (by default
     * around 2 seconds I think) has passed without any further edits.
     * Update your Suggestions as necessary. This may mean removing
     * previously registered Suggestions, or editing existing ones,
     * or adding new ones, depending on the current contents of the
     * document.
     * <p>
     * @param document The document being edited
     */
    public void docEditedStable(Document document, DocumentEvent event,
                                   DataObject dataobject) {
        if (scanning) {
            update(document, dataobject);
        }
    }

    /**
     * The given document has been "shown"; it is now visible.
     * <p>
     * @param document The document being shown
     */
    public void docShown(Document document, DataObject dataobject) {
        if ((document == null) || (dataobject == null)) {
            return;
        }
        update(document, dataobject);
    }

    /** Update the manager with the current document contents */
    private void update(Document doc, DataObject dobj) {
        List newTasks = scan(doc, dobj);
        SuggestionManager manager = SuggestionManager.getDefault();

        if ((newTasks == null) && (showingTasks == null)) {
            return;
        }
        manager.register(TYPE, newTasks, showingTasks);
        showingTasks = newTasks;
    }
    
    /** The actual workhorse of this class - scan a document for rule violations */
    public List scan(Document doc, DataObject dobj) {
        List tasks = null;
        try {
            
        SuggestionManager manager = SuggestionManager.getDefault();
        if (!manager.isEnabled(TYPE)) {
            return null;
        }

        SourceCookie cookie =
            (SourceCookie)dobj.getCookie(SourceCookie.class);

        // The file is not a java file
        if(cookie == null) {
            return null;
        }

        String text = null;
        try {
            int len = doc.getLength();
            text = doc.getText(0, len);
        } catch (BadLocationException e) {
            TopManager.getDefault().
                getErrorManager().notify(ErrorManager.WARNING, e);
            return null;
        }
        Reader reader = new StringReader(text);
        String name = cookie.getSource().getClasses()[0].getName().getFullName();
        PMD pmd = new PMD();
        RuleContext ctx = new RuleContext();
        Report report = new Report();
        ctx.setReport(report);
        ctx.setSourceCodeFilename(name);

        RuleSet set = new RuleSet();
        List rlist = ConfigUtils.createRuleList(
                             PMDOptionsSettings.getDefault().getRules());
        Iterator it = rlist.iterator();
        while(it.hasNext()) {
            set.addRule((Rule)it.next());
        }
        try {
            pmd.processFile(reader, set, ctx);
        } catch (RuntimeException e) {
            // For some reason, some of the PMD classes
            // throw RuntimeExceptions,
            // for example AbstractScope.addDeclaration
            // I suspect PMD wasn't written with the intent of it being run
            // on incomplete or invalid classes. So we just swallow the
            // exceptions here

            //ErrorManager.getDefault().notify(e);
        }
        Iterator iterator = ctx.getReport().iterator();

        Image taskIcon = Utilities.loadImage("org/netbeans/modules/tasklist/pmd/fixable.gif"); // NOI18N

        if(!ctx.getReport().isEmpty()) {
            while(iterator.hasNext()) {
                final RuleViolation violation = (RuleViolation)iterator.next();
                try {
                    // Violation line numbers seem to be 0-based
                    final Line line = getLine(dobj, violation.getLine());
                    
                    //System.out.println("Next violation = " + violation.getRule().getName() + " with description " + violation.getDescription() + " on line " + violation.getLine());
                    
                    boolean fixable = false;
                    SuggestionPerformer action = null;
                    
                    String rulename = violation.getRule().getName();
                    if (rulename.equals("UnusedImports") || // NOI18N
                    rulename.equals("DuplicateImports")) { // NOI18N
                        fixable = true;
                        action = new SuggestionPerformer() {
                            public void perform(Suggestion s) {
                                // Remove the particular line
                                deleteLine(line, "import ", // NOI18N
                                false); // whitespace etc.?
                            }
                            public boolean hasConfirmation() {
                                return true;
                            }
                            public Object getConfirmation(Suggestion s) {
                                DataObject dao = line.getDataObject();
                                int linenumber = line.getLineNumber();
                                String filename = dao.getPrimaryFile().getNameExt();
                                String ruleDesc = violation.getRule().getDescription();
                                String ruleExample = 
                                    violation.getRule().getExample();
                                String beforeDesc = 
                                   NbBundle.getMessage(ViolationProvider.class,
                                        "ImportConfirmation"); // NOI18N
                                StringBuffer sb = new StringBuffer(200);
                                Line l = line;
                                String text = l.getText();
                                sb.append("<html>"); // NOI18N
                                TLUtils.appendSurroundingLine(sb, l, -1);
                                sb.append("<br>");
                                sb.append("<b><strike>");
                                sb.append(line.getText());
                                sb.append("</strike></b>");
                                sb.append("<br>");
                                TLUtils.appendSurroundingLine(sb, l, +1);
                                sb.append("</html>"); // NOI18N
                                String beforeContents = sb.toString();
                                
                                return new ConfPanel(beforeDesc, 
                                                     beforeContents, null, 
                                                     null,
                                                     filename, linenumber, 
                                       getBottomPanel(ruleDesc, ruleExample));
                                
                            }
                        };
                    } else if ((rulename.equals("UnusedPrivateField") || // NOI18N
                    rulename.equals("UnusedLocalVariable")) && // NOI18N
                    deleteLine(line, "", true)) { // only a check
                        fixable = true;
                        action = new SuggestionPerformer() {
                            public void perform(Suggestion s) {
                                // Remove the particular line
                                deleteLine(line, "", false);
                            }
                            public boolean hasConfirmation() {
                                return true;
                            }
                            public Object getConfirmation(Suggestion s) {
                                DataObject dao = line.getDataObject();
                                int linenumber = line.getLineNumber();
                                String filename = dao.getPrimaryFile().getNameExt();
                                String ruleDesc = violation.getRule().getDescription();
                                String ruleExample = violation.getRule().getExample();
                                String beforeDesc = NbBundle.getMessage(ViolationProvider.class,
                                        "UnusedConfirmation"); // NOI18N

                                StringBuffer sb = new StringBuffer(200);
                                Line l = line;
                                String text = l.getText();
                                sb.append("<html>"); // NOI18N
                                TLUtils.appendSurroundingLine(sb, l, -1);
                                sb.append("<br>");
                                sb.append("<b><strike>");
                                sb.append(line.getText());
                                sb.append("</strike></b>");
                                sb.append("<br>");
                                TLUtils.appendSurroundingLine(sb, l, +1);
                                sb.append("</html>"); // NOI18N
                                String beforeContents = sb.toString();

                                return new org.netbeans.modules.tasklist.core.ConfPanel(beforeDesc, 
                                      beforeContents, null, null,
                                      filename, linenumber, getBottomPanel(ruleDesc, ruleExample));
                            }
                        };
                    } else {
                        action = new LineSuggestionPerformer();
                    }
                    
                    Suggestion s = manager.createSuggestion(
                        TYPE,
                        rulename + " : " + // NOI18N
                           violation.getDescription(),
                        action);
                    // XXX Is there a priority for each rule?
                    s.setPriority(SuggestionPriority.NORMAL);
                    s.setLine(line);
                    if (fixable) {
                        s.setIcon(taskIcon);
                    }
                    if (tasks == null) {
                        tasks = new ArrayList(ctx.getReport().size());
                    }
                    tasks.add(s);
                } catch (Exception e) {
                    ErrorManager.getDefault().notify(e);
                }
            }
        }
        } catch (Exception e) {
            ErrorManager.getDefault().notify(e);
        }
        return tasks;
    }
    
    /** Look up the Line object for a particular file:linenumber */
    Line getLine(DataObject dataobject, int lineno) {
        // Go to the given line
        try {
            LineCookie lc = (LineCookie)dataobject.getCookie(LineCookie.class);
            if (lc != null) {
                Line.Set ls = lc.getLineSet();
                if (ls != null) {
                    // XXX HACK
                    // I'm subtracting 1 because empirically I've discovered
                    // that the editor highlights whatever line I ask for plus 1
                    Line l = ls.getCurrent(lineno-1);
                    return l;
                }
            }
        } catch (Exception e) {
            TopManager.getDefault().getErrorManager().
                notify(ErrorManager.INFORMATIONAL, e);
        }
        return null;
        
    }
    
    private Element getElement(Document d, Line line) {
	if (d == null) {
            ErrorManager.getDefault().log(ErrorManager.USER, "d was null");
            return null;
	}

        if (!(d instanceof StyledDocument)) {
            ErrorManager.getDefault().log(ErrorManager.USER, "Not a styleddocument");
            return null;
        }
            
        StyledDocument doc = (StyledDocument)d;
        Element e = doc.getParagraphElement(0).getParentElement();
        if (e == null) {
            // try default root (should work for text/plain)
            e = doc.getDefaultRootElement ();
        }
        int lineNumber = line.getLineNumber();
        Element elm = e.getElement(lineNumber);
        return elm;
    }

    private Document getDoc(Line line) {
        DataObject dao = line.getDataObject();
        if (!dao.isValid()) {
            ErrorManager.getDefault().log(ErrorManager.USER, "dataobject was not null");
            return null;
        }

	final EditorCookie edit = (EditorCookie)dao.getCookie(EditorCookie.class);
	if (edit == null) {
            ErrorManager.getDefault().log(ErrorManager.USER, "no editor cookie!");
	    return null;
	}

        Document d = edit.getDocument(); // Does not block
        return d;
    }

    
    /** Remove a particular line. Make sure that the line begins with
     * a given prefix, just in case.
     * @param prefix A prefix that the line to be deleted must start with
     * @param checkOnly When true, don't actually delete the line, only
     *         report whether the deletion should be attempted or not
     */
    boolean deleteLine(Line line, String prefix, boolean checkOnly) {
        Document doc = getDoc(line);
        Element elm = getElement(doc, line);
        if (elm == null) {
            return false;
        }
        int offset = elm.getStartOffset();
        int endOffset = elm.getEndOffset();

        try {
            String text = doc.getText(offset, endOffset-offset);
            if (!text.startsWith(prefix)) {
                return false;
            }
            if (checkOnly) {
                return isDeleteSafe(text);
            } else {
                doc.remove(offset, endOffset-offset);
            }
        } catch (BadLocationException ex) {
            TopManager.getDefault().
                getErrorManager().notify(ErrorManager.WARNING, ex);
        }
        return false;
    }

    
    /**
     * Checks designed to prevent deleting additional content on the
     * line - for example, it won't delete anything if it detects
     * multiple statements on the line, or function calls. It may err
     * on the safe side; e.g. not delete even when it would be safe to do so.
     */
    private boolean isDeleteSafe(String text) {
        /*
          What about a weird corner case like this:
          int z = 0;
          for (int y = 0;
          z < 5;
          z++) {
          importantCall();
          }
          Will I delete the "for(int y = 0" line since y is unused?

          No - because I will see the "(" and bail!
        */

        // A small statemachine to figure out if the line can
        // be "safely" deleted
        int n = text.length();
        boolean inString = false;
        boolean escaped = false;

        //  What do we initialize comment too? It's POSSIBLE that you
        //  have code like this
        //  /* Begin comment:
        //     end */    int unused = 5;
        //  ...and I begin in the middle of a comment. But I think this
        // is an unusual scenario...
        boolean comment = false;
                
        boolean seenSemi = false;
        boolean seenComma = false;
        for (int i = 0; i < n; i++) {
            char c = text.charAt(i);
            if (comment) {
                if ((c == '*') && (i < (n-1)) &&
                    ((text.charAt(i+1) == '/'))) {
                    comment = false;
                } else {
                    continue;
                }
            } else if (c == '\\') {
                escaped = !escaped;
            } else if (c == '"') {
                if (!escaped) {
                    inString = !inString;
                }
            } else if ((c == '/') && (i < (n-1)) &&
                       ((text.charAt(i+1) == '*'))) {
                comment = true;
            } else if (c == '(') {
                if (!inString && !escaped) {
                    // BAIL! "(" on a line makes me nervous, e.g. unused
                    // variable "success" in
                    //   boolean success = saveData();
                    //System.out.println("BAILING: function call on the line!");
                    return false;
                }
            } else if (c == ',') {
                if (!inString && !escaped) {
                    seenComma = true;
                }
            } else if (c == ';') {
                if (!inString && !escaped) {
                    seenSemi = true;
                }
            } else if (Character.isWhitespace(c)) {
                // do nothing
            } else {
                // Some other character
                if (!inString && !escaped && (seenSemi || seenComma)) {
                    // BAIL -- we've seen text after a semicolon or
                    // comma - multiple statements on the line!
                    //System.out.println("BAILING: character after semi=" + seenSemi + " or comma=" + seenComma);
                    return false;
                }
            }
        }
        return true;
    }
    
    
    private JPanel getBottomPanel(String ruleDesc, String ruleExample) {
        java.awt.GridBagConstraints gridBagConstraints;
        // Variables declaration - do not modify
        javax.swing.JLabel jLabel9;
        javax.swing.JLabel jLabel8;
        javax.swing.JScrollPane jScrollPane2;
        javax.swing.JTextArea descText;
        javax.swing.JScrollPane jScrollPane1;
        javax.swing.JPanel jPanel1;
        javax.swing.JTextArea exampleText;
        // End of variables declaration
        
        
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descText = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        exampleText = new javax.swing.JTextArea();

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel8.setText(NbBundle.getMessage(ViolationProvider.class, "Description")); // NOI18N();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel8, gridBagConstraints);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 200));
        descText.setWrapStyleWord(true);
        descText.setLineWrap(true);
        descText.setEditable(false);
        jScrollPane1.setViewportView(descText);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jLabel9.setText(NbBundle.getMessage(ViolationProvider.class, "Example")); // NOI18N();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel9, gridBagConstraints);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(200, 200));
        exampleText.setEditable(false);
        exampleText.setPreferredSize(null);
        jScrollPane2.setViewportView(exampleText);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(18, 12, 11, 11);

        // Dont' use monospaced fonts
        descText.setFont(jLabel8.getFont());
        exampleText.setFont(jLabel8.getFont());
        
        descText.setText(ruleDesc.trim());
        exampleText.setText(ruleExample.trim());
        
        return jPanel1;
    }

    /** 
     * The given document has been "hidden"; it's still open, but
     * the editor containing the document is not visible.
     * <p>
     * @param document The document being hidden
     */
    public void docHidden(Document document, DataObject dataobject) {
	// Remove existing items
        if (showingTasks != null) {
            SuggestionManager manager = SuggestionManager.getDefault();
            manager.register(TYPE, null, showingTasks);
	    showingTasks = null;
	}     
    }


    public void docClosed(Document document, DataObject dataobject) {
    }
    public void docOpened(Document document, DataObject dataobject) {
    }
    public void docEdited(Document document, DocumentEvent event,
                             DataObject dataobject) {
    }
    
    /** The list of tasks we're currently showing in the tasklist */
    private List showingTasks = null;
}
