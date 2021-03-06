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

package org.netbeans.modules.vcs.profiles.cvsprofiles.list;

import java.io.*;
import java.util.*;
import java.beans.*;
import java.text.*;

import org.netbeans.modules.vcscore.Variables;
import org.netbeans.modules.vcscore.VcsFileSystem;
import org.netbeans.modules.vcscore.turbo.Statuses;
import org.netbeans.modules.vcscore.util.*;
import org.netbeans.modules.vcscore.commands.CommandOutputListener;
import org.netbeans.modules.vcscore.commands.CommandDataOutputListener;

import org.netbeans.modules.vcs.profiles.list.AbstractListCommand;
import org.openide.util.NbBundle;

/**
 * List command for CVS.
 * @author  Martin Entlicher
 */
public class CvsListCommand extends AbstractListCommand {

    static final String CVS_DIRNAME = "CVS"; // NOI18N
    static final String[] CVS_DIRCONTENT = {"Entries", "Repository", "Root"}; // NOI18N
    static final String CVS_FILE_TAG = "Tag"; // NOI18N
    static final String EXAMINING_STR = ": Examining"; // NOI18N
    static final String MATCH_FILE = "File:"; // NOI18N
    static final String MATCH_STATUS = "Status:"; // NOI18N
    static final String MATCH_REVISION = "Working revision:"; // NOI18N
    static final String MATCH_STICKY_TAG = "Sticky Tag:"; // NOI18N
    static final String MATCH_STICKY_DATE = "Sticky Date:"; // NOI18N
    static final String MATCH_STICKY_OPTIONS = "Sticky Options:"; // NOI18N
    static final String STICKY_NONE = "(none)"; // NOI18N
    static final String STATUS_UNKNOWN = "Unknown"; // NOI18N
    static final String FILE_SEPARATOR = "===================================="; // NOI18N
    
    static final String LOG_WORKING_FILE = "Working file:"; // NOI18N
    static final String LOG_LOCKS = "locks:"; // NOI18N

    private String dir=null;

    private Vector files=new Vector(30);

    private StringBuffer dataBuffer=new StringBuffer(4096);

    /** Creates new CvsListCommand */
    public CvsListCommand() {
    }

    public void setFileSystem(VcsFileSystem fileSystem) {
        super.setFileSystem(fileSystem);
    }

    private void initDir(Hashtable vars) {
        String rootDir = (String) vars.get("ROOTDIR"); // NOI18N
        if (rootDir == null) {
            rootDir = "."; // NOI18N
        }
        this.dir = (String) vars.get("DIR"); // NOI18N
        if (this.dir == null) {
            this.dir = ""; // NOI18N
        }
        String module = (String) vars.get("MODULE"); // NOI18N
        if (dir.equals("")) { // NOI18N
            dir=rootDir;
            if (module != null && module.length() > 0) dir += File.separator + module;
        } else {
            if (module == null)
                dir=rootDir+File.separator+dir;
            else
                dir=rootDir+File.separator+module+File.separator+dir;
        }
        if (dir.charAt(dir.length() - 1) == File.separatorChar)
            dir = dir.substring(0, dir.length() - 1);
    }

    private boolean isCVSDir() {
        File d=new File(dir);
        String[] files=d.list();
        if (files != null) {
            for(int i = 0; i < files.length; i++)
                if (files[i].equals(CVS_DIRNAME)) return true;
        }
        shouldFail=true ;
        return false;
    }

    //------------------------------------------
    private boolean furtherExamining(String data, int index) {
        while(Character.isWhitespace(data.charAt(index))) index++;
        return (data.charAt(index) == '.');
    }

    /** Fill the data information into the hash table containing files
     * and their status attributes.
     */
    private void fillHashtableFromStatus(Hashtable filesByName) {
        String data=new String(dataBuffer);

        int pos=0;
        /* I expect file listing in the form: File: <filename> Status: <status>
         * There has to be info line about examining directories.
         * I.e. Regex: "^(File:.*Status:.*$)|( *Working revision:.*$)|( *Sticky .*$)|(cvs status.*)|(cvs server.*)"
         */
        int fileIndex;
        int examIndex = -1;
        examIndex = data.indexOf(EXAMINING_STR, pos);
        boolean examining = true;
        if (examIndex < 0) {
            //if (!shouldFail) E.err("Warning: No examining info from cvs status command !");
            examining = false;
        } else {
            examining = furtherExamining(data, examIndex += EXAMINING_STR.length());
            examIndex = data.indexOf(EXAMINING_STR, examIndex);
        }
        fileIndex = data.indexOf(MATCH_FILE, pos);
        while( examining && fileIndex >=0 ){
            int statusIndex = data.indexOf(MATCH_STATUS, fileIndex);
            int endFileIndex = data.indexOf(FILE_SEPARATOR, statusIndex);
            if (endFileIndex < 0) endFileIndex = data.length() - 1;
            fileIndex += MATCH_FILE.length();
            String fileName=data.substring(fileIndex,statusIndex).trim();
            int i=-1;
            if ((i=fileName.indexOf("no file")) >=0  ){ // NOI18N
                fileName=fileName.substring(i+7).trim();
            }
            int[] index = new int[] { statusIndex };
            String fileStatus = getAttribute(data, MATCH_STATUS, index);
            if (fileStatus == null) {
                fileStatus = STATUS_UNKNOWN;
            }
            String fileRevision = getAttribute(data, MATCH_REVISION, index);
            String fileDate = "";
            String fileTime = "";
            if (fileRevision == null) {
                fileRevision = "";
            } else {
                String revInfo = fileRevision;
                int endRevIndex = fileRevision.indexOf(" ");
                int endRevIndex1 = fileRevision.indexOf("\t");
                if (endRevIndex1 >= 0 && endRevIndex1 < endRevIndex) endRevIndex = endRevIndex1;
                if (endRevIndex < 0) endRevIndex = revInfo.length();
                fileRevision = revInfo.substring(0, endRevIndex);
                revInfo = revInfo.substring(endRevIndex).trim();
            }
            String fileStickyTag = getAttribute(data, MATCH_STICKY_TAG, index);
            if (fileStickyTag == null || index[0] > endFileIndex
                || STICKY_NONE.equals(fileStickyTag)) fileStickyTag = "";
            else {
                int spaceIndex = fileStickyTag.indexOf(" ");
                if (spaceIndex > 0) fileStickyTag = fileStickyTag.substring(0, spaceIndex);
            }
            String fileStickyDate = getAttribute(data, MATCH_STICKY_DATE, index);
            if (fileStickyDate == null || index[0] > endFileIndex
                || STICKY_NONE.equals(fileStickyDate)) fileStickyDate = "";
            String fileSticky = (fileStickyTag + " " + fileStickyDate).trim();
            
            String[] fileStatuses = new String[7];
            fileStatuses[0] = fileName;
            fileStatuses[1] = fileStatus;
            fileStatuses[2] = fileRevision;
            fileStatuses[3] = fileTime;
            fileStatuses[4] = fileDate;
            fileStatuses[5] = fileSticky;
            fileStatuses[6] = "";
            filesByName.put(fileName,fileStatuses);
            if (stdoutListener != null) stdoutListener.outputData(fileStatuses);
            pos = endFileIndex;
            fileIndex = data.indexOf(MATCH_FILE, pos);
            if (examIndex > 0 && examIndex < fileIndex) {
                examining = furtherExamining(data, examIndex += EXAMINING_STR.length());
                examIndex = data.indexOf(EXAMINING_STR, examIndex);
            }
        }
    }
    
    /** Fill the data information into the hash table containing files
     * and their status attributes.
     */
    private void fillHashtableFromLog(Hashtable filesByName) {
        String data = new String(dataBuffer);

        int pos=0;
        int fileIndex;
        while ((fileIndex = data.indexOf(LOG_WORKING_FILE, pos)) > 0) {
            fileIndex += LOG_WORKING_FILE.length();
            int eolIndex = data.indexOf('\n', fileIndex);
            if (eolIndex < 0) break;
            String fileName = data.substring(fileIndex, eolIndex).trim();
            String[] statuses = (String[]) filesByName.get(fileName);
            if (statuses == null) {
                statuses = new String[7];
                statuses[0] = fileName;
                statuses[1] = Statuses.STATUS_DEAD;
                filesByName.put(fileName, statuses);
            }
            pos = eolIndex;
            String revision = statuses[2];
            if (revision != null && revision.length() > 0) {
                String lockers = "";
                int lockIndex = data.indexOf(LOG_LOCKS, pos);
                if (lockIndex > 0) {
                    pos = lockIndex;
                    int lockerIndex;
                    eolIndex = data.indexOf('\n', lockIndex);
                    while(eolIndex > 0 && (lockerIndex = data.indexOf('\t', eolIndex)) == eolIndex + 1) {
                        eolIndex = data.indexOf('\n', lockerIndex);
                        if (eolIndex < 0) break;
                        String locker = data.substring(lockerIndex, eolIndex).trim();
                        int lockedRevisionIndex = locker.indexOf(':');
                        if (lockedRevisionIndex < 0) {
                            lockers += ((lockers.length() > 0) ? ", " : "") + locker;
                        } else {
                            String lockedRevision = locker.substring(lockedRevisionIndex + 1).trim();
                            if (revision.equals(lockedRevision)) {
                                locker = locker.substring(0, lockedRevisionIndex).trim();
                                lockers += ((lockers.length() > 0) ? ", " : "") + locker;
                            }
                        }
                    }
                }
                statuses[6] = lockers;
            }
            if (stdoutListener != null) stdoutListener.outputData(statuses);
        }
    }
    
    static String getAttribute(String data, String attr, int[] index) {
        String attrValue = null;
        int attrIndex = data.indexOf(attr, index[0]);
        if (attrIndex >= 0) {
            index[0] = attrIndex + attr.length();
            int eolIndex = data.indexOf("\n", index[0]);
            if (eolIndex < 0) eolIndex = data.length();
            attrValue = data.substring(index[0], eolIndex).trim();
        }
        return attrValue;
    }

    /**
    * Test if the directory was checked out by CVS or not.
    * @param dir the directory name to test
    * @return <code>true</code> if the directory was created by CVS, <code>false</code> if not.
    */
    public static boolean isCVSDirectory(File dir) {
        File subdir = new File(dir, CVS_DIRNAME);
        if (!subdir.isDirectory()) return false;
        for(int i = 0; i < CVS_DIRCONTENT.length; i++) {
            File cvsFile = new File(subdir, CVS_DIRCONTENT[i]);
            if (!cvsFile.isFile()) return false;
        }
        return true;
    }

    public static String findStickyOfDir(File dir) {
        String sticky = "";
        File tagFile = new File(dir + File.separator + CVS_DIRNAME, CVS_FILE_TAG);
        if (tagFile.canRead()) {
            InputStream in = null;
            try {
                in = new FileInputStream(tagFile);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line = reader.readLine();
                if (line != null && line.length() > 0) {
                    // assuming the sticky tag/date is the whole line without the first letter
                    sticky = line.substring(1);
                }
            } catch (FileNotFoundException fnfexc) {
                // Ignored
            } catch (IOException ioexc) {
                // Ignored
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException exc) {
                        // Ignored
                    }
                }
            }
        }
        return sticky;
    }

    /**
     * Add local directories with no status information.
     * @param filesByName the files container
     */
    private void addLocalFiles(Hashtable filesByName){
        File d=new File(dir);
        String[] files=d.list();
        if (files != null) {
            for(int i=0;i<files.length;i++){
                String[] fileStatuses = new String[7];
                fileStatuses[1] = ""; // NOI18N
                String fileName=files[i];

                File dirfile = new File(d+File.separator+fileName);
                if( dirfile.isDirectory() ){
                    fileName+="/"; // NOI18N
                } else continue;
                if( fileName.equals(CVS_DIRNAME+"/") ){ // NOI18N
                    continue;
                }

                if (!isCVSDirectory(dirfile)) continue;
                if (filesByName.get(fileName) == null) {
                    //System.out.println("addLocalFiles(): '"+fileName+"'");
                    fileStatuses[0] = fileName;
                    fileStatuses[5] = CvsListCommand.findStickyOfDir(dirfile);
                    filesByName.put(fileName, fileStatuses);
                    if (stdoutListener != null) stdoutListener.outputData(fileStatuses);
                }
            }
        }
    }

    //--------------------------------------------
    /**
     * List files of CVS Repository.
     * @param vars Variables used by the command
     * @param args Command-line arguments
     * filesByName listing of files with status attributes
     * @param stdoutNRListener listener of the standard output of the command
     * @param stderrNRListener listener of the error output of the command
     * @param stdoutListener listener of the standard output of the command which
     *                       satisfies regex <CODE>dataRegex</CODE>
     * @param dataRegex the regular expression for parsing the standard output
     * @param stderrListener listener of the error output of the command which
     *                       satisfies regex <CODE>errorRegex</CODE>
     * @param errorRegex the regular expression for parsing the error output
     */
    public boolean list(Hashtable vars, String[] args, Hashtable filesByName,
                        CommandOutputListener stdoutNRListener, CommandOutputListener stderrNRListener,
                        CommandDataOutputListener stdoutListener, String dataRegex,
                        CommandDataOutputListener stderrListener, String errorRegex) {

        this.stdoutNRListener = stdoutNRListener;
        this.stderrNRListener = new ErrorMsgTranslator(stderrNRListener);;
        this.stdoutListener = stdoutListener;
        this.stderrListener = stderrListener;
        this.dataRegex = dataRegex;
        this.errorRegex = errorRegex;
        initVars(vars);
        initDir(vars);
        if (args.length < 2) {
            stderrNRListener.outputLine("Expecting two commands as arguments!"); //NOI18N
            return false;
        }
        String statusCmd = args[0];
        String logCmd = args[1];
        try {
            runCommand(vars, statusCmd, true);
        } catch (InterruptedException iexc) {
            return false;
        }
        /*if (!shouldFail)*/ fillHashtableFromStatus(filesByName);
        String showDeadFiles = (String) vars.get(Variables.SHOW_DEAD_FILES);
        if (!shouldFail && showDeadFiles != null && showDeadFiles.trim().length() > 0) {
            dataBuffer.delete(0, dataBuffer.length());
            try {
                runCommand(vars, logCmd, true);
            } catch (InterruptedException iexc) {
                return false;
            }
            fillHashtableFromLog(filesByName);
        }
        addLocalFiles(filesByName);
        return !shouldFail;
    }

    public void outputData(String[] elements) {
        dataBuffer.append(elements[0]+"\n");
    }
    
    private static final class ErrorMsgTranslator extends Object implements CommandOutputListener {
        
        private static final String NO_ROOT = "CVSROOT: No such file or directory"; // NOI18N
        
        private CommandOutputListener errorListener;
        
        public ErrorMsgTranslator(CommandOutputListener errorListener) {
            this.errorListener = errorListener;
        }
        
        public void outputLine(String line) {
            int noRootIndex = line.indexOf(NO_ROOT);
            if (noRootIndex > 0) {
                String root = line.substring(0, noRootIndex);
                int bracket = root.indexOf('[');
                if (bracket > 0) {
                    int lastBracket = VcsUtilities.getPairIndex(line, bracket + 1, '[', ']');
                    if (lastBracket > 0) {
                        root = root.substring(lastBracket + 2).trim();
                    }
                }
                errorListener.outputLine(line); // write the original error
                errorListener.outputLine("");   // blank line to make it more readable
                String msg = NbBundle.getMessage(CvsListCommand.class, "ReposNotInitialized", root);
                errorListener.outputLine(msg);  // The "translated" message
            } else {
                errorListener.outputLine(line);
            }
        }
        
    }
}
