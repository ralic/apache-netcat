/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2004 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.j2ee.blueprints.ui;

import java.net.URL;
import org.netbeans.modules.j2ee.blueprints.catalog.bpcatalogxmlparser.Category;
import org.netbeans.modules.j2ee.blueprints.catalog.bpcatalogxmlparser.Solution;
import org.netbeans.modules.j2ee.blueprints.catalog.bpcatalogxmlparser.Writeup;

/**
 * Tab Panel containing a browser with the contents of the article.
 *
 * @author Mark Roth
 */
public class HomeTab 
    extends BluePrintsTabPanel
{
    /** Creates new form SolutionTab */
    public HomeTab(BluePrintsPanel bluePrintsPanel) {
        super(bluePrintsPanel);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        homeBrowser = new HtmlBrowserWithScrollPosition();

        setLayout(new java.awt.BorderLayout());

        add(homeBrowser, java.awt.BorderLayout.CENTER);

    }
    // </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel homeBrowser;
    // End of variables declaration//GEN-END:variables
    
    public void setScrollPosition(int scrollPosition) {
        ((HtmlBrowserWithScrollPosition)homeBrowser).
            setScrollPosition(scrollPosition);
    }

    public int getScrollPosition() {
        return ((HtmlBrowserWithScrollPosition)homeBrowser).
            getScrollPosition();
    }
    
    public void updateTab() {
        String articleURLString = BluePrintsPanel.CATALOG_RESOURCES_URL 
                + "/descriptions/bpcatalog-home.html"; // NOI18N
            BpcatalogLocalizedResource htmlrsc =
                    new BpcatalogLocalizedResource(articleURLString, "html");
            URL articleURL = htmlrsc.getResourceURL();
            ((HtmlBrowserWithScrollPosition)homeBrowser).setURL(articleURL);
    }
}
