package com.example.engine.shapes;

import android.graphics.Canvas;

import androidx.annotation.NonNull;

import com.example.engine.Graphics;
import com.example.engine.dataTypes.Rect2D;
import com.example.engine.dataTypes.Transform;
import com.example.engine.dataTypes.Vect2;

import org.jetbrains.annotations.Contract;

public class Shape2D {

    /* ================================
    VARIABLES
    ================================ */

    Transform lastTransform = Transform.ZERO();


    /* ================================
    STATIC
    ================================ */

    //
    @NonNull
    @Contract("_ -> new")
    public static Shape2D RECT(Vect2 size) {
        Vect2[] points = new Vect2[4];

        points[0] = new Vect2(0f, 0f);
        points[1] = new Vect2(size.x, 0f);
        points[2] = new Vect2(size.x, size.y);
        points[3] = new Vect2(0f, size.y);

        return new PolygonShape2D(points);
    }

    //
    @NonNull
    @Contract("_ -> new")
    public static Shape2D CENTERED_RECT(Vect2 size) {

        float w = size.x;
        float h = size.y;

        Vect2[] points = new Vect2[4];

        points[0] = new Vect2(-w * 0.5f, -h * 0.5f);
        points[1] = new Vect2(w * 0.5f, -h * 0.5f);
        points[2] = new Vect2(w * 0.5f, h * 0.5f);
        points[3] = new Vect2(-w * 0.5f, h * 0.5f);

        return new PolygonShape2D(points);
    }

    //
    @NonNull
    @Contract("_ -> new")
    public static Shape2D CENTERED_TRIANGLE(float size) {

        float h = (float) (Math.sqrt(3) / 2 * size);

        Vect2[] points = new Vect2[3];

        points[0] = new Vect2(0f, -h / 2f);
        points[1] = new Vect2(-size / 2f, h / 2f);
        points[2] = new Vect2(size / 2f, h / 2f);

        return new PolygonShape2D(points);
    }

    //
    @NonNull
    @Contract("_ -> new")
    public static Shape2D CIRCLE(float radius) {
        return new CircleShape2D(radius);
    }

    //
    @NonNull
    @Contract("_ -> new")
    public static Shape2D CENTERED_CIRCLE(float radius) {
        return new CircleShape2D(radius, true);
    }


    /* ================================
    METHODES
    ================================ */

    //
    public Rect2D getApproximateRect(Transform t) {return Rect2D.ZERO();}

    //
    public boolean dirty(Transform t) {
        if(lastTransform.equals(t)) return false;
        lastTransform = Transform.CLONE(t);
        return true;
    }

    //
    public boolean pointOnShape(Transform t, Vect2 point) {return false;}


    /* ================================
    COLLISIONS
    ================================ */

    //
    public boolean overLapScreen(ScreenShape2D screen) {return false;}

    //
    public boolean overlapPolygon2D(PolygonShape2D polygon) {return false;}

    //
    public boolean overlapCircle2D(CircleShape2D circle) {return false;}

    //
    public boolean overlap(Shape2D shape) {
        if(shape instanceof PolygonShape2D) return overlapPolygon2D((PolygonShape2D) shape);
        else if(shape instanceof CircleShape2D) return overlapCircle2D((CircleShape2D) shape);
        else if(shape instanceof ScreenShape2D) return overLapScreen((ScreenShape2D) shape);
        else return false;
    }


    /* ================================
    RENDER
    ================================ */

    //
    public void render(Canvas canvas, Graphics graphics, Transform t, int col) {}
}
