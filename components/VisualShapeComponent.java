package com.example.engine.components;

import android.graphics.Canvas;

import com.example.engine.Graphics;
import com.example.engine.shapes.Shape2D;

public class VisualShapeComponent extends Component {

    /* ================================
    VARIABLES
    ================================ */

    Shape2D shape;
    int col;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public VisualShapeComponent(Shape2D shape, int col) {
        this.shape = shape;
        this.col = col;
    }


    /* ================================
    RENDER
    ================================ */

    //
    @Override
    public void render(Canvas canvas, Graphics graphics) {
        if(parent != null) {
            shape.render(canvas, graphics, parent.getGlobalTransform(), col);
        }
    }
}
