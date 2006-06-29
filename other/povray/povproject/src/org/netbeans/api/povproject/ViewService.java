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
 * ViewService.java
 *
 * Created on February 17, 2005, 9:18 PM
 */

package org.netbeans.api.povproject;

import org.openide.filesystems.FileObject;

/**
 * Service which will open the image corresponding with a scene file.
 *
 * @author Timothy Boudreau
 */
public interface ViewService {
    /**
     * Determine if there is an existing render of this file.
     */
    public boolean isFileRendered (FileObject file);

    /**
     * Determine if the existing render of this file has a newer timestamp
     * than the file itself.
     */
public boolean isUpToDate (FileObject file);
    
    /**
     * View the passed scene as an image, rendering it if no image 
     * exists (but not re-rendering if it exists but is out of date).
     */
    public void view (FileObject file);
}
