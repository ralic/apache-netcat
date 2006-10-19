/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 */
package org.netbeans.modules.visual.examples.shapes.palette;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.general.IconNodeWidget;
import org.netbeans.core.spi.multiview.CloseOperationState;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.MultiViewElementCallback;
import org.netbeans.modules.visual.examples.shapes.GraphSceneImpl;
import org.netbeans.modules.visual.examples.shapes.assistant.AssistantModel;
import org.netbeans.modules.visual.examples.shapes.assistant.AssistantModel;
import org.netbeans.modules.visual.examples.shapes.assistant.AssistantView;
import org.netbeans.modules.visual.examples.shapes.assistant.ModelHelper;
import org.netbeans.modules.visual.examples.shapes.navigator.ShapeNavigatorHint;
import org.netbeans.spi.palette.PaletteController;
import org.openide.ErrorManager;
import org.openide.loaders.MultiDataObject;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.lookup.Lookups;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
public final class ShapeTopComponent extends TopComponent implements MultiViewElement {
    
    private static ShapeTopComponent instance;
    /** path to the icon used by the component and its open action */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
    
    private static final String PREFERRED_ID = "ShapeTopComponent";
    
    //For multiview implementation:
    private transient MultiViewElementCallback callback;
    private MultiDataObject.Entry entry;
    private final transient CloseOperationState cos;

    private AssistantModel localmodel;
    
    private static PaletteController controller;
    private transient Lookup lookup;
    
    private transient Scene scene;
    private transient JComponent sceneView;
    private transient JComponent satelliteView;
    
    public ShapeTopComponent(MultiDataObject.Entry entry, CloseOperationState cos) {
        this.entry = entry;
        this.cos = cos;
    }
    
    public ShapeTopComponent() {
        scene = new GraphSceneImpl ();
        sceneView = scene.getView();
        satelliteView = scene.createSatelliteView();
        
        cos = null;
                
        localmodel = ModelHelper.returnAssistantModel();
        
        initComponents();
        
        setName(NbBundle.getMessage(ShapeTopComponent.class, "CTL_ShapeTopComponent"));
        setToolTipText(NbBundle.getMessage(ShapeTopComponent.class, "HINT_ShapeTopComponent"));
        
        controller = Utils.getPalette();
        
        lookup = Lookups.fixed( new Object[] {controller, new ShapeNavigatorHint () } );
        
        associateLookup( lookup );
    }
    
    public JComponent getNavigatorView () {
        System.out.println("NAVIGATOR:" + satelliteView);
        return satelliteView;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new JScrollPane(sceneView);
        jPanel1 = new AssistantView(localmodel);

        setLayout(new java.awt.BorderLayout());
        add(jScrollPane1, java.awt.BorderLayout.CENTER);
        add(jPanel1, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link findInstance}.
     */
    public static synchronized ShapeTopComponent getDefault() {
        if (instance == null) {
            instance = new ShapeTopComponent();
        }
        return instance;
    }
    
    public Lookup getLookup () {
        return lookup;
    }
    
    /**
     * Obtain the ShapeTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized ShapeTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            ErrorManager.getDefault().log(ErrorManager.WARNING, "Cannot find Shape component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof ShapeTopComponent) {
            return (ShapeTopComponent)win;
        }
        ErrorManager.getDefault().log(ErrorManager.WARNING, "There seem to be multiple components with the '" + PREFERRED_ID + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_NEVER;
    }
    
    public void componentOpened() {
        // TODO add custom code on component opening
    }
    
    public void componentClosed() {
        // TODO add custom code on component closing
    }
    
//    /** replaces this in object stream */
//    public Object writeReplace() {
//        return new ResolvableHelper();
//    }
    
    protected String preferredID() {
        return PREFERRED_ID;
    }
    
    
//    final static class ResolvableHelper implements Serializable {
//        private static final long serialVersionUID = 1L;
//        public Object readResolve() {
//            return ShapeTopComponent.getDefault();
//        }
//    }
    
    public void setMultiViewCallback(MultiViewElementCallback callback) {
        this.callback = callback;
//        callback.updateTitle(getName());
    }
    
    public JComponent getVisualRepresentation() {
        return this;
    }
    
    public JComponent getToolbarRepresentation() {
        // XXX can put add/del buttons here
        return new JPanel();
    }
    
    public CloseOperationState canCloseElement() {
        if (entry.getDataObject().isModified()) {
            return cos;
        } else {
            return CloseOperationState.STATE_OK;
        }
    }
    
    public void componentDeactivated() {
        super.componentDeactivated();
    }
    public void componentActivated() {
        super.componentActivated();
    }
    public void componentHidden() {
        super.componentHidden();
    }
    public void componentShowing() {
        super.componentShowing();
    }
}
