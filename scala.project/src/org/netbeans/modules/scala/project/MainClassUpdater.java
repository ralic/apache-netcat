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
package org.netbeans.modules.scala.project;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.lang.model.element.TypeElement;
import javax.swing.SwingUtilities;
import org.netbeans.modules.gsfpath.api.classpath.ClassPath;
import org.netbeans.napi.gsfret.source.ClasspathInfo;
import org.netbeans.napi.gsfret.source.CompilationController;
import org.netbeans.napi.gsfret.source.Source;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectManager;
import org.netbeans.modules.gsf.api.CancellableTask;
import org.netbeans.modules.java.api.common.ant.UpdateHelper;
import org.netbeans.modules.scala.editing.ScalaMimeResolver;
import org.netbeans.modules.scala.editing.ScalaParserResult;
import org.netbeans.modules.scala.editing.SourceUtils;
import org.netbeans.modules.scala.editing.nodes.AstScope;
import org.netbeans.modules.scala.editing.nodes.tmpls.ObjectTemplate;
import org.netbeans.modules.scala.editing.nodes.Packaging;
import org.netbeans.napi.gsfret.source.Phase;
import org.netbeans.spi.project.support.ant.AntProjectHelper;
import org.netbeans.spi.project.support.ant.EditableProperties;
import org.netbeans.spi.project.support.ant.PropertyEvaluator;
import org.openide.filesystems.FileChangeAdapter;
import org.openide.filesystems.FileChangeListener;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileRenameEvent;
import org.openide.util.Exceptions;
import org.openide.util.Mutex;
import org.openide.util.MutexException;
import org.openide.util.RequestProcessor;
import org.openide.util.WeakListeners;

/**
 *
 * @author Tomas Zezula
 */
public class MainClassUpdater extends FileChangeAdapter implements PropertyChangeListener {

    private static final RequestProcessor RP = new RequestProcessor("main-class-updater", 1);       //NOI18N

    private final Project project;
    private final PropertyEvaluator eval;
    private final UpdateHelper helper;
    private final ClassPath sourcePath;
    private final String mainClassPropName;
    private FileObject current;
    private FileChangeListener listener;

    /** Creates a new instance of MainClassUpdater */
    public MainClassUpdater(final Project project, final PropertyEvaluator eval,
            final UpdateHelper helper, final ClassPath sourcePath, final String mainClassPropName) {
        assert project != null;
        assert eval != null;
        assert helper != null;
        assert sourcePath != null;
        assert mainClassPropName != null;
        this.project = project;
        this.eval = eval;
        this.helper = helper;
        this.sourcePath = sourcePath;
        this.mainClassPropName = mainClassPropName;
        this.eval.addPropertyChangeListener(this);
        this.addFileChangeListener();
    }

    public synchronized void unregister() {
        if (current != null && listener != null) {
            current.removeFileChangeListener(listener);
        }
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (this.mainClassPropName.equals(evt.getPropertyName())) {
            //Go out of the ProjectManager.MUTEX, see #118722
            RP.post(new Runnable() {

                public void run() {
                    MainClassUpdater.this.addFileChangeListener();
                }
            });
        }
    }

    @Override
    public void fileRenamed(final FileRenameEvent evt) {
        if (!project.getProjectDirectory().isValid()) {
            return;
        }
        final FileObject _current;
        synchronized (this) {
            _current = this.current;
        }
        if (evt.getFile() == _current) {
            Runnable r = new Runnable() {

                public void run() {
                    try {
                        final String oldMainClass = ProjectManager.mutex().readAccess(new Mutex.ExceptionAction<String>() {

                            public String run() throws Exception {
                                return eval.getProperty(mainClassPropName);
                            }
                        });

                        Collection<TypeElement> main = SourceUtils.getMainClasses(_current);
                        String newMainClass = null;
                        if (!main.isEmpty()) {
                            TypeElement mainHandle = main.iterator().next();
                            newMainClass = mainHandle.getQualifiedName().toString();
                        }
                        if (newMainClass != null && !newMainClass.equals(oldMainClass) && helper.requestUpdate() &&
                                // XXX ##84806: ideally should update nbproject/configs/*.properties in this case:
                                eval.getProperty(J2SEConfigurationProvider.PROP_CONFIG) == null) {
                            final String newMainClassFinal = newMainClass;
                            ProjectManager.mutex().writeAccess(new Mutex.ExceptionAction<Void>() {

                                public Void run() throws Exception {
                                    EditableProperties props = helper.getProperties(AntProjectHelper.PROJECT_PROPERTIES_PATH);
                                    props.put(mainClassPropName, newMainClassFinal);
                                    helper.putProperties(AntProjectHelper.PROJECT_PROPERTIES_PATH, props);
                                    ProjectManager.getDefault().saveProject(project);
                                    return null;
                                }
                            });
                        }
                    } catch (IOException e) {
                        Exceptions.printStackTrace(e);
                    } catch (MutexException e) {
                        Exceptions.printStackTrace(e);
                    }
                }
            };
            if (SwingUtilities.isEventDispatchThread()) {
                r.run();
            } else {
                SwingUtilities.invokeLater(r);
            }
        }
    }

    private void addFileChangeListener() {
        synchronized (MainClassUpdater.this) {
            if (current != null && listener != null) {
                current.removeFileChangeListener(listener);
                current = null;
                listener = null;
            }
        }
        final String mainClassName = MainClassUpdater.this.eval.getProperty(mainClassPropName);
        if (mainClassName != null) {
            try {
                FileObject[] roots = sourcePath.getRoots();
                if (roots.length > 0) {
                    ClassPath bootCp = ClassPath.getClassPath(roots[0], ClassPath.BOOT);
                    ClassPath compileCp = ClassPath.getClassPath(roots[0], ClassPath.COMPILE);
                    final ClasspathInfo cpInfo = ClasspathInfo.create(bootCp, compileCp, sourcePath);
                    
                    /** 
                     * @TODO ugly hacking to find mainClass's fo, this hacking 
                     * requirs main class name is in the same name .scala file 
                     */
                    String[] paths = mainClassName.split("\\.");
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < paths.length; i++) {
                        sb.append(paths[i]);
                        if (i < paths.length - 1) {
                            sb.append(File.separator);
                        }
                    }
                    sb.append(".scala");
                    String mainClassFoPath = sb.toString();
                    
                    FileObject mainClassFo = null;
                    for (FileObject root : roots) {
                        mainClassFo = root.getFileObject(mainClassFoPath);
                        if (mainClassFo != null) {
                            break;
                        }
                    }
                    if (mainClassFo == null) {
                        return;
                    }
                    
                    Source js = Source.create(cpInfo, mainClassFo);
                    js.runUserActionTask(new CancellableTask<CompilationController>() {

                        public void cancel() {
                        }

                        public void run(CompilationController c) throws Exception {
                            if (c.toPhase(Phase.RESOLVED).compareTo(Phase.RESOLVED) < 0) {
                                return;
                            }
                            ScalaParserResult pResult = (ScalaParserResult) c.getEmbeddedResult(ScalaMimeResolver.MIME_TYPE, 0);
                            AstScope rootScope = pResult.getRootScope();
                            if (rootScope == null) {
                                return;
                            }
                            
                            List<ObjectTemplate> objs = null;
                            for (Packaging packaging : rootScope.getDefsInScope(Packaging.class)) {
                                objs = packaging.getBindingScope().getDefsInScope(ObjectTemplate.class);
                                break;
                            }
                            if (objs == null) {
                                objs = rootScope.getDefsInScope(ObjectTemplate.class);
                            }
                            ObjectTemplate mainClass = null;
                            for (ObjectTemplate obj : objs) {
                                if (obj.getQualifiedName().toString().equals(mainClassName)) {
                                    mainClass = obj;
                                    break;
                                }
                            }
                            if (mainClass != null) {
                                synchronized (MainClassUpdater.this) {
                                    current = c.getFileObject();
                                    listener = WeakListeners.create(FileChangeListener.class, MainClassUpdater.this, current);
                                    if (current != null && sourcePath.contains(current)) {
                                        current.addFileChangeListener(listener);
                                    }
                                }
                            }
                        }
                    }, true);
                }
            } catch (IOException ioe) {
            }
        }
    }
}
