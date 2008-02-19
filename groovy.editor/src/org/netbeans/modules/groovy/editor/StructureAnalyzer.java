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
package org.netbeans.modules.groovy.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.text.BadLocationException;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.netbeans.api.gsf.CompilationInfo;
import org.netbeans.api.gsf.ElementKind;
import org.netbeans.api.gsf.HtmlFormatter;
import org.netbeans.api.gsf.OffsetRange;
import org.netbeans.api.gsf.StructureItem;
import org.netbeans.api.gsf.StructureScanner;
import org.netbeans.api.lexer.Token;
import org.netbeans.api.lexer.TokenSequence;
import org.netbeans.editor.BaseDocument;
import org.netbeans.editor.Utilities;
import org.netbeans.modules.groovy.editor.elements.AstClassElement;
import org.netbeans.modules.groovy.editor.elements.AstElement;
import org.netbeans.modules.groovy.editor.elements.AstFieldElement;
import org.netbeans.modules.groovy.editor.elements.AstMethodElement;
import org.netbeans.modules.groovy.editor.parser.GroovyParserResult;
import org.openide.util.Exceptions;
import org.netbeans.modules.groovy.editor.lexer.LexUtilities;
import org.netbeans.modules.groovy.editor.lexer.GroovyTokenId;

/**
 * @author Martin Adamek
 */
public class StructureAnalyzer implements StructureScanner {

    private List<AstElement> structure;
    private Map<AstClassElement, Set<FieldNode>> fields;
    private List<AstMethodElement> methods;

    public AnalysisResult analyze(GroovyParserResult result) {
        return scan(result);
    }

    public List<? extends StructureItem> scan(CompilationInfo info, HtmlFormatter formatter) {
        return Collections.<StructureItem>emptyList();
    }

    private AnalysisResult scan(GroovyParserResult result) {
        AnalysisResult analysisResult = new AnalysisResult();

        ASTNode root = AstUtilities.getRoot(result);

        if (root == null) {
            return analysisResult;
        }

        structure = new ArrayList<AstElement>();
        fields = new HashMap<AstClassElement, Set<FieldNode>>();
        methods = new ArrayList<AstMethodElement>();

        AstPath path = new AstPath();
        path.descend(root);
        // TODO: I should pass in a "default" context here to stash methods etc. outside of modules and classes
        scan(root, path, null, null, null);
        path.ascend();

        // Process fields
        Map<String, FieldNode> names = new HashMap<String, FieldNode>();

        for (AstClassElement clz : fields.keySet()) {
            Set<FieldNode> assignments = fields.get(clz);

            // Find unique variables
            if (assignments != null) {
                for (FieldNode assignment : assignments) {
                    names.put(assignment.getName(), assignment);
                }

                // Add unique fields
                for (FieldNode field : names.values()) {
                    AstFieldElement co = new AstFieldElement(field);
                    //co.setIn(AstUtilities.getClassOrModuleName(clz));
                    co.setIn(clz.getFqn());

                    // Make sure I don't already have an entry for this field as an
                    // attr_accessor or writer
                    String fieldName = field.getName();

                    boolean found = false;

                    for (AstElement member : clz.getChildren()) {
                        if ((member.getKind() == ElementKind.ATTRIBUTE) &&
                                member.getName().equals(fieldName)) {
                            found = true;

                            break;
                        }
                    }

                    if (!found) {
                        clz.addChild(co);
                    }
                }

                names.clear();
            }
        }

        analysisResult.setElements(structure);

        return analysisResult;
    }

    private void scan(ASTNode node, AstPath path, String in, Set<String> includes, AstElement parent) {
        
        if (node instanceof ClassNode) {
            AstClassElement co = new AstClassElement(node);
            co.setFqn(((ClassNode)node).getName());
            co.setIn(in);
            
            if (parent != null) {
                parent.addChild(co);
            } else {
                structure.add(co);
            }

            parent = co;
        } else if (node instanceof FieldNode) {
            if (parent instanceof AstClassElement) {
                // We don't have unique declarations, only assignments (possibly many)
                // so stash these in a map and extract unique fields when we're done
                Set<FieldNode> assignments = fields.get(parent);

                if (assignments == null) {
                    assignments = new HashSet<FieldNode>();
                    fields.put((AstClassElement)parent, assignments);
                }

                assignments.add((FieldNode)node);
            }
        } else if (node instanceof MethodNode) {
            AstMethodElement co = new AstMethodElement(node);
            methods.add(co);
            co.setIn(in);

            // TODO - don't add this to the top level! Make a nested list
            if (parent != null) {
                parent.addChild(co);
            } else {
                structure.add(co);
            }
        }

        @SuppressWarnings("unchecked")
        List<ASTNode> list = AstUtilities.children(node);

        for (ASTNode child : list) {
            path.descend(child);
            scan(child, path, in, includes, parent);
            path.ascend();
        }
    }

    public Map<String, List<OffsetRange>> folds(CompilationInfo info) {
        ASTNode root = AstUtilities.getRoot(info);

        if (root == null) {
            return Collections.emptyMap();
        }

        GroovyParserResult rpr = (GroovyParserResult)info.getParserResult();
        AnalysisResult analysisResult = rpr.getStructure();

        Map<String,List<OffsetRange>> folds = new HashMap<String,List<OffsetRange>>();
        List<OffsetRange> codefolds = new ArrayList<OffsetRange>();
        folds.put("codeblocks", codefolds); // NOI18N

        try {
            BaseDocument doc = (BaseDocument)info.getDocument();
            
            TokenSequence<?> ts = LexUtilities.getGroovyTokenSequence(doc, 1);
            
            int importStart = 0;
            int importEnd   = 0;
            
            while (ts.moveNext()) {
                Token t = ts.token();
                if (t.id() == GroovyTokenId.LITERAL_import) {
                    int offset = ts.offset();
                    if (importStart == 0) {
                        importStart = offset;
                    }
                    importEnd = offset;
                }
            }
            
            importEnd = Utilities.getRowEnd(doc, importEnd);
            
            // see GsfFoldManager.addTree() for suitable blocknames.
            
            List<OffsetRange> importfolds = new ArrayList<OffsetRange>();
            OffsetRange range = new OffsetRange(importStart, importEnd);
            importfolds.add(range);
            folds.put("imports", importfolds); // NOI18N

            addFolds(doc, analysisResult.getElements(), folds, codefolds);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        
        return folds;
    }
    
    private String aaa;
    
    private void addFolds(BaseDocument doc, List<? extends AstElement> elements, 
            Map<String,List<OffsetRange>> folds, List<OffsetRange> codeblocks) throws BadLocationException {
        for (AstElement element : elements) {
            ElementKind kind = element.getKind();
            switch (kind) {
            case FIELD:
            case METHOD:
            case CONSTRUCTOR:
            case CLASS:
            case MODULE:
                ASTNode node = element.getNode();
                OffsetRange range = AstUtilities.getRangeFull(node, doc);
                
//                System.out.println("### range: " + element + ", " + range.getStart() + ", " + range.getLength());
                
                if (kind == ElementKind.METHOD || kind == ElementKind.CONSTRUCTOR || 
                    (kind == ElementKind.FIELD && ((FieldNode)node).getInitialExpression() instanceof ClosureExpression) ||
                    // Only make nested classes/modules foldable, similar to what the java editor is doing
                    (range.getStart() > Utilities.getRowStart(doc, range.getStart())) && kind != ElementKind.FIELD) {
                    
                    int start = range.getStart();
                    // Start the fold at the END of the line behind last non-whitespace, remove curly brace, if any
                    start = Utilities.getRowLastNonWhite(doc, start);
                    if (doc.getChars(start, 1)[0] != '{') {
                        start++;
                    }
                    int end = range.getEnd();
                    if (start != (-1) && end != (-1) && start < end && end <= doc.getLength()) {
                        range = new OffsetRange(start, end);
                        codeblocks.add(range);
                    }
                }
                break;
            }
            
            List<? extends AstElement> children = element.getChildren();
            
            if (children != null && children.size() > 0) {
                addFolds(doc, children, folds, codeblocks);
            }
        }
    }

    public static final class AnalysisResult {

        private List<?extends AstElement> elements;
        
        Set<String> getRequires() {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        List<?extends AstElement> getElements() {
            if (elements == null) {
                return Collections.emptyList();
            }
            return elements;
        }

        private void setElements(List<?extends AstElement> elements) {
            this.elements = elements;
        }
        
    }
    
}
