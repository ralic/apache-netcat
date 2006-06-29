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
package org.netbeans.modules.swingproject;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openide.filesystems.FileUtil;
import org.openide.util.Utilities;

/**
 * Replaces strings in content being unzipped to customize the project name to
 * match what the user entered in the wizard.
 *
 * @author Tim Boudreau
 */
final class Substitutions {
    final Properties props;
    final String basePackage;
    final String userEnteredProjectName;
    final String basePath;

    /** Creates a new instance of Substitutions */
    Substitutions(String templateName, String userEnteredProjectName, String basePackage) throws IOException {
        this (new BufferedInputStream (
                Substitutions.class.getResourceAsStream(
                templateName + ".properties")), userEnteredProjectName, basePackage);
    }

    Substitutions (InputStream stream, String userEnteredProjectName, String basePackage) throws IOException {
        if (stream == null) {
            throw new IOException ("Could not find properties file");
        }
        props = new Properties();
        props.load(stream);
        this.userEnteredProjectName = userEnteredProjectName;
        this.basePackage = basePackage;
        this.basePath = Utilities.replaceString(basePackage, ".", "/");
    }


    String substitutePath (String filepath) {
        String result = props.getProperty(filepath);
        if (result != null) {
            result = result.replaceAll("%basepath%", basePath);
        }
        return result == null ? filepath : result;
    }

    InputStream substituteContent (long originalSize, InputStream input, String filename) throws IOException {
        if (filename.endsWith (".gif") || filename.endsWith (".png") || filename.endsWith(".jar")) {
            return input;
        }
        if (originalSize > Integer.MAX_VALUE || originalSize < 0) {
            throw new IllegalArgumentException ("File too large: " +
                    originalSize);
        }
        ByteArrayOutputStream temp = new ByteArrayOutputStream ((int) originalSize);
        FileUtil.copy (input, temp);
        byte[] b = temp.toByteArray();

        //XXX do we want default charset, or UTF-8 - UTF-8 I think...
        CharBuffer cb = Charset.defaultCharset().decode(ByteBuffer.wrap(b));
        String data = cb.toString();

        String user = System.getProperty ("user.name");
        for (Iterator i = props.keySet().iterator(); i.hasNext();) {
            String key = (String) i.next();
            String val = props.getProperty(key);
            val = val.replaceAll("%basepackage%", basePackage);
            val = val.replaceAll("%basepath%", basePath);
            val = val.replaceAll("%applicationName%", userEnteredProjectName);
            if (user != null) {
                val = val.replaceAll ("%userName%", user);
            } else {
                val = val.replaceAll("%userName%", "Somebody"); //uh, well...
            }
            Matcher m = Pattern.compile(key).matcher(data);
            data = m.replaceAll(val);
        }

        return new ByteArrayInputStream (data.getBytes());
    }
}

