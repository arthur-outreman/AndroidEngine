package com.example.engine;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;

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
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);

    }


    /* ================================
    BACKGROUND
    ================================ */

    //
    public void background(Canvas canvas, int col) {
        canvas.drawColor(col);
    }


    /* ================================
    DRAW LINE
    ================================ */

    //
    public void drawLine(Canvas canvas, Vect2 p1, Vect2 p2, int col) {
        paint.setColor(col);
        canvas.drawLine(p1.x, p1.y, p2.x, p2.y, paint);
    }


    /* ================================
    DRAW PATH
    ================================ */

    //
    public void drawPath(Canvas canvas, Path p, int col) {
        paint.setColor(col);
        canvas.drawPath(p, paint);
    }


    /* ================================
    DRAW CIRCLE
    ================================ */

    //
    public void drawCircle(Canvas canvas, Vect2 position, float rayon, int col) {
        paint.setColor(col);
        canvas.drawCircle(position.x, position.y, rayon, paint);
    }


    /* ================================
    DRAW TEXT
    ================================ */

    //
    public void drawText(Canvas canvas, String str, Vect2 position, float textSize, int baseCol, int strokeCol) {
        paint.setTextSize(textSize);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(strokeCol);

        canvas.drawText(str, position.x, position.y+(textSize/3), paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(baseCol);

        canvas.drawText(str, position.x, position.y+(textSize/3), paint);
    }


    /* ================================
    DRAW BITMAP
    ================================ */

    //
    public void drawSprite(Canvas canvas, Bitmap sprite, Vect2 position) {
        canvas.drawBitmap(sprite, position.x, position.y, paint);
    }
}