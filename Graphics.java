package com.example.engine;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.engine.dataTypes.Vect2;

public class Graphics {

    /* ================================
    VARIABLES
    ================================ */
    public static int width = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static int height = Resources.getSystem().getDisplayMetrics().heightPixels;

    public Paint paint = new Paint();


    /* ================================
    CONSTRUCTEURS
    ================================ */

    public Graphics() {
        paint.setStrokeWidth(10);
    }


    /* ================================
    BACKGROUND
    ================================ */

    //
    public void background(Canvas canvas, int r, int g, int b) {
        canvas.drawColor(Color.rgb(r, g, b));
    }

    //
    public void background(Canvas canvas, int col) {
        background(canvas, col, col, col);
    }


    /* ================================
    DRAW LINE
    ================================ */

    //
    public void drawLine(Canvas canvas, Vect2 p1, Vect2 p2, int r, int g, int b) {
        paint.setColor(Color.rgb(r, g, b));

        canvas.drawLine(p1.x, p1.y, p2.x, p2.y, paint);
    }
}