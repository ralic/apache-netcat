/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2000 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.vcscore.versioning;

import javax.swing.event.*;
import java.util.*;

import org.openide.nodes.*;
import org.openide.util.*;

/**
 *
 * @author  Martin Entlicher
 */
public abstract class RevisionChildren extends Children.Keys implements ChangeListener, java.io.Serializable {

    protected static final Object WAIT_KEY = new Object();
    
    private String acceptField = "";
    private int numAcceptDots = 1;
    private RevisionList list = null;
    private ChangeListener changeListenerToList;
    private ArrayList notificationListeners = new ArrayList(2);
    private Runnable initProcess = null;
    
    /** Creates new RevisionChildren 
     * @param list the RevisionList, can be null
     */
    public RevisionChildren(RevisionList list) {
        changeListenerToList = WeakListener.change (this, this);
        if (list != null) list.addChangeListener(changeListenerToList);
        this.list = list;
        stateChanged (null);
    }
    
    public RevisionList getList() {
        return list;
    }
    
    /**
     * Set a new revision list.
     */
    public void setList(RevisionList newList) {
        if (list != null) list.removeChangeListener(changeListenerToList);
        changeListenerToList = WeakListener.change (this, this);
        newList.addChangeListener(changeListenerToList);
        this.list = newList;
        stateChanged (null);
    }
    
    public void addNotificationListener(NotificationListener l) {
        synchronized (notificationListeners) {
            notificationListeners.add(l);
        }
    }
    
    public void removeNotificationListener(NotificationListener l) {
        synchronized (notificationListeners) {
            notificationListeners.remove(l);
        }
    }
    
    public void setInitProcess(Runnable initProcess) {
        this.initProcess = initProcess;
    }
    
    protected void addNotify() {
        if (list == null && initProcess != null) {
            org.openide.util.RequestProcessor.postRequest(initProcess);
            initProcess = null;
        }
        org.openide.util.RequestProcessor.postRequest(new Runnable() {
            public void run() {
                ArrayList notifList;
                synchronized (notificationListeners) {
                    notifList = new ArrayList(notificationListeners);
                }
                for (int i = notifList.size() - 1; i >= 0; i--) {
                    NotificationListener l = (NotificationListener) notifList.get(i);
                    l.notifyAdded();
                }
            }
        });
        super.addNotify();
    }
    
    protected void removeNotify() {
        org.openide.util.RequestProcessor.postRequest(new Runnable() {
            public void run() {
                ArrayList notifList;
                synchronized (notificationListeners) {
                    notifList = new ArrayList(notificationListeners);
                }
                for (int i = notifList.size() - 1; i >= 0; i--) {
                    NotificationListener l = (NotificationListener) notifList.get(i);
                    l.notifyRemoved();
                }
            }
        });
        super.removeNotify();
    }
    
    private void setAcceptField(String acceptField) {
        this.acceptField = acceptField;
    }
    
    private void setNumAcceptDots(int dots) {
        numAcceptDots = dots;
    }
    
    protected abstract boolean accept(RevisionItem item);
    
    protected abstract RevisionChildren getChildrenFor(RevisionItem item);
    
    protected Node createWaitingNode() {
        AbstractNode n = new AbstractNode(Children.LEAF);
        n.setName(NbBundle.getMessage(RevisionChildren.class, "WaitNodeTooltip"));
        n.setIconBase("/org/netbeans/modules/vcscore/versioning/wait");
        return n;
    }
    
    protected Node[] createNodes(Object key) {
        if (WAIT_KEY.equals(key)) {
            return new Node[] { createWaitingNode() };
        }
        Node[] nodes; //new Node[] { Node.EMPTY };
        RevisionItem item = (RevisionItem) key;
        //System.out.println("createNodes("+item.getRevision()+")");
        if (accept(item)) {
            //System.out.println("isRevision = "+(item.isRevision() && !item.isBranch()));
            //if (item.isRevision() && !item.isBranch()) {
            Node newNode;
            if (!list.containsSubRevisions(item.getRevision()) && !item.isBranch()) {
                newNode = list.getNodeDelegate(item, null);
            } else {
                RevisionChildren children = getChildrenFor(item);
                newNode = list.getNodeDelegate(item, children);
            }
            nodes = new Node[] { newNode };
        } else {
            nodes = new Node[0];
        }
        //if (nodes.length > 0) System.out.println("return node = "+nodes[0]);
        return nodes;
    }
    
    public synchronized void stateChanged(ChangeEvent evt) {
        //System.out.println("RevisionChildren.stateChanged("+evt+")");
        if (list == null) {
            setKeys(Collections.singleton(WAIT_KEY));
        } else {
            setKeys(list /*(Collection) evt.getSource()*/);
        }
        
    }
    
    public static interface NotificationListener {
        public void notifyAdded();
        public void notifyRemoved();
    }

}