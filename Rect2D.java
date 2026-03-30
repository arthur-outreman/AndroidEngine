package com.example.androidengine;

import android.graphics.Canvas;

import androidx.annotation.Nullable;

public class Rect2D extends Node2D {

    /* ================================
    VARIABLES
    ================================ */

    public int r, g, b;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    Rect2D(@Nullable Node2D parent, float x, float y, float sX, float sY, int r, int g, int b) {
        super(parent, x, y, sX, sY);
        this.r = r; this.g = g; this.b = b;
    }


    /* ================================
    DRAW
    ================================ */

    //
    @Override
    public void drawSelf(Canvas canvas) {
        Graphics.drawRect(canvas, getLocalPosition(), size, r, g, b);

        drawChildren(canvas);
    }
}
