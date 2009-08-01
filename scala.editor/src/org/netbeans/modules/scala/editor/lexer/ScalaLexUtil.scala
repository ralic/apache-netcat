/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
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
package org.netbeans.modules.scala.editor.lexer

import _root_.java.io.IOException
import _root_.java.util.{ArrayList, Arrays, Collections, HashSet, LinkedList, Stack}
import javax.swing.text.{BadLocationException, Document}

import org.netbeans.modules.csl.api.OffsetRange
import org.netbeans.api.lexer.{Language, Token, TokenHierarchy, TokenId, TokenSequence}
import org.netbeans.editor.{BaseDocument, Utilities}
import org.netbeans.modules.parsing.spi.Parser
import org.openide.cookies.EditorCookie
import org.openide.filesystems.{FileObject, FileUtil}
import org.openide.loaders.{DataObject, DataObjectNotFoundException}
import org.openide.util.Exceptions

//import org.netbeans.modules.scala.editor.nodes.AstNode;
import org.netbeans.modules.scala.editor.lexer.ScalaTokenId._

import _root_.scala.collection.mutable.ArrayBuffer

import org.netbeans.api.language.util.lex.LexUtil

/**
 * Utilities associated with lexing or analyzing the document at the
 * lexical level, unlike AstUtilities which is contains utilities
 * to analyze parsed information about a document.
 *
 * @author Caoyuan Deng
 * @author Tor Norbye
 */

object ScalaLexUtil extends LexUtil {

  override val LANGUAGE = ScalaTokenId.language

  override val WS_COMMENTS: Set[TokenId] = Set(ScalaTokenId.Ws,
                                               ScalaTokenId.Nl,
                                               ScalaTokenId.LineComment,
                                               ScalaTokenId.DocCommentStart,
                                               ScalaTokenId.DocCommentEnd,
                                               ScalaTokenId.BlockCommentStart,
                                               ScalaTokenId.BlockCommentEnd,
                                               ScalaTokenId.BlockCommentData
  )

  override val WS: Set[TokenId] = Set(ScalaTokenId.Ws,
                                      ScalaTokenId.Nl
  )

  /**
   * Tokens that should cause indentation of the next line. This is true for all {@link #END_PAIRS},
   * but also includes tokens like "else" that are not themselves matched with end but also contribute
   * structure for indentation.
   *
   */
  override val INDENT_WORDS: Set[TokenId] = Set(ScalaTokenId.Class,
                                                ScalaTokenId.Object,
                                                ScalaTokenId.Trait,
                                                ScalaTokenId.Do,
                                                ScalaTokenId.For,
                                                ScalaTokenId.While,
                                                ScalaTokenId.Case,
                                                ScalaTokenId.If,
                                                ScalaTokenId.Else
  )

  override val BLOCK_COMMENTS: Set[TokenId] = Set(ScalaTokenId.BlockCommentStart,
                                                  ScalaTokenId.BlockCommentEnd,
                                                  ScalaTokenId.BlockCommentData,
                                                  ScalaTokenId.CommentTag
  )

  override val DOC_COMMENTS: Set[TokenId] = Set(ScalaTokenId.DocCommentStart,
                                                ScalaTokenId.DocCommentEnd,
                                                ScalaTokenId.DocCommentData,
                                                ScalaTokenId.CommentTag
  )

  override val LINE_COMMENTS: Set[TokenId] = Set(
    ScalaTokenId.LineComment
  )

  override val WHITE_SPACE: TokenId = ScalaTokenId.Ws
  override val NEW_LINE: TokenId = ScalaTokenId.Nl
  override val LPAREN: TokenId = ScalaTokenId.LParen
  override val RPAREN: TokenId = ScalaTokenId.RParen

  override def getDocCommentRangeBefore(th: TokenHierarchy[_], lexOffset: Int): OffsetRange = {
    val ts = getTokenSequence(th, lexOffset)
    if (ts == null) {
      return OffsetRange.NONE
    }

    ts.move(lexOffset)
    var offset = -1
    var endOffset = -1
    var done = false
    while (ts.movePrevious && !done) {
      val id = ts.token.id

      if (id == ScalaTokenId.DocCommentEnd) {
        val token = ts.offsetToken
        endOffset = token.offset(th) + token.length
      } else if (id == ScalaTokenId.DocCommentStart) {
        val token = ts.offsetToken
        offset = token.offset(th)
        done = true
      } else if (!isWsComment(id) && !isKeyword(id)) {
        done = true
      }
    }

    if (offset != -1 && endOffset != -1) {
      new OffsetRange(offset, endOffset)
    } else OffsetRange.NONE
  }


  private def findMultilineRange(ts: TokenSequence[TokenId]): OffsetRange = {
    val startOffset = ts.offset
    val token = ts.token
    var id = token.id
    id match {
      case Else =>
        ts.moveNext
        id = ts.token.id
      case If | For | While =>
        ts.moveNext
        if (!skipParenthesis(ts, false)) {
          return OffsetRange.NONE
        }
        id = ts.token.id
      case _ =>
        return OffsetRange.NONE
    }

    var eolFound = false
    var lastEolOffset = ts.offset
    // skip whitespaces and comments
    if (isWsComment(id)) {
      if (ts.token.id == NEW_LINE) {
        lastEolOffset = ts.offset
        eolFound = true
      }
      while (ts.moveNext && isWsComment(ts.token.id)) {
        if (ts.token.id == NEW_LINE) {
          lastEolOffset = ts.offset
          eolFound = true
        }
      }
    }
    // if we found end of sequence or end of line
    if (ts.token == null || (ts.token.id != ScalaTokenId.LBrace && eolFound)) {
      new OffsetRange(startOffset, lastEolOffset);
    } else OffsetRange.NONE
  }

  def getMultilineRange(doc :BaseDocument, ts :TokenSequence[TokenId]): OffsetRange = {
    val index = ts.index
    val offsetRange = findMultilineRange(ts)
    ts.moveIndex(index)
    ts.moveNext
    offsetRange
  }

  val PotentialIdTokens: Set[TokenId] = Set(ScalaTokenId.Identifier,
                                            ScalaTokenId.True,
                                            ScalaTokenId.False,
                                            ScalaTokenId.IntegerLiteral,
                                            ScalaTokenId.FloatingPointLiteral,
                                            ScalaTokenId.StringLiteral,
                                            ScalaTokenId.CharacterLiteral,
                                            ScalaTokenId.Null,
                                            ScalaTokenId.XmlAttName,
                                            ScalaTokenId.XmlAttValue,
                                            ScalaTokenId.XmlCDData,
                                            ScalaTokenId.XmlCDEnd,
                                            ScalaTokenId.XmlComment,
                                            ScalaTokenId.XmlSTagName,
                                            ScalaTokenId.XmlSTagName,
                                            ScalaTokenId.XmlCharData
  )

  /** Some AstItems have Xml Nl etc type of idToken, here we just pick following as proper one */
  def isProperIdToken(id: TokenId): Boolean = {
    id match {
      case ScalaTokenId.Identifier | ScalaTokenId.This | ScalaTokenId.Super | ScalaTokenId.Wild => true
      case _ => false
    }
  }

  def findImportPrefix(th: TokenHierarchy[_], lexOffset: Int): List[Token[_ <: TokenId]] = {
    val ts = getTokenSequence(th, lexOffset)
    ts.move(lexOffset)

    var lbraceMet = false
    var lbraceExpected = false
    var extractBehindComma = false
    val paths = new ArrayBuffer[Token[_ <: TokenId]]
    while (ts.isValid && ts.movePrevious) {
      val token = ts.token
      token.id match {
        case ScalaTokenId.Import =>
          if (!lbraceExpected || lbraceExpected && lbraceMet) {
            paths.reverse
            return paths.toList
          }
        case ScalaTokenId.Dot =>
          paths += token
        case ScalaTokenId.Identifier =>
          paths += token
        case ScalaTokenId.LBrace =>
          if (lbraceMet) {
            // we can only meet LBrace once
            return Nil
          }
          lbraceMet = true
          if (paths.size > 0) {
            // keep first met id token only
            val idToken = paths(0)
            paths.clear
            if (!extractBehindComma) {
              paths += idToken
            }
          }
        case ScalaTokenId.Comma =>
          lbraceExpected = true
          if (paths.isEmpty) {
            extractBehindComma = true
          }
        case id if isWsComment(id) =>
        case _ => return Nil
      }
    }

    Nil
  }
}
