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

package org.netbeans.api.vcs.commands;

//import java.io.InputStream;
//import javax.swing.event.EventListenerList;
import java.util.ArrayList;
import java.util.List;

import org.openide.filesystems.FileObject;
import org.openide.util.Task;

import org.netbeans.modules.vcscore.commands.CommandProcessor;
import org.netbeans.modules.vcscore.commands.CommandTaskInfo;

/**
 * This class represents the actual command, that is executed to acually perform a VCS action.
 *
 * @author  Martin Entlicher
 */
public abstract class CommandTask extends Task {
    
    public static final int STATUS_NOT_STARTED = -2;
    public static final int STATUS_RUNNING = -1;
    public static final int STATUS_SUCCEEDED = 0;
    public static final int STATUS_FAILED = 1;
    public static final int STATUS_INTERRUPTED = 2;
    
    private volatile int status = STATUS_NOT_STARTED;
    
    private final Object notifyLock = new Object();
    private final Object taskInfoLock = new Object();
    private CommandTaskInfo taskInfo;

    private final Exception origin;

    protected CommandTask() {
        if (Boolean.getBoolean("netbeans.vcsdebug")) { // NOI18N
            origin = new Exception("Task allocation stack trace");  // NOI18N
        } else {
            origin = null;
        }
    }

    //private EventListenerList outputListeners = new EventListenerList();
    /**
     * Get the name of the command.
     */
    public abstract String getName();
    
    /**
     * Get the display name of the command. It will be visible on the popup menu under this name.
     * When <code>null</code>, the command will not be visible on the popup menu.
     */
    public abstract String getDisplayName();
    
    /**
     * Get the command associated with this task.
     *
     // The command can change it's properties right after execute().
     // It has no sense to get the command instance then.
     // The Command would have to be clonned if this method is required.
    public abstract Command getCommand();
     */
    
    /**
     * Get files this task acts on.
     */
    public abstract FileObject[] getFiles();
    
    /**
     * Get the priority of this task. This can be taken into account by the task
     * processor. <p>
     * Currently only binary priority is distinguished by the CommandProcessor:
     * <code>priority == 0</code> and <code>priority != 0</code>. When
     * <code>priority != 0</code>, the task can be run sooner than other tasks
     * with <code>priority == 0</code>. After the task is running, it's performance
     * does not depend on the priority.
     * @return The task's priority. The default implementation returns <code>0</code>.
     */
    public int getPriority() {
        return 0;
    }
    
    /**
     * Tell, whether the task can be executed now. The task may wish to aviod parallel
     * execution with other tasks or other events.
     * @return <code>true</code> if the task is to be executed immediately. This is the
     *                           default implementation.
     *         <code>false</code> if the task should not be executed at this time.
     *                            In this case the method will be called later to check
     *                            whether the task can be executed already.
     */
    protected boolean canExecute() {
        return true;
    }
    
    /**
     * Schedules the command task for being executed. The actual execution
     * might be done asynchronously. In this case this method returns immediately.
     */
    public final void run() {
        synchronized (taskInfoLock) {
            if (taskInfo != null) {
                throw new IllegalStateException("This task was already executed. Use Command.execute() to run a new task.");
            }
            taskInfo = new CommandTaskInfoImpl(this);
        }
        taskInfo.register();
        //throw new UnsupportedOperationException("Do not execute the task by this method. Put it into the CommandProcessor instead.");
    }

    /** Accessed by reflection from VcsRuntimeCommand */
    final Exception getOrigin() {
        return origin;
    }

    final void runCommandTask() {
        status = STATUS_RUNNING;
        notifyRunning();
        try {
            status = execute();
        } finally {
            if (taskInfo.isInterrupted()) {
                status = STATUS_INTERRUPTED;
            } else {
                if (status == STATUS_RUNNING) {
                    status = STATUS_FAILED;
                }
            }
            notifyFinished();
            synchronized (notifyLock) {
                notifyLock.notifyAll();
            }
        }
        // Free all listeners after the command finished.
        //outputListeners = null;
    }
    
    final void cancel() {
        if (status == STATUS_NOT_STARTED) {
            try {
                notifyRunning();
            } finally {
                status = STATUS_INTERRUPTED;
                try {
                    notifyFinished();
                } finally {
                    synchronized (notifyLock) {
                        notifyLock.notifyAll();
                    }
                }
            }
        }
    }
    
    /**
     * Put the actual execution of this task here.
     * This method will be called automatically after process() call. Do NOT call this
     * method.
     * @return The exit status. One of STATUS_* constants.
     */
    protected abstract int execute();
    
    /**
     * Say whether the command is running just now.
     * @return True if the command is running, false if not.
     */
    public final boolean isRunning() {
        return STATUS_RUNNING == status;
    }
    
    /**
     * Stop the command's execution. The default implementation kills
     * the command's thread by hard.
     */
    public void stop() {
        killHard();
    }
    
    /**
     * Kill the command's thread by hard.
     */
    final void killHard() {
        CommandProcessor.getInstance().kill(this);
    }
    
    /**
     * Get the exit status of the command.
     */
    public final int getExitStatus() {
        return status;
    }
    
    /**
     * Wait for the task to finish while allowing interruption. Because the
     * method Task.waitFinished() ignores InterruptedException, this method was
     * created. If the current thread is interrupted while waiting for the task
     * to finish, the InterruptedException is thrown.
     * @param timeout The maximum time to wait in milliseconds. 
     * @throws InterruptedException when the current thread is interrupted while
     * waiting.
     */
    public void waitFinished(int timeout) throws InterruptedException {
        synchronized (notifyLock) {
            if (!isFinished()) {
                notifyLock.wait(timeout);
            }
        }
    }
    
}

