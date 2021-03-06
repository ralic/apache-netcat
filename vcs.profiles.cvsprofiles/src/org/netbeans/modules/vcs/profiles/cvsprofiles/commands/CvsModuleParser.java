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

package org.netbeans.modules.vcs.profiles.cvsprofiles.commands;

import java.util.*;

/**
 *
 * @author  Martin Entlicher
 * @version
 */
public class CvsModuleParser extends Object {

    private static final String PATHSEP = "/";

    /**
     * The hash table of modules by name.
     * Keys are module names.
     * Values are Vectors of: - Boolean whether it is an alias,
     *                        - if is alias the set of alias values,
     *                        - if not alias working directory, repository directory and possibly set of files.
     */
    private Hashtable modules = new Hashtable();
    
    /**
     * Table of pairs "Work path", "Rep. path"
     */
    private Hashtable dirLocations = new Hashtable();
    /**
     * Table of pairs "Work path/file", "Rep. path/file"
     */
    private Hashtable fileLocations = new Hashtable();

    private String convertLastRepPath = "";
    private String convertLastWorkPath = "";
    private boolean convertFiles = false;

    /** Creates new CvsModuleParser */
    public CvsModuleParser() {
    }

    /**
     * Add the module definition.
     */
    public void addModule(String moduleDef) {
        int index = moduleDef.indexOf(' ');
        if (index < 0) return;
        int len = moduleDef.length();
        String moduleName = moduleDef.substring(0, index);
        if (modules.containsKey(moduleName)) return; // The module has duplicate definition => ignoring
        String moduleDir = moduleName;
        boolean alias = false;
        while(true) {
            while(index < len && Character.isWhitespace(moduleDef.charAt(index))) index++;
            if (index >= len) return;
            if (moduleDef.regionMatches(index, "-a", 0, "-a".length())) {
                alias = true;
                index += "-a".length();
            }
            if (moduleDef.regionMatches(index, "-d", 0, "-d".length())) {
                index += "-d".length();
                while(index < len && Character.isWhitespace(moduleDef.charAt(index))) index++;
                if (index >= len) return;
                int index2 = moduleDef.indexOf(' ', index);
                if (index2 < 0) return;
                moduleDir = moduleDef.substring(index, index2);
                index = index2;
            }
            if (moduleDef.charAt(index) == '-') { // an other option
                index += 2;
                while(index < len && Character.isWhitespace(moduleDef.charAt(index))) index++;
                if (index >= len) return;
                int index2 = moduleDef.indexOf(' ', index);
                if (index2 < 0) return;
                index = index2;
            } else break;
        }
        Vector module = new Vector();
        module.add(moduleDef.trim());
        module.add(alias ? Boolean.TRUE : Boolean.FALSE);
        modules.put(moduleName, module);
        if (alias) {
            while(true) {
                while(index < len && Character.isWhitespace(moduleDef.charAt(index))) index++;
                if (index >= len) break;
                int index2 = moduleDef.indexOf(' ', index);
                if (index2 < 0) index2 = len;
                String file = moduleDef.substring(index, index2);
                module.add(file);
                index = index2;
            }
        } else {
            while(index < len && Character.isWhitespace(moduleDef.charAt(index))) index++;
            int index2 = moduleDef.indexOf(' ', index);
            if (index2 < 0) index2 = len;
            int index3 = moduleDef.indexOf('&', index+1);
            if (index3 >= 0 && index3 < index2) index2 = index3;
            String repDir = moduleDef.substring(index, index2);
            /*
            if (repDir.length() > 0 && repDir.charAt(0) == '&') { // ampersand module
                repDir = repDir.substring(1);
            }
            */
            module.add(moduleDir);
            module.add(repDir);
            index = index2;
            boolean fileDefined = false;
            while(true) {
                while(index < len && Character.isWhitespace(moduleDef.charAt(index))) index++;
                if (index >= len) break;
                index2 = moduleDef.indexOf(' ', index);
                if (index2 < 0) index2 = len;
                index3 = moduleDef.indexOf('&', index+1);
                if (index3 > 0 && index3 < index2) {
                    index2 = index3;
                }
                String file = moduleDef.substring(index, index2);
                if (file.length() > 0 && file.charAt(0) == '&') {
                    dirLocations.put(moduleName+PATHSEP+file.substring(1), file); // file = &module_name
                } else {
                    fileDefined = true;
                    fileLocations.put(moduleDir+PATHSEP+file, repDir+PATHSEP+file);
                }
                module.add(file);
                index = index2;
            }
            if (!fileDefined) {
		if (repDir.length() > 0 && repDir.charAt(0) == '&') {
		    dirLocations.put(moduleDir+PATHSEP+repDir.substring(1), repDir);
		} else {
		    dirLocations.put(moduleDir, repDir);
		}
	    }
        }
    }
    
    /**
     * Set proper values when there are symbolic module links (ampersand modules).
     */
    public void resolveModuleLinks() {
        for(Enumeration enum = dirLocations.keys(); enum.hasMoreElements(); ) {
            String work = (String) enum.nextElement();
            String rep = (String) dirLocations.get(work);
            if (rep.length() > 0 && rep.charAt(0) == '&') { // resolve module link
                String moduleName = rep.substring(1);
                boolean match = false;
                for(Enumeration enum2 = modules.keys(); enum2.hasMoreElements(); ) {
                    String moduleName2 = (String) enum2.nextElement();
                    if (moduleName2.equals(moduleName)) {
                        match = true;
                        Vector module = (Vector) modules.get(moduleName2);
                        Boolean alias = (Boolean) module.get(1);
                        int n = module.size();
			dirLocations.remove(work);
                        if (alias.booleanValue()) {
                            for(int i = 2; i < n; i++) {
                                String moduleRep = (String) module.get(i);
                                dirLocations.put(work+PATHSEP+moduleRep, moduleRep);
                            }
                        } else {
                            boolean fileDefined = false;
                            String moduleDir = (String) module.get(2);
                            String moduleRep = (String) module.get(3);
                            for(int i = 3; i < n; i++) {
                                String file = (String) module.get(i);
                                if (file.charAt(0) == '&') {
                                    dirLocations.put(work+/*PATHSEP+moduleDir+*/PATHSEP+file.substring(1), file);
                                } else {
                                    fileDefined = true;
                                    fileLocations.put(work/*+PATHSEP+moduleDir*/+PATHSEP+file, moduleRep+PATHSEP+file);
                                }
                            }
                            if (!fileDefined) {
                                if (moduleRep.charAt(0) == '&') {
                                    dirLocations.put(work+/*PATHSEP+moduleDir+*/PATHSEP+moduleRep.substring(1), moduleRep);
                                } else {
                                    dirLocations.put(work+/*PATHSEP+moduleDir+*/PATHSEP+moduleRep, moduleRep);
                                }
                            }
                        }
                        //dirLocations.remove(work);
                        break;
                    }
                }
                if (!match) { // module is not defined => considering as a directory
                    dirLocations.put(work/*+PATHSEP+moduleName*/, moduleName);
                    //dirLocations.remove(work);
                }
                enum = dirLocations.keys(); // dirLocations chaned => have to recreate Enumeration
            }
        }
    }

    public Vector getModuleNames() {
        Vector moduleNames = new Vector();
        Enumeration enum = modules.keys();
        while(enum.hasMoreElements()) {
            moduleNames.add(enum.nextElement());
        }
        return moduleNames;
    }

    public String getModuleDef(String name) {
        Vector module = (Vector) modules.get(name);
        if (module == null) return null;
        return (String) module.get(0);
    }

    /**
     * Convert the repository path to working paths based on module definitions.
     * @return the working paths or null when the conversion can not be done.
     */
    public String[] convertRepPathToWorking(String repPath, String fileName, boolean fileDependent[]) {
        Vector workings = new Vector();
        fileDependent[0] = false;
        for(Enumeration enum = dirLocations.keys(); enum.hasMoreElements(); ) {
            String work = (String) enum.nextElement();
            String rep = (String) dirLocations.get(work);
            if (repPath.regionMatches(0, rep, 0, rep.length())) {
                workings.add(work+repPath.substring(rep.length()));
            }
        }
        String filePath = repPath+PATHSEP+fileName;
        for(Enumeration enum = fileLocations.keys(); enum.hasMoreElements(); ) {
            String work = (String) enum.nextElement();
            String rep = (String) fileLocations.get(work);
            if (rep.equals(filePath)) {
                int index = work.lastIndexOf(PATHSEP);
                if (index > 0) work = work.substring(0, index);
                workings.add(new String(work));
            }
            fileDependent[0] = true;
        }
        if (workings.isEmpty()) return null;
        else return (String[]) workings.toArray(new String[0]);
    }

}
