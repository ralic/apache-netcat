<?xml version="1.0" encoding="UTF-8"?>
<!--
                Sun Public License Notice

The contents of this file are subject to the Sun Public License
Version 1.0 (the "License"). You may not use this file except in
compliance with the License. A copy of the License is available at
http://www.sun.com/

The Original Code is NetBeans. The Initial Developer of the Original
Code is Sun Microsystems, Inc. Portions Copyright 1997-2004 Sun
Microsystems, Inc. All Rights Reserved.
-->
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:project="http://www.netbeans.org/ns/project/1"
                xmlns:groovyproject="http://www.netbeans.org/ns/groovy-project/1"
                xmlns:xalan="http://xml.apache.org/xslt"
                exclude-result-prefixes="xalan project groovyproject">
    <xsl:output method="xml" indent="yes" encoding="UTF-8" xalan:indent-amount="4"/>
    <xsl:template match="/">
    
        <!-- Annoyingly, the JAXP impl in JRE 1.4.2 seems to randomly reorder attrs. -->
        <!-- (I.e. the DOM tree gets them in an unspecified order?) -->
        <!-- As a workaround, use xsl:attribute for all but the first attr. -->
        <!-- This seems to produce them in the order you want. -->
        <!-- Tedious, but appears to do the job. -->
        <!-- Important for build.xml, which is very visible; not so much for build-impl.xml. -->

        <xsl:comment> You may freely edit this file. See commented blocks below for </xsl:comment>
        <xsl:comment> some examples of how to customize the build. </xsl:comment>
        <xsl:comment> (If you delete it and reopen the project it will be recreated.) </xsl:comment>
        
        <xsl:variable name="name" select="/project:project/project:configuration/groovyproject:data/groovyproject:name"/>
        <!-- Synch with build-impl.xsl: -->
        <!-- XXX really should translate all chars that are *not* safe (cf. PropertyUtils.getUsablePropertyName): -->
        <xsl:variable name="codename" select="translate($name, ' ', '_')"/>
        <project name="{$codename}">
            <xsl:attribute name="default">default</xsl:attribute>
            <xsl:attribute name="basedir">.</xsl:attribute>
            <description>Builds and runs the project <xsl:value-of select="$name"/>.</description>
            <import file="nbproject/build-impl.xml"/>

            <xsl:comment><![CDATA[

    There exist several targets which are by default empty and which can be 
    used for execution of your tasks. These targets are usually executed 
    before and after some main targets. They are: 

      -pre-init:                 called before initialization of project properties
      -post-init:                called after initialization of project properties
      -pre-compile:              called before javac compilation
      -post-compile:             called after javac compilation
      -pre-compile-single:       called before javac compilation of single file
      -post-compile-single:      called after javac compilation of single file
      -pre-jar:                  called before JAR building
      -post-jar:                 called after JAR building
      -post-clean:               called after cleaning build products

    (Targets beginning with '-' are not intended to be called on their own.)

    Example of inserting an obfuscator after compilation could look like this:

        <target name="-post-compile">
            <obfuscate>
                <fileset dir="${build.classes.dir}"/>
            </obfuscate>
        </target>

    For list of available properties check the imported 
    nbproject/build-impl.xml file. 


    Another way to customize the build is by overriding existing main targets.
    The targets of interest are: 

      -init-macrodef-javac:     defines macro for javac compilation
      -init-macrodef-java:      defines macro for class execution
      -do-jar-with-manifest:    JAR building (if you are using a manifest)
      -do-jar-without-manifest: JAR building (if you are not using a manifest)
      run:                      execution of project 

    An example of overriding the target for project execution could look like this:

        <target name="run" depends="]]><xsl:value-of select="$codename"/><![CDATA[-impl.jar">
            <exec dir="bin" executable="launcher.exe">
                <arg file="${dist.jar}"/>
            </exec>
        </target>

    Notice that the overridden target depends on the jar target and not only on 
    the compile target as the regular run target does. Again, for a list of available 
    properties which you can use, check the target you are overriding in the
    nbproject/build-impl.xml file. 

    ]]></xsl:comment>

        </project>

    </xsl:template>
    
</xsl:stylesheet> 