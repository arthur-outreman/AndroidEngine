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
    public Rect2D approximateRect;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public CircleShape2D(float rayon) {
        this.radius = rayon;
        center = new Vect2(rayon, rayon);
        transformedCenter = new Vect2(rayon, rayon);
        generateApproximateRect(Transform.ZERO());
    }

    //
    public CircleShape2D(float rayon, boolean centered) {
        this.radius = rayon;
        center = centered? Vect2.ZERO():new Vect2(rayon, rayon);
        transformedCenter = centered? Vect2.ZERO():new Vect2(rayon, rayon);
        generateApproximateRect(Transform.ZERO());
    }


    /* ================================
    METHODES
    ================================ */

    //
    private void generateApproximateRect(Transform t) {
        Vect2 position = new Vect2(
                transformedCenter.x - radius,
                transformedCenter.y - radius
        );
        Vect2 size = new Vect2(radius * 2, radius * 2);

        approximateRect = new Rect2D(position, size);
    }

    //
    private void generateTransform(Transform t) {
        if(!dirty(t)) return;
        transformedCenter = Vect2.SUM(center, t.position);
        generateApproximateRect(t);
    }

    //
    @Override
    public Rect2D getApproximateRect(Transform t) {
        generateTransform(t);

        return approximateRect;
    }

    //
    @Override
    public boolean pointOnShape(Transform t, Vect2 point) {
        generateTransform(t);

        Vect2 diff = Vect2.TRANSLATION(transformedCenter, point);
        return diff.length() <= radius;
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
