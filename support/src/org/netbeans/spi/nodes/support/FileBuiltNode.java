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
package org.netbeans.spi.nodes.support;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.queries.FileBuiltQuery;
import org.netbeans.api.queries.FileBuiltQuery.Status;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.RequestProcessor;
import org.openide.util.Utilities;

/**
 *
 * @author Jan Lahoda
 */
class FileBuiltNode extends FilterNode implements ChangeListener {
    
    private static final Image NEEDS_COMPILE = Utilities.loadImage("org/netbeans/modules/support/resources/needs-compile.gif");
    
    private Status status;
    
    /** Creates a new instance of FileBuiltNode */
    public FileBuiltNode(Node original) {
        super(original);
        
        FileObject file = (FileObject) original.getLookup().lookup(FileObject.class);
        
        if (file == null) {
            DataObject od = (DataObject) original.getLookup().lookup(DataObject.class);
            
            if (od != null) {
                file = od.getPrimaryFile();
            }
        }
        
        if (file != null) {
            status = FileBuiltQuery.getStatus(file);
        }
        
        if (status != null) {
            status.addChangeListener(this);
        }
    }

    public void stateChanged(ChangeEvent e) {
        fireIconChange();
        fireOpenedIconChange();
    }

    public Image getIcon(int type) {
        Image i = super.getIcon(type);
        
        return enhanceIcon(i);
    }

    public Image getOpenedIcon(int type) {
        Image i = super.getOpenedIcon(type);
        
        return enhanceIcon(i);
    }

    private Image enhanceIcon(Image i) {
        if (status == null || status.isBuilt())
            return i;
        
        return Utilities.mergeImages(i, NEEDS_COMPILE, 16, 0);
    }
    
    private static List queue = new ArrayList();
    
    private static void enqueue(FileBuiltNode node) {
        synchronized (queue) {
            queue.add(node);
            
            WORKER_TASK.schedule(50);
        }
    }
    
    private static RequestProcessor WORKER = new RequestProcessor("Compile Badge Worker", 1);
    private static RequestProcessor.Task WORKER_TASK = WORKER.create(new Runnable() {
        public void run() {
            synchronized (queue) {
                while (!queue.isEmpty()) {
                    FileBuiltNode node = (FileBuiltNode) queue.remove(0);
                    
                    node.fireIconChange();
                    node.fireOpenedIconChange();
                }
            }
        }
    });
    
}
