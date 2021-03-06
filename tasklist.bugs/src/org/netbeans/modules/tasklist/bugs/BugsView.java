/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */

package org.netbeans.modules.tasklist.bugs;

import java.util.Date;


import org.netbeans.modules.tasklist.core.*;

import org.openide.util.NbBundle;
import org.openide.util.Utilities;
import org.openide.util.actions.SystemAction;
import org.openide.nodes.Node;


/** View showing the todo list items
 * @author Tor Norbye
 */
public class BugsView extends TaskListView implements TaskListener {

    private static final long serialVersionUID = 1;

    final static String CATEGORY = "bugs"; // NOI18N
    
    public BugsView() {
        this(null);
    }
    
    /** Construct a new TaskListView. Most work is deferred to
	componentOpened. NOTE: this is only for use by the window
	system when deserializing windows. Client code should not call
	it; use the constructor which takes category, title and icon
	parameters. I can't make it protected because then the window
	system wouldn't be able to get to this. But the code relies on
	readExternal getting called after this constructor to finalize
	construction of the window.*/
    public BugsView(BugQuery inQuery) {
	super(
	   CATEGORY,  // NOI18N
	   NbBundle.getMessage(BugsView.class, "ViewName"), // NOI18N
	   // I made a taskView.png, but it was larger (286 bytes) than the
	   // gif (186 bytes). More importantly, it had ugly display artifacts.
	   Utilities.loadImage(
		 "org/netbeans/modules/tasklist/bugs/bugsView.gif"),
	   true,
           BugList.getDefault(inQuery));
    }

    static final String PROP_BUG_ID = "bugId"; // NOI18N
    static final String PROP_BUG_SYNOPSIS = "bugSynopsis"; // NOI18N
    static final String PROP_BUG_PRIO = "bugPrio"; // NOI18N
    static final String PROP_BUG_TYPE = "bugType"; // NOI18N
    static final String PROP_BUG_COMP = "bugComp"; // NOI18N
    static final String PROP_BUG_SUBCOMP = "bugSubComp"; // NOI18N
    static final String PROP_BUG_CREATED = "bugCreated"; // NOI18N
    static final String PROP_BUG_KEYWORDS = "bugKeywords"; // NOI18N
    static final String PROP_BUG_ASSIGNED = "bugAssigned"; // NOI18N
    static final String PROP_BUG_REPORTEDBY = "bugReported"; // NOI18N
    static final String PROP_BUG_STATUS = "bugStatus"; // NOI18N
    static final String PROP_BUG_TARGET = "bugTarget"; // NOI18N
    static final String PROP_BUG_VOTES = "bugVotes"; // NOI18N


    public ColumnProperty getSummaryColumn(int width) {
        // Tree column
        // NOTE: Bug.getDisplayName() must also be kept in sync here
        return new ColumnProperty(
	    0, // UID -- never change (part of serialization
            PROP_TASK_SUMMARY,
            NbBundle.getMessage(BugsView.class, "Summary"), // NOI18N
            NbBundle.getMessage(BugsView.class, "Summary"), // NOI18N
	    true,
            width
	    );
    }


    public ColumnProperty getSynopsisColumn(boolean visible, int width) {
        return new ColumnProperty(
	    1, // UID -- never change (part of serialization
            PROP_BUG_SYNOPSIS,
            String.class,
            NbBundle.getMessage(BugsView.class, "Synopsis"), // NOI18N
            NbBundle.getMessage(BugsView.class, "SynopsisHint"), // NOI18N
            true,
            visible,
            width
            );
    }
   


    public ColumnProperty getPriorityColumn(boolean visible, int width) {
        return new ColumnProperty(
	    2, // UID -- never change (part of serialization
            PROP_BUG_PRIO,
            Integer.TYPE,
            NbBundle.getMessage(BugsView.class, "Priority"), // NOI18N
            NbBundle.getMessage(BugsView.class, "PriorityHint"), // NOI18N
            true,
            visible,
            width
            );
    }

    public ColumnProperty getBugIdColumn(boolean visible, int width) {
        return new ColumnProperty(
	    3, // UID -- never change (part of serialization
            PROP_BUG_ID,
            String.class,
            NbBundle.getMessage(BugsView.class, "BugId"), // NOI18N
            NbBundle.getMessage(BugsView.class, "BugIdHint"), // NOI18N
            true,
            visible,
            width
            );
    }
    
    public ColumnProperty getTypeColumn(boolean visible, int width) {
        return new ColumnProperty(
	    4, // UID -- never change (part of serialization
            PROP_BUG_TYPE,
            String.class,
            NbBundle.getMessage(BugsView.class, "Type"), // NOI18N
            NbBundle.getMessage(BugsView.class, "TypeHint"), // NOI18N
            true,
            visible,
            width
            );
    }
   
    public ColumnProperty getComponentColumn(boolean visible, int width) {
        return new ColumnProperty(
	    5, // UID -- never change (part of serialization
            PROP_BUG_COMP,
            String.class,
            NbBundle.getMessage(BugsView.class, "Component"), // NOI18N
            NbBundle.getMessage(BugsView.class, "ComponentHint"), // NOI18N
            true,
            visible,
            width
            );
    }
   
    public ColumnProperty getSubComponentColumn(boolean visible, int width) {
        return new ColumnProperty(
	    6, // UID -- never change (part of serialization
            PROP_BUG_SUBCOMP,
            String.class,
            NbBundle.getMessage(BugsView.class, "SubComponent"), // NOI18N
            NbBundle.getMessage(BugsView.class, "SubComponentHint"), // NOI18N
            true,
            visible,
            width
            );
    }
   
    public ColumnProperty getDateColumn(boolean visible, int width) {
        return new ColumnProperty(
	    7, // UID -- never change (part of serialization
            PROP_BUG_CREATED,
            Date.class,
            NbBundle.getMessage(BugsView.class, "Created"), // NOI18N
            NbBundle.getMessage(BugsView.class, "CreatedHint"), // NOI18N
            true,
            visible,
            width
            );
    }
   
    public ColumnProperty getKeywordsColumn(boolean visible, int width) {
        return new ColumnProperty(
	    8, // UID -- never change (part of serialization
            PROP_BUG_KEYWORDS,
            String.class,
            NbBundle.getMessage(BugsView.class, "Keywords"), // NOI18N
            NbBundle.getMessage(BugsView.class, "KeywordsHint"), // NOI18N
            true,
            visible,
            width
            );
    }
   
    public ColumnProperty getAssignedToColumn(boolean visible, int width) {
        return new ColumnProperty(
	    9, // UID -- never change (part of serialization
            PROP_BUG_ASSIGNED,
            String.class,
            NbBundle.getMessage(BugsView.class, "Assigned"), // NOI18N
            NbBundle.getMessage(BugsView.class, "AssignedHint"), // NOI18N
            true,
            visible,
            width
            );
    }
   
    public ColumnProperty getReportedByColumn(boolean visible, int width) {
        return new ColumnProperty(
	    10, // UID -- never change (part of serialization
            PROP_BUG_REPORTEDBY,
            String.class,
            NbBundle.getMessage(BugsView.class, "ReportedBy"), // NOI18N
            NbBundle.getMessage(BugsView.class, "ReportedByHint"), // NOI18N
            true,
            visible,
            width
            );
    }
   
    public ColumnProperty getStatusColumn(boolean visible, int width) {
        return new ColumnProperty(
	    11, // UID -- never change (part of serialization
            PROP_BUG_STATUS,
            String.class,
            NbBundle.getMessage(BugsView.class, "Status"), // NOI18N
            NbBundle.getMessage(BugsView.class, "StatusHint"), // NOI18N
            true,
            visible,
            width
            );
    }
   
    public ColumnProperty getTargetColumn(boolean visible, int width) {
        return new ColumnProperty(
	    12, // UID -- never change (part of serialization
            PROP_BUG_TARGET,
            String.class,
            NbBundle.getMessage(BugsView.class, "Target"), // NOI18N
            NbBundle.getMessage(BugsView.class, "TargetHint"), // NOI18N
            true,
            visible,
            width
            );
    }
   
    public ColumnProperty getVotesColumn(boolean visible, int width) {
        return new ColumnProperty(
	    13, // UID -- never change (part of serialization
            PROP_BUG_VOTES,
            Integer.TYPE,
            NbBundle.getMessage(BugsView.class, "Votes"), // NOI18N
            NbBundle.getMessage(BugsView.class, "VotesHint"), // NOI18N
            true,
            visible,
            width
            );
    }
   


    protected ColumnProperty[] createColumns() {
        // No point allowing other attributes of the task since that's
        // all we support for scan items (they are not created by
        // the user - and they are not persisted.
        return new ColumnProperty[] { 
            getSummaryColumn(800),

            getBugIdColumn(false, 150),
	    getTypeColumn(false, 100),
	    getComponentColumn(false, 200),
	    getSubComponentColumn(false, 200),
            getSynopsisColumn(false, 800),
	    getDateColumn(false, 200),
	    getKeywordsColumn(false, 200),
	    getAssignedToColumn(false, 200),
	    getReportedByColumn(false, 200),
	    getStatusColumn(true, 150),
	    getTargetColumn(false, 200),
	    getVotesColumn(false, 100),

            getPriorityColumn(true, 100)

	    
        };
    };

    /*
    public void readExternal(java.io.ObjectInput objectInput) throws java.io.IOException, java.lang.ClassNotFoundException {
        super.readExternal(objectInput);
    }

    public void writeExternal(java.io.ObjectOutput objectOutput) throws java.io.IOException {
        super.writeExternal(objectOutput);
    }
    */

    /** Create the root node to be used in this view */
    protected Node createRootNode() {
      return new TaskListNode(getModel());
    }
    
    public org.netbeans.modules.tasklist.filter.Filter createFilter() {
        return null; // TODO
    }

    protected SystemAction[] getToolBarActions() {
        return new SystemAction[] {
            SystemAction.get(RefreshAction.class),
            SystemAction.get(ViewBugAction.class),
//            SystemAction.get(FilterAction.class),
//            SystemAction.get(RemoveFilterAction.class)
        };
    }

}
