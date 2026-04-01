package com.example.engine.dataTypes;

import android.graphics.Canvas;
import com.example.engine.Graphics;

public class PolygonShape2D extends Shape2D {

    /* ================================
    VARIABLES
    ================================ */

    public int nb_points;
    public Vect2[] points;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public PolygonShape2D(Vect2[] points) {
        nb_points = points.length;
        this.points = points;
    }


    /* ================================
    METHODES
    ================================ */

    //
    private Vect2[] transform(Transform t) {
        Vect2[] transformed_polygon = new Vect2[nb_points];

        for(int i = 0; i < nb_points; i++) {
            Vect2 rotated = Vect2.ROTATION(points[i], Vect2.ZERO(), t.rotation);
            transformed_polygon[i] = Vect2.SUM(rotated, t.position);
        }
        return transformed_polygon;
    }


    /* ================================
    RENDER
    ================================ */

    //
    @Override
    public void render(Canvas canvas, Graphics graphics, Transform t) {
        if (points == null || nb_points < 2) return;

        Vect2[] pts = transform(t);

        for (int i = 0; i < nb_points; i++) {
            Vect2 p1 = pts[i];
            Vect2 p2 = pts[(i + 1) % nb_points];

            graphics.drawLine(canvas, p1, p2, 0, 0, 0);
        }
    }
}
