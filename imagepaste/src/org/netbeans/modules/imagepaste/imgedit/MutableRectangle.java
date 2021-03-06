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
package org.netbeans.modules.imagepaste.imgedit;

import java.awt.Point;
import java.awt.Rectangle;


/**
 *
 * A rectangle which keeps an always-positive width and height, and which
 * can be adjusted by changing the location of any corner (and if the
 * transformation by adjusting a corner changes the logical corner being
 * dragged, it will say so).
 */
final class MutableRectangle extends Rectangle {
    static final int ANY = 0;
    static final int NE = 1;
    static final int SE = 2;
    static final int NW = 3;
    static final int SW = 4;

    public MutableRectangle(Point nw, Point se) {
        super(genRectangle(nw, se));
    }

    /**
     * Given two points, generate a rectangle with positive width
     * and height
     */
    private static Rectangle genRectangle(Point nw, Point se) {
        Point a = new Point();
        Point b = new Point();

        if (nw.x == se.x || nw.y == se.y) {
            //            throw new IllegalArgumentException("" + nw + se);
        }
        if (nw.x < se.x) {
            a.x = nw.x;
            b.x = se.x;
        } else {
            a.x = se.x;
            b.x = nw.x;
        }
        if (nw.y < se.y) {
            a.y = nw.y;
            b.y = se.y;
        } else {
            a.y = se.y;
            b.y = nw.y;
        }
        int w = b.x - a.x;
        int h = b.y - a.y;

        return new Rectangle(a.x, a.y, w, h);
    }
    
    public Rectangle normalize() {
        Point a = this.getLocation();
        Point b = new Point (a);
        b.x += width;
        b.y += height;
        return genRectangle (a, b);
    }

    public void makeSquare(int corner) {
        int n = Math.min(width, height);

        switch(corner) {
            case NW:

                x = (x + width) - n;

                y = (y + height) - n;

                width = n;

                height = n;

                break;
            case NE:

                width = n;

                y = (y + height) - n;

                height = n;

                break;
            case SW:

                width = n;

                height = n;

                break;
            case SE:

                x = (x + width) - n;

                width = n;

                height = n;

                break;
        }
    }

    /**
     * Get a point representing the requested corner
     */

    public Point getPoint(int which) {
        Point result = getLocation();

        switch(which) {
            case NW:

                break;
            case NE:

                result.x += width;

                break;
            case SE:

                result.y += height;

                break;
            case SW:

                result.x += width;

                result.y += height;

                break;
            default:

                throw new IllegalArgumentException();
        }
        return result;
    }

    /**
     * Get the nearest corner to the passed point
     */

    public int nearestCorner(Point p) {
        int best = 0;
        double bestDistance = Integer.MAX_VALUE;

        for (int i = NE; i <= SW; i++) {
            Point check = getPoint(i);
            double dist = check.distance(p);

            if (dist < bestDistance) {
                bestDistance = dist;
                best = i;
            }
        }
        return best;
    }

    /**
     * Set one of the corners to a new location.
     * @param p The location
     * @param which ID of the corner to set
     * @return -1 if nothing has changed (the corner to set to the new point
     * already had those coordinates);  -2 if the corner was moved, but the
     * transform did not cause the corner to become logically a different
     * corner;  a corner ID, != which, that is now the corner being moved.
     * i.e. if you drag the southeast corner above the northeast corner,
     * you are now dragging the northeast corner.
     */

    public int setPoint(Point p, int which) {
        int newX;
        int newY;
        int newW;
        int newH;
        int result = -1;

        switch(which) {
            case NW:

                newW = width + (x - p.x);

                newH = height + (y - p.y);

                if (changeBounds(p.x, p.y, newW, newH)) {
                    if (newW <= 0 && newH > 0) {
                        result = NE;
                    } else                        if (newW <= 0 && newH <= 0) {
                        result = SE;
                    } else                            if (newW > 0 && newH <= 0) {
                        result = SE;
                    } else {
                        result = -2;
                    }
                }

                break;
            case NE:

                newW = p.x - x;

                newY = p.y;

                newH = height + (y - p.y);

                if (changeBounds(x, newY, newW, newH)) {
                    if (newW <= 0 && newH > 0) {
                        result = NW;
                    } else                        if (newW <= 0 && newH <= 0) {
                        result = SE;
                    } else                            if (newW > 0 && newH <= 0) {
                        result = SW;
                    } else {
                        result = -2;
                    }
                }

                break;
            case SW:

                newW = p.x - x;

                newH = p.y - y;

                if (changeBounds(x, y, newW, newH)) {
                    if (newW <= 0 && newH > 0) {
                        result = SE;
                    } else                        if (newW <= 0 && newH <= 0) {
                        result = NW;
                    } else                            if (newW > 0 && newH <= 0) {
                        result = NE;
                    } else {
                        result = -2;
                    }
                }

                break;
            case SE:

                newX = p.x;

                newW = width + (x - p.x);

                newH = p.y - y;

                if (changeBounds(newX, y, newW, newH)) {
                    if (newW <= 0 && newH > 0) {
                        result = SW;
                    } else                        if (newW <= 0 && newH <= 0) {
                        result = NE;
                    } else                            if (newW > 0 && newH <= 0) {
                        result = NW;
                    } else {
                        result = -2;
                    }
                }

                break;
            default:

                assert false : "Bad corner: " + which;
        }
        return result;
    }

    /**
     * Set the bounds, returning true if an actual change occurred
     */

    private boolean changeBounds(int x, int y, int w, int h) {
        boolean change = x != this.x || y != this.y || w != this.width ||
                h != this.height;

        if (change) {
            setBounds(x, y, w, h);
        }
        return change;
    }

    /**
     * Overridden to convert negative width/height into relocation with
     * positive width/height
     */

    public void setBounds(int x, int y, int w, int h) {
        if (w < 0) {
            int newW = -w;

            x += w;
            w = newW;
        }
        if (h < 0) {
            int newH = -h;

            y += h;
            h = newH;
        }
        super.setBounds(x, y, w, h);
    }
}