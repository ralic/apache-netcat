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
 * Portions Copyrighted 2007 Sun Microsystems, Inc.
 */

package org.netbeans.modules.j2ee.geronimo2.nodes;

import org.netbeans.modules.j2ee.deployment.plugins.spi.RegistryNodeFactory;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 * Allows plugin to create all the registry nodes 
 * (other than the root node) as org.openide.nodes.Node subclasses, 
 * and use FilterNode to generate the display, adding infrastructure actions in,
 * and exposing certain infrastructure to the plugins for use in
 * constructing nodes.  Use a look-like infrastructure so migration to
 * looks can happen easier.
 * Plugins need to register an isntance of this class in module layer in folder
 * J2EE/Jsr88Plugins/{plugin_name}.
 *
 * @author Max Sauer
 */
public class GeRegistryNodeFactory implements RegistryNodeFactory {
    
     /**
      * Provide node representing JSR88 Target object.  
      * @param lookup will contain DeploymentFactory, DeploymentManager, Target, Management objects.
      * @return target server node
      */
    public Node getTargetNode(Lookup lookup) {
        return new GeTargetNode(lookup);
    }
    
    public Node getManagerNode(Lookup lookup) {
        return new GeInstanceNode(lookup);
    }    
}
