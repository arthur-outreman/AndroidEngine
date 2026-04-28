package com.example.engine.dataTypes;

import android.graphics.Canvas;

import com.example.engine.Graphics;

public class Path2D {

    /* ================================
    VARIABLES
    ================================ */

    public Vect2[] points;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public Path2D(Vect2[] points) {
        this.points = points;
    }


    /* ================================
    RENDER
    ================================ */

    //
    public void render(Canvas canvas, Graphics graphics, int size, int col) {
        graphics.drawPath2D(canvas, points, size, col);
    }
}