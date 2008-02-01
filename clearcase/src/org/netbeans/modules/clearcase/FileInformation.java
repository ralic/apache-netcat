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
package org.netbeans.modules.clearcase;

import org.netbeans.modules.clearcase.client.status.FileStatus;
import org.openide.util.NbBundle;

import java.io.Serializable;
import java.io.File;
import java.util.*;

/**
 * Immutable class encapsulating status of a file.
 *
 * @author Maros Sandor
 */
public class FileInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * There is nothing known about the file, it may not even exist.
     */ 
    public static final int STATUS_UNKNOWN                              = 0;

    /**
     * The file is not managed by the module, i.e. the user does not wish it to be under control of this
     * versioning system module. All files except files under versioned roots have this status.
     */ 
    public static final int STATUS_NOTVERSIONED_NOTMANAGED              = 1;    

    /**
     * The file exists locally but is NOT under version control because it should not be (i.e. is has
     * the Ignore property set or resides under an excluded folder). The file itself IS under a versioned root.
     */ 
    public static final int STATUS_NOTVERSIONED_NEWLOCALLY              = 2;
    
    /**
     * The file exists locally but is NOT under version control because it should not be (i.e. is has
     * the Ignore property set or resides under an excluded folder). The file itself IS under a versioned root.
     */ 
    public static final int STATUS_NOTVERSIONED_IGNORED                = 4;
        
    /**
     * The file is under version control and is in sync with repository.
     */ 
    public static final int STATUS_VERSIONED_UPTODATE                   = 8;

    /**
     * The file is checkedout
     */ 
    public static final int STATUS_VERSIONED_CHECKEDOUT_RESERVED        = 16;

    /**
     * The file is checkedout and modified
     */ 
    public static final int STATUS_VERSIONED_CHECKEDOUT_UNRESERVED      = 32;

    /**
     * The file is checkedout but was localy removed without the cleartool rm command
     */ 
    public static final int STATUS_VERSIONED_CHECKEDOUT_BUT_REMOVED      = 64;

    /**
     * The file is loaded in a snapshot but was localy removed without the cleartool rm command
     */ 
    public static final int STATUS_VERSIONED_LOADED_BUT_MISSING         = 128;
    
    /**
     * The file is hijacked
     */ 
    public static final int STATUS_VERSIONED_HIJACKED                   = 256;           

    /**
     * The file is view private and eclipsed
     */ 
    public static final int STATUS_NOTVERSIONED_ECLIPSED                = 512;  
    
    public static final int STATUS_ALL = ~0;

    /**
     * All statuses except <tt>STATUS_NOTVERSIONED_NOTMANAGED</tt>
     *
     * <p>Note: it covers ignored files.
     */
    public static final int STATUS_MANAGED = FileInformation.STATUS_ALL & ~FileInformation.STATUS_NOTVERSIONED_NOTMANAGED;


    /** Is versioned by CC */
    public static final int STATUS_VERSIONED = 
            FileInformation.STATUS_VERSIONED_UPTODATE |
            FileInformation.STATUS_VERSIONED_CHECKEDOUT_RESERVED |
            FileInformation.STATUS_VERSIONED_CHECKEDOUT_UNRESERVED |            
            FileInformation.STATUS_VERSIONED_CHECKEDOUT_BUT_REMOVED |            
            FileInformation.STATUS_VERSIONED_LOADED_BUT_MISSING |            
            FileInformation.STATUS_VERSIONED_HIJACKED;

    public static final int STATUS_LOCAL_CHANGE =
            FileInformation.STATUS_NOTVERSIONED_NEWLOCALLY | 
            FileInformation.STATUS_VERSIONED_CHECKEDOUT_RESERVED |
            FileInformation.STATUS_VERSIONED_CHECKEDOUT_UNRESERVED |
            FileInformation.STATUS_VERSIONED_CHECKEDOUT_BUT_REMOVED |            
            FileInformation.STATUS_VERSIONED_LOADED_BUT_MISSING |            
            FileInformation.STATUS_VERSIONED_HIJACKED |
            FileInformation.STATUS_NOTVERSIONED_ECLIPSED;
    
    public static final int STATUS_DIFFABLE = 
            FileInformation.STATUS_VERSIONED_CHECKEDOUT_RESERVED |
            FileInformation.STATUS_VERSIONED_CHECKEDOUT_UNRESERVED |
            FileInformation.STATUS_VERSIONED_CHECKEDOUT_BUT_REMOVED |            
            FileInformation.STATUS_VERSIONED_LOADED_BUT_MISSING |
            FileInformation.STATUS_VERSIONED_HIJACKED | 
            FileInformation.STATUS_NOTVERSIONED_ECLIPSED;
        
    /**
     * 
     * Status constant.
     */ 
    private final int   status;

    /**
     * More detailed information about a file, you may disregard the field if not needed.
     */
    private transient FileStatus fileStatus;

    /**
     * Directory indicator, mainly because of files that may have been deleted so file.isDirectory() won't work.
     */ 
    private final boolean   isDirectory;
    
    /**
     * For deserialization purposes only.
     */ 
    public FileInformation() {
        status = 0;
        isDirectory = false;
    }

    public FileInformation(int status, FileStatus fileStatus, boolean isDirectory) {
        this.status = status;
        this.fileStatus = fileStatus;
        this.isDirectory = isDirectory;
    }

    FileInformation(int status, boolean isDirectory) {
        this(status, null, isDirectory);
    }
    
    /**
     * Retrieves the status constant representing status of the file.
     * 
     * @return one of status constants
     */ 
    public int getStatus() {
        return status;
    }

    public boolean isDirectory() {
        return isDirectory;
    }
    
    /**
     * Retrieves file's Status.
     *
     * @param file file this information belongs to or null if you do not want the entry to be read from disk 
     * in case it is not loaded yet
     * @return Status parsed output from 'cleartool ls, diff, ...'
     * is not versioned or its entry is invalid
     */
    // XXX why do we need the file?
    public FileStatus getStatus(File file) {
        if (fileStatus == null && file != null) {
            readEntry(file);
        }
        return fileStatus;
    }
    
    private void readEntry(File file) {
        fileStatus = null;       // TODO: read your detailed information about the file here, or disregard the entry field
    }    

    /**
     * Returns localized text representation of status.
     * 
     * @return status name, for multistatuses prefers local
     * status name.
     */ 
    public String getStatusText() {
        return getStatusText(~0);
    }    

    /**
     * Returns localized text representation of status.
     *
     * @param displayStatuses statuses bitmask
     *
     * @return status name, for multistatuses prefers local
     * status name, for masked <tt>""</tt>.
     */
    public String getStatusText(int displayStatuses) {
        // XXX consolidate
        int status = this.status & displayStatuses;
        ResourceBundle loc = NbBundle.getBundle(FileInformation.class);
        if (status == FileInformation.STATUS_UNKNOWN) {
            return loc.getString("CTL_FileInfoStatus_Unknown");            
        } else if (FileInformation.match(status, FileInformation.STATUS_NOTVERSIONED_IGNORED)) {
            return loc.getString("CTL_FileInfoStatus_Ignored");
        } else if (FileInformation.match(status, FileInformation.STATUS_NOTVERSIONED_NEWLOCALLY)) {
            return loc.getString("CTL_FileInfoStatus_NewLocally");
        } else if (FileInformation.match(status, FileInformation.STATUS_VERSIONED_CHECKEDOUT_RESERVED)) {
            return loc.getString("CTL_FileInfoStatus_Checkedout_Reserved");            
        } else if (FileInformation.match(status, FileInformation.STATUS_VERSIONED_CHECKEDOUT_UNRESERVED)) {
            return loc.getString("CTL_FileInfoStatus_Checkedout_Unreserved");                        
        } else if (FileInformation.match(status, FileInformation.STATUS_VERSIONED_CHECKEDOUT_BUT_REMOVED)) {
            return loc.getString("CTL_FileInfoStatus_Checkedout_But_Removed");                                    
        } else if (FileInformation.match(status, FileInformation.STATUS_VERSIONED_LOADED_BUT_MISSING)) {
            return loc.getString("CTL_FileInfoStatus_Loaded_But_Missing");                                    
        } else if (FileInformation.match(status, FileInformation.STATUS_VERSIONED_HIJACKED)) {
            return loc.getString("CTL_FileInfoStatus_Hijacked");                        
        } else if (FileInformation.match(status, FileInformation.STATUS_NOTVERSIONED_ECLIPSED)) {
            return loc.getString("CTL_FileInfoStatus_Eclipsed");                                    
        } else {
            return "";   // NOI18N                     
        }
    }    

    /**
     * @return short status name for local changes, for remote
     * changes returns <tt>""</tt>
     */
    public String getShortStatusText() {
        // XXX consolidate        
        ResourceBundle loc = NbBundle.getBundle(FileInformation.class);
        if (status == FileInformation.STATUS_UNKNOWN) {
            return loc.getString("CTL_FileInfoStatus_Unknown");            
        } else if (FileInformation.match(status, FileInformation.STATUS_NOTVERSIONED_IGNORED)) {
            return loc.getString("CTL_FileInfoStatus_Ignored");
        } else if (FileInformation.match(status, FileInformation.STATUS_NOTVERSIONED_NEWLOCALLY)) {
            return loc.getString("CTL_FileInfoStatus_NewLocally");
        } else if (FileInformation.match(status, FileInformation.STATUS_VERSIONED_CHECKEDOUT_RESERVED)) {
            return loc.getString("CTL_FileInfoStatus_Checkedout_Reserved");            
        } else if (FileInformation.match(status, FileInformation.STATUS_VERSIONED_CHECKEDOUT_UNRESERVED)) {
            return loc.getString("CTL_FileInfoStatus_Checkedout_Unreserved");                        
        } else if (FileInformation.match(status, FileInformation.STATUS_VERSIONED_HIJACKED)) {
            return loc.getString("CTL_FileInfoStatus_Hijacked");                        
        } else if (FileInformation.match(status, FileInformation.STATUS_NOTVERSIONED_ECLIPSED)) {
            return loc.getString("CTL_FileInfoStatus_Eclipsed");                                    
        } else {
            return "";   // NOI18N                     
        }
    }
    
    private static boolean match(int status, int mask) {
        return (status & mask) != 0;
    }

    @Override
    public String toString() {
        return "Text: " + status + " " + getStatusText(status); // NOI18N
    }
}