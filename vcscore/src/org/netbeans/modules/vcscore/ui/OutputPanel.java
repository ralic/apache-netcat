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

package org.netbeans.modules.vcscore.ui;

import javax.swing.*;
import org.openide.util.NbBundle;

/**
 * OutputPanel.java
 *
 * Created on December 21, 2003, 7:17 PM 
 * @author  Richard Gregor
 */
public class OutputPanel extends AbstractOutputPanel{
    
    private JTextArea stdOutput;
    private JTextArea errOutput;    
   
    protected JComponent getErrComponent() {
        if(errOutput == null){
            errOutput = new JTextArea();
            errOutput.setEditable(false);
            errOutput.getAccessibleContext().setAccessibleName(NbBundle.getBundle(OutputPanel.class).getString("ACS_OutputPanel.ErrComponent"));//NOI18N
            errOutput.getAccessibleContext().setAccessibleDescription(NbBundle.getBundle(OutputPanel.class).getString("ACSD_OutputPanel.ErrComponent"));//NOI18N
            java.awt.Font font = errOutput.getFont();
            errOutput.setFont(new java.awt.Font("Monospaced", font.getStyle(), font.getSize()));
        }
        return errOutput;
    }
    
    protected JComponent getStdComponent() {
        if(stdOutput == null){
            stdOutput = new JTextArea();
            stdOutput.setEditable(false);
            stdOutput.getAccessibleContext().setAccessibleName(NbBundle.getBundle(OutputPanel.class).getString("ACS_OutputPanel.StdComponent"));//NOI18N
            stdOutput.getAccessibleContext().setAccessibleDescription(NbBundle.getBundle(OutputPanel.class).getString("ACSD_OutputPanel.StdComponent"));//NOI18N
            java.awt.Font font = stdOutput.getFont();
            stdOutput.setFont(new java.awt.Font("Monospaced", font.getStyle(), font.getSize()));
        }
        return stdOutput;
    }
    
}
