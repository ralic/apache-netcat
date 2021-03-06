/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Nokia. Portions Copyright 2003 Nokia.
 * All Rights Reserved.

If you wish your version of this file to be governed by only the CDDL
or only the GPL Version 2, indicate your decision by adding
"[Contributor] elects to include this software in this distribution
under the [CDDL or GPL Version 2] license." If you do not indicate a
single choice of license, a recipient has the option to distribute
your version of this file under either the CDDL, the GPL Version 2 or
to extend the choice of license to its licensees as provided above.
However, if you add GPL Version 2 code and therefore, elected the GPL
Version 2 license, then the option applies only if the new code is
made subject to such option by the copyright holder.
 */

package org.netbeans.modules.enode.test;

import org.netbeans.spi.enode.LookupContentFactory;
import org.openide.nodes.Node;

/**
 * A simple implementation of a factory that can be registered
 * in the xml layer to produce object for ExtensibleNode's lookup.
 * @author David Strupl
 */
public class C1Factory implements LookupContentFactory {

    /** Creates a new instance of C1Factory */
    public C1Factory() {
    }

    /**
     * Implementing interface LookupContentFactory.
     */
    public Object create(Node n) {
        return new MONodeEnhancerImpl(n);
    }

    /**
     * Implementing interface LookupContentFactory.
     */ 
    public org.openide.util.Lookup createLookup(Node n) {
        return null;
    }
    
}
