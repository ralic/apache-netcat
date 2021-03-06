/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
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
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
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

package org.netbeans.modules.gsf.spi;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.ImageIcon;
import org.netbeans.modules.gsf.api.CompletionProposal;
import org.netbeans.modules.gsf.api.ElementKind;
import org.netbeans.modules.gsf.api.HtmlFormatter;
import org.netbeans.modules.gsf.api.Modifier;

/**
 * Default implementation of a CompletionProposal with some useful base
 * functionality.
 *
 * @author Tor Norbye
 */
public abstract class DefaultCompletionProposal implements CompletionProposal {
    protected int anchorOffset;
    protected boolean smart;
    protected ElementKind elementKind;

    public int getSortPrioOverride() {
        return 0;
    }

    public String getName() {
        return "";
    }


    public ImageIcon getIcon() {
        return null;
    }

    public Set<Modifier> getModifiers() {
        return Collections.emptySet();
    }

    public boolean isSmart() {
        return smart;
    }

    public int getAnchorOffset() {
        return anchorOffset;
    }

    public String getInsertPrefix() {
        return getName();
    }

    public String getSortText() {
        return getName();
    }

    public ElementKind getKind() {
        return elementKind;
    }

    public String getLhsHtml(HtmlFormatter formatter) {
        ElementKind kind = getKind();
        formatter.name(kind, true);
        formatter.appendText(getName());
        formatter.name(kind, false);

        return formatter.getText();
    }

    public String getRhsHtml(HtmlFormatter formatter) {
        return null;
    }

    /**
     * Set the kind of this item. Controls what kind of icon or sorting priority
     * is assigned this item by the infrastructure.
     * 
     * @param kind The kind of completion item, such as "class" or "variable"
     */
    public void setKind(ElementKind kind) {
        this.elementKind = kind;
    }

    /** Set whether this item is "smart", e.g. should bubble to the top of
     * the completion list
     *
     * @param smart True iff item is smart
     */
    public void setSmart(boolean smart) {
        this.smart = smart;
    }

    /**
     * Set the anchor offset for this item. The anchor offset is the position
     * that, when this completion item is inserted, should have all text between
     * the caret and the anchor position removed and the completion text inserted.
     *
     * @param anchorOffset
     */
    public void setAnchorOffset(int anchorOffset) {
        this.anchorOffset = anchorOffset;
    }

    /**
     * Parameters to be inserted for this item, if any. Has no effect
     * if getCustomInsertTemplate() returns non null.
     * This will be used to implement getCustomInsertTemplate on your behalf.
     *
     * @return A list of insert parameters
     */
    public List<String> getInsertParams() {
        return null;
    }

    /** The strings to be inserted to start and end a parameter list. Should be a String of length 2.
     * In Java we would expect {(,)}, and in Ruby it's either {(,)} or { ,}.
     *
     * This will be used to implement getCustomInsertTemplate on your behalf.
     */
    public String[] getParamListDelimiters() {
        return null;
    }

    public String getCustomInsertTemplate() {
        List<String> params = getInsertParams();
        if (params == null || params.size() == 0) {
            return getInsertPrefix();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getInsertPrefix());
        String[] delimiters = getParamListDelimiters();
        assert delimiters.length == 2;
        sb.append(delimiters[0]);
        int id = 1;
        for (Iterator<String> it = params.iterator(); it.hasNext();) {
            String paramDesc = it.next();
            sb.append("${"); //NOI18N
            // Ensure that we don't use one of the "known" logical parameters
            // such that a parameter like "path" gets replaced with the source file
            // path!
            sb.append("gsf-cc-"); // NOI18N
            sb.append(Integer.toString(id++));
            sb.append(" default=\""); // NOI18N
            sb.append(paramDesc);
            sb.append("\""); // NOI18N
            sb.append("}"); //NOI18N
            if (it.hasNext()) {
                sb.append(", "); //NOI18N
            }
        }
        sb.append(delimiters[1]);
        sb.append("${cursor}"); // NOI18N

        return sb.toString();
    }
}
