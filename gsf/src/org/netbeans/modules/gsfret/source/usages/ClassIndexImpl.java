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

package org.netbeans.modules.gsfret.source.usages;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import org.netbeans.modules.gsf.api.Index.SearchResult;
import org.netbeans.modules.gsf.api.NameKind;
import org.netbeans.napi.gsfret.source.ClassIndex;
import org.netbeans.napi.gsfret.source.Source;
import org.openide.filesystems.FileObject;

/** 
 * This file is originally from Retouche, the Java Support 
 * infrastructure in NetBeans. I have modified the file as little
 * as possible to make merging Retouche fixes back as simple as
 * possible. 
 *
 * Should probably final class with private constructor.
 *
 * @author Petr Hrebejk, Tomas Zezula
 */
public abstract class ClassIndexImpl {


    public static enum UsageType {

        SUPER_CLASS( 0 ),
        SUPER_INTERFACE( 1 ),
        FIELD_REFERENCE( 2 ), 
        METHOD_REFERENCE( 3 ),
        TYPE_REFERENCE (4);

        private int offset;

        UsageType( final int offset) {
            this.offset = offset;
        }

        int getOffset () {
            return this.offset;
        }
    }
    
    
    public static ClassIndexFactory FACTORY;    
    
    public abstract FileObject[] getSourceRoots ();
   
//    public abstract BinaryAnalyser getBinaryAnalyser ();
    
    public abstract SourceAnalyser getSourceAnalyser ();
    
    public abstract void setDirty (Source js);
    
    protected abstract void close () throws IOException;
    
    // BEGIN TOR MODIFICATIONS
    //public abstract void gsfStore(Set<Map<String,String>> fieldToData, Set<String> toDelete) throws IOException;
// For index browser
    public abstract void search(final String primaryField, final String name, final NameKind kind, 
            final Set<ClassIndex.SearchScope> scope, /*final ResultConvertor<T> convertor,*/ 
            final Set<SearchResult> result, Set<String> terms) throws IOException;
    public abstract URL getRoot();
    public abstract File getSegment();
    public abstract Map<String,String> getTimeStamps() throws IOException;

    public abstract void storeEmpty();
    // END TOR MODIFICATIONS
    
}
