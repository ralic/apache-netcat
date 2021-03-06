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
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
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

package org.netbeans.modules.tasklist.scanningscopes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.api.project.Sources;
import org.netbeans.api.project.ui.OpenProjects;
import org.openide.filesystems.FileObject;

/**
 * Iterate all resources (files and folders) that are under the current main project.
 * The iretator is empty when no project has been set as the main one.
 * 
 * @author S. Aubrecht
 */
class MainProjectIterator implements Iterator<FileObject> {
    
    private Iterator<FileObject> iterator;
    private Collection<FileObject> editedFiles;
    
    /** Creates a new instance of MainProjectIterator */
    public MainProjectIterator( Collection<FileObject> editedFiles ) {
        this.editedFiles = editedFiles;
    }
    
    public boolean hasNext() {
        initialize();
        return iterator.hasNext();
    }

    public FileObject next() {
        initialize();
        return iterator.next();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    private void initialize() {
        if( null == iterator ) {
            iterator = createIterator();
        }
    }
    
    protected Iterator<FileObject> createIterator() {
        Project mainProject = OpenProjects.getDefault().getMainProject();
        if( null == mainProject ) {
            return new EmptyIterator();
        }
        
        ArrayList<FileObject> roots = new ArrayList<FileObject>(10);
        
        addProject( mainProject, roots );
        
        return new FileObjectIterator( roots, editedFiles );
    }
    
    private void addProject( Project p, ArrayList<FileObject> roots ) {
        Sources sources = ProjectUtils.getSources( p );
        SourceGroup[] groups = sources.getSourceGroups( Sources.TYPE_GENERIC );
        for( SourceGroup group : groups ) {
            FileObject rootFolder = group.getRootFolder();
            roots.add( rootFolder );
        }
    }
}
