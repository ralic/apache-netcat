/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2001 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.vcscore.versioning.impl;

import org.openide.nodes.Children;
import org.openide.nodes.Node;

import org.netbeans.modules.vcscore.versioning.VcsFileObject;

/**
 *
 * @author Martin Entlicher
 */
final class FolderChildren extends Children.Keys {

    private AbstractVcsFolder folder;
    
    /** Creates new FolderChildren */
    public FolderChildren(AbstractVcsFolder folder) {
        this.folder = folder;
    }

    protected Node[] createNodes(Object key) {
        VcsFileObject fo = (VcsFileObject) key;
        Node clone;
        if (fo instanceof AbstractVcsFolder) {
            clone = ((AbstractVcsFolder) fo).createClonedNodeDelegate();
        } else {
            clone = fo.getNodeDelegate().cloneNode();
        }
        return new Node[] { clone };
    }
    
    /** Initializes the children.
     */
    protected void addNotify() {
        initialize(true);
    }
    
    protected void removeNotify() {
        setKeys(java.util.Collections.EMPTY_SET);
    }
    
    void reInitialize() {
        initialize(true);
    }
    
    private void initialize(boolean force) {
        VcsFileObject[] ch = folder.getChildren();
        setKeys(ch);
    }
    
}
