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


public class ImportPerformer implements SuggestionPerformer {
    private Line line;
    private RuleViolation violation;
    private boolean comment;

    ImportPerformer(Line line, RuleViolation violation, 
                    boolean comment) {
        this.line = line;
        this.violation = violation;
        this.comment = comment;
    }

    public void perform(Suggestion s) {
        // Remove the particular line
        if (comment) {
            TLUtils.commentLine(line, "import "); // NOI18N
        } else {
            TLUtils.deleteLine(line, "import "); // NOI18N
        }
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
        StringBuffer sb = new StringBuffer(200);
        String beforeContents = null;
        String afterContents = null;
        String afterDesc = null;
        String beforeDesc = null;
        if (comment) {
            beforeDesc = NbBundle.getMessage(ImportPerformer.class,
                                "ImportConfirmationBefore"); // NOI18N
            afterDesc = 
                NbBundle.getMessage(ImportPerformer.class,
                                "ImportConfirmationAfter"); // NOI18N

            Line l = line;
            String text = l.getText();
            sb.append("<html>"); // NOI18N
            TLUtils.appendSurroundingLine(sb, l, -1);
            sb.append("<br><b>"); // NOI18N
            sb.append(line.getText());
            sb.append("</b><br>"); // NOI18N
            TLUtils.appendSurroundingLine(sb, l, +1);
            sb.append("</html>"); // NOI18N
            beforeContents = sb.toString();

            sb.setLength(0);
            sb.append("<html>"); // NOI18N
            TLUtils.appendSurroundingLine(sb, l, -1);
            sb.append("<br><b><i>// "); // NOI18N
            sb.append(line.getText());
            sb.append("</i></b><br>"); // NOI18N
            TLUtils.appendSurroundingLine(sb, l, +1);
            sb.append("</html>"); // NOI18N
            afterContents = sb.toString();
        } else {
            beforeDesc = NbBundle.getMessage(ImportPerformer.class,
                                "ImportConfirmation"); // NOI18N
            Line l = line;
            String text = l.getText();
            sb.append("<html>"); // NOI18N
            TLUtils.appendSurroundingLine(sb, l, -1);
            sb.append("<br>");
            sb.append("<b><strike>"); // NOI18N
            sb.append(line.getText());
            sb.append("</strike></b>"); // NOI18N
            sb.append("<br>"); // NOI18N
            TLUtils.appendSurroundingLine(sb, l, +1);
            sb.append("</html>"); // NOI18N
            beforeContents = sb.toString();
        }
        
        return new ConfPanel(beforeDesc, 
                             beforeContents, afterDesc, 
                             afterContents,
                             filename, linenumber, 
                             ViolationProvider.getBottomPanel(ruleDesc, 
                                                              ruleExample));
        
    }
}
