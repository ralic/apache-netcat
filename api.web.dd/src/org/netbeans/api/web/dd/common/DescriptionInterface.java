/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2003 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.api.web.dd.common;
/**
 * Super interface for all DD elements having the description property/properties. 
 *
 *<p><b><font color="red"><em>Important note: Do not provide an implementation of this interface unless you are a DD API provider!</em></font></b>
 *</p>
 *
 * @deprecated Use the API for web module deployment descriptor in j2ee/ddapi module.
 * @author Milan Kuchtiak
 */
public interface DescriptionInterface {
    
    /**
     * Sets the description element for particular locale.<br>
     * If locale=null the method sets the description element without xml:lang attribute.<br>
     * If description=null method removes the description element for a specified locale.<br>
     *
     * @param locale string representing the locale - the value for xml:lang attribute e.g. "fr"
     * @param description value for description element
     */
    public void setDescription(String locale, String description) throws VersionNotSupportedException;
    
    /**
     * Sets the description element without xml:lang attribute.
     *
     * @param description value for description element
     */
    public void setDescription(String description);

    /**
     * Sets the multiple description elements.
     *
     * @param descriptions Map of descriptions in the form of [locale,description]
     */
    public void setAllDescriptions(java.util.Map descriptions) throws VersionNotSupportedException;
    
    /**
     * Returns the description element value for particular locale.<br>
     * If locale=null method returns description for default locale.
     *
     * @param locale string representing the locale - the value of xml:lang attribute e.g. "fr".
     * @return description element value or null if not specified for given locale
     */
    public String getDescription(String locale) throws VersionNotSupportedException;

    /**
     * Returns the description element value for default locale. 
     *
     * @return description element value or null if not specified for default locale
     */
    public String getDefaultDescription();

    /**
     * Returns all description elements in the form of <@link java.util.Map>. 
     *
     * @return map of all descriptions in the form of [locale:description]
     */
    public java.util.Map getAllDescriptions();
    
    /**
     * Removes the description element for particular locale.
     * If locale=null the method removes the description element for default locale.
     *
     * @param locale string representing the locale - the value of xml:lang attribute e.g. "fr"
     */
    public void removeDescriptionForLocale(String locale) throws VersionNotSupportedException;
    
    /**
     * Removes description element for default locale.
     */
    public void removeDescription();
    
    /**
     * Removes all description elements from DD element.
     */
    public void removeAllDescriptions();

}
