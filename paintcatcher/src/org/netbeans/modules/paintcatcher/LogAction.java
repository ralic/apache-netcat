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
package org.netbeans.modules.paintcatcher;

import java.awt.Toolkit;
import javax.swing.Action;
import javax.swing.SwingUtilities;
import org.openide.awt.StatusDisplayer;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.BooleanStateAction;

/**
 * Action that can always be invoked and work procedurally.
 * @author tim
 */
public class LogAction extends BooleanStateAction {
    static PaintCatcher catcher = null;

    public LogAction() {
    }

    private boolean start() {
        if (catcher != null) {
            return false;
        }
        if (ConfigureAction.classToLog == null) {
            Toolkit.getDefaultToolkit().beep();
            StatusDisplayer.getDefault().setStatusText("No class to log");
            return false;
        }
        Filter filter = new ComponentPaintFilter (
            ConfigureAction.classToLog, 
            ConfigureAction.matchIfSubclass, 
            ConfigureAction.matchIfAncestor);
        
        Logger logger = new ConsoleLogger(ConfigureAction.dumpStack);  
        
        catcher = new PaintCatcher(filter, logger);
        catcher.setLogAWTEvents(ConfigureAction.logAWTEvents);
        
        StatusDisplayer.getDefault().setStatusText("Paint logging started");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                catcher.setEnabled(true);
            }
        });
        return true;
    }
    
    private void stop() {
        if (catcher == null) {
            throw new IllegalStateException();
        }
        catcher.setEnabled(false);
        catcher = null;
        StatusDisplayer.getDefault().setStatusText("Paint logging stopped");
    }
    
    public String getName() {
        return NbBundle.getMessage(LogAction.class, "LBL_Logging");
    }
    
    protected String iconResource() {
        return "org/netbeans/modules/paintcatcher/LogActionIcon.gif";
    }
    
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
        // If you will provide context help then use:
        // return new HelpCtx(LogAction.class);
    }
    
    protected void initialize() {
        super.initialize();
        putProperty(Action.SHORT_DESCRIPTION, NbBundle.getMessage(LogAction.class, "HINT_Logging"));
        ConfigureAction.logAction = this;
     }
    
    public void setBooleanState(boolean value) {
        //do nothing
    }    
    
    public boolean getBooleanState() {
        return catcher != null;
    }    
    
    public void actionPerformed (java.awt.event.ActionEvent ev) {
        boolean running = catcher != null;
        if (!running) {
            start();
        } else {
            stop();
        }
        boolean nowRunning = catcher != null;
        if (nowRunning != running) {
            firePropertyChange(PROP_BOOLEAN_STATE, running ? 
                Boolean.FALSE : Boolean.TRUE, 
                nowRunning ? 
                Boolean.TRUE : Boolean.FALSE);
        }
    }
}
