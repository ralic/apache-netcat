/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.j2ee.ejbfreeform;

import java.io.File;
import org.netbeans.junit.*;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.LocalFileSystem;
import org.openide.filesystems.MultiFileSystem;
import org.openide.filesystems.Repository;
import org.openide.filesystems.XMLFileSystem;
import org.openide.util.Lookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;

/**
 *
 * @author jungi
 */
public class TestBase extends NbTestCase {
    
    static {
        System.setProperty("org.openide.util.Lookup", Lkp.class.getName());
    }
    
    public TestBase(java.lang.String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        clearWorkDir();
        scratchF = getWorkDir();
        mkdir("system/J2EE/InstalledServers");
        mkdir("system/J2EE/DeploymentPlugins");
        System.setProperty("SYSTEMDIR", new File(scratchF, "system").getAbsolutePath());
        FileObject sfs = Repository.getDefault().getDefaultFileSystem().getRoot();
        assertNotNull("no default FS", sfs);
        FileObject j2eeFolder = sfs.getFileObject("J2EE");
        assertNotNull("have J2EE", j2eeFolder);
    }
    
    protected boolean runInEQ() {
        return true;
    }
    
    private File scratchF;
    
    private void mkdir(String path) {
        new File(scratchF, path.replace('/', File.separatorChar)).mkdirs();
    }
    
    private static final class Repo extends Repository {
        
        public Repo() throws Exception {
            super(mksystem());
        }
        
        private static FileSystem mksystem() throws Exception {
            LocalFileSystem lfs = new LocalFileSystem();
            lfs.setRootDirectory(new File(System.getProperty("SYSTEMDIR")));
            //get layer for the generic server
            java.net.URL layerFile = Repo.class.getClassLoader().getResource("org/netbeans/modules/j2ee/genericserver/resources/layer.xml");
            assert layerFile != null;
            XMLFileSystem layer = new XMLFileSystem(layerFile);
            FileSystem layers [] = new FileSystem [] {lfs, layer};
            MultiFileSystem mfs = new MultiFileSystem(layers);
            return mfs;
        }
        
    }
    
    public static final class Lkp extends ProxyLookup {
        
        public Lkp() {
            super(new Lookup[] {
                Lookups.fixed(new Object[] {"repo"}, new Conv()),
                Lookups.metaInfServices(Lkp.class.getClassLoader()),
                Lookups.singleton(Lkp.class.getClassLoader()),
            });
        }
        
        private static final class Conv implements InstanceContent.Convertor {
            public Conv() {}
            public Object convert(Object obj) {
                assert obj == "repo";
                try {
                    return new Repo();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            public String displayName(Object obj) {
                return obj.toString();
            }
            public String id(Object obj) {
                return obj.toString();
            }
            public Class type(Object obj) {
                assert obj == "repo";
                return Repository.class;
            }
        }
    }
}
