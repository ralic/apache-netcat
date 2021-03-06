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

package org.netbeans.modules.gsf.api;

import java.util.Random;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import junit.framework.TestCase;

/**
 *
 * @author Tor Norbye
 */
public class EditHistoryTest extends TestCase {
    public EditHistoryTest(String testName) {
        super(testName);
    }            

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private Document getDocument(String s) throws Exception {
        Document doc = new PlainDocument();
        doc.insertString(0, s, null);
        return doc;
    }

    private void validateHistory(String original, String modified, EditHistory history) throws Exception {
        // Check position mapping
// This won't work in the presence of removes! There will be positions lost that maps back to the
// boundaries of the deleted block rather than the interior        
//        for (int i = 0; i < original.length(); i++) {
//            int newPos = history.convertOldToNew(i);
//            int oldPos = history.convertNewToOld(newPos);
//            assertEquals("Incorrect position mapping for " + modified.charAt(i), i, oldPos);
//        }

        // Ensure that the head and tail of the document is identical to the beginning
        String head = original.substring(0, history.getStart());
        assertEquals("Wrong head; ", head, modified.substring(0, history.getStart()));
        String tail = original.substring(history.getStart()+history.getOriginalSize());
        assertEquals("Wrong tail; ", tail, modified.substring(history.getStart()+history.getEditedSize()));
    }

    private void insert(Document document, EditHistory history, int offset, String string) throws Exception {
        try {
            document.addDocumentListener(history);
            document.insertString(offset, string, null);
        } finally {
            document.removeDocumentListener(history);
        }
    }

    private void remove(Document document, EditHistory history, int offset, int length) throws Exception {
        try {
            document.addDocumentListener(history);
            document.remove(offset, length);
        } finally {
            document.removeDocumentListener(history);
        }
    }

    public void testInserts1() throws Exception {
        EditHistory history = new EditHistory();
        String original = "   HelloWorld";
        Document doc = getDocument(original);
        //012345678901234567890
        //   HelloWorld
        //   He__lloWorld
        insert(doc, history, 5, "__");
        String modified = doc.getText(0, doc.getLength());
        assertEquals("   He__lloWorld", modified);
        validateHistory(original, modified, history);
        assertEquals(5, history.getStart());
        assertEquals(0, history.getOriginalSize());
        assertEquals(2, history.getEditedSize());
    }

    public void testRemoves1() throws Exception {
        EditHistory history = new EditHistory();
        String original = "   HelloWorld";
        Document doc = getDocument(original);
        //012345678901234567890
        //   HelloWorld
        //   HeoWorld
        remove(doc, history, 5, 2);
        String modified = doc.getText(0, doc.getLength());
        assertEquals("   HeoWorld", modified);
        validateHistory(original, modified, history);
        assertEquals(5, history.getStart());
        assertEquals(2, history.getOriginalSize());
        assertEquals(0, history.getEditedSize());
    }

    public void testMultipleRemoves1() throws Exception {
        EditHistory history = new EditHistory();
        String original = "   HelloWorld";
        Document doc = getDocument(original);
        //012345678901234567890
        //   HelloWorld
        //   HeoWorld
        remove(doc, history, 5, 2);
        //012345678901234567890
        //   HelloWorld
        //   HeoWld
        remove(doc, history, 7, 2);
        String modified = doc.getText(0, doc.getLength());
        assertEquals("   HeoWld", modified);
        assertEquals(5, history.getStart());
        assertEquals(11, history.getOriginalEnd());
        assertEquals(7, history.getEditedEnd());
        assertEquals(6, history.getOriginalSize());
        assertEquals(2, history.getEditedSize());
        assertEquals(-4, history.getSizeDelta());
        validateHistory(original, modified, history);
    }

    public void testMultipleRemoves2() throws Exception {
        EditHistory history = new EditHistory();
        String original = "   HelloWorld";
        Document doc = getDocument(original);
        //012345678901234567890
        //   HelloWorld
        //   HelloWod
        remove(doc, history, 10, 2);
        //012345678901234567890
        //   HelloWod
        //   HeoWod
        remove(doc, history, 5, 2);
        String modified = doc.getText(0, doc.getLength());
        assertEquals("   HeoWod", modified);
        assertEquals(5, history.getStart());
        assertEquals(12, history.getOriginalEnd());
        assertEquals(8, history.getEditedEnd());
        assertEquals(7, history.getOriginalSize());
        assertEquals(3, history.getEditedSize());
        assertEquals(-4, history.getSizeDelta());
        validateHistory(original, modified, history);
    }

    public void testMultipleRemoves3() throws Exception {
        EditHistory history = new EditHistory();
        String original = "   HelloWorld";
        Document doc = getDocument(original);
        //012345678901234567890
        //   HelloWorld
        //   HelloWod
        remove(doc, history, 10, 2);
        //012345678901234567890
        //   HelloWod
        //   HeoWod
        remove(doc, history, 5, 2);
        //012345678901234567890
        //   HeoWod
        //   Heood
        remove(doc, history, 6, 1);
        String modified = doc.getText(0, doc.getLength());
        assertEquals("   Heood", modified);
        assertEquals(5, history.getStart());
        assertEquals(12, history.getOriginalEnd());
        assertEquals(7, history.getEditedEnd());
        assertEquals(7, history.getOriginalSize());
        assertEquals(2, history.getEditedSize());
        assertEquals(-5, history.getSizeDelta());
        validateHistory(original, modified, history);
    }

    public void testMultipleInserts1() throws Exception {
        EditHistory history = new EditHistory();
        String original = "   HelloWorld";
        Document doc = getDocument(original);
        //012345678901234567890
        //   HelloWorld
        //   He__lloWorld
        insert(doc, history, 5, "__");
        //012345678901234567890
        //   He__lloWorld
        //   He__llo__World
        insert(doc, history, 10, "__");

        String modified = doc.getText(0, doc.getLength());
        assertEquals("   He__llo__World", modified);
        assertEquals(5, history.getStart());
        assertEquals(8, history.getOriginalEnd());
        assertEquals(12, history.getEditedEnd());
        assertEquals(3, history.getOriginalSize());
        assertEquals(7, history.getEditedSize());
        assertEquals(4, history.getSizeDelta());
        validateHistory(original, modified, history);
    }

    public void testMultipleInserts2() throws Exception {
        EditHistory history = new EditHistory();
        String original = "   HelloWorld";
        Document doc = getDocument(original);
        //012345678901234567890
        //   HelloWorld
        //   HelloWo__rld
        insert(doc, history, 10, "__");
        //012345678901234567890
        //   HelloWo__rld
        //   He__lloWo__rld
        insert(doc, history, 5, "__");

        String modified = doc.getText(0, doc.getLength());
        assertEquals("   He__lloWo__rld", modified);
        assertEquals(5, history.getStart());
        assertEquals(10, history.getOriginalEnd());
        assertEquals(14, history.getEditedEnd());
        assertEquals(5, history.getOriginalSize());
        assertEquals(9, history.getEditedSize());
        assertEquals(4, history.getSizeDelta());
        validateHistory(original, modified, history);
    }

    public void testMultipleInserts3() throws Exception {
        EditHistory history = new EditHistory();
        String original = "   HelloWorld";
        Document doc = getDocument(original);
        //012345678901234567890
        //   HelloWorld
        //   HelloWo__rld
        insert(doc, history, 10, "__");
        //012345678901234567890
        //   HelloWo__rld
        //   He__lloWo__rld
        insert(doc, history, 5, "__");
        //012345678901234567890
        //   He__lloWo__rld
        //   He__ll__oWo__rld
        insert(doc, history, 9, "__");

        String modified = doc.getText(0, doc.getLength());
        assertEquals("   He__ll__oWo__rld", modified);
        assertEquals(5, history.getStart());
        assertEquals(10, history.getOriginalEnd());
        assertEquals(16, history.getEditedEnd());
        assertEquals(5, history.getOriginalSize());
        assertEquals(11, history.getEditedSize());
        assertEquals(6, history.getSizeDelta());
        validateHistory(original, modified, history);
    }

    public void testMixed2() throws Exception {
        EditHistory history = new EditHistory();
        String original = "   HelloWorld";
        Document doc = getDocument(original);
        //012345678901234567890
        //   HelloWorld
        //   He__lloWorld
        insert(doc, history, 5, "__");
        //012345678901234567890
        //   He__lloWorld
        //   He__llorld
        remove(doc, history, 10, 2);

        String modified = doc.getText(0, doc.getLength());
        assertEquals("   He__llorld", modified);
        assertEquals(5, history.getStart());
        assertEquals(10, history.getOriginalEnd());
        assertEquals(10, history.getEditedEnd());
        assertEquals(5, history.getOriginalSize());
        assertEquals(5, history.getEditedSize());
        assertEquals(0, history.getSizeDelta());
        validateHistory(original, modified, history);
    }

    public void testMixed3() throws Exception {
        EditHistory history = new EditHistory();
        String original = "   HelloWorld";
        Document doc = getDocument(original);
        //012345678901234567890
        //   HelloWorld
        //   He__lloWorld
        insert(doc, history, 5, "__");
        //012345678901234567890
        //   He__lloWorld
        //   He__llo__World
        insert(doc, history, 10, "__");
        //012345678901234567890
        //   He__llo__World
        //   He__l__World
        remove(doc, history, 8, 2);

        String modified = doc.getText(0, doc.getLength());
        assertEquals("   He__l__World", modified);
        assertEquals(5, history.getStart());
        assertEquals(8, history.getOriginalEnd());
        assertEquals(10, history.getEditedEnd());
        assertEquals(3, history.getOriginalSize());
        assertEquals(5, history.getEditedSize());
        assertEquals(2, history.getSizeDelta());
        validateHistory(original, modified, history);
    }

    public void testRandom() throws Exception {
        // Try lots of edits and make sure the edit history at the end is valid
        String PREFIX = "DONTTOUCHSTART";
        String SUFFIX = "DONTTOUCHEND";
        String original = PREFIX +
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890" +
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890" +
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890" +
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890" +
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890" +
            SUFFIX;

        Document doc = getDocument(original);
        EditHistory history = new EditHistory();
        Random random = new Random(500);
        for (int i = 0; i < 100; i++) {
            boolean insert = random.nextBoolean();
            int docLength = doc.getLength()-PREFIX.length()-SUFFIX.length()-1;

            int offset = (int)(random.nextDouble()*docLength)+PREFIX.length();
            if (insert) {
                insert(doc, history, offset, "_");
            } else {
                remove(doc, history, offset, 1);
            }
        }
        String modified = doc.getText(0, doc.getLength());
        validateHistory(original, modified, history);
    }

    public void testCombined1() throws Exception {
        EditHistory firstHistory = new EditHistory();
        EditHistory history = new EditHistory();
        firstHistory.add(history);
        String original = "   HelloWorld";
        Document doc = getDocument(original);
        //012345678901234567890
        //   HelloWorld
        //   He__lloWorld
        insert(doc, history, 5, "__");
        String modified = doc.getText(0, doc.getLength());
        assertEquals("   He__lloWorld", modified);
        validateHistory(original, modified, history);
        assertEquals(5, history.getStart());
        assertEquals(0, history.getOriginalSize());
        assertEquals(2, history.getEditedSize());

        // Add some more history
        original = modified;
        EditHistory oldHistory = history;
        history = new EditHistory();
        oldHistory.add(history);
        insert(doc, history, 10, "__");
        modified = doc.getText(0, doc.getLength());
        assertEquals("   He__llo__World", modified);
        validateHistory(original, modified, history);
        assertEquals(10, history.getStart());
        assertEquals(0, history.getOriginalSize());
        assertEquals(2, history.getEditedSize());

        // Now test combined
        // Just most recent:
        assertEquals(1, history.getVersion());
        EditHistory h;

        h = EditHistory.getCombinedEdits(oldHistory.getVersion(), history);
        assertNotNull(h);
        assertEquals(10, h.getStart());
        assertEquals(0, h.getOriginalSize());
        assertEquals(2, h.getEditedSize());

        h = EditHistory.getCombinedEdits(-1, history);
        assertNotNull(h);
        assertEquals(5, h.getStart());
        assertEquals(3, h.getOriginalSize());
        assertEquals(7, h.getEditedSize());

        // From the beginning:
        assertEquals(0, oldHistory.getVersion());
        h = EditHistory.getCombinedEdits(history.getVersion(), history);
        assertNull(h);
    }

    public void testChoppedHistory() throws Exception {
        EditHistory old = new EditHistory();
        for (int i = 0; i < 50; i++) {
            EditHistory history = new EditHistory();
            old.add(history);
            old = history;
        }

        assertNotNull(EditHistory.getCombinedEdits(48, old));
        assertNotNull(EditHistory.getCombinedEdits(47, old));
        assertNotNull(EditHistory.getCombinedEdits(46, old));

        EditHistory curr = old;
        for (int i = 0; i < 5; i++) {
            assertTrue(curr.previous != null);
            assertTrue(curr.previous != curr);
            curr = curr.previous;
        }

        int i = 0;
        for (; i < 49; i++) {
            assertTrue(curr.previous != curr);
            curr = curr.previous;
            if (curr == null) {
                break;
            }
        }
        // Make sure we reached the end of the previous pointers well before the 50
        assertTrue(i < 40);
    }

    public void testMultipleInserts4a() throws Exception {
        // Test real life scenario I ran into where the edit history
        // wasn't right - simplified from 4b
        EditHistory history = new EditHistory();
        String original = "<script>\n    function foo() {}\n</script>";
        String modified;

        Document doc = getDocument(original);
        insert(doc, history, 29, "\n    "); // 5 chars
        modified = doc.getText(0, doc.getLength());
        assertEquals("<script>\n    function foo() {\n    }\n</script>", modified);
        assertEquals(29, history.getStart());
        assertEquals(29, history.getOriginalEnd());
        assertEquals(34, history.getEditedEnd());
        assertEquals(0, history.getOriginalSize());
        assertEquals(5, history.getEditedSize());
        assertEquals(5, history.getSizeDelta());
        validateHistory(original, modified, history);


        insert(doc, history, 32, "        "); // 8 chars
        modified = doc.getText(0, doc.getLength());
        assertEquals("<script>\n    function foo() {\n            }\n</script>", modified);
        assertEquals(29, history.getStart());
        assertEquals(29, history.getOriginalEnd());
        assertEquals(42, history.getEditedEnd());
        assertEquals(0, history.getOriginalSize());
        assertEquals(13, history.getEditedSize());
        assertEquals(13, history.getSizeDelta());
        validateHistory(original, modified, history);
    }

    public void testMultipleInserts4b() throws Exception {
        // Test real life scenario I ran into where the edit history
        // wasn't right
        EditHistory history = new EditHistory();
        String original = "<script>\n    function foo() {}\n</script>";
        String modified;

        Document doc = getDocument(original);
        insert(doc, history, 29, "\n    "); // 5 chars
        modified = doc.getText(0, doc.getLength());
        assertEquals("<script>\n    function foo() {\n    }\n</script>", modified);
        assertEquals(29, history.getStart());
        assertEquals(29, history.getOriginalEnd());
        assertEquals(34, history.getEditedEnd());
        assertEquals(0, history.getOriginalSize());
        assertEquals(5, history.getEditedSize());
        assertEquals(5, history.getSizeDelta());
        validateHistory(original, modified, history);


        insert(doc, history, 29, "\n"); // 1 char
        modified = doc.getText(0, doc.getLength());
        assertEquals("<script>\n    function foo() {\n\n    }\n</script>", modified);
        assertEquals(29, history.getStart());
        assertEquals(29, history.getOriginalEnd());
        assertEquals(35, history.getEditedEnd());
        assertEquals(0, history.getOriginalSize());
        assertEquals(6, history.getEditedSize());
        assertEquals(6, history.getSizeDelta());
        validateHistory(original, modified, history);

        insert(doc, history, 30, "        "); // 8 chars
        modified = doc.getText(0, doc.getLength());
        assertEquals("<script>\n    function foo() {\n        \n    }\n</script>", modified);
        assertEquals(29, history.getStart());
        assertEquals(29, history.getOriginalEnd());
        assertEquals(43, history.getEditedEnd());
        assertEquals(0, history.getOriginalSize());
        assertEquals(14, history.getEditedSize());
        assertEquals(14, history.getSizeDelta());
        validateHistory(original, modified, history);
    }
    public void testMultipleRemoves4a() throws Exception {
        // Similar scenario to the deletion scenario
    }

    public void testMultipleRemoves4b() throws Exception {
        // Test real life scenario I ran into where the edit history
        // wasn't right. This is the reverse of testMultipleInserts4b, which
        // happens when you perform an undo.
        EditHistory history = new EditHistory();
        String original = "<script>\n    function foo() {\n        \n    }\n</script>";
        String modified;

        Document doc = getDocument(original);
        remove(doc, history, 30, 8); // "        "
        modified = doc.getText(0, doc.getLength());
        assertEquals("<script>\n    function foo() {\n\n    }\n</script>", modified);
        assertEquals(30, history.getStart());
        assertEquals(38, history.getOriginalEnd());
        assertEquals(30, history.getEditedEnd());
        assertEquals(8, history.getOriginalSize());
        assertEquals(0, history.getEditedSize());
        assertEquals(-8, history.getSizeDelta());
        validateHistory(original, modified, history);


        remove(doc, history, 29, 1); // "\n"
        modified = doc.getText(0, doc.getLength());
        assertEquals("<script>\n    function foo() {\n    }\n</script>", modified);
        assertEquals(29, history.getStart());
        assertEquals(38, history.getOriginalEnd());
        assertEquals(29, history.getEditedEnd());
        assertEquals(9, history.getOriginalSize());
        assertEquals(0, history.getEditedSize());
        assertEquals(-9, history.getSizeDelta());
        //validateHistory(original, modified, history);

        remove(doc, history, 29, 5); // "\n    "
        modified = doc.getText(0, doc.getLength());
        assertEquals("<script>\n    function foo() {}\n</script>", modified);
        assertEquals(29, history.getStart());
        assertEquals(43, history.getOriginalEnd());
        assertEquals(29, history.getEditedEnd());
        assertEquals(14, history.getOriginalSize());
        assertEquals(0, history.getEditedSize());
        assertEquals(-14, history.getSizeDelta());
        //validateHistory(original, modified, history);
    }

    // TODO : random test. Apply a bunch of edits, then apply them in reverse order
    // and make sure I don't have any errors in the translation before and after.
    // The net change (added/removed) should be identical.

    public void testRandom2() throws Exception {
        EditHistory history = new EditHistory();
        Random random = new Random(0);
        StringBuilder original = new StringBuilder();
        for (int j = 0; j < 100; j++) {
            char c = (char) ('a' + random.nextInt(25));
            original.append(c);
        }

        Document doc = getDocument(original.toString());
        int originalLength = original.length();
        random = new Random(0);

        int added = 0;
        int removed = 0;
        for (int i = 0; i < 5000; i++) {
            boolean insert = random.nextBoolean();
            int editLength = random.nextInt(insert ? 8 : 8);
            if (doc.getLength() < editLength) {
                editLength = doc.getLength();
                if (editLength == 0) {
                    break;
                }
            }
            int offset = random.nextInt(doc.getLength());

            if (random.nextBoolean()) {
                // Insert
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < editLength; j++) {
                    char c = (char) ('a' + j);
                    sb.append(c);
                }
                insert(doc, history, offset, sb.toString());
                added += editLength;
            } else {
                if (offset + editLength > doc.getLength()) {
                    editLength = doc.getLength()-offset;
                }
                // Remove
                remove(doc, history, offset, editLength);
                removed += editLength;
            }


            // Now make sure this makes sense
            int totalDelta = added-removed;
            assertEquals(totalDelta, history.getSizeDelta());

            assertEquals(0, history.convertOriginalToEdited(0));
            assertEquals(doc.getLength(), history.convertOriginalToEdited(originalLength));
            assertEquals(originalLength, history.convertEditedToOriginal(doc.getLength()));
        }
    }

    // TODO - test TokenHierarchy
}
