/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2001 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.corba.browser.ir.nodes;

import org.omg.CORBA.*;

import java.io.PrintWriter;
import java.io.IOException;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import org.openide.nodes.*;
import org.openide.TopManager;
import org.openide.util.actions.SystemAction;
import org.openide.actions.OpenAction;
import org.openide.util.datatransfer.ExClipboard;
import org.netbeans.modules.corba.browser.ir.Util;
import org.netbeans.modules.corba.browser.ir.util.Generatable;
import org.netbeans.modules.corba.browser.ir.util.GenerateSupport;
import org.netbeans.modules.corba.browser.ir.util.GenerateSupportFactory;


/**
 * Class ConstantDefNode
 *
 */
public class IRConstantDefNode extends IRLeafNode implements Node.Cookie, Generatable {

    ConstantDef _constant;
  

    private static final String CONSTANT_ICON_BASE =
        "org/netbeans/modules/corba/idl/node/const";
  
  
    private static class ConstantCodeGenerator implements GenerateSupport {
        private ConstantDef _constant;
    
        public ConstantCodeGenerator ( ConstantDef constant){
            this._constant = constant;
        }
    
        public String generateHead (int indent){
            return "";
        }
    
        public String generateSelf (int indent){
            String code ="";
            for (int i=0; i<indent; i++)
                code = code + SPACE;
            code = code + "const ";
            code = code + Util.typeCode2TypeString ( _constant.value().type())+ " ";
            code = code + _constant.name() + " = ";
            code = code + ConstantCodeGenerator.getValue(this._constant) + ";\n\n";
            return code;
        }
    
        public String generateTail (int indent){
            return "";
        }
    
        /** Returns the value of constant as String
         *  @return String value
         */
        public static String getValue(ConstantDef constant) {
            Any value = constant.value();
            switch (value.type().kind().value()){
            case TCKind._tk_boolean:
                return new Boolean (value.extract_boolean()).toString();
            case TCKind._tk_char:
                return new Character(value.extract_char()).toString();
            case TCKind._tk_wchar:
                return new Character(value.extract_char()).toString();
            case TCKind._tk_string:
                return value.extract_string();
            case TCKind._tk_wstring:
                return value.extract_wstring();
            case TCKind._tk_float:
                return new Float(value.extract_float()).toString();
            case TCKind._tk_double:
                return new Double(value.extract_double()).toString();
            case TCKind._tk_longdouble:
                return new Double(value.extract_double()).toString();
            case TCKind._tk_fixed:
                return value.extract_fixed().toString();
            case TCKind._tk_short:
                return new Short(value.extract_short()).toString();
            case TCKind._tk_long:
                return new Integer(value.extract_long()).toString();
            case TCKind._tk_longlong:
                return new Long (value.extract_longlong()).toString();
            case TCKind._tk_ushort:
                return new Short(value.extract_short()).toString();
            case TCKind._tk_ulong:
                return new Integer(value.extract_long()).toString();
            case TCKind._tk_ulonglong:
                return new Long (value.extract_longlong()).toString();
            default:
                return "";
            }
        }
  
    }
  

    public IRConstantDefNode(Contained value) {
        super ();
        _constant = ConstantDefHelper.narrow (value);
        this.getCookieSet().add(this);
        setIconBase (CONSTANT_ICON_BASE);
    }

    public String getDisplayName () {
        if (_constant != null)
            return _constant.name ();
        else 
            return "";
    }

    public String getName () {
        return _constant.name ();
    }
  
    public SystemAction getDefaultAction () {
        SystemAction result = super.getDefaultAction();
        return result == null ? SystemAction.get(OpenAction.class) : result;
    }

    protected Sheet createSheet () {
        Sheet s = Sheet.createDefault ();
        Sheet.Set ss = s.get (Sheet.PROPERTIES);
        ss.put (new PropertySupport.ReadOnly (Util.getLocalizedString("TITLE_Name"), String.class, Util.getLocalizedString("TITLE_Name"), Util.getLocalizedString("TIP_ConstName")) {
                public java.lang.Object getValue () {
                    return _constant.name ();
                }
            });
        ss.put (new PropertySupport.ReadOnly (Util.getLocalizedString("TITLE_Id"), String.class, Util.getLocalizedString("TITLE_Id"), Util.getLocalizedString("TIP_ConstId")) {
                public java.lang.Object getValue () {
                    return _constant.id ();
                }
            });
        ss.put (new PropertySupport.ReadOnly (Util.getLocalizedString("TITLE_Version"), String.class, Util.getLocalizedString("TITLE_Version"), 
                                              Util.getLocalizedString("TIP_ConstVersion")) {
                public java.lang.Object getValue () {
                    return _constant.version ();
                }
            });
        ss.put (new PropertySupport.ReadOnly (Util.getLocalizedString("TITLE_Type"), String.class,Util.getLocalizedString("TITLE_Type"), 
                                              Util.getLocalizedString("TIP_ConstType")) {
                public java.lang.Object getValue () {
                    return Util.typeCode2TypeString (_constant.type ());
                }
            });
    
        ss.put ( new PropertySupport.ReadOnly (Util.getLocalizedString("TITLE_Value"),String.class,Util.getLocalizedString("TITLE_Value"),Util.getLocalizedString("TIP_ConstValue")){
                public java.lang.Object getValue(){
                    return ConstantCodeGenerator.getValue(_constant);
                }
            });
    
        return s;
    }
  
    public String getRepositoryId () {
        return this._constant.id();
    }
  
    public GenerateSupport createGenerator () {
        if (this.generator == null)
            this.generator = new ConstantCodeGenerator (_constant);
        return this.generator;
    }
  
    public static GenerateSupport createGeneratorFor (Contained type){
        ConstantDef constant = ConstantDefHelper.narrow ( type);
        if ( type == null )
            return null;
        return new ConstantCodeGenerator (constant);
    }

    public void generateCode() {
     
        ExClipboard clipboard = TopManager.getDefault().getClipboard();
        StringSelection genCode = new StringSelection ( this.generateHierarchy ());
        clipboard.setContents(genCode,genCode);
    }

    public void generateCode (PrintWriter out) throws IOException {
        out.println ( this.generateHierarchy ());
    }

    private String generateHierarchy () {
        Node node = this.getParentNode();
        String code ="";
        // Generate the start of namespace
        ArrayList stack = new ArrayList();
        while ( node instanceof IRContainerNode){
            stack.add(((GenerateSupportFactory)node).createGenerator());
            node = node.getParentNode();
        }
        int size = stack.size();
        for (int i = size -1 ; i>=0; i--)
            code = code + ((GenerateSupport)stack.get(i)).generateHead((size -i -1));
        // Generate element itself
        code = code + this.createGenerator().generateSelf(size);
        //Generate tail of namespace
        for (int i = 0; i< stack.size(); i++)
            code = code + ((GenerateSupport)stack.get(i)).generateTail((size -i));
        return code;
    }
}

/*
 * $Log
 * $
 */
