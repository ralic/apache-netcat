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
package org.netbeans.modules.latex.editor.completion.latex;

import java.net.URL;
import javax.swing.Action;
import org.netbeans.spi.editor.completion.CompletionDocumentation;

/**
 *
 * @author Jan Lahoda
 */
public class TexCompletionDocumentation implements CompletionDocumentation {
    
    private String text;
    
    /** Creates a new instance of TexCompletionDocumentation */
    public TexCompletionDocumentation(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public URL getURL() {
        return null;
    }

    public CompletionDocumentation resolveLink(String link) {
        return null;
    }

    public Action getGotoSourceAction() {
        return null;
    }
    
}
