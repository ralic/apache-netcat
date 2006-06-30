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

package org.netbeans.modules.tasklist.suggestions.settings;

import java.beans.*;
import java.util.ResourceBundle;
import java.awt.*;

import org.openide.util.NbBundle;
import org.openide.util.Utilities;

public class ManagerSettingsBeanInfo extends SimpleBeanInfo {

    // Bean descriptor //GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor(){
        BeanDescriptor beanDescriptor = new BeanDescriptor  ( ManagerSettings.class , null );
        beanDescriptor.setExpert ( true );//GEN-HEADEREND:BeanDescriptor
        
        // Here you can add code for customizing the BeanDescriptor.
        beanDescriptor.setDisplayName(NbBundle.getBundle(ManagerSettings.class).getString("BK0007"));
        beanDescriptor.setShortDescription(NbBundle.getBundle(ManagerSettings.class).getString("BK0008"));


        return beanDescriptor;         }//GEN-LAST:BeanDescriptor
    
    
    // Property identifiers //GEN-FIRST:Properties
    private static final int PROPERTY_editScanDelay = 0;
    private static final int PROPERTY_node = 1;
    private static final int PROPERTY_saveScanDelay = 2;
    private static final int PROPERTY_scanOnEdit = 3;
    private static final int PROPERTY_scanOnSave = 4;
    private static final int PROPERTY_scanOnShow = 5;
    private static final int PROPERTY_showScanDelay = 6;

    // Property array 
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor(){
        PropertyDescriptor[] properties = new PropertyDescriptor[7];
    
        try {
            properties[PROPERTY_editScanDelay] = new PropertyDescriptor ( "editScanDelay", ManagerSettings.class, "getEditScanDelay", "setEditScanDelay" );
            properties[PROPERTY_editScanDelay].setExpert ( true );
            properties[PROPERTY_node] = new PropertyDescriptor ( "node", ManagerSettings.class, "getNode", null );
            properties[PROPERTY_node].setHidden ( true );
            properties[PROPERTY_saveScanDelay] = new PropertyDescriptor ( "saveScanDelay", ManagerSettings.class, "getSaveScanDelay", "setSaveScanDelay" );
            properties[PROPERTY_saveScanDelay].setExpert ( true );
            properties[PROPERTY_scanOnEdit] = new PropertyDescriptor ( "scanOnEdit", ManagerSettings.class, "isScanOnEdit", null );
            properties[PROPERTY_scanOnEdit].setHidden ( true );
            properties[PROPERTY_scanOnSave] = new PropertyDescriptor ( "scanOnSave", ManagerSettings.class, "isScanOnSave", null );
            properties[PROPERTY_scanOnSave].setHidden ( true );
            properties[PROPERTY_scanOnShow] = new PropertyDescriptor ( "scanOnShow", ManagerSettings.class, "isScanOnShow", null );
            properties[PROPERTY_scanOnShow].setHidden ( true );
            properties[PROPERTY_showScanDelay] = new PropertyDescriptor ( "showScanDelay", ManagerSettings.class, "getShowScanDelay", "setShowScanDelay" );
            properties[PROPERTY_showScanDelay].setExpert ( true );
        }
        catch( IntrospectionException e) {}//GEN-HEADEREND:Properties
        
        ResourceBundle bundle = NbBundle.getBundle(ManagerSettingsBeanInfo.class);
        
        properties[PROPERTY_showScanDelay].setName(bundle.getString("BK0001"));
        properties[PROPERTY_showScanDelay].setShortDescription(bundle.getString("BK0002"));
        properties[PROPERTY_editScanDelay].setName(bundle.getString("BK0003"));
        properties[PROPERTY_editScanDelay].setShortDescription(bundle.getString("BK0004"));
        properties[PROPERTY_saveScanDelay].setName(bundle.getString("BK0005"));
        properties[PROPERTY_saveScanDelay].setShortDescription(bundle.getString("BK0006"));
        
        return properties;         }//GEN-LAST:Properties
    
    // EventSet identifiers//GEN-FIRST:Events

    // EventSet array
    /*lazy EventSetDescriptor*/
    private static EventSetDescriptor[] getEdescriptor(){
        EventSetDescriptor[] eventSets = new EventSetDescriptor[0];//GEN-HEADEREND:Events
        
        // Here you can add code for customizing the event sets array.
        
        return eventSets;         }//GEN-LAST:Events
    
    // Method identifiers //GEN-FIRST:Methods

    // Method array 
    private static MethodDescriptor[] methods = new MethodDescriptor[0];

    private static MethodDescriptor[] getMdescriptor(){
        return methods;
    }
//GEN-HEADEREND:Methods
        
        // Here you can add code for customizing the methods array.
        
  //GEN-LAST:Methods
    
    
    private static final int defaultPropertyIndex = -1;//GEN-BEGIN:Idx
    private static final int defaultEventIndex = -1;//GEN-END:Idx
    
    
 //GEN-FIRST:Superclass
    
    // Here you can add code for customizing the Superclass BeanInfo.
    
 //GEN-LAST:Superclass
    
    /**
     * Gets the bean's <code>BeanDescriptor</code>s.
     *
     * @return BeanDescriptor describing the editable
     * properties of this bean.  May return null if the
     * information should be obtained by automatic analysis.
     */
    public BeanDescriptor getBeanDescriptor() {
        return getBdescriptor();
    }
    
    /**
     * Gets the bean's <code>PropertyDescriptor</code>s.
     *
     * @return An array of PropertyDescriptors describing the editable
     * properties supported by this bean.  May return null if the
     * information should be obtained by automatic analysis.
     * <p>
     * If a property is indexed, then its entry in the result array will
     * belong to the IndexedPropertyDescriptor subclass of PropertyDescriptor.
     * A client of getPropertyDescriptors can use "instanceof" to check
     * if a given PropertyDescriptor is an IndexedPropertyDescriptor.
     */
    public PropertyDescriptor[] getPropertyDescriptors() {
        return getPdescriptor();
    }
    
    /**
     * Gets the bean's <code>EventSetDescriptor</code>s.
     *
     * @return  An array of EventSetDescriptors describing the kinds of
     * events fired by this bean.  May return null if the information
     * should be obtained by automatic analysis.
     */
    public EventSetDescriptor[] getEventSetDescriptors() {
        return getEdescriptor();
    }
    
    /**
     * Gets the bean's <code>MethodDescriptor</code>s.
     *
     * @return  An array of MethodDescriptors describing the methods
     * implemented by this bean.  May return null if the information
     * should be obtained by automatic analysis.
     */
    public MethodDescriptor[] getMethodDescriptors() {
        return getMdescriptor();
    }
    
    /**
     * A bean may have a "default" property that is the property that will
     * mostly commonly be initially chosen for update by human's who are
     * customizing the bean.
     * @return  Index of default property in the PropertyDescriptor array
     * 		returned by getPropertyDescriptors.
     * <P>	Returns -1 if there is no default property.
     */
    public int getDefaultPropertyIndex() {
        return defaultPropertyIndex;
    }
    
    /**
     * A bean may have a "default" event that is the event that will
     * mostly commonly be used by human's when using the bean.
     * @return Index of default event in the EventSetDescriptor array
     *		returned by getEventSetDescriptors.
     * <P>	Returns -1 if there is no default event.
     */
    public int getDefaultEventIndex() {
        return defaultEventIndex;
    }

    public Image getIcon(int iconKind) {
        return Utilities.loadImage("org/netbeans/modules/tasklist/suggestions/settings/setting.gif");
    }
}

