package com.example.engine.dataTypes;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Contract;

public class Angle {

    /* ================================
    VARIABLES
    ================================ */

    public float angle;

    public float cos;
    public float sin;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public Angle(float angleDegree) {
        angle = angleDegree;
        normalize();
    }


    /* ================================
    NEW ANGLE
    ================================ */

    //
    @NonNull
    @Contract(" -> new")
    public static Angle ZERO() {
        return new Angle(0f);
    }

    //
    @NonNull
    @Contract("_, _ -> new")
    public static Angle SUM(Angle a1, Angle a2) {
        return new Angle(a1.angle + a2.angle);
    }


    /* ================================
    METHODES
    ================================ */

    //
    private void calculateTrigo() {
        float rad = (float) Math.toRadians(angle);
        cos = (float) Math.cos(rad);
        sin = (float) Math.sin(rad);
    }

    //
    private void normalize() {
        if(angle < -360f) angle += 360f;
        if(angle > 360f) angle -= 360f;
        calculateTrigo();
    }

    //
    public void rotate(float angleDegree) {
        angle += angleDegree;
        normalize();
    }

    //
    public void add(Angle a) {
        angle += a.angle;
        normalize();
    }

    //
    public void sub(Angle a) {
        angle -= a.angle;
        normalize();
    }

    //
    public void scalar(float k) {
        angle *= k;
        normalize();
    }
}
