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

import java.awt.Dialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.InputEvent;
import java.util.Hashtable;
import java.util.Vector;
import java.util.StringTokenizer;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.JList;

import org.openide.TopManager;
import org.openide.DialogDescriptor;
import org.openide.NotifyDescriptor;


/** Panel for dialog for adding new Context
 *
 *  @author Ales Novak, Tomas Zezula
 */
final class NewJndiRootPanel extends GridBagPanel implements ItemListener, ActionListener {
  /* this two static constants are for testing only
  in final version only valid constructor will be NewJndiRootPanel(String[],String[])*/

  public static final String[] factories = {"com.sun.jndi.fscontext.RefFSContextFactory","com.sun.jndi.cosnaming.CNCtxFactory","com.sun.jndi.rmi.registry.RegistryContextFactory","com.sun.jndi.nis.NISCtxFactory","com.sun.jndi.ldap.LdapCtxFactory", "com.gemstone.naming.GsCtxFactory"};
  public static final String[] protocols = {"file://","iiop://localhost:900","rmi://localhost:1099","nis://","ldap://", "gsj://localhost:10200"};

  String[] proto;
  JTextField label;
  JComboBox  factory;
  JTextField context;
  JTextField authentification;
  JTextField principal;
  JTextField credentials;
  JList list;
  JPopupMenu menu;
  Vector properties;
  Dialog dlg=null;
  NewPropertyPanel panel;    


  /** default constructor
   */
  public NewJndiRootPanel() {
    this(factories, protocols);
  }

  /** constructor takes as parameter array of factories and protocols
   * @param fcs array of factories
   * @param proto array of protocols
   */
  public NewJndiRootPanel(String[] fcs, String[] proto) {
    this.proto = proto;
    this.label = new JTextField(26);
    this.factory = new JComboBox(fcs);
    this.factory.setEditable(true);
    this.factory.setSize(this.label.getSize());
    this.factory.addItemListener(this);
    this.context = new JTextField(26);
    this.context.setText(proto[0]);
    this.authentification = new JTextField(26);
    this.principal = new JTextField(26);
    this.credentials= new JTextField(26);
    this.properties = new Vector();

    this.add(new JLabel(JndiRootNode.getLocalizedString("TXT_ContextLabel")),1,2,2,1,7,5,0,5);
    this.add(this.label,3,2,2,1,7,0,0,5);
    this.add(new JLabel(JndiRootNode.getLocalizedString("TXT_Factory")),1,3,2,1,0,5,0,5);
    this.add(this.factory,3,3,2,1,0,0,0,5);
    this.add(new JLabel(JndiRootNode.getLocalizedString("TXT_InitialContext")),1,4,2,1,0,5,0,5);
    this.add(this.context,3,4,2,1,0,0,0,5);
    this.add(new JLabel(JndiRootNode.getLocalizedString("TXT_Auth")),1,5,2,1,0,5,0,5);
    this.add(this.authentification,3,5,2,1,0,0,0,5);
    this.add(new JLabel(JndiRootNode.getLocalizedString("TXT_Principal")),1,6,2,1,0,5,0,5);
    this.add(this.principal,3,6,2,1,0,0,0,5);
    this.add(new JLabel(JndiRootNode.getLocalizedString("TXT_Credentials")),1,7,2,1,0,5,0,5);
    this.add(this.credentials,3,7,2,1,0,0,0,5);
    this.add(new JLabel(JndiRootNode.getLocalizedString("TXT_OtherProps")),5,1,2,1,0,0,0,0);
    list = new JList(this.properties);
    this.add(new JScrollPane(list),5,2,2,8);
    menu = new JPopupMenu();
    JMenuItem item = new JMenuItem(JndiRootNode.getLocalizedString("TXT_Add"));
    item.setActionCommand("ADD");
    item.addActionListener (this);				
    menu.add(item);
    item= new JMenuItem(JndiRootNode.getLocalizedString("TXT_Rem"));
    item.setActionCommand("DEL");
    item.addActionListener(this);
    menu.add(item);
    item = new JMenuItem(JndiRootNode.getLocalizedString("TXT_Change"));
    item.setActionCommand("CHANGE");
    item.addActionListener(this);
    menu.add(item);
    list.add(menu);
    list.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent event) {
        if ((event.getModifiers() & (InputEvent.BUTTON2_MASK | InputEvent.BUTTON3_MASK)) != 0) {
                 NewJndiRootPanel.this.menu.show(NewJndiRootPanel.this.list,event.getX(),event.getY());
        }
      }
    });
  }

  /** Accessor for Label
   *  @return String name of JndiRootNode
   */
  public String getLabel()  {
    return this.label.getText();
  }

  /** Accessor for Factory
   *  @return String name of Factory
   */
  public String getFactory() {
    return (String) factory.getSelectedItem();
  }

  /** Accessor for Context
   *  @return String name of starting context
   */
  public String getContext() {
    return context.getText();
  }


  /** Accessor for Autentification
   *  @return String autentification
   */
  public String getAuthentification() {
    return authentification.getText();
  }

  /** Accessor for principals
   *  @return String principals
   */
  public String getPrincipal() {
    return principal.getText();
  }

  /** Accessor for credentials
   *  @return String credentials
   */
  public String getCredentials() {
    return credentials.getText();
  }

  /** Accessor for additional properties
   *  @return Vector of type java.lang.String of format key=value
   */
  public Vector getAditionalProperties() {
    return properties;
  }

  /** Synchronization of Factory and Protocol
   *  @param event ItemEvent
   */
  public void itemStateChanged(ItemEvent event) {

    if (event.getSource() == factory) {
      int index = factory.getSelectedIndex();
      if (index != -1) {
        context.setText(proto[index]);
      }
    }
  }

  /** Action handling
   *  @param ActionEvent event
   */
  public void actionPerformed(ActionEvent event) {
    if (event.getActionCommand().equals("ADD")) {
      panel = new NewPropertyPanel();
      DialogDescriptor descriptor = new DialogDescriptor(panel,
        JndiRootNode.getLocalizedString("TITLE_Add_property"),
        true,
        DialogDescriptor.OK_CANCEL_OPTION,
        DialogDescriptor.OK_OPTION,
        new ActionListener() {
          public void actionPerformed(ActionEvent event) {
            if (event.getSource() == DialogDescriptor.OK_OPTION) {
              if ((panel.getName().length()==0) ||
                  (panel.getValue().length() == 0)) {
                TopManager.getDefault().notify(new NotifyDescriptor.Message(JndiRootNode.getLocalizedString("EXC_Params"),NotifyDescriptor.Message.ERROR_MESSAGE));
                return;
              }
              String pr = panel.getName() + "=" + panel.getValue();
              properties.add(pr);
              list.setListData(properties);
              dlg.setVisible(false);
              dlg.dispose();
            } else if (event.getSource() == DialogDescriptor.CANCEL_OPTION) {
              dlg.setVisible(false);
              dlg.dispose();
            }
          }
        }
      );
      dlg = TopManager.getDefault().createDialog(descriptor);
      dlg.setVisible(true);
    } else if (event.getActionCommand().equals("DEL")) {
      int index = NewJndiRootPanel.this.list.getSelectedIndex();
      if (index < 0) {
        return;
      }
      NewJndiRootPanel.this.properties.removeElementAt(index);
      NewJndiRootPanel.this.list.setListData(properties);
    } else if (event.getActionCommand().equals("CHANGE")) {
      panel = new NewPropertyPanel();
      int index = list.getSelectedIndex();
      if (index < 0) {
        return;
      } 
      StringTokenizer tk = new StringTokenizer((String) properties.elementAt(index), "=");
      if (tk.countTokens() != 2) return;
      panel.setName(tk.nextToken());
      panel.setValue(tk.nextToken());
      DialogDescriptor descriptor = new DialogDescriptor(panel,
        JndiRootNode.getLocalizedString("TITLE_Change_property"),
        true,  
        DialogDescriptor.OK_CANCEL_OPTION,
        DialogDescriptor.OK_OPTION,
        new ActionListener() {
          public void actionPerformed(ActionEvent event) {
            if (event.getSource() == DialogDescriptor.OK_OPTION) {
              if ((panel.getName().length() == 0) ||
                  (panel.getValue().length() == 0)) {
                TopManager.getDefault().notify(new NotifyDescriptor.Message(JndiRootNode.getLocalizedString("EXC_Params"),NotifyDescriptor.Message.ERROR_MESSAGE));
                return;
              }
              properties.removeElementAt(list.getSelectedIndex());
              String pr = panel.getName() + "=" + panel.getValue();
              properties.add(pr);
              list.setListData(properties);
              dlg.setVisible(false);
              dlg.dispose();
            } else if (event.getSource() == DialogDescriptor.CANCEL_OPTION) {
              dlg.setVisible(false);
              dlg.dispose();
            }
          }
        }
      );
      dlg = TopManager.getDefault().createDialog(descriptor);
      dlg.setVisible(true);
    }
  }
}
