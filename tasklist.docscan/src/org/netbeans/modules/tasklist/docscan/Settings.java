/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2003 Sun
 * Microsystems, Inc. All Rights Reserved.
 */


package org.netbeans.modules.tasklist.docscan;

import org.netbeans.api.tasklist.SuggestionPriority;
import org.openide.options.SystemOption;
import org.openide.util.NbBundle;


/** Settings for the tasklist module.
 */

public class Settings extends SystemOption {

    /** serial uid */
    // XXX check this one
    static final long serialVersionUID = -29424677132370773L;

    // Option labels
    public static final String
	PROP_SCAN_SOURCES	= "scanSources",	//NOI18N
	PROP_SCAN_SKIP  	= "skipComments",	//NOI18N
	PROP_SCAN_DELAY		= "scanDelay",		//NOI18N
	PROP_SCAN_TAGS		= "taskTags";		//NOI18N

    /** Return the signleton cppSettings */
    public static Settings getDefault() {
	return (Settings) findObject(Settings.class, true);
    }


    /**
     * Get the display name.
     *
     *  @return value of OPTION_TASK_SETTINGS_NAME
     */
    public String displayName () {
	return NbBundle.getMessage(Settings.class,
				   "OPTION_TASK_SETTINGS_NAME"); //NOI18N
    }

    /*
    public HelpCtx getHelpCtx () {
	return new HelpCtx ("Welcome_opt_editing_sources");	        //NOI18N
    }
    */


    /**
     * @return true iff the user wants source scanning (where
     * when the todolist is visible, source files in the editor
     * are scanned for tasks identified by (stringtable, such
     * as TODO). The default value is False.
     */
    public boolean getScanSources() {
        Boolean b = (Boolean)getProperty(PROP_SCAN_SOURCES);

	/*
	// Default to off (null != Boolean.TRUE)
	return (b == Boolean.TRUE);
	*/

	// Default to on now that I'm still in a prototype stage
	return (b != Boolean.FALSE);
    }

    /** Sets the scanSources property
     * @param doScan True iff you want to scan sources
     */
    public void setScanSources(boolean doScan) {
	Boolean b = doScan ? Boolean.TRUE : Boolean.FALSE;
	putProperty(PROP_SCAN_SOURCES, b, true);
	//firePropertyChange(PROP_SCAN_SOURCES, null, b);	
    }


    /**
     * @return true iff the user wants to skip all tasks tokens
     * appear outside of comment sections.  The default value
     * is true.
     */
    public boolean getSkipComments() {
        // XXX I did a spectacularly poor job naming this method.
        // I never skip comments, I skip non-comments.
        Boolean b = (Boolean)getProperty(PROP_SCAN_SKIP);

        /*
	// Default to on
	return (b != Boolean.FALSE);
        */
        
	// Default to off (null != Boolean.TRUE)
	return (b == Boolean.TRUE);
    }

    /** Sets the skip-outside-of-comments property
     * @param doSkip True iff you want to skip tasks outside of comments
     */
    public void setSkipComments(boolean doSkip) {
	Boolean b = doSkip ? Boolean.TRUE : Boolean.FALSE;
	putProperty(PROP_SCAN_SKIP, b, true);
	//firePropertyChange(PROP_SCAN_SKIP, null, b);	
    }


    /**
     * @return Delay (in milliseconds) after file exposure
     * or file edit before the scanner kicks in to update the
     * tasklist.
     */
    public int getScanDelay() {
        Integer d = (Integer)getProperty(PROP_SCAN_DELAY);
	if (d == null) {
	    return 1000; // Default: 1 seconds
	}
	return d.intValue();
    }

    /** Sets the scanDelay type
     * @param delay The delay (in milliseconds) after file exposure
     * or file edit before the scanner kicks in to update the
     * tasklist. Must be greater than or equal to 0.
     */
    public void setScanDelay(int delay) {
	if (delay < 0) {
	    throw new IllegalArgumentException();	    
	}
	Integer d = new Integer(delay);
	putProperty(PROP_SCAN_DELAY, d, true);
	//Done above: firePropertyChange(PROP_SCAN_DELAY, null, d);	
    }

    public TaskTags getTaskTags() {
        if (tags == null) {
            TaskTags d = (TaskTags)getProperty(PROP_SCAN_TAGS);
            if (d != null) {
                tags = d;
            } else {
                tags = new TaskTags();
                tags.setTags(new TaskTag[] {
                    new TaskTag("@todo", SuggestionPriority.MEDIUM),
                    new TaskTag("TODO", SuggestionPriority.MEDIUM),
                    new TaskTag("FIXME", SuggestionPriority.MEDIUM),
                    new TaskTag("XXX", SuggestionPriority.MEDIUM),
                    new TaskTag("PENDING", SuggestionPriority.MEDIUM),
                    // XXX CVS merge conflict: overlaps with skipNonComments settings
                    new TaskTag("<<<<<<<", SuggestionPriority.HIGH),

                    // Additional candidates: HACK, WORKAROUND, REMOVE, OLD
                });;
            }
        }
        return tags;
    }

    private TaskTags tags = null;

    /** Sets the skip-outside-of-comments property
     * @param doSkip True iff you want to skip tasks outside of comments
     */
    public void setTaskTags(TaskTags scanTasks) {
        tags = scanTasks;
	putProperty(PROP_SCAN_TAGS, tags, true);
	//firePropertyChange(PROP_SCAN_TAGS, null, b);	
    }



}
