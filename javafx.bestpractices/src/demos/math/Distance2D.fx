package math;

import javafx.ui.*;
import javafx.ui.canvas.*;
import java.lang.Math;

import java.lang.System;

var distance = 0;
var circles : Circle[];

var width : Number = 100;
var height : Number = 100;

var mouseX : Number = 0;
var mouseY : Number = 0;

var max_distance : Number = dist( 0, 0, width, height );

function dist( x1 : Number, y1 : Number, x2 : Number, y2 : Number ): Number {
    return Math.sqrt(( x1 - x2 ) * ( x1 - x2 ) + ( y1 - y2 ) * ( y1 - y2 ));
}

for( x in [0..10] ) {
    for( y in [0..10] ) {
        var cx = x * 20 + 10;
        var cy = y * 20 + 10;
        insert Circle {
            cx : cx
            cy : cy
            radius : bind dist( mouseX, mouseY, cx, cy ) / max_distance * 20
            fill : Color.WHITE
        } into circles;
    }
}

Frame {
    content : Canvas {    
        width : 200
        height : 200
        background : Color.GRAY   
        content : circles
        onMouseMoved : function( e : MouseEvent ): Void {
            mouseX = e.x;
            mouseY = e.y;
        }            
    };        
    
    visible : true
    title : "Distance 2D"
    width : 200
    height : 232
    onClose : function() { java.lang.System.exit( 0 ); }    
}
