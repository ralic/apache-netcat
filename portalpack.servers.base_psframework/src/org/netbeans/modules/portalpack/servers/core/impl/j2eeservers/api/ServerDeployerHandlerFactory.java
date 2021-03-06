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

package org.netbeans.modules.portalpack.servers.core.impl.j2eeservers.api;

import org.netbeans.modules.portalpack.servers.core.api.PSDeploymentManager;
import org.netbeans.modules.portalpack.servers.core.common.ServerConstants;
import org.netbeans.modules.portalpack.servers.core.impl.j2eeservers.jboss.JBDeployHandler;
import org.netbeans.modules.portalpack.servers.core.impl.j2eeservers.sunappserver.GlassFishServerDeployHandler;
import org.netbeans.modules.portalpack.servers.core.impl.j2eeservers.tomcat.TomcatDeployHandler;
import org.netbeans.modules.portalpack.servers.core.util.PSConfigObject;


/**
 *
 * @author root
 */
public class ServerDeployerHandlerFactory {

    /** Creates a new instance of ServerDeployerHandlerFactory */
    private ServerDeployerHandlerFactory() {
    }

    public static ServerDeployHandler getServerDeployerHandler(PSDeploymentManager dm)
    {
         PSConfigObject psconfig = dm.getPSConfig();
         if(psconfig.getServerType() == null || psconfig.getServerType().trim().length() == 0)
             return new DefaultServerDeployHandler();

         if(psconfig.getServerType().equals(ServerConstants.SUN_APP_SERVER_9))
         {
             return new GlassFishServerDeployHandler(dm);
         }
         else if(psconfig.getServerType().equals(ServerConstants.JBOSS_5_X)) {
             return new JBDeployHandler(dm);
        }
         else{
             return new TomcatDeployHandler(dm);
         }
    }

}
