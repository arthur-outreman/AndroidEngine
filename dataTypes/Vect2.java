package com.example.engine.dataTypes;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Contract;

public class Vect2 {

    /* ================================
    VARIABLES
    ================================ */

    public float x, y;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public Vect2(float x, float y) {
        this.x = x;
        this.y = y;
    }


    /* ================================
    NEW VECT
    ================================ */

    //
    @NonNull
    @Contract(" -> new")
    public static Vect2 ZERO() {
        return new Vect2(0f, 0f);
    }

    //
    @NonNull
    @Contract("_, _ -> new")
    public static Vect2 TRANSLATION(Vect2 v1, Vect2 v2) {
        return new Vect2(v2.x - v1.x, v2.y - v1.y);
    }

    //
    @NonNull
    @Contract("_, _ -> new")
    public static Vect2 SUM(Vect2 v1, Vect2 v2) {
        return new Vect2(v1.x + v2.x, v1.y + v2.y);
    }

    //
    @NonNull
    @Contract("_, _, _ -> new")
    public static Vect2 ROTATION(Vect2 v, Vect2 pivot, Angle rotation) {
        return new Vect2(
                (v.x - pivot.x) * rotation.cos - (v.y - pivot.y) * rotation.sin + pivot.x,
                (v.x - pivot.x) * rotation.sin + (v.y - pivot.y) * rotation.cos + pivot.y
        );
    }


    /* ================================
    METHODES
    ================================ */

    //
    public void add(Vect2 v) {
        x += v.x;
        y += v.y;
    }

    //
    public void sub(Vect2 v) {
        x -= v.x;
        y -= v.y;
    }

    //
    public void scalar(float k) {
        x *= k;
        y *= k;
    }

    public void rotate(Vect2 pivot, Angle rotation) {
        float dx = x - pivot.x;
        float dy = y - pivot.y;

        float newX = dx * rotation.cos - dy * rotation.sin + pivot.x;
        float newY = dx * rotation.sin + dy * rotation.cos + pivot.y;

        x = newX;
        y = newY;
    }

    //
    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    //
    public void normalize() {
        float len = length();
        if (len != 0) {
            x /= len;
            y /= len;
        }
    }

    //
    public void clamp(Vect2 max) {
        if (x==0 && y==0) return;

        float scaleX = (max.x != 0) ? Math.abs(max.x / x) : Float.MAX_VALUE;
        float scaleY = (max.y != 0) ? Math.abs(max.y / y) : Float.MAX_VALUE;

        float scale = Math.min(scaleX, scaleY);

        if (scale<1f) {
            x *= scale;
            y *= scale;
        }
    }
}
