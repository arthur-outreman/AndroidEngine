package com.example.engine.components;

import android.graphics.Canvas;

import com.example.engine.Graphics;
import com.example.engine.dataTypes.Shape2D;
import com.example.engine.dataTypes.Transform;

public class VisualShapeComponent extends Component {

    /* ================================
    VARIABLES
    ================================ */

    Shape2D shape;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    public VisualShapeComponent(Shape2D shape) {
        this.shape = shape;
    }

    /* ================================
    RENDER
    ================================ */

    //
    @Override
    public void render(Canvas canvas, Graphics graphics) {
        if(parent != null) {
            shape.render(canvas, graphics, parent.getGlobalTransform());
        }
    }
}
