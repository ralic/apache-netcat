/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2009 Sun Microsystems, Inc. All rights reserved.
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
 * Portions Copyrighted 2009 Sun Microsystems, Inc.
 */

package org.netbeans.modules.scala.hints

import org.netbeans.modules.csl.api.RuleContext
import org.openide.filesystems.FileObject
import org.netbeans.api.java.classpath.ClassPath
import org.netbeans.api.java.source.ClasspathInfo
import org.netbeans.modules.csl.api.OffsetRange
import org.netbeans.editor.Utilities



class ScalaRuleContext() extends RuleContext {

    def getFileObject = parserResult.getSnapshot().getSource().getFileObject()

    /**
    * cache somehow?
    */
    def getClasspathInfo  : ClasspathInfo  = {
        val fo = getFileObject
        val bootPath = ClassPath.getClassPath(fo, ClassPath.BOOT);
        val compilePath = ClassPath.getClassPath(fo, ClassPath.COMPILE);
        val srcPath = ClassPath.getClassPath(fo, ClassPath.SOURCE);

        if (bootPath == null || compilePath == null || srcPath == null) {
            throw new IllegalStateException("NO classpath, is that illegal?")
        }
        ClasspathInfo.create(bootPath, compilePath, srcPath)
    }

    def calcOffsetRange(start : Int, end : Int) : Option[OffsetRange] = {
        try {
            Some(new OffsetRange(Utilities.getRowStart(doc, start), Utilities.getRowEnd(doc, end)))
        } catch {
            case x : Exception => None
        }
    }


}
