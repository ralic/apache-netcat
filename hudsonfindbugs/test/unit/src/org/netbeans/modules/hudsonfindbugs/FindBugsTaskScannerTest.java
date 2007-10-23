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
 * Portions Copyrighted 2007 Sun Microsystems, Inc.
 */

package org.netbeans.modules.hudsonfindbugs;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.netbeans.modules.apisupport.project.NbModuleProject;
import org.netbeans.spi.tasklist.FileTaskScanner.Callback;
import org.netbeans.spi.tasklist.Task;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Jaroslav Tulach <jtulach@netbeans.org>
 */
public class FindBugsTaskScannerTest extends TestCase {

    FindBugsTaskScanner scan;
    
    public FindBugsTaskScannerTest(String testName) {
        super(testName);
    }            

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        URL url = getClass().getResource("err.xml");
        String ext = url.toExternalForm();
        int last = ext.lastIndexOf('/');
        
        url = new URL(ext.substring(0, last + 1));
        scan = new FindBugsTaskScanner(url);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testParseXML() throws Exception {
        List<Task> arr = new ArrayList<Task>();
        FileObject fo = null;
        scan.parse("err", fo, arr);
        assertEquals("One bug", 1, arr.size());
    }

}
