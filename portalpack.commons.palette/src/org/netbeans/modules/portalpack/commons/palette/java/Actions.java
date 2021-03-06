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
/*
 * Actions.java
 *
 * Created on March 8, 2007, 10:30 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.netbeans.modules.portalpack.commons.palette.java;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.text.JTextComponent;
import org.netbeans.editor.Utilities;
import org.netbeans.spi.palette.PaletteActions;
import org.netbeans.spi.palette.PaletteController;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.text.ActiveEditorDrop;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

/**
 *
 * @author Satyaranjan
 */
public class Actions extends PaletteActions{
    
    /** Creates a new instance of TestDDPaletteActions */
    public Actions() {
    }

    public Action[] getImportActions() {
        return new Action[0];
    }

    public Action[] getCustomPaletteActions() {
        return new Action[0];
    }

    public Action[] getCustomCategoryActions(Lookup lookup) {
        return new Action[0];
    }

    public Action[] getCustomItemActions(Lookup lookup) {
        return new Action[0];
    }

    public Action getPreferredAction(Lookup item) {
        return new MFPaletteInsertAction(item);
    }
    
    private static class MFPaletteInsertAction extends AbstractAction {
        
        private Lookup item;
        
        MFPaletteInsertAction(Lookup item) {
            this.item = item;
        }
                
        public void actionPerformed(ActionEvent e) {
      
            ActiveEditorDrop drop = (ActiveEditorDrop) item.lookup(ActiveEditorDrop.class);
            
            JTextComponent target = Utilities.getFocusedComponent();
            if (target == null) {
                String msg = NbBundle.getMessage(Actions.class, "MSG_ErrorNoFocussedDocument");
                DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(msg, NotifyDescriptor.ERROR_MESSAGE));
                return;
            }
            
            try {
                drop.handleTransfer(target);
            }
            finally {
                Utilities.requestFocus(target);
            }
            
            try {
                PaletteController pc = JavaPaletteFactory.getPalette();
                pc.clearSelection();
            }
            catch (IOException ioe) {
            } //should not occur

        }
    }

    
}
