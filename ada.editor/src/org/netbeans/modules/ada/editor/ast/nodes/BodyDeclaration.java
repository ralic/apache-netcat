/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 2008 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 * 
 * Contributor(s):
 * 
 * Portions Copyrighted 2008 Sun Microsystems, Inc.
 */
package org.netbeans.modules.ada.editor.ast.nodes;

/**
 * Based on org.netbeans.modules.php.editor.parser.astnodes.BodyDeclaration
 *
 * Base class for package member declarations
 */
public abstract class BodyDeclaration extends Statement {

    private int modifier;

    public BodyDeclaration(int start, int end, int modifier, boolean shouldComplete) {
        super(start, end);

        this.modifier = (shouldComplete ? completeModifier(modifier) : modifier);
    }

    public BodyDeclaration(int start, int end, int modifier) {
        this(start, end, modifier, false);
    }

    public String getModifierString() {
        return Modifier.toString(modifier);
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public void setPrivate () {
        if ((this.modifier & Modifier.PRIVATE) != Modifier.PRIVATE) {
            this.modifier |= Modifier.PRIVATE;
        }
    }

    public void setTagged () {
        if ((this.modifier & Modifier.TAGGED) != Modifier.TAGGED) {
            this.modifier |= Modifier.TAGGED;
        }
    }

    public void setLimited () {
        if ((this.modifier & Modifier.LIMITED) != Modifier.LIMITED) {
            this.modifier |= Modifier.LIMITED;
        }
    }

    public void setAbstract () {
        if ((this.modifier & Modifier.ABSTRACT) != Modifier.ABSTRACT) {
            this.modifier |= Modifier.ABSTRACT;
        }
    }

    /**
     * Complets the modidifer to public if needed 
     * @param mod
     */
    private static int completeModifier(int mod) {
        if (!BodyDeclaration.Modifier.isPrivate(mod)) {
            mod |= BodyDeclaration.Modifier.PUBLIC;
        }
        return mod;
    }

    /**
     * This is a utility for member modifiers  
     */
    public static class Modifier {

        /**
         * The <code>int</code> value representing the <code>public</code> modifier.
         */
        public static final int PUBLIC = 0x00000001;
        /**
         * The <code>int</code> value representing the <code>private</code> modifier.
         */
        public static final int PRIVATE = 0x00000002;
        /**
         * The <code>int</code> value representing the <code>tagged</code> modifier.
         */
        public static final int TAGGED = 0x00000004;
        /**
         * The <code>int</code> value representing the <code>limited</code> modifier.
         */
        public static final int LIMITED = 0x00000008;
        /**
         * The <code>int</code> value representing the <code>abstract</code> modifier.
         */
        public static final int ABSTRACT = 0x00000010;

        /**
         * Return <tt>true</tt> if the integer argument includes the
         * <tt>public</tt> modifer, <tt>false</tt> otherwise.
         *
         * @param 	mod a set of modifers
         * @return <tt>true</tt> if <code>mod</code> includes the
         * <tt>public</tt> modifier; <tt>false</tt> otherwise.
         */
        public static boolean isPublic(int mod) {
            return (mod & PUBLIC) != 0;
        }

        /**
         * Return <tt>true</tt> if the integer argument includes the
         * <tt>private</tt> modifer, <tt>false</tt> otherwise.
         *
         * @param 	mod a set of modifers
         * @return <tt>true</tt> if <code>mod</code> includes the
         * <tt>private</tt> modifier; <tt>false</tt> otherwise.
         */
        public static boolean isPrivate(int mod) {
            return (mod & PRIVATE) != 0;
        }

        /**
         * Return <tt>true</tt> if the integer argument includes the
         * <tt>tagged</tt> modifer, <tt>false</tt> otherwise.
         *
         * @param 	mod a set of modifers
         * @return <tt>true</tt> if <code>mod</code> includes the
         * <tt>tagged</tt> modifier; <tt>false</tt> otherwise.
         */
        public static boolean isTagged(int mod) {
            return (mod & TAGGED) != 0;
        }

        /**
         * Return <tt>true</tt> if the integer argument includes the
         * <tt>limited</tt> modifer, <tt>false</tt> otherwise.
         *
         * @param 	mod a set of modifers
         * @return <tt>true</tt> if <code>mod</code> includes the
         * <tt>limited</tt> modifier; <tt>false</tt> otherwise.
         */
        public static boolean isLimited(int mod) {
            return (mod & LIMITED) != 0;
        }


        /**
         * Return <tt>true</tt> if the integer argument includes the
         * <tt>abstract</tt> modifer, <tt>false</tt> otherwise.
         *
         * @param 	mod a set of modifers
         * @return <tt>true</tt> if <code>mod</code> includes the
         * <tt>abstract</tt> modifier; <tt>false</tt> otherwise.
         */
        public static boolean isAbstract(int mod) {
            return (mod & ABSTRACT) != 0;
        }

        public static String toString(int mod) {
            StringBuffer sb = new StringBuffer();

            if ((mod & PUBLIC) != 0) {
                sb.append("public "); //$NON-NLS-1$
            }
            if ((mod & TAGGED) != 0) {
                sb.append("tagged "); //$NON-NLS-1$
            }
            if ((mod & PRIVATE) != 0) {
                sb.append("private "); //$NON-NLS-1$
            }
            if ((mod & ABSTRACT) != 0) {
                sb.append("abstract "); //$NON-NLS-1$
            }
            if ((mod & LIMITED) != 0) {
                sb.append("limited "); //$NON-NLS-1$
            }

            int len;
            if ((len = sb.length()) > 0) { /* trim trailing space */
                return sb.toString().substring(0, len - 1);
            }
            return ""; //$NON-NLS-1$
        }
    }
}
