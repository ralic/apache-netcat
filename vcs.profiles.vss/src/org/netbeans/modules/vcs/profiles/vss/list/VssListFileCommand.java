/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2001 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.vcs.profiles.vss.list;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.io.*;

import org.netbeans.modules.vcscore.VcsFileSystem;
import org.netbeans.modules.vcscore.commands.*;
import org.netbeans.modules.vcscore.cmdline.VcsAdditionalCommand;
import org.netbeans.modules.vcscore.cmdline.ExecuteCommand;
import org.netbeans.modules.vcscore.util.*;

/**
 * List command for VSS.
 * @author  Martin Entlicher
 */
public class VssListFileCommand extends Object implements VcsAdditionalCommand, CommandDataOutputListener {

    private Debug E=new Debug("VssList", true);
    private Debug D=E;
    
    private static final int STATUS_POSITION = 19;
    
    private static final String NOT_EXISTING = "not an existing filename or project";
    private static final String STATUS_UNKNOWN = "Unknown"; // The local file status
    
    //private String dir=null; //, rootDir=null;
    //private String relDir = null;
    private String[] args=null;
    private volatile String[] statuses = null;
    private Hashtable vars = null;
    private VcsFileSystem fileSystem = null;
    private HashSet currentFiles = null;
    private HashSet missingFiles = null;
    private HashSet differentFiles = null;
    private Hashtable filesByName = new Hashtable();
    private CommandOutputListener stderrListener;
    /** is set to true when sb. interrupts me */
    private volatile boolean interrupted = false;

    /** Creates new VssListCommand */
    public VssListFileCommand() {
    }

    public void setFileSystem(VcsFileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    /*
    private void runCommand(Hashtable vars, String cmdName) {
        VcsCommand cmd = fileSystem.getCommand(cmdName);
        VcsCommandExecutor ec = fileSystem.getVcsFactory().getCommandExecutor(cmd, vars);
        ec.addDataOutputListener(this);
        fileSystem.getCommandsPool().preprocessCommand(ec, vars);
        fileSystem.getCommandsPool().startExecutor(ec);
        fileSystem.getCommandsPool().waitToFinish(ec);
        if (ec.getExitStatus() != VcsCommandExecutor.SUCCEEDED) {
            shouldFail=true;
        }
    }
     */
    
    /**
     * This method is used to execute the command.
     * @param vars the variables that can be passed to the command
     * @param args the command line parametres passed to it in properties
     * @param stdoutListener listener of the standard output of the command
     * @param stderrListener listener of the error output of the command
     * @param stdoutDataListener listener of the standard output of the command which
     *                          satisfies regex <CODE>dataRegex</CODE>
     * @param dataRegex the regular expression for parsing the standard output
     * @param stderrDataListener listener of the error output of the command which
     *                          satisfies regex <CODE>errorRegex</CODE>
     * @param errorRegex the regular expression for parsing the error output
     * @return true if the command was succesfull
     *        false if some error occured.
     */
    public boolean exec(Hashtable vars, String[] args,
                        CommandOutputListener stdoutListener, CommandOutputListener stderrListener,
                        CommandDataOutputListener stdoutDataListener, String dataRegex,
                        CommandDataOutputListener stderrDataListener, String errorRegex) {

        this.args = args;
        this.vars = vars;
        this.stderrListener = stderrListener;
        if (args.length < 2) {
            if (stderrListener != null) stderrListener.outputLine("Bad number of arguments. "+
                                                                  "Expecting two arguments: diff and status reader");
            return false;
        }
        Collection processingFiles = ExecuteCommand.createProcessingFiles(fileSystem, vars);
        boolean succeeded = true;
        missingFiles = new HashSet();
        currentFiles = new HashSet();
        differentFiles = new HashSet();
        //System.out.println("processingFiles = "+processingFiles);
        for (Iterator it = processingFiles.iterator(); it.hasNext() && !interrupted; ) {
            String file = (String) it.next();
            succeeded = getFileStatus(file, args[0]) && succeeded;
        }
        if (!interrupted) fillFilesByName();
        //System.out.println("filesByName = "+filesByName);
        sendFilesByName(stdoutDataListener);
        return succeeded;
    }
    
    private void sendFilesByName(CommandDataOutputListener stdoutDataListener) {
        for (Iterator elIt = filesByName.values().iterator(); elIt.hasNext(); ) {
            String[] elements = (String[]) elIt.next();
            stdoutDataListener.outputData(elements);
        }
    }
    
    private boolean getFileStatus(String file, String diffCmd) {
        if (!fileSystem.getFile(file).exists()) {
            missingFiles.add(file);
            return true;
        } else {
            Hashtable varsCmd = new Hashtable(vars);
            varsCmd.put("DIR", VcsUtilities.getDirNamePart(file));
            varsCmd.put("FILE", VcsUtilities.getFileNamePart(file));
            VcsCommand cmd = fileSystem.getCommand(diffCmd);
            if (cmd == null) {
                stderrListener.outputLine("Unknown command: "+diffCmd);
                return false;
            }
            VcsCommandExecutor vce = fileSystem.getVcsFactory().getCommandExecutor(cmd, varsCmd);
            final boolean[] differ = new boolean[1];
            differ[0] = false;
            vce.addDataOutputListener(new CommandDataOutputListener() {
                public void outputData(String[] elements) {
                    if (elements != null) {
                        //D.deb(" ****  status match = "+VcsUtilities.arrayToString(elements));
                        if (elements[0].length() > 0) differ[0] = true;
                    }
                }
            });
            fileSystem.getCommandsPool().preprocessCommand(vce, varsCmd, fileSystem);
            fileSystem.getCommandsPool().startExecutor(vce);
            try {
                fileSystem.getCommandsPool().waitToFinish(vce);
            } catch (InterruptedException iexc) {
                fileSystem.getCommandsPool().kill(vce);
                interrupted = true;
                return false;
            }
            if (differ[0]) {
                differentFiles.add(file);
            } else {
                currentFiles.add(file);
            }
            return vce.getExitStatus() == VcsCommandExecutor.SUCCEEDED || differ[0];
        }
    }
    
    private void fillFilesByName() {
        fillFilesByName(currentFiles, "Current");
        fillFilesByName(missingFiles, "Missing");
        fillFilesByName(differentFiles, "Locally Modified");
    }
    
    private void fillFilesByName(Set files, String status) {
        if (files == null) return ;
        for (Iterator fileIt = files.iterator(); fileIt.hasNext(); ) {
            String file = (String) fileIt.next();
            statuses = new String[3];
            statuses[0] = file;
            statuses[1] = status;
            VcsCommand cmd = fileSystem.getCommand(args[1]);
            if (cmd != null) {
                Hashtable varsCmd = new Hashtable(vars);
                varsCmd.put("DIR", VcsUtilities.getDirNamePart(file));
                varsCmd.put("FILE", VcsUtilities.getFileNamePart(file));
                //cmd.setProperty(UserCommand.PROPERTY_DATA_REGEX, "^"+file.substring(0, Math.min(STATUS_POSITION, file.length()))+" (.*$)");
                statuses[2] = null;
                final boolean[] existing = new boolean [1];
                existing[0] = true;
                VcsCommandExecutor vce = fileSystem.getVcsFactory().getCommandExecutor(cmd, varsCmd);
                vce.addDataOutputListener(new CommandDataOutputListener() {
                    public void outputData(String[] elements) {
                        if (elements != null) {
                            //D.deb(" ****  status match = "+VcsUtilities.arrayToString(elements));
                            if (elements[0].indexOf("$/") == 0) return ; // skip the $/... folder
                            addStatuses(elements);
                        }
                    }
                });
                vce.addDataErrorOutputListener(new CommandDataOutputListener() {
                    public void outputData(String[] elements2) {
                        if (elements2 != null) {
                            if (elements2[0].indexOf("$/") == 0) {
                                if (elements2[0].indexOf(NOT_EXISTING) > 0) {
                                    existing[0] = false;
                                }
                                return ; // skip the $/... folder
                            }
                        }
                    }
                });
                fileSystem.getCommandsPool().preprocessCommand(vce, varsCmd, fileSystem);
                fileSystem.getCommandsPool().startExecutor(vce);
                try {
                    fileSystem.getCommandsPool().waitToFinish(vce);
                } catch (InterruptedException iexc) {
                    fileSystem.getCommandsPool().kill(vce);
                    interrupted = true;
                    return ;
                }
                if (!existing[0]) statuses[1] = STATUS_UNKNOWN;
            } else statuses[2] = "";
            filesByName.put(statuses[0], statuses);
        }
    }
    
    private void readLocalFiles(String dir) {
        File fileDir = new File(dir);
        currentFiles = new HashSet();
        String[] subFiles = fileDir.list();
        if (subFiles == null) return ;
        for (int i = 0; i < subFiles.length; i++) {
            if (new File(dir, subFiles[i]).isFile()) {
                currentFiles.add(subFiles[i]);
            }
        }
    }

    private void addStatuses(String[] elements) {
        //D.deb(" !!!!!!!!!!  adding statuses "+VcsUtilities.arrayToString(elements));
        /*
        for (int i = 1; i < Math.min(elements.length, statuses.length); i++)
          statuses[i] = elements[i];
        */
        if (statuses[2] != null) return ; // The status is already set (it can be called more than once with some garbage then)
        int index = Math.min(STATUS_POSITION + 1, elements[0].length());
        int index2 = elements[0].indexOf(' ', index);
        if (index2 < 0) index2 = elements[0].length();
        if (index < index2) {
            statuses[2] = elements[0].substring(index, index2).trim();
        } else {
            statuses[2] = "";
        }
    }
    
    private int findWhiteSpace(String str) {
        return findWhiteSpace(str, 0);
    }
    
    private int findWhiteSpace(String str, int index) {
        for (int i = index; i < str.length(); i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    private String getFileFromRow(String row) {
        int index;
        //System.out.println("getFileFromRow("+row+")");
        for (index = findWhiteSpace(row); index >= 0 && index < row.length(); index = findWhiteSpace(row, index + 1)) {
            String file = row.substring(0, index).trim();
            //System.out.println("    test file = '"+file+"', contains = "+currentFiles.contains(file));
            if (currentFiles.contains(file)) break;
        }
        //System.out.println("   have CVS file = "+((index > 0 && index < row.length()) ? row.substring(0, index) : row));
        if (index > 0 && index < row.length()) return row.substring(0, index);
        else return row;
    }
    
    private boolean gettingFolders = true;
    private boolean gettingLocalFiles = false;
    private boolean gettingSourceSafeFiles = false;
    private boolean gettingDifferentFiles = false;
    private String lastFileName = "";
    
    private void removeLocalFiles() {
        //System.out.println("removeLocalFiles: "+lastFileName);
        if (lastFileName == null) return ;
        while (lastFileName.length() > 0) {
            String fileName = getFileFromRow(lastFileName);
            //System.out.println("file From Row = '"+fileName+"'");
            currentFiles.remove(fileName.trim());
            lastFileName = lastFileName.substring(fileName.length());
        }
    }
    
    private void flushLastFile() {
        if (lastFileName == null || lastFileName.length() == 0) return ;
        if (gettingSourceSafeFiles) {
            missingFiles.add(lastFileName.trim());
            lastFileName = "";
        } else if (gettingDifferentFiles) {
            differentFiles.add(lastFileName.trim());
            lastFileName = "";
        }
    }
    
    public void outputData(String[] elements) {
        String line = elements[0];
        //System.out.println("outputData("+line+")");
        if (line == null) return;
        
    }
    
}
