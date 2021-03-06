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


package org.netbeans.modules.assistant;

import org.openide.windows.*;
import org.openide.text.EditorSupport.Editor;
import org.openide.nodes.*;
import org.openide.util.actions.CallableSystemAction;
import org.openide.util.SharedClassObject;

import org.netbeans.modules.assistant.event.*;
import java.beans.*;
import java.util.*;
import java.io.*;
import javax.swing.event.*;
import java.net.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;



/*
 * DefaultAssistantModel.java
 *
 * Created on October 11, 2002, 1:52 PM
 *
 * @author  Richard Gregor
 */
public class DefaultAssistantModel implements AssistantModel, PropertyChangeListener{
    private Vector sections = null; 
    protected EventListenerList listenerList = new EventListenerList();
    private static AssistantModel model;
    private AssistantContentViewer contentViewer;
    private AssistantID currentID;
    private TopComponent oldComp = null;
    
    /*
     * Registry contains all registered IDs - assistant contents
     * Key is ID name, value is AssistantID
     *
     */
    private HashSet registry;
    
    public DefaultAssistantModel(){
        this(null);
    } 
    
    public DefaultAssistantModel(AssistantContext ctx){
        registry = new HashSet();
        if(ctx != null){
            registry.add(ctx);
        }
        //need to change !!! 
        //use AssistantModelListener on contentViewer instead
        contentViewer = AssistantContentViewer.createComp();
        TopComponent.getRegistry().addPropertyChangeListener(this);
    }
    
    public static AssistantModel getModel(){
        if(model == null){
            model = new DefaultAssistantModel();
        }
        return model;
    }
    
    /**
     * Sets the current ID
     * AssistantModelListeners are notified. This should cause change of assistant content.
     *
     * @param id the ID used to set
     */
    public void setCurrentID(AssistantID id){
        this.currentID = id;
        fireIDChanged(this,id);
    }
    /**
     * Sets the current ID
     * AssistantModelListeners are notified. This should cause change of assistant content.
     *
     * @param id the ID used to set
     * @return True if ID exists
     */
    public synchronized boolean setCurrentID(String idName){        
        if(idName.equals("assistant"))
            return false;
        for(Iterator it = registry.iterator();it.hasNext();){
            AssistantContext ctx = (AssistantContext)it.next();
            for(Enumeration en = ctx.getIDs();en.hasMoreElements();){
                AssistantID id = (AssistantID)en.nextElement();
                if(id.getName().equals(idName)){
                    setCurrentID(id);                    
                    return true;
                }
            }
        }
        return false;
    }     
    
    /**
     * Gets the current ID
     */
    public AssistantID getCurrentID(){
        return currentID;
    }
    
    /**
     * Sets the current URL
     * AssistantModelListeners are notified
     *
     * @param url the URL used to set
     */
    public void setCurrentURL(URL url){
        debug("set url: "+url);
        //fireURLChanged(this, url); //need rethink whether to use events or not   
        contentViewer.open();
        contentViewer.requestFocus();
        contentViewer.setPage(url);
    }
    
    /**
     * Gets the current URL
     */
    public URL getCurrentURL(){
        return null;
    }        
    
    /**
     * Registry the assistant's items with this model
     *
     *@param xmlFile The xml file definig assistant structure according to assistant-1_0.dtd 
     */
    public void registry(File xmlFile){
    }
    /**
     * Adds a listener for the AssistantModelEvent posted after the model has
     * changed.
     * 
     * @param l The listener to add.
     * @see org.netbeans.modules.AssistantModel#removeAssistantModelListener
     */
    public void addAssistantModelListener(AssistantModelListener l){        
	listenerList.add(AssistantModelListener.class, l);    
    }

    /**
     * Removes a listener previously added with <tt>addAssistantModelListener</tt>
     *
     * @param l The listener to remove.
     * @see org.netbeans.modules.AssistantModel#addAssistantModelListener
     */
    public void removeAssistantModelListener(AssistantModelListener l){
        listenerList.remove(AssistantModelListener.class, l);
    }

    /**
     * Adds a listener to monitor changes to the properties in this model
     *
     * @param l  The listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener l){
    }

    /**
     * Removes a listener monitoring changes to the properties in this model
     *
     * @param l  The listener to remove.
     */
    public void removePropertyChangeListener(PropertyChangeListener l){
    }
    
    protected void fireURLChanged(Object source, URL url) {
	Object[] listeners = listenerList.getListenerList();
	AssistantModelEvent e = null;

	for (int i = listeners.length - 2; i >= 0; i -= 2) {
	    if (listeners[i] == AssistantModelListener.class) {
		if (e == null) {
		    e = new AssistantModelEvent(source, null, url);
		}		
		((AssistantModelListener)listeners[i+1]).urlChanged(e);
	    }	       
	}
    }
    
    protected void fireIDChanged(Object source, AssistantID id) {
	Object[] listeners = listenerList.getListenerList();
	AssistantModelEvent e = null;

	for (int i = listeners.length - 2; i >= 0; i -= 2) {
	    if (listeners[i] == AssistantModelListener.class) {
		if (e == null) {
		    e = new AssistantModelEvent(source, id, null);
		}		
		((AssistantModelListener)listeners[i+1]).idChanged(e);
	    }	       
	}
    }
    
    /** This method gets called when a bound property is changed.
     * @param evt A PropertyChangeEvent object describing the event source
     *   	and the property that has changed.
     *
     */
    public void propertyChange(PropertyChangeEvent evt) {
        TopComponent.Registry registry = (TopComponent.Registry)evt.getSource();
        TopComponent comp = registry.getActivated();
        if(comp == null)
            return;
        if(comp == oldComp){
            if(comp instanceof Editor){
                Node[] nodes = comp.getActivatedNodes();
                for(int i = 0; i < nodes.length; i++){
                    Node.Cookie cookie = nodes[i].getCookie(org.openide.cookies.EditorCookie.class);
                    JEditorPane[] pane = ((org.openide.cookies.EditorCookie)cookie).getOpenedPanes();
                    for(int j = 0; j < pane.length; j++){
                        //do this only once per component and don't forget to remove listeners
                        //when comp.is closed
                        //if there is the same keyword - don't do anything
                        //decide if there was comp change or keyword change in the same comp
                        pane[j].addCaretListener(new ModelKeywordLListener(pane[j]));
                    }
                }
                Editor editor = (Editor)comp;
                debug("editor support");
                return;
            }
        }else{
            oldComp = comp;
            String helpID = comp.getHelpCtx().getHelpID();
            if (setCurrentID(comp.getHelpCtx().getHelpID()+"_"+comp.getName()));
            else setCurrentID(helpID);
            
            debug("id: "+comp.getHelpCtx().getHelpID());
            debug("name: "+comp.getName());
            //setCurrentID(comp.getHelpCtx().getHelpID());
        }
    }
  
    private class ModelKeywordLListener implements CaretListener{
        private JTextComponent edit;
        
        public ModelKeywordLListener(JTextComponent edit){
            this.edit = edit;
        }
            
        public void caretUpdate(CaretEvent ce){
            int offset = ce.getDot();
            String word;
            try{
                word = org.netbeans.editor.Utilities.getWord(edit, offset);
            }catch(javax.swing.text.BadLocationException e){
                return;
            }
            if(org.netbeans.editor.ext.java.JavaTokenContext.getKeyword(word) != null){
                //it is keyword
                debug("keyword: "+word);
                setCurrentID("keyword_"+word.trim());
            }            
        }        
    }
        
    /** 
     * Performs the action
     *
     */
    public void performAction(String action) {
        debug("perform action:"+action);
        try{
            Class clazz = Class.forName(action);
            ((CallableSystemAction) SharedClassObject.findObject(clazz, true)).performAction();
        }catch(ClassNotFoundException e){
            //do nothing
        }
    }
    
    private static final boolean debug = false;
    private static void debug(String msg) {
	if (debug) {
	    System.err.println("DefaultAssistantModel: "+msg);
	}
    }
}
