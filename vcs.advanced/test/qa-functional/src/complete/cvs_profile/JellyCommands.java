/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */

package complete.cvs_profile;

import complete.GenericStub;
import complete.GenericStub.GenericNode;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.util.StringTokenizer;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import org.netbeans.jellytools.NbDialogOperator;
import org.netbeans.jellytools.modules.javacvs.output.AnnotateOutputOperator;
import org.netbeans.jellytools.modules.javacvs.output.LogOutputOperator;
import org.netbeans.jellytools.modules.javacvs.output.StatusOutputOperator;
import org.netbeans.jellytools.modules.vcscore.VCSCommandsInOutputOperator;
import org.netbeans.jellytools.nodes.FilesystemNode;
import org.netbeans.jellytools.nodes.Node;
import org.netbeans.jemmy.operators.JLabelOperator;
import org.netbeans.jemmy.operators.JTableOperator;
import org.netbeans.junit.NbTestSuite;
import org.netbeans.jellytools.modules.vcsgeneric.cvs_profile.*;
import util.Filter;

public class JellyCommands extends CVSStub {
    
    public JellyCommands(String testName) {
        super(testName);
    }
    
    public static Test suite() {
//        GenericNode.DEBUG = true;
        TestSuite suite = new NbTestSuite();
        suite.addTest(new JellyCommands("configure"));
        suite.addTest(new JellyCommands("testRelease"));
        suite.addTest(new JellyCommands("testStatusLogPrepare"));
        suite.addTest(new JellyCommands("testStatus"));
        suite.addTest(new JellyCommands("testDefaultLog"));
        suite.addTest(new JellyCommands("testCheckOutHistory"));
        suite.addTest(new JellyCommands("testDefaultAnnotate"));
        suite.addTest(new JellyCommands("testDefaultImport"));
        suite.addTest(new JellyCommands("testDefaultExport"));
        suite.addTest(new JellyCommands("testUnmount"));
        return suite;
    }
    
    public static void main(java.lang.String[] args) {
        TestRunner.run(suite());
    }
    
    static String serverDir;
    static String username;
    GenericNode initdir;
    GenericNode statusDir1, statusDir2, statusFile1, statusFile2;
    GenericNode imdir, imfile;
    
    protected void prepareServer(String dir) {
        serverDir = dir;
        new File(dir + "/initdir").mkdirs();
    }
    
    public void createStructure() {
        initdir = new GenericNode (root, "initdir");
        statusDir1 = new GenericNode (root, "StatusDir1");
        statusDir2 = new GenericNode (statusDir1, "StatusDir2");
        statusFile1 = new GenericNode (statusDir1, "File1");
        statusFile2 = new GenericNode (statusDir2, "File2");

        imdir = new GenericNode (root, "imdir");
        imfile = new GenericNode (imdir, "imfile");
    }
    
    public void configure () {
        super.configure();
    }

    public void testRelease () {
        String str, s;
        GenericNode releaseNode = new GenericNode (root, "releasedirectory");
        releaseNode.mkdirs ();
        GenericNode releaseFileNode1 = new GenericNode (releaseNode, "releasefile1");
        releaseFileNode1.save("Init1");
        GenericNode releaseFileNode2 = new GenericNode (releaseNode, "releasefile2");
        releaseFileNode2.save("Init2");

        refresh (root);
//        releaseNode.waitStatus ("Local");
        addDirectory (releaseNode);
        
        refresh (releaseNode);
        releaseFileNode1.waitStatus ("Local");
        addFile (releaseFileNode1, null);
        commitFile (releaseFileNode1, null, null);
        releaseFileNode1.waitStatus ("Up-to-date; 1.1");
        
        releaseFileNode1.save("Mod1");
        refresh (releaseNode);
        releaseFileNode1.waitStatus ("Locally Modified; 1.1");
        releaseFileNode2.waitStatus ("Local");

        releaseNode.cvsNode ().cVSRelease();
        assertQuestionNoDialog(null);
        releaseNode.waitHistory ("PRE_RELEASE");
        refresh (root); // stabilization
        releaseNode.waitStatus (null);

        releaseNode.cvsNode ().cVSRelease();
        NbDialogOperator dia = new NbDialogOperator ("Question");
        str = new JLabelOperator (dia, "M ").getText ();
        s = "M " + releaseFileNode1.name ();
        assertTrue ("ReleaseFileNode1 is not listed: Label: " + str, str.indexOf (s) >= 0);
        str = new JLabelOperator (dia, "? ").getText ();
        s = "? " + releaseFileNode2.name ();
        assertTrue ("ReleaseFileNode2 is not listed: Label: " + str, str.indexOf (s) >= 0);
        str = new JLabelOperator (dia, "altered files").getText ();
        assertTrue ("Invalid altered files count: Label: " + str, str.indexOf ("[1]") >= 0);
        str = new JLabelOperator (dia, releaseNode.name ()).getText ();
        dia.yes ();
        dia.waitClosed();
        releaseNode.waitHistory ("Release");
//        Helper.waitNoNode (repository.tree (), root.node (), releaseNode.name()); // fails due to bug #28223
        for (int a = 0; a < 60; a ++) {
            sleep (1000);
            if (!new File (releaseFileNode1.file ()).exists()  &&  !new File (releaseFileNode2.file ()).exists()) {
                sleep (10000); // recovery from bug #28223
                if (new File (releaseNode.file ()).exists ()) { // recovery from bug #28223
                    info.println ("Recovering from bug #28223"); // recovery from bug #28223
                    deleteRecursively (new File (releaseNode.file ())); // recovery from bug #28223
                }
                return;
            }
        }
        assertTrue ("Timeout: ReleaseFileNodes still exists", false);
    }
    
    public void testStatusLogPrepare () {
        statusDir1.mkdirs ();
        statusDir2.mkdirs ();
        statusFile1.save("Init1");
        statusFile2.save("Init2");
        refresh(root);
        addDirectory (statusDir1);
        addDirectory (statusDir2);
        addFile (statusFile1, "Init1");
        addFile (statusFile2, "Init2");
        commitFile (statusFile1, null, "HEAD1");
        statusFile1.waitStatus ("Up-to-date; 1.1");
        commitFile (statusFile2, null, "HEAD2");
        statusFile2.waitStatus ("Up-to-date; 1.1");
        addTagFile (statusFile1, "BranchTag1", true);
        addTagFile (statusFile2, "BranchTag2", true);
        statusFile1.save ("Mod1");
        statusFile2.save ("Mod2");
        commitFile (statusFile1, "BranchTag1", "BranchCommit1");
        commitFile (statusFile2, "BranchTag2", "BranchCommit2");
    }
    
    public void testStatus () {
        CVSStatusFileAdvDialog stat1;
        CVSStatusFolderAdvDialog stat2;
        String str;
        Filter filt = new Filter ();
        filt.addFilterAfter("1.1.2.1");

        info.println ("File1 - Brief");
        out.println ("File1 - Brief");
        closeAllVCSOutputs();
        statusFile1.cvsNode ().cVSStatus();
        stat1 = new CVSStatusFileAdvDialog ();
        stat1.oK ();
        stat1.waitClosed ();
        statusFile1.waitHistory ("Status");
        StatusOutputOperator soo = new StatusOutputOperator (statusFile1.name());
        soo.dumpAll(out, serverDir);
        soo.close();
//        soo.waitClosed ();

        info.println ("File1 - Verbose");
        out.println ("File1 - Verbose");
        closeAllVCSOutputs();
        statusFile1.cvsNode ().cVSStatus();
        stat1 = new CVSStatusFileAdvDialog ();
        stat1.checkVerboseFormatTagInfo(true);
        stat1.oK ();
        stat1.waitClosed ();
        statusFile1.waitHistory ("Status");
        soo = new StatusOutputOperator (statusFile1.name());
        soo.dumpAll(out, serverDir);
        soo.close();
//        soo.waitClosed ();

        info.println ("Dir1 - Recursive - Brief");
        out.println ("Dir1 - Recursive - Brief");
        closeAllVCSOutputs();
        statusDir1.cvsNode ().cVSStatus();
        stat2 = new CVSStatusFolderAdvDialog ();
        stat2.checkProcessDirectoriesRecursively(true);
        stat2.checkVerboseFormatTagInfo(false);
        stat2.oK ();
        stat2.waitClosed ();
        statusDir1.waitHistory ("Status");
        soo = new StatusOutputOperator (true);
        soo.dumpAll(out, serverDir);
        soo.close();
//        soo.waitClosed ();

        info.println ("Dir1 - Recursive - Verbose");
        out.println ("Dir1 - Recursive - Verbose");
        closeAllVCSOutputs();
        statusDir1.cvsNode ().cVSStatus();
        stat2 = new CVSStatusFolderAdvDialog ();
        stat2.checkProcessDirectoriesRecursively(true);
        stat2.checkVerboseFormatTagInfo(true);
        stat2.oK ();
        stat2.waitClosed ();
        statusDir1.waitHistory ("Status");
        soo = new StatusOutputOperator (true);
        soo.dumpAll(out, serverDir);
        soo.close();
//        soo.waitClosed ();

        info.println ("Dir1 - Brief");
        out.println ("Dir1 - Brief");
        closeAllVCSOutputs();
        statusDir1.cvsNode ().cVSStatus();
        stat2 = new CVSStatusFolderAdvDialog ();
        stat2.checkProcessDirectoriesRecursively(false);
        stat2.checkVerboseFormatTagInfo(false);
        stat2.oK ();
        stat2.waitClosed ();
        statusDir1.waitHistory ("Status");
        soo = new StatusOutputOperator (true);
        soo.dumpAll(out, serverDir);
        soo.close();
//        soo.waitClosed ();

        info.println ("Dir1 - Verbose");
        out.println ("Dir1 - Verbose");
        closeAllVCSOutputs();
        statusDir1.cvsNode ().cVSStatus();
        stat2 = new CVSStatusFolderAdvDialog ();
        stat2.checkProcessDirectoriesRecursively(false);
        stat2.checkVerboseFormatTagInfo(true);
        stat2.oK ();
        stat2.waitClosed ();
        statusDir1.waitHistory ("Status");
        soo = new StatusOutputOperator (true);
        soo.dumpAll(out, serverDir);
        soo.close();
//        soo.waitClosed ();

        compareReferenceFiles();
    }
    
    public void testDefaultLog () {
        String str;
        Filter filt = new Filter ();
        filt.addFilterAfter("RCS file: ");
        filt.addFilterBetween("date: ", ";");
        filt.addFilterBetween("author: ", ";");
        closeAllVCSOutputs();

        statusDir1.cvsNode ().cVSLog();
        CVSLogFolderAdvDialog log = new CVSLogFolderAdvDialog ();
        log.oK ();
        log.waitClosed ();
        statusDir1.waitHistory ("Log");
        LogOutputOperator loo = new LogOutputOperator (true, false, false);
        loo.dumpAll(out, serverDir);
        loo.close();
//        loo.waitClosed ();
        
        compareReferenceFiles ();
    }
    
    public void testCheckOutHistory () {
        VCSCommandsInOutputOperator coo;
        String str;
        closeAllVCSOutputs();

        root.cvsNode ().cVSHistory ();
        CVSHistoryAdvDialog hi = new CVSHistoryAdvDialog ();
        hi.ok ();
        hi.waitClosed();
        root.waitHistory("History");
        coo = new VCSCommandsInOutputOperator ("History - " + ".");
        
        waitNoEmpty (coo.txtStandardOutput ());
        str = coo.txtStandardOutput ().getText ();
        info.println (str);
        StringTokenizer st = new StringTokenizer (str, "\n");
        int c = st.countTokens();
        assertTrue ("Invalid lines count: Count: " + c + " Text: " + str, c == 1);
        str = st.nextToken();
        assertTrue ("History does not contain =.=", str.indexOf ("=.=") >= 0);
        assertTrue ("History does not contain O at the beginning", str.startsWith ("O"));
        assertTrue ("History does not contain * at the end", str.endsWith ("*"));
    }
    
    public void dumpTable (JTableOperator table) {
        for (int a = 0; a < table.getRowCount(); a ++) {
            Object o0 = table.getValueAt(a, 0);
            Object o1 = table.getValueAt(a, 1);
            Object o4 = table.getValueAt(a, 4);
            out.println ("Line: " + a + " ---- " + o0 + "\t" + o1 + "\t" + o4);
        }
    }

    public void dumpTableColors (JTableOperator table) {
        for (int a = 0; a < table.getRowCount(); a ++) {
            Object o0 = table.getValueAt(a, 0);
            TableCellRenderer tcr = table.getCellRenderer(a, 0);
            Component c = tcr.getTableCellRendererComponent((JTable) table.getSource (), o0, false, false, a, 0);
            Color col = c.getBackground ();
            String str = col.toString ();
            if (annoWhite.equals (col))
                str = "White";
            else if (annoGreen.equals (col))
                str = "Green";
            else if (annoBlue.equals (col))
                str = "Blue";
            else if (annoRed.equals (col))
                str = "Red";
            out.println ("Line: " + a + " ---- " + str);
        }
    }
    
    protected void testAnnoVersion (AnnotateOutputOperator aoo, String equ) {
        out.println ("==== RevisionEquals: " + equ + " ====");
        aoo.selectFilterRevision(equ);
        out.println ("--- Revision: " + AnnotateOutputOperator.ITEM_NOREVISIONSELECTED);
        aoo.selectSelectRevision(AnnotateOutputOperator.ITEM_NOREVISIONSELECTED);
        dumpTableColors(aoo.tabTableOfFileAnnotations());
        out.println ("--- Revision: 1.1");
        aoo.selectSelectRevision("1.1");
        dumpTableColors(aoo.tabTableOfFileAnnotations());
        out.println ("--- Revision: 1.2");
        aoo.selectSelectRevision("1.2");
        dumpTableColors(aoo.tabTableOfFileAnnotations());
        out.println ("--- Revision: 1.3");
        aoo.selectSelectRevision("1.3");
        dumpTableColors(aoo.tabTableOfFileAnnotations());
    }
    
    public void testDefaultAnnotate () {
        closeAllVCSOutputs();
        GenericNode annofile = new GenericNode (initdir, "annofile");
        if (!GenericStub.DEBUG) {
            annofile.save ("Commit-1.1 - Line1\nCommit-1.1 - Line2\nCommit-1.1 - Line4\nCommit-1.1 - Line5\n");
            refresh(initdir);
            annofile.cvsNode ();
            addFile (annofile, "InitialState");
            commitFile (annofile, null, "Commit_1.1");
            annofile.waitStatus ("Up-to-date; 1.1");
            annofile.save ("Commit-1.1 - Line1\nCommit-1.1 - Line2 - Modified-1.2\nCommit-1.2 - Line3 - Added-1.2\nCommit-1.1 - Line4\nCommit-1.1 - Line5 - Modified-1.2\n");
            commitFile (annofile, null, "Commit_1.2");
            annofile.waitStatus ("Up-to-date; 1.2");
            annofile.save ("Commit-1.3 - Line0 - Added-1.3\nCommit-1.1 - Line1\nCommit-1.1 - Line2 - Modified-1.2 - Modified-1.3\nCommit-1.2 - Line3 - Added-1.2\nCommit-1.1 - Line4\nCommit-1.1 - Line5 - Modified-1.2\n");
            commitFile (annofile, null, "Commit_1.3");
            annofile.waitStatus ("Up-to-date; 1.3");
        }
        annofile.cvsNode ().cVSAnnotate();
        CVSAnnotateAdvDialog anno = new CVSAnnotateAdvDialog ();
        anno.oK ();
        anno.waitClosed ();
        annofile.waitHistory ("Annotate");
        AnnotateOutputOperator aoo = new AnnotateOutputOperator (annofile.name (), false);
        
        aoo.dumpAll(out, root.name ());
        
        out.println ("==== Author: " + AnnotateOutputOperator.ITEM_NOAUTHORSELECTED + " ====");
        aoo.selectFilterByAuthor(AnnotateOutputOperator.ITEM_NOAUTHORSELECTED);
        testAnnoVersion (aoo, AnnotateOutputOperator.ITEM_EQUALS);
        testAnnoVersion (aoo, AnnotateOutputOperator.ITEM_NEWERTHAN);
        testAnnoVersion (aoo, AnnotateOutputOperator.ITEM_OLDERTHAN);
        out.println ("==== Author: <AUTHOR> ====");
        info.println ("User author: " + aoo.cboFilterByAuthor().getItemAt(1));
        aoo.selectFilterByAuthor((String) aoo.cboFilterByAuthor().getItemAt(1));
        testAnnoVersion (aoo, AnnotateOutputOperator.ITEM_EQUALS);
        testAnnoVersion (aoo, AnnotateOutputOperator.ITEM_NEWERTHAN);
        testAnnoVersion (aoo, AnnotateOutputOperator.ITEM_OLDERTHAN);

        aoo.close ();
//        aoo.waitClosed ();
        compareReferenceFiles();
    }
    
    public void testDefaultImport () {
        NbDialogOperator dia;
        String str, s;
        imdir.mkdirs ();
        imfile.save("Init");
        refresh(root);
        imdir.cvsNode ().cVSImport();
        CVSImportFolderAdvDialog imp = new CVSImportFolderAdvDialog ();
        imp.setLoggingMessage("LoggingMessage");
/*        imp.oK ();
        
        dia = new NbDialogOperator ("Warning");
        str = new JLabelOperator (dia).getText ();
        s = "The value of Vendor Tag: should not be empty.";
        assertEquals("Invalid warning dialog message: Expect: " + s + ", Got: " + str, s, str);
        dia.ok();*/
        imp.setVendorTag("VendorTag");
/*        imp.oK ();
        
        dia = new NbDialogOperator ("Warning");
        str = new JLabelOperator (dia).getText ();
        s = "The value of Vendor Tag: should not be empty.";
        assertEquals("Invalid warning dialog message: Expect: " + s + ", Got: " + str, s, str);
        dia.ok();*/
        imp.setReleaseTag("ReleaseTag");
        imp.oK ();

        imp.waitClosed ();
        assertInformationDialog (null);
        imdir.waitHistory ("Import");
        refresh (root);
        imdir.waitStatus (null);
        refresh (imdir);
        imfile.waitStatus ("Up-to-date; 1.1.1.1");
        
        Filter filt = new Filter ();
        filt.addFilterAfter("RCS file: ");
        filt.addFilterBetween("date: ", ";");
        filt.addFilterBetween("author: ", ";");
        closeAllVCSOutputs();

        imfile.cvsNode ().cVSLog();
        CVSLogFileAdvDialog log = new CVSLogFileAdvDialog ();
        log.oK ();
        log.waitClosed ();
        imfile.waitHistory ("Log");
        LogOutputOperator loo = new LogOutputOperator (imfile.name (), false, false);
        loo.dumpAll (out, serverDir);
        loo.close ();
//        loo.waitClosed ();
        
        compareReferenceFiles ();
    }
    
    public void testDefaultExport () {
        GenericNode exported1 = new GenericNode (root, "exported1");
        GenericNode exdir1 = new GenericNode (exported1, "imdir");
        GenericNode exfile1 = new GenericNode (exdir1, "imfile");
        GenericNode exported2 = new GenericNode (root, "exported2");
        GenericNode exdir2 = new GenericNode (exported2, "imdir");
        GenericNode exfile2 = new GenericNode (exdir2, "imfile");

        imdir.cvsNode ().cVSExport ();
        CVSExportFolderAdvDialog exp1 = new CVSExportFolderAdvDialog ();
        exp1.setFolderToExportTo(exported1.file ());
        exp1.setRevisionOrTag("HEAD");
        exported1.mkdirs();
        exp1.oK ();
        exp1.waitClosed ();
        imdir.waitHistory ("Export");
        refresh (root);
        exported1.cvsNode ();
        exdir1.cvsNode ();
        exfile1.cvsNode ();
        
        imfile.cvsNode ().cVSExport ();
        CVSExportFileAdvDialog exp2 = new CVSExportFileAdvDialog ();
        exp2.setFolderToExportTo(exported2.file ());
        exp2.setRevisionOrTag("HEAD");
        exported2.mkdirs();
        exp2.oK ();
        exp2.waitClosed ();
        imfile.waitHistory ("Export");
        refresh (root);
        exported2.cvsNode ();
        exdir2.cvsNode ();
        exfile2.cvsNode ();
    }
    
    public void testUnmount() {
        new FilesystemNode(repository.tree(), root.node ()).unmount();
        new Node (repository.tree(), "").waitChildNotPresent(root.node ());
    }

}
