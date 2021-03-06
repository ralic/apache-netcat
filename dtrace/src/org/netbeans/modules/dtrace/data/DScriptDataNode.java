/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.

 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.

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

 * Contributor(s):

 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.

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


package org.netbeans.modules.dtrace.data;

import java.io.File;
import org.netbeans.modules.dtrace.script.Script;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObject;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;

public class DScriptDataNode extends DataNode {
    
    private static final String IMAGE_ICON_BASE = "SET/PATH/TO/ICON/HERE";  //NOI18N
    
    private Script script = null;
    
    public DScriptDataNode(DataObject obj) {
        super(obj, Children.LEAF);
//        setIconBaseWithExtension(IMAGE_ICON_BASE);
   //     setValue("nodeDescription", getScript().getDescription());
        setShowFileExtensions(true);
    }
    
    /** Creates a property sheet. */
    protected Sheet createSheet() {
        Sheet s = super.createSheet();
        //Sheet.Set ss = s.get(Sheet.PROPERTIES);
        //if (ss == null) {
            //ss = Sheet.createPropertiesSet();
            //s.put(ss);
        //}
        s.remove(Sheet.PROPERTIES);
        Sheet.Set ps = new Sheet.Set();
        ps.setName(org.openide.util.NbBundle.getMessage(DScriptDataNode.class, "DTrace_Config"));
        ps.setDisplayName(org.openide.util.NbBundle.getMessage(DScriptDataNode.class, "DTrace_Config"));
        ps.setShortDescription(org.openide.util.NbBundle.getMessage(DScriptDataNode.class, "DTrace_Config"));
        s.put(ps);
        
        ps.put(new PidProperty());
        ps.put(new ScriptArgsProperty());
        ps.put(new ExecNameProperty());
        ps.put(new ExecArgsProperty());
        
        
        // TODO add some relevant properties: ss.put(...)
        return s;
    }
    
    private Script getScript() {
        if (script == null) {
            script = new Script(new File(FileUtil.getFileDisplayName(getDataObject().getPrimaryFile())));
        }
        return script;
    }
   
    private final class PidProperty extends PropertySupport.ReadWrite {
        public PidProperty() {
            super(org.openide.util.NbBundle.getMessage(DScriptDataNode.class, "PID"),
                  String.class,
                  org.openide.util.NbBundle.getMessage(DScriptDataNode.class, "PID"),
                  org.openide.util.NbBundle.getMessage(DScriptDataNode.class, "PID"));
        }
        
        public Object getValue() {
            return getScript().getPid();
        }

        public void setValue(Object value) {
            getScript().setPid((String)value);
            getScript().writeConfig();
        }
    }
    
    private final class ExecNameProperty extends PropertySupport.ReadWrite {
        public ExecNameProperty() {
            super(org.openide.util.NbBundle.getMessage(DScriptDataNode.class, "Executable_Name"),
                  String.class,
                  org.openide.util.NbBundle.getMessage(DScriptDataNode.class, "Executable_Name"),
                  org.openide.util.NbBundle.getMessage(DScriptDataNode.class, "Executable_Name"));
        }
        public Object getValue() {
            return getScript().getExecPath();
        }

        public void setValue(Object value) {
            getScript().setExecPath((String)value);
            getScript().writeConfig();
        }
    }
    
    private final class ExecArgsProperty extends PropertySupport.ReadWrite {
        public ExecArgsProperty() {
            super(org.openide.util.NbBundle.getMessage(DScriptDataNode.class, "Executable_Args"),
                  String.class,
                  org.openide.util.NbBundle.getMessage(DScriptDataNode.class, "Executable_Args"),
                  org.openide.util.NbBundle.getMessage(DScriptDataNode.class, "Executable_Args"));
        }
        
        public Object getValue() {
            return getScript().getExecArgs();
        }

        public void setValue(Object value) {
            getScript().setExecArgs((String)value);
            getScript().writeConfig();
        }
    }
    
    private final class ScriptArgsProperty extends PropertySupport.ReadWrite {
        public ScriptArgsProperty() {
            super(org.openide.util.NbBundle.getMessage(DScriptDataNode.class, "Script_Args"),
                  String.class,
                  org.openide.util.NbBundle.getMessage(DScriptDataNode.class, "Script_Args"),
                  org.openide.util.NbBundle.getMessage(DScriptDataNode.class, "Script_Args"));
        }
        
        public Object getValue() {
            return getScript().getArgs();
        }

        public void setValue(Object value) {
            getScript().setArgs((String)value);
            getScript().writeConfig();
        }
    }    
    
}
