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
package org.netbeans.modules.erlang.editor.ast

import _root_.java.util.{Collections, Set}
import com.ericsson.otp.erlang.OtpErlangObject
import org.netbeans.api.lexer.Token
import org.netbeans.api.lexer.TokenId
import org.netbeans.api.lexer.TokenHierarchy
import org.netbeans.modules.csl.api.{ElementKind, ElementHandle, Modifier, OffsetRange}
import org.netbeans.modules.csl.spi.{ParserResult}
import org.netbeans.modules.erlang.editor.ErlangMimeResolver
import org.openide.filesystems.{FileObject}

/**
 *
 * @author Caoyuan Deng
 */
abstract class AstItem(var symbol:OtpErlangObject, var _idToken:Token[TokenId]) extends ForElementHandle {

    protected def this(symbol:OtpErlangObject) = this(symbol, null)
    protected def this(idToken:Token[TokenId]) = this(null, idToken)
    protected def this() = this(null, null)

    protected def NO_MEANING_NAME = "-1"

    /**
     * @Note:
     * 1. Not all AstItem has pickToken, such as Expr etc.
     * 2. Due to strange behavior of StructureAnalyzer, we can not rely on
     *    pickToken's text as name, pickToken may be <null> and pickToken.text()
     *    will return null when an Identifier token modified, seems sync issue
     */
    private var _enclosingScope :AstScope = _
    var resultType :String = _
    protected var name :String = _

    private def name_=(idToken:Token[TokenId]) {
        if (idToken == null) {
            name = "" // should not happen?
        }
        
        /**
         * symbol.nameString() is same as idToken's text, for editor, it's always
         * better to use idToken's text, for example, we'll use this name to
         * decide occurrences etc.
         */
        /** @todo why will throws NPE here? */
        try {
            this.name = idToken.text.toString
        } catch {
            case ex:Exception =>
                val l = idToken.length()
                val sb = new StringBuilder(l)
                var i = 0
                while (i < l) {
                    sb.append(" ")
                    i += 1
                }
                this.name = sb.toString
                System.out.println("NPE in AstItem#getName:" + idToken.id)
        }
    }

    def idToken = _idToken
    
    def idToken_=(idToken:Token[TokenId]) {
        this._idToken = idToken
        name = idToken
    }

    def idOffset(th:TokenHierarchy[TokenId]) = {
        if (idToken != null) {
            idToken.offset(th)
        } else {
            assert(false, getName + ": Should implement getIdOffset(th)")
            -1
        }
    }

    def idEndOffset(th:TokenHierarchy[TokenId]) :Int = {
        if (idToken != null) {
            idToken.offset(th) + idToken.length()
        } else {
            assert(false, name + ": Should implement getIdEndOffset(th)")
            -1
        }
    }

    def binaryName = name

    def enclosingDef[T <: AstDef](clazz:Class[T]) :Option[T] = {
        enclosingScope.enclosingDef(clazz)
    }

    /**
     * @Note: enclosingScope will be set when call
     *   {@link AstScope#addElement(Element)} or {@link AstScope#addMirror(Mirror)}
     */
    def enclosingScope_=(enclosingScope:AstScope) :AstItem = {
        this._enclosingScope = enclosingScope
        this
    }

    /**
     * @return the scope that encloses this item
     */
    def enclosingScope :AstScope = {
        assert(_enclosingScope != null, name + ": Each item should set enclosing scope!, except native TypeRef")
        _enclosingScope
    }
}

/**
 * Wrap functions that implemented some ElementHandle's methods
 */
trait ForElementHandle {self:AstItem =>
    
    def getMimeType :String = ErlangMimeResolver.MIME_TYPE

    def getName = self.name

    def getIn :String = {
        ""
        //return symbol.enclClass().nameString()
    }

    def getKind :ElementKind = ElementKind.OTHER

    def signatureEquals(handle:ElementHandle) = false

    def getModifiers :Set[Modifier] = Collections.emptySet[Modifier]

    def getOffsetRange(result:ParserResult) :OffsetRange = OffsetRange.NONE
}
