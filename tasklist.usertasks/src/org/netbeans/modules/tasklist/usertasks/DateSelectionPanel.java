/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2003 Sun
 * Microsystems, Inc. All Rights Reserved.
 */
package org.netbeans.modules.tasklist.usertasks;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import org.openide.explorer.propertysheet.editors.EnhancedCustomPropertyEditor;

/**
 * This is a small panel to allow the user to select a full date. When I
 * started the implementation of the Alarm functionality I had the user to
 * write the complete time/date, and I pretty soon realized that noone will
 * remember the format each time.... Well, the panel "works for me now" so I
 * move on to the next phase in my project, but one should really:
 *
 * @todo The panel is too big...
 *
 * @author  Trond Norbye
 */
public class DateSelectionPanel extends javax.swing.JPanel
    implements EnhancedCustomPropertyEditor {

    private static final long serialVersionUID = 1;

    /**
     * A SimpleDateFormat I use for conversion to/from textual representation
     * of date fields.
     */
    private SimpleDateFormat format;
   
    /** Creates new form DateSelectionPanel. */
    public DateSelectionPanel() {
        this(new Date());
    }

    /**
     * Create a new DateSelectionPanel with the given date selected...
     *
     * @param date initial selection
     */
    public DateSelectionPanel(Date date) {
        initComponents();
        jCalendar.setDate(date);
    }
    
    /**
     * Returns the selected date
     *
     * @return selected date
     */
    public Date getDate() {
        return jCalendar.getDate();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        jCalendar = new com.toedter.calendar.JCalendar();
        jLabel1 = new javax.swing.JLabel();
        timeFld = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout());

        setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(11, 11, 12, 12)));
        jPanel1.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(0, 0, 11, 0)));
        jPanel1.add(jCalendar);

        jLabel1.setText("@");
        jPanel1.add(jLabel1);

        timeFld.setColumns(8);
        timeFld.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        timeFld.setInputVerifier(new javax.swing.InputVerifier() {
            public boolean verify(javax.swing.JComponent obj) {
                boolean ret;
                try {
                    format.applyPattern("HH:mm:ss");
                    format.parse(((javax.swing.JTextField)obj).getText());
                    ret = true;
                } catch (Exception e) {
                    ret = false;
                }
                return ret;
            }
        });
        jPanel1.add(timeFld);

        add(jPanel1, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JCalendar jCalendar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField timeFld;
    // End of variables declaration//GEN-END:variables
    
    // When used as a property customizer
    public Object getPropertyValue() throws IllegalStateException {
        return getDate();	    
    }
}
