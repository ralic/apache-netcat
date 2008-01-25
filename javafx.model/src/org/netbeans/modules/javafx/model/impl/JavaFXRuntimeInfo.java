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

package org.netbeans.modules.javafx.model.impl;

import java.io.File;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import net.java.javafx.type.Module;
import net.java.javafx.type.expr.CompilationUnit;
import net.java.javafx.typeImpl.Compilation;
import net.java.javafx.typeImpl.TypeFactoryImpl;
import org.netbeans.api.java.classpath.ClassPath;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.netbeans.modules.javafx.model.FXDocument;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author answer
 */
public class JavaFXRuntimeInfo {
    private FileObject fileObject;
    private FXDocument document;
    private Compilation compilation;
    private CompilationUnit unit;
    private Compilation previewCompilation;
    private CompilationUnit previewUnit;
    private ClassLoader loader;
    private JComponent resultComponent;
    
    private HashSet<FileObject> dependencies = new HashSet<FileObject>();
    
    public JavaFXRuntimeInfo(FXDocument doc){
        document = doc;
        fileObject = NbEditorUtilities.getFileObject(doc);
        assert fileObject != null;
        loader = createClassLoader();
        compilation = createCompilation(false);
        unit = null;
        resultComponent = null;
        previewCompilation = null; //createCompilation(true);
        previewUnit = null;
    }
    
    public void sourceChanged(){
        synchronized(this){
            previewUnit = null;
            resultComponent = null;
            unit = null;
        }
    }
    
    public Compilation getCompilation() {
        synchronized(this){
            if ((unit == null) && (previewUnit != null)){
                return previewCompilation;
            }
            if (compilation == null){
                compilation = createCompilation(false);
            }
        }
        return compilation;
    }

    public Compilation getPreviewCompilation() {
        synchronized(this){
            if (previewCompilation == null){
                previewCompilation = createCompilation(true);
            }
        }
        return previewCompilation;
    }
    
    public FXDocument getDocument() {
        return document;
    }

    public FileObject getFileObject() {
        return fileObject;
    }

    public ClassLoader getLoader() {
        if (loader == null){
            loader = createClassLoader();
        }
        return loader;
    }

    public CompilationUnit getUnit() {
        if ((unit == null) && (previewUnit != null)){
            return previewUnit;
        }
        if (unit == null){
            unit = readCompilationUnit(false);
            fillDependencies();
        }
        return unit;
    }

    public CompilationUnit getPreviewUnit() {
        if (previewUnit == null){
            previewUnit = readCompilationUnit(true);
            fillDependencies();
        }
        return previewUnit;
    }
    
    public JComponent getResultComponent(){
        return resultComponent;
    }
    
    public void setResultComponent(JComponent comp){
        resultComponent = comp;
    }

    public void fireDependenciesUpdate(FXDocument doc){
//        if (dependencies.contains(NbEditorUtilities.getFileObject(doc))){
//            JavaFXModel.sourceDependencyChanged(document);
//        }
        Iterator iterator = dependencies.iterator();
        while(iterator.hasNext()){
            FileObject fo = (FileObject)iterator.next();
            if (fo.getPath().equals(NbEditorUtilities.getFileObject(doc).getPath())){
                JavaFXModel.sourceDependencyChanged(document);
            }
        }
    }
    
    public void sourceDependencyChanged(){
        synchronized(this){
            compilation = null;
            previewCompilation = null;
            loader = null;
        }
    }
    
    private ClassLoader createClassLoader(){
        final ClassLoader contextLoader = Thread.currentThread().getContextClassLoader();
        final ClassPath classPath = ClassPath.getClassPath(fileObject, ClassPath.EXECUTE);
        final ClassPath sourcePath = ClassPath.getClassPath(fileObject, ClassPath.SOURCE);
        final ClassLoader classpathLoader;
        final ClassLoader sourcepathLoader;
        
        try{
            classpathLoader = classPath.getClassLoader(true);
            sourcepathLoader = sourcePath.getClassLoader(false);
        }catch(Exception e){
            return contextLoader;
        }
        
        ClassLoader result = new ClassLoader(contextLoader) {

            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                try{
                    return super.findClass(name);
                }catch(ClassNotFoundException e){}
                    try{
                        return classpathLoader.loadClass(name);
                    }catch(ClassNotFoundException e){}
                throw new ClassNotFoundException(name);
            }

            @Override
            public URL getResource(String name) {
                URL url;
                try{
                    url = sourcepathLoader.getResource(name);
                    if (url != null) {
                        String uri = url.toString();
                        FileObject fileObj = sourcePath.findResource(name);
                        return url;
                    }
                }catch(Exception e){}
                
                try{
                    url = classpathLoader.getResource(name);
                    if (url != null) {
                        String uri = url.toString();
                        FileObject fileObj = sourcePath.findResource(name);
                        return url;
                    }
                }catch(Exception e){}
                
                //fix for get images for the preview
                if (!name.endsWith(".fx") && !name.startsWith("file:")){
                    return this.getResource("file:" + name);
                }
                return null;
            }
        };
        
        return result;
    }
    
    private Compilation createCompilation(boolean forPreview){
//        System.out.println("[JavaFXRuntimeInfo] Create Compilation: " + forPreview);
        TypeFactoryImpl factory = new TypeFactoryImpl();
        Module module = factory.createModule();
        module.setClassLoader(getLoader()); 
        Compilation comp = new Compilation(module);
        if (forPreview){
            Reader readerW = JavaFXModel.getPreviewWidgetSource();
            CompilationUnit widgetUnit = comp.readCompilationUnit("javafx.netbeans.preview", readerW);
            try{
                readerW.close();
            }catch(Exception e){
            }
        }
        return comp;
    }
    
    private CompilationUnit readCompilationUnit(boolean forPreview){
        CompilationUnit result = null;
        CompilationThread t = new CompilationThread(this, forPreview);
        try{
            if(!SwingUtilities.isEventDispatchThread()) {
                SwingUtilities.invokeAndWait(t);
            } else {
                t.run();
            }
            t.join();
            result = t.getCompilationUnit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    
    private void fillDependencies(){
        Compilation comp;
        if (unit==null){
            comp = previewCompilation;
        }else{
            comp = compilation;
        }
        dependencies.clear();
        for (Object obj : comp.getCompilationUnits().values()) {
            CompilationUnit u = (CompilationUnit)obj;
            try{
                String us = u.getURI();
                if (!us.startsWith("jar:file:") && !us.equals("javafx.netbeans.preview") && !fileObject.getPath().equals(us)){
                    URI uri = new URI(us);
                    if (uri != null){
                        FileObject fo = FileUtil.toFileObject(new File(uri));
                        if (fo != null){
                            dependencies.add(fo);
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
