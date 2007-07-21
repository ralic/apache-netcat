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
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.latex.refactoring.actions;

import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.text.Document;
import org.netbeans.api.gsf.CancellableTask;
import org.netbeans.api.retouche.source.CompilationController;
import org.netbeans.api.retouche.source.Phase;
import org.netbeans.api.retouche.source.Source;
import org.netbeans.modules.latex.model.LaTeXParserResult;
import org.netbeans.modules.latex.model.command.ArgumentNode;
import org.netbeans.modules.latex.model.command.BlockNode;
import org.netbeans.modules.latex.model.command.Command;
import org.netbeans.modules.latex.model.command.CommandNode;
import org.netbeans.modules.latex.model.command.Environment;
import org.netbeans.modules.latex.model.command.Node;
import org.netbeans.modules.latex.model.command.impl.NodeImpl;
import org.netbeans.modules.latex.refactoring.FindUsagesPerformer;
import org.netbeans.modules.latex.refactoring.UsagesQuery;
import org.netbeans.modules.refactoring.api.Problem;
import org.netbeans.modules.refactoring.spi.ui.ActionsImplementationProvider;
import org.netbeans.modules.refactoring.spi.ui.UI;
import org.openide.cookies.EditCookie;
import org.openide.cookies.EditorCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

/**
 *
 * @author Jan Lahoda
 */
public class ActionsImplementationProviderImpl extends ActionsImplementationProvider {

    public ActionsImplementationProviderImpl() {
    }

    @Override
    public boolean canFindUsages(Lookup lookup) {
        //not correct currently:
        org.openide.nodes.Node n = lookup.lookup(org.openide.nodes.Node.class);
        
        if (n == null)
            return false;
        
        FileObject f = n.getLookup().lookup(FileObject.class);
        
        return "text/x-tex".equals(FileUtil.getMIMEType(f));
    }

    
    @Override
    public void doFindUsages(Lookup lookup) {
        perform(lookup, true);
    }

    @Override
    public boolean canRename(Lookup lookup) {
        return canFindUsages(lookup);
    }

    @Override
    public void doRename(Lookup lookup) {
        perform(lookup, false);
    }
    
    private void perform(Lookup lookup, boolean whereUsed) {
        try {
        //XXX:
        org.openide.nodes.Node n = lookup.lookup(org.openide.nodes.Node.class);
        
        FileObject file = n.getLookup().lookup(FileObject.class);
        
//        JEditorPane jep = lookup.lookup(JEditorPane.class);
//        final Document doc = jep.getDocument();
//        final int caret = jep.getCaretPosition();
//        DataObject od = (DataObject) doc.getProperty(Document.StreamDescriptionProperty);
//        FileObject file = od.getPrimaryFile();
        
        DataObject od = DataObject.find(file);
        EditorCookie ec = od.getLookup().lookup(EditorCookie.class);
        
        JEditorPane[] panes = ec.getOpenedPanes();
        if (panes == null) {
            System.err.println("#############################");
            return ;
        }
        
        final String[] searchFor = new String[1];
        final Problem[] problem = new Problem[1];
        
        final int caret = panes[0].getCaretPosition();
        
            final Source source = Source.forFileObject(file);
            
            source.runUserActionTask(new CancellableTask<CompilationController>() {
                public void cancel() {}
                public void run(CompilationController parameter) throws Exception {
                    parameter.toPhase(Phase.RESOLVED);
                    
                    LaTeXParserResult lpr = (LaTeXParserResult) parameter.getParserResult();
                    Document doc = parameter.getDocument();
                    
                    if (doc == null)
                        return ;
                    
                    if (searchFor(doc, lpr, caret) != null) {
                        searchFor[0] = "Something";
                    } else {
                        searchFor[0] ="Nothing";
                        problem[0] = new Problem(true, "Cannot resolve");
                    }
                }
            }, true);
        
            UI.openRefactoringUI(new LaTeXFURefactoringUI(source, caret, searchFor[0], problem[0], whereUsed));
        } catch (IOException e) {
            Exceptions.printStackTrace(e);
        }
    }
    
    public static Object searchFor(Document doc, LaTeXParserResult lpr, int caret) throws IOException {
        Node n = lpr.getCommandUtilities().findNode(doc, caret);
        FindUsagesPerformer performer = null;
        String name = null;
        
        if (n instanceof ArgumentNode) {
            ArgumentNode anode = (ArgumentNode) n;
            
            if (anode.hasAttribute("#ref") || anode.hasAttribute("#label")) {
                //check for label usages:
                return UsagesQuery.getArgumentContent(anode).toString();
            }
            
            if (anode.hasAttribute("#command-def")) {
                name = UsagesQuery.getArgumentContent(anode).toString();
                return ((NodeImpl) anode.getCommand()).getCommand(name, true);
            }
            
            if (anode.hasAttribute("#envname")) {
                name = UsagesQuery.getArgumentContent(anode).toString();
                return ((NodeImpl) anode.getCommand()).getEnvironment(name, true);
            }
            
            if (anode.hasAttribute("#environmentname")) {
                Node block = anode.getCommand().getParent();
                
                if (block instanceof BlockNode) {
                    return ((BlockNode) block).getEnvironment();
                }
            }
        }
        
        if (n instanceof CommandNode) {
            //TODO: check if the command has been defined in the document, not in the class or package:
            CommandNode cnode = (CommandNode) n;
            Command cmd = cnode.getCommand();
            
            return cmd.getCommand();
        }
        
        return null;
    }
}
