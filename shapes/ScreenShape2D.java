package com.example.engine.shapes;

import android.graphics.Canvas;

import com.example.engine.Graphics;
import com.example.engine.dataTypes.Rect2D;
import com.example.engine.dataTypes.Transform;
import com.example.engine.dataTypes.Vect2;

public class ScreenShape2D extends Shape2D {

    /* ================================
    VARIABLES
    ================================ */

    Rect2D rect;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public ScreenShape2D() {
        rect = new Rect2D(Vect2.ZERO(), new Vect2(Graphics.width, Graphics.height));
    }


    /* ================================
    METHODES
    ================================ */

    //
    @Override
    public Rect2D getApproximateRect(Transform t) {return rect;}

    //
    @Override
    public boolean pointOnShape(Transform t, Vect2 point) {return true;}


    /* ================================
    RENDER
    ================================ */

    //
    @Override
    public void render(Canvas canvas, Graphics graphics, Transform t, int col) {
        graphics.background(canvas, col);
    }
}
