/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2005 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.adnode;

import java.awt.Component;
import java.awt.Image;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.util.Enumeration;
import javax.swing.Action;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.adaptable.Adaptable;
import org.netbeans.spi.adnode.Name;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.actions.SystemAction;
import org.openide.util.datatransfer.NewType;
import org.openide.util.datatransfer.PasteType;

/**
 *
 * @author Jaroslav Tulach
 */
final class ANode extends org.openide.nodes.Node 
implements ChangeListener {
    private Adaptable a;
    
    /** Creates a new instance of ANode */
    public ANode(Adaptable a) {
        super(Children.LEAF);
        
        this.a = a;
        a.addChangeListener(this);
    }

    public String getName() {
        Name n = a.lookup(Name.class);
        return n == null ? "" : n.getName();
    }

    public void stateChanged(ChangeEvent e) {
        fireNameChange(null, null);
    }

    public Node cloneNode() {
        return new ANode(a);
    }

    public Image getIcon(int type) {
        throw new UnsupportedOperationException();
    }

    public Image getOpenedIcon(int type) {
        throw new UnsupportedOperationException();
    }

    public HelpCtx getHelpCtx() {
        throw new UnsupportedOperationException();
    }

    public boolean canRename() {
        throw new UnsupportedOperationException();
    }

    public boolean canDestroy() {
        throw new UnsupportedOperationException();
    }

    public Node.PropertySet[] getPropertySets() {
        throw new UnsupportedOperationException();
    }

    public Transferable clipboardCopy() throws IOException {
        throw new UnsupportedOperationException();
    }

    public Transferable clipboardCut() throws IOException {
        throw new UnsupportedOperationException();
    }

    public Transferable drag() throws IOException {
        throw new UnsupportedOperationException();
    }

    public boolean canCopy() {
        throw new UnsupportedOperationException();
    }

    public boolean canCut() {
        throw new UnsupportedOperationException();
    }

    public PasteType[] getPasteTypes(Transferable t) {
        throw new UnsupportedOperationException();
    }

    public PasteType getDropType(Transferable t, int action, int index) {
        throw new UnsupportedOperationException();
    }

    public NewType[] getNewTypes() {
        throw new UnsupportedOperationException();
    }

    public boolean hasCustomizer() {
        throw new UnsupportedOperationException();
    }

    public Component getCustomizer() {
        throw new UnsupportedOperationException();
    }

    public Node.Handle getHandle() {
        throw new UnsupportedOperationException();
    }

    public void setDisplayName(String s) {
        throw new UnsupportedOperationException();
    }

    public void setName(String s) {
        throw new UnsupportedOperationException();
    }

    public void setShortDescription(String s) {
        throw new UnsupportedOperationException();
    }

    public Node.Cookie getCookie(Class type) {
        throw new UnsupportedOperationException();
    }

    public Action[] getActions(boolean context) {
        throw new UnsupportedOperationException();
    }

    public String getDisplayName() {
        throw new UnsupportedOperationException();
    }

    public String getShortDescription() {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return super.toString() + " for " + a;
    }

    public void destroy() throws IOException {
        throw new UnsupportedOperationException();
    }

    public String getHtmlDisplayName() {
        throw new UnsupportedOperationException();
    }

    public Action getPreferredAction() {
        throw new UnsupportedOperationException();
    }

    public Object getValue(String attributeName) {
        throw new UnsupportedOperationException();
    }

    public void setValue(String attributeName, Object value) {
        throw new UnsupportedOperationException();
    }

    public void setPreferred(boolean preferred) {
        throw new UnsupportedOperationException();
    }

    public void setHidden(boolean hidden) {
        throw new UnsupportedOperationException();
    }

    public void setExpert(boolean expert) {
        throw new UnsupportedOperationException();
    }

    public boolean isPreferred() {
        throw new UnsupportedOperationException();
    }

    public boolean isHidden() {
        throw new UnsupportedOperationException();
    }

    public boolean isExpert() {
        throw new UnsupportedOperationException();
    }

    public Enumeration<String> attributeNames() {
        throw new UnsupportedOperationException();
    }
}
