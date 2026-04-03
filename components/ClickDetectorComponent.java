package com.example.engine.components;

import android.util.Log;
import android.view.MotionEvent;

import com.example.engine.dataTypes.Transform;
import com.example.engine.dataTypes.Vect2;
import com.example.engine.shapes.Shape2D;

public class ClickDetectorComponent extends Component {

    /* ================================
    VARIABLES
    ================================ */

    private Shape2D shape;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public ClickDetectorComponent(Shape2D shape) {
        this.shape = shape;
    }


    /* ================================
    EVENT
    ================================ */

    //
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            Transform t = (parent!=null)? parent.getGlobalTransform():Transform.ZERO();
            if(shape.pointOnShape(t, new Vect2(event.getX(), event.getY()))) {
                signal("ClickDetectorClicked");
                return true;
            }
        }
        return false;
    }
}
