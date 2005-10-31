/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2005 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.adnode;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.adaptable.Adaptable;
import org.openide.nodes.Children;

/**
 *
 * @author Jaroslav Tulach
 */
final class ANode extends org.openide.nodes.AbstractNode 
implements ChangeListener {
    private Adaptable a;
    
    /** Creates a new instance of ANode */
    public ANode(Adaptable a) {
        super(Children.LEAF);
        
        this.a = a;
        a.addChangeListener(this);
    }

    public String getName() {
        org.netbeans.spi.adnode.Name n = (org.netbeans.spi.adnode.Name)a.lookup(org.netbeans.spi.adnode.Name.class);
        return n == null ? "" : n.getName();
    }

    public void stateChanged(ChangeEvent e) {
        fireNameChange(null, null);
    }
}
