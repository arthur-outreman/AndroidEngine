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
    STATIC
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

    //
    @NonNull
    @Contract("_ -> new")
    public static Angle CLONE(Angle a) {
        return new Angle(a.angle);
    }


    /* ================================
    NEW ANGLE
    ================================ */

    //
    public Angle add(Angle a) {
        return new Angle(angle + a.angle);
    }

    //
    public Angle sub(Angle a) {
        return new Angle(angle - a.angle);
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Angle)) return false;

        Angle a = (Angle) o;
        return angle == a.angle;
    }
}
