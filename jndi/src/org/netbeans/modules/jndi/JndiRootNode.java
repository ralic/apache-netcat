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

import java.util.StringTokenizer;
import java.util.Properties;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.StringTokenizer;
import java.util.ResourceBundle;
import javax.naming.NamingException;
import javax.naming.Context;
import javax.naming.directory.DirContext;

import org.openide.TopManager;
import org.openide.NotifyDescriptor;
import org.openide.actions.NewAction;
import org.openide.actions.PropertiesAction;
import org.openide.actions.ToolsAction;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.DefaultHandle;
import org.openide.util.NbBundle;
import org.openide.util.actions.SystemAction;
import org.openide.util.datatransfer.NewType;

/** Top Level JNDI Node
 *
 * @author Ales Novak, Tomas Zezula
 */
public final class JndiRootNode extends AbstractNode {
  
  /** Name of property holding the name of context */
  public final static String NB_LABEL="NB_LABEL";
  /** Name of property holding the initial offset */
  public final static String NB_ROOT="HomePath";
  /** SystemActions*/
  protected SystemAction[] jndiactions = null;
  /** NewTypes*/
  protected NewType[] jndinewtypes = null;
  
  /** Constructor
   */
  public JndiRootNode() {
    super(new Children.Array());
    setName("JNDI");
    setIconBase(JndiIcons.ICON_BASE + JndiIcons.getIconName(JndiRootNode.NB_ROOT));
    initStartContexts();
    createProperties();
  }
  
  /** Returns name of the node
   *  @return Object the name of node
   */
  public Object getValue() {
    return getName();
  }

  /** Sets name of this node
   *  @param name name of the object
   */  
  public void setValue(Object name) {
    if (name instanceof String) {
      setName((String) name);
    }
  }
  
  /** Disable Destroying
   * @return false
   */
  public boolean canDestroy() {
    return false;
  }
  
  /** Disable Copy
   * @return false
   */
  public boolean canCopy() {
    return false;
  }
  
  /** Disable Cut
   * @return false
   */
  public boolean canCut() {
    return false;
  }
  
  /** Disable Rename
   *  @return false
   */
  public boolean canRename() {
    return false;
  }

  /** No default action
   *  @return null;
   */  
  public SystemAction getDefaultAction() {
    return null;
  }

  /** Returns actions for this node	
   *  @return array of SystemAction
   */
  public SystemAction[] getActions() {
    if (jndiactions == null) {
      jndiactions = this.createActions();
    }
    return jndiactions;
  }
  
  /** Creates actions for this node 
   *  @return array of SystemAction
   */
  public SystemAction[] createActions() {
    return new SystemAction[] {
      SystemAction.get(NewAction.class),
      null,
      SystemAction.get(ToolsAction.class),
    };
  }
  
  /** Creates an JNDI Type
   *  @return array with JndiDataType
   */
  public NewType[] getNewTypes() {
    if (jndinewtypes == null) {
      jndinewtypes= new NewType[]{ new JndiDataType(this)};
    }
    return jndinewtypes;
  }

  /** Creates handle
   *  @return Handle 
   */ 
  public Handle getHandle() {
    return DefaultHandle.createHandle(this);
  }

  /** This function adds an Context
   *  @param context adds context from String
   */	
  public void addContext(String context) throws NamingException {
    JndiNode[] nodes;	    
    nodes = new JndiNode[1]; 
    Properties env = parseStartContext(context);
    DirContext ctx = new JndiDirContext(env);
    nodes[0] = new JndiNode(ctx);
    getChildren().add(nodes);
  }
  
  /** This function adds an Context
   *  @param label name of node
   *  @param factory JndiFactory
   *  @param context starting Context
   *  @param authentification authentification to naming system
   *  @param principals principals for naming system
   *  @param credentials credentials for naming system
   *  @param prop vector type java.lang.String, additional properties in form key=value
   */
  public void addContext(String label, String factory, String context, String authentification, String principal, String credentials, Vector prop)
    throws NamingException {
      if (label==null || factory==null || label.equals("") || factory.equals("")) throw new JndiException("Arguments missing");
      JndiNode[] nodes = new JndiNode[1];
      Properties env = new Properties();
      env.put(JndiRootNode.NB_LABEL,label);
      env.put(Context.INITIAL_CONTEXT_FACTORY,factory);
      env.put(JndiRootNode.NB_ROOT,"");
      if (context != null && context.length() > 0) {
        env.put(Context.PROVIDER_URL,context);
      }
      if (authentification != null && !authentification.equals("")) {
        env.put(Context.SECURITY_AUTHENTICATION, authentification);
      }
      if (principal != null && !principal.equals("")) {
        env.put(Context.SECURITY_PRINCIPAL, principal);
      }
      if (credentials != null && !credentials.equals("")) {
        env.put(Context.SECURITY_CREDENTIALS,credentials);
      }
      for (int i = 0; i < prop.size(); i++) {
        StringTokenizer tk = new StringTokenizer(((String)prop.elementAt(i)),"=");
        if (tk.countTokens() != 2) {
          continue;
        }
        String path = tk.nextToken();
        if (path.equals(NB_ROOT)) {
          env.put(NB_ROOT, tk.nextToken());
        } else {
          env.put(path, tk.nextToken());
        }
      }
      JndiDirContext ctx = new JndiDirContext(env);
      nodes[0]= new JndiNode(ctx);
      this.getChildren().add(nodes);
  }
  
  
  
  /**This function takes a string and converts it to set of properties
   * @return Properties set of properties if Ok, null on error
   */ 
  protected Properties parseStartContext(String ident) throws NamingException {
    StringTokenizer tk = new StringTokenizer(ident,"|");
    Properties env = new Properties();
    
    try {
      env.put(JndiRootNode.NB_LABEL,tk.nextToken());
      env.put(Context.INITIAL_CONTEXT_FACTORY,tk.nextToken());
      env.put(Context.PROVIDER_URL,tk.nextToken());
    } catch(NoSuchElementException nee) {
      // The parameters above are obligatory
      throw new JndiException("Argument missing");
    }
    try {
      env.put(JndiRootNode.NB_ROOT,tk.nextToken());
    } catch(NoSuchElementException nee) {
      //If this parameter is missing set it to empty string.
      env.put(JndiRootNode.NB_ROOT,"");
    }
    try {
      env.put(Context.SECURITY_AUTHENTICATION, tk.nextToken());
      env.put(Context.SECURITY_PRINCIPAL,tk.nextToken());
      env.put(Context.SECURITY_CREDENTIALS,tk.nextToken());
    } catch(NoSuchElementException nee) {
      // no more elements
    }
    return env;
  }
  
  
  /** Set up initial start contexts
  */
  protected void initStartContexts() {
  }

  /** CreatesProperties
   */
  protected void createProperties() {
  }

  /** Notifies about an exception that was raised in non Netbeans code. 
   */
  static void notifyForeignException(Throwable t) {

    String msg;
    
    if ((t.getMessage() == null) ||
        t.getMessage().equals("")) {
          msg = t.getClass().getName();
    } else {
      msg = t.getClass().getName() + ": " + t.getMessage();
    }
    
    final NotifyDescriptor nd = new NotifyDescriptor.Exception(t, msg);
    Runnable run = new Runnable() {
      public void run() {
        TopManager.getDefault().notify(nd);
      }
    };
    java.awt.EventQueue.invokeLater(run);
  }

  /** Bundle with localizations. */
  private static ResourceBundle bundle;
  /** @return a localized string */
  static String getString(String s) {
    if (bundle == null) {
      bundle = NbBundle.getBundle(JndiRootNode.class);
    }
    return bundle.getString(s);
  }
}
