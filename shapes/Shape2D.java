package com.example.engine.shapes;

import android.graphics.Canvas;

import com.example.engine.Graphics;
import com.example.engine.dataTypes.Rect2D;
import com.example.engine.dataTypes.Transform;

public class Shape2D {

    /* ================================
    VARIABLES
    ================================ */

    Transform lastTransform = Transform.ZERO();


    /* ================================
    METHODES
    ================================ */

    //
    public Rect2D getApproximateRect(Transform t) {return Rect2D.ZERO();}

    //
    public boolean dirty(Transform t) {
        if(lastTransform.equals(t)) return false;
        lastTransform = t;
        return true;
    }


    /* ================================
    COLLISIONS
    ================================ */

    //
    public boolean overlapPolygon2D(PolygonShape2D polygon) {return false;}

    //
    public boolean overlapCircle2D(CircleShape2D circle) {return false;}

    //
    public boolean overlap(Shape2D shape) {
        if(shape instanceof PolygonShape2D) return overlapPolygon2D((PolygonShape2D) shape);
        else if(shape instanceof CircleShape2D) return overlapCircle2D((CircleShape2D) shape);
        else return false;
    }


    /* ================================
    RENDER
    ================================ */

    //
    public void render(Canvas canvas, Graphics graphics, Transform t, int col) {}
}
