<?xml version="1.0" encoding="UTF-8" ?>
<!--
The contents of this file are subject to the terms of the Common Development
and Distribution License (the License). You may not use this file except in
compliance with the License.

You can obtain a copy of the License at http://www.netbeans.org/cddl.html
or http://www.netbeans.org/cddl.txt.

When distributing Covered Code, include this CDDL Header Notice in each file
and include the License file at http://www.netbeans.org/cddl.txt.
If applicable, add the following below the CDDL Header, with the fields
enclosed by brackets [] replaced by your own identifying information:
"Portions Copyrighted [year] [name of copyright owner]"

The Original Software is NetBeans. The Initial Developer of the Original
Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
Microsystems, Inc. All Rights Reserved.
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text"/>

    <xsl:template match="/">#!/bin/sh
#THIS SCRIPT LAUNCHES YOUR APPLICATION ON MACINTOSH
#  You may edit this script freely; it will be copied into the application
#  structure as part of the build process.
#
#  If not edited, it may be regenerated by the NetBeans Packager module
#  if you install a new version of it which contains a new version of this
#  script.  If it has been edited (if this file's CRC 32 checksum does not 
#  match the template's), it will not be modified.  If you have modified it,
#  but *want* it replaced by the Packager module, simply delete this file and
#  reopen the project.


#  Finds all jars in Contents/Resources and includes them in the java classpath

append_jars_to_cp() {
    dir="$1"
    for ex in jar zip ; do
        if [ "`echo "${dir}"/*.$ex`" != "${dir}/*.$ex" ] ; then
            for x in "${dir}"/*.$ex ; do
                if [ ! -z "$cp" ] ; then cp="$cp:" ; fi
                cp="$cp$x"
            done
        fi
    done
}


#  Locates the directory relative to which should be the jar files

contentsdir="`dirname $0`/.."


#  Populates the variable $cp with the list of jar files

append_jars_to_cp "$contentsdir/Resources"


#  Starts the application.  The main-class argument will be substituted by
#  the build script with the main class for the project you selected.

java -cp "$cp" -Dcom.apple.mrj.application.apple.menu.about.name="@APPNAME@" -Xdock:name="@APPNAME@" -Xdock:icon="$contentsdir/Resources/@APPNAME@.icns" -Dapple.laf.useScreenMenuBar=true  @MAIN-CLASS@

</xsl:template>

</xsl:stylesheet>
