<?xml version='1.0' encoding='ISO-8859-1' ?>
<!--
                Sun Public License Notice

The contents of this file are subject to the Sun Public License
Version 1.0 (the "License"). You may not use this file except in
compliance with the License. A copy of the License is available at
http://www.sun.com/

The Original Code is NetBeans. The Initial Developer of the Original
Code is Sun Microsystems, Inc. Portions Copyright 1997-2001 Sun
Microsystems, Inc. All Rights Reserved.
-->

<!DOCTYPE helpset
  PUBLIC "-//Sun Microsystems Inc.//DTD JavaHelp HelpSet Version 1.0//EN"
         "http://java.sun.com/products/javahelp/helpset_1_0.dtd">

<helpset version="1.0">

<!-- last updated 17oct2000 -->

  <!-- title -->
  <title>RMI Module Help</title>
  
  <!-- maps -->
  <maps>
     <homeID>rmi_intro</homeID>
     <mapref location="rmiMap.jhm" />
  </maps>
  
  <!-- views -->
  <view>
    <name>TOC</name>
    <label>Table Of Contents</label>
    <type>javax.help.TOCView</type>
    <data>rmi-toc.xml</data> 
 </view>
 
 <view>
    <name>Index</name>
    <label>Index</label>
    <type>javax.help.IndexView</type>
    <data>rmi-idx.xml</data>
  </view>

  <view>
    <name>Search</name>
   <label>Search</label>
   <type>javax.help.SearchView</type>
   <data engine="com.sun.java.help.search.DefaultSearchEngine">
      JavaHelpSearch
   </data>
  </view>

</helpset>
