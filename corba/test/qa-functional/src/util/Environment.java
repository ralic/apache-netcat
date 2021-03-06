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
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
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

package util;

import java.io.File;
import java.util.Enumeration;
import org.netbeans.modules.corba.settings.CORBASupportSettings;
import org.netbeans.modules.corba.settings.ORBSettingsBundle;
import org.openide.execution.NbProcessDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.JarFileSystem;
import org.openide.filesystems.LocalFileSystem;
import org.openide.filesystems.Repository;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;

public class Environment {
    
    public static class ORB {
        
        String shortcut;
        String displayName;

        String[] dirs;
        String[] jars;
        String[] jarsdirs;
        
        public ORB (String shortcut, String displayName) {
            this (shortcut, displayName, null, null, null);
        }
        
        public ORB (String shortcut, String displayName, String[] dirs, String[] jars, String[] jarsdirs) {
            this.shortcut = shortcut;
            this.displayName = displayName;
            this.dirs = dirs;
            this.jars = jars;
            this.jarsdirs = jarsdirs;
        }
        
        public String getShortcut () {
            return shortcut;
        }
        
        public void load () {
            if (dirs != null)
                for (int a = 0; a < dirs.length; a ++)
                    mountDir(dirs[a]);
            if (jars != null)
                for (int a = 0; a < jars.length; a ++)
                    mountJar(jars[a]);
            if (jarsdirs != null)
                for (int a = 0; a < jarsdirs.length; a ++)
                    mountJarsDir(jarsdirs[a]);
            css.setOrb(displayName);
        }
        
        public void unload () {
            if (dirs != null)
                for (int a = 0; a < dirs.length; a ++)
                    unmountDir(dirs[a]);
            if (jars != null)
                for (int a = 0; a < jars.length; a ++)
                    unmountJar(jars[a]);
            if (jarsdirs != null)
                for (int a = 0; a < jarsdirs.length; a ++)
                    unmountJarsDir(jarsdirs[a]);
        }
        
        public void setNSBinding () {
            css.getActiveSetting ().setServerBindingFromString (ORBSettingsBundle.SERVER_NS);
            css.getActiveSetting ().setClientBindingFromString (ORBSettingsBundle.CLIENT_NS);
        }
        
    }
    
    public static class Open1xORB extends ORB {
        
        public Open1xORB () {
            super ("OPEN1X", "OpenORB 1.x (unsupported)", null, null, (System.getProperty ("OPEN1X_DIR") != null) ? new String[] {System.getProperty ("OPEN1X_DIR"), System.getProperty ("netbeans.home") + "/lib/ext"} : new String[] {System.getProperty ("netbeans.home") + "/lib/ext"});
        }

        public void load () {
            super.load ();
            NbProcessDescriptor pd = css.getActiveSetting().getIdl();
            String prog = System.getProperty ("java.home", null);
            if (prog != null) {
                if (winOS)
                    prog += "/bin/java.exe";
                else
                    prog += "/bin/java";
            } else
                prog = pd.getProcessName();
            String args = pd.getArguments();
            int i = args.indexOf("{classpath}");
            if (i >= 0) {
                args = args.substring(0, i) + "{filesystems}" + args.substring(i + 11);
//                css.getActiveSetting().setIdl(new NbProcessDescriptor(System.getProperty ("java.home") + ((winOS) ? "/bin/java.exe" : "/bin/java"), args, pd.getInfo()));
                css.getActiveSetting().setIdl(new NbProcessDescriptor(prog, args, pd.getInfo()));
            }
        }
        
    }
    
    public static class JDKORB extends ORB {
        
        public JDKORB (String shortcut, String displayName) {
            super (shortcut, displayName, null, null, null);
        }

        public void load () {
            super.load ();
            NbProcessDescriptor pd = css.getActiveSetting().getIdl();
            String prog = System.getProperty ("java.home", null);
            if (prog != null) {
                if (winOS)
                    prog += "/bin/java.exe";
                else
                    prog += "/bin/java";
            } else
                prog = pd.getProcessName();
            css.getActiveSetting().setIdl(new NbProcessDescriptor(prog, pd.getArguments(), pd.getInfo()));
        }
        
    }
    
    public static final boolean winOS = System.getProperty("os.name").startsWith("Win");
    public static final CORBASupportSettings css = (CORBASupportSettings) CORBASupportSettings.findObject(CORBASupportSettings.class, true);
    public static final ORB[] orbs;
    
    static {
        orbs = new ORB[14];
        orbs[0] = new ORB ("J2EE", "J2EE ORB");
        orbs[1] = new JDKORB ("JDK13", "JDK 1.3 ORB");
        orbs[2] = new JDKORB ("JDK14", "JDK 1.4 ORB");
        orbs[3] = new ORB ("OB4X", winOS ? "ORBacus for Java 4.x for Windows" : "ORBacus for Java 4.x");
        orbs[4] = new ORB ("OW2000", "Orbix 2000 1.x for Java");
        orbs[5] = new ORB ("OW32", "OrbixWeb 3.2");
        orbs[6] = new ORB ("VB34", "VisiBroker 3.4 for Java");
        orbs[7] = new ORB ("VB4X", "VisiBroker 4.x for Java");
        orbs[8] = new ORB ("E1X", "eORB 1.x (unsupported)");
        orbs[9] = new ORB ("JAC13", "JacORB 1.3.x (unsupported)");
        orbs[10] = new ORB ("JAVA22", "JavaORB 2.2.x (unsupported)");
        orbs[11] = new ORB ("JDK12", "JDK 1.2 ORB (unsupported)");
        orbs[12] = new Open1xORB ();
        orbs[13] = new ORB ("OB3X", winOS ? "ORBacus for Java 3.x for Windows" : "ORBacus for Java 3.x");
    }
    
    public static void unloadAllORBEnvironment () {
        for (int a = 0; a < orbs.length; a ++)
            orbs[a].unload ();
    }
    public static ORB findORBByDisplayName (String shortcut) {
        for (int a = 0; a < orbs.length; a ++)
            if (orbs[a].getShortcut ().equals (shortcut))
                return orbs[a];
        return null;
    }
    
    public static ORB loadORBEnvironment (String shortcut) {
        unloadAllORBEnvironment ();
        ORB o = findORBByDisplayName (shortcut);
        o.load ();
        return o;
    }
    
    public static String mountDir(String name) {
        try {
            if (Repository.getDefault ().findFileSystem(name) != null)
                return null;
            LocalFileSystem lfs = new LocalFileSystem();
            lfs.setRootDirectory(new File(name));
            Repository.getDefault ().addFileSystem(lfs);
            return lfs.getDisplayName ();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static void mountJar(String name) {
        try {
            if (Repository.getDefault ().findFileSystem(name) != null)
                return;
            JarFileSystem jfs = new JarFileSystem();
            jfs.setJarFile(new File(name));
            Repository.getDefault ().addFileSystem(jfs);
        } catch (Exception e) {
        }
    }
    
    public static void mountJarsDir(String name) {
        File[] jars = new File(name).listFiles();
        if (jars == null)
            return;
        for (int a = 0; a < jars.length; a ++) {
            String path = jars[a].getAbsolutePath ();
            if (path.endsWith(".jar")  ||  path.endsWith(".zip"))
                mountJar(path);
        }
    }
    
    public static void unmountDir(String name) {
        name = removeEndingSeparator (name);
        Enumeration e = Repository.getDefault ().getFileSystems();
        while (e.hasMoreElements()) {
            FileSystem fs = (FileSystem) e.nextElement();
            if (compareFileSystemNames (fs.getSystemName(), name))
                Repository.getDefault ().removeFileSystem (fs);
        }
    }
    
    public static void unmountJar(String name) {
        try {
            name = new File (name).getCanonicalPath();
        } catch (Exception e) {
            return;
        }
        Enumeration e = Repository.getDefault ().getFileSystems();
        while (e.hasMoreElements()) {
            FileSystem fs = (FileSystem) e.nextElement();
            if (fs instanceof JarFileSystem) {
                JarFileSystem jfs = (JarFileSystem) fs;
                if (compareFileSystemNames (jfs.getJarFile().getAbsolutePath(), name))
                    Repository.getDefault ().removeFileSystem (jfs);
            }
        }
    }
    
    public static void unmountJarsDir(String name) {
        File[] jars = new File(name).listFiles();
        if (jars == null)
            return;
        for (int a = 0; a < jars.length; a ++) {
            String path = jars[a].getAbsolutePath ();
            if (path.endsWith(".jar")  ||  path.endsWith(".zip"))
                unmountJar(path);
        }
    }
    
    public static String removeEndingSeparator (String path) {
        if (path.indexOf(':') >= 0  &&  path.length () <= 3)
            return path;
        if (path != null  &&  path.endsWith(File.separator))
            path = path.substring (0, path.length () - File.separator.length());
        else if (path != null  &&  path.endsWith("/"))
            path = path.substring (0, path.length () - 1);
        return path;
    }
    
    public static String replaceWinSeparator (String path) {
        return path.replace ('\\', '/');
    }
    
    public static String lowerFirstLetter (String path) {
        return (winOS) ? path.toLowerCase() : path;
    }
    
    public static String normalizeFileSystemName (String path) {
        return lowerFirstLetter (replaceWinSeparator (removeEndingSeparator (path)));
    }
    
    public static boolean compareFileSystemNames (String name1, String name2) {
        name1 = normalizeFileSystemName (name1);
        name2 = normalizeFileSystemName (name2);
        return name1.compareTo(name2) == 0;
    }
    
    public static FileObject findFileObject (String path) {
        return Repository.getDefault().findResource(path);
    }
    
    public static DataObject getDataObject (FileObject fo) {
        try {
            return DataObject.find (fo);
        } catch (DataObjectNotFoundException e) {
            return null;
        }
    }
    
    public static String getActiveORBName () {
        return css.getActiveSetting().getOrbName();
    }

    public static String getServerBindingName () {
        return css.getActiveSetting ().getServerBindingName();
    }

    public static String getClientBindingName () {
        return css.getActiveSetting ().getClientBindingName();
    }

}
