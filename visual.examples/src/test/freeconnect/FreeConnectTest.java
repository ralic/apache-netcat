/*
 * NewJPanel.java
 *
 * Created on September 7, 2006, 3:19 PM
 */

package test.freeconnect;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JComponent;
import org.netbeans.api.visual.widget.Widget;
import test.SceneSupport;

/**
 *
 * @author  alex
 */
public class FreeConnectTest extends javax.swing.JPanel {
    
    private DemoGraphScene scene;
    protected JComponent view;
    /** Creates new form NewJPanel */
    public FreeConnectTest() {
        initComponents();
        scene = new DemoGraphScene();
        
        String nodeID1 = "Node 1";
        String nodeID2 = "Node 2";
        String edge = "edge";
        
        Widget hello = scene.addNode(nodeID1);
        Widget world = scene.addNode(nodeID2);
        
        scene.addEdge(edge);
        
        scene.setEdgeSource(edge, nodeID1);
        scene.setEdgeTarget(edge, nodeID2);
        
        hello.setPreferredLocation(new Point(100, 100));
        world.setPreferredLocation(new Point(400, 200));
        
        setFocusable (true);
        setFocusTraversalKeysEnabled (false);
        view=scene.createView();
        add(view,java.awt.BorderLayout.CENTER);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jToolBar1 = new javax.swing.JToolBar();
        GridsToggleButton = new javax.swing.JToggleButton();

        setLayout(new java.awt.BorderLayout());

        jToolBar1.setFloatable(false);
        GridsToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/resources/dots.gif")));
        GridsToggleButton.setSelected(true);
        GridsToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GridsToggleButtonActionPerformed(evt);
            }
        });

        jToolBar1.add(GridsToggleButton);

        add(jToolBar1, java.awt.BorderLayout.NORTH);

    }// </editor-fold>//GEN-END:initComponents
    
    private void GridsToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GridsToggleButtonActionPerformed
        if(GridsToggleButton.isSelected())scene.initGrids();
        else {
            scene.setBackground(Color.WHITE);
            scene.validate();
        }
    }//GEN-LAST:event_GridsToggleButtonActionPerformed
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FreeConnectTest fct =new FreeConnectTest();
                SceneSupport.show(fct);
                fct.view.requestFocusInWindow();
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton GridsToggleButton;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
    
    
    
}
