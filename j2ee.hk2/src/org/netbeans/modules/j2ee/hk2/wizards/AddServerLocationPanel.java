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
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.j2ee.hk2.wizards;

import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import java.awt.Component;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Ludo
 */
public class AddServerLocationPanel implements WizardDescriptor.Panel, ChangeListener {
    
    private final static String PROP_ERROR_MESSAGE = "WizardPanel_errorMessage"; // NOI18   
    
    private Hk2InstantiatingIterator instantiatingIterator;
    private AddServerLocationVisualPanel component;
    private WizardDescriptor wizard;
    private transient Set <ChangeListener>listeners = new HashSet<ChangeListener>(1);

    /**
     * 
     * @param instantiatingIterator 
     */
    public AddServerLocationPanel(Hk2InstantiatingIterator instantiatingIterator){
        this.instantiatingIterator = instantiatingIterator;
    }
    
    /**
     * 
     * @param ev 
     */
    public void stateChanged(ChangeEvent ev) {
        fireChangeEvent(ev);
    }
    
    private void fireChangeEvent(ChangeEvent ev) {
        Iterator it;
        synchronized (listeners) {
            it = new HashSet<ChangeListener>(listeners).iterator();
        }
        while (it.hasNext()) {
            ((ChangeListener)it.next()).stateChanged(ev);
        }
    }
    
    /**
     * 
     * @return 
     */
    public Component getComponent() {
        if (component == null) {
            component = new AddServerLocationVisualPanel();
            component.addChangeListener(this);
        }
        return component;
    }
    
    /**
     * 
     * @return 
     */
    public HelpCtx getHelp() {
        return new HelpCtx("j2eeplugins_registering_app_server_hk2_location"); //NOI18N
    }
    
    /**
     * 
     * @return 
     */
    public boolean isValid() {
        AddServerLocationVisualPanel component = (AddServerLocationVisualPanel) getComponent();
        String locationStr = component.getHk2HomeLocation();
        locationStr = (locationStr != null) ? locationStr.trim() : null;
        if(locationStr == null || locationStr.length() ==0) {
            wizard.putProperty(PROP_ERROR_MESSAGE, "Install location cannot be empty.");
            return false;
        }
            
        File installDir = new File(locationStr);
        if(!installDir.exists()) {
            wizard.putProperty(PROP_ERROR_MESSAGE, locationStr + " does not exist.");
            return false;
        } else if(!isValidV3Install(installDir)) {
            wizard.putProperty(PROP_ERROR_MESSAGE, locationStr + " is not a valid V3 install.");
            return false;
        } else {
            String statusText = component.getStatusText();
            if(statusText != null && statusText.length() > 0) {
                wizard.putProperty(PROP_ERROR_MESSAGE, statusText);
                return false;
            }
        }
        
        wizard.putProperty(PROP_ERROR_MESSAGE, null);
        instantiatingIterator.setHk2HomeLocation(locationStr);
        return true;
    }
    
    private boolean isValidV3Install(File installDir) {
        File glassfishRef = new File(installDir, "lib" + File.separator + "glassfish-10.0-SNAPSHOT.jar");
        if(!glassfishRef.exists()) {
            System.out.println(glassfishRef.getPath() + " does not exist.");
            return false;
        }
        
        File domainRef = new File(installDir, "domains" + File.separator + "domain1");
        if(!domainRef.exists()) {
            System.out.println(domainRef.getPath() + " does not exist.");
            return false;
        }
        
        return true;
    }

    /**
     * 
     * @param l 
     */
    public void removeChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.remove(l);
        }
    }
    
    /**
     * 
     * @param l 
     */
    public void addChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.add(l);
        }
    }
    
    /**
     * 
     * @param settings 
     */
    public void readSettings(Object settings) {
        if (wizard == null)
            wizard = (WizardDescriptor)settings;
    }
    
    /**
     * 
     * @param settings 
     */
    public void storeSettings(Object settings) {
    }
}