<?xml version="1.0" encoding="UTF-8" ?>
<!--
                Sun Public License Notice

The contents of this file are subject to the Sun Public License
Version 1.0 (the "License"). You may not use this file except in
compliance with the License. A copy of the License is available at
http://www.sun.com/

The Original Code is NetBeans. The Initial Developer of the Original
Code is Sun Microsystems, Inc. Portions Copyright 1997-2003 Sun
Microsystems, Inc. All Rights Reserved.
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml"/>

    <!-- unique key over all groups of apis -->
    <xsl:key match="//api[@type='export']" name="apiGroups" use="@group" />
    
    <xsl:template match="/apis" >
        <html>
        <head>
            <!-- projects.netbeans.org -->
           <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
           <title>NetBeans Architecture Documents List</title>
            <link rel="stylesheet" href="http://www.netbeans.org/netbeans.css" type="text/css"/>

          <link REL="icon" href="http://www.netbeans.org/favicon.ico" type="image/ico" />
          <link REL="shortcut icon" href="http://www.netbeans.org/favicon.ico" />

        </head>

        <body>

        <center><h1>NetBeans Architecture Documents List</h1></center>

        <!--
        This document provides a simple list of <em>NetBeans Architecture Documents</em>. 
        If you are looking for a more detailed overview, check <a href="index.html">
        NetBeans APIs</a> list.
        
        <hr/>
        -->
        <xsl:call-template name="list-modules" />
        <hr/>
        
        </body>
        </html>
       
    </xsl:template>
    
    <xsl:template name="list-modules">
    <!--
        <h2>Content</h2>
        -->
        <ul>
            <xsl:for-each select="/apis/module" >
            	<xsl:sort select="@name" />
                <xsl:choose>
                    <xsl:when test="api" >
                       <li>
                            <a>
                                <xsl:attribute name="href">
                                    <xsl:value-of select="@target" />
                                </xsl:attribute>
                                <xsl:value-of select="@name"/>
                            </a> 
                            (<a><xsl:attribute name="href">
                                    <xsl:value-of select="substring-before(@target, '/')" /><xsl:text>/list.html</xsl:text>
                                </xsl:attribute>
                                list of changed pages</a>)
                                
                            <xsl:apply-templates select="description" />
                        </li>
                    </xsl:when>
                    <xsl:otherwise>
                            <!-- will be covered later -->
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:for-each>
            <xsl:for-each select="/apis/module" >
                <xsl:sort select="api" order="descending" />
            	<xsl:sort select="@name" />
                <xsl:choose>
                    <xsl:when test="api" >
                            <!-- covered before -->
                    </xsl:when>
                    <xsl:otherwise>
                        <li>
                            <xsl:variable name="where" select="substring-before(@target, '/')"/>
                            <b><xsl:value-of select="$where"/></b>
                            <!--
                            <b><a href="{$where}/index.html"><xsl:value-of select="$where"/></a></b>
                            - no API description provided
                            (see <a href="http://openide.netbeans.org/tutorial/api.html">how to do it</a>)
                            -->
                        </li>
                    </xsl:otherwise>
                </xsl:choose>
             </xsl:for-each>
        </ul>
    </xsl:template>

    <xsl:template match="module">
            <xsl:variable name="interfaces" select="api[@type='export']" />
            <xsl:variable name="module.name" select="@name" />
            <xsl:variable name="arch.stylesheet" select="@stylesheet" />
            <xsl:variable name="arch.overviewlink" select="@overviewlink" />
            <xsl:variable name="arch.footer" select="@footer" />
            <xsl:variable name="arch.target" select="@target" />

            <xsl:if test="$interfaces">
                <h3>

                    <a name="def-api-{$module.name}"><xsl:value-of select="$module.name"/></a>
                    
                    (<a>
                        <xsl:attribute name="href">
                            <xsl:call-template name="filedirapi" >
                                <xsl:with-param name="arch.target" select="$arch.target" />
                            </xsl:call-template>
                            <xsl:text>/index.html</xsl:text>
                        </xsl:attribute>
                        <xsl:text>javadoc</xsl:text>
                    </a>,
                    <a>
                        <xsl:attribute name="href">
                            <xsl:call-template name="filedirapi" >
                                <xsl:with-param name="arch.target" select="$arch.target" />
                            </xsl:call-template>
                            <xsl:text>.zip</xsl:text>
                        </xsl:attribute>
                        <xsl:text>download</xsl:text>
                    </a>)
                </h3>

                <div><xsl:apply-templates select="description"/></div>

                <xsl:if test="deploy-dependencies">
                    <div>
                       <p><b>Usage:</b></p>
                       <xsl:apply-templates select="deploy-dependencies"/>
                    </div>
                </xsl:if>

                <p/>
                <table border="3" cellpadding="6" width="90%">
                    <thead>
                        <th valign="bottom" width="30%"><b>Interface Name</b></th>
                        <th valign="bottom" width="15%"><b>Stability Classification</b></th>
                        <th valign="bottom" width="45%"><b>Specified in What Document?</b></th>
                    </thead>

                    <xsl:for-each select="$interfaces">
                        <tr/>
                        <xsl:if test="@group='java'" >
                            <xsl:call-template name="api" />
                        </xsl:if>
                    </xsl:for-each>

                    <xsl:for-each select="//api[generate-id() = generate-id(key('apiGroups', @group))]">
                        <xsl:variable name="grp" select="@group" />
                        <xsl:if test="$grp!='java'" >
                            <xsl:variable name="apis" select="/apis" />
                            <xsl:variable name="module" select="$apis/module[@name=$module.name]" />

                            <xsl:variable name="allOfTheGroup" select="$module/api[@group=$grp]" />
                            <xsl:if test="$allOfTheGroup">
                                <tr/>
                                <td>Set of <xsl:value-of select="$grp"/> APIs</td>
                                <td>Individual</td>
                                <td>
                                    <a href="{$arch.target}#group-{$grp}">table with definitions</a>
                                </td>
                            </xsl:if>
                        </xsl:if>
                    </xsl:for-each>

                </table>
            </xsl:if>


            <P/>

    </xsl:template>

    <xsl:template name="api">
        <xsl:variable name="name" select="@name" />
        <xsl:variable name="type" select="@type" />
        <xsl:variable name="category" select="@category" />
        <xsl:variable name="url" select="@url" />

        <tbody>
            <td>
                <xsl:value-of select="$name"/>
            </td>
            <!--
            <td>
                <xsl:choose>
                    <xsl:when test="$type='import'">Imported</xsl:when>
                    <xsl:when test="$type='export'">Exported</xsl:when>
                    <xsl:otherwise>WARNING: <xsl:value-of select="$type" /></xsl:otherwise>
                </xsl:choose>
            </td> -->
            <td> <!-- stability category -->
                <xsl:choose>
                    <xsl:when test="$category='official'">Official</xsl:when>
                    <xsl:when test="$category='stable'">Stable</xsl:when>
                    <xsl:when test="$category='devel'">Under Development</xsl:when>
                    <xsl:when test="$category='third'">Third party</xsl:when>
                    <xsl:when test="$category='standard'">Standard</xsl:when>
                    <xsl:when test="$category='friend'">Friend</xsl:when>
                    <xsl:when test="$category='private'">Private</xsl:when>
                    <xsl:when test="$category='deprecated'">Deprecated</xsl:when>
                    <xsl:otherwise>
                        <xsl:message>
                            WARNING: <xsl:value-of select="$category"/>
                        </xsl:message>
                    </xsl:otherwise>
                </xsl:choose>
            </td>

            <td> <!-- url -->
                <a href="{$url}"><xsl:value-of select="$url"/></a>
            </td>
        </tbody>

    </xsl:template>

    <xsl:template match="api-ref">
        <!-- simply bold the name, it link will likely be visible bellow -->
        <b>
            <xsl:value-of select="@name" />
        </b>
    </xsl:template>

    <!-- extracts first part before slash from LoadersAPI/bleble.html or
     and prints it or prints OpenAPIs as a fallback -->

    <xsl:template name="filedirapi" >
        <xsl:param name="arch.target" />
    
        <xsl:if test="substring-before($arch.target,'/')">
            <xsl:value-of select="substring-before($arch.target,'/')" />
        </xsl:if>
        <xsl:if test="not (substring-before($arch.target,'/'))">
            <xsl:text>OpenAPIs</xsl:text>
        </xsl:if>
    </xsl:template>


    <!-- Format random HTML elements as is: -->
    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

    <!-- Gets the first sentence with HTML tags -->
    
    <xsl:template mode="first-sentence" match="api-ref">
        <b><xsl:value-of select="@name" /></b><xsl:text> </xsl:text>
    </xsl:template>

    <xsl:template mode="first-sentence" match="node()">
        <xsl:choose>
            <xsl:when test="count(child::*) = 0" >
                <xsl:variable name="first-sentence" select="substring-before(normalize-space(), '. ')" />
                <xsl:variable name="first-dot" select="substring-before(normalize-space(), '.')" />
                <xsl:choose>
                    <xsl:when test="$first-sentence" >
                        <xsl:value-of select="$first-sentence" />
                        <!-- this trick starts comment which disables output produces after 
                           Which means comments out everything after the .
                           -->
                        <xsl:text disable-output-escaping="yes">&lt;!--</xsl:text>
                    </xsl:when>
                    <xsl:when test="$first-dot" >
                        <xsl:value-of select="$first-dot" />
                        <!-- this trick starts comment which disables output produces after 
                           Which means comments out everything after the .
                           -->
                        <xsl:text disable-output-escaping="yes">&lt;!--</xsl:text>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="." />
                    </xsl:otherwise>
                </xsl:choose>
                <xsl:apply-templates mode="first-sentence" select="child::*"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:apply-templates mode="first-sentence" select="node()"/>
            </xsl:otherwise>
        </xsl:choose>
        
    </xsl:template>
    
</xsl:stylesheet>


