/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.languages.html;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import org.netbeans.api.lexer.Token;
import org.netbeans.api.lexer.TokenSequence;
import org.netbeans.modules.languages.Cookie;
import org.netbeans.modules.languages.LibrarySupport;
import org.netbeans.modules.languages.SyntaxCookie;
import org.netbeans.modules.languages.parser.ASTNode;
import org.netbeans.modules.languages.parser.SToken;


/**
 *
 * @author Jan Jancura
 */
public class HTML {
    
//    private static final String HTML40DOC = "modules/ext/html40.zip";
    private static final String HTML401 = "org/netbeans/modules/languages/html/HTML401.xml";
    
    
//    public static Runnable hyperlink (PTPath path) {
//        SToken t = (SToken) path.getLeaf ();
//        String s = t.getIdentifier ();
//        s = s.substring (1, s.length () - 1).trim ();
//        if (!s.endsWith (")")) return null;
//        s = s.substring (0, s.length () - 1).trim ();
//        if (!s.endsWith ("(")) return null;
//        s = s.substring (0, s.length () - 1).trim ();
//        final Line l = (Line) DatabaseManager.get (s);
//        if (l != null)
//            return new Runnable () {
//                public void run () {
//                    l.show (l.SHOW_SHOW);
//                }
//            };
//        return null;
//    }

    public static boolean isDeprecatedTag (Cookie cookie) {
        Token t = cookie.getTokenSequence ().token ();
        String tagName = t.id ().name ().toLowerCase ();
        return "true".equals (getLibrary ().getProperty ("TAG", tagName, "deprecated"));
    }

    public static boolean isEndTagRequired (Cookie cookie) {
        Token t = cookie.getTokenSequence ().token ();
        return isEndTagRequired (t.id ().name ().toLowerCase ());
    }

    static boolean isEndTagRequired (String tagName) {
        String v = getLibrary ().getProperty ("TAG", tagName, "endTag");
        return !"O".equals (v) && !"F".equals (v);
    }

    public static List tags (Cookie cookie) {
        return getLibrary ().getItems ("TAG");
    }

    private static List tagDescriptions = null;
    
    public static List tagDescriptions (Cookie cookie) {
        if (tagDescriptions == null) {
            List tags = getLibrary ().getItems ("TAG");
            tagDescriptions = new ArrayList (tags.size ());
            Iterator it = tags.iterator ();
            while (it.hasNext ()) {
                String tag = (String) it.next ();
                String description = getLibrary ().getProperty 
                    ("TAG", tag, "description");
                tagDescriptions.add (
                    "<html><b><font color=blue>" + tag.toUpperCase () + 
                    ": </font></b><font color=black> " + 
                    description + "</font></html>"
                );
            }
        }
        return tagDescriptions;
    }

    public static List attributes (Cookie cookie) {
        String tagName = tagName (cookie.getTokenSequence ());
        if (tagName == null) return Collections.EMPTY_LIST;
        List r = getLibrary ().getItems (tagName);
        System.out.println("attributes " + r);
        return r;
    }

    public static List attributeDescriptions (Cookie cookie) {
        String tagName = tagName (cookie.getTokenSequence ());
        if (tagName == null) return Collections.EMPTY_LIST;
        List as = getLibrary ().getItems (tagName);
        List attributeDescriptions = new ArrayList (as.size ());
        Iterator it = as.iterator ();
        while (it.hasNext ()) {
            String tag = (String) it.next ();
            String description = getLibrary ().getProperty 
                (tagName, tag, "description");
            attributeDescriptions.add (
                "<html><b><font color=blue>" + tag.toUpperCase () + 
                ": </font></b><font color=black> " + 
                description + "</font></html>"
            );
        }
        System.out.println("attributeDescriptions " + attributeDescriptions);
        return attributeDescriptions;
    }
    

    public static boolean isDeprecatedAttribute (Cookie cookie) {
        TokenSequence ts = cookie.getTokenSequence ();
        String attribName = ts.token ().text ().toString ().toLowerCase ();
        String tagName = tagName (cookie.getTokenSequence ());
        if (tagName == null) return false;
        return "true".equals (getLibrary ().getProperty (tagName, attribName, "deprecated"));
    }
    
    public static ASTNode process (SyntaxCookie cookie) {
        ASTNode n = (ASTNode) cookie.getPTPath ().getRoot ();
        List l = new ArrayList ();
        resolve (n, new Stack (), l, true);
        return ASTNode.create (n.getMimeType (), n.getNT (), n.getRule (), n.getParent (), l, n.getOffset ());
    }
    
    
    // private methods .........................................................

    private static String tagName (TokenSequence ts) {
        while (!ts.token ().id ().name ().equals ("html-element_name"))
            if (!ts.movePrevious ()) break;
        if (!ts.token ().id ().name ().equals ("html-element_name")) 
            return null;
        return ts.token ().text ().toString ();
    }
    
    private static LibrarySupport library;
    
    private static LibrarySupport getLibrary () {
        if (library == null)
            library = LibrarySupport.create (HTML401);
        return library;
    }
    
    private static ASTNode create (ASTNode n, String nt) {
        return ASTNode.create (n.getMimeType (), nt, n.getRule (), n.getParent (), n.getChildren (), n.getOffset ());
    }
    
    private static void resolve (ASTNode n, Stack s, List l, boolean findUnpairedTags) {
        Iterator it = n.getChildren ().iterator ();
        while (it.hasNext ()) {
            Object o = it.next ();
            if (o instanceof SToken) {
                l.add (o);
                continue;
            }
            ASTNode node = (ASTNode) o;
            if (node.getNT ().equals ("startTag")) {
                if (node.getTokenType ("html-end_element_end") != null) {
                    l.add (create (node, "simpleTag"));
                } else {
                    String name = node.getTokenTypeIdentifier ("html-element_name");
                    if (name == null) 
                        name = "";
                    else
                        name = name.toLowerCase ();
                    s.add (name);
                    s.add (new Integer (l.size ()));
                    if (findUnpairedTags && isEndTagRequired (name))
                        l.add (create (node, "unpairedStartTag"));
                    else
                        l.add (create (node, "startTag"));
                }
                continue;
            } else
            if (node.getNT ().equals ("endTag")) {
                String name = node.getTokenTypeIdentifier ("html-element_name");
                if (name == null) 
                    name = "";
                else
                    name = name.toLowerCase ();
                int indexS = s.lastIndexOf (name);
                if (indexS >= 0) {
                    int indexL = ((Integer) s.get (indexS + 1)).intValue ();
                    List ll = l.subList (indexL, l.size ());
                    ll.set (0, create ((ASTNode) ll.get (0), "startTag"));
                    List ll1 = new ArrayList (ll);
                    ll1.add (node);
                    ASTNode tag = ASTNode.create (
                        node.getMimeType (),
                        "tag",
                        node.getRule (),
                        node.getParent (),
                        ll1,
                        ((ASTNode) ll1.get (0)).getOffset ()
                    );
                    ll.clear ();
                    s.subList (indexS, s.size ()).clear ();
                    l.add (tag);
                } else
                    l.add (create (node, "unpairedEndTag"));
                continue;
            } else
            if (node.getNT ().equals ("tags")) {
                resolve (node, s, l, findUnpairedTags);
                continue;
            }
            l.add (node);
        }
    }
}

