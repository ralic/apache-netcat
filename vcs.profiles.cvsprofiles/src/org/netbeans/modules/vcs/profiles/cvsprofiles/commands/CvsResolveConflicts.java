/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2000 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.vcs.profiles.cvsprofiles.commands;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Hashtable;

import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;

import org.netbeans.api.diff.Difference;
import org.netbeans.api.diff.StreamSource;
import org.netbeans.spi.diff.MergeVisualizer;
//import org.netbeans.modules.merge.builtin.visualizer.GraphicalMergeVisualizer;

import org.netbeans.modules.vcscore.VcsFileSystem;
import org.netbeans.modules.vcscore.commands.CommandOutputListener;
import org.netbeans.modules.vcscore.commands.CommandDataOutputListener;
import org.netbeans.modules.vcscore.cmdline.*;

/**
 * This class is used to resolve merge conflicts in a graphical way using a merge visualizer.
 * We parse the file with merge conflicts marked, let the conflicts resolve by the
 * visual merging tool and after successfull conflicts resolution save it back
 * to the original file.
 *
 * @author  Martin Entlicher
 */
public class CvsResolveConflicts implements VcsAdditionalCommand {
    
    private static final String TMP_PREFIX = "merge"; // NOI18N
    private static final String TMP_SUFFIX = "tmp"; // NOI18N
    
    private static final String CHANGE_LEFT = "<<<<<<< "; // NOI18N
    private static final String CHANGE_RIGHT = ">>>>>>> "; // NOI18N
    private static final String CHANGE_DELIMETER = "======="; // NOI18N

    private VcsFileSystem fileSystem = null;
    private String leftFileRevision = null;
    private String rightFileRevision = null;

    public void setFileSystem(VcsFileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    public boolean exec(Hashtable vars, String[] args,
                        CommandOutputListener stdoutNRListener, CommandOutputListener stderrNRListener,
                        CommandDataOutputListener stdoutListener, String dataRegex,
                        CommandDataOutputListener stderrListener, String errorRegex) {

        /*
        if (fileSystem instanceof CvsFileSystem) {
            CvsFileSystem cvsFileSystem = (CvsFileSystem) fileSystem;
         */
        Collection files = ExecuteCommand.createProcessingFiles(fileSystem, vars);
        MergeVisualizer merge = null;
        for (Iterator it = files.iterator(); it.hasNext(); ) {
            String fileName = (String) it.next();
            File file = fileSystem.getFile(fileName);
            if (file != null) {
                FileObject fo = fileSystem.findResource(fileName);
                if (merge == null) {
                    merge = (MergeVisualizer) Lookup.getDefault().lookup(MergeVisualizer.class);
                    if (merge == null) {
                        org.openide.TopManager.getDefault().notify(
                            new org.openide.NotifyDescriptor.Message(
                                org.openide.util.NbBundle.getMessage(CvsResolveConflicts.class, 
                                                                     "Merge.noMergeAvailable")));
                        return false;
                    }
                }
                try {
                    handleMergeFor(file, (fo == null) ? "text/plain" : fo.getMIMEType(), merge);
                } catch (IOException ioex) {
                    org.openide.TopManager.getDefault().notifyException(ioex);
                }
            }
        }
        return true;
    }
    
    private void handleMergeFor(final File file, String mimeType, MergeVisualizer merge) throws IOException {
        File f1 = File.createTempFile(TMP_PREFIX, TMP_SUFFIX);
        File f2 = File.createTempFile(TMP_PREFIX, TMP_SUFFIX);
        File f3 = File.createTempFile(TMP_PREFIX, TMP_SUFFIX);
        
        Difference[] diffs = copyParts(true, file, f1, true);
        if (diffs.length == 0) {
            org.openide.TopManager.getDefault ().notify (new org.openide.NotifyDescriptor.Message(
                org.openide.util.NbBundle.getMessage(CvsResolveConflicts.class, "NoConflictsInFile", file)));
            return ;
        }
        copyParts(false, file, f2, false);
        //GraphicalMergeVisualizer merge = new GraphicalMergeVisualizer();
        String originalLeftFileRevision = leftFileRevision;
        String originalRightFileRevision = rightFileRevision;
        if (leftFileRevision != null) leftFileRevision.trim();
        if (rightFileRevision != null) rightFileRevision.trim();
        java.awt.Component c;
        if (leftFileRevision == null || leftFileRevision.equals(file.getName())) {
            leftFileRevision = org.openide.util.NbBundle.getMessage(CvsResolveConflicts.class, "Diff.titleWorkingFile");
        } else {
            leftFileRevision = org.openide.util.NbBundle.getMessage(CvsResolveConflicts.class, "Diff.titleRevision", leftFileRevision);
        }
        if (rightFileRevision == null || rightFileRevision.equals(file.getName())) {
            rightFileRevision = org.openide.util.NbBundle.getMessage(CvsResolveConflicts.class, "Diff.titleWorkingFile");
        } else {
            rightFileRevision = org.openide.util.NbBundle.getMessage(CvsResolveConflicts.class, "Diff.titleRevision", rightFileRevision);
        }
        String resultTitle = org.openide.util.NbBundle.getMessage(CvsResolveConflicts.class, "Merge.titleResult");
        c = merge.createView(diffs, StreamSource.createSource(file.getName(), leftFileRevision, mimeType, f1),
                             StreamSource.createSource(file.getName(), rightFileRevision, mimeType, f2),
                             /*file.getName(), resultTitle,*/
                             new MergeResultWriterInfo(f1, f2, f3, file, mimeType,
                                                       originalLeftFileRevision,
                                                       originalRightFileRevision)/*, new VetoableChangeListener() {
                                 public void vetoableChange(PropertyChangeEvent evt) {
                                     if ("OK".equals(evt.getActionCommand())) {
                                         try {
                                            org.openide.filesystems.FileUtil.copy(
                                                new FileInputStream(f3), new FileOutputStream(file));
                                         } catch (IOException ioex) {
                                             org.openide.TopManager.getDefault().notify(new org.openide.NotifyDescriptor.Message(
                                                 org.openide.util.NbBundle.getMessage(CvsResolveConflicts.class,
                                                                                      "Merge.problemsSave",
                                                                                      ioex.getLocalizedMessage())));
                                         }
                                     }
                                 }
                             }*/);
    }
    
    /**
     * Copy the file and conflict parts into another file.
     */
    private Difference[] copyParts(boolean generateDiffs, File source,
                                   File dest, boolean leftPart) throws IOException {
        //System.out.println("copyParts("+generateDiffs+", "+source+", "+dest+", "+leftPart+")");
        BufferedReader r = new BufferedReader(new FileReader(source));
        BufferedWriter w = new BufferedWriter(new FileWriter(dest));
        ArrayList diffList = null;
        if (generateDiffs) {
            diffList = new ArrayList();
        }
        try {
            String line;
            boolean isChangeLeft = false;
            boolean isChangeRight = false;
            int f1l1 = 0, f1l2 = 0, f2l1 = 0, f2l2 = 0;
            StringBuffer text1 = new StringBuffer();
            StringBuffer text2 = new StringBuffer();
            int i = 1, j = 1;
            while ((line = r.readLine()) != null) {
                if (line.startsWith(CHANGE_LEFT)) {
                    if (generateDiffs) {
                        if (leftFileRevision == null) {
                            leftFileRevision = line.substring(CHANGE_LEFT.length());
                        }
                        if (isChangeLeft) {
                            f1l2 = i - 1;
                            diffList.add((f1l1 > f1l2) ? new Difference(Difference.ADD,
                                                                        f1l1 - 1, 0, f2l1, f2l2,
                                                                        text1.toString(),
                                                                        text2.toString()) :
                                         (f2l1 > f2l2) ? new Difference(Difference.DELETE,
                                                                        f1l1, f1l2, f2l1 - 1, 0,
                                                                        text1.toString(),
                                                                        text2.toString())
                                                       : new Difference(Difference.CHANGE,
                                                                        f1l1, f1l2, f2l1, f2l2,
                                                                        text1.toString(),
                                                                        text2.toString()));
                            f1l1 = f1l2 = f2l1 = f2l2 = 0;
                            text1.delete(0, text1.length());
                            text2.delete(0, text2.length());
                        } else {
                            f1l1 = i;
                        }
                    }
                    isChangeLeft = !isChangeLeft;
                    continue;
                } else if (line.startsWith(CHANGE_RIGHT)) {
                    if (generateDiffs) {
                        if (rightFileRevision == null) {
                            rightFileRevision = line.substring(CHANGE_RIGHT.length());
                        }
                        if (isChangeRight) {
                            f2l2 = j - 1;
                            diffList.add((f1l1 > f1l2) ? new Difference(Difference.ADD,
                                                                        f1l1 - 1, 0, f2l1, f2l2,
                                                                        text1.toString(),
                                                                        text2.toString()) :
                                         (f2l1 > f2l2) ? new Difference(Difference.DELETE,
                                                                        f1l1, f1l2, f2l1 - 1, 0,
                                                                        text1.toString(),
                                                                        text2.toString())
                                                       : new Difference(Difference.CHANGE,
                                                                        f1l1, f1l2, f2l1, f2l2,
                                                                        text1.toString(),
                                                                        text2.toString()));
                                                       /*
                            diffList.add(new Difference((f1l1 > f1l2) ? Difference.ADD :
                                                        (f2l1 > f2l2) ? Difference.DELETE :
                                                                        Difference.CHANGE,
                                                        f1l1, f1l2, f2l1, f2l2));
                                                        */
                            f1l1 = f1l2 = f2l1 = f2l2 = 0;
                            text1.delete(0, text1.length());
                            text2.delete(0, text2.length());
                        } else {
                            f2l1 = j;
                        }
                    }
                    isChangeRight = !isChangeRight;
                    continue;
                } else if (line.equals(CHANGE_DELIMETER)) {
                    if (isChangeLeft) {
                        isChangeLeft = false;
                        isChangeRight = true;
                        f1l2 = i - 1;
                        f2l1 = j;
                        continue;
                    } else if (isChangeRight) {
                        isChangeRight = false;
                        isChangeLeft = true;
                        f2l2 = j - 1;
                        f1l1 = i;
                        continue;
                    }
                }
                if (!isChangeLeft && !isChangeRight || leftPart == isChangeLeft) {
                    w.write(line);
                    w.newLine();
                }
                if (isChangeLeft) text1.append(line + "\n");
                if (isChangeRight) text2.append(line + "\n");
                if (generateDiffs) {
                    if (isChangeLeft) i++;
                    else if (isChangeRight) j++;
                    else {
                        i++;
                        j++;
                    }
                }
            }
        } finally {
            try {
                r.close();
            } finally {
                w.close();
            }
        }
        if (generateDiffs) {
            return (Difference[]) diffList.toArray(new Difference[diffList.size()]);
        } else {
            return null;
        }
    }
    
    private static class MergeResultWriterInfo extends StreamSource {
        
        private File tempf1, tempf2, tempf3, outputFile;
        private String mimeType;
        private String leftFileRevision;
        private String rightFileRevision;
        
        public MergeResultWriterInfo(File tempf1, File tempf2, File tempf3,
                                     File outputFile, String mimeType,
                                     String leftFileRevision, String rightFileRevision) {
            this.tempf1 = tempf1;
            this.tempf2 = tempf2;
            this.tempf3 = tempf3;
            this.outputFile = outputFile;
            this.mimeType = mimeType;
            this.leftFileRevision = leftFileRevision;
            this.rightFileRevision = rightFileRevision;
        }
        
        public String getName() {
            return outputFile.getName();
        }
        
        public String getTitle() {
            return org.openide.util.NbBundle.getMessage(CvsResolveConflicts.class, "Merge.titleResult");
        }
        
        public String getMIMEType() {
            return mimeType;
        }
        
        public Reader createReader() throws IOException {
            throw new IOException("No reader of merge result"); // NOI18N
        }
        
        /**
         * Create a writer, that writes to the source.
         * @param conflicts The list of conflicts remaining in the source.
         *                  Can be <code>null</code> if there are no conflicts.
         * @return The writer or <code>null</code>, when no writer can be created.
         */
        public Writer createWriter(Difference[] conflicts) throws IOException {
            if (conflicts == null || conflicts.length == 0) {
                return new FileWriter(outputFile);
            } else {
                return new MergeConflictFileWriter(outputFile, conflicts,
                                                   leftFileRevision, rightFileRevision);
            }
        }
        
        /**
         * This method is called when the visual merging process is finished.
         * All possible writting processes are finished before this method is called.
         */
        public void notifyClosed() {
            tempf1.delete();
            tempf2.delete();
            tempf3.delete();
        }
        
    }
    
    private static class MergeConflictFileWriter extends FileWriter {
        
        private Difference[] conflicts;
        private int lineNumber;
        private int currentConflict;
        private String leftName;
        private String rightName;
        
        public MergeConflictFileWriter(File file, Difference[] conflicts,
                                       String leftName, String rightName) throws IOException {
            super(file);
            this.conflicts = conflicts;
            this.leftName = leftName;
            this.rightName = rightName;
            this.lineNumber = 1;
            this.currentConflict = 0;
            if (lineNumber == conflicts[currentConflict].getFirstStart()) {
                writeConflict(conflicts[currentConflict]);
                currentConflict++;
            }
        }
        
        public void write(String str) throws IOException {
            //System.out.println("MergeConflictFileWriter.write("+str+")");
            super.write(str);
            lineNumber += numChars('\n', str);
            //System.out.println("  lineNumber = "+lineNumber+", current conflict start = "+conflicts[currentConflict].getFirstStart());
            if (currentConflict < conflicts.length && lineNumber >= conflicts[currentConflict].getFirstStart()) {
                writeConflict(conflicts[currentConflict]);
                currentConflict++;
            }
        }
        
        private void writeConflict(Difference conflict) throws IOException {
            //System.out.println("MergeConflictFileWriter.writeConflict('"+conflict.getFirstText()+"', '"+conflict.getSecondText()+"')");
            super.write(CHANGE_LEFT + leftName + "\n");
            super.write(conflict.getFirstText());
            super.write(CHANGE_DELIMETER + "\n");
            super.write(conflict.getSecondText());
            super.write(CHANGE_RIGHT + rightName + "\n");
        }
        
        private static int numChars(char c, String str) {
            int n = 0;
            for (int pos = str.indexOf(c); pos >= 0 && pos < str.length(); pos = str.indexOf(c, pos + 1)) {
                n++;
            }
            return n;
        }
    }
}

