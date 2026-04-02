package com.example.engine.components;

import android.util.Log;

import com.example.engine.dataTypes.Rect2D;
import com.example.engine.dataTypes.Transform;
import com.example.engine.shapes.Shape2D;

public class CollisionComponent extends Component {

    /* ================================
    VARIABLES
    ================================ */

    public Shape2D shape;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public CollisionComponent(Shape2D shape) {
        this.shape = shape;
    }


    /* ================================
    COLLISIONS
    ================================ */

    //
    public Rect2D getApproximateRect() {
        Transform t = (parent!=null)? parent.getGlobalTransform():Transform.ZERO();
        return shape.getApproximateRect(t);
    }

    //
    public boolean approximateOverlap(CollisionComponent obj) {
        Rect2D approximateRect1 = getApproximateRect();
        return approximateRect1.overlap(obj.getApproximateRect());
    }

    //
    public boolean overlap(CollisionComponent obj) {
        Transform t1 = (parent!=null)? parent.getGlobalTransform():Transform.ZERO();
        Transform t2 = (obj.parent!=null)? obj.parent.getGlobalTransform():Transform.ZERO();

        shape.getApproximateRect(t1);   // force update
        obj.shape.getApproximateRect(t2);

        if(!approximateOverlap(obj)) return false;

        return shape.overlap(obj.shape);
    }
}
