/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 2008 Sun Microsystems, Inc. All rights reserved.
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
 * 
 * Contributor(s):
 * 
 * Portions Copyrighted 2008 Sun Microsystems, Inc.
 */
package org.netbeans.modules.scala.editing.nodes;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.netbeans.modules.gsf.api.ElementKind;
import org.netbeans.modules.gsf.api.HtmlFormatter;
import org.netbeans.modules.gsf.api.OffsetRange;

/**
 *
 * @author Caoyuan Deng
 */
public class Function extends AstDef {

    private List<TypeRef> typeParams;
    private List<Var> params;

    public Function(String name, OffsetRange nameRange, AstScope bindingScope, ElementKind kind) {
        super(name, nameRange, bindingScope, kind);
    }

    public void setTypeParam(List<TypeRef> typeParams) {
        this.typeParams = typeParams;
    }

    public List<TypeRef> getTypeParam() {
        return typeParams == null ? Collections.<TypeRef>emptyList() : typeParams;
    }

    public void setParam(List<Var> params) {
        this.params = params;
    }

    public List<Var> getParams() {
        return params == null ? Collections.<Var>emptyList() : params;
    }

    @Override
    public boolean referredBy(AstRef ref) {
        if (ref instanceof FunRef) {
            return getName().equals(ref.getName()) && params.size() == ((FunRef) ref).getParams().size();
        }
        
        return false;
    }

    @Override
    public void htmlFormat(HtmlFormatter formatter) {
        super.htmlFormat(formatter);
        if (getTypeParam().size() > 0) {
            formatter.appendHtml("[");

            for (Iterator<TypeRef> itr = getTypeParam().iterator(); itr.hasNext();) {
                TypeRef typeParam = itr.next();
                typeParam.htmlFormat(formatter);

                if (itr.hasNext()) {
                    formatter.appendHtml(", ");
                }
            }

            formatter.appendHtml("]");
        }

        formatter.appendHtml("(");
        if (getParams().size() > 0) {
            formatter.parameters(true);

            for (Iterator<Var> itr = getParams().iterator(); itr.hasNext();) {
                Var param = itr.next();
                param.htmlFormat(formatter);

                if (itr.hasNext()) {
                    formatter.appendHtml(", ");
                }
            }

            formatter.parameters(false);
        }
        formatter.appendHtml(")");

        if (getType() != null) {
            formatter.appendHtml(" :");
            getType().htmlFormat(formatter);
        }
    }
}