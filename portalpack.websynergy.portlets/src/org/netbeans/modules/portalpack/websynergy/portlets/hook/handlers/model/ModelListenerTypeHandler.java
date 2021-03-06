/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 2008 Sun Microsystems, Inc. All rights reserved.
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

package org.netbeans.modules.portalpack.websynergy.portlets.hook.handlers.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.netbeans.api.java.project.JavaProjectConstants;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.modules.portalpack.portlets.genericportlets.core.actions.util.PortletProjectUtils;
import org.netbeans.modules.portalpack.servers.core.util.PSConfigObject;
import org.netbeans.modules.portalpack.servers.websynergy.common.WebSpacePropertiesUtil;
import org.netbeans.modules.portalpack.websynergy.portlets.hook.api.HookTypeHandler;
import org.netbeans.modules.portalpack.websynergy.portlets.hook.api.WizardDescriptorPanelWrapper;
import org.netbeans.modules.portalpack.websynergy.portlets.util.PluginXMLUtil;
import org.netbeans.modules.portalpack.websynergy.portlets.util.PortalPropertyUtil;
import org.netbeans.modules.portalpack.websynergy.portlets.util.TemplateUtil;
import org.netbeans.spi.java.project.support.ui.templates.JavaTemplates;
import org.netbeans.spi.project.ui.templates.support.Templates;
import org.openide.WizardDescriptor;
import org.openide.WizardDescriptor.Panel;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;

/**
 *
 * @author Santh Chetan Chadalavada
 */
public class ModelListenerTypeHandler extends HookTypeHandler{

    private Project project;
    
    public ModelListenerTypeHandler(Project project) {
        this.project = project;
    }
    
    @Override
    public Panel getConfigPanel() {
        SourceGroup[] sourceGroups = ProjectUtils.getSources(project).getSourceGroups(JavaProjectConstants.SOURCES_TYPE_JAVA);
        return JavaTemplates.
                createPackageChooser(project, sourceGroups, new WizardDescriptorPanelWrapper(new ModelListenerConfigPanel(project)));
    }

    @Override
    public void addHookDefinition(FileObject hookXml, WizardDescriptor desc) {
        if (hookXml == null)
            return;
        
        String className = Templates.getTargetName(desc);
       
        FileObject packageFileObj = Templates.getTargetFolder(desc);
        String pkgName = PortletProjectUtils.getPackage(packageFileObj);
        if (pkgName != null && pkgName.trim().length() != 0)
        {
            className = pkgName + "." + className;
        }
        
         //Adding property to portal.properties for 5.2
        String PORTAL_PROPERTIES = "portal";
        FileObject srcRoot = PortletProjectUtils.getSourceRoot(project);//getWebModule(project).getWebInf();
        FileObject ppFO = srcRoot.getFileObject(PORTAL_PROPERTIES, "properties");
        if (ppFO == null) {
            try {
                TemplateUtil templateUtil = new TemplateUtil("hook/templates");
                ppFO = templateUtil.createFileFromTemplate("portalproperties.template", srcRoot, PORTAL_PROPERTIES, "properties");
                //result.add(ppFO);
             
            } catch (org.netbeans.modules.portalpack.websynergy.portlets.util.TemplateNotFoundException ex) {
                Exceptions.printStackTrace(ex);
            } 
        }

         try{
            PluginXMLUtil util = new PluginXMLUtil(hookXml);
            if(util.addPortalPropsHandlerHook()) {
                util.store();
            }
        }catch(Exception e) {
            Exceptions.printStackTrace(e);
        }
        PortalPropertyUtil.addProperty(FileUtil.toFile(ppFO), "value.object.listener."+(String)desc.getProperty("mdl_Name"), className);
    }

    @Override
    public void createAdditionalFiles(Project project, WizardDescriptor desc, Set result) {
        Map values = new HashMap();
        String className = Templates.getTargetName(desc);
        FileObject packageName = Templates.getTargetFolder(desc);
        String pkgName = PortletProjectUtils.getPackage(packageName);
        String mdlLsnrName = (String)desc.getProperty("mdl_Name");
        values.put("PACKAGE", pkgName);
        values.put("CLASS_NAME", className);
        values.put("MDL_LISTENER", mdlLsnrName);
        
        TemplateUtil templateUtil = new TemplateUtil("hook/templates");

        PSConfigObject psConfig = WebSpacePropertiesUtil.getSelectedServerProperties(project);
        int liferayVersion = 0;
        String mdlLsnrTmpl = "mdlLsnrHookPlugin.java";
        if (psConfig != null) {
            liferayVersion = WebSpacePropertiesUtil.getLiferayVersion(psConfig);
            if (liferayVersion >= 5203) {
                mdlLsnrTmpl = "mdlLsnrHookPlugin523.java";
            }
        }

        try {
            
            FileObject startupEvntHookPluginTemplate = templateUtil.getTemplateFile(mdlLsnrTmpl);
            FileObject mdlFO = templateUtil.mergeTemplateToFile(startupEvntHookPluginTemplate, packageName, className, values);
            result.add(mdlFO);

        } catch (org.netbeans.modules.portalpack.websynergy.portlets.util.TemplateNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } 
    }

}
