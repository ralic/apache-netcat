package org.netbeans.modules.venice.viewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
final class LGViewerTopComponent extends TopComponent {

    private static final long serialVersionUID = 1L;

    private static LGViewerTopComponent instance;

    private LGViewerTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(LGViewerTopComponent.class, "CTL_LGViewerTopComponent"));
        setToolTipText(NbBundle.getMessage(LGViewerTopComponent.class, "HINT_LGViewerTopComponent"));
        setIcon(Utilities.loadImage("org/netbeans/modules/venice/viewer/setting-inherited.gif", true));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());

    }
    // </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    /**
     * Gets default instance. Don't use directly, it reserved for '.settings' file only,
     * i.e. deserialization routines, otherwise you can get non-deserialized instance.
     */
    public static synchronized LGViewerTopComponent getDefault() {
        if (instance == null) {
            instance = new LGViewerTopComponent();
        }
        return instance;
    }

    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    public void componentOpened() {
        // TODO add custom code on component opening
    }

    public void componentClosed() {
        // TODO add custom code on component closing
    }

    /** replaces this in object stream */
    public Object writeReplace() {
        return new ResolvableHelper();
    }

    protected String preferredID() {
        return "LGViewerTopComponent";
    }

    final static class ResolvableHelper implements Serializable {
        private static final long serialVersionUID = 1L;
        public Object readResolve() {
            return LGViewerTopComponent.getDefault();
        }
    }

}
