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

package org.netbeans.modules.vcs.advanced;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.beans.*;
import java.text.*;
import javax.swing.*;

import org.openide.util.actions.*;
import org.openide.util.NbBundle;
import org.openide.*;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.FileSystem.Status;
import org.openide.filesystems.FileUtil;
import org.openide.filesystems.FileStateInvalidException;
import org.openide.filesystems.AbstractFileSystem;
import org.openide.filesystems.DefaultAttributes;
import org.openide.nodes.Node;
import org.openide.nodes.Children;

import org.netbeans.modules.vcscore.*;
import org.netbeans.modules.vcscore.cmdline.UserCommand;
import org.netbeans.modules.vcscore.commands.CommandOutputListener;
import org.netbeans.modules.vcscore.commands.CommandDataOutputListener;
import org.netbeans.modules.vcscore.commands.CommandsPool;
import org.netbeans.modules.vcscore.commands.VcsCommand;
import org.netbeans.modules.vcscore.commands.VcsCommandExecutor;
import org.netbeans.modules.vcscore.commands.VcsCommandNode;
import org.netbeans.modules.vcscore.util.*;

import org.netbeans.modules.vcs.advanced.variables.VariableIO;
import org.netbeans.modules.vcs.advanced.variables.VariableIOCompat;

/** Generic command line VCS filesystem.
 * 
 * @author Michal Fadljevic, Martin Entlicher
 */
//-------------------------------------------
public class CommandLineVcsFileSystem extends VcsFileSystem implements java.beans.PropertyChangeListener {
    
    public static final String VAR_LOCAL_FILES_FILTERED_OUT = "LOCAL_FILES_FILTERED_OUT";
    public static final String VAR_LOCAL_FILES_FILTERED_OUT_CASE_SENSITIVE = "LOCAL_FILES_FILTERED_OUT_CASE_SENSITIVE";
    public static final String VAR_POSSIBLE_FILE_STATUSES = "POSSIBLE_FILE_STATUSES";
    public static final String VAR_POSSIBLE_FILE_STATUSES_LOCALIZED = "POSSIBLE_FILE_STATUSES_LOCALIZED";
    public static final String VAR_POSSIBLE_FILE_STATUSES_LOCALIZED_SHORT = "POSSIBLE_FILE_STATUSES_LOCALIZED_SHORT";
    public static final String VAR_NOT_MODIFIABLE_FILE_STATUSES = "NOT_MODIFIABLE_FILE_STATUSES";
    public static final String VAR_ICONS_FOR_FILE_STATUSES = "ICONS_FOR_FILE_STATUSES";
    
    /**
     * The name of a variable, which contains the parent ignore list for
     * CREATE_FOLDER_IGNORE_LIST command.
     */
    public static final String VAR_PARENT_IGNORE_LIST = "PARENT_IGNORE_LIST";
    
    /**
     * This command is executed to get the initial ignore list.
     * It's supposed to return the ignored files on its data output.
     */
    public static final String CMD_CREATE_INITIAL_IGNORE_LIST = "CREATE_INITIAL_IGNORE_LIST";
    /**
     * This command is executed to get the ignore list on each folder.
     * It gets the parent ignore list in PARENT_IGNORE_LIST variable.
     * It's supposed to return the ignored files on its data output.
     */
    public static final String CMD_CREATE_FOLDER_IGNORE_LIST = "CREATE_FOLDER_IGNORE_LIST";
    
    public static final String PROP_SHORT_FILE_STATUSES = "shortFileStatuses";
    
    private static final boolean DEFAULT_LOCAL_FILE_FILTER_CASE_SENSITIVE = true;
    
    //private static final String DEFAULT_CONFIG_NAME = "empty.xml";
    //private static final String DEFAULT_CONFIG_NAME_COMPAT = "empty.properties";
    
    private static ResourceBundle resourceBundle = null;

    public static final String TEMPORARY_CONFIG_FILE_NAME = "tmp"; // NOI18N

    private String config = null;//"Empty (Unix)"; // NOI18N

    private Debug D = new Debug ("CommandLineVcsFileSystem", true); // NOI18N
    private /*static transient*/ String CONFIG_ROOT="vcs/config"; // NOI18N
    private FileObject CONFIG_ROOT_FO;
    private String configFileName = null;
    private transient Hashtable commandsByName=null;
    private HashMap additionalPossibleFileStatusesMap = null;
    private transient HashMap additionalStatusIconMap = null;
    private Vector localFilesFilteredOut = null;
    private boolean localFileFilterCaseSensitive = DEFAULT_LOCAL_FILE_FILTER_CASE_SENSITIVE;
    private Vector docCleanupRemoveItems = null;
    private static final String CACHE_FILE_NAME = "vcs.cache";
    private String cacheRoot;
    private String cachePath;
    private long cacheId = 0;
    private boolean shortFileStatuses = false;
    private Set compatibleOSs = null;
    private Set uncompatibleOSs = null;
    private transient boolean doInitialCheckout = false; // whether to do an initial checkout after the FS is mounted

    static final long serialVersionUID =-1017235664394970926L;
    //-------------------------------------------
    public CommandLineVcsFileSystem () {
        //D.deb("CommandLineVcsFileSystem()"); // NOI18N
        super ();
        setConfigFO();
        //boolean status = readConfiguration (DEFAULT_CONFIG_NAME);
        //if (status == false) readConfigurationCompat(DEFAULT_CONFIG_NAME_COMPAT);
        setCommands(new VcsCommandNode(new Children.Array(), new UserCommand("NONE")));
        addPropertyChangeListener(this);
        /*
        cacheRoot = System.getProperty("netbeans.user")+File.separator+
                    "system"+File.separator+"vcs"+File.separator+"cache"; // NOI18N
         */
        cachePath = createNewCacheDir();
        setCreateVersioningSystem(true);
        try {
            setRootDirectory(getRootDirectory(), true);
        } catch (PropertyVetoException vetoExc) {
        } catch (IOException ioExc) {
        }
        setIgnoreListSupport(new GenericIgnoreListSupport());
        setCreateBackupFiles(true);
        setFilterBackupFiles(true);
    }

    public VcsFactory getVcsFactory () {
        return new CommandLineVcsFactory (this);
    }

    /**
     * Get the root of the configuration files
     */
    public String getConfigRoot(){
        return CONFIG_ROOT;
    }

    /**
     * Get the root of the configuration as a FileObject.
     */
    public FileObject getConfigRootFO() {
        return CONFIG_ROOT_FO;
    }
    
    public void setConfigRoot(String s) {
        CONFIG_ROOT = s;
    }

    public void setConfig(String label) {
        this.config = label;
    }
    
    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }

    private void setConfigFO() {
        FileSystem dfs = TopManager.getDefault ().getRepository ().getDefaultFileSystem ();
        FileObject fo = dfs.findResource(CONFIG_ROOT);
        if (fo == null) {
            javax.swing.SwingUtilities.invokeLater(new Runnable () {
                public void run () {
                    TopManager.getDefault ().notify (new NotifyDescriptor.Message (CommandLineVcsFileSystem.this.clg("DLG_ConfigurationPathNotFound", CONFIG_ROOT)));
                }
            });
        }
        CONFIG_ROOT_FO = fo;
    }

    /**
     * Get the configuration display name or null, when no configuration is loaded.
     */
    public String getConfig() {
        return config;
    }

    /*
     * Get the cache identification.
     *
    public String getCacheIdStr() {
        System.out.println("CmdLineVcsFileSystem.getCacheIdStr(): cacheId = "+cacheId);
        Thread.dumpStack();
        return "VCS_Cache" + getCacheId();
    }
     */
    
    /**
     * Get the ID of the disk cache. Note, that this does not have any connection
     * to VcsFileSystem.getCacheIdStr(), which returns the string identification
     * of a memory cache.
     */
    public long getCacheId() {
        if (cacheId == 0) {
            createNewCacheId();
        }
        return cacheId;
    }

    /**
     * Get the full file path where cache information should be stored.
     */
    public String getCacheFileName(String path) {
        return cachePath + File.separator + getRelativeMountPoint()
               + File.separator + path + File.separator + CACHE_FILE_NAME;
        /*
        File file = getFile(path);
        if (!file.isDirectory()) file = file.getParentFile();
        return file.getAbsolutePath() + File.separator + CVS_DIRNAME + File.separator + CACHE_FILE_NAME;
         */
    }

    private void createDir(String path) {
        File dir = new File(path);
        if (dir.isDirectory()) {
            return ;
        }
        if (dir.mkdirs() == false) {
            //E.err(g("MSG_UnableToCreateDirectory", path)); // NOI18N
            debug(clg("MSG_UnableToCreateDirectory", path)); // NOI18N
        }
    }
    
    private void createNewCacheId() {
        cacheRoot = System.getProperty("netbeans.user")+File.separator+
                    "system"+File.separator+"vcs"+File.separator+"cache"; // NOI18N
        do {
            cacheId = 10000 * (1 + Math.round (Math.random () * 8)) + Math.round (Math.random () * 1000);
        } while (new File(cacheRoot+File.separator+cacheId).isDirectory ());
        FileSystem dfs = TopManager.getDefault ().getRepository ().getDefaultFileSystem ();
        FileObject vcs = dfs.findResource("vcs");
        try {
            if (vcs == null) {
                vcs = dfs.getRoot().createFolder("vcs");
            }
            FileObject cache = vcs.getFileObject("cache");
            if (cache == null) {
                cache = vcs.createFolder("cache");
            }
        } catch (java.io.IOException exc) {
        }
    }

    private String createNewCacheDir() {
        String dir;
        if (cacheId == 0) {
            createNewCacheId();
        }
        dir = cacheRoot+File.separator+cacheId;
        createDir(dir);
        return dir;
    }

    /**
     * Set whether to do initial checkout after mount or not.
     */
    public void setInitialCheckout(boolean doInitialCheckout) {
        this.doInitialCheckout = doInitialCheckout;
    }
    
    /**
     * Notification, that the filesystem is being added to the repository
     */
    public void addNotify() {
        if (this.doInitialCheckout) {
            Table files = new Table();
            files.put("", findResource(""));
            VcsCommand cmd = getCommand("CHECKOUT");
            if (cmd != null) {
                VcsAction.doCommand(files, cmd, null, this);
            }
        }
        super.addNotify();
    }    
    
    public void removeNotify() {
        File dir = new File (cachePath);
        if(dir.exists () && dir.isDirectory () && dir.canWrite ()) {
            if(!VcsUtilities.deleteRecursive(dir)) {
                // Ignored. Let it be, when I can not remove it.
            }
        }
        super.removeNotify();
    }
    
    public boolean readConfigurationCompat (String configFileName) {
        D.deb ("readConfigurationCompat ()"); // NOI18N
        //CONFIG_ROOT=System.getProperty("netbeans.user")+File.separator+
        //            "system"+File.separator+"vcs"+File.separator+"config"; // NOI18N
        //CONFIG_ROOT = "vcs"+File.separator+"config"; // NOI18N
        //setConfigFO();
        if (CONFIG_ROOT_FO == null) return false;
        //Properties props=VcsConfigVariable.readPredefinedPropertiesIO(CONFIG_ROOT+File.separator+"empty.properties"); // NOI18N
        Properties props = VariableIOCompat.readPredefinedProperties(CONFIG_ROOT_FO, configFileName); // NOI18N
        String label = props.getProperty("label", clg("CTL_No_label_configured"));
        setVariables (VariableIOCompat.readVariables(props));
        D.deb("setVariables DONE."); // NOI18N
        
        setCommands ((org.openide.nodes.Node) CommandLineVcsAdvancedCustomizer.readConfig (props));
        D.deb("readConfigurationCompat() done"); // NOI18N
        setConfig(label);
        return true;
    }

    public boolean readConfiguration (String configFileName) {
        D.deb ("readConfiguration ()"); // NOI18N
        //CONFIG_ROOT=System.getProperty("netbeans.user")+File.separator+
        //            "system"+File.separator+"vcs"+File.separator+"config"; // NOI18N
        //CONFIG_ROOT = "vcs"+File.separator+"config"; // NOI18N
        //setConfigFO();
        if (CONFIG_ROOT_FO == null) return false;
        //Properties props=VcsConfigVariable.readPredefinedPropertiesIO(CONFIG_ROOT+File.separator+"empty.properties"); // NOI18N
        org.w3c.dom.Document doc = VariableIO.readPredefinedConfigurations(CONFIG_ROOT_FO, configFileName); // NOI18N
        if (doc == null) return false;
        String label = VariableIO.getConfigurationLabel(doc);
        setVariables (VariableIO.readVariables(doc));
        D.deb("setVariables DONE."); // NOI18N
        
        setCommands ((org.openide.nodes.Node) CommandLineVcsAdvancedCustomizer.readConfig (doc));
        D.deb("readConfiguration() done"); // NOI18N
        setConfig(label);
        return true;
    }

    /**
     * Call this method to perform the login process.
     */
    public boolean checkLogin(String connectStr, String password) 
            throws IOException, java.net.UnknownHostException {
        VcsCommand cmd = getCommand("DO_LOGIN");
        if (cmd == null) return true; // suppose, that the login is O.K.
        Table files = new Table();
        Hashtable additionalVars = new Hashtable();
        additionalVars.put("CONNECT_STR", connectStr);
        additionalVars.put("PASSWORD", password);
        final StringBuffer errOutput = new StringBuffer();
        CommandOutputListener errListener = new CommandOutputListener() {
            public void outputLine(String line) {
                errOutput.append(line + "\n");
            }
        };
        files.put("", null); // the command will not run if no files would be supplied.
        VcsCommandExecutor[] execs = VcsAction.doCommand(files, cmd, additionalVars, this, null, errListener, null, null);
        if (execs.length == 0) return true;
        VcsCommandExecutor exec = execs[0];
        getCommandsPool().waitToFinish(exec);
        boolean succeeded = (exec.getExitStatus() == VcsCommandExecutor.SUCCEEDED);
        if (!succeeded) {
            throw new java.io.IOException() {
                public String getLocalizedMessage() {
                    return errOutput.toString();
                }
            };
        }
        return succeeded;
    }
    
    /**
     * Allows some cleanup of the document which the user is asked for.
     * doc The Document
     * promptNum the order of the document
     * docIdentif some identification that can be set in settting the listener.
     */
    public void filePromptDocumentCleanup(javax.swing.JTextArea ta, int promptNum, Object docIdentif) {
        // Let the document unchanged by default
        javax.swing.text.Document doc = ta.getDocument();
        if (docIdentif instanceof UserCommand) {
            UserCommand cmd = (UserCommand) docIdentif;
            if (docCleanupRemoveItems != null) {
                for(int i = 0; i < docCleanupRemoveItems.size(); i++) {
                    CommandLineVcsFileSystem.DocCleanupRemoveItem item = (CommandLineVcsFileSystem.DocCleanupRemoveItem) docCleanupRemoveItems.get(i);
                    if (cmd.getName().equals(item.getCmdName()) && promptNum == item.getOrder()) {
                        String lineBegin = item.getLineBegin();
                        for(int line = 0; line < ta.getLineCount(); line++) {
                            try {
                                int begin = ta.getLineStartOffset(line);
                                int end = ta.getLineEndOffset(line);
                                String lineStr = doc.getText(begin, end - begin);
                                if (lineStr.regionMatches(0, lineBegin, 0, lineBegin.length())) {
                                    doc.remove(begin, end - begin);
                                    line--;
                                }
                            } catch (javax.swing.text.BadLocationException exc) {
                                org.openide.TopManager.getDefault().notifyException(exc);
                            }
                        }
                    }
                }
            }
        }
    }

    public void propertyChange (PropertyChangeEvent evt) {
        if (evt.getPropertyName() != FileSystem.PROP_VALID) return;
        if (isValid()) {
            D.deb("Filesystem added to the repository, setting refresh time to "+refreshTimeToSet); // NOI18N
            setRefreshTime(refreshTimeToSet);
            warnDirectoriesDoNotExists();
        } else {
            D.deb("Filesystem is not valid any more, setting refresh time to 0"); // NOI18N
            setRefreshTime(0);
        }
    }
    
    public boolean isShortFileStatuses() {
        return shortFileStatuses;
    }
    
    public void setShortFileStatuses(boolean shortFileStatuses) {
        if (this.shortFileStatuses != shortFileStatuses) {
            this.shortFileStatuses = shortFileStatuses;
            setPossibleFileStatusesFromVars();
            refreshStatusOfExistingFiles();
        }
    }
    
    protected void refreshStatusOfExistingFiles() {
        Enumeration enum = existingFileObjects(getRoot());
        HashSet existingFOs = new HashSet();
        while (enum.hasMoreElements()) {
            existingFOs.add(enum.nextElement());
        }
        fireFileStatusChanged(new org.openide.filesystems.FileStatusEvent(this, existingFOs, false, true));
    }
    
    private void setPossibleFileStatusesFromVars() {
        VcsConfigVariable varStatuses = (VcsConfigVariable) variablesByName.get (VAR_POSSIBLE_FILE_STATUSES);
        VcsConfigVariable varStatusesLclz;
        if (isShortFileStatuses()) {
            varStatusesLclz = (VcsConfigVariable) variablesByName.get (VAR_POSSIBLE_FILE_STATUSES_LOCALIZED_SHORT);
        } else {
            varStatusesLclz = (VcsConfigVariable) variablesByName.get (VAR_POSSIBLE_FILE_STATUSES_LOCALIZED);
        }
        if (additionalPossibleFileStatusesMap != null) VcsUtilities.removeKeys(possibleFileStatusesMap, additionalPossibleFileStatusesMap);
        additionalPossibleFileStatusesMap = null;
        if (varStatuses != null) {
            additionalPossibleFileStatusesMap = new HashMap();
            String[] possStatuses = VcsUtilities.getQuotedStrings(varStatuses.getValue());
            String[] possStatusesLclz = null;
            if (varStatusesLclz != null) possStatusesLclz = VcsUtilities.getQuotedStrings(varStatusesLclz.getValue());
            int i = 0;
            if (possStatusesLclz != null) {
                for(; i < possStatuses.length && i < possStatusesLclz.length; i++) {
                    additionalPossibleFileStatusesMap.put(possStatuses[i], possStatusesLclz[i]);
                }
            }
            for(; i < possStatuses.length; i++) {
                additionalPossibleFileStatusesMap.put(possStatuses[i], possStatuses[i]);
            }
            possibleFileStatusesMap.putAll(additionalPossibleFileStatusesMap);
        }
    }
    
    private void setBadgeIconsFromVars() {
        VcsConfigVariable varStatuses = (VcsConfigVariable) variablesByName.get (VAR_POSSIBLE_FILE_STATUSES);
        VcsConfigVariable varIcons = (VcsConfigVariable) variablesByName.get (VAR_ICONS_FOR_FILE_STATUSES);
        if (additionalStatusIconMap != null) VcsUtilities.removeKeys(statusIconMap, additionalStatusIconMap);
        additionalStatusIconMap = null;
        if (varStatuses != null) {
            additionalStatusIconMap = new HashMap();
            String[] possStatuses = VcsUtilities.getQuotedStrings(varStatuses.getValue());
            String[] iconResources = null;
            if (varIcons != null) iconResources = VcsUtilities.getQuotedStrings(varIcons.getValue());
            if (iconResources != null) {
                FileSystem defaultFS = TopManager.getDefault().getRepository().getDefaultFileSystem();
                for (int i = 0; i < possStatuses.length && i < iconResources.length; i++) {
                    if (iconResources[i].length() == 0) continue;
                    FileObject resourceFile = defaultFS.findResource(iconResources[i]);
                    if (resourceFile == null) {
                        TopManager.getDefault().notify(new NotifyDescriptor.Message(
                            NbBundle.getMessage(CommandLineVcsFileSystem.class, "MSG_CanNotFindIconResource", iconResources[i])));
                        continue;
                    }
                    try {
                        additionalStatusIconMap.put(possStatuses[i], new javax.swing.ImageIcon(resourceFile.getURL()).getImage());
                    } catch (FileStateInvalidException exc) {
                        TopManager.getDefault().notify(new NotifyDescriptor.Message(
                            NbBundle.getMessage(CommandLineVcsFileSystem.class, "MSG_InvalidFileIconResource", iconResources[i])));
                        continue;
                    }
                }
                statusIconMap.putAll(additionalStatusIconMap);
            }
        }
    }
    
    private void setNotModifiableStatusesFromVars() {
        VcsConfigVariable var = (VcsConfigVariable) variablesByName.get(VAR_NOT_MODIFIABLE_FILE_STATUSES);
        if (var != null) {
            String[] statuses = VcsUtilities.getQuotedStrings(var.getValue());
            setNotModifiableStatuses(new ArrayList(Arrays.asList(statuses))); // using Arraylist to be Serializable
        }
    }
    
    private void setLocalFileFilterFromVars() {
        VcsConfigVariable varLocalFilter = (VcsConfigVariable) variablesByName.get(VAR_LOCAL_FILES_FILTERED_OUT);
        VcsConfigVariable varLocalFilterCS = (VcsConfigVariable) variablesByName.get(VAR_LOCAL_FILES_FILTERED_OUT_CASE_SENSITIVE);
        if (varLocalFilter != null) {
            if (varLocalFilterCS != null) {
                localFileFilterCaseSensitive = varLocalFilterCS.getValue().equalsIgnoreCase("true");
            }
            String qfiles = varLocalFilter.getValue();
            if (!localFileFilterCaseSensitive) qfiles = qfiles.toUpperCase();
            String[] files = VcsUtilities.getQuotedStrings(qfiles);
            localFilesFilteredOut = new Vector(Arrays.asList(files));
        } else localFilesFilteredOut = null;
    }
    
    private void setDocumentCleanupFromVars() {
        VcsConfigVariable docCleanupRemove;
        docCleanupRemoveItems = null;
        for(int i = 1; (docCleanupRemove = (VcsConfigVariable) variablesByName.get("DOCUMENT_CLEANUP_REMOVE"+i)) != null; i++) {
            String[] removeWhat = VcsUtilities.getQuotedStrings(docCleanupRemove.getValue());
            if (removeWhat.length < 3) continue;
            int order = 0;
            try {
                order = Integer.parseInt(removeWhat[1]);
                order--;
            } catch (NumberFormatException exc) {
                org.openide.TopManager.getDefault().notifyException(exc);
                continue;
            }
            CommandLineVcsFileSystem.DocCleanupRemoveItem item = new CommandLineVcsFileSystem.DocCleanupRemoveItem(removeWhat[0], order, removeWhat[2]);
            if (docCleanupRemoveItems == null) docCleanupRemoveItems = new Vector();
            docCleanupRemoveItems.add(item);
        }
    }
    
    private void setAdditionalParamsLabels() {
        VcsConfigVariable userParams = (VcsConfigVariable) variablesByName.get("USER_GLOBAL_PARAM_LABELS");
        if (userParams != null) {
            setUserParamsLabels(VcsUtilities.getQuotedStrings(userParams.getValue()));
        }
        userParams = (VcsConfigVariable) variablesByName.get("USER_LOCAL_PARAM_LABELS");
        if (userParams != null) {
            setUserLocalParamsLabels(VcsUtilities.getQuotedStrings(userParams.getValue()));
        }
    }
    
    /**
     * Set the file system's variables.
     * @param variables the vector of <code>VcsConfigVariable</code> objects.
     */
    public void setVariables(Vector variables){
        super.setVariables(variables);
        setPossibleFileStatusesFromVars();
        setBadgeIconsFromVars();
        setNotModifiableStatusesFromVars();
        setLocalFileFilterFromVars();
        setDocumentCleanupFromVars();
        setAdditionalParamsLabels();
    }

    public FilenameFilter getLocalFileFilter() {
        return new FilenameFilter() {
                   public boolean accept(File dir, String name) {
                       if (!localFileFilterCaseSensitive) name = name.toUpperCase();
                       if (localFilesFilteredOut == null) return true;
                       else return !localFilesFilteredOut.contains(name);
                       //return !name.equalsIgnoreCase("CVS"); // NOI18N
                   }
               };
    }
    
    /**
     * Finds out, whether the configuration file name is a temporary configuration.
     * Temporary configurations are saved during serialization of the FS.
     */
    public static boolean isTemporaryConfig(String configFileName) {
        if (configFileName.startsWith(TEMPORARY_CONFIG_FILE_NAME)) {
            String tempNo = configFileName.substring(TEMPORARY_CONFIG_FILE_NAME.length());
            if (tempNo.length() == 5) {
                try {
                    Integer.parseInt(tempNo);
                } catch (NumberFormatException exc) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    
    private org.openide.nodes.Node tryToFindDefaultCommands() {
        if (config == null) return null;
        ProfilesCache cache = new ProfilesCache(CONFIG_ROOT_FO);
        return (org.openide.nodes.Node) cache.getProfileCommands(config);
    }
    
    private void loadCurrentConfig() {
        //System.out.println("loadCurrentConfig() configFileName = "+configFileName);
        org.openide.nodes.Node commands = null;
        if (configFileName != null) {
            org.w3c.dom.Document doc = VariableIO.readPredefinedConfigurations(CONFIG_ROOT_FO, configFileName);
            if (doc == null) return ;
            try {
                commands = (org.openide.nodes.Node) CommandLineVcsAdvancedCustomizer.readConfig (doc);
            } catch (org.w3c.dom.DOMException exc) {
                org.openide.TopManager.getDefault().notifyException(exc);
            }
            if (commands == null) {
                commands = tryToFindDefaultCommands();
            }
            if (commands == null) return ;
            this.setCommands(commands);
        }
    }
    
    private void saveCurrentConfig() {
        configFileName = TEMPORARY_CONFIG_FILE_NAME + cacheId + "." + VariableIO.CONFIG_FILE_EXT;
        FileObject file = CONFIG_ROOT_FO.getFileObject(TEMPORARY_CONFIG_FILE_NAME + cacheId, VariableIO.CONFIG_FILE_EXT);
        if (file == null) {
            try {
                file = CONFIG_ROOT_FO.createData(TEMPORARY_CONFIG_FILE_NAME + cacheId, VariableIO.CONFIG_FILE_EXT);
            } catch (IOException ioexc) {
                TopManager.getDefault().notifyException(ioexc);
                return ;
            }
        }
        org.w3c.dom.Document doc = null;
        /*
        org.openide.loaders.DataObject dobj = null;
        try {
            dobj = org.openide.loaders.DataObject.find(file);
        } catch (org.openide.loaders.DataObjectNotFoundException exc) {
            dobj = null;
        }
        if (dobj != null && dobj instanceof org.openide.loaders.XMLDataObject) {
            doc = ((org.openide.loaders.XMLDataObject) dobj).createDocument();
        }
         */
        doc = org.openide.xml.XMLUtil.createDocument(VariableIO.CONFIG_ROOT_ELEM, null, null, null);
        Vector variables = this.getVariables ();
        //org.openide.nodes.Node commands = this.getCommands();
        //String label = selected;
        if (doc != null) {
            org.openide.filesystems.FileLock lock = null;
            try {
                VariableIO.writeVariables(doc, config, variables);
                CommandLineVcsAdvancedCustomizer.writeConfig(doc, this.getCommands());
                lock = file.lock();
                //VariableIO.writeVariables(doc, label, variables);
                org.openide.xml.XMLUtil.write(doc, file.getOutputStream(lock), null);
                //org.openide.loaders.XMLDataObject.write(doc, new BufferedWriter(new OutputStreamWriter(file.getOutputStream(lock))));
            //} catch (org.w3c.dom.DOMException exc) {
            //    org.openide.TopManager.getDefault().notifyException(exc);
            } catch (java.io.IOException ioexc) {
                org.openide.TopManager.getDefault().notifyException(ioexc);
            } finally {
                if (lock != null) lock.releaseLock();
            }
        }
    }

    /** Get the commands. Overide this method of VcsFileSystem to setup
     * the commands if necessary.
     * @return the root command
     */
    public Node getCommands() {
        Node commandRoot = super.getCommands();
        if (commandRoot == null) {
            loadCurrentConfig();
            setBadgeIconsFromVars();
        }
        return super.getCommands();
    }

    /** Getter for property compatibleOSs.
     * @return Value of property compatibleOSs.
     */
    public java.util.Set getCompatibleOSs() {
        return compatibleOSs;
    }
    
    /** Setter for property compatibleOSs.
     * @param compatibleOSs New value of property compatibleOSs.
     */
    public void setCompatibleOSs(java.util.Set compatibleOSs) {
        this.compatibleOSs = compatibleOSs;
    }
    
    /** Getter for property uncompatibleOSs.
     * @return Value of property uncompatibleOSs.
     */
    public java.util.Set getUncompatibleOSs() {
        return uncompatibleOSs;
    }
    
    /** Setter for property uncompatibleOSs.
     * @param uncompatibleOSs New value of property uncompatibleOSs.
     */
    public void setUncompatibleOSs(java.util.Set uncompatibleOSs) {
        this.uncompatibleOSs = uncompatibleOSs;
    }
    
    private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException, NotActiveException {
        in.defaultReadObject();
        setConfigFO();
        setIgnoreListSupport(new GenericIgnoreListSupport());
        if (!isCreateBackupFilesSet()) setCreateBackupFiles(true);
        if (!isFilterBackupFilesSet()) setFilterBackupFiles(true);
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        /*if (configFileName == null) */saveCurrentConfig();
        out.defaultWriteObject();
    }

    private class DocCleanupRemoveItem implements Serializable {
        
        private String cmdName;
        private int order;
        private String lineBegin;
        
        static final long serialVersionUID =-1259352637936409072L;
        /**
         * Create new cleanup remove item.
         * @param cmdName The name of the command.
         * @param order the order of JTextArea in the VariableInputDialog.
         * @param lineBegin the beginning of lines which will be removed.
         */
        public DocCleanupRemoveItem(String cmdName, int order, String lineBegin) {
            this.cmdName = cmdName;
            this.order = order;
            this.lineBegin = lineBegin;
        }
        
        public String getCmdName() {
            return cmdName;
        }
        
        public int getOrder() {
            return order;
        }
        
        public String getLineBegin() {
            return lineBegin;
        }
    }

    private class GenericIgnoreListSupport extends Object implements VcsFileSystem.IgnoreListSupport {
        
        private ArrayList initialIgnoreList = null;
        
        public ArrayList createInitialIgnoreList() {
            if (initialIgnoreList == null) {
                initialIgnoreList = new ArrayList();
                VcsCommand cmd = getCommand(CMD_CREATE_INITIAL_IGNORE_LIST);
                if (cmd != null) {
                    Table files = new Table();
                    files.put ("", findFileObject(""));
                    VcsCommandExecutor[] executors = VcsAction.doCommand(
                        files, cmd, null, CommandLineVcsFileSystem.this, null, null,
                        new CommandDataOutputListener() {
                            public void outputData(String[] data) {
                                for (int i = 0; i < data.length; i++) {
                                    String element = data[i];
                                    if ("!".equals(element)) initialIgnoreList.clear();
                                    else initialIgnoreList.add(element);
                                }
                            }
                        }, null, false);
                    CommandsPool pool = getCommandsPool();
                    for (int i = 0; i < executors.length; i++) {
                        pool.waitToFinish (executors[i]);
                    }
                }
            }
            return initialIgnoreList;
        }
        
        public ArrayList createIgnoreList(String fileName, ArrayList parentIgnoreList) {
            VcsCommand cmd = getCommand(CMD_CREATE_FOLDER_IGNORE_LIST);
            if (cmd == null) {
                return parentIgnoreList;
            }
            String[] parentIgnoreListItems = (String[]) parentIgnoreList.toArray(new String[0]);
            Hashtable additionalVars = new Hashtable();
            additionalVars.put(VAR_PARENT_IGNORE_LIST, VcsUtilities.arrayToQuotedStrings(parentIgnoreListItems));
            final ArrayList ignoreList = new ArrayList();
            Table files = new Table();
            files.put(fileName, findResource(fileName));
            VcsCommandExecutor[] executors = VcsAction.doCommand(
                files, cmd, additionalVars, CommandLineVcsFileSystem.this, null, null,
                new CommandDataOutputListener() {
                    public void outputData(String[] data) {
                        for (int i = 0; i < data.length; i++) {
                            String element = data[i];
                            if ("!".equals(element)) ignoreList.clear();
                            else ignoreList.add(element);
                        }
                    }
                }, null, false);
            CommandsPool pool = getCommandsPool();
            for (int i = 0; i < executors.length; i++) {
                pool.waitToFinish(executors[i]);
            }
            return ignoreList;
        }
        
    }

    private String clg(String s) {
        //D.deb("getting "+s);
        if (resourceBundle == null) {
            synchronized (this) {
                if (resourceBundle == null) {
                    resourceBundle = NbBundle.getBundle(CommandLineVcsFileSystem.class);
                }
            }
        }
        return resourceBundle.getString (s);
    }
    
    private String clg(String s, Object obj) {
        return MessageFormat.format (clg(s), new Object[] { obj });
    }
    
}

