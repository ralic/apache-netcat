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
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.tasklist.usertasks.model;

import java.util.Iterator;
import org.netbeans.modules.tasklist.usertasks.*;
import org.netbeans.modules.tasklist.usertasks.util.ObjectList;
import org.netbeans.modules.tasklist.usertasks.util.ObjectListEvent;

/**
 * List of UserTasks
 *
 * @author tl
 */
public class UserTaskObjectList extends ObjectList<UserTask> {
    private Object parent;

    /**
     * Creates a new instance of UserTaskObjectList
     *
     * @param parent parent for all tasks in this collection. UserTask or
     * UserTaskList
     */
    public UserTaskObjectList(Object parent) {
        assert parent instanceof UserTask || parent instanceof UserTaskList;
        this.parent = parent;
    }
    
    /**
     * Returns the user task with the specified index
     *
     * TODO: use get(int) instead
     * 
     * @param index index of the task
     * @see #get(int)
     */
    public UserTask getUserTask(int index) {
        return (UserTask) get(index);
    }
    
    public void add(int index, UserTask element) {
        UserTask ut = (UserTask) element;
        if (ut.getParent() != null)
            ut.getParent().getSubtasks().remove(ut);
        else if (ut.getList() != null)
            ut.getList().getSubtasks().remove(ut);
        if (parent instanceof UserTask) {
            ut.setParent((UserTask) parent);
            ut.setList(((UserTask) parent).getList());
        } else {
            ut.setParent(null);
            ut.setList((UserTaskList) parent);
        }
        super.add(index, element);
    }
    
    public UserTask remove(int index) {
        UserTask element = super.remove(index);
        ((UserTask) element).setParent(null);
        ((UserTask) element).setList(null);
        return element;
    }    
    
    /**
     * Removes completed tasks (recursively).
     */
    public void purgeCompletedItems() {
        if (size() == 0)
            return;

        Iterator it = iterator();
        while (it.hasNext()) {
            UserTask task = (UserTask) it.next();
            if (task.isDone())
                it.remove();
            else
                task.getSubtasks().purgeCompletedItems();
        }
    }

    @Override
    protected void fireEvent(ObjectListEvent e) {
        UserTaskList list;
        if (parent instanceof UserTaskList)
            list = (UserTaskList) parent;
        else
            list = ((UserTask) parent).getList();
        super.fireEvent(e);
        list.fireEvent(e);
    }    
}
