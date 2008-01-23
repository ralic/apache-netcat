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
 * 
 * Contributor(s):
 * 
 * Portions Copyrighted 2008 Sun Microsystems, Inc.
 */

package org.netbeans.modules.websvc.axis2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.netbeans.modules.xml.xam.ModelSource;
import org.openide.cookies.EditorCookie;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author mkuchtiak
 */
public class AxisUtils {
    
    private static final String DEFAULT_NAMESPACE="http://ws.apache.org/axis2"; //NOI18N
    private static final String DEFAULT_SCHEMA_NAMESPACE=DEFAULT_NAMESPACE+"/xsd"; //NOI18N
    
    public static void retrieveServicesFromResource(final FileObject targetDir, boolean isWsGroup) throws IOException {
        final String handlerContent =
                (isWsGroup?
                    readResource(AxisUtils.class.getResourceAsStream("/org/netbeans/modules/websvc/axis2/resources/services_2.xml")): //NOI18N
                    readResource(AxisUtils.class.getResourceAsStream("/org/netbeans/modules/websvc/axis2/resources/services_1.xml"))); //NOI18N
        FileSystem fs = targetDir.getFileSystem();
        fs.runAtomicAction(new FileSystem.AtomicAction() {
            public void run() throws IOException {
                FileObject servicesFo = FileUtil.createData(targetDir, "services.xml");//NOI18N
                FileLock lock = servicesFo.lock();
                BufferedWriter bw = null;
                OutputStream os = null;
                try {
                    os = servicesFo.getOutputStream(lock);
                    bw = new BufferedWriter(new OutputStreamWriter(os));
                    bw.write(handlerContent);
                } finally {
                    if(bw != null)
                        bw.close();
                    if(os != null)
                        os.close();
                    if(lock != null)
                        lock.releaseLock();
                }
            }
        });
    }
    
    public static void retrieveAxis2FromResource(final FileObject targetDir) throws IOException {
        final String axis2Content =
                    readResource(AxisUtils.class.getResourceAsStream("/org/netbeans/modules/websvc/axis2/resources/axis2.xml")); //NOI18N
        FileSystem fs = targetDir.getFileSystem();
        fs.runAtomicAction(new FileSystem.AtomicAction() {
            public void run() throws IOException {
                FileObject axis2Fo = FileUtil.createData(targetDir, "axis2.xml");//NOI18N
                FileLock lock = axis2Fo.lock();
                BufferedWriter bw = null;
                OutputStream os = null;
                try {
                    os = axis2Fo.getOutputStream(lock);
                    bw = new BufferedWriter(new OutputStreamWriter(os));
                    bw.write(axis2Content);
                } finally {
                    if(bw != null)
                        bw.close();
                    if(os != null)
                        os.close();
                    if(lock != null)
                        lock.releaseLock();
                }
            }
        });
    }
    
    private static String readResource(InputStream is) throws IOException {
        // read the config from resource first
        StringBuffer sb = new StringBuffer();
        String lineSep = System.getProperty("line.separator");//NOI18N
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append(lineSep);
            line = br.readLine();
        }
        br.close();
        return sb.toString();
    }
    
    public static FileObject getAxisConfigFolder(FileObject projectDir, boolean create) {
        FileObject configFolder = projectDir.getFileObject("xml-resources/axis2/META-INF"); //NOI18N
        if (configFolder == null && create) {
            try {
                configFolder = FileUtil.createData(projectDir, "xml-resources/axis2/META-INF");
            } catch (IOException ex) {
                
            }
        }
        return configFolder;
    }
    
    public static FileObject getNbprojectFolder(FileObject projectDir) {
        return projectDir.getFileObject("nbproject"); //NOI18N
    }
    
    public static ModelSource createModelSource(final FileObject thisFileObj,
            boolean editable) {
        assert thisFileObj != null : "Null file object.";

        final DataObject dobj;
        try {
            dobj = DataObject.find(thisFileObj);
            final EditorCookie editor = dobj.getCookie(EditorCookie.class);
            if (editor != null) {
                Lookup proxyLookup = Lookups.proxy(
                   new Lookup.Provider() { 
                        public Lookup getLookup() {
                            try {
                                return Lookups.fixed(new Object[] {editor.openDocument(), dobj, thisFileObj});
                            } catch (IOException ex) {
                                return Lookups.fixed(new Object[] {dobj, thisFileObj});
                            }
                        }

                    }
                );
                return new ModelSource(proxyLookup, editable);
            }
        } catch (DataObjectNotFoundException ex) {
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE,
                ex.getMessage(), ex);
        }
        

        return null;
    }
    
    public static String getNamespaceFromClassName(String className) {
        StringTokenizer tokens = new StringTokenizer(className,"."); //NOI18N
        if (tokens.countTokens() <= 1) return DEFAULT_NAMESPACE;
        else {
            List<String> list = new ArrayList<String>();
            int index=0;
            while (tokens.hasMoreTokens()) {
                list.add(tokens.nextToken());
                index++;
            }
            list.remove(index-1);
            StringBuffer buf = new StringBuffer("http://"); //NOI18N
            for (int i = index-2; i>=0; i--) {
                buf.append(list.get(i)+"/"); //NOI18N
            }
            return buf.toString();
        }
    }
    
    public static String getSchemaNamespaceFromClassName(String className) {
        StringTokenizer tokens = new StringTokenizer(className,"."); //NOI18N
        if (tokens.countTokens() <= 1) return DEFAULT_SCHEMA_NAMESPACE;
        else {
            return getNamespaceFromClassName(className)+"xsd/";
        }
    }
}