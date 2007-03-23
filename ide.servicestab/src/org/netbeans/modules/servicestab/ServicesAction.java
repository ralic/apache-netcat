
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
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 */
/*
 * ServicesAction.java
 *
 * Created on February 16, 2007, 3:46 PM
 */
package org.netbeans.modules.servicestab;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;

/**
 * Action which shows the Services Top Component.
 * @author Winston Prakash
 */
public class ServicesAction extends AbstractAction {
    
    /**
     * Create new Services Action that shows the Services Top Component
     */
    public ServicesAction() {
        super(NbBundle.getMessage(ServicesAction.class, "CTL_ServicesAction"));
        putValue(SMALL_ICON, new ImageIcon(Utilities.loadImage(ServicesTopComponent.ICON_PATH, true)));
    }
    
    /**
     * Execute the action
     * @param evt 
     */
    public void actionPerformed(ActionEvent evt) {
        TopComponent win = ServicesTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
    
}
