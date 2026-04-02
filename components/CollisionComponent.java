package com.example.engine.components;

import com.example.engine.dataTypes.Rect2D;
import com.example.engine.dataTypes.Transform;
import com.example.engine.shapes.Shape2D;

public class CollisionComponent extends Component {

    /* ================================
    VARIABLES
    ================================ */

    Shape2D shape;


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
        if(!approximateOverlap(obj)) return false;
        else return shape.overlap(obj.shape);
    }
}
