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

package org.netbeans.modules.latex.editor;

import org.netbeans.editor.BaseImageTokenID;
import org.netbeans.editor.BaseTokenID;
import org.netbeans.editor.TokenContext;
import org.netbeans.editor.TokenContextPath;

/**
* Tokens used in formatting
*
* @author Miloslav Metelka
* @version 1.00
*/

public class TexTokenContext extends TokenContext {

    // Numeric-ids for token-ids
    public static final int TEXT_ID       =  1;
    public static final int EOL_ID        =  2;

    public static final BaseTokenID TEXT
    = new BaseTokenID("text", TEXT_ID);

    public static final BaseImageTokenID EOL
    = new BaseImageTokenID("EOL", EOL_ID, "\n");

    // Context declaration
    public static final TexTokenContext context = new TexTokenContext();

    public static final TokenContextPath contextPath = context.getContextPath();

    private TexTokenContext() {
        super("format-");

        try {
            addDeclaredTokenIDs();
        } catch (Exception e) {
            if (Boolean.getBoolean("netbeans.debug.exceptions")) { // NOI18N
                e.printStackTrace();
            }
        }

    }

}
