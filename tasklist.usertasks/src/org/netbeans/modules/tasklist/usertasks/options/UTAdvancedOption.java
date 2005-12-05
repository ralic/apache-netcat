/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2005 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.tasklist.usertasks.options;

import org.netbeans.spi.options.AdvancedOption;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.NbBundle;

/**
 * Options.
 *
 * @author tl
 */
public class UTAdvancedOption extends AdvancedOption {
    /**
     * Creates a new instance of UTAdvancedOption.
     */
    public UTAdvancedOption() {
    }

    public String getTooltip() {
        return NbBundle.getMessage(UTAdvancedOption.class, 
            "OptionTooltip"); // NOI18N
    }

    public String getDisplayName() {
        return NbBundle.getMessage(UTAdvancedOption.class, 
            "OptionDisplayName"); // NOI18N
    }

    public OptionsPanelController create() {
        return new UTPanelController();
    }
    
}
