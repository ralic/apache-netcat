/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2004 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.j2ee.ejbfreeform;

import java.io.File;
import org.netbeans.api.project.Project;
import org.netbeans.modules.j2ee.api.ejbjar.EjbJar;
import org.netbeans.modules.j2ee.deployment.common.api.EjbChangeDescriptor;
import org.netbeans.modules.j2ee.deployment.devmodules.api.J2eeModule;
import org.netbeans.modules.j2ee.deployment.devmodules.api.ModuleChangeReporter;
import org.netbeans.modules.j2ee.deployment.devmodules.spi.J2eeModuleProvider;
import org.netbeans.spi.project.support.ant.AntProjectHelper;
import org.netbeans.spi.project.support.ant.PropertyEvaluator;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;


/**
 * An ejb module implementation on top of project.
 *
 * @author Martin Adamek
 */
public class EJBFreeformProvider extends J2eeModuleProvider implements ModuleChangeReporter, EjbChangeDescriptor {
    
    private Project project;
    private AntProjectHelper helper;
    private PropertyEvaluator evaluator;
    private EJBModules ejbModules;
    private EJBFreeformModule ejbModule;
    
    /** Creates a new instance of EJBFreeformProvider */
    public EJBFreeformProvider(Project project, AntProjectHelper helper, PropertyEvaluator evaluator) {
        this.project = project;
        this.helper = helper;
        this.evaluator = evaluator;
        ejbModules = new EJBModules(project, helper, evaluator);
        ejbModule = new EJBFreeformModule(project, helper, evaluator);
    }
    
    // from J2eeModuleProvider
    
    public File getDeploymentConfigurationFile(String name) {
        FileObject moduleFolder = ejbModules.getEjbJarImplementation().getMetaInf();
        File configFolder = FileUtil.toFile(moduleFolder);
        return new File(configFolder, name);
    }
    
    public FileObject findDeploymentConfigurationFile(String name) {
        return ejbModules.getEjbJarImplementation().getMetaInf().getFileObject(name);
    }
    
    public ModuleChangeReporter getModuleChangeReporter() {
        return this;
    }
    
    public J2eeModule getJ2eeModule() {
        return ejbModule;
    }
    
    //  from ModuleChangeReporter
    
    public boolean isManifestChanged(long timestamp) {
        return false;
    }
    
    public EjbChangeDescriptor getEjbChanges(long timestamp) {
        return this;
    }
    
    // from EjbChangeDescriptor
    
    public String[] getChangedEjbs() {
        return new String[] {};
    }
    
    public boolean ejbsChanged() {
        return false;
    }
}
