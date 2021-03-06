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

package org.netbeans.modules.a11y;

import java.awt.Component;
import java.awt.Point;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import javax.accessibility.AccessibleContext;

import org.netbeans.a11y.AccessibilityTester;
import org.netbeans.a11y.TestSettings;

import org.netbeans.modules.form.ComponentInspector;
import org.netbeans.modules.form.RADComponent;
import org.netbeans.modules.form.VisualReplicator;

import org.openide.nodes.Node;

import org.openide.windows.OutputEvent;
import org.openide.windows.OutputListener;
import org.openide.windows.OutputWriter;


/**
 *  A report generator for AccessibilityTester that will show the variable name
 *  of components with accessbility problems.
 *
 *  @author Tristan Bonsall
 */
public class NetBeansReport extends AccessibilityTester.ReportGenerator{
    
    /**
     *  Create a NetBeansReport for an AccessibilityTester.
     *
     *  @param tester the AccesibilityTester
     */
    public NetBeansReport(AccessibilityTester tester, VisualReplicator replicator, TestSettings set){
        super(tester, set);
        this.replicator = replicator;
    }
    
    /**
     *  Generate the text report from the tests.
     *  <p>
     *  Reports are sent to a Writer, for instance:
     *  <ul>
     *  <li><code>new java.io.Writer(System.out)</code> will write to std out
     *  <li><code>new java.io.Writer(new java.io.FileWriter(new java.io.File("result.xml")))</code> will write to the file result.txt
     *  </ul>
     *
     *  @param out a Writer to send the report to
     */
    public void getReport(Writer writer){
        PrintWriter out = getPrintWriter(writer);
        
        out.println("Results of Accessibility test");
        out.println();
        
        out.println("\n Doesn't implement Accessible :");
        printComponents(getNoAccess(),out, testSettings.accessibleInterface);
        
        out.println("\n No Accessible name :");
        printComponents(getNoName(),out, testSettings.AP_accessibleName);
        
        out.println("\n No Accessible description :");
        printComponents(getNoDesc(),out, testSettings.AP_accessibleDescription);
        
        out.println("\n Label with LABEL_FOR not set :");
        printComponents(getNoLabelFor(),out, testSettings.AP_labelForSet);
        
        out.println("\n Components with no LABEL_FOR pointing to it :");
        printComponents(getNoLabelForPointing(),out, testSettings.AP_noLabelFor);
        
        out.println("\n Components with no mnemonic :");
        printComponents(getNoMnemonic(),out, testSettings.AP_mnemonics);
        
        out.println("\n Components not reachable with tab traversal :");
        printComponents(getNotTraversable(),out, testSettings.tabTraversal);
        
        out.flush();
        
        /* Commented out because closing the writer for OutputWindow in NetBeans */
        /* erases the contents of the window. Uncomment in future if this changes */
        //out.close();
    }
    
    /**
     *  Output the details of a component to the writer in text.
     */
    private void printComponentDetails(PrintWriter out, Component comp){
        StringBuffer componentPrintString = new StringBuffer("");
        
        RADComponent metacomp = replicator.getTopMetaComponent();
        if (metacomp != null){
            String classname = comp.getClass().toString();
            
            if (classname.startsWith("class ")){
                classname = classname.substring(6);
            }
            componentPrintString.append("   Class: " + classname);
            
            if(printName || printDescription){
                componentPrintString.append(" { ");
                AccessibleContext ac = comp.getAccessibleContext();
                
                if (ac != null){
                    String name = ac.getAccessibleName();
                    if ((name != null) && printName){
                        componentPrintString.append(" " + name);
                    }
                    
                    componentPrintString.append(" | ");
                    
                    String desc = ac.getAccessibleDescription();
                    if ((desc != null) && printDescription){
                        componentPrintString.append(" " + desc);
                    }
                }
                componentPrintString.append(" } ");
            }
            
            if(printPosition) {
                try{
                    Point top = getTestTarget().getLocationOnScreen();
                    Point child = comp.getLocationOnScreen();
                    componentPrintString.append(" [" + (child.x - top.x) + "," + (child.y - top.y) + "]");
                } catch(Exception e){}
            }
            
            if (out instanceof OutputWriter){
                listener.addComponent(metacomp.getName(), metacomp);
                try{
                    ((OutputWriter)out).println(componentPrintString.toString(), listener);
                } catch(IOException e){
                    out.println(componentPrintString);
                }
            } else{
                out.println(classname + " " + metacomp.getName());
            }
        }
    }
    
    
    private void printComponents(HashSet components, PrintWriter pw, boolean tested) {
        if(!tested){
            pw.println("   - not tested.");
        } else {
            if (components.size() > 0){
                LinkedList componentsString = new LinkedList();
                
                Iterator i = components.iterator();
                while(i.hasNext()){
                    Component comp = (Component)(i.next());
                    printComponentDetails(pw, comp);
                }
                
                Collections.sort(componentsString);
                
                Iterator is = componentsString.iterator();
                while(is.hasNext()){
                    pw.println(is.next());
                }
                
                pw.println();
            }else{
                pw.println("   - none.");
            }
        }
    }
    
    /**
     *  Implementation of OutputListener to allows selection of lines that represent
     *  components with problems to focus the actual component in the form designer.
     */
    private class ReportListener implements OutputListener{
        
        /**
         *  Add a mapping from the components variable name to the RADComponent.
         *
         *  @param key the variable name
         *  @param comp the RADComponent
         */
        public void addComponent(String key, RADComponent comp){
            if (components.get(key) == null){
                components.put(key, comp);
            }
        }
        
        /**
         *  Focus the component when selected.
         */
        public void outputLineSelected(OutputEvent ev){
            
            String text = ev.getLine();
            String name = text.substring(text.lastIndexOf(' ') + 1);
            RADComponent comp = (RADComponent)(components.get(name));
            if (comp != null){
                Node node = comp.getNodeReference();
                if (node != null){
                    try{
                        ComponentInspector.getInstance().getExplorerManager().setSelectedNodes(new Node[] {node});
                    } catch (Exception e){}
                }
            }
        }
        
        /**
         *  Not implemented.
         */
        public void outputLineAction(OutputEvent ev){}
        
        /**
         *  Not implemented.
         */
        public void outputLineCleared(OutputEvent ev){}
        
        private HashMap components = new HashMap();
    }
    
    private VisualReplicator replicator = null;
    private ReportListener listener = new ReportListener();
}