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
package org.sample.registry.model.impl;

import java.util.List;
import org.netbeans.modules.xml.xam.dom.AbstractDocumentComponent;
import org.sample.registry.model.Entries;
import org.sample.registry.model.KnownTypes;
import org.sample.registry.model.Registry;
import org.sample.registry.model.RegistryComponent;
import org.sample.registry.model.RegistryComponentFactory;
import org.sample.registry.model.RegistryVisitor;
import org.sample.registry.model.Service;
import org.sample.registry.model.ServiceProvider;
import org.sample.registry.model.ServiceType;
import org.w3c.dom.Element;

public class RegistryComponentFactoryImpl implements RegistryComponentFactory {
    private RegistryModelImpl model;
    
    public RegistryComponentFactoryImpl(RegistryModelImpl model) {
        this.model = model;
    }

    public RegistryComponent create(Element element, RegistryComponent context) {
        return new CreateVisitor().create(element, context);
    }
    
    public ServiceType createServiceType() {
        return new ServiceTypeImpl(model);
    }

    public ServiceProvider createServiceProvider() {
        return new ServiceProviderImpl(model);
    }

    public Service createService() {
        return new ServiceImpl(model);
    }

    public Registry createRegistry() {
        return new RegistryImpl(model);
    }

    public static class CreateVisitor extends RegistryVisitor.Default {
        Element element;
        RegistryComponent created;
        
        RegistryComponent create(Element element, RegistryComponent context) {
            this.element = element;
            context.accept(this);
            return created;
        }
        
        private boolean isElementQName(RegistryQNames q) {
            return areSameQName(q, element);
        }
        
        private boolean areSameQName(RegistryQNames q, Element e) {
            return q.getQName().equals(AbstractDocumentComponent.getQName(e));
        }

        public void visit(Registry context) {
            if (isElementQName(RegistryQNames.ENTRIES)) {
                created = new EntriesImpl((RegistryModelImpl) context.getModel(), element);
            } else if (isElementQName(RegistryQNames.KNOWN_TYPES)) {
                created = new KnownTypesImpl((RegistryModelImpl) context.getModel(), element);
            }
        }
        
        public void visit(Entries context) {
            if (isElementQName(RegistryQNames.SERVICE)) {
                new ServiceImpl((RegistryModelImpl)context.getModel(), element);
            }
        }
        
        public void visit(KnownTypes context) {
            if (isElementQName(RegistryQNames.KNOWN_TYPES)) {
                new ServiceImpl((RegistryModelImpl)context.getModel(), element);
            }
        }
        
        public void visit(Service context) {
            if (isElementQName(RegistryQNames.SERVICE_PROVIDER)) {
                new ServiceProviderImpl((RegistryModelImpl)context.getModel(), element);
            }
        }
        
    }
}
