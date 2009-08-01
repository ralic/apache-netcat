/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2009 Sun Microsystems, Inc. All rights reserved.
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
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
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
 */
package org.netbeans.modules.scala.editor

import org.netbeans.api.lexer.Language
import org.netbeans.modules.csl.api.{CodeCompletionHandler,
                                     DeclarationFinder,
                                     Formatter,
                                     IndexSearcher,
                                     InstantRenamer,
                                     KeystrokeHandler,
                                     OccurrencesFinder,
                                     SemanticAnalyzer,
                                     StructureScanner}
import org.netbeans.modules.csl.spi.DefaultLanguageConfig
import org.netbeans.modules.parsing.spi.Parser
import org.netbeans.modules.parsing.spi.indexing.EmbeddingIndexerFactory
import org.openide.filesystems.{FileObject, FileUtil}
import org.netbeans.modules.scala.editor.lexer.ScalaTokenId

/**
 * Language/lexing configuration for Scala
 *
 * @author Caoyuan Deng
 */
class ScalaLanguage extends DefaultLanguageConfig {
  import ScalaLanguage._

  override def getLexerLanguage = ScalaTokenId.language

  override def getLineCommentPrefix = "//" // NOI18N
 
  override def getDisplayName: String =  "Scala" // NOI18N
    
  override def getPreferredExtension: String = "Scala" // NOI18N

  /**
   * @see org.netbeans.modules.scala.platform.ScalaPlatformClassPathProvider and ModuleInstall
   */
  override def getLibraryPathIds = _root_.java.util.Collections.singleton(BOOT)

  override def getSourcePathIds = _root_.java.util.Collections.singleton(SOURCE)
    
  override def getParser = new ScalaParser
  
  override def getSemanticAnalyzer = new ScalaSemanticAnalyzer

  override def hasStructureScanner = true
  override def getStructureScanner = new ScalaStructureAnalyzer
  
  override def hasOccurrencesFinder = true
  override def getOccurrencesFinder = new ScalaOccurrencesFinder
  
  //   override def getKeystrokeHandler = new ScalaKeystrokeHandler
  //
  //   override def hasFormatter =  true
  //   override def getFormatter = new ScalaFormatter
  //
  //   override def getInstantRenamer = new ScalaInstantRenamer
  //
  //   override def getDeclarationFinder = new ScalaDeclarationFinder
  //
  //   override def getIndexerFactory = new ScalaIndexer.Factory
  //
  //   override def getCompletionHandler = new ScalaCodeCompletion
}

object ScalaLanguage {
  val BOOT    = "scala/classpath/boot"
  val COMPILE = "scala/classpath/compile"
  val EXECUTE = "scala/classpath/execute"
  val SOURCE  = "scala/classpath/source"
}