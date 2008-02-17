/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.
 *
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

package org.netbeans.modules.erlang.project.ui.customizer;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.netbeans.api.project.ProjectManager;
import org.netbeans.modules.erlang.makeproject.spi.support.EditableProperties;
import org.netbeans.modules.erlang.makeproject.spi.support.GeneratedFilesHelper;
import org.netbeans.modules.erlang.makeproject.spi.support.PropertyEvaluator;
import org.netbeans.modules.erlang.makeproject.spi.support.RakeProjectHelper;
import org.netbeans.modules.erlang.makeproject.spi.support.ReferenceHelper;
import org.netbeans.modules.erlang.makeproject.spi.support.ui.StoreGroup;
import org.netbeans.modules.erlang.project.RubyProject;
import org.netbeans.modules.erlang.project.RubyProjectUtil;
import org.netbeans.modules.erlang.project.SourceRoots;
import org.netbeans.modules.erlang.project.UpdateHelper;
import org.openide.DialogDisplayer;
import org.openide.ErrorManager;
import org.openide.NotifyDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Mutex;
import org.openide.util.MutexException;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;

/**
 * @author Petr Hrebejk
 */
public class RubyProjectProperties {
    // Special properties of the project
    public static final String Ruby_PROJECT_NAME = "j2se.project.name"; // NOI18N
    public static final String JAVA_PLATFORM = "platform.active"; // NOI18N
    
    // Properties stored in the PROJECT.PROPERTIES    
    // TODO - nuke me!
    public static final String MAIN_CLASS = "main.file"; // NOI18N
    public static final String JAVAC_COMPILER_ARG = "javac.compilerargs";    //NOI18N
    public static final String RUN_JVM_ARGS = "run.jvmargs"; // NOI18N
    public static final String RUN_WORK_DIR = "work.dir"; // NOI18N
    public static final String DIST_DIR = "dist.dir"; // NOI18N
    public static final String DIST_JAR = "dist.jar"; // NOI18N
    public static final String JAVAC_CLASSPATH = "javac.classpath"; // NOI18N
    public static final String RUN_CLASSPATH = "run.classpath"; // NOI18N
    public static final String DEBUG_CLASSPATH = "debug.classpath"; // NOI18N
    public static final String JAR_COMPRESS = "jar.compress"; // NOI18N
    public static final String JAVAC_SOURCE = "javac.source"; // NOI18N
    public static final String JAVAC_TARGET = "javac.target"; // NOI18N
    public static final String JAVAC_TEST_CLASSPATH = "javac.test.classpath"; // NOI18N
    public static final String RUN_TEST_CLASSPATH = "run.test.classpath"; // NOI18N
    public static final String BUILD_DIR = "build.dir"; // NOI18N
    public static final String BUILD_CLASSES_DIR = "build.classes.dir"; // NOI18N
    public static final String BUILD_TEST_CLASSES_DIR = "build.test.classes.dir"; // NOI18N
    public static final String BUILD_TEST_RESULTS_DIR = "build.test.results.dir"; // NOI18N
    public static final String BUILD_CLASSES_EXCLUDES = "build.classes.excludes"; // NOI18N
    public static final String DIST_JAVADOC_DIR = "dist.javadoc.dir"; // NOI18N
    public static final String NO_DEPENDENCIES="no.dependencies"; // NOI18N
    public static final String DEBUG_TEST_CLASSPATH = "debug.test.classpath"; // NOI18N
    
                    
    // Properties stored in the PRIVATE.PROPERTIES
    public static final String APPLICATION_ARGS = "application.args"; // NOI18N
    
    // Well known paths
//    public static final String[] WELL_KNOWN_PATHS = new String[] {            
//            "${" + JAVAC_CLASSPATH + "}", 
//            "${" + JAVAC_TEST_CLASSPATH  + "}", 
//            "${" + RUN_CLASSPATH  + "}", 
//            "${" + RUN_TEST_CLASSPATH  + "}", 
//            "${" + BUILD_CLASSES_DIR  + "}", 
//            "${" + BUILD_TEST_CLASSES_DIR  + "}", 
//    };
//    
//    // Prefixes and suffixes of classpath
//    public static final String LIBRARY_PREFIX = "${libs."; // NOI18N
//    public static final String LIBRARY_SUFFIX = ".classpath}"; // NOI18N
//    // XXX looks like there is some kind of API missing in ReferenceHelper?
//    public static final String ANT_ARTIFACT_PREFIX = "${reference."; // NOI18N
    
    // SOURCE ROOTS
    // public static final String SOURCE_ROOTS = "__virtual_source_roots__";   //NOI18N
    // public static final String TEST_ROOTS = "__virtual_test_roots__"; // NOI18N
                        
    // MODELS FOR VISUAL CONTROLS
    
    // CustomizerSources
    DefaultTableModel SOURCE_ROOTS_MODEL;
    DefaultTableModel INCLUDE_ROOTS_MODEL;
    DefaultTableModel TEST_ROOTS_MODEL;
     
//    // CustomizerLibraries
//    DefaultListModel JAVAC_CLASSPATH_MODEL;
//    DefaultListModel JAVAC_TEST_CLASSPATH_MODEL;
//    DefaultListModel RUN_CLASSPATH_MODEL;
//    DefaultListModel RUN_TEST_CLASSPATH_MODEL;
//    ComboBoxModel PLATFORM_MODEL;
//    ListCellRenderer CLASS_PATH_LIST_RENDERER;
//    ListCellRenderer PLATFORM_LIST_RENDERER;
    
    // CustomizerCompile
//    ButtonModel NO_DEPENDENCIES_MODEL;
    Document JAVAC_COMPILER_ARG_MODEL;
    
    // CustomizerCompileTest
                
//    // CustomizerJar
//    Document DIST_JAR_MODEL; 
//    Document BUILD_CLASSES_EXCLUDES_MODEL; 
//    ButtonModel JAR_COMPRESS_MODEL;
                
    // CustomizerRun
    Map<String/*|null*/,Map<String,String/*|null*/>/*|null*/> RUN_CONFIGS;
    String activeConfig;

    // CustomizerRunTest

    // Private fields ----------------------------------------------------------------------    
    private RubyProject project;
    private HashMap properties;    
    private UpdateHelper updateHelper;
    private PropertyEvaluator evaluator;
    private ReferenceHelper refHelper;
    private GeneratedFilesHelper genFileHelper;
    
    private StoreGroup privateGroup; 
    private StoreGroup projectGroup;
    
    RubyProject getProject() {
        return project;
    }
    
    /** Creates a new instance of RubyUIProperties and initializes them */
    public RubyProjectProperties( RubyProject project, UpdateHelper updateHelper, PropertyEvaluator evaluator, ReferenceHelper refHelper, GeneratedFilesHelper genFileHelper ) {
        this.project = project;
        this.updateHelper  = updateHelper;
        this.evaluator = evaluator;
        this.refHelper = refHelper;
        this.genFileHelper = genFileHelper;
        //this.cs = new ClassPathSupport( evaluator, refHelper, updateHelper.getRakeProjectHelper(), WELL_KNOWN_PATHS, LIBRARY_PREFIX, LIBRARY_SUFFIX, ANT_ARTIFACT_PREFIX );
                
        privateGroup = new StoreGroup();
        projectGroup = new StoreGroup();
        
        init(); // Load known properties        
    }

    /** Initializes the visual models 
     */
    private void init() {        
        // CustomizerSources
        SOURCE_ROOTS_MODEL = RubySourceRootsUi.createModel( project.getSourceRoots() );
        INCLUDE_ROOTS_MODEL = RubySourceRootsUi.createModel( project.getIncludeRoots() );
        TEST_ROOTS_MODEL = RubySourceRootsUi.createModel( project.getTestSourceRoots() );        
        JAVAC_COMPILER_ARG_MODEL = projectGroup.createStringDocument( evaluator, JAVAC_COMPILER_ARG );

        // CustomizerRun
        RUN_CONFIGS = readRunConfigs();
        activeConfig = evaluator.getProperty("config");
                
    }
    
    public void save() {
        try {                        
            // Store properties 
            Boolean result = (Boolean) ProjectManager.mutex().writeAccess(new Mutex.ExceptionAction() {
                final FileObject projectDir = updateHelper.getRakeProjectHelper().getProjectDirectory();
                public Object run() throws IOException {
                    if ((genFileHelper.getBuildScriptState(GeneratedFilesHelper.BUILD_IMPL_XML_PATH,
                        RubyProject.class.getResource("resources/build-impl.xsl") //NOI18N
                        )
                        & GeneratedFilesHelper.FLAG_MODIFIED) == GeneratedFilesHelper.FLAG_MODIFIED) {  //NOI18N
                        if (showModifiedMessage (NbBundle.getMessage(RubyProjectProperties.class,"TXT_ModifiedTitle"))) {
                            //Delete user modified build-impl.xml
                            FileObject fo = projectDir.getFileObject(GeneratedFilesHelper.BUILD_IMPL_XML_PATH);
                            if (fo != null) {
                                fo.delete();
                            }
                        }
                        else {
                            return Boolean.FALSE;
                        }
                    }
                    storeProperties();
                    return Boolean.TRUE;
                }
            });
            // and save the project
            if (result == Boolean.TRUE) {
                ProjectManager.getDefault().saveProject(project);
            }
        } 
        catch (MutexException e) {
            ErrorManager.getDefault().notify((IOException)e.getException());
        }
        catch ( IOException ex ) {
            ErrorManager.getDefault().notify( ex );
        }
    }
    
    
        
    private void storeProperties() throws IOException {
        // Store source roots
        storeRoots( project.getSourceRoots(), SOURCE_ROOTS_MODEL );
        storeRoots( project.getIncludeRoots(), INCLUDE_ROOTS_MODEL );
        storeRoots( project.getTestSourceRoots(), TEST_ROOTS_MODEL );
                
        // Store standard properties
        EditableProperties projectProperties = updateHelper.getProperties( RakeProjectHelper.PROJECT_PROPERTIES_PATH );        
        EditableProperties privateProperties = updateHelper.getProperties( RakeProjectHelper.PRIVATE_PROPERTIES_PATH );
        
        // Standard store of the properties
        projectGroup.store( projectProperties );        
        privateGroup.store( privateProperties );
        
        storeRunConfigs(RUN_CONFIGS, projectProperties, privateProperties);
        EditableProperties ep = updateHelper.getProperties("nbproject/private/config.properties");
        if (activeConfig == null) {
            ep.remove("config");
        } else {
            ep.setProperty("config", activeConfig);
        }
        updateHelper.putProperties("nbproject/private/config.properties", ep);

        // Store the property changes into the project
        updateHelper.putProperties( RakeProjectHelper.PROJECT_PROPERTIES_PATH, projectProperties );
        updateHelper.putProperties( RakeProjectHelper.PRIVATE_PROPERTIES_PATH, privateProperties );        
        
    }
  
    private static String getDocumentText( Document document ) {
        try {
            return document.getText( 0, document.getLength() );
        }
        catch( BadLocationException e ) {
            return ""; // NOI18N
        }
    }
        
    private void storeRoots( SourceRoots roots, DefaultTableModel tableModel ) throws MalformedURLException {
        Vector data = tableModel.getDataVector();
        URL[] rootURLs = new URL[data.size()];
        String []rootLabels = new String[data.size()];
        for (int i=0; i<data.size();i++) {
            File f = (File) ((Vector)data.elementAt(i)).elementAt(0);
            rootURLs[i] = RubyProjectUtil.getRootURL(f,null);            
            rootLabels[i] = (String) ((Vector)data.elementAt(i)).elementAt(1);
        }
        roots.putRoots(rootURLs,rootLabels);
    }
    
    private static boolean showModifiedMessage (String title) {
        String message = NbBundle.getMessage(RubyProjectProperties.class,"TXT_Regenerate");
        JButton regenerateButton = new JButton (NbBundle.getMessage(RubyProjectProperties.class,"CTL_RegenerateButton"));
        regenerateButton.setDefaultCapable(true);
        regenerateButton.getAccessibleContext().setAccessibleDescription (NbBundle.getMessage(RubyProjectProperties.class,"AD_RegenerateButton"));
        NotifyDescriptor d = new NotifyDescriptor.Message (message, NotifyDescriptor.WARNING_MESSAGE);
        d.setTitle(title);
        d.setOptionType(NotifyDescriptor.OK_CANCEL_OPTION);
        d.setOptions(new Object[] {regenerateButton, NotifyDescriptor.CANCEL_OPTION});        
        return DialogDisplayer.getDefault().notify(d) == regenerateButton;
    }
        
    /**
     * A mess.
     */
    Map<String/*|null*/,Map<String,String>> readRunConfigs() {
        Map<String,Map<String,String>> m = new TreeMap<String,Map<String,String>>(new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1 != null ? (s2 != null ? s1.compareTo(s2) : 1) : (s2 != null ? -1 : 0);
            }
        });
        Map<String,String> def = new TreeMap<String,String>();
        for (String prop : new String[] {MAIN_CLASS, APPLICATION_ARGS, RUN_JVM_ARGS, RUN_WORK_DIR}) {
            String v = updateHelper.getProperties(RakeProjectHelper.PRIVATE_PROPERTIES_PATH).getProperty(prop);
            if (v == null) {
                v = updateHelper.getProperties(RakeProjectHelper.PROJECT_PROPERTIES_PATH).getProperty(prop);
            }
            if (v != null) {
                def.put(prop, v);
            }
        }
        m.put(null, def);
        FileObject configs = project.getProjectDirectory().getFileObject("nbproject/configs");
        if (configs != null) {
            for (FileObject kid : configs.getChildren()) {
                if (!kid.hasExt("properties")) {
                    continue;
                }
                m.put(kid.getName(), new TreeMap<String,String>(updateHelper.getProperties(FileUtil.getRelativePath(project.getProjectDirectory(), kid))));
            }
        }
        configs = project.getProjectDirectory().getFileObject("nbproject/private/configs");
        if (configs != null) {
            for (FileObject kid : configs.getChildren()) {
                if (!kid.hasExt("properties")) {
                    continue;
                }
                Map<String,String> c = m.get(kid.getName());
                if (c == null) {
                    continue;
                }
                c.putAll(new HashMap<String,String>(updateHelper.getProperties(FileUtil.getRelativePath(project.getProjectDirectory(), kid))));
            }
        }
        //System.err.println("readRunConfigs: " + m);
        return m;
    }

    /**
     * A royal mess.
     */
    void storeRunConfigs(Map<String/*|null*/,Map<String,String/*|null*/>/*|null*/> configs,
            EditableProperties projectProperties, EditableProperties privateProperties) throws IOException {
        //System.err.println("storeRunConfigs: " + configs);
        Map<String,String> def = configs.get(null);
        for (String prop : new String[] {MAIN_CLASS, APPLICATION_ARGS, RUN_JVM_ARGS, RUN_WORK_DIR}) {
            String v = def.get(prop);
            EditableProperties ep = (prop.equals(APPLICATION_ARGS) || prop.equals(RUN_WORK_DIR)) ?
                privateProperties : projectProperties;
            if (!Utilities.compareObjects(v, ep.getProperty(prop))) {
                if (v != null && v.length() > 0) {
                    ep.setProperty(prop, v);
                } else {
                    ep.remove(prop);
                }
            }
        }
        for (Map.Entry<String,Map<String,String>> entry : configs.entrySet()) {
            String config = entry.getKey();
            if (config == null) {
                continue;
            }
            String sharedPath = "nbproject/configs/" + config + ".properties"; // NOI18N
            String privatePath = "nbproject/private/configs/" + config + ".properties"; // NOI18N
            Map<String,String> c = entry.getValue();
            if (c == null) {
                updateHelper.putProperties(sharedPath, null);
                updateHelper.putProperties(privatePath, null);
                continue;
            }
            for (Map.Entry<String,String> entry2 : c.entrySet()) {
                String prop = entry2.getKey();
                String v = entry2.getValue();
                String path = (prop.equals(APPLICATION_ARGS) || prop.equals(RUN_WORK_DIR)) ?
                    privatePath : sharedPath;
                EditableProperties ep = updateHelper.getProperties(path);
                if (!Utilities.compareObjects(v, ep.getProperty(prop))) {
                    if (v != null && (v.length() > 0 || (def.get(prop) != null && def.get(prop).length() > 0))) {
                        ep.setProperty(prop, v);
                    } else {
                        ep.remove(prop);
                    }
                    updateHelper.putProperties(path, ep);
                }
            }
            // Make sure the definition file is always created, even if it is empty.
            updateHelper.putProperties(sharedPath, updateHelper.getProperties(sharedPath));
        }
    }
}
