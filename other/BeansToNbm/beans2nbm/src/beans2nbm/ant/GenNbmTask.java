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
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 */
package beans2nbm.ant;

import beans2nbm.Main;
import beans2nbm.gen.JarInfo;
import beans2nbm.gen.JarInfo.ScanObserver;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.netbeans.modules.wizard.InstructionsPanel;
import org.netbeans.spi.wizard.ResultProgressHandle;

/**
 *
 * @author Tim Boudreau
 */
public class GenNbmTask extends Task {
    private Map map = new HashMap();
    public GenNbmTask() {
    }
    
    public void execute() throws BuildException {
        String[] keys = new String[] {
        "destFolder",
        "destFileName",
        "description",
        "libversion",
        "homepage",
        "codeName",
        "jarFileName",
        "author",
        "displayName",
        "license",
        "javaVersion",
        };
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            check (key);
        }
        
        String codeName = getCodeName();
        if (codeName.indexOf(".") < 0) {
            throw new BuildException ("Code name should be a unique" +
                    " java package name, e.g. com.foo.mypackage");
        }
        
        if (getSourceJar() == null) {
            log("Source JAR not specified.  It is not" +
                    " a requirement, but it helps people to debug.  Please" +
                    " consider including one.");
        }
        if (getDocsJar() == null) {
            log("Javadoc JAR/ZIP not specified.  It is not" +
                    " a requirement, but it helps use components.  Please" +
                    " consider including one.");
        }
        
        JarInfo info = new JarInfo ((String) map.get("jarFileName"));
        O o = new O();
        info.scan(o);
        synchronized (o) {
            try         {
                log("Enter wait on ScanObserver");
                o.wait(30000);
            } catch (InterruptedException ex) {
                throw new BuildException (ex);
            }
        }
        if (o.failMsg != null) {
            throw new BuildException (o.failMsg);
        }
        List l = info.getBeans();
        if (l.isEmpty()) {
            throw new BuildException ("No JavaBeans found.  Make sure" +
                    " the JAR manifest has the appropriate" +
                    " Java-Bean sections.");
        }
        map.put("jarInfo", info);
        Main.BackgroundBuilder builder = new Main.BackgroundBuilder ();
        R r = new R();
        builder.start(map, r);
        synchronized (r) {
            try {
                log("Enter wait on Handle");
                r.wait (120000);
            } catch (Exception e) {
                throw new BuildException (e);
            }
        }
        if (r.failMsg != null) {
            throw new BuildException (r.failMsg);
        }
    }
    
    private void check (String key) throws BuildException {
        if (!map.containsKey(key)) {
            throw new BuildException (key + " not specified");
        }
    }
    
    class O implements ScanObserver {
        public void start() {
            log("Scanning JAR for JavaBeans");
        }

        public void progress(int progress) {
        }

        String failMsg;
        public void fail(String msg) {
            Thread.dumpStack();
            failMsg = msg;
            synchronized (this) {
                notifyAll();
            }
        }

        public void done() {
            log("Completed scan");
        }
    }
    
    class R implements ResultProgressHandle {
        public void setProgress(int currentStep, int totalSteps) {
            
        }

        public void setProgress(String description, int currentStep, int totalSteps) {
            log(description + " step " + currentStep + 
                    " of " + totalSteps);
        }

        public void setBusy(String description) {
        }

        public void finished(Object result) {
            synchronized (this) {
                notifyAll();
            }
            log("Created " + result);
        }

        String failMsg;
        public void failed(String message, boolean canNavigateBack) {
            Thread.dumpStack();
            failMsg = message;
            synchronized (this) {
                notifyAll();
            }
        }

        public void addProgressComponents(InstructionsPanel panel) {
        }

        public boolean isRunning() {
            //unused - no gui
            return true;
        }
    }
    
    public void setDestFolder  (File val) { map.put ("destFolder", val == null ? null : val.getAbsolutePath()); }
    public void setDestFileName  (String val) { map.put ("destFileName", val); }
    public void setDescription  (String val) { map.put ("description", val); }
    public void setVersion  (String val) { map.put ("libversion", val); }
    public void setHomepage  (String val) { map.put ("homepage", val); }
    public void setCodeName  (String val) { map.put ("codeName", val); }
    public void setJarFileName  (String val) { map.put ("jarFileName", val); }
    public void setAuthor  (String val) { map.put ("author", val); }
    public void setDocsJar  (File val) { map.put ("docsJar", val == null ? null : val.getAbsolutePath()); }
    public void setSourceJar  (File val) { map.put ("sourceJar", val == null ? null : val.getAbsolutePath()); }
    public void setDisplayName  (String val) { map.put ("displayName", val); }
    public void setLicense  (String val) { map.put ("license", val); }
    public void setMinJDK  (String val) { map.put ("javaVersion", val); }
    public File getDestFolder  () { 
        String s = (String) map.get  ("destFolder");
        return s == null ? null : new File(s); 
    }
    public String getDestFileName  () { return (String) map.get  ("destFileName"); }
    public String getDescription  () { return (String) map.get  ("description"); }
    public String getVersion  () { return (String) map.get  ("libversion"); }
    public String getHomepage  () { return (String) map.get  ("homepage"); }
    public String getCodeName  () { return (String) map.get  ("codeName"); }
    public String getJarFileName  () { return (String) map.get  ("jarFileName"); }
    public String getAuthor  () { return (String) map.get  ("author"); }
    public File getDocsJar  () { 
        String s = (String) map.get  ("docsJar");
        return s == null ? null : new File(s); 
    }
    public File getSourceJar  () { 
        String s = (String) map.get  ("sourceJar");
        return s == null ? null : new File(s); 
    }
    public String getDisplayName  () { return (String) map.get  ("displayName"); }
    public String getLicense  () { return (String) map.get  ("license"); }
    public String getMinJDK  () { return (String) map.get  ("javaVersion"); }
}
