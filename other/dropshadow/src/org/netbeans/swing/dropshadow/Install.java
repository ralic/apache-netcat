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
 * Install.java
 *
 * Created on 22. December 2003, 15:34
 */
package org.netbeans.swing.dropshadow;
import java.beans.PropertyEditorManager;
import org.openide.modules.ModuleInstall;

/** A simple moduleinstall class that registers/unregisters the drop-shadow
 * PopupFactory
 *
 * @author  Tim Boudreau  */
public class Install extends ModuleInstall {
    /** Creates a new instance of Install */
    public Install() {
    }

    public void restored() {
        NbPopupFactory.install();
    }

    public void uninstalled() {
        NbPopupFactory.uninstall();
    }
    
    /** Main method for testing (semi-dangerous) */
    public static void main(String args[]) {
        new Install().restored();
    }    
}
