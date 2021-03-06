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
/*
 * LineOfSightIntersticeFactory.java
 *
 * Created on May 4, 2004, 3:27 PM
 */

package org.netbeans.swing.splitcontainer;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * An implementation of IntersticeFactory which will use a line-of-sight
 * algorithm to determine which components will be affected by a drag at
 * the given point.
 * <p>
 * Think of the grid of components as a street map, and the components as
 * buildings on the street.  This IntersticeFactory treats the point passed
 * as a place you are standing on the street.  All the buildings which have
 * at least two corners visible will be affected by the drag (assume you have
 * excellent eyesight).  If three 
 * corners are visible, the drag will have an effect in both the horizontal
 * and vertical axes.
 * <p>
 * (XXX probably this should be modified not to do *pure* line of sight - 
 * there are a few corner cases that result that should be handled).
 *
 * @author  Tim Boudreau
 */
class LineOfSightIntersticeFactory extends IntersticeFactory {
    private static Rectangle scr = new Rectangle();
    
    private static final int ABOVE = 1;
    private static final int BELOW = 2;
    private static final int LEFT = 4;
    private static final int RIGHT = 8;    
   
    public Interstice createInterstice(SplitContainer c, Point p, Rectangle[] rects, Component[] comps) {
        Interstice result = null;
        int[] constraints = new int[rects.length];
        getRectangleConstraints (p, rects, constraints);

        List above = null;
        List below = null;
        List right = null;
        List left = null;
        for (int i=0; i < rects.length; i++) {
            if (((constraints[i]) & ABOVE) != 0) {
                if (above == null) above = new ArrayList();
                above.add (comps[i]);
            }

            if (((constraints[i]) & BELOW) != 0) {
                if (below == null) below = new ArrayList();
                below.add (comps[i]);
            }

            if (((constraints[i]) & LEFT) != 0) {
                if (left == null) left = new ArrayList();
                left.add (comps[i]);
            }

            if (((constraints[i]) & RIGHT) != 0) {
                if (right == null) right = new ArrayList();
                right.add (comps[i]);
            }
        }
        if (left == null && right == null && above == null && below == null) {
            return null;
        }

        Component[] empty = new Component[0];

        Component[] leftc = left == null ? empty : (Component[]) left.toArray (empty);
        Component[] abovec = above == null ? empty : (Component[]) above.toArray (empty);
        Component[] rightc = right == null ? empty : (Component[]) right.toArray (empty);
        Component[] belowc = below == null ? empty : (Component[]) below.toArray (empty);

        result = new Interstice (leftc, rightc, abovec, belowc, true);
        return result;
    }
    
    public static void getRectangleConstraints (Point p, Rectangle[] r, int[] constraints) {
        if (p == null) return;
        for (int i=0; i < r.length; i++) {
            constraints[i] = constraintFor (r, p, r[i]);
        }
    }    
    
    private static int constraintFor (Rectangle[] rects, Point p, Rectangle r) {
        int result = 0;
        
        boolean upperLeft = notIntersected (rects, r, p, r.x, r.y);
        boolean upperRight = notIntersected (rects, r, p, r.x + r.width, r.y);
        boolean lowerRight = notIntersected (rects, r, p, r.x + r.width, r.y + r.height);
        boolean lowerLeft = notIntersected (rects, r, p, r.x, r.y + r.height);
        
        result |= (upperLeft && lowerLeft) ? LEFT : 0;
        result |= (upperLeft && upperRight) ? BELOW : 0;
        result |= (upperRight && lowerRight) ? RIGHT : 0;
        result |= (lowerRight && lowerLeft) ? ABOVE : 0;
        
        return result;
    }

    
    private static boolean notIntersected (Rectangle[] r, Rectangle curr, Point origin, int x1, int y1) {
        int x = origin.x;
        int y = origin.y;
        
        //Shrink the rectangle we're testing a point on slightly, so we don't
        //get a false positive because one of the points is a corner of it
        scr.setBounds (curr);
        scr.width -= 2;
        scr.height -= 2;
        scr.x += 1;
        scr.y += 1;
        boolean result = !scr.intersectsLine (origin.x, origin.y, x1, y1);
        
        if (result) {
            for (int i=0; i < r.length; i++) {
                if (curr != r[i]) {
                    result &= !r[i].intersectsLine (origin.x, origin.y, x1, y1);
                    if (!result) break;
                }
            }
        }
        return result;
    }
}
