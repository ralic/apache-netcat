/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2001 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package com.netbeans.enterprise.modules.jndi;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

/** Panel for adding new subdirectory */
final class NewJndiSubContextPanel extends JPanel {
  private JTextField name;    

  public NewJndiSubContextPanel() {
    this.setLayout(new BorderLayout());
    this.name = new JTextField(25);
    this.add("North", new JLabel("Subcontext name:"));
    this.add("Center", this.name); 
  }
    
  //accessor for directory name
  public String getName() {
    return name.getText();
  }
}