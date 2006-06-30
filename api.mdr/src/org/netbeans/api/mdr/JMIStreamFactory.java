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

package org.netbeans.api.mdr;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/** Class used by JMI mapper to obtain output streams for generating JMI interfaces.
 * Subclasses should implement the <code>{@link #createStream(List, String, String)}
 * method to create/open an output stream based on a provided package name, class
 * name and file extension.
 *
 */
public abstract class JMIStreamFactory {

    /** Extension for Java source files  */
    public static final String EXT_JAVA = "java"; //NOI18N

    /** Extension for Java bytecode files  */
    public static final String EXT_CLASS = "class"; //NOI18N

    /** Creates a new output stream based on a provided package name and class name.
     * Assumes that the data generated will be Java source code (e.g. creates FileOutputStream
     * for a file with "java" extension). Calling this method has the same effect
     * as calling {@link #createStream(List, String, String)} with the last parameter
     * set to <code>EXT_JAVA</code>.
     * @param pkg Parsed package name.
     * @param className Class name.
     * @throws IOException I/O error during stream creation.
     * @return Created stream, or <code>null</code> if nothing needs to be written
     *         for the class or interface.
     *
     */
    public OutputStream createStream(List pkg, String className) throws IOException {
        return createStream(pkg, className, EXT_JAVA);
    }

    /** Creates a new output stream based on a provided package name, class name and
     * extension for the returned stream should correspond to  (e.g.
     * {@link #EXT_CLASS} to generate byte code or {@link #EXT_JAVA} to generate
     * source code). The stream factory can return <code>null</code> to indicate
     * that nothing needs to be written for the given class or interface. For
     * example, if the stream factory is able to determine that the destination
     * file already exists and is up to date, then <code>null</code> could be
     * returned so that the file is not needlessly rewritten.
     * @param pkg Parsed package name.
     * @param className Class name.
     * @param extension The type of file that should be generated.
     * @throws IOException I/O error during stream creation.
     * @return Created stream, or <code>null</code> if nothing needs to be written
     *         for the class or interface.
     *
     */
    public abstract OutputStream createStream(List pkg, String className, String extension) throws IOException;
}

