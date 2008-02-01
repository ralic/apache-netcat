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

import org.netbeans.modules.versioning.spi.VCSInterceptor;
import org.netbeans.modules.clearcase.ui.checkout.CheckoutAction;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import org.netbeans.modules.clearcase.client.ClearcaseClient;
import org.netbeans.modules.clearcase.client.DeleteCommand;
import org.netbeans.modules.clearcase.client.ExecutionUnit;
import org.netbeans.modules.clearcase.client.MoveCommand;
import org.netbeans.modules.clearcase.client.status.FileStatus;
import org.netbeans.modules.clearcase.client.status.ListFiles;

/**
 * Listens on file system changes and reacts appropriately, mainly refreshing affected files' status.
 * 
 * @author Maros Sandor
 */
public class ClearcaseInterceptor extends VCSInterceptor {

    private final FileStatusCache   cache;

    public ClearcaseInterceptor() {
        cache = Clearcase.getInstance().getFileStatusCache();
    }

    @Override
    public boolean beforeDelete(File file) {                
        Clearcase.LOG.finer("beforeDelete " + file);        
                
        // let the IDE take care for deletes of unversioned files        
        return cache.getClearcaseStatus(file) != FileStatus.ClearcaseStatus.REPOSITORY_STATUS_VIEW_PRIVATE;            
    }

    @Override
    public void doDelete(final File file) throws IOException {
        Clearcase.LOG.finer("doDelete " + file);        
        Clearcase.getInstance().getRequestProcessor().post(new Runnable() {
            public void run() {                         
                fileDeletedImpl(file);
            }
        });        
    }

    @Override
    public void afterDelete(final File file) {
        Clearcase.LOG.finer("afterDelete " + file);             
    }

    private void checkout(File parent) {
        // check if not already checkedout
        ListFiles listedStatus = new ListFiles(parent, false);
        try {
            Clearcase.getInstance().getClient().exec(listedStatus);
        } catch (ClearcaseException ex) {
            Clearcase.LOG.log(Level.SEVERE, "Exception in status command ", ex);
        }
        List<ListFiles.ListOutput> outputList = listedStatus.getOutputList();
        if (outputList.size() == 1) {
            ListFiles.ListOutput output = outputList.get(0);
            if (!output.getVersion().isCheckedout()) {
                CheckoutAction.checkout(parent);
            }
        } else {
            // something went wrong
            // TOTO handle it!
        }
    }
    
    private void fileDeletedImpl(File file) {
        // TODO clean up
        
        File parent = file.getParentFile();
        if(parent == null) {
            // how is this possible ?
            return;
        }
                
        // XXX use execution unit
        if(Clearcase.getInstance().isManaged(parent)) {
            // 1. checkout parent if needed
            checkout(parent);
             
            // 2. remove the file
            ClearcaseClient.CommandRunnable cr = Clearcase.getInstance().getClient().post(new ExecutionUnit(
                "Deleting ...", new DeleteCommand(new File [] { file })));
            cr.waitFinished();
            
            // the file stays on the filessytem if it was checkedout eventually
            file.delete();
            
            cache.refreshLater(file); 
        } else {
            // XXX what if VOB root ???
        }                                 
    }    

    @Override
    public boolean beforeMove(File from, File to) {
        Clearcase.LOG.finer("beforeMove " + from + " " + to);        
        
        // let the IDE take care for move of unversioned files        
        return cache.getClearcaseStatus(from) != FileStatus.ClearcaseStatus.REPOSITORY_STATUS_VIEW_PRIVATE;
    }

    @Override
    public void doMove(File from, File to) throws IOException {
        Clearcase.LOG.finer("doMove " + from + " " + to);
        fileMovedImpl(from, to);                
    }

    @Override
    public void afterMove(final File from, final File to) {
        Clearcase.LOG.finer("afterMove " + from + " " + to);
        cache.refreshLater(from);
        cache.refreshLater(to);
    }

    private void fileMovedImpl(File from, File to) {
        // TODO clean up
        
        File fromParent = from.getParentFile();
        File toParent = to.getParentFile();
        if(fromParent == null || toParent == null) {
            // how is this possible ?
            return;
        }
                
        if(Clearcase.getInstance().isManaged(fromParent) && Clearcase.getInstance().isManaged(toParent)) {
            // 1. checkout parents if needed
            checkout(fromParent);
            if(!fromParent.equals(toParent)){
                checkout(toParent);
            }
            
            // 2. move the file
            ClearcaseClient.CommandRunnable cr = Clearcase.getInstance().getClient().post(new ExecutionUnit(
                "Moving ...", new MoveCommand(from, to)));
            cr.waitFinished();            
        } else {
            // XXX what if VOB root ???
            // XXX or whatever else ???
        }                                 
    }
    
    @Override
    public boolean beforeCreate(File file, boolean isDirectory) {
        Clearcase.LOG.finer("beforeCreate " + file);
        
        return super.beforeCreate(file, isDirectory);
    }

    @Override
    public void doCreate(File file, boolean isDirectory) throws IOException {
        Clearcase.LOG.finer("doCreate " + file);        
        
        super.doCreate(file, isDirectory);
    }

    @Override
    public void afterCreate(final File file) {
        Clearcase.LOG.finer("afterCreate " + file);
        cache.refreshLater(file);                 
    }
    
    @Override
    public void afterChange(final File file) {
        Clearcase.LOG.finer("afterChange " + file);
        
        Clearcase.getInstance().getRequestProcessor().post(new Runnable() {
            public void run() {
                fileChangedImpl(file);
            }
        });
    }

    private void fileChangedImpl(File file) {
        cache.refreshLater(file);
    }

    @Override
    public void beforeEdit(File file) {
        Clearcase.LOG.finer("beforeEdit " + file);        
        if(!file.canWrite()) { // XXX HACK             
            CheckoutAction.checkout(file);   
        }
    }
    
    
}