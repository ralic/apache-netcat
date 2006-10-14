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

package org.netbeans.modules.docbook;

import java.awt.Image;
import java.beans.BeanInfo;
import org.openide.loaders.*;
import org.openide.nodes.*;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;

public class DocBookDataNode extends DataNode {

    public DocBookDataNode(DocBookDataObject obj) {
        super(obj, Children.LEAF);
    }

    public Image getIcon(int type) {
        if (type == BeanInfo.ICON_COLOR_16x16 || type == BeanInfo.ICON_MONO_16x16) {
            return Utilities.loadImage("org/netbeans/modules/docbook/docbook.png", true);
        } else {
            return null;
        }
    }
    
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }
    
    public String getShortDescription() {
        String mime = getDataObject().getPrimaryFile().getMIMEType();
        if (mime.equals(DocBookDataLoader.MIME_DOCBOOK)) {
            return NbBundle.getMessage(DocBookDataNode.class, "HINT_file_docbook_xml");
        } else if (mime.equals(DocBookDataLoader.MIME_SLIDES)) {
            return NbBundle.getMessage(DocBookDataNode.class, "HINT_file_slides");
        } else {
            //Mime type can be wrong if the document is malformed
            return super.getShortDescription();
        }
    }
    
}
