/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2000 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.vcscore.annotation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author  Milos Kleint
 */
public interface AnnotationProvider {

    public static final String ANN_PROVIDER_FO_ATTRIBUTE = "AnnotationProviderObject"; //NOI18N
    
    /**
     * for a specified file (relative path from the fs root - FileObject getPackageNameExt() method), 
     * returns the value of the attribute (if defined) otherwise null
     */
 
    public String getAttributeValue(String fileName, String attributeName);

}
