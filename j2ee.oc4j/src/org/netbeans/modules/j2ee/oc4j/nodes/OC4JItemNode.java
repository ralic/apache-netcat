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

package org.netbeans.modules.j2ee.oc4j.nodes;

import java.awt.Image;
import javax.enterprise.deploy.spi.TargetModuleID;
import javax.enterprise.deploy.spi.status.ProgressObject;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.netbeans.modules.j2ee.deployment.plugins.api.InstanceProperties;
import org.netbeans.modules.j2ee.deployment.plugins.api.UISupport;
import org.netbeans.modules.j2ee.deployment.plugins.api.UISupport.ServerIcon;
import org.netbeans.modules.j2ee.oc4j.OC4JDeploymentManager;
import org.netbeans.modules.j2ee.oc4j.nodes.actions.OpenURLAction;
import org.netbeans.modules.j2ee.oc4j.nodes.actions.OpenURLActionCookie;
import org.netbeans.modules.j2ee.oc4j.nodes.actions.RefreshModulesAction;
import org.netbeans.modules.j2ee.oc4j.nodes.actions.RefreshModulesCookie;
import org.netbeans.modules.j2ee.oc4j.nodes.actions.Refreshable;
import org.netbeans.modules.j2ee.oc4j.nodes.actions.UndeployModuleAction;
import org.netbeans.modules.j2ee.oc4j.nodes.actions.UndeployModuleCookie;
import org.netbeans.modules.j2ee.oc4j.util.OC4JPluginProperties;
import org.netbeans.modules.j2ee.oc4j.util.OC4JPluginUtils;
import org.openide.filesystems.Repository;
import org.openide.loaders.DataFolder;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.RequestProcessor.Task;
import org.openide.util.Utilities;
import org.openide.util.actions.SystemAction;

/**
 * Universal extensible node.
 *
 * @author Michal Mocnak
 */
public class OC4JItemNode extends AbstractNode {
    
    private ItemType type;
    
    private static final String HTTP_HEADER = "http://";
    
    private static final int TIMEOUT = 30000;
    
    private OC4JItemNode(Children children, final TargetModuleID module, final Lookup lookup, ItemType type) {
        super(children);
        this.type = type;
        
        if(type.equals(ItemType.J2EE_APPLICATION_FOLDER) ||
                type.equals(ItemType.REFRESHABLE_FOLDER)) {
            getCookieSet().add(new RefreshModulesCookie() {
                public void refresh() {
                    Children children = getChildren();
                    if(children instanceof Refreshable)
                        ((Refreshable)children).updateKeys();
                }
            });
        } else if(type.equals(ItemType.J2EE_APPLICATION)) {
            getCookieSet().add(new OpenURLActionCookie() {
                public String getWebURL() {
                    if(module == null || lookup == null)
                        return null;
                    
                    try {
                        OC4JDeploymentManager dm = lookup.lookup(OC4JDeploymentManager.class);
                        String app = null;
                        
                        if(module.getWebURL() != null)
                            app = module.getWebURL();
                        
                        for(TargetModuleID id:module.getChildTargetModuleID()) {
                            if(id.getWebURL() != null) {
                                app = id.getWebURL();
                                break;
                            }
                        }
                        
                        InstanceProperties ip = dm.getInstanceProperties();
                        
                        String host = ip.getProperty(OC4JPluginProperties.PROPERTY_HOST);
                        String httpPort = ip.getProperty(InstanceProperties.HTTP_PORT_NUMBER);
                        
                        if(app == null || host == null || httpPort == null)
                            return null;
                        
                        return HTTP_HEADER + host + ":" + httpPort + app;
                    } catch (Throwable t) {
                        return null;
                    }
                }
            });
            getCookieSet().add(new UndeployModuleCookie() {
                private boolean isRunning = false;
                
                public Task undeploy() {
                    final OC4JDeploymentManager dm = lookup.lookup(OC4JDeploymentManager.class);
                    final ProgressHandle handle = ProgressHandleFactory.createHandle(NbBundle.getMessage(OC4JItemNode.class,
                            "LBL_UndeployProgress", OC4JPluginUtils.getName(module)));
                    
                    Runnable r = new Runnable() {
                        public void run() {
                            isRunning = true;
                            
                            // Save the current time so that we can deduct that the undeploy
                            // failed due to timeout
                            long start = System.currentTimeMillis();
                            
                            ProgressObject o = dm.undeploy(new TargetModuleID[] {module});
                            
                            while(!o.getDeploymentStatus().isCompleted() && System.currentTimeMillis() - start < TIMEOUT) {
                                try {
                                    Thread.sleep(2000);
                                } catch(InterruptedException ex) {
                                    // Nothing to do
                                }
                            }
                            
                            handle.finish();
                            isRunning = false;
                        }
                    };
                    
                    handle.start();
                    return RequestProcessor.getDefault().post(r);
                }
                
                public synchronized boolean isRunning() {
                    return isRunning;
                }
            });
        }
    }
    
    public OC4JItemNode(Lookup lookup, TargetModuleID module, ItemType type) {
        this(Children.LEAF, module, lookup, type);
        setDisplayName(OC4JPluginUtils.getName(module));
    }
    
    public OC4JItemNode(Lookup lookup, Children children, String name, ItemType type) {
        this(children, null, lookup, type);
        setDisplayName(name);
    }
    
    public Image getIcon(int type) {
        if(this.type.equals(ItemType.J2EE_APPLICATION_FOLDER)) {
            return UISupport.getIcon(ServerIcon.EAR_FOLDER);
        } else if(this.type.equals(ItemType.J2EE_APPLICATION) ||
                this.type.equals(ItemType.J2EE_APPLICATION_SYSTEM)) {
            return UISupport.getIcon(ServerIcon.EAR_ARCHIVE);
        } else if(this.type.equals(ItemType.JDBC_RESOURCES)) {
            return Utilities.loadImage(JDBC_RESOURCE_ICON);
        } else if(this.type.equals(ItemType.CONNECTION_POOLS)) {
            return Utilities.loadImage(CONNECTION_POOL_ICON);
        } else {
            return getIconDelegate().getIcon(type);
        }
    }
    
    public Image getOpenedIcon(int type) {
        if(this.type.equals(ItemType.J2EE_APPLICATION_FOLDER)) {
            return UISupport.getIcon(ServerIcon.EAR_OPENED_FOLDER);
        } else if(this.type.equals(ItemType.J2EE_APPLICATION) ||
                this.type.equals(ItemType.J2EE_APPLICATION_SYSTEM) ||
                this.type.equals(ItemType.JDBC_RESOURCES) ||
                this.type.equals(ItemType.CONNECTION_POOLS)) {
            return getIcon(type);
        } else {
            return getIconDelegate().getOpenedIcon(type);
        }
    }
    
    private Node getIconDelegate() {
        return DataFolder.findFolder(Repository.getDefault().getDefaultFileSystem().getRoot()).getNodeDelegate();
    }
    
    public javax.swing.Action[] getActions(boolean context) {
        SystemAction[] actions = new SystemAction[] {};
        
        if(type.equals(ItemType.J2EE_APPLICATION_FOLDER)
                || type.equals(ItemType.REFRESHABLE_FOLDER)) {
            actions = new SystemAction[] {
                SystemAction.get(RefreshModulesAction.class)
            };
        } else if(type.equals(ItemType.J2EE_APPLICATION)) {
            actions = new SystemAction[] {
                SystemAction.get(OpenURLAction.class),
                SystemAction.get(UndeployModuleAction.class)
            };
        }
        
        return actions;
    }
    
    /* Creates and returns the instance of the node
     * representing the status 'WAIT' of the node.
     * It is used when it spent more time to create elements hierarchy.
     * @return the wait node.
     */
    public static Node createWaitNode() {
        AbstractNode n = new AbstractNode(Children.LEAF);
        n.setName(NbBundle.getMessage(OC4JItemNode.class, "LBL_WaitNode_DisplayName")); //NOI18N
        n.setIconBaseWithExtension("org/openide/src/resources/wait.gif"); // NOI18N
        return n;
    }
    
    private static final String JDBC_RESOURCE_ICON = "org/netbeans/modules/j2ee/oc4j/resources/JDBCResource.gif";
    private static final String CONNECTION_POOL_ICON = "org/netbeans/modules/j2ee/oc4j/resources/ConnectionPool.gif";
    
    public static class ItemType {
        
        public static ItemType J2EE_APPLICATION_FOLDER = new ItemType();
        public static ItemType J2EE_APPLICATION = new ItemType();
        public static ItemType J2EE_APPLICATION_SYSTEM = new ItemType();
        public static ItemType REFRESHABLE_FOLDER = new ItemType();
        public static ItemType JDBC_RESOURCES = new ItemType();
        public static ItemType CONNECTION_POOLS = new ItemType();
        
        private ItemType() {}
    }
}