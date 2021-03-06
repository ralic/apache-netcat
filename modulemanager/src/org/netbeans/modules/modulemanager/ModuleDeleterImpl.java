/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2008 Sun Microsystems, Inc. All rights reserved.
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
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2008 Sun
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

package org.netbeans.modules.modulemanager;

import java.util.logging.Logger;
import org.openide.filesystems.FileUtil;
import org.openide.modules.InstalledFileLocator;
import org.openide.xml.XMLUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import org.netbeans.Module;
import org.netbeans.core.startup.Main;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.modules.ModuleInfo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/** Control if the module's file can be deleted and can delete them from disk.
 * <p> Deletes all files what are installed together with given module, info about
 * these files read from <code>update_tracking</code> file corresponed to the module.
 * If this <code>update_tracking</code> doesn't exist the files cannot be deleted.
 * The Deleter waits until the module is enabled before start delete its files.
 *
 * @author  Jirka Rechtacek (jrechtacek@netbeans.org)
 */
public final class ModuleDeleterImpl implements ModuleDeleter {
    private static final ModuleDeleterImpl INSTANCE = new ModuleDeleterImpl();
    private static final String TRACKING_FILE_NAME = "update_tracking"; // NOI18N
    private static final String ELEMENT_MODULE = "module"; // NOI18N
    private static final String ELEMENT_VERSION = "module_version"; // NOI18N
    private static final String ATTR_LAST = "last"; // NOI18N
    private static final String ATTR_FILE_NAME = "name"; // NOI18N
    
    private Logger err = Logger.getLogger (ModuleDeleterImpl.class.getName ());
    
    public static ModuleDeleterImpl getInstance() {
        return INSTANCE;
    }
    
    public boolean canDelete (Module module) {
        if (module == null) {
            return false;
        }
        if (module.isFixed ()) {
            err.log(Level.FINE,
                    "Cannot delete module because module " +
                    module.getCodeName() + " isFixed.");
        }
        return isUninstallAllowed (module) && findUpdateTracking (module);
    }
    
    private static boolean isUninstallAllowed(final Module m) {
        return ! (m.isFixed ());
    }
    
    public void delete (Module... modules) throws IOException {
        if (modules == null) {
            throw new IllegalArgumentException ("ModuleInfo argument cannot be null.");
        }
        
        for (Module moduleInfo : modules) {
            err.log(Level.FINE,"Locate and remove config file of " + moduleInfo.getCodeNameBase ());           
            removeControlModuleFile(moduleInfo);
        }

        new HackModuleListRefresher().run();
        int rerunWaitCount = 0;
        for (Module moduleInfo : modules) {
            err.log(Level.FINE,"Locate and remove config file of " + moduleInfo.getCodeNameBase ());                       
            for (; rerunWaitCount < 100 && !isModuleUninstalled (moduleInfo) ;rerunWaitCount++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            removeModuleFiles(moduleInfo); 
        }
    }
    
    private boolean isModuleUninstalled(Module module) {
        return (! module.isEnabled () &&
                (Main.getModuleSystem ().getManager ().get (module.getCodeNameBase()) == null) &&
                module.isValid ()
                );
    }

    private File locateControlFile (ModuleInfo m) {
        String configFile = "config" + '/' + "Modules" + '/' + m.getCodeNameBase ().replace ('.', '-') + ".xml"; // NOI18N
        return InstalledFileLocator.getDefault ().locate (configFile, m.getCodeNameBase (), false);
    }
    
    private void removeControlModuleFile (ModuleInfo m) throws IOException {
        File configFile = null;
        while ((configFile = locateControlFile (m)) != null) {
            if (configFile != null && configFile.exists ()) {
                err.log(Level.FINE, "Try delete the config File " + configFile);
                //FileUtil.toFileObject (configFile).delete ();
                configFile.delete();
            } else {
                err.log(Level.FINE,
                        "Warning: Config File " + configFile + " doesn\'t exist!");
            }
        }
    }
    
    private static File locateUpdateTracking (ModuleInfo m) {
        String fileNameToFind = TRACKING_FILE_NAME + '/' + m.getCodeNameBase ().replace ('.', '-') + ".xml"; // NOI18N
        return InstalledFileLocator.getDefault ().locate (fileNameToFind, m.getCodeNameBase (), false);
    }
    
    private boolean findUpdateTracking (ModuleInfo moduleInfo) {
        File updateTracking = locateUpdateTracking (moduleInfo);
        if (updateTracking != null && updateTracking.exists ()) {
            //err.log ("Find UPDATE_TRACKING: " + updateTracking + " found.");
            // check the write permission
            if (! updateTracking.getParentFile ().canWrite ()) {
                err.log(Level.FINE,
                        "Cannot delete module " + moduleInfo.getCodeName() +
                        " because no write permission to directory " +
                        updateTracking.getParent());
                return false;
            }
            return true;
        } else {
            err.log(Level.FINE,
                    "Cannot delete module " + moduleInfo.getCodeName() +
                    " because no update_tracking file found.");
            return false;
        }
    }
            
    private void removeModuleFiles (ModuleInfo m) throws IOException {
        err.log (Level.FINE, "Entry removing files of module " + m);
        File updateTracking = null;
        while ((updateTracking = locateUpdateTracking (m)) != null) {
            removeModuleFilesInCluster (m, updateTracking);
        }
        err.log (Level.FINE, "Exit removing files of module " + m);
    }
    
    private void removeModuleFilesInCluster (ModuleInfo moduleInfo, File updateTracking) throws IOException {
        err.log(Level.FINE, "Read update_tracking " + updateTracking + " file.");
        Set<String> moduleFiles = readModuleFiles (getModuleConfiguration (updateTracking));
        String configFile = "config" + '/' + "Modules" + '/' + moduleInfo.getCodeNameBase ().replace ('.', '-') + ".xml"; // NOI18N
        if (moduleFiles.contains (configFile)) {
            File file = InstalledFileLocator.getDefault ().locate (configFile, moduleInfo.getCodeNameBase (), false);
            assert file == null || ! file.exists () : "Config file " + configFile + " must be already removed.";
        }
        for (String fileName : moduleFiles) {
            if (fileName.equals (configFile)) {
                continue;
            }
            File file = InstalledFileLocator.getDefault ().locate (fileName, moduleInfo.getCodeNameBase (), false);
            if (file.equals (updateTracking)) {
                continue;
            }
            assert file.exists () : "File " + file + " exists.";
            if (file.exists ()) {
                err.log(Level.FINE, "File " + file + " is deleted.");
                FileLock lock = null;
                try {
                    FileObject fo = FileUtil.toFileObject (file);
                    lock = (fo != null) ? fo.lock() : null;
                    //assert fo != null || !file.exists() : file.getAbsolutePath();
                    file.delete();
                } catch (IOException ioe) {
                    assert false : "Waring: IOException " + ioe.getMessage () + " was caught. Propably file lock on the file.";
                    err.log(Level.FINE,
                            "Waring: IOException " + ioe.getMessage() +
                            " was caught. Propably file lock on the file.");
                    err.log(Level.FINE,
                            "Try call File.deleteOnExit() on " + file);
                    file.deleteOnExit ();
                } finally {
                    if (lock != null) {
                        lock.releaseLock();
                    }
                }
            }
        }
        FileObject trackingFo = FileUtil.toFileObject (updateTracking);
        FileLock lock = null;
        try {
        lock = (trackingFo != null) ? trackingFo.lock() : null;        
        updateTracking.delete ();
        } finally {
            if (lock != null) {
                lock.releaseLock();
            }
        }
        err.log(Level.FINE, "File " + updateTracking + " is deleted.");
    }
    
    private Node getModuleConfiguration (File moduleUpdateTracking) {
        Document document = null;
        InputStream is;
        try {
            is = new FileInputStream (moduleUpdateTracking);
            InputSource xmlInputSource = new InputSource (is);
            document = XMLUtil.parse (xmlInputSource, false, false, null, org.openide.xml.EntityCatalog.getDefault ());
            if (is != null) {
                is.close ();
            }
        } catch (SAXException saxe) {
            err.log(Level.WARNING, null, saxe);
            return null;
        } catch (IOException ioe) {
            err.log(Level.WARNING, null, ioe);
        }

        assert document.getDocumentElement () != null : "File " + moduleUpdateTracking + " must contain <module> element.";
        return getModuleElement (document.getDocumentElement ());
    }
    
    private Node getModuleElement (Element element) {
        Node lastElement = null;
        assert ELEMENT_MODULE.equals (element.getTagName ()) : "The root element is: " + ELEMENT_MODULE + " but was: " + element.getTagName ();
        NodeList listModuleVersions = element.getElementsByTagName (ELEMENT_VERSION);
        for (int i = 0; i < listModuleVersions.getLength (); i++) {
            lastElement = getModuleLastVersion (listModuleVersions.item (i));
            if (lastElement != null) {
                break;
            }
        }
        return lastElement;
    }
    
    private Node getModuleLastVersion (Node version) {
        Node attrLast = version.getAttributes ().getNamedItem (ATTR_LAST);
        assert attrLast != null : "ELEMENT_VERSION must contain ATTR_LAST attribute.";
        if (Boolean.valueOf (attrLast.getNodeValue ()).booleanValue ()) {
            return version;
        } else {
            return null;
        }
    }
    
    private Set<String> readModuleFiles (Node version) {
        Set<String> files = new HashSet<String> ();
        NodeList fileNodes = version.getChildNodes ();
        for (int i = 0; i < fileNodes.getLength (); i++) {
            if (fileNodes.item (i).hasAttributes ()) {
                NamedNodeMap map = fileNodes.item (i).getAttributes ();
                files.add (map.getNamedItem (ATTR_FILE_NAME).getNodeValue ());
                err.log(Level.FINE,
                        "Mark to delete: " +
                        map.getNamedItem(ATTR_FILE_NAME).getNodeValue());
            }
        }
        return files;
    }

    private class HackModuleListRefresher implements Runnable {
        public void run () {
            // XXX: the modules list should be delete automatically when config/Modules/module.xml is removed
            FileObject modulesRoot = FileUtil.getConfigFile ("Modules"); // NOI18N
            err.log(Level.FINE,
                    "It\'s a hack: Call refresh on " + modulesRoot +
                    " file object.");
            if (modulesRoot != null) {
                modulesRoot.refresh ();
            }
            // end of hack
        }
    }

}
