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
 * The Original Software is the LaTeX module.
 * The Initial Developer of the Original Software is Jan Lahoda.
 * Portions created by Jan Lahoda_ are Copyright (C) 2002,2003.
 * All Rights Reserved.
 *
 * Contributor(s): Jan Lahoda.
 */
package org.netbeans.modules.latex.editor;

/**
 *
 * @author Jan Lahoda
 */
public abstract class ErrorManager {

    private static ErrorManager instance = null;

    /** Creates a new instance of ErrorManager */
    protected ErrorManager() {
    }

    public synchronized static ErrorManager getDefault() {
        if (instance == null) {
            try {
                Class.forName("org.openide.ErrorManager");
                instance = new IDEErrorManager();
            } catch (Throwable t) {
                if (t instanceof ThreadDeath)
                    throw (ThreadDeath) t;
                
                instance = new SAErrorManager();
            }
        }
        
        return instance;
    }

    public abstract Throwable annotate(Throwable t, String annotation);
    public abstract Throwable annotate(Throwable t, Throwable th);
    public abstract void notify(Throwable t);
    public abstract void notifyInformational(Throwable t);
    
    private static class SAErrorManager extends ErrorManager {
        
        protected SAErrorManager() {
        }
        
        public Throwable annotate(Throwable t, String annotation) {
            System.err.println("About to annotate: ");
            t.printStackTrace(System.err);
            System.err.println("With: \"" + annotation + "\".");
            
            return t;
        }
    
        public void notify(Throwable t) {
            t.printStackTrace(System.err);
        }
        
        public Throwable annotate(Throwable t, Throwable th) {
            System.err.println("About to annotate: ");
            t.printStackTrace(System.err);
            System.err.println("With: ");
            th.printStackTrace(System.err);
            
            return t;
        }

        public void notifyInformational(Throwable t) {
            notify(t);
        }
        
    }
    
    private static class IDEErrorManager extends ErrorManager {
        
        public Throwable annotate(Throwable t, String annotation) {
            return org.openide.ErrorManager.getDefault().annotate(t, annotation);
        }
        
        public void notify(Throwable t) {
            org.openide.ErrorManager.getDefault().notify(t);
        }
        
        public Throwable annotate(Throwable t, Throwable th) {
            return org.openide.ErrorManager.getDefault().annotate(t, th);
        }

        public void notifyInformational(Throwable t) {
            org.openide.ErrorManager.getDefault().notify(org.openide.ErrorManager.INFORMATIONAL, t);
        }
        
    }
}
