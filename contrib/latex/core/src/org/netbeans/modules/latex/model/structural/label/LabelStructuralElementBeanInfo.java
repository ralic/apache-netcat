/*
 *                          Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License Version
 * 1.0 (the "License"). You may not use this file except in compliance with
 * the License. A copy of the License is available at http://www.sun.com/
 *
 * The Original Code is the LaTeX module.
 * The Initial Developer of the Original Code is Jan Lahoda.
 * Portions created by Jan Lahoda_ are Copyright (C) 2002,2003.
 * All Rights Reserved.
 *
 * Contributor(s): Jan Lahoda.
 */
package org.netbeans.modules.latex.model.structural.label;

import java.beans.*;

/**
 * @author Jan Lahoda
 */
public class LabelStructuralElementBeanInfo extends SimpleBeanInfo {
    
    // Bean descriptor //GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor(){
        BeanDescriptor beanDescriptor = new BeanDescriptor  ( LabelStructuralElement.class , null );
        beanDescriptor.setShortDescription ( org.openide.util.NbBundle.getBundle(LabelStructuralElement.class).getString("SD_Element") );//GEN-HEADEREND:BeanDescriptor
        
        // Here you can add code for customizing the BeanDescriptor.
        
        return beanDescriptor;         }//GEN-LAST:BeanDescriptor
    
    
    // Property identifiers //GEN-FIRST:Properties
    private static final int PROPERTY_caption = 0;
    private static final int PROPERTY_endingPosition = 1;
    private static final int PROPERTY_helpCtx = 2;
    private static final int PROPERTY_label = 3;
    private static final int PROPERTY_name = 4;
    private static final int PROPERTY_node = 5;
    private static final int PROPERTY_priority = 6;
    private static final int PROPERTY_startingPosition = 7;
    private static final int PROPERTY_subElements = 8;

    // Property array 
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor(){
        PropertyDescriptor[] properties = new PropertyDescriptor[9];
    
        try {
            properties[PROPERTY_caption] = new PropertyDescriptor ( "caption", LabelStructuralElement.class, "getCaption", null );
            properties[PROPERTY_caption].setDisplayName ( org.openide.util.NbBundle.getBundle(LabelStructuralElement.class).getString("LBL_Caption") );
            properties[PROPERTY_caption].setShortDescription ( org.openide.util.NbBundle.getBundle(LabelStructuralElement.class).getString("SD_Caption") );
            properties[PROPERTY_endingPosition] = new PropertyDescriptor ( "endingPosition", LabelStructuralElement.class, "getEndingPosition", null );
            properties[PROPERTY_endingPosition].setHidden ( true );
            properties[PROPERTY_helpCtx] = new PropertyDescriptor ( "helpCtx", LabelStructuralElement.class, "getHelpCtx", null );
            properties[PROPERTY_helpCtx].setHidden ( true );
            properties[PROPERTY_label] = new PropertyDescriptor ( "label", LabelStructuralElement.class, "getLabel", null );
            properties[PROPERTY_label].setDisplayName ( org.openide.util.NbBundle.getBundle(LabelStructuralElement.class).getString("LBL_Label") );
            properties[PROPERTY_label].setShortDescription ( org.openide.util.NbBundle.getBundle(LabelStructuralElement.class).getString("SD_Label") );
            properties[PROPERTY_name] = new PropertyDescriptor ( "name", LabelStructuralElement.class, "getName", null );
            properties[PROPERTY_name].setHidden ( true );
            properties[PROPERTY_node] = new PropertyDescriptor ( "node", LabelStructuralElement.class, "getNode", null );
            properties[PROPERTY_node].setHidden ( true );
            properties[PROPERTY_priority] = new PropertyDescriptor ( "priority", LabelStructuralElement.class, "getPriority", null );
            properties[PROPERTY_priority].setHidden ( true );
            properties[PROPERTY_startingPosition] = new PropertyDescriptor ( "startingPosition", LabelStructuralElement.class, "getStartingPosition", null );
            properties[PROPERTY_startingPosition].setHidden ( true );
            properties[PROPERTY_subElements] = new PropertyDescriptor ( "subElements", LabelStructuralElement.class, "getSubElements", null );
            properties[PROPERTY_subElements].setHidden ( true );
        }
        catch( IntrospectionException e) {}//GEN-HEADEREND:Properties
        
        // Here you can add code for customizing the properties array.
        
        return properties;         }//GEN-LAST:Properties
    
    // Event set information will be obtained from introspection.//GEN-FIRST:Events
    private static EventSetDescriptor[] eventSets = null;
    private static EventSetDescriptor[] getEdescriptor(){//GEN-HEADEREND:Events
        
        // Here you can add code for customizing the event sets array.
        
        return eventSets;     } //GEN-LAST:Events
    
    // Method information will be obtained from introspection.//GEN-FIRST:Methods
    private static MethodDescriptor[] methods = null;
    private static MethodDescriptor[] getMdescriptor(){//GEN-HEADEREND:Methods
        
        // Here you can add code for customizing the methods array.
        
        return methods;     } //GEN-LAST:Methods
    
    
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
}

