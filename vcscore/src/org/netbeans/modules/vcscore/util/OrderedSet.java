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

package org.netbeans.modules.vcscore.util;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;

/**
 *
 * @author  Martin Entlicher
 *
 * The set, that keeps the order of elements in the same order they were added.
 * Only add of collections of elements is supported in this set.
 */
public final class OrderedSet extends AbstractSet {

    /** Queue of collections of elements. */
    private java.util.LinkedList queue = new java.util.LinkedList ();
    /** Objects stored in this set. */
    Object[] objects = null;

    /** Creates new OrderedSet */
    public OrderedSet() {
    }
    
    public boolean add(Object obj) {
        queue.add (Collections.singleton(obj));
        return true;
    }

    /**
     * Adds all of the elements in the specified collection to this collection.
     */
    public boolean addAll(Collection coll) {
        queue.add (coll);
        return true;
    }
    
    
    private Object[] getObjects() {
        if (objects == null) {
            class Col2Enum implements org.openide.util.Enumerations.Processor {
                public Object process (Object obj, Collection toAdd) {
                    return Collections.enumeration((Collection) obj);
                }
            }
            
            Enumeration altered = org.openide.util.Enumerations.convert (
                Collections.enumeration (queue), new Col2Enum ()
            );
            Enumeration sequenced = org.openide.util.Enumerations.concat (altered);
            Enumeration result = org.openide.util.Enumerations.removeDuplicates (sequenced);
            ArrayList objectList = new ArrayList();
            for (int i = 0; result.hasMoreElements(); i++) {
                objectList.add(result.nextElement());
            }
            objects = objectList.toArray();
        }
        return objects;
    }
    
    /**
     * Returns an iterator over the elements contained in this collection.
     */
    public Iterator iterator() {
        final int size = getObjects().length;
        return new Iterator() {
            int i = 0;
            public boolean hasNext() {
                return i < size;
            }
            
            public Object next() {
                return objects[i++];
            }
            
            public void remove() {
                throw new UnsupportedOperationException("Remove is not supported.");
            }
        };
    }
    
    /**
     * Returns the number of elements in this collection.
     */
    public int size() {
        return getObjects().length;
    }
}
