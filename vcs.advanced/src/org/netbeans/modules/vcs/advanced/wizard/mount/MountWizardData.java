/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2001 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.vcs.advanced.wizard.mount;

import org.netbeans.modules.vcs.advanced.CommandLineVcsFileSystem;
import org.netbeans.modules.vcs.advanced.VcsCustomizer;

/**
 * The data set in the Generic VCS wizard.
 *
 * @author  Martin Entlicher
 */
public class MountWizardData {

    private String workingDir;
    private String[] mountPoints;
    private int refreshRate;
    
    private CommandLineVcsFileSystem fileSystem;
    private VcsCustomizer customizer;

    /** Creates new MountWizardData */
    public MountWizardData(Object instance) {
        if (instance instanceof CommandLineVcsFileSystem) {
            this.fileSystem = (CommandLineVcsFileSystem) instance;
            this.customizer = new VcsCustomizer();
            customizer.setObject(fileSystem);
        } else throw new IllegalArgumentException("Bad instance "+instance);
    }
    
    javax.swing.JPanel getProfilePanel() {
        return customizer.getConfigPanel();
    }

    javax.swing.JPanel getAdvancedPanel() {
        return customizer.getAdvancedPanel();
    }

    javax.swing.JPanel getEnvironmentPanel() {
        return customizer.getEnvironmentPanel();
    }

    /** Getter for property workingDir.
     * @return Value of property workingDir.
     */
    public String getWorkingDir() {
        return workingDir;
    }
    
    /** Setter for property workingDir.
     * @param workingDir New value of property workingDir.
     */
    public void setWorkingDir(String workingDir) {
        this.workingDir = workingDir;
    }
    
    public String[] getMountPoints () {
        return this.mountPoints;
    }
    
    public void setMountPoints (String[] mountPoints) {
        this.mountPoints = mountPoints;
    }
    
    /** Getter for property refreshRate.
     * @return Value of property refreshRate.
     */
    public int getRefreshRate() {
        return refreshRate;
    }
    
    /** Setter for property refreshRate.
     * @param refreshRate New value of property refreshRate.
     */
    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }
    
}
