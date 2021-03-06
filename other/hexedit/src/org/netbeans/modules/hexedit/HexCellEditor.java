/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
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
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
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
 */
/*
 * HexCellEditor.java
 *
 * Created on April 28, 2004, 12:00 AM
 */

package org.netbeans.modules.hexedit;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author  tim
 */
class HexCellEditor implements TableCellEditor, ActionListener {
    private ArrayList listeners = new ArrayList();
    private JTextField ed = new JTextField();
    
    /** Creates a new instance of HexCellEditor */
    public HexCellEditor() {
        ed.setHorizontalAlignment(SwingConstants.TRAILING);
        ed.addActionListener (this);
        ed.getInputMap(JComponent.WHEN_FOCUSED).put (
            KeyStroke.getKeyStroke (KeyEvent.VK_ESCAPE, 0), "cancel");  //NOI18N
        ed.getActionMap().put("cancel", new CancelAction()); //NOI18N
    }

    public void focusEditor() {
        String text = ed.getText();
        if (text != null && text.length() > 0) {
            ed.setSelectionStart(0);
            ed.setSelectionEnd(text.length());
        }
        ed.requestFocus();
    }

    private class CancelAction extends AbstractAction {
        public void actionPerformed(ActionEvent actionEvent) {
            cancelCellEditing();
        }
    }
    
    public void cancelCellEditing() {
        fire (true);
        ed.setText ("");
        editingClass = null;
    }
    
    public boolean stopCellEditing() {
        fire (false);
        ed.setText ("");
        editingClass = null;
        return true;
    }
    
    private void fire (boolean cancelled) {
        ChangeEvent ce = new ChangeEvent (this);
        List l = null;
        synchronized (this) {
            l = new ArrayList(listeners);
        }
        for (Iterator i=l.iterator(); i.hasNext();) {
            CellEditorListener e = (CellEditorListener) i.next();
            if (cancelled) {
                e.editingCanceled(ce);
            } else {
                e.editingStopped(ce);
            }
        }
    }
    
    public Object getCellEditorValue() {
        String s = ed.getText();
        Object result = null;
        try {
            result = Util.convertFromString (s, editingClass);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(ed, e.getLocalizedMessage(),
                Util.getMessage("TITLE_BAD_VALUE"), JOptionPane.WARNING_MESSAGE);
        }
        return result;
    }
    
    private Class editingClass = null;
    public Component getTableCellEditorComponent(JTable jTable, Object o, boolean sel, int row, int col) {
        if (o == null || o == HexTableModel.PARTIAL_VALUE) {
            return null;
        }
        editingClass = o.getClass();
        String txt = Util.convertToString(o);
        ed.setText (txt);
        ed.setForeground (jTable.getForeground());
        ed.setBackground (jTable.getBackground());
        ed.setFont (jTable.getFont());
        return ed;
    }
    
    public void actionPerformed (ActionEvent ae) {
        stopCellEditing();
    }
    
    public boolean isCellEditable(EventObject o) {
        if (o instanceof MouseEvent) {
            int clickCount = ((MouseEvent) o).getClickCount();
            return clickCount > 0 && clickCount % 2 == 0;
        }
        return true;
    }
    
    public synchronized void removeCellEditorListener(CellEditorListener l) {
        listeners.remove(l);
    }
    
    public boolean shouldSelectCell(EventObject eventObject) {
        return true;
    }
    
    public synchronized void addCellEditorListener(CellEditorListener l) {
        listeners.add(l);
    }
}
