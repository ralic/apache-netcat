/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.
 
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
package org.netbeans.modules.clearcase;

import org.netbeans.modules.clearcase.client.status.FileStatus;
import org.netbeans.modules.clearcase.client.status.ListCheckouts.LSCOOutput;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.FileUtil;

import java.io.File;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level; 
import org.netbeans.api.queries.SharabilityQuery;
import org.netbeans.modules.clearcase.client.status.ListCheckouts;
import org.netbeans.modules.clearcase.client.status.ListFiles;
import org.netbeans.modules.clearcase.util.Utils;
import org.netbeans.modules.versioning.spi.VCSContext;
import org.netbeans.modules.versioning.spi.VersioningSupport;
import org.netbeans.modules.versioning.util.ListenersSupport;
import org.netbeans.modules.versioning.util.VersioningListener;

/**
 * Central part of status management, deduces and caches statuses of files under version control.
 *
 * @author Maros Sandor
 */
public class FileStatusCache {
    
    /**
     * Indicates that status of a file changed and listeners SHOULD check new status
     * values if they are interested in this file.
     * The New value is a ChangedEvent object (old FileInformation object may be null)
     */
    public static final String EVENT_FILE_STATUS_CHANGED = "status.changed";
    
    /**
     * A special map saying that no file inside the folder is managed.
     */
    private static final Map<File, FileInformation> NOT_MANAGED_MAP = new NotManagedMap();
    
    public static final FileStatus REPOSITORY_STATUS_UNKNOWN  = null;
    public static final FileStatus REPOSITORY_STATUS_FILE_ADDED = null;
    
    // Constant FileInformation objects that can be safely reused
    // Files that have a revision number cannot share FileInformation objects
    private static final FileInformation FILE_INFORMATION_IGNORED = new FileInformation(FileInformation.STATUS_NOTVERSIONED_IGNORED, false);
    private static final FileInformation FILE_INFORMATION_EXCLUDED_DIRECTORY = new FileInformation(FileInformation.STATUS_NOTVERSIONED_IGNORED, true);    
    private static final FileInformation FILE_INFORMATION_NOTMANAGED = new FileInformation(FileInformation.STATUS_NOTVERSIONED_NOTMANAGED, false);
    private static final FileInformation FILE_INFORMATION_NOTMANAGED_DIRECTORY = new FileInformation(FileInformation.STATUS_NOTVERSIONED_NOTMANAGED, true);
    private static final FileInformation FILE_INFORMATION_UNKNOWN = new FileInformation(FileInformation.STATUS_UNKNOWN, false);
    
    private ListenersSupport listenerSupport = new ListenersSupport(this);

    private Map<File, Map<File, FileInformation>> statusMap = new HashMap<File, Map<File, FileInformation>>();
    
    private Clearcase clearcase;
    
    private Set<FileSystem> filesystemsToRefresh;

    FileStatusCache() {
        this.clearcase = Clearcase.getInstance();        
    }
    
    // --- Public interface -------------------------------------------------
    
//    /**
//     * Lists <b>modified files</b> and all folders that are known to be inside
//     * this folder. There are locally modified files present
//     * plus any files that exist in the folder in the remote repository.
//     *
//     * @param dir folder to list
//     * @return
//     */
//    public File [] listFiles(File dir) {
//        Set<File> files = getScannedFiles(dir).keySet();
//        return files.toArray(new File[files.size()]);
//    }
    
    /**
     * Lists <b>interesting files</b> that are known to be inside given folders.
     * Only locally and remotely modified and ignored files are returned
     *
     * <p>This method returns both folders and files.
     *
     * @param context context to examine
     * @param includeStatus limit returned files to those having one of supplied statuses
     * @return File [] array of interesting files
     */
    // XXX context vs VCSContext
    public File [] listFiles(VCSContext context, int includeStatus) {
        Set<File> set = new HashSet<File>();        
        
        // XXX this is crap. chcek for files from context
        for(Entry<File, Map<File, FileInformation>> entry : statusMap.entrySet()) {
            
            Map<File, FileInformation> map = entry.getValue();    
            for (Iterator i = map.keySet().iterator(); i.hasNext();) {                
                File file = (File) i.next();                                   
                FileInformation info = (FileInformation) map.get(file);
                if ((info.getStatus() & includeStatus) == 0) {                    
                    continue;
                }
                                                    
                for (File root : context.getRootFiles()) {
                    if (VersioningSupport.isFlat(root)) {
                        if (file.equals(root) || file.getParentFile().equals(root)) {
                            set.add(file);
                            break;
                        }
                    } else {
                        if (Utils.isParentOrEqual(root, file)) {
                            set.add(file);
                            break;
                        }   
                    }
                }
            }
        }
        if (context.getExclusions().size() > 0) {
            for (Iterator i = context.getExclusions().iterator(); i.hasNext();) {
                File excluded = (File) i.next();
                for (Iterator j = set.iterator(); j.hasNext();) {
                    File file = (File) j.next();
                    if (Utils.isParentOrEqual(excluded, file)) {
                        j.remove();
                    }
                }
            }
        }
        return set.toArray(new File[set.size()]);
    }

    
//    /**
//     * Lists <b>interesting files</b> that are known to be inside given folders.
//     * Only locally and remotely modified and ignored files are returned
//     *
//     * <p>Comapring to CVS this method returns both folders and files.
//     *
//     * @param roots context to examine
//     * @param includeStatus limit returned files to those having one of supplied statuses
//     * @return File [] array of interesting files
//     */
//    public File [] listFiles(File[] roots, int includeStatus) {
//        Set<File> set = new HashSet<File>();
//        Map allFiles = cacheProvider.getAllModifiedValues();
//        for (Iterator i = allFiles.keySet().iterator(); i.hasNext();) {
//            File file = (File) i.next();
//            FileInformation info = (FileInformation) allFiles.get(file);
//            if ((info.getStatus() & includeStatus) == 0) continue;
//            for (int j = 0; j < roots.length; j++) {
//                File root = roots[j];
//                if (VersioningSupport.isFlat(root)) {
//                    if (file.getParentFile().equals(root)) {
//                        set.add(file);
//                        break;
//                    }
//                } else {
//                    if (Utils.isAncestorOrEqual(root, file)) {
//                        set.add(file);
//                        break;
//                    }
//                }
//            }
//        }
//        return set.toArray(new File[set.size()]);
//    }        
    
    
    
    /**
     * Returns the versionig status for a file as long it is already stored in the cache. Otherwise it returns
     * {@link #FILE_INFORMATION_UNKNOWN} and asynchronously refreshes the status for the given file. Status will 
     * fired so all registered listenes will be notified.
     * 
     * @param file file to get status for
     * @return FileInformation structure containing the file status or null if there is no staus known yet
     * @see FileInformation 
     */
    public FileInformation getCachedInfo(final File file) {
        File dir = file.getParentFile();
        if (dir == null) {
            return FileStatusCache.FILE_INFORMATION_NOTMANAGED; // default for filesystem roots
        }
                
        Map<File, FileInformation> dirMap = statusMap.get(dir); // XXX synchronize this
        FileInformation info = dirMap != null ? dirMap.get(file) : null;
        if(info == null) {
            // nothing known about the file yet, 
            // return unknown info for now and 
            // refresh with forced event firing
            Clearcase.getInstance().getRequestProcessor().post(new Runnable() {
                // XXX 1. do not use the Clearcase request processor 
                //     2. schedule later and group files
                public void run() {
                    refresh(file, true);
                }
            });                
            return FILE_INFORMATION_UNKNOWN;     
        } else {
            return info;
        }        
    }
    
    /**
     * Determines the versioning status information for a file. 
     * This method synchronously accesses disk and may block for a long period of time.
     *
     * @param file file to get the FileeInformation for
     * @return FileInformation structure containing the file status
     * @see FileInformation
     */
    public FileInformation getInfo(File file) {      // XXX this sucks! - getFileInformation() !!!!                      
        FileInformation fi = getCachedInfo(file);
        if(fi == null) {
            fi = refresh(file, true); // XXX force event
        }
        if (fi == null) return FILE_INFORMATION_UNKNOWN;    // TODO: HOTFIX
        return fi;
               
//
//        try {
//            List<FileStatus> fs = cmd.getStatus(file, false);    
//        } catch (ClearcaseException ex) {
//            Exceptions.printStackTrace(ex);
//        }
          //  return new FileInformation(FileInformation.STATUS_UNKNOWN, file.isDirectory());         
// XXX HACK
//        if (files == FileStatusCache.NOT_MANAGED_MAP) return FileStatusCache.FILE_INFORMATION_NOTMANAGED;
//        FileInformation fi = (FileInformation) files.get(file);
//        if (fi != null) {
//            return fi;
//        }
//        if (!exists(file)) return FileStatusCache.FILE_INFORMATION_UNKNOWN;
//        if (file.isDirectory()) {
//            return refresh(file, FileStatus.RepositoryStatus.REPOSITORY_STATUS_UNKNOWN);
//        } else {
//            return new FileInformation(FileInformation.STATUS_VERSIONED_UPTODATE, false);
//        }

//        Map files = getScannedFiles(dir);
//        if (files == FileStatusCache.NOT_MANAGED_MAP) return FileStatusCache.FILE_INFORMATION_NOTMANAGED;
//        FileInformation fi = (FileInformation) files.get(file);
//        if (fi != null) {
//            return fi;
//        }
//        if (!exists(file)) return FileStatusCache.FILE_INFORMATION_UNKNOWN;
//        if (file.isDirectory()) {
//            return refresh(file, FileStatus.RepositoryStatus.REPOSITORY_STATUS_UNKNOWN);
//        } else {
//            return new FileInformation(FileInformation.STATUS_VERSIONED_UPTODATE, false);
//        }

    }
    
    /**
     * Refreshes recursively all files in the given context.
     * @param ctx the context to be refreshed
     */
    public void refreshRecursively(VCSContext ctx) {
        Set<File> dirsToRefresh = new HashSet<File>();
        for(File file : ctx.getRootFiles()) {
            if(file.isFile()) {
                File parent = file.getAbsoluteFile(); // XXX parent could be an unversioned directory. Be sure we can deal with it.
                if(parent != null) {
                    dirsToRefresh.add(parent);
                }                
            } else {
                dirsToRefresh.add(file);
            }
        }        
        
        for(File file : dirsToRefresh) {
            refreshRecursively(file);
        }
    }
    
    /**
     * Refreshes recursively all files in the given directory.
     * @param dir
     */
    private void refreshRecursively(File dir) {        
        File[] files = dir.listFiles();
        if(files == null || files.length == 0) {
            return;
        }
        boolean kidsRefreshed = false; // XXX HACK
        for(File file : files) {
            if(file.isDirectory()) {
                refresh(file, true);
                refreshRecursively(file);
                kidsRefreshed = true;
            } else if(!kidsRefreshed) {
                refresh(file, true);
            }
        }
    }
    
    /**
     * Cache refresh as Interceptor route the changes
     * originated from file system. Only managed directory
     * will be refreshed
     *
     */
    // XXX refresh all files from the files parent, or only the file
    public FileInformation refresh(File file, boolean forceChangeEvent) { 
        
        forceChangeEvent = true; /// XXX FIX ME
        // check if it is a managed directory structure
        File dir = file.getParentFile();
        if (dir == null) {
            return FileStatusCache.FILE_INFORMATION_NOTMANAGED; // default for filesystem roots
        }
        
        if(!Clearcase.getInstance().isManaged(dir)) {            
            if(!Clearcase.getInstance().isManaged(file)) {
                // TODO what about children if dir?
                return file.isDirectory() ? FILE_INFORMATION_NOTMANAGED_DIRECTORY : FILE_INFORMATION_NOTMANAGED;
            }                          
            // file seems to be the vob root
            dir = file;            
        }               
        boolean isRoot = dir.equals(file);
        
        //File mapKey;
        
        // 1. list files ...
        ListFiles listedStatusUnit = listedStatusUnit = new ListFiles(new ListFiles.ListCommand(dir, !isRoot));
//        if(file.isFile()) {
//            listedStatusUnit = new ListFiles(new ListFiles.ListCommand(dir, isRoot));
//          //  mapKey = dir;
//        } else {
//            listedStatusUnit = new ListFiles(new ListFiles.ListCommand(file, true), new ListFiles.ListCommand(file, false));            
//            //mapKey = file; 
//        }        
        try {
            Clearcase.getInstance().getClient().exec(listedStatusUnit);
        } catch (ClearcaseException ex) {
            Clearcase.LOG.log(Level.SEVERE, "Exception in status command ", ex);
            return null; // XXX or maybe this? new FileInformation(FileInformation.STATUS_UNKNOWN, null, false);
        }
        
        // 2. ... go throught the ct ls output ...
        List<ListFiles.ListOutput> listOutput = listedStatusUnit.getOutputList();
        List<FileStatus> statusValues = new ArrayList<FileStatus>(listOutput.size());
        Map<File, ListFiles.ListOutput> checkedout = new HashMap<File, ListFiles.ListOutput>();
        for(ListFiles.ListOutput o : listOutput) {        
            if(o.getVersion() != null && o.getVersion().isCheckedout()) {
                checkedout.put(o.getFile(), o);
            } else {
                statusValues.add(new FileStatus(o.getType(), o.getFile(), o.getOriginVersion(), o.getVersion(), o.getAnnotation(), false));   
            }
        }        
        
        if(checkedout.size() > 0) {            
            ListCheckouts lsco = new ListCheckouts(dir, !isRoot);   // TODO !!!            
            
            try {
                Clearcase.getInstance().getClient().exec(lsco);    
            } catch (ClearcaseException ex) {
                Clearcase.LOG.log(Level.SEVERE, "Exception in status command ", ex);
                return null; // XXX or maybe this? new FileInformation(FileInformation.STATUS_UNKNOWN, null, false); 
            }
            
            List<LSCOOutput> checkouts = lsco.getOutputList();
            for(LSCOOutput c : checkouts) {        
                ListFiles.ListOutput o = checkedout.get(c.getFile());
                // if(o != null) {
                    statusValues.add(new FileStatus(o.getType(), o.getFile(), o.getOriginVersion(), o.getVersion(), o.getAnnotation(), c.isReserved()));               
                //}
            }                
        }
        Map<File, FileInformation> oldDirMap = statusMap.get(dir); // XXX synchronize this!
        Map<File, FileInformation> newDirMap = new HashMap<File, FileInformation>();
        
        //FileInformation current = oldDirMap != null ? oldDirMap.get(file) : null;        
        
        for(FileStatus fs : statusValues) {            
            // FileInformation fiCurrent = oldDirMap != null ? oldDirMap.get(fs.getFile()) : null;
            FileInformation fiNew = createFileInformation(fs, null); // XXX null for repository status!                            
            try {
                newDirMap.put(fs.getFile().getCanonicalFile(), fiNew);            
            } catch (IOException ioe) {
                Clearcase.LOG.log(Level.SEVERE, ioe.getMessage(), ioe);
            }
//            if (!equivalent(fiNew, fiCurrent)) {
//                //if (forceChangeEvent) fireFileStatusChanged(file, current, fi); // XXX do we need this?       
//            }            
        }       
        statusMap.put(dir, newDirMap);
        FileInformation fi = null;
        FileInformation oldFi = null;
        try {        
            fi = newDirMap.get(file.getCanonicalFile());
            oldFi = oldDirMap != null ? oldDirMap.get(file.getCanonicalFile()) : null;
        } catch (IOException ioe) {
            Clearcase.LOG.log(Level.SEVERE, ioe.getMessage(), ioe);
        }

        // assert fi != null : "NULL FileInformation for " + file; - is null when triggered by interceptor.delete()
        if(fi == null) {            
            // XXX HACK - may happen if there isn't the relevant entry in the newDirMap - how is that possible?!
            fi = FILE_INFORMATION_UNKNOWN;            
        }
        if(forceChangeEvent) {
            fireFileStatusChanged(file, oldFi, fi); // XXX null => this is  alsways forced
        }
        return fi;
        
        
        //if (files == FileStatusCache.NOT_MANAGED_MAP && repositoryStatus == FileStatus.RepositoryStatus.REPOSITORY_STATUS_UNKNOWN) return FileStatusCache.FILE_INFORMATION_NOTMANAGED;
        
        // current cached file status info
        
//        // repository file status
//        FileInformation fi = null;
//        if(repositoryStatus == FileStatus.RepositoryStatus.REPOSITORY_STATUS_FILE_ADDED){
//            // update file status info as up to date
//            fi = new FileInformation(FileInformation.STATUS_VERSIONED_UPTODATE, file.isDirectory());
//        } else if (repositoryStatus == FileStatus.RepositoryStatus.REPOSITORY_STATUS_FILE_NEW){
//            fi = new FileInformation(FileInformation.STATUS_NOTVERSIONED_NEWLOCALLY, file.isDirectory());
//        } else if (repositoryStatus == FileStatus.RepositoryStatus.REPOSITORY_STATUS_FILE_MODIFIED){
//            fi = new FileInformation(FileInformation.STATUS_VERSIONED_UPTODATE, file.isDirectory());
//        } else if (repositoryStatus == FileStatus.RepositoryStatus.REPOSITORY_STATUS_FILE_LOCAL_MODIFIED) {
//            // since local modified event could be fired by new file creation by interceptor
//            // interceptor does not know if it is from new file or real file modified event
//            // so we test here if it is from new file, do not set to local modified state
//            fi = createFileInformation(file);
//            // if there is not in cache, it is previously up to date file
//            // if it is new file, the change is not the real file modification
//            if(fi == null || fi.getStatus() != FileInformation.STATUS_NOTVERSIONED_NEWLOCALLY)
//                fi = new FileInformation(FileInformation.STATUS_VERSIONED_MODIFIEDLOCALLY, file.isDirectory());
//        } else if(repositoryStatus == FileStatus.RepositoryStatus.REPOSITORY_STATUS_FILE_REMOVED){
//            fi = new FileInformation(FileInformation.STATUS_VERSIONED_REMOVEDINREPOSITORY, file.isDirectory());
//        } else if(repositoryStatus == FileStatus.RepositoryStatus.REPOSITORY_STATUS_FILE_REMOVED_LOCAL){
//            fi = new FileInformation(FileInformation.STATUS_VERSIONED_REMOVEDLOCALLY, file.isDirectory());
//        } else if(repositoryStatus == FileStatus.RepositoryStatus.REPOSITORY_STATUS_FILE_OPEN){
//            fi = new FileInformation(FileInformation.STATUS_VERSIONED_UPTODATE, file.isDirectory());
//        } else {
//            fi = createFileInformation(file);
//        }
//        if (FileStatusCache.equivalent(fi, current)) {
//            if (forceChangeEvent) fireFileStatusChanged(file, current, fi);
//            return fi;
//        }
        
        // do not include uptodate files into cache, missing directories must be included
//        if (current == null && fi != null && !fi.isDirectory() && fi.getStatus() == FileInformation.STATUS_VERSIONED_UPTODATE) {
//            if (forceChangeEvent) fireFileStatusChanged(file, current, fi);
//            return fi;
//        }
        
//        file = FileUtil.normalizeFile(file);
//        dir = FileUtil.normalizeFile(dir);
//        Map<File, FileInformation> newFiles = new HashMap<File, FileInformation>(files);
//        if (fi != null && fi.getStatus() == FileInformation.STATUS_UNKNOWN) {
//            newFiles.remove(file);
//            turbo.writeEntry(file, FILE_STATUS_MAP, null);  // remove mapping in case of directories
//        } else if (fi != null && fi.getStatus() == FileInformation.STATUS_VERSIONED_UPTODATE && file.isFile()) {
//            newFiles.remove(file);
//        } else {
//            newFiles.put(file, fi);
//        }
//        assert newFiles.containsKey(dir) == false;
//        turbo.writeEntry(dir, FILE_STATUS_MAP, newFiles.size() == 0 ? null : newFiles);
//        
//        if (file.isDirectory() && needRecursiveRefresh(fi, current)) {
//            File [] content = listFiles(file);
//            for (int i = 0; i < content.length; i++) {
//                refresh(content[i], FileStatus.RepositoryStatus.REPOSITORY_STATUS_UNKNOWN);
//            }
//        }
//        fireFileStatusChanged(file, current, fi);
//        return fi;
    }
    
    /**
     * Examines a file or folder and computes its status. 
     * 
     * @param status entry for this file or null if the file is unknown to subversion
     * @return FileInformation file/folder status bean
     */ 
    private FileInformation createFileInformation(FileStatus status, FileStatus repositoryStatus) { // XXX get and handle repository status
        // XXX why is this based on the repository status? WTH is the repository for? do we realy need it?
        if(status.getStatus() == FileStatus.ClearcaseStatus.REPOSITORY_STATUS_VIEW_PRIVATE) {
            if(isIgnored(status.getFile())) {
                return FILE_INFORMATION_IGNORED; // XXX what if file does not exists -> isDir = false;   
            } else {
                return new FileInformation(FileInformation.STATUS_NOTVERSIONED_NEWLOCALLY, status, status.getFile().isDirectory()); 
            }            
        } else if(status.getStatus() == FileStatus.ClearcaseStatus.REPOSITORY_STATUS_FILE_CHECKEDOUT_RESERVED) {
            return new FileInformation(FileInformation.STATUS_VERSIONED_CHECKEDOUT_RESERVED, status, status.getFile().isDirectory());
        } else if(status.getStatus() == FileStatus.ClearcaseStatus.REPOSITORY_STATUS_FILE_CHECKEDOUT_UNRESERVED) {
            return new FileInformation(FileInformation.STATUS_VERSIONED_CHECKEDOUT_UNRESERVED, status, status.getFile().isDirectory());            
        } else if(status.getStatus() == FileStatus.ClearcaseStatus.REPOSITORY_STATUS_FILE_CHECKEDOUT_BUT_REMOVED) {
            // XXX we don't know if directory but could be retrieved from ct ls -long or ct describe 
            return new FileInformation(FileInformation.STATUS_VERSIONED_CHECKEDOUT_BUT_REMOVED, status, status.getFile().isDirectory());  
        } else if(status.getStatus() == FileStatus.ClearcaseStatus.REPOSITORY_STATUS_FILE_LOADED_BUT_MISSING) {
            // XXX we don't know if directory but could be retrieved from ct ls -long or ct describe 
            return new FileInformation(FileInformation.STATUS_VERSIONED_LOADED_BUT_MISSING, status, status.getFile().isDirectory());              
        } else if(status.getStatus() == FileStatus.ClearcaseStatus.REPOSITORY_STATUS_FILE_HIJACKED) {
            return new FileInformation(FileInformation.STATUS_VERSIONED_HIJACKED, status, status.getFile().isDirectory());        
        } else if(status.getStatus() == FileStatus.ClearcaseStatus.REPOSITORY_STATUS_FILE_ECLIPSED) {
            return new FileInformation(FileInformation.STATUS_NOTVERSIONED_ECLIPSED, status, status.getFile().isDirectory());
        } else if(status.getVersion() != null) {
            // has predecesor (is versioned) and no other status value known ...
            return new FileInformation(FileInformation.STATUS_VERSIONED_UPTODATE, status, status.getFile().isDirectory());
        }
        return new FileInformation(FileInformation.STATUS_UNKNOWN, status, status.getFile().isDirectory());
        
//        if (/** unversioned */ ) {
//            if (!svn.isManaged(file)) {
//                return file.isDirectory() ? FILE_INFORMATION_NOTMANAGED_DIRECTORY : FILE_INFORMATION_NOTMANAGED;
//            }
//            return createMissingEntryFileInformation(file, repositoryStatus);
//        }                     
    }
    
    /**
     * Non-recursive ignore check.
     *
     * <p>Side effect: if versioned by CC and ignered then also stores the is ignored information
     *
     * @return true if file is listed in parent's ignore list
     * or IDE thinks it should be.
     */
    boolean isIgnored(final File file) {
        String name = file.getName();

        if(ClearcaseModuleConfig.isIgnored(file)) {
            return true;
        }

        if (SharabilityQuery.getSharability(file) == SharabilityQuery.NOT_SHARABLE) {
            // BEWARE: In NetBeans VISIBILTY == SHARABILITY                                 
            ClearcaseModuleConfig.setIgnored(file);
            return true;
        } else {
            // XXX do we still need this hack?!
            // backward compatability #68124
            if (".nbintdb".equals(name)) {  // NOI18N
                return true;
            }

            return false;
        }
    }    
           
    /**
     * Two FileInformation objects are equivalent if their status contants are equal AND they both reperesent a file (or
     * both represent a directory) AND Entries they cache, if they can be compared, are equal.
     *
     * @param other object to compare to
     * @return true if status constants of both object are equal, false otherwise
     */
    private static boolean equivalent(FileInformation main, FileInformation other) {
        if (other == null || main.getStatus() != other.getStatus() || main.isDirectory() != other.isDirectory()) return false;
        
        FileStatus e1 = main.getStatus(null);
        FileStatus e2 = other.getStatus(null);
        return e1 == e2 || e1 == null || e2 == null || e1.equals(e2);
    }
    
//    private boolean needRecursiveRefresh(FileInformation fi, FileInformation current) {
//        if (fi != null && fi.getStatus() == FileInformation.STATUS_NOTVERSIONED_EXCLUDED ||
//                current != null && current.getStatus() == FileInformation.STATUS_NOTVERSIONED_EXCLUDED) return true;
//        if (fi != null && fi.getStatus() == FileInformation.STATUS_NOTVERSIONED_NOTMANAGED ||
//                current != null && current.getStatus() == FileInformation.STATUS_NOTVERSIONED_NOTMANAGED) return true;
//        return false;
//    }
    
//    /**
//     * Refreshes information about a given file or directory ONLY if its status is already cached. The
//     * only exception are non-existing files (new-in-repository) whose statuses are cached in all cases.
//     *
//     * @param file
//     * @param repositoryStatus
//     */
//    public void refreshCached(File file, FileStatus.RepositoryStatus repositoryStatus) {
//        refresh(file, repositoryStatus);
//    }
    
//    /**
//     * Refreshes status of all files inside given context. Files that have some remote status, eg. REMOTELY_ADDED
//     * are brought back to UPTODATE.
//     *
//     * @param ctx context to refresh
//     */
//    public void refreshCached(VCSContext ctx) {
//        
//        File [] files = listFiles(ctx, ~0);
//        
//        for (int i = 0; i < files.length; i++) {
//            File file = files[i];
//            refreshCached(file, FileStatus.RepositoryStatus.REPOSITORY_STATUS_UNKNOWN);
//        }
//    }
    
    // --- Package private contract ------------------------------------------
        
    Map<File, FileInformation> getAllModifiedValues(File root) {  // XXX add recursive flag
        Map<File, FileInformation> ret = new HashMap<File, FileInformation>();
        
        for(File modifiedDir : statusMap.keySet()) {
            if(Utils.isParentOrEqual(root, modifiedDir)) {                
                Map<File, FileInformation> map = statusMap.get(modifiedDir);
                for(File file : map.keySet()) {
                    FileInformation info = map.get(file);

                    if( (info.getStatus() & FileInformation.STATUS_LOCAL_CHANGE) != 0 ) { // XXX anything else
                        ret.put(file, info);
                    }                
                }                
            }            
        }        

        return ret;
    }
    
//    /**
//     * Refreshes given directory and all subdirectories.
//     *
//     * @param dir directory to refresh
//     */
//    void directoryContentChanged(File dir) {
//        Map originalFiles = (Map) turbo.readEntry(dir, FILE_STATUS_MAP);
//        if (originalFiles != null) {
//            for (Iterator i = originalFiles.keySet().iterator(); i.hasNext();) {
//                File file = (File) i.next();
//                refresh(file, FileStatus.RepositoryStatus.REPOSITORY_STATUS_UNKNOWN);
//            }
//        }
//    }
    
//    /**
//     * Cleans up the cache by removing or correcting entries that are no longer valid or correct.
//     */
//    void cleanUp() {
//        Map files = cacheProvider.getAllModifiedValues();
//        for (Iterator i = files.keySet().iterator(); i.hasNext();) {
//            File file = (File) i.next();
//            // in case files only existed in cache but no longer
//            //exist on the file system. It could be the reason
//            //of manually delete files from file system or p4 delete
//            if(!file.exists())continue;
//            FileInformation info = (FileInformation) files.get(file);
//            if ((info.getStatus() & FileInformation.STATUS_LOCAL_CHANGE) != 0) {
//                refresh(file, FileStatus.RepositoryStatus.REPOSITORY_STATUS_UNKNOWN);
//            } else if (info.getStatus() == FileInformation.STATUS_NOTVERSIONED_EXCLUDED) {
//                // remove entries that were excluded but no longer exist
//                // cannot simply call refresh on excluded files because of 'excluded on server' status
//                if (!exists(file)) {
//                    refresh(file, FileStatus.RepositoryStatus.REPOSITORY_STATUS_UNKNOWN);
//                }
//            }
//        }
//    }
    
//    /**
//     * read files from cache entries
//     *
//     */
//    private Map<File, FileInformation> getScannedFiles(File dir) {
//        Map<File, FileInformation> files;
////        //Reads given attribute "FILE_STATUS_MAP" for given fileobject "dir"
////        files = (Map<File, FileInformation>) turbo.readEntry(dir, FILE_STATUS_MAP);
////        if (files != null && !files.isEmpty()) return files;
////        // check if it is a managed file structure
////        if (isNotManagedByDefault(dir)) {
////            return FileStatusCache.NOT_MANAGED_MAP;
////        }
////        
////        // scan and populate cache with results
////        
////        dir = FileUtil.normalizeFile(dir);
////        files = scanFolder(dir);
////        assert files.containsKey(dir) == false;
////        turbo.writeEntry(dir, FILE_STATUS_MAP, files);
////        for (Iterator i = files.keySet().iterator(); i.hasNext();) {
////            File file = (File) i.next();
////            FileInformation info = files.get(file);
////            if(info == null)continue;
////            if ((info.getStatus() & FileInformation.STATUS_LOCAL_CHANGE) != 0) {
////                fireFileStatusChanged(file, null, info);
////            }
////        }
//        return files;
//    }
    
//    private boolean isNotManagedByDefault(File dir) {
//        return !dir.exists();
//    }
    
//    /**
//     * Scans all files in the given folder, computes and stores their VCS status.
//     *
//     * @param dir directory to scan
//     * @return Map map to be included in the status cache (File => FileInformation)
//     */
//    private Map<File, FileInformation> scanFolder(File dir) {
//        File [] files = dir.listFiles();
//        Map<File, FileInformation> folderFiles = new HashMap<File, FileInformation>(files.length);
//        //cache only for local new or modified files
//        Map allFiles = cacheProvider.getAllModifiedValues();
//        String cachedFilePath = null;
//        String dirPath = dir.getAbsolutePath();
//        FileInformation info = null;
//        File cachedFile = null;
//        File localFile = null;
//        // compute the file status for all files under the given folder
//        for(int j = 0; j < files.length; j++){
//            // walk through flatten down cache
//            localFile = files[j];
//            for (Iterator i = allFiles.keySet().iterator(); i.hasNext();) {
//                cachedFile = (File) i.next();
//                cachedFilePath = cachedFile.getAbsolutePath();
//                // if cached file is under targeted dir
//                if(cachedFilePath.indexOf(dirPath) != -1){
//                    info = (FileInformation) allFiles.get(localFile);
//                    folderFiles.put(localFile, info);
//                }
//            }
//        }
//        return folderFiles;
//    }
    
    private boolean exists(File file) {
        if (!file.exists()) return false;
        return file.getAbsolutePath().equals(FileUtil.normalizeFile(file).getAbsolutePath());
    }
            
    public void refreshDirtyFileSystems() {
        Set<FileSystem> filesystems = getFilesystemsToRefresh();
        FileSystem[]  filesystemsToRefresh = new FileSystem[filesystems.size()];
        synchronized (filesystems) {
            filesystemsToRefresh = filesystems.toArray(new FileSystem[filesystems.size()]);
            filesystems.clear();
        }
        for (int i = 0; i < filesystemsToRefresh.length; i++) {
            // don't call refresh() in synchronized (filesystems). It may lead to a deadlock.
            filesystemsToRefresh[i].refresh(true);
        }
    }
    
    private Set<FileSystem> getFilesystemsToRefresh() {
        if(filesystemsToRefresh == null) {
            filesystemsToRefresh = new HashSet<FileSystem>();
        }
        return filesystemsToRefresh;
    }
    
    private static final class NotManagedMap extends AbstractMap<File, FileInformation> {
        public Set<Entry<File, FileInformation>> entrySet() {
            return Collections.emptySet();
        }
    }
    
    public static class ChangedEvent {
        
        private File file;
        private FileInformation oldInfo;
        private FileInformation newInfo;
        
        public ChangedEvent(File file, FileInformation oldInfo, FileInformation newInfo) {
            this.file = file;
            this.oldInfo = oldInfo;
            this.newInfo = newInfo;
        }
        
        public File getFile() {
            return file;
        }
        
        public FileInformation getOldInfo() {
            return oldInfo;
        }
        
        public FileInformation getNewInfo() {
            return newInfo;
        }
    }
    
    public void addVersioningListener(VersioningListener listener) {
        listenerSupport.addListener(listener);
    }

    public void removeVersioningListener(VersioningListener listener) {
        listenerSupport.removeListener(listener);
    }
    
    private void fireFileStatusChanged(File file, FileInformation oldInfo, FileInformation newInfo) {
        if( oldInfo == null && newInfo == null || 
            ( oldInfo != null && newInfo != null && oldInfo.getStatus() == newInfo.getStatus() ) ) 
        {
            return;
        }                      
        listenerSupport.fireVersioningEvent(EVENT_FILE_STATUS_CHANGED, new Object [] { file, oldInfo, newInfo });                
    }    
}
