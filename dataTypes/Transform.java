package com.example.engine.dataTypes;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Contract;

public class Transform {

    /* ================================
    VARIABLES
    ================================ */

    public Vect2 position;
    public Angle rotation;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public Transform(Vect2 position, Angle rotation) {
        this.position = position;
        this.rotation = rotation;
    }


    /* ================================
    NEW TRANSFORM
    ================================ */

    //
    @NonNull
    @Contract(" -> new")
    public static Transform ZERO() {
        return new Transform(Vect2.ZERO(), Angle.ZERO());
    }

    //
    @NonNull
    @Contract("_, _ -> new")
    public static Transform SUM(Transform t1, Transform t2) {
        return new Transform(
                Vect2.SUM(t1.position, t2.position),
                Angle.SUM(t1.rotation, t2.rotation)
        );
    }

    //
    @NonNull
    @Contract("_ -> new")
    public static Transform CLONE(Transform t) {
        return new Transform(Vect2.CLONE(t.position), Angle.CLONE(t.rotation));
    }


    /* ================================
    METHODES
    ================================ */

    //
    public void add(Transform t) {
        position = position.add(t.position);
        rotation = rotation.add(t.rotation);
    }

    //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transform)) return false;

        Transform t = (Transform) o;
        return position.equals(t.position) & rotation.equals(t.rotation);
    }
}
