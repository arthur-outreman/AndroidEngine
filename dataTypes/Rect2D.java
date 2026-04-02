package com.example.engine.dataTypes;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Contract;

public class Rect2D {

    /* ================================
    VARIABLES
    ================================ */

    public Vect2 position, size;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public Rect2D(Vect2 position, Vect2 size) {
        this.position = position;
        this.size = size;
    }


    /* ================================
    NEW RECT
    ================================ */

    //
    @NonNull
    @Contract(" -> new")
    public static Rect2D ZERO() {
        return new Rect2D(Vect2.ZERO(), Vect2.ZERO());
    }


    /* ================================
    METHODES
    ================================ */

    //
    public boolean overlap(Rect2D rect) {
        if(!(position.x < rect.position.x + rect.size.x)) return false;
        if(!(position.x + size.x > rect.position.x)) return false;
        if(!(position.y < rect.position.y + rect.size.y)) return false;
        return position.y + size.y > rect.position.y;
    }

    //
    public Vect2 findCenterOfMass() {
        return new Vect2(position.x + size.x/2, position.y + size.y/2);
    }
}
