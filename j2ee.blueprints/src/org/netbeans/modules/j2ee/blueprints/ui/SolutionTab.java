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

package org.netbeans.modules.j2ee.blueprints.ui;

import java.net.URL;
import org.netbeans.modules.j2ee.blueprints.catalog.bpcatalogxmlparser.Nbcategory;
import org.netbeans.modules.j2ee.blueprints.catalog.bpcatalogxmlparser.Nbsolution;
import org.netbeans.modules.j2ee.blueprints.catalog.bpcatalogxmlparser.Nbwriteup;

/**
 * Tab Panel containing a browser with the contents of the article.
 *
 * @author Mark Roth
 */
public class SolutionTab
    extends BluePrintsTabPanel
{
    /** Creates new form SolutionTab */
    public SolutionTab(BluePrintsPanel bluePrintsPanel) {
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
        solutionBrowser = new HtmlBrowserWithScrollPosition();

        setLayout(new java.awt.BorderLayout());

        add(solutionBrowser, java.awt.BorderLayout.CENTER);

    }
    // </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel solutionBrowser;
    // End of variables declaration//GEN-END:variables
    
    public void setScrollPosition(int scrollPosition) {
        ((HtmlBrowserWithScrollPosition)solutionBrowser).
            setScrollPosition(scrollPosition);
    }

    public int getScrollPosition() {
        return ((HtmlBrowserWithScrollPosition)solutionBrowser).
            getScrollPosition();
    }
    
    public void updateTab() {
        Nbcategory category = bluePrintsPanel.getSelectedCategory();
        Nbsolution solution = bluePrintsPanel.getSelectedArticle();
        if(solution != null) {
            Nbwriteup writeup = solution.getNbwriteup();
            String articleURLString = BluePrintsPanel.CATALOG_RESOURCES_URL 
                + "/" + writeup.getArticlePath(); // NOI18N
            BpcatalogLocalizedResource htmlrsc =
                    new BpcatalogLocalizedResource(articleURLString, "html");
            URL articleURL = htmlrsc.getResourceURL();
            /***
            URL articleURL = getClass().getResource(articleURLString);
             ***/
            ((HtmlBrowserWithScrollPosition)solutionBrowser).setURL(
                articleURL);
        }
    }
}
