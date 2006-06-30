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

package org.netbeans.modules.tasklist.suggestions;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;
import org.openide.util.NbBundle;
import java.util.List;

/**
 * This class represents a SuggestionType. This concept is described
 * in the TaskList api (org.netbeans.api.tasklist) documentation.
 *
 * @author Tor Norbye
 */

final public class SuggestionType {

    /**
     * Create a SuggestionType
     * @param name The name which identifies this Suggestion Type
     * @param bundle The file where the localized name for the type is found
     * @param key The key which holds the localized name in the bundle file
     * @param longkey The key which holds the localized full, possibly
     *     multiline description of the type in the bundle file
     * @param icon A url to the icon to be used by default for these suggestions */
    SuggestionType(String name, String bundle, String key, 
                   String longkey, URL icon, List actions) {
        this.name = name;
        this.bundle = bundle;
        this.key = key;
        this.longkey = longkey;
        this.icon = icon;
        this.actions = actions;
    }

    List getActions() {
        return actions;
    }

    /** @return The name which identifies this Suggestion Type */
    public String getName() {
        return name;
    }
        
    /** @return The file where the localized name for the type is found */
    String getBundle() {
        return bundle;
    }

    /** @return The key which holds the localized name in the bundle file */
    String getKey() {
        return key;
    }

    /** @return A url to the icon to be used by default for these suggestions */
    URL getIcon() {
        return icon;
    }

    /** Gets Image which represents the icon. */
    public Image getIconImage() {
        if ((img == null) && (icon != null)) {
            img = Toolkit.getDefaultToolkit().getImage(icon);
        }
        return img;
    }

    /** Return the name of the Suggestion type - localized. */
    public String getLocalizedName() {
        if (localizedName == null) {
            ResourceBundle rb = NbBundle.getBundle(bundle);
            localizedName = rb.getString(key);
            if (localizedName == null) {
                localizedName = "";
            }
        }
        return localizedName;
    }

    /** Return the description of the Suggestion type - localized. */
    String getDescription() {
        if (localizedDesc == null) {
            if (longkey != null) {
                ResourceBundle rb = NbBundle.getBundle(bundle);
                localizedDesc = rb.getString(longkey);
            }
            if (localizedDesc == null) {
                localizedDesc = "";
            }
        }
        return localizedDesc;
    }

   /** Return a description of this object. Format may change any time
     * and is not localized. Do not depend on its content or format. */
    public String toString() {
        return "SuggestionType[name=" + name + ",bundle=" + bundle + // NOI18N
            ",key=" + key + ",icon=" + icon + ",pos=" + position +"]"; // NOI18N
    }    

    /** Set the relative position of this type in the type hierarchy */
    public void setPosition(int position) {
        this.position = position;
    }

    /** Return the relative position of this type in the type hierarchy */
    public int getPosition() {
        return position;
    }

    private int position;
    private String name;
    private String bundle;
    private String key;
    private String longkey;
    private URL icon;
    private Image img = null;
    private String localizedName = null;
    private String localizedDesc = null;
    private List actions;
}
