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
    public Vect2 pivot;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public PolygonShape2D(Vect2[] points) {
        nb_points = points.length;
        this.points = points;
        pivot = findCenterOfMass();
        initTransform();
    }

    //
    public PolygonShape2D(Vect2[] points, Vect2 pivot) {
        nb_points = points.length;
        this.points = points;
        this.pivot = pivot;
        initTransform();
    }


    /* ================================
    METHODES
    ================================ */

    //
    private void initTransform() {
        for(int i=0; i<nb_points; i++) {
            transformedPoints[i] = Vect2.CLONE(points[i]);
        }
    }

    //
    private void generateTransform(Transform t) {
        if(!dirty(t)) return;

        transformedPoints = new Vect2[nb_points];
        for(int i=0; i<nb_points; i++) {
            Vect2 rotated = Vect2.ROTATION(points[i], pivot, t.rotation);
            transformedPoints[i] = Vect2.SUM(rotated, t.position);
        }
    }

    //
    @Override
    public Rect2D getApproximateRect(Transform t) {
        if(points == null) return Rect2D.ZERO();

        generateTransform(t);

        float minX = transformedPoints[0].x, maxX = transformedPoints[0].x;
        float minY = transformedPoints[0].y, maxY = transformedPoints[0].y;

        for(int i=1; i<nb_points; i++) {
            if(transformedPoints[i].x < minX) minX = transformedPoints[i].x;
            else if(transformedPoints[i].x > maxX) maxX = transformedPoints[i].x;

            if(transformedPoints[i].y < minY) minY = transformedPoints[i].y;
            else if(transformedPoints[i].y > maxY) maxY = transformedPoints[i].y;
        }

        return new Rect2D(new Vect2(minX, minY), new Vect2(maxX-minX, maxY-minY));
    }

    //
    private Vect2 findCenterOfMass() {
        Rect2D approximateRect = getApproximateRect(Transform.ZERO());
        return approximateRect.findCenterOfMass();
    }


    /* ================================
    COLLISIONS
    ================================ */

    //
    @Override
    public boolean overlapPolygon2D(PolygonShape2D polygon) {
        return SAT.polygonOnPolygon(this, polygon);
    }

    //
    @Override
    public boolean overlapCircle2D(CircleShape2D circle) {
        return SAT.polygonOnCircle(this, circle);
    }


    /* ================================
    RENDER
    ================================ */

    //
    @Override
    public void render(Canvas canvas, Graphics graphics, Transform t, int col) {
        if(points == null || nb_points < 2) return;

        generateTransform(t);

        Path path = new Path();
        path.moveTo(transformedPoints[0].x, transformedPoints[0].y);
        for (int i=1; i<nb_points; i++) {
            path.lineTo(transformedPoints[i].x, transformedPoints[i].y);
        }
        path.close();

        graphics.drawPath(canvas, path, col);
    }
}
