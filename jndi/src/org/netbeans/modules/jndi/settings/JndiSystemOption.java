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

package com.netbeans.enterprise.modules.jndi.settings;

import org.openide.options.SystemOption;
import org.openide.nodes.Node;
import com.netbeans.enterprise.modules.jndi.JndiRootNode;
import com.netbeans.enterprise.modules.jndi.JndiAbstractNode;
import com.netbeans.enterprise.modules.jndi.JndiProvidersNode;
import java.util.ArrayList;
import java.util.Hashtable;


/** 
 *
 * @author  tzezula
 * @version 
 */
public class JndiSystemOption extends SystemOption {

  private static int timeOut;
  
  static final long serialVersionUID =2488076532292258803L;
  /** Creates new JndiSystemOption */
  public JndiSystemOption() {
    this.timeOut=4000;
  }
  
  public int getTimeOut(){
    return this.timeOut;
  }
  
  public void setTimeOut (int timeOut) {
    int oldTimeOut = this.timeOut;
    this.timeOut = timeOut;
    firePropertyChange("timeOut",new Integer(oldTimeOut),new Integer(timeOut));
  }
  
  public String displayName() {
    return JndiRootNode.getLocalizedString("Module_Name");
  }
  
  public void readExternal (java.io.ObjectInput in){
    try {
      timeOut = ((Integer)in.readObject()).intValue();
      ArrayList redProviders = (ArrayList) in.readObject();
      JndiRootNode node = JndiRootNode.getDefault();
      if ( node != null ) node.initStartContexts(redProviders);
    }catch (java.io.IOException ioe){timeOut=4000;}
    catch (java.lang.ClassNotFoundException cnfe) {timeOut=4000;}
  }
  
  public void writeExternal (java.io.ObjectOutput out){
    JndiRootNode node = JndiRootNode.getDefault();
    ArrayList array = null;
    if (node != null){
      Node[] nodes = node.getChildren().getNodes();
      array = new ArrayList();
      for (int i = 0; i < nodes.length; i++) {
        if (!nodes[i].getName().equals(JndiRootNode.getLocalizedString(JndiProvidersNode.DRIVERS))) {
          try {
           Hashtable contextProperties = ((JndiAbstractNode) nodes[i]).getInitialDirContextProperties();
          array.add(contextProperties);
        }catch (javax.naming.NamingException ne){}
        }
      }
    }
    try{
      out.writeObject( new Integer(timeOut));
      if (array != null) out.writeObject( array);
    }catch (java.io.IOException ioe) {}
  }
}