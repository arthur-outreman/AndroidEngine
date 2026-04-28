package com.example.engine.shapes;

import android.graphics.Canvas;
import android.graphics.Path;

import com.example.engine.Graphics;
import com.example.engine.SAT;
import com.example.engine.dataTypes.Rect2D;
import com.example.engine.dataTypes.Transform;
import com.example.engine.dataTypes.Vect2;

public class PolygonShape2D extends Shape2D {

    /* ================================
    VARIABLES
    ================================ */

    public int nb_points;
    public Vect2[] points;
    public Vect2[] transformedPoints;
    public Rect2D approximateRect;
    public Vect2 pivot;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    public PolygonShape2D(Vect2[] points) {
        nb_points = points.length;
        this.points = points;

        this.pivot = findCenterOfMass();
        initTransform();
        generateApproximateRect();
    }

    public PolygonShape2D(Vect2[] points, Vect2 pivot) {
        nb_points = points.length;
        this.points = points;

        this.pivot = pivot;
        initTransform();
        generateApproximateRect();
    }


    /* ================================
    METHODES
    ================================ */

    private void initTransform() {
        transformedPoints = new Vect2[nb_points];
        for(int i=0; i<nb_points; i++) {
            transformedPoints[i] = Vect2.CLONE(points[i]);
        }
    }

    private void generateApproximateRect() {
        if (transformedPoints == null || nb_points == 0) {
            approximateRect = Rect2D.ZERO();
            return;
        }

        float minX = transformedPoints[0].x, maxX = transformedPoints[0].x;
        float minY = transformedPoints[0].y, maxY = transformedPoints[0].y;

        for(int i=1; i<nb_points; i++) {

            if(transformedPoints[i].x < minX) minX = transformedPoints[i].x;
            if(transformedPoints[i].x > maxX) maxX = transformedPoints[i].x;

            if(transformedPoints[i].y < minY) minY = transformedPoints[i].y;
            if(transformedPoints[i].y > maxY) maxY = transformedPoints[i].y;
        }

        approximateRect = new Rect2D(
                new Vect2(minX, minY),
                new Vect2(maxX - minX, maxY - minY)
        );
    }

    private void generateTransform(Transform t) {
        if(!dirty(t)) return;

        for(int i=0; i<nb_points; i++) {

            Vect2 rotated = Vect2.ROTATION(points[i], pivot, t.rotation);

            transformedPoints[i].x = rotated.x + t.position.x;
            transformedPoints[i].y = rotated.y + t.position.y;
        }

        generateApproximateRect();
    }

    @Override
    public Rect2D getApproximateRect(Transform t) {
        if(points == null || nb_points == 0) return Rect2D.ZERO();

        generateTransform(t);
        return approximateRect;
    }

    private Vect2 findCenterOfMass() {
        float minX = points[0].x, maxX = points[0].x;
        float minY = points[0].y, maxY = points[0].y;

        for(int i=1; i<nb_points; i++) {
            if(points[i].x < minX) minX = points[i].x;
            if(points[i].x > maxX) maxX = points[i].x;

            if(points[i].y < minY) minY = points[i].y;
            if(points[i].y > maxY) maxY = points[i].y;
        }

        return new Vect2(
                (minX + maxX) / 2f,
                (minY + maxY) / 2f
        );
    }

    @Override
    public boolean pointOnShape(Transform t, Vect2 point) {
        Rect2D approximate = getApproximateRect(t);
        return approximate.overlap(new Rect2D(point, Vect2.ZERO()));
    }


    /* ================================
    COLLISIONS
    ================================ */

    @Override
    public boolean overlapPolygon2D(PolygonShape2D polygon) {
        return SAT.polygonOnPolygon(this, polygon);
    }

    @Override
    public boolean overlapCircle2D(CircleShape2D circle) {
        return SAT.polygonOnCircle(this, circle);
    }


    /* ================================
    RENDER
    ================================ */

    @Override
    public void render(Canvas canvas, Graphics graphics, Transform t, int col) {
        if(points == null || nb_points < 2) return;

        generateTransform(t);

        graphics.drawPolygon(canvas, transformedPoints, col);
    }
}
