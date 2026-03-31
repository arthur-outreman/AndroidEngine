package com.example.engine.dataTypes;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Contract;

public class Angle {

    /* ================================
    VARIABLES
    ================================ */

    public float angle;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    Angle(float angleDegree) {
        this.angle = angleDegree;
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


    /* ================================
    METHODES
    ================================ */

    //
    private void normalize() {
        if(angle < -360f) angle += 360f;
        if(angle > 360f) angle -= 360f;
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
