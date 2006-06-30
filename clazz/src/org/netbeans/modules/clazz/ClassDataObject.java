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

package org.netbeans.modules.clazz;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import javax.jmi.reflect.InvalidObjectException;
import org.netbeans.api.java.classpath.ClassPath;
import org.netbeans.api.java.queries.SourceForBinaryQuery;
import org.netbeans.modules.classfile.ClassFile;
import org.netbeans.spi.java.classpath.support.ClassPathSupport;
import org.openide.ErrorManager;
import org.openide.awt.StatusDisplayer;
import org.openide.cookies.InstanceCookie;
import org.openide.cookies.SourceCookie;
import org.openide.cookies.OpenCookie;
import org.openide.filesystems.FileChangeAdapter;
import org.openide.filesystems.FileEvent;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileStateInvalidException;
import org.openide.filesystems.FileUtil;
import org.openide.filesystems.Repository;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.loaders.InstanceSupport;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.CookieSet.Factory;
import org.openide.nodes.CookieSet;
import org.openide.nodes.Node.Cookie;
import org.openide.src.ClassElement;
import org.openide.src.ConstructorElement;
import org.openide.src.Identifier;
import org.openide.src.SourceElement;
import org.openide.src.Type;
import org.openide.src.nodes.ElementNodeFactory;
import org.openide.src.nodes.FilterFactory;
import org.openide.util.NbBundle;

/** This DataObject loads sourceless classes and provides a common framework
 * for presenting them in the IDE.
 * The descendants define specific behaviour, restrictions and operations.<P>
 * <B>Note:</B> The previous version of ClassDataObject has become CompiledDataObject.
 * Because ClassDataObject was public, the name was retained for compatibility reasons.
 * Method behaviour specific to compiled classes was moved to CompiledDataObject and
 * the behaviour specific to serialized objects was moved into SerDataObject.
 *
 * @author sdedic
 */
public class ClassDataObject extends MultiDataObject implements Factory, SourceCookie {
    public static final String PROP_CLASS_LOADING_ERROR = "classLoadingError"; // NOI18N
    /**
     * Holds an exception that occured during an attempt to create the class.
     */
    private Throwable classLoadingError;

    /** The generic type */
    protected static final int UNDECIDED = 0;

    /** The DataObject contains an applet */
    protected static final int APPLET = 1;

    /** The DataObject is an application. */
    protected static final int APPLICATION = 2;

    /** CHANGE!!! */
    static final long serialVersionUID = -1;

    /** Support for working with class */
    transient private InstanceSupport instanceSupport;

    transient private boolean sourceCreated;
    
    transient PropL propL;
    
    transient Reference srcEl = new WeakReference(null);
    
    transient private ClassFile mainClass; // don't access directly, use getMainClass() instead
    
    /** Creates a new sourceless DataObject.
     */
    public ClassDataObject(final FileObject fo, final MultiFileLoader loader) throws DataObjectExistsException {
        super(fo, loader);
    }

    static ClassDataObject createSerDataObject(FileObject fo, ClassDataLoader dl)
                throws DataObjectExistsException, IOException {
        return new SerDataObject(fo, dl);
    }

    static ClassDataObject createCompiledDataObject(FileObject fo, ClassDataLoader dl)
                throws DataObjectExistsException, IOException {
        return new CompiledDataObject(fo, dl);
    }
    
    private class PropL extends FileChangeAdapter implements Runnable, PropertyChangeListener {
        public void propertyChange(PropertyChangeEvent ev) {
            String prop = ev.getPropertyName();
            if (PROP_PRIMARY_FILE.equals(prop)) {
                FileObject p = getPrimaryFile();
                p.addFileChangeListener(FileUtil.weakFileChangeListener(this, getPrimaryFile()));
                postReload();
            }
        }
        
        public void fileChanged(FileEvent ev) {
            postReload();
        }
        
        public void run() {
            forceReload();
        }
        
        private void postReload() {
            Util.getClassProcessor().post(this, 100);
        }
    }
    
    protected void initCookies() {
        CookieSet cs = getCookieSet();
        cs.add(new Class[] {
            SourceCookie.class, 
        }, this);
        cs.add(OpenCookie.class,this);
    }

    /**
     * Creates InstanceSupport for the primary .class file.
     */
    protected InstanceSupport createInstanceSupport() {
        if (instanceSupport != null)
            return instanceSupport;
        synchronized (this) {
            if (instanceSupport == null) {
                instanceSupport = new ClazzInstanceSupport(getPrimaryEntry());
                if (propL == null) {
                    propL = new PropL();
                    FileObject p = getPrimaryFile();
                    p.addFileChangeListener(FileUtil.weakFileChangeListener(propL, p));
                }
            }
        }
        return instanceSupport;
    }
    
    public SourceElement getSource() {
        SourceElement s;

        s = (SourceElement)srcEl.get();
        if (s != null)
            return s;
        synchronized (this) {
            s = (SourceElement)srcEl.get();
            if (s != null)
                return s;
            sourceCreated = true;
            s = new SourceElement(new SourceElementImpl(this));
            srcEl = new WeakReference(s);
        }
        return s;
    }
    
    public Cookie createCookie(Class desired) {
        if (desired == InstanceCookie.class) {
            return createInstanceSupport();
        }
        else if (OpenCookie.class.isAssignableFrom(desired)) {
            return new OpenSourceCookie ();
        }
        return null;
    }
    
    protected Throwable getClassLoadingError() {
        getClassFile();
        return classLoadingError;
    }
    
    /**
     * Forces reload of the class and the whole instance support.
     */
    protected void forceReload() {
        CookieSet s = getCookieSet();
        InstanceCookie prevCookie;
    
        prevCookie = instanceSupport;
        synchronized (this) {
            instanceSupport = null;
            mainClass = null;
        }
        // if the previous support was != null, it recreates the cookie
        // (and fires PROP_COOKIE change).
        if (prevCookie != null) {
            s.remove(prevCookie);
            s.add(new Class[] {
                InstanceCookie.class
            }, this);
        }

        if (sourceCreated) {
            SourceCookie sc = (SourceCookie)getCookie(SourceCookie.class);
            SourceElementImpl impl = (SourceElementImpl)sc.getSource().getCookie(SourceElement.Impl.class);
            if (impl != null)
                impl.setResource(null);
        }
    }
    
    /**
     * Returns the "main" class for this DataObject, or null if the class
     * cannot be loaded. In such case, it records the error trace into
     * {@link #classLoadingError} variable.
     */
    protected ClassFile getClassFile() {
        if (mainClass==null) {        
            Throwable t = this.classLoadingError;
            classLoadingError = null;
            try {
                mainClass = loadClassFile();            
            } catch (RuntimeException ex) {
                classLoadingError = ex;
            } catch (IOException ex) {
                classLoadingError = ex;
            } catch (ClassNotFoundException ex) {
                classLoadingError = ex;
            }
            if (classLoadingError != null)
                firePropertyChange(PROP_CLASS_LOADING_ERROR, t, classLoadingError);
        }
        return mainClass;
    }
    
    protected ClassElement getMainClass() {
        ClassElement ce[]=getSource().getClasses();
        
        if (ce.length==0)
            return null;
        return ce[0];
    }
    
    protected ClassFile loadClassFile() throws IOException,ClassNotFoundException {
        InputStream stream=getPrimaryEntry().getFile().getInputStream();
        
        if (stream==null)
            return null;
        try {
            return new ClassFile(stream,false);
        } finally {
            stream.close();
        }   
    }
    
    // DataObject implementation .............................................

    /** Getter for copy action.
    * @return true if the object can be copied
    */
    public boolean isCopyAllowed () {
        return true;    
    }

    public boolean isMoveAllowed() {
        return getPrimaryFile().canWrite();
    }

    public boolean isRenameAllowed() {
        return getPrimaryFile().canWrite();
    }

    // =======================================================================
    // Various properties describing the file although it is questionable to 
    // put them at the file level. At least JavaBean and Applet are properties
    // of a class, not a file or serialized object.
    public boolean isJavaBean () {
        return createInstanceSupport().isJavaBean();
    }

    public boolean isApplet () {
        return createInstanceSupport().isApplet ();
    }

    public boolean isInterface () {
        return createInstanceSupport().isInterface ();
    }

    public String getSuperclass () {
        ClassElement ce=getMainClass();
        
        if (ce==null)
            return "";
        Identifier id = ce.getSuperclass();
        return id == null ? "" : id.getFullName();
    }

    public String getModifiers () throws IOException, ClassNotFoundException {
        ClassElement ce=getMainClass();
        
        if (ce==null)
            throw new ClassNotFoundException();
        return Modifier.toString(ce.getModifiers());
    }

    public String getClassName () {
        return createInstanceSupport().instanceName ();
    }

    public Class getBeanClass () throws IOException, ClassNotFoundException {
        return createInstanceSupport().instanceClass ();
    }

    // =============== The mechanism for regeisteing node factories ==============

    // =============== The mechanism for regeisteing node factories ==============
    
    private static NodeFactoryPool explorerFactories;
    private static NodeFactoryPool browserFactories;
    private static ElementNodeFactory basicBrowser;
    private static ElementNodeFactory basicExplorer;

    /**
     * DO NOT USE THIS METHOD!!! <P>
     * This method is intended to be called only during initialization of java
     * module-provided node factories from the installation layer. It won't
     * be maintained for compatibility reasons.
     */
    synchronized static ElementNodeFactory createBasicExplorerFactory() {
        if (basicExplorer == null) {
            basicExplorer = new ClassElementNodeFactory();
        }
        return basicExplorer;
    }
    
    /**
     * DO NOT USE THIS METHOD!!! <P>
     * This method is intended to be called only during initialization of java
     * module-provided node factories from the installation layer. It won't
     * be maintained for compatibility reasons.
     */
    synchronized static ElementNodeFactory createBasicBrowserFactory() {
        if (basicBrowser == null) {
            basicBrowser = new ClassElementNodeFactory();
            ((ClassElementNodeFactory)basicBrowser).setGenerateForTree (true);
        }
        return basicBrowser;
    }

    public static ElementNodeFactory getExplorerFactory() {
        NodeFactoryPool pool = createExplorerFactory();
        ElementNodeFactory f = null;
        
        if (pool != null)
            f = pool.getHead();
        if (f == null)
            f = createBasicExplorerFactory();
        return f;
    }
    
    public static ElementNodeFactory getBrowserFactory() {
        NodeFactoryPool pool = createBrowserFactory();
        ElementNodeFactory f = null;
        
        if (pool != null)
            f = pool.getHead();
        if (f == null)
            f = createBasicBrowserFactory();
        return f;
    }

    static NodeFactoryPool createFactoryPool(String folderName, ElementNodeFactory def) {
        FileObject f = Repository.getDefault().getDefaultFileSystem().findResource(folderName);
	if (f == null)
    	    return null;
        try {
            DataFolder folder = (DataFolder)DataObject.find(f).getCookie(DataFolder.class);
            return new NodeFactoryPool(folder, def);
        } catch (DataObjectNotFoundException ex) {
            return null;
        }
    }
    
    synchronized static NodeFactoryPool createBrowserFactory() {
        if (browserFactories != null)
            return browserFactories;
        browserFactories = createFactoryPool("/NodeFactories/clazz/objectbrowser", createBasicBrowserFactory()); // NOI18N
        return browserFactories;
    }
    
    synchronized static NodeFactoryPool createExplorerFactory() {
        if (explorerFactories != null)
            return explorerFactories;
        explorerFactories = createFactoryPool("/NodeFactories/clazz/explorer", createBasicExplorerFactory()); // NOI18N
        return explorerFactories;
    }

    /**
     * @deprecated use installation layer for registering a factory for the the whole
     * time a module is installed. Note: This feature will be dropped in the next
     * release.
     */
    public static void addExplorerFilterFactory( FilterFactory factory ) {
        NodeFactoryPool p = createExplorerFactory();
        if (p != null)
            p.addFactory(factory);
    }

    /**
     * @deprecated use installation layer for registering a factory for the the whole
     * time a module is installed. Note: This feature will be dropped in the next
     * release.
     */
    public static void removeExplorerFilterFactory( FilterFactory factory ) {
        NodeFactoryPool p = createExplorerFactory();
        if (p != null)
            p.removeFactory(factory);
    }

    /**
     * @deprecated use installation layer for registering a factory for the the whole
     * time a module is installed. Note: This feature will be dropped in the next
     * release.
     */
    public static void addBrowserFilterFactory(FilterFactory factory) {
        NodeFactoryPool p = createBrowserFactory();
        if (p != null)
            p.addFactory(factory);
    }

    /**
     * @deprecated use installation layer for registering a factory for the the whole
     * time a module is installed. Note: This feature will be dropped in the next
     * release.
     */
    public static void removeBrowserFilterFactory( FilterFactory factory ) {
        NodeFactoryPool p = createBrowserFactory();
        if (p != null)
            p.removeFactory(factory);
    }

    protected final class ClazzInstanceSupport extends InstanceSupport {

        /** the class is bean */
        private Boolean bean;

        /** the class is executable */
        private Boolean executable;

        ClazzInstanceSupport(MultiDataObject.Entry entry) {
            super(entry);
        }
        
        public Class instanceClass() throws IOException, ClassNotFoundException {
            try {
                Class c = super.instanceClass();
                return c;
            } catch (RuntimeException ex) {
                // convert RuntimeExceptions -> CNFE
                ClassDataObject.this.classLoadingError = ex;
                throw new ClassNotFoundException(ex.getMessage());
            }
        }
        
        /** 
         * Creates class loader that permits <<ALL FILES>> and all properties to be read,
         * and is based on a reference file.
         */
        protected ClassLoader createClassLoader(FileObject ref) {
            Collection c = new HashSet ();
            ClassPath cp = ClassPath.getClassPath(ref, ClassPath.COMPILE);
            if (cp != null) {
                c.add (cp);
            }
            cp = ClassPath.getClassPath(ref, ClassPath.EXECUTE);
            if (cp != null) {
                c.add (cp);
            }
            if (c.size()>0) {
                cp = ClassPathSupport.createProxyClassPath((ClassPath[])c.toArray(new ClassPath[c.size()]));
                return cp.getClassLoader(true);
            }
            else {
                return null;
            }
        }
        
        /** Is this a JavaBean?
         * @return <code>true</code> if this class represents JavaBean (is public and has a public default constructor).
         */
        public boolean isJavaBean() {
        if (bean != null) return bean.booleanValue ();
        
        // if from ser file => definitely it is a java bean
        if (isSerialized ()) {
            bean = Boolean.TRUE;
            return true;
        }
        
        // try to find out...
        try {
            ClassElement clazz = getMainClass();
            
            if (clazz==null) return false;
            int modif = clazz.getModifiers();
            if (!Modifier.isPublic(modif) || Modifier.isAbstract(modif)) {
                bean = Boolean.FALSE;
                return false;
            }
            ConstructorElement c=clazz.getConstructor(new Type[0]);
            if ((c == null) || !Modifier.isPublic(c.getModifiers())) {
                bean = Boolean.FALSE;
                return false;
            }
            // check: if the class is an inner class, all outer classes have
            // to be public and in the static context:
            
            for (ClassElement outer = clazz.getDeclaringClass(); outer != null; outer = outer.getDeclaringClass()) {
                // check if the enclosed class is static
                if (!Modifier.isStatic(modif)) {
                    bean = Boolean.FALSE;
                    return false;
                }
                modif = outer.getModifiers();
                // ... and the enclosing class is public
                if (!Modifier.isPublic(modif)) {
                    bean = Boolean.FALSE;
                    return false;
                }
            }
        } catch (InvalidObjectException ioe) {
            bean = Boolean.FALSE;
            return false;
        } catch (RuntimeException ex) {
            throw ex;
        } catch (ThreadDeath t) {
            throw t;
        } catch (Throwable t) {
            // false when other errors occur (NoClassDefFoundError etc...)
            bean = Boolean.FALSE;
            return false;
        }
        // okay, this is bean...
        //    return isBean = java.io.Serializable.class.isAssignableFrom (clazz);
        bean = Boolean.TRUE;
        return true;   
        }
        
        /** Is this an interface?
         * @return <code>true</code> if the class is an interface
         */
        public boolean isInterface() {
            ClassElement ce=getMainClass();
            
            return (ce==null)?false:ce.isInterface();
        }
                
        public String instanceName() {
            ClassElement ce=getMainClass();
            
            if (ce==null)
                return super.instanceName();
            return ce.getName().getFullName();
        }
        
        /*Query to found out if the object created by this cookie is 
        * instance of given type.
        * @param type the class type we want to check
        * @return true if this cookie can produce object of given type
        */
        public boolean instanceOf(Class type) {
            String className=type.getName();
            ClassElement ce=getMainClass();
            
            if (ce == null)
                return false;
            boolean isClassType = !type.isInterface();
            String typename = type.getName().replace('$', '.');
            Identifier id;
            LinkedList l = new LinkedList();
            
            do {
                if (ce.getName().getFullName().equals(typename)) 
                    return true;
                id = ce.getSuperclass();
                Identifier[] itfs = ce.getInterfaces();
                for (int i = 0; i < itfs.length; i++) {
                    l.addLast(itfs[i]);
                }
                if (id == null) {
                    if (l.isEmpty())
                        return false;
                    else
                        id = (Identifier)l.removeFirst();
                }
                ce = ClassElement.forName(id.getFullName(), ClassDataObject.this.getPrimaryFile());
                while (ce == null && !l.isEmpty()) 
                    ce = ClassElement.forName(((Identifier)l.removeFirst()).getFullName(),
                            ClassDataObject.this.getPrimaryFile());
            } while (ce != null);
            return false;
        }

        /** Is this a standalone executable?
         * @return <code>true</code> if this class has main method
         * (e.g., <code>public static void main (String[] arguments)</code>).
         */
        public boolean isExecutable() {
            try {
                if (executable == null) {
                    ClassElement ce=getMainClass();
                    
                    executable = ((ce==null) ? false : ce.hasMainMethod()) ? Boolean.TRUE : Boolean.FALSE;
                }
            } catch (InvalidObjectException ioe) {
                executable = Boolean.FALSE;
            }
            return executable.booleanValue();
        }
        
        /** Test whether the instance represents serialized version of a class
         * or not.
         * @return true if the file entry extension is ser
         */
        private boolean isSerialized () {
            return instanceOrigin().getExt().equals("ser"); // NOI18N
        }

        public Object instanceCreate() throws java.io.IOException, ClassNotFoundException {
            try {
                if (isSerialized()) {
                    // create from ser file
                    BufferedInputStream bis = new BufferedInputStream(instanceOrigin().getInputStream(), 1024);
                    ClassLoader classLoader = createClassLoader(instanceOrigin());
                    if (classLoader == null) {
                        throw new ClassNotFoundException ();
                    }
                    CMObjectInputStream cis = new CMObjectInputStream(bis,classLoader);
                    Object o = null;
                    try {
                        o = cis.readObject();
                    } finally {
                        cis.close();
                    }
                    return o;
                } else {
                    return super.instanceCreate();
                }
            } catch (IOException ex) {
                // [PENDING] annotate with localized message
                ErrorManager.getDefault().annotate(ex, instanceName());
                throw ex;
            } catch (ClassNotFoundException ex) {
                throw ex;
            } catch (Exception e) {
                // turn other throwables into class not found ex.
                throw new ClassNotFoundException(e.toString(), e);
            } catch (LinkageError e) {
                throw new ClassNotFoundException(e.toString(), e);
                
            }
        }
        
        private final class CMObjectInputStream extends ObjectInputStream {
            
            private ClassLoader loader;
            
            protected CMObjectInputStream(InputStream s, ClassLoader cl) throws IOException {
                super(s);
                loader=cl;
            }
            
            protected Class resolveClass(ObjectStreamClass s) throws IOException, ClassNotFoundException {
                return Class.forName(s.getName(), false, loader);
            }
            
        }        
    }

    private class OpenSourceCookie implements OpenCookie {

        public void open() {
            try {
                FileObject fo = getPrimaryFile();
                FileObject binaryRoot = null;
                String resourceName = null;
                ClassPath cp = ClassPath.getClassPath (fo, ClassPath.COMPILE);
                if (cp == null || (binaryRoot = cp.findOwnerRoot (fo))==null) {
                    cp = ClassPath.getClassPath (fo, ClassPath.EXECUTE);
                    if (cp != null) {
                        binaryRoot = cp.findOwnerRoot (fo);
                        resourceName = cp.getResourceName(fo,'/',false);  //NOI18N
                    }
                }
                else if (binaryRoot != null) {
                    resourceName = cp.getResourceName(fo,'/',false);  //NOI18N
                }
                FileObject[] sourceRoots = null;
                if (binaryRoot != null) {
                     sourceRoots = SourceForBinaryQuery.findSourceRoots (binaryRoot.getURL()).getRoots();
                }
                FileObject resource = null;
                if (sourceRoots != null && sourceRoots.length>0) {
                    cp = ClassPathSupport.createClassPath(sourceRoots);
                    resource = cp.findResource (resourceName+ ".java"); //NOI18N
                }
                if (resource !=null ) {
                    DataObject sourceFile = DataObject.find(resource);
                    OpenCookie oc = (OpenCookie) sourceFile.getCookie(OpenCookie.class);
                    if (oc != null) {
                        oc.open();
                    }
                    else {
                        ErrorManager.getDefault().log ("SourceFile: "+resource.getPath()+" has no OpenCookie"); //NOI18N
                    }
                }
                else {
                   if (resourceName == null)
                       resourceName = fo.getName();
                   StatusDisplayer.getDefault().setStatusText(NbBundle.getMessage(ClassDataObject.class,"TXT_NoSources",
                        resourceName.replace('/','.'))); //NOI18N
                }
            } catch (FileStateInvalidException e) {
                ErrorManager.getDefault().notify(e);
            }
            catch (DataObjectNotFoundException nf) {
                ErrorManager.getDefault().notify(nf);
            }
        }
    }
}
