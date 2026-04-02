package com.example.engine.shapes;

import android.graphics.Canvas;

import com.example.engine.Graphics;
import com.example.engine.SAT;
import com.example.engine.dataTypes.Rect2D;
import com.example.engine.dataTypes.Transform;
import com.example.engine.dataTypes.Vect2;

public class CircleShape2D extends Shape2D {

    /* ================================
    VARIABLES
    ================================ */

    public Vect2 center, transformedCenter;
    public float radius;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public CircleShape2D(float rayon) {
        this.radius = rayon;
        center = new Vect2(rayon, rayon);
        transformedCenter = new Vect2(rayon, rayon);
    }


    /* ================================
    METHODES
    ================================ */

    //
    @Override
    public Rect2D getApproximateRect(Transform t) {
        generateTransform(t);

        Vect2 position = new Vect2(t.position.x, t.position.y);
        Vect2 size = new Vect2(radius *2, radius *2);

        return new Rect2D(position, size);
    }

    //
    private void generateTransform(Transform t) {
        if(!dirty(t)) return;
        transformedCenter = Vect2.SUM(center, t.position);
    }


    /* ================================
    COLLISIONS
    ================================ */

    //
    @Override
    public boolean overlapPolygon2D(PolygonShape2D polygon) {
        return SAT.polygonOnCircle(polygon, this);
    }

    //
    @Override
    public boolean overlapCircle2D(CircleShape2D circle) {
        Vect2 diff = Vect2.TRANSLATION(transformedCenter, circle.transformedCenter);
        return diff.length() < radius + circle.radius;
    }


    /* ================================
    RENDER
    ================================ */

    //
    @Override
    public void render(Canvas canvas, Graphics graphics, Transform t, int col) {
        generateTransform(t);

        graphics.drawCircle(canvas, transformedCenter, radius, col);
    }
}
