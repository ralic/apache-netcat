/*
 *                          Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License Version
 * 1.0 (the "License"). You may not use this file except in compliance with
 * the License. A copy of the License is available at http://www.sun.com/
 *
 * The Original Code is the LaTeX module.
 * The Initial Developer of the Original Code is Jan Lahoda.
 * Portions created by Jan Lahoda_ are Copyright (C) 2002,2003.
 * All Rights Reserved.
 *
 * Contributor(s): Jan Lahoda.
 */
package org.netbeans.modules.latex.model.command.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Position;
import org.netbeans.modules.latex.model.Utilities;

import org.netbeans.modules.latex.model.command.ArgumentNode;
import org.netbeans.modules.latex.model.command.CommandNode;
import org.netbeans.modules.latex.model.command.Node;
import org.netbeans.modules.latex.model.command.TraverseHandler;
import org.netbeans.modules.latex.model.command.Command;
import org.netbeans.modules.latex.model.command.Command.Param;
import org.netbeans.modules.latex.model.command.CommandCollection;
import org.netbeans.modules.latex.model.command.LaTeXSource;
import org.netbeans.modules.latex.model.command.SourcePosition;
import org.netbeans.modules.latex.test.TestCertificate;
import org.openide.ErrorManager;

/**
 *
 * @author Jan Lahoda
 */
public class CommandNodeImpl extends NodeImpl implements CommandNode {
    
    public static final Command NULL_COMMAND = new Command("\\nullcommand: ");
    private Command                   command;
    private List/*<ArgumentNode>*/    arguments;
    
    
    /** Creates a new instance of CommandNodeImpl */
    public CommandNodeImpl(Node parent, Command command, NodeImpl previousCommandDefiningNode) {
        super(parent, previousCommandDefiningNode);
        this.command = command;
        arguments    = new ArrayList/*<ArgumentNode>*/();
    }
    
    public ArgumentNode getArgument(int index) {
        return (ArgumentNode) arguments.get(index);
    }
    
    public int getArgumentCount() {
        return arguments.size();
    }
    
//    public Param getArgumentDescription(int index) {
//        return command.getArgument(index);
//    }
    
    public void putArgument(int index, ArgumentNode arg) {
//        System.err.println("putArgument, command=" + this + ", argument=" + arg + ", children count=" + arg.getChildrenCount());
        while (arguments.size() < index) {
            ArgumentNodeImpl an = new ArgumentNodeImpl(this, false, ((NodeImpl) arg).getPreviousCommandDefiningNode());
            
            an.setArgument(command.getArgument(arguments.size()));
            an.setStartingPosition(arg.getStartingPosition());
            an.setEndingPosition(arg.getStartingPosition());
            
            arguments.add(an);
        }
        
        if (arguments.size() == index) {
            arguments.add(index, arg);
        } else {
            arguments.set(index, arg);
        }
    }
    
    public void addArgument(ArgumentNode arg) {
        putArgument(arguments.size(), arg);
    }
    
    public Command getCommand() {
        return command;
    }
    
    public boolean isComplete() {
        return arguments.size() == command.getArgumentCount();
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer();
        
        sb.append("CommandNodeImpl[");
        sb.append("start=");
        sb.append(getStartingPosition());
        sb.append(", end=");
        sb.append(getEndingPosition());
        sb.append(", command=");
        sb.append(getCommand().getCommand());
        sb.append("]");
        
        return sb.toString();
    }
    
    public void traverse(TraverseHandler th) {
        if (th.commandStart(this)) {
            int count = getArgumentCount();
            
            for (int cntr = 0; cntr < count; cntr++) {
                getArgument(cntr).traverse(th);
            }
        } else {
//            System.err.println("commandStart returned false, th=" + th + ", this=" + this);
        }
        
        th.commandEnd(this);
    }
    
    public boolean isValid() {
        if (getCommand() == NULL_COMMAND)
            return false;
        
        //TODO: Check arguments;
        //TODO: Check arguments validity:
        
        return true;
    }

    public void setCommand(LaTeXSource.WriteLock lock, Command command) {
        //Validation of the lock is missing.
        SourcePosition start = getStartingPosition();
        
        try {
            Document doc = Utilities.getDefault().openDocument(start.getFile());

            doc.remove(start.getOffsetValue(), getCommand().getCommand().length());
            doc.insertString(start.getOffsetValue(), command.getCommand(), null);
        } catch (BadLocationException e) {
            IllegalStateException ex = new IllegalStateException();
            
            ErrorManager.getDefault().annotate(ex, e);
            
            throw ex;
        } catch (IOException e) {
            IllegalStateException ex = new IllegalStateException();
            
            ErrorManager.getDefault().annotate(ex, e);
            
            throw ex;
        }
        
        this.command = command;
    }

    protected boolean isInChild(Object file, Position pos) {
        int count = getArgumentCount();
        
        for (int cntr = 0; cntr < count; cntr++) {
            if (isIn(file, pos, getArgument(cntr)))
                return true;
        }
        
        return false;
    }
    
    public void dump(TestCertificate tc, PrintWriter pw) {
        pw.print("<CommandNodeImpl ");
        pw.print("name=\"");
        pw.print(getCommand());
        pw.println("\">");
        
        dumpPositions(tc, pw);
        
        for (int cntr = 0; cntr < getArgumentCount(); cntr++) {
            ((NodeImpl) getArgument(cntr)).dump(tc, pw);
        }
        
        pw.println("</CommandNodeImpl>");
    }

    public void setCommandCollection(CommandCollection commandCollection) {
        super.setCommandCollection(commandCollection);
    }
    
}
