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

package com.netbeans.enterprise.modules.corba.settings;

import java.io.*;

import com.netbeans.ide.options.SystemOption;
//import com.netbeans.ide.options.ContextSystemOption;
import com.netbeans.ide.util.NbBundle;

import com.netbeans.developer.modules.loaders.java.settings.JavaSettings;

import java.util.Properties;
import java.util.Vector;
import java.util.Enumeration;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import com.netbeans.ide.TopManager;
import com.netbeans.ide.filesystems.FileObject;
import com.netbeans.enterprise.modules.corba.*;

public class CORBASupportSettings extends SystemOption implements PropertyChangeListener {
   
   private static final boolean DEBUG = false;
   //private static final boolean DEBUG = true;

   private String[] checkSections = {"CTL_NAME", "IMPORT", "SETTINGS_ORB_PROPERTIES", 
				     "ORB_SERVER_INIT", "ORB_CLIENT_INIT", "ORB_SERVER_RUN",
				     "ORB_OBJECT_ACTIVATION", "DIR_PARAM", 
				     "PACKAGE_PARAM", "COMPILER", "PACKAGE_DELIMITER",
				     "ERROR_EXPRESSION", "FILE_POSITION", "LINE_POSITION",
				     "COLUMN_POSITION", "MESSAGE_POSITION", "TIE_PARAM"};

   private String[] cbindings = {"NS", "IOR_FROM_FILE", "IOR_FROM_INPUT", "BINDER"};

   private String[] sbindings = {"NS", "IOR_TO_FILE", "IOR_TO_OUTPUT", "BINDER"};



   private Vector clientBindings;

   private Vector serverBindings;

   private Vector props;

   private Vector names;

   public static String orb;
   
   public static String skels = CORBASupport.INHER;

   public static String params;

   //public static String _server_binding = CORBASupport.SERVER_NS;
   public static String _server_binding;

   //public static String _client_binding = CORBASupport.CLIENT_NS;    
   public static String _client_binding;

   // advanced settings

   public static String _test;
    
   public static File idl;
    
   public static String _tie_param;

   public static String _package_param;
    
   public static String _dir_param;
    
   public static String _orb_class;
    
   public static String _orb_singleton;
    
   public static String _orb_import;
    
   public static String _package_delimiter;
    
   public static String _error_expression;
    
   public static String _file_position;
    
   public static String _line_position;
    
   public static String _column_position;
    
   public static String _message_position;

   private boolean _is_tie;

   public static String _table = "USER="+System.getProperty("user.name")+"\n"; 
      //      + "VERSION="+System.getProperty ("com.netbeans.ide.major.version")+"\n";


   String addition = "";

   /** @return human presentable name */
   public String displayName() {
      return CORBASupport.bundle.getString("CTL_CORBASupport_options");
   }
  
   public CORBASupportSettings () {
      //	setOrb (CORBASupport.bundle.getString ("CTL_ORBIX"));
      //addOption (getCORBASupportAdvancedSettings ());
      if (DEBUG)
	 System.out.println ("CORBASupportSettings () ...");
      //names = new Vector (5);
      props = new Vector (5);
      clientBindings = new Vector (5);
      serverBindings = new Vector (5);
      //loadImpl ();
      addPropertyChangeListener (this); 
      //addOption (getCORBASupportAdvancedSettings ());
      //      setOrb (CORBASupport.bundle.getString ("CTL_ORBIX"));
      //this.getCookieSet.add (UpdateCookie.class);
   }

   public void propertyChange (PropertyChangeEvent event) {
      
      if (DEBUG)
      	 System.out.println ("propertyChange: " + event.getPropertyName ());
      if (event.getPropertyName ().equals ("orb"))
	 setAdvancedOrbOptions ((String) event.getNewValue ());
      if (event.getPropertyName ().equals ("_client_binding"))
	 setAdvancedClientBinding ((String) event.getNewValue ());
      if (event.getPropertyName ().equals ("_server_binding"))
	 setAdvancedServerBinding ((String) event.getNewValue ());
      
   }

   public void setAdvancedClientBinding (String binding) {

      if (DEBUG)
	 System.out.println ("client binding: " + binding);
      //if (DEBUG)
      // System.out.println ("ctl_client_binding: " + getCtlClientBindingName ());
      setJavaTemplateTable ();
   }

   public void setAdvancedServerBinding (String binding) {

      if (DEBUG)
	 System.out.println ("server binding: " + binding);
      //if (DEBUG)
      //	 System.out.println ("ctl_server_binding: " + getCtlServerBindingName ());
      setJavaTemplateTable ();
   }
   

   public Vector getNames () {
      
      if (names == null) {
	 // lazy initialization
	 names = new Vector (5);
	 loadImpl ();
      }

      return names;
   }

   public String getOrb () {
      //loadImpl ();
      return orb;
   }

   public void setOrb (String s) {
      String old = "";
      orb = s;
      try {
	 firePropertyChange ("orb", old, orb);
      } catch (Exception e) {
	 e.printStackTrace ();
      }
      //setAdvancedOptions ();
      //loadImpl (); -- it's for template debuging only !!!
   }

   /*

   public String getOrbName () {
      
      String name = "";

      if (orb.equals (CORBASupport.ORBIX))
	 name = "ORBIX";
      if (orb.equals (CORBASupport.VISIBROKER))
	 name = "VISIBROKER";
      if (orb.equals (CORBASupport.ORBACUS))
	 name = "ORBACUS";
      if (orb.equals (CORBASupport.JAVAORB))
	 name = "JAVAORB";

      return name;
   }
   
   public String getCtlOrbName () {
      return "CTL_" + getOrbName () + "_";
   }

   */


   public String getClientBindingName () {

      String name = "";

      if (_client_binding != null) {
	 if (_client_binding.equals (CORBASupport.CLIENT_NS))
	    name = "NS";
	 if (_client_binding.equals (CORBASupport.CLIENT_IOR_FROM_FILE))
	    name = "IOR_FROM_FILE";
	 if (_client_binding.equals (CORBASupport.CLIENT_IOR_FROM_INPUT))
	    name = "IOR_FROM_INPUT";
	 if (_client_binding.equals (CORBASupport.CLIENT_BINDER))
	    name = "BINDER";
      }
      return name;
   }

   /*
   public String getCtlClientBindingName () {
      return getCtlOrbName () + "CLIENT_" + getClientBindingName ();
   }
   */

   public String getServerBindingName () {

      String name = "";

      if (_server_binding != null) {
	 if (_server_binding.equals (CORBASupport.SERVER_NS))
	    name = "NS";
	 if (_server_binding.equals (CORBASupport.SERVER_IOR_TO_FILE))
	    name = "IOR_TO_FILE";
	 if (_server_binding.equals (CORBASupport.SERVER_IOR_TO_OUTPUT))
	    name = "IOR_TO_OUTPUT";
	 if (_server_binding.equals (CORBASupport.SERVER_BINDER))
	    name = "BINDER";
      }
      return name;
   }

   /*
   public String getCtlServerBindingName () {
      return getCtlOrbName () + "SERVER_" + getServerBindingName ();
   }
   */

   public String getSkels () {
      return skels;
   }

   public void setSkels (String s) {
      String old = skels;
      skels = s;
      firePropertyChange ("skels", old, skels);
   }

   public void setParams (String s) {
      String old = params;
      params = s;
      firePropertyChange ("params", old, params);
   }

   public String getParams () {
      return params;
   }

   public static String param () {
      return params;
   }


   public String getClientBinding () {
      return _client_binding;
   }

   public void setClientBinding (String s) {
       String old = _client_binding;
      _client_binding = s;
      firePropertyChange ("_client_binding", old, _client_binding);
   }

   public String getServerBinding () {
      return _server_binding;
   }

   public void setServerBinding (String s) {
       String old = _server_binding;
      _server_binding = s;
      firePropertyChange ("_server_binding", old, _server_binding); 
   }

    
   // advanced settings
   public File getIdl () {
      return idl;
   }

   public static String idl () {
      return idl.getPath ();
   }

   public void setIdl (File s) {
      File old = idl;
      idl = s;
      firePropertyChange ("idl", old, idl);
   }

   public void setTieParam (String s) {
      String old = _tie_param;
      _tie_param = s;
      firePropertyChange ("_tie_param", old, _tie_param);
   }

   public boolean isTie () {

      if (skels.equals (CORBASupport.TIE)) {
	 _is_tie = true;
	 if (DEBUG)
	    System.out.println ("is TIE");
      }
      else {
	 _is_tie = false;
	 if (DEBUG)
	    System.out.println ("isn't TIE");
      }

      return _is_tie;
   }

   public String getTieParam () {
      return _tie_param;
   }

   //public static String tie_param () {
   //   return _tie_param;
   //}

   public void setPackageParam (String s) {
      String old = _package_param;
      _package_param = s;
      firePropertyChange ("_package_param", old, _package_param);
   }

   public String getPackageParam () {
      return _package_param;
   }

   public static String package_param () {
      return _package_param;
   }

   public void setDirParam (String s) {
      String old = _dir_param;
      _dir_param = s;
      firePropertyChange ("_dir_param", old, _dir_param);
   }

   public String getDirParam () {
      return _dir_param;
   }

   public static String dir_param () {
      return _dir_param;
   }

   public String getPackageDelimiter () {
      return _package_delimiter;
   }

   public void setPackageDelimiter (String s) {
      String old = _package_delimiter;
      _package_delimiter = s;
      firePropertyChange ("_package_delimiter", old, _package_delimiter);
   }

   public char delim () {
      return _package_delimiter.charAt (0);
   }

   public String getErrorExpression () {
      return _error_expression;
   }

   public void setErrorExpression (String s) {
      String old = _error_expression;
      _error_expression = s;
      firePropertyChange ("_error_expression", old, _error_expression);
   }

   public static String expression () {
      return _error_expression;
   }

   public String getFilePosition () {
      return _file_position;
   }

   public void setFilePosition (String s) {
      String old = _file_position;
      _file_position = s;
      firePropertyChange ("_file_position", old, _file_position);
   }

   public int file () {
      return new Integer(_file_position).intValue ();
   }

   public String getLinePosition () {
      return _line_position;
   }

   public void setLinePosition (String s) {
      String old = _line_position;
      _line_position = s;
      firePropertyChange ("_line_position", old, _line_position);
   }

   public int line () {
      return new Integer(_line_position).intValue ();
   }

   public String getColumnPosition () {
      return _column_position;
   }

   public void setColumnPosition (String s) {
      String old = _column_position;
      _column_position = s;
      firePropertyChange ("_column_position", old, _column_position);
   }

   public int column () {
      return new Integer(_column_position).intValue ();
   }

   public String getMessagePosition () {
      return _message_position;
   }

   public void setMessagePosition (String s) {
      String old = _message_position;
      _message_position = s;
      firePropertyChange ("_message_position", old, _message_position);
   }

   public int message () {
      return new Integer(_message_position).intValue ();
   }

   public void setReplaceableStringsTable (String s) {
      String old = _table;
      _table = s;
      firePropertyChange ("_table", old, _table);
   }

   public String getRaplaceableStringsTable () {
      return _table;
   }
   
   public Properties getReplaceableStringsProps () {
      Properties props = new Properties ();
      try {
	 props.load (new StringBufferInputStream(_table));
      }
      catch (IOException e) {
      }
      return props;
   }

   public void fireChangeChoices () {
      firePropertyChange ("_client_binding", null, null);
      firePropertyChange ("_server_binding", null, null);
   }      

 
   public String[] getClientBindingsChoices () {

      String[] choices;
      choices = new String[1];
      choices[0] = new String ("");
      int index = -1;
      int length = -1;

      for (int i=0; i<getNames ().size (); i++) {
	 if (DEBUG)
	    System.out.println ("names[" + i + "] = " + getNames ().elementAt (i));
	 if (getNames ().elementAt (i).equals (orb)) {
	    index = i;
	    break;
	 }
      }
      if (index >= 0) {
	 length = ((Vector)clientBindings.elementAt (index)).size ();
	 choices = new String[length];
	 if (DEBUG) {
	    System.out.println ("index: " + index);
	    System.out.println ("orb: " + orb);
	    System.out.println ("length: " + length);
	    System.out.println ("bindings: " + (Vector)clientBindings.elementAt (index));
	 }
      }
      if (index >= 0)
	 for (int i=0; i<length; i++) {
	    choices[i] = (String)((Vector)clientBindings.elementAt (index)).elementAt (i);
	    if (DEBUG)
	       System.out.println ("choice: " + choices[i]);
	 }

      return choices;
   }



   public String[] getServerBindingsChoices () {

      String[] choices;
      choices = new String[1];
      choices[0] = new String ("");
      int index = -1;
      int length = -1;

      for (int i=0; i<getNames ().size (); i++) {
	 if (DEBUG)
	    System.out.println ("names[" + i + "] = " + getNames ().elementAt (i));
	 if (getNames ().elementAt (i).equals (orb)) {
	    index = i;
	    break;
	 }
      }
      if (index >= 0) {
	 length = ((Vector)serverBindings.elementAt (index)).size ();
	 choices = new String[length];
	 if (DEBUG) {
	    System.out.println ("index: " + index);
	    System.out.println ("orb: " + orb);
	    System.out.println ("length: " + length);
	    System.out.println ("bindings: " + (Vector)serverBindings.elementAt (index));
	 }
      }
      if (index >= 0)
	 for (int i=0; i<length; i++) {
	    choices[i] = (String)((Vector)serverBindings.elementAt (index)).elementAt (i);
	    if (DEBUG)
	       System.out.println ("choice: " + choices[i]);
	 }

      return choices;
   }


   public void setAdvancedOrbOptions (String orb) {

      if (DEBUG)
	 System.out.println ("orb: " + orb);

      if (DEBUG)
	 System.out.println ("setAdvancedOptions :)");
      JavaSettings js = (JavaSettings)JavaSettings.findObject (JavaSettings.class, true);

      //ClientBindingPropertyEditor cbedit = (ClientBindingPropertyEditor)ClientBindingPropertyEditor.findObject (ClientBindingPropertyEditor.class, true);
      //cbedit.setChoices (getChoices);
      
      String old_tie = getTieParam ();
      String old_dir = getDirParam ();
      String old_package = getPackageParam ();
      String old_expression = getErrorExpression ();
      String old_file = getFilePosition ();
      String old_line = getLinePosition ();
      String old_column = getColumnPosition ();
      String old_message = getMessagePosition ();
      String new_expression = "";
      String new_file = "";
      String new_line = "";
      String new_column = "";
      String new_message = "";
      String new_dir = "";
      String new_package = "";
      String new_tie = "";
      File old_idl = getIdl ();
      File new_idl = new File ("noname_idl");
      String old_delimiter = getPackageDelimiter();
      String new_delimiter = ".";
	
      setJavaTemplateTable ();

      int index = -1;

      for (int i = 0; i<getNames ().size (); i++) {
	 if (getNames ().elementAt (i).equals (orb)) {
	    index = i;
	    break;
	 }
      }

      if (index == -1)
	 return;

      new_tie = ((Properties)props.elementAt (index)).getProperty ("TIE_PARAM");
      new_dir = ((Properties)props.elementAt (index)).getProperty ("DIR_PARAM");
      new_package = ((Properties)props.elementAt (index)).getProperty ("PACKAGE_PARAM");
      new_idl = new File (((Properties)props.elementAt (index)).getProperty ("COMPILER"));
      new_expression = ((Properties)props.elementAt (index)).getProperty ("ERROR_EXPRESSION");
      new_file = ((Properties)props.elementAt (index)).getProperty ("FILE_POSITION");
      new_line = ((Properties)props.elementAt (index)).getProperty ("LINE_POSITION");
      new_column = ((Properties)props.elementAt (index)).getProperty ("COLUMN_POSITION");
      new_message = ((Properties)props.elementAt (index)).getProperty ("MESSAGE_POSITION");
      new_delimiter = ((Properties)props.elementAt (index)).getProperty ("PACKAGE_DELIMITER");

      setTieParam (new_tie);
      setDirParam (new_dir);
      setPackageParam (new_package);
      setIdl (new_idl);
      setErrorExpression (new_expression);
      setFilePosition (new_file);
      setLinePosition (new_line);
      setColumnPosition (new_column);
      setMessagePosition (new_message);
      setPackageDelimiter (new_delimiter);

      if (DEBUG)
	 System.out.println ("setAdvancedOptions () - end!");

   }

   public void setJavaTemplateTable () {

      int index = 0;

      String tmp_property;

      if (DEBUG)
	 System.out.println ("setJavaTemplateTable");

      JavaSettings js = (JavaSettings)JavaSettings.findObject (JavaSettings.class, true);
      Properties p = js.getReplaceableStringsProps ();

      if (orb == null)
	 return;

      try {
	 if (DEBUG)
	    System.out.println ("orb: " + orb);
	 
	 for (int i = 0; i<getNames ().size (); i++) {
	    if (getNames ().elementAt (i).equals (orb)) {
	       index = i;
	       break;
	    }
	 }

	 //if (DEBUG)
	 //   System.out.println ("props at position: " + props.elementAt (index));

	 //if (DEBUG) 
	 //   System.out.println (((Properties)props.elementAt (index)).getProperty 
	 //     ("SETTINGS_ORB_PROPERTIES"));

      //if (DEBUG)
      //    System.out.println ("sett: " + ((Properties)props.elementAt (index)).getProperty
	 //			("SETTINGS_ORB_PROPERTIES"));
	 if (DEBUG)
	    System.out.println ("cb: " + getClientBindingName ());
	 if (DEBUG)
	    System.out.println ("sb: " + getServerBindingName ());	 

	 p.setProperty ("ORB_NAME", orb);

	 if (getServerBinding () != null)
	    p.setProperty ("SERVER_BINDING", getServerBinding ());
	 if (getClientBinding () != null)
	    p.setProperty ("CLIENT_BINDING", getClientBinding ());
	 

	 p.setProperty ("SETTINGS_ORB_PROPERTIES", ((Properties)props.elementAt (index)).getProperty
			("SETTINGS_ORB_PROPERTIES"));
	 if (((Properties)props.elementAt (index)).getProperty 
	     ("IMPORT_" + getClientBindingName ()) != null) {
	    p.setProperty ("ORB_IMPORT",((Properties)props.elementAt (index)).getProperty 
			   ("IMPORT_" + getClientBindingName ()));
	 }
	 else {
	    if (((Properties)props.elementAt (index)).getProperty 
		("IMPORT_" + getServerBindingName ()) != null) {
	       p.setProperty ("ORB_IMPORT",((Properties)props.elementAt (index)).getProperty 
			      ("IMPORT_" + getServerBindingName ()));
	    }
	    else {
	       p.setProperty ("ORB_IMPORT", ((Properties)props.elementAt (index)).getProperty 
			      ("IMPORT"));
	    }
	 }

	 p.setProperty ("ORB_SERVER_INIT", ((Properties)props.elementAt (index)).getProperty 
			("ORB_SERVER_INIT"));
	 p.setProperty ("ORB_CLIENT_INIT", ((Properties)props.elementAt (index)).getProperty
			("ORB_CLIENT_INIT"));
	 if (!getClientBindingName ().equals (""))  
	    if ((tmp_property = ((Properties)props.elementAt (index)).getProperty 
		 ("CLIENT_" + getClientBindingName ())) != null)
	       p.setProperty ("ORB_CLIENT_BINDING", tmp_property);
	 
	 if (!getServerBindingName ().equals (""))
	    if ((tmp_property = ((Properties)props.elementAt (index)).getProperty 
		 ("SERVER_" + getServerBindingName ())) != null)
	       p.setProperty ("ORB_SERVER_BINDING", tmp_property);

	 p.setProperty ("ORB_OBJECT_ACTIVATION", ((Properties)props.elementAt (index)).getProperty
			("ORB_OBJECT_ACTIVATION"));

	 p.setProperty ("ORB_SERVER_RUN", ((Properties)props.elementAt (index)).getProperty 
			("ORB_SERVER_RUN"));
			
      } catch (Exception e) {
	 e.printStackTrace ();
      }


      //js.setReplaceableStringsTable 
      ByteArrayOutputStream bs = new ByteArrayOutputStream ();
      try {
	 p.store (bs, null);
      } catch (IOException e) {
	 if (DEBUG)
	    System.out.println (e);
      }
      //if (DEBUG)
      //	 System.out.println ("properties: " + bs.toString ());
      js.setReplaceableStringsTable (bs.toString ());    

   }


   public void loadImpl () {

      names = new Vector (5);
      props = new Vector (5);
      clientBindings = new Vector (5);
      serverBindings = new Vector (5);
      

      if (DEBUG)
	 System.out.println ("loadImpl () ...");

      TopManager tm = TopManager.getDefault ();
      
      try {
	 Enumeration folders = tm.getRepository ().getDefaultFileSystem ().getRoot ().getFolders 
	    (false);
	 CORBASupportSettings settings = (CORBASupportSettings)CORBASupportSettings.findObject 
	    (CORBASupportSettings.class, true);
	 for (int i=1; folders.hasMoreElements (); ) {
	    FileObject fo = (FileObject)folders.nextElement ();
	    if (fo.toString ().equals ("CORBA")) {
	       FileObject[] files = fo.getChildren ();
	       for (i = 0; i<files.length ; i++) {
		  if (DEBUG)
		     System.out.println ("file: " + files[i].toString ());
		  Properties p = new Properties ();
		  p.load (files[i].getInputStream ());
		  
		  // checking of important properties fields

		  boolean error = false;
		  for (int j=0; j<checkSections.length; j++)
		     if (p.getProperty (checkSections[j]) == null) {
			System.out.println ("error in " + files[i].toString () + " missing " 
					    + checkSections[j] + " variable.");
			error = true;
		     }
		  if (error)
		     continue;
		  if (DEBUG)
		     System.out.println ("impl: " + p.getProperty ("CTL_NAME"));
		  
		  getNames ().add (p.getProperty ("CTL_NAME"));
		  props.add (p);

		  // make client and server bindings

		  Vector tmp_clientBindings = new Vector (5);
		  for (int j=0; j<cbindings.length; j++)
		     if (p.getProperty ("CLIENT_" + cbindings[j]) != null) {
			if (DEBUG)
			   System.out.println ("add cb: " + "CTL_CLIENT_" + cbindings[j]);
			tmp_clientBindings.add (CORBASupport.bundle.getString 
					    ("CTL_CLIENT_" + cbindings[j]));
		     }
		  clientBindings.add (tmp_clientBindings);

		  Vector tmp_serverBindings = new Vector (5);
		  for (int j=0; j<sbindings.length; j++)
		     if (p.getProperty ("SERVER_" + sbindings[j]) != null) {
			if (DEBUG)
			   System.out.println ("add sb: " + "CTL_SERVER_" + sbindings[j]);
			tmp_serverBindings.add (CORBASupport.bundle.getString 
					    ("CTL_SERVER_" + sbindings[j]));
		     }
		  serverBindings.add (tmp_serverBindings);
		  //System.out.println ("props: ");
		  //props.list (System.out);
	       }
	       if (DEBUG) {
		  System.out.println ("names: " + getNames ());
		  System.out.println ("clients bindings: " + clientBindings);
		  System.out.println ("servers bindings: " + serverBindings);
	       }
	    }
	 }
      } catch (Exception e) {
	 e.printStackTrace ();
      }


   }


}




