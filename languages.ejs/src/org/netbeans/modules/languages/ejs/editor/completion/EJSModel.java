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

package org.netbeans.modules.languages.ejs.editor.completion;
import java.util.ArrayList;
import javax.swing.text.Document;
import org.netbeans.api.lexer.Token;
import org.netbeans.api.lexer.TokenHierarchy;
import org.netbeans.api.lexer.TokenHierarchyEvent;
import org.netbeans.api.lexer.TokenHierarchyListener;
import org.netbeans.api.lexer.TokenSequence;
import org.netbeans.editor.BaseDocument;
import org.netbeans.modules.languages.ejs.lexer.api.EJSTokenId;

/**
 * Creates a javascript (e4x) model for an EJS file. Simulates Phobos to generate js from
 * the EJS.
 *
 * This class attaches itself to a document, and listens on changes. When
 * a client asks for the js source of the EJS file, it lazily generates it
 * if and only if the document has been modified.
 *
 * @author Marek Fukala
 * @author Tor Norbye
 */
public class EJSModel {
    private final Document doc;
    private final ArrayList<CodeBlockData> codeBlocks = new ArrayList<CodeBlockData>();
    private String jsCode;
    private boolean documentDirty = true;
    
    /** Caching */
    private int prevAstOffset; // Don't need to initialize: the map 0 => 0 is correct
    /** Caching */
    private int prevLexOffset;
 
    public static EJSModel get(Document doc) {
        EJSModel model = (EJSModel)doc.getProperty(EJSModel.class);
        if(model == null) {
            model = new EJSModel(doc);
            doc.putProperty(EJSModel.class, model);
        }

        return model;
    }
    
    EJSModel(Document doc) {
        this.doc = doc;

        if (doc != null) { // null in some unit tests
            TokenHierarchy hi = TokenHierarchy.get(doc);
            hi.addTokenHierarchyListener(new TokenHierarchyListener() {
                public void tokenHierarchyChanged(TokenHierarchyEvent evt) {
                    documentDirty = true;
                }
            });
        }
    }

    public String getJsCode() {
        if (documentDirty) {
            documentDirty = false;

            // Debugging
            //try {
            //    rhtmlCode = doc.getText(0, doc.getLength());
            //} catch (Exception e) {
            //    e.printStackTrace();
            //}
            codeBlocks.clear();
            StringBuilder buffer = new StringBuilder();
            
            BaseDocument d = (BaseDocument) doc;
            try {
                d.readLock();
                TokenHierarchy<Document> tokenHierarchy = TokenHierarchy.get(doc);
                TokenSequence<EJSTokenId> tokenSequence = tokenHierarchy.tokenSequence(EJSTokenId.language()); //get top level token sequence

                ejavascript(buffer, tokenHierarchy, tokenSequence);
            } finally {
                d.readUnlock();
            }
            jsCode = buffer.toString();
        }
        //System.out.println("jsCode is:"+jsCode);
        return jsCode;
    }
    
    /** Perform ejavascript translation 
     * @param outputBuffer The buffer to emit the translation to
     * @param tokenHierarchy The token hierarchy for the EJS code
     * @param tokenSequence  The token sequence for the EJS code
     */
    void ejavascript(StringBuilder outputBuffer,
            TokenHierarchy<Document> tokenHierarchy,            
            TokenSequence<EJSTokenId> tokenSequence) {
        StringBuilder buffer = outputBuffer;
        // Add a super class such that code completion, goto declaration etc.
        // knows where to pull the various link_to etc. methods from
        
        // Pretend that this code is an extension to ActionView::Base such that
        // code completion, go to declaration etc. sees the inherited methods from
        // ActionView -- link_to and friends.
//ludo        buffer.append("class ActionView::Base\n"); // NOI18N
        // TODO Try to include the helper class as well as the controller fields too;
        // for now this logic is hardcoded into javascript's code completion engine (CodeCompleter)

        // Erubis uses _buf; I've seen ejavascript using something else (_erbout?)
        //buffer.append("_buf=''"); // NOI18N
        //codeBlocks.add(new CodeBlockData(0, 0, 0, buffer.length()));

        boolean skipNewline = false;
        while(tokenSequence.moveNext()) {
            Token<EJSTokenId> token = tokenSequence.token();

            if (token.id() == EJSTokenId.HTML){
                int sourceStart = token.offset(tokenHierarchy);
                int sourceEnd = sourceStart + token.length();
                int generatedStart = buffer.length();

                String text = token.text().toString();
                // If there is leading whitespace in this token followed by a newline,
                // emit it directly first, then insert my buffer append. Otherwise,
                // insert a semicolon if we're on the same line as the previous output.
                boolean found = false;
                int i = 0;
                for (; i < text.length(); i++) {
                    char c = text.charAt(i);
                    if (c == '\n') {
                        i++; // include it
                        found = true;
                        break;
                    } else if (!Character.isWhitespace(c)) {
                        break;
                    }
                }

                if (found) {
                    buffer.append(text.substring(0, i));
                    text = text.substring(i);
                } else {
                    buffer.append(';');
                }
                //buffer.append("_buf = _buf + (<r><![CDATA["); // NOI18N
                buffer.append("(<r><![CDATA["); // NOI18N
                if (skipNewline && text.startsWith("\n")) { // NOI18N
                    text = text.substring(1);
                    sourceEnd--;
                }
                // Escape 's in the document so they don't escape out of the javascript code
                // I don't have to do this on lines that are in comments... But no big harm
                text = text.replace("'", "\\'");
                buffer.append(text);
                // TODO: This "\n" shouldn't be there if the next "<%" is a "<%-" !
                //buffer.append("]]></r>).toString();\n"); // NOI18N
                buffer.append("]]></r>);\n"); // NOI18N
                int generatedEnd = buffer.length();

                CodeBlockData blockData = new CodeBlockData(sourceStart, sourceEnd, generatedStart, generatedEnd);
                codeBlocks.add(blockData);

                skipNewline = false;
            } else if (token.id() == EJSTokenId.JAVASCRIPT){
                int sourceStart = token.offset(tokenHierarchy);
                int sourceEnd = sourceStart + token.length();
                int generatedStart = buffer.length();

                String text = token.text().toString();
                skipNewline = false;
                if (text.endsWith("-")) { // NOI18N
                    text = text.substring(0, text.length()-1);
                    skipNewline = true;
                }

                buffer.append(text);

                int generatedEnd = buffer.length();

                CodeBlockData blockData = new CodeBlockData(sourceStart, sourceEnd, generatedStart, generatedEnd);
                codeBlocks.add(blockData);

                skipNewline = false;
            } else if (token.id() == EJSTokenId.JAVASCRIPT_EXPRESSION) {
                //buffer.append("_buf = _buf + ("); // NOI18N
                buffer.append("("); // NOI18N
                int sourceStart = token.offset(tokenHierarchy);
                int sourceEnd = sourceStart + token.length();
                int generatedStart = buffer.length();

                String text = token.text().toString();
                skipNewline = false;
                if (text.endsWith("-")) { // NOI18N
                    text = text.substring(0, text.length()-1);
                    skipNewline = true;
                }
                buffer.append(text);
                int generatedEnd = buffer.length();

                CodeBlockData blockData = new CodeBlockData(sourceStart, sourceEnd, generatedStart, generatedEnd);
                codeBlocks.add(blockData);

// Make code sanitizing work better:  buffer.append("\n).to_s;"); // NOI18N
                buffer.append(")"); // NOI18N
            }
        }

        // Close off the class
        // ejavascript also ends with this statement: _buf.to_s
        String end = "\n\n"; // NOI18N
        buffer.append(end);
        if (doc != null) {
            codeBlocks.add(new CodeBlockData(doc.getLength(), doc.getLength(), buffer.length()-end.length(), buffer.length()));
        }
    }
    
    public int sourceToGeneratedPos(int sourceOffset){
        // Caching
        if (prevLexOffset == sourceOffset) {
            return prevAstOffset;
        }
        prevLexOffset = sourceOffset;
        
        // TODO - second level of caching on the code block to catch
        // nearby searches
        
        // Not checking dirty flag here; sourceToGeneratedPos() should apply
        // to the positions as they were when we generated the javascript code
        
        CodeBlockData codeBlock = getCodeBlockAtSourceOffset(sourceOffset);
        
        if (codeBlock == null){
            return -1; // no embedded java code at the offset
        }
        
        int offsetWithinBlock = sourceOffset - codeBlock.sourceStart;
        int generatedOffset = codeBlock.generatedStart+offsetWithinBlock;
        if (generatedOffset <= codeBlock.generatedEnd) {
            prevAstOffset = generatedOffset;
        } else {
            prevAstOffset = codeBlock.generatedEnd;
        }
        
        return prevAstOffset;
    }
    
    public int generatedToSourcePos(int generatedOffset) {
        // Caching
        if (prevAstOffset == generatedOffset) {
            return prevLexOffset;
        }
        prevAstOffset = generatedOffset;
        // TODO - second level of caching on the code block to catch
        // nearby searches

        
        // Not checking dirty flag here; generatedToSourcePos() should apply
        // to the positions as they were when we generated the javascript code

        CodeBlockData codeBlock = getCodeBlockAtGeneratedOffset(generatedOffset);
        
        if (codeBlock == null){
            return -1; // no embedded java code at the offset
        }
        
        int offsetWithinBlock = generatedOffset - codeBlock.generatedStart;
        int sourceOffset = codeBlock.sourceStart+offsetWithinBlock;
        if (sourceOffset <= codeBlock.sourceEnd) {
            prevLexOffset = sourceOffset;
        } else {
            prevLexOffset = codeBlock.sourceEnd;
        }
        
        return prevLexOffset;
    }
    
    private CodeBlockData getCodeBlockAtSourceOffset(int offset){
        for (CodeBlockData codeBlock : codeBlocks){
            if (codeBlock.sourceStart <= offset && codeBlock.sourceEnd >= offset){
                return codeBlock;
            }
        }
        return null;
    }

    private CodeBlockData getCodeBlockAtGeneratedOffset(int offset){
        // TODO - binary search!! they are ordered!
        for (CodeBlockData codeBlock : codeBlocks){
            if (codeBlock.generatedStart <= offset && codeBlock.generatedEnd >= offset){
                return codeBlock;
            }
        }
        return null;
    }
    
    private class CodeBlockData {
        /** Start of section in EJS file */
        private int sourceStart;
        /** End of section in EJS file */
        private int sourceEnd;
        /** Start of section in generated javascript */
        private int generatedStart;
        /** End of section in generated javascript */
        private int generatedEnd;
        
        public CodeBlockData(int sourceStart, int sourceEnd, int generatedStart, int generatedEnd) {
            this.sourceStart = sourceStart;
            this.generatedStart = generatedStart;
            this.sourceEnd = sourceEnd;
            this.generatedEnd = generatedEnd;
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("CodeBlockData[");
            sb.append("\n  EJS(" + sourceStart+","+sourceEnd+")");
            //sb.append("=\"");
            //sb.append(rhtmlCode.substring(sourceStart, sourceEnd));
            //sb.append("\"");
            sb.append(",\n  JAVASCRIPT(" + generatedStart + "," + generatedEnd + ")");
            //sb.append("=\"");
            //sb.append(javascriptCode.substring(generatedStart,generatedEnd));
            //sb.append("\"");
            sb.append("]");
            
            return sb.toString();
        }
    }

    // For debugging only; pass in "javascriptCode" or "rhtmlCode" in RhtmlModel to print
    //private String debugPos(String code, int pos) {
    //    if (pos == -1) {
    //        return "<-1:notfound>";
    //    }
    //
    //    int start = pos-15;
    //    if (start < 0) {
    //        start = 0;
    //    }
    //    int end = pos+15;
    //    if (end > code.length()) {
    //        end = code.length();
    //    } 
    //
    //    return code.substring(start, pos) + "^" + code.substring(pos, end);
    //}
}
