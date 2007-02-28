/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 */
package org.netbeans.modules.erd.graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import org.netbeans.api.visual.anchor.AnchorShape;

public class OneManyAnchor implements AnchorShape{
    
    private int size;
    private boolean filled;
    private boolean output;
    
    
    
    public OneManyAnchor() {
        this(4)    ;
    }
    
    
    public OneManyAnchor(int size) {
        this.size = size;
        
    }
    
    public double getCutDistance(){
        return 0;
    }
    
    public boolean isLineOriented() {
        return true;
    }
    
    public int getRadius() {
        return (int) Math.ceil(1.5f * size);
    }
    
    public void paint(Graphics2D graphics, boolean source) {
        GeneralPath generalPath = new GeneralPath();
        float side = size * 0.05f+5;
        
        generalPath.moveTo(0.0f, 0.0f);
        generalPath.lineTo(size, 0);
        generalPath.moveTo(size, 0);
        generalPath.lineTo(size-10, 0);
        generalPath.moveTo(size, 0);
        generalPath.lineTo(-side, -size);
        generalPath.moveTo(size,0 );
        generalPath.lineTo(-side, +size);
        generalPath.moveTo(size,0 );
        generalPath.lineTo(size,size );
        generalPath.moveTo(size,0 );
        generalPath.lineTo(size,-size );
        
        
        graphics.draw(generalPath);
    }
}
