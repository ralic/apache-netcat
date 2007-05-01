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

package org.netbeans.modules.wsdlextensions.ldap.impl;

import org.netbeans.modules.xml.wsdl.model.WSDLModel;
import org.netbeans.modules.wsdlextensions.ldap.LDAPAddress;
import org.netbeans.modules.wsdlextensions.ldap.LDAPComponent;
import org.netbeans.modules.wsdlextensions.ldap.LDAPQName;
import org.w3c.dom.Element;

/**
 */
public class LDAPAddressImpl extends LDAPComponentImpl implements LDAPAddress {
    public LDAPAddressImpl(WSDLModel model, Element e) {
        super(model, e);
    }
    
    public LDAPAddressImpl(WSDLModel model){
        this(model, createPrefixedElement(LDAPQName.ADDRESS.getQName(), model));
    }
    
    public void accept(LDAPComponent.Visitor visitor) {
        visitor.visit(this);
    }

    public void setLocation(String loc) {
        setAttribute(LDAPAddress.LDAP_LOCATION_PROPERTY, LDAPAttribute.LDAP_LOCATION_PROPERTY, loc);
    }

    public String getLocation() {
        return getAttribute(LDAPAttribute.LDAP_LOCATION_PROPERTY);
    }
}
