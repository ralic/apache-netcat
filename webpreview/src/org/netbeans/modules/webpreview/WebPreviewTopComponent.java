/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2009 Sun Microsystems, Inc.
 */

package org.netbeans.modules.webpreview;

import java.awt.BorderLayout;
import java.util.logging.Logger;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
//import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.core.browser.api.EmbeddedBrowserFactory;
import org.netbeans.core.browser.api.WebBrowser;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
    dtd="-//org.netbeans.modules.webpreview//WebPreview//EN",
    autostore=false
)
public final class WebPreviewTopComponent extends TopComponent {

    public static Logger LOGGER = Logger.getLogger("WebPreview");

    private WebBrowser webBrowser;

    private static WebPreviewTopComponent instance;
    
    /** path to the icon used by the component and its open action */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";

    private static final String PREFERRED_ID = "WebPreviewTopComponent";

    public WebPreviewTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(WebPreviewTopComponent.class, "CTL_WebPreviewTopComponent"));
        setToolTipText(NbBundle.getMessage(WebPreviewTopComponent.class, "HINT_WebPreviewTopComponent"));
//        setIcon(ImageUtilities.loadImage(ICON_PATH, true));
        if (EmbeddedBrowserFactory.getDefault().isEnabled()) {
            webBrowser = EmbeddedBrowserFactory.getDefault().createEmbeddedBrowser();
        }
    }

    public void maybeInitWebBrowser() {
        if (webBrowser == null && EmbeddedBrowserFactory.getDefault().isEnabled()) {
            webBrowser = EmbeddedBrowserFactory.getDefault().createEmbeddedBrowser();
        }
    }

    public WebBrowser getWebBrowser() {
        return webBrowser;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized WebPreviewTopComponent getDefault() {
        if (instance == null) {
            instance = new WebPreviewTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the WebPreviewTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized WebPreviewTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            LOGGER.warning("Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof WebPreviewTopComponent) {
            return (WebPreviewTopComponent) win;
        }
        LOGGER.warning("There seem to be multiple components with the '" + PREFERRED_ID +
                "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        if (webBrowser != null) {
            add(webBrowser.getComponent(), BorderLayout.CENTER);
        }
    }

    @Override
    public void componentClosed() {
        if (webBrowser != null) {
            removeAll();
            webBrowser.dispose();
        }
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    Object readProperties(java.util.Properties p) {
        WebPreviewTopComponent singleton = WebPreviewTopComponent.getDefault();
        singleton.readPropertiesImpl(p);
        return singleton;
    }

    private void readPropertiesImpl(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

}
