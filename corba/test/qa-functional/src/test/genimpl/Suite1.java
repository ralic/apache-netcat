/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2002 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package test.genimpl;

public class Suite1 extends Main {
    
    public Suite1(String name) {
        super(name);
    }
    
    public static junit.framework.Test suite() {
        org.netbeans.junit.NbTestSuite test = new org.netbeans.junit.NbTestSuite();
        test.addTest(new Suite1("testGenImpl_Suite1"));
        return test;
    }
    
    public void testGenImpl_Suite1 () {
        testGenImpl("OPEN1X", false, "data/genimpl/stage1");
        testGenImpl("OPEN1X", false, "data/genimpl/stage3");
        compareReferenceFiles();
    }
    
    public static void main(java.lang.String[] args) {
        junit.textui.TestRunner.run(suite());
    }
    
}
