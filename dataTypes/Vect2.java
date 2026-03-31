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
    Vect2(float x, float y) {
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
    public static Vect2 translation(Vect2 v1, Vect2 v2) {
        return new Vect2(v2.x - v1.x, v2.y - v1.y);
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
