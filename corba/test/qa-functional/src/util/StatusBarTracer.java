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

package util;

import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import junit.framework.AssertionFailedError;
import org.openide.awt.StatusDisplayer;

public class StatusBarTracer implements ChangeListener {
    
    ArrayList array;
    
    public StatusBarTracer() {
        array = new ArrayList ();
    }
    
    public void start () {
        StatusDisplayer.getDefault().addChangeListener(this);
    }
    
    public void stop () {
        StatusDisplayer.getDefault().removeChangeListener(this);
    }
    
    public void stateChanged(ChangeEvent evt) {
        synchronized (this) {
            array.add (StatusDisplayer.getDefault ().getStatusText ());
        }
    }
    
    public void clear () {
        synchronized (this) {
            array.clear();
        }
    }
    
    public String waitText (String text) {
        return waitText (60, text, true);
    }
    
    public String waitText (int iter, String text, boolean exact) {
        return waitText (iter, text, exact, false);
    }

    public String waitText (int iter, String text, boolean exact, boolean ends) {
        for (; iter > 0; iter --) {
            synchronized (this) {
                for (int a = 0; a < array.size (); a ++) {
                    String str = (String) array.get (a);
//                    System.out.println ("Status: \"" + str + "\"");
                    if ((exact  &&  str.equals (text))  ||  (!exact  &&  ((ends  &&  str.endsWith(text))  ||  (!ends  &&  str.indexOf(text) >= 0)))) {
//                        System.out.println ("Yes");
                        return str;
                    }
                }
//                System.out.println ("No");
            }
            Helper.sleep (1000);
        }
        throw new AssertionFailedError ("TIMEOUT: Waiting for status bar text: Exact: " + exact + " Text: " + text);
    }

    public String removeText (String text) {
        return removeText (text, true);
    }
    
    public String removeText (String text, boolean exact) {
        return removeText (60, text, exact);
    }
    
    public String removeText (String text, boolean exact, boolean ends) {
        return removeText (60, text, exact, ends);
    }
    
    public String removeText (int iter, String text, boolean exact) {
        return removeText (iter, text, exact, false);
    }
    
    public String removeText (int iter, String text, boolean exact, boolean ends) {
        for (; iter > 0; iter --) {
            synchronized (this) {
                while (array.size () > 0) {
                    String str = (String) array.remove (0);
//                    System.out.println ("Status: \"" + str + "\"");
                    if ((exact  &&  str.equals (text))  ||  (!exact  &&  ((ends  &&  str.endsWith(text))  ||  (!ends  &&  str.indexOf(text) >= 0)))) {
//                        System.out.println ("Yes");
                        return str;
                    }
                }
//                System.out.println ("No");
            }
            Helper.sleep (1000);
        }
        throw new AssertionFailedError ("TIMEOUT: Waiting for status bar text: Exact: " + exact + " Text: " + text);
    }
    
    public void finalize () {
        stop ();
    }
    
}
