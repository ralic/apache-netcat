/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2002 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.assistant.parsing;

import org.xml.sax.*;

/*
 * File:           AssistantHandler.java
 * Date:           October 22, 2002  5:15 PM
 *
 * @author  Richard Gregor
 * @version generated by NetBeans XML module
 */
public interface AssistantHandler {
    
    /**
     * A data element event handling method.
     * @param data value or null
     * @param meta attributes
     *
     */
    public void handle_item(final java.lang.String data, final AttributeList meta) throws SAXException;
    
    /**
     * A container element start event handling method.
     * @param meta attributes
     *
     */
    public void start_section(final AttributeList meta) throws SAXException;
    
    /**
     * A container element end event handling method.
     *
     */
    public void end_section() throws SAXException;
    
    /**
     * A container element start event handling method.
     * @param meta attributes
     *
     */
    public void start_assistant(final AttributeList meta) throws SAXException;
    
    /**
     * A container element end event handling method.
     *
     */
    public void end_assistant() throws SAXException;
    
    /**
     * A data element event handling method.
     * @param data value or null
     * @param meta attributes
     *
     */
    public void handle_name(final java.lang.String data, final AttributeList meta) throws SAXException;
    
    /**
     * A container element start event handling method.
     * @param meta attributes
     *
     */
    public void start_id(final AttributeList meta) throws SAXException;
    
    /**
     * A container element end event handling method.
     *
     */
    public void end_id() throws SAXException;
    
}

