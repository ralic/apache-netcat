/*
 * LexUtil.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.netbeans.modules.erlang.editor.lexer

import javax.swing.text.{BadLocationException,Document}
import org.netbeans.api.lexer.{Token,TokenId,TokenHierarchy,TokenSequence}
import org.netbeans.modules.csl.api.OffsetRange
import org.netbeans.modules.csl.spi.ParserResult
import org.netbeans.modules.parsing.api.Snapshot
import org.netbeans.editor.{BaseDocument,Utilities}
import org.netbeans.modules.erlang.editor.lexer.ErlangTokenId._
import org.openide.filesystems.FileUtil
import org.openide.loaders.DataObject
import org.openide.util.Exceptions
import scala.collection.mutable.Stack

/**
 * Special functions for ErlangTokenId
 */
trait LanguageLexUtil {
    protected val language = ErlangTokenId.language

    protected val LPAREN = ErlangTokenId.LParen
    protected val RPAREN = ErlangTokenId.RParen

    protected val WS = Set(ErlangTokenId.Ws,
                           ErlangTokenId.Nl
    )

    protected val WS_COMMENT = Set(ErlangTokenId.Ws,
                                   ErlangTokenId.Nl,
                                   ErlangTokenId.LineComment
    )

    protected val INDENT_TOKENS = Set(ErlangTokenId.Case,
                                      ErlangTokenId.After,
                                      ErlangTokenId.If,
                                      ErlangTokenId.Receive,
                                      ErlangTokenId.Begin
    )

    protected val END_PAIRS = Set(ErlangTokenId.Try)

    def isWs(id:TokenId) = {id == ErlangTokenId.Ws}
    def isNl(id:TokenId) = {id == ErlangTokenId.Nl}

    def isComment(id:TokenId) :Boolean = id match {
        case ErlangTokenId.LineComment => true
        case _ => false
    }

    def isLineComment(id:TokenId) :Boolean = id match {
        case ErlangTokenId.LineComment => true
        case _ => false
    }

    def isBeginToken(id:TokenId) = id match {
        case ErlangTokenId.Begin => true
        case ErlangTokenId.Case => true
        case ErlangTokenId.If => true
        case ErlangTokenId.Receive => true
        case ErlangTokenId.Try => true
        case _ => false
    }

    def isEndToken(id:TokenId) = id match {
        case ErlangTokenId.End => true
        case _ => false
    }

    def isIndentToken(id:TokenId) = id match {
        case ErlangTokenId.After => true
        case ErlangTokenId.Begin => true
        case ErlangTokenId.Case => true
        case ErlangTokenId.Catch => true
        case ErlangTokenId.If => true
        case ErlangTokenId.Receive => true
        case ErlangTokenId.Try => true
        case ErlangTokenId.RArrow => true
        case _ => false
    }
}

object LexUtil extends LanguageLexUtil {

    def document(pResult:ParserResult, forceOpen:Boolean) :Option[BaseDocument] = pResult match {
        case null => None
        case _ => document(pResult.getSnapshot, forceOpen)
    }

    def document(snapshot:Snapshot, forceOpen:Boolean) :Option[BaseDocument] = snapshot match {
        case null => None
        case _ => snapshot.getSource.getDocument(forceOpen) match {
                case doc:BaseDocument => Some(doc)
                case _ => None
            }
    }

    def tokenHierarchy(snapshot:Snapshot) :Option[TokenHierarchy[_]] = document(snapshot, false) match {
        // * try get th from BaseDocument first, if it has been opened, th should has been there
        case doc:BaseDocument => TokenHierarchy.get(doc) match {
                case null => None
                case th => Some(th)
            }
        case _ => TokenHierarchy.create(snapshot.getText, language) match {
                case null => None
                case th => Some(th)
            }
    }

    def tokenHierarchy(pResult:ParserResult) :Option[TokenHierarchy[_]] = pResult match {
        case null => null
        case _ => tokenHierarchy(pResult.getSnapshot)
    }

    def tokenSequence(doc:BaseDocument, offset:Int) :Option[TokenSequence[TokenId]] = {
        val th = TokenHierarchy.get(doc)
        tokenSequence(th, offset)
    }

    def tokenSequence(th:TokenHierarchy[_], offset:Int) :Option[TokenSequence[TokenId]] = th.tokenSequence(language) match {
        case null =>
            // * Possibly an embedding scenario such as an RHTML file
            def find(itr:_root_.java.util.Iterator[TokenSequence[TokenId]]) :Option[TokenSequence[TokenId]] = itr.hasNext match {
                case true => itr.next match {
                        case ts if ts.language == language => Some(ts)
                        case _ => find(itr)
                    }
                case false => None
            }
         
            // * First try with backward bias true
            val itr1 = th.embeddedTokenSequences(offset, true).iterator.asInstanceOf[_root_.java.util.Iterator[TokenSequence[TokenId]]]
            find(itr1) match {
                case None =>
                    val itr2 = th.embeddedTokenSequences(offset, false).iterator.asInstanceOf[_root_.java.util.Iterator[TokenSequence[TokenId]]]
                    find(itr2)
                case x => x
            }
        case ts => Some(ts)
    }

    def rangeOfToken(th:TokenHierarchy[_], token:Token[TokenId]) :OffsetRange = {
        val offset = token.offset(th)
        new OffsetRange(offset, offset + token.length)
    }

    /** For a possibly generated offset in an AST, return the corresponding lexing/true document offset */
    def lexerOffset(pResult:ParserResult, astOffset:Int) :Int = {
        pResult.getSnapshot.getOriginalOffset(astOffset)
    }

    def lexerOffsets(pResult:ParserResult, astRange:OffsetRange) :OffsetRange = {
        // * there has been  astRange, we can assume pResult not null
        val rangeStart = astRange.getStart
        pResult.getSnapshot.getOriginalOffset(rangeStart) match {
            case -1 => OffsetRange.NONE
            case `rangeStart` => astRange
            case start =>
                // Assumes the translated range maintains size
                new OffsetRange(start, start + astRange.getLength)
        }
    }

    def astOffset(pResult:ParserResult, lexOffset:Int) :Int = pResult match {
        case null => lexOffset
        case _ => pResult.getSnapshot.getEmbeddedOffset(lexOffset)
    }

    def astOffsets(pResult:ParserResult, lexRange:OffsetRange) :OffsetRange = pResult match {
        case null => lexRange
        case _ =>
            val rangeStart = lexRange.getStart
            pResult.getSnapshot.getEmbeddedOffset(rangeStart) match {
                case -1 => OffsetRange.NONE
                case `rangeStart` => lexRange
                case start =>
                    // Assumes the translated range maintains size
                    new OffsetRange(start, start + lexRange.getLength())
            }
    }

    def positionedSequence(doc:BaseDocument, offset:Int) :Option[TokenSequence[TokenId]] = {
        positionedSequence(doc, offset, true)
    }

    def positionedSequence(doc:BaseDocument, offset:Int, lookBack:Boolean) :Option[TokenSequence[TokenId]] = {
        for (ts <- tokenSequence(doc, offset)) {
            try {
                ts.move(offset)
            } catch {
                case e:AssertionError =>
                    doc.getProperty(Document.StreamDescriptionProperty) match {
                        case null =>
                        case dobj:DataObject => Exceptions.attachMessage(e, FileUtil.getFileDisplayName(dobj.getPrimaryFile))
                    }
                    throw e
            }

            if (!lookBack && !ts.moveNext) {
                return None
            } else if (lookBack && !ts.moveNext && !ts.movePrevious) {
                return None
            }

            return Some(ts)
        }

        return None
    }

    def token(doc:BaseDocument, offset:Int) :Option[Token[TokenId]] = {
        for (ts <- positionedSequence(doc, offset)) {
            return Some(ts.token)
        }

        return None
    }

    def tokenChar(doc:BaseDocument, offset:Int) :Char = token(doc, offset) match {
        case None => 0
        case Some(x) =>
            val text = x.text.toString
            
            if (text.length > 0) { // Usually true, but I could have gotten EOF right?
                text.charAt(0)
            } else 0
    }
    

    def findNextNonWsNonComment(ts:TokenSequence[TokenId]) :Token[TokenId] = {
        findNext(ts, WS_COMMENT.asInstanceOf[Set[TokenId]])
    }

    def findPreviousNonWsNonComment(ts:TokenSequence[TokenId]) :Token[TokenId] = {
        findPrevious(ts, WS_COMMENT.asInstanceOf[Set[TokenId]])
    }

    def findNextNonWs(ts:TokenSequence[TokenId]) :Token[TokenId] = {
        findNext(ts, WS.asInstanceOf[Set[TokenId]])
    }

    def findPreviousNonWs(ts:TokenSequence[TokenId]) :Token[TokenId] = {
        findPrevious(ts, WS.asInstanceOf[Set[TokenId]])
    }

    def findNext(ts:TokenSequence[TokenId], ignores:Set[TokenId]) :Token[TokenId] = {
        if (ignores.contains(ts.token.id)) {
            while (ts.moveNext && ignores.contains(ts.token.id)) {}
        }
        ts.token
    }

    def findPrevious(ts:TokenSequence[TokenId], ignores:Set[TokenId]) :Token[TokenId] = {
        if (ignores.contains(ts.token.id)) {
            while (ts.movePrevious && ignores.contains(ts.token.id)) {}
        }
        ts.token
    }

    def findNext(ts:TokenSequence[TokenId], id:TokenId) :Token[TokenId] = {
        if (ts.token.id != id) {
            while (ts.moveNext && ts.token.id != id) {}
        }
        ts.token
    }

    def findNextIn(ts:TokenSequence[TokenId], includes:Set[TokenId] ) :Token[TokenId] = {
        if (!includes.contains(ts.token.id)) {
            while (ts.moveNext && !includes.contains(ts.token.id)) {}
        }
        ts.token
    }

    def findPrev(ts:TokenSequence[TokenId], id:TokenId) :Token[TokenId] = {
        if (ts.token.id != id) {
            while (ts.movePrevious && ts.token.id != id) {}
        }
        ts.token
    }

    def findNextIncluding(ts:TokenSequence[TokenId], includes:Set[TokenId] ) :Token[TokenId] = {
        while (ts.moveNext && !includes.contains(ts.token.id)) {}
        ts.token
    }

    def findPrevIncluding(ts:TokenSequence[TokenId], includes:Set[TokenId]) :Token[TokenId] = {
        if (!includes.contains(ts.token.id)) {
            while (ts.movePrevious && !includes.contains(ts.token.id)) {}
        }
        ts.token
    }

    def skipParenthesis(ts:TokenSequence[TokenId]) :Boolean = {
        skipParenthesis(ts, false)
    }

    /**
     * Tries to skip parenthesis
     */
    def skipParenthesis(ts:TokenSequence[TokenId], back:Boolean) :Boolean = {
        var balance = 0

        var token = ts.token
        if (token == null) {
            return false
        }

        var id = token.id

        // skip whitespace and comment
        if (isWsComment(id)) {
            while ((if (back) ts.movePrevious else ts.moveNext) && isWsComment(id)) {}
        }

        // if current token is not parenthesis
        if (ts.token.id != (if (back) RPAREN else LPAREN)) {
            return false
        }

        do {
            token = ts.token
            id = token.id

            if (id == (if (back) RPAREN else LPAREN)) {
                balance += 1
            } else if (id == (if (back) LPAREN else RPAREN)) {
                balance match {
                    case 0 =>
                        return false
                    case 1 =>
                        if (back) ts.movePrevious else ts.moveNext
                        return true
                    case _ => balance -= 1
                }
            }
        } while (if (back) ts.movePrevious else ts.moveNext)

        false
    }

    /**
     * Tries to skip parenthesis
     */
    def skipPair(ts:TokenSequence[TokenId], left:TokenId, right:TokenId, back:Boolean) :Boolean = {
        var balance = 0

        var token = ts.token
        if (token == null) {
            return false
        }

        var id = token.id

        // * skip whitespace and comment
        if (isWsComment(id)) {
            while ((if (back) ts.movePrevious else ts.moveNext) && isWsComment(id)) {}
        }

        // * if current token is not parenthesis
        if (ts.token.id != (if (back) right else left)) {
            return false
        }

        do {
            token = ts.token
            id = token.id

            if (id == (if (back) right else left)) {
                balance += 1
            } else if (id == (if (back) left else right)) {
                balance match {
                    case 0 =>
                        return false
                    case 1 =>
                        if (back) ts.movePrevious else ts.moveNext
                        return true
                    case _ => balance -= 1
                }
            }
        } while (if (back) ts.movePrevious else ts.moveNext)

        false
    }

    /** Search forwards in the token sequence until a token of type <code>down</code> is found */
    def findFwd(ts:TokenSequence[TokenId], ups:Set[TokenId], down:TokenId) :OffsetRange = {
        var balance = 0
        while (ts.moveNext) {
            val token = ts.token
            val id = token.id
            (id, ups.contains(id), balance) match {
                case (`down`, _, 0) => return new OffsetRange(ts.offset, ts.offset + token.length)
                case (`down`, _, _) => balance -= 1
                case (_, true,   _) => balance += 1
                case _ =>
            }
        }

        OffsetRange.NONE
    }

    /** Search backwards in the token sequence until a token of type <code>up</code> is found */
    def  findBwd(ts:TokenSequence[TokenId], ups:Set[TokenId], down:TokenId) :OffsetRange = {
        var balance = 0
        while (ts.movePrevious) {
            val token = ts.token
            val id = token.id
            (id, ups.contains(id), balance) match {
                case (_, true,   0) => return new OffsetRange(ts.offset, ts.offset + token.length)
                case (_, true,   _) => balance -= 1
                case (`down`, _, _) => balance += 1
                case _ =>
            }
        }

        OffsetRange.NONE
    }

    /** Search forwards in the token sequence until a token of type <code>down</code> is found */
    def findFwd(ts:TokenSequence[TokenId], up:TokenId, down:TokenId) :OffsetRange = {
        var balance = 0
        while (ts.moveNext) {
            val token = ts.token
            (token.id, balance) match {
                case (`up`,   _) => balance += 1
                case (`down`, 0) => return new OffsetRange(ts.offset, ts.offset + token.length)
                case (`down`, _) => balance -= 1
                case _ =>
            }
        }

        OffsetRange.NONE
    }

    /** Search backwards in the token sequence until a token of type <code>up</code> is found */
    def  findBwd(ts:TokenSequence[TokenId], up:TokenId, down:TokenId) :OffsetRange = {
        var balance = 0
        while (ts.movePrevious) {
            val token = ts.token
            (token.id, balance) match {
                case (`up`,   0) => return new OffsetRange(ts.offset, ts.offset + token.length)
                case (`up`,   _) => balance += 1
                case (`down`, _) => balance -= 1
                case _ =>
            }
        }

        OffsetRange.NONE
    }

    /** Search forwards in the token sequence until a token of type <code>down</code> is found */
    def  findFwd(ts:TokenSequence[TokenId], up:String, down:String) :OffsetRange = {
        var balance = 0
        while (ts.moveNext) {
            val token = ts.token
            (token.text.toString, balance) match {
                case (`up`,   _) => balance += 1
                case (`down`, 0) => return new OffsetRange(ts.offset, ts.offset + token.length)
                case (`down`, _) => balance -= 1
                case _ =>
            }
        }

        OffsetRange.NONE
    }

    /** Search backwards in the token sequence until a token of type <code>up</code> is found */
    def findBwd(ts:TokenSequence[TokenId], up:String, down:String) :OffsetRange = {
        var balance = 0
        while (ts.movePrevious) {
            val token = ts.token
            (token.text.toString, balance) match {
                case (`up`,   0) => return new OffsetRange(ts.offset, ts.offset + token.length)
                case (`up`,   _) => balance += 1
                case (`down`, _) => balance -= 1
                case _ =>
            }
        }

        OffsetRange.NONE
    }

    def lineIndent(doc:BaseDocument, offset:Int) :Int = {
        try {
            val start = Utilities.getRowStart(doc, offset)
            var end = if (Utilities.isRowWhite(doc, start)) {
                Utilities.getRowEnd(doc, offset)
            } else {
                Utilities.getRowFirstNonWhite(doc, start)
            }

            val indent = Utilities.getVisualColumn(doc, end);

            indent
        } catch {
            case ex:BadLocationException =>
                Exceptions.printStackTrace(ex)
                0
        }
    }

    def isWsComment(id:TokenId) :Boolean = isWs(id) || isNl(id) || isComment(id)

    /**
     * The same as braceBalance but generalized to any pair of matching
     * tokens.
     * @param open the token that increses the count
     * @param close the token that decreses the count
     */
    @throws(classOf[BadLocationException])
    def tokenBalance(doc:BaseDocument, open:TokenId, close:TokenId, offset:Int) :Int = {
        val ts = tokenSequence(doc, 0) match {
            case None => return 0
            case Some(x) => x
        }

        // XXX Why 0? Why not offset?
        ts.moveIndex(0)
        if (!ts.moveNext) {
            return 0
        }

        var balance = 0
        do {
            ts.token.id match {
                case `open` => balance += 1
                case `close` => balance -= 1
                case _ =>
            }
        } while (ts.moveNext)

        balance
    }

    /**
     * The same as braceBalance but generalized to any pair of matching
     * tokens.
     * @param open the token that increses the count
     * @param close the token that decreses the count
     */
    @throws(classOf[BadLocationException])
    def tokenBalance(doc:BaseDocument, open:String, close:String, offset:int) :Int = {
        val ts = tokenSequence(doc, 0) match {
            case None => return 0
            case Some(x) => x
        }

        // XXX Why 0? Why not offset?
        ts.moveIndex(0)
        if (!ts.moveNext) {
            return 0
        }

        var balance = 0
        do {
            val token = ts.token
            token.text.toString match {
                case `open` => balance += 1
                case `close` => balance -= 1
                case _ =>
            }
        } while (ts.moveNext)

        balance
    }


    /** Compute the balance of begin/end tokens on the line.
     * @param doc the document
     * @param offset The offset somewhere on the line
     * @param upToOffset If true, only compute the line balance up to the given offset (inclusive),
     *   and if false compute the balance for the whole line
     */
    def beginEndLineBalance(doc:BaseDocument, offset:Int, upToOffset:Boolean) :Int = {
        try {
            val begin = Utilities.getRowStart(doc, offset)
            val end = if (upToOffset) offset else Utilities.getRowEnd(doc, offset)

            val ts = tokenSequence(doc, begin) match {
                case None => return 0
                case Some(x) => x
            }

            ts.move(begin)
            if (!ts.moveNext) {
                return 0
            }

            var balance = 0
            do {
                val token = ts.token
                val id = token.id
                if (isBeginToken(id)) {
                    balance += 1
                } else if (isEndToken(id)) {
                    balance -= 1
                }
            } while (ts.moveNext && (ts.offset <= end))

            balance
        } catch {
            case ex:BadLocationException =>
                Exceptions.printStackTrace(ex)
                0
        }
    }

    /** Compute the balance of pair tokens on the line */
    def lineBalance(doc:BaseDocument, offset:Int, up:TokenId, down:TokenId) :Stack[Token[TokenId]] = {
        val balanceStack = new Stack[Token[TokenId]]
        try {
            val begin = Utilities.getRowStart(doc, offset)
            val end = Utilities.getRowEnd(doc, offset)

            val ts = tokenSequence(doc, begin) match {
                case None => return balanceStack
                case Some(x) => x
            }

            ts.move(begin)
            if (!ts.moveNext) {
                return balanceStack
            }

            var balance = 0
            do {
                val token = ts.offsetToken
                token.id match {
                    case `up` =>
                        balanceStack.push(token)
                        balance += 1
                    case `down` =>
                        if (!balanceStack.isEmpty) {
                            balanceStack.pop
                        }
                        balance -= 1
                    case _ =>
                }
            } while (ts.moveNext && (ts.offset <= end))

            balanceStack
        } catch {
            case ex:BadLocationException =>
                Exceptions.printStackTrace(ex)
                balanceStack
        }
    }

    /**
     * Return true iff the line for the given offset is a JavaScript comment line.
     * This will return false for lines that contain comments (even when the
     * offset is within the comment portion) but also contain code.
     */
    @throws(classOf[BadLocationException])
    def isCommentOnlyLine(doc:BaseDocument, offset:Int) :Boolean = {
        val begin = Utilities.getRowFirstNonWhite(doc, offset)
        if (begin == -1) {
            return false // whitespace only
        }

        for (token <- token(doc, begin)) {
            return isLineComment(token.id)
        }

        return false
    }


}

