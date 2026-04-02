package com.example.engine;

import com.example.engine.dataTypes.Vect2;
import com.example.engine.shapes.CircleShape2D;
import com.example.engine.shapes.PolygonShape2D;

public final class SAT {

    /* ================================
    METHODES
    ================================ */

    //
    private static boolean overlapOnAxis(Vect2 axis, Vect2[] a, Vect2[] b) {

        float minA = axis.dot(a[0]);
        float maxA = minA;

        for (int i = 1; i < a.length; i++) {
            float p = axis.dot(a[i]);
            if (p < minA) minA = p;
            if (p > maxA) maxA = p;
        }

        float minB = axis.dot(b[0]);
        float maxB = minB;

        for (int i = 1; i < b.length; i++) {
            float p = axis.dot(b[i]);
            if (p < minB) minB = p;
            if (p > maxB) maxB = p;
        }

        return !(maxA < minB || maxB < minA);
    }

    //
    private static boolean circleOverlap(Vect2 axis, Vect2[] poly, Vect2 center, float radius) {

        float minPoly = axis.dot(poly[0]);
        float maxPoly = minPoly;

        for (int i = 1; i < poly.length; i++) {
            float p = axis.dot(poly[i]);
            if (p < minPoly) minPoly = p;
            if (p > maxPoly) maxPoly = p;
        }

        float centerProj = axis.dot(center);

        float minCircle = centerProj - radius;
        float maxCircle = centerProj + radius;

        return !(maxPoly < minCircle || maxCircle < minPoly);
    }


    /* ================================
    POLYGON ON POLYGON
    ================================ */

    //
    public static boolean polygonOnPolygon(PolygonShape2D shape1, PolygonShape2D shape2) {

        Vect2[] a = shape1.transformedPoints;
        Vect2[] b = shape2.transformedPoints;

        for (int i = 0; i < a.length; i++) {
            Vect2 p1 = a[i];
            Vect2 p2 = a[(i + 1) % a.length];

            Vect2 edge = new Vect2(p2.x - p1.x, p2.y - p1.y);
            Vect2 axis = edge.perp();
            axis.normalize();

            if (!overlapOnAxis(axis, a, b)) return false;
        }

        for (int i = 0; i < b.length; i++) {
            Vect2 p1 = b[i];
            Vect2 p2 = b[(i + 1) % b.length];

            Vect2 edge = new Vect2(p2.x - p1.x, p2.y - p1.y);
            Vect2 axis = edge.perp();
            axis.normalize();

            if (!overlapOnAxis(axis, a, b)) return false;
        }
        return true;
    }


    /* ================================
    POLYGON ON CIRCLE
    ================================ */

    //
    public static boolean polygonOnCircle(PolygonShape2D polygon, CircleShape2D circle) {

        Vect2[] poly = polygon.transformedPoints;
        Vect2 center = circle.transformedCenter;
        float radius = circle.radius;

        for (int i=0; i<poly.length; i++) {

            Vect2 p1 = poly[i];
            Vect2 p2 = poly[(i+1) % poly.length];

            Vect2 edge = new Vect2(p2.x - p1.x, p2.y - p1.y);
            Vect2 axis = edge.perp();
            axis.normalize();

            if (!circleOverlap(axis, poly, center, radius)) return false;
        }

        Vect2 closest = poly[0];
        float minDist = Float.MAX_VALUE;

        for (int i=0; i<poly.length; i++) {
            float dx = center.x - poly[i].x;
            float dy = center.y - poly[i].y;
            float dist = dx * dx + dy * dy;

            if (dist < minDist) {
                minDist = dist;
                closest = poly[i];
            }
        }

        Vect2 axis = new Vect2(closest.x - center.x, closest.y - center.y);
        axis.normalize();

        return circleOverlap(axis, poly, center, radius);
    }
}
