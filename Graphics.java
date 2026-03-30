package com.example.androidengine;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public final class Graphics {

    /* ================================
    VARIABLES
    ================================ */
    public static int width = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static int height = Resources.getSystem().getDisplayMetrics().heightPixels;
    public static boolean debug = true;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    private Graphics() {
        // Class non instantiable
    }


    /* ================================
    BACKGROUND
    ================================ */

    //
    public static void background(Canvas canvas, int r, int g, int b) {
        canvas.drawColor(Color.rgb(r, g, b));
    }

    //
    public static void background(Canvas canvas, int col) {
        background(canvas, col, col, col);
    }


    /* ================================
    DRAW RECT
    ================================ */

    //
    public static void drawRect(Canvas canvas, float x, float y, float sX, float sY, int r, int g, int b, int a) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(r, g, b));
        paint.setAlpha(a);

        canvas.drawRect(x, y, x+sX, y+sY, paint);
    }

    //
    public static void drawRect(Canvas canvas, float x, float y, float sX, float sY, int r, int g, int b) {
        drawRect(canvas, x, y, sX, sY, r, g, b, 255);
    }

    //
    public static void drawRect(Canvas canvas, float x, float y, float sX, float sY, int col) {
        drawRect(canvas, x, y, sX, sY, col, col, col, 255);
    }

    //
    public static void drawRect(Canvas canvas, Vect2 position, Vect2 size, int r, int g, int b, int a) {
        drawRect(canvas, position.x, position.y, size.x, size.y, r, g, b, a);
    }

    //
    public static void drawRect(Canvas canvas, Vect2 position, Vect2 size, int r, int g, int b) {
        drawRect(canvas, position.x, position.y, size.x, size.y, r, g, b, 255);
    }

    //
    public static void drawRect(Canvas canvas, Vect2 position, Vect2 size, int col) {
        drawRect(canvas, position.x, position.y, size.x, size.y, col, col, col, 255);
    }


    /* ================================
    DRAW CIRCLE
    ================================ */

    //
    public static void drawCircle(Canvas canvas, float x, float y, float rayon, int r, int g, int b, int a) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(r, g, b));
        paint.setAlpha(a);

        canvas.drawCircle(x, y, rayon, paint);
    }

    //
    public static void drawCircle(Canvas canvas, float x, float y, float rayon, int r, int g, int b) {
        drawCircle(canvas, x, y, rayon, r, g, b, 255);
    }

    //
    public static void drawCircle(Canvas canvas, float x, float y, int rayon, int col) {
        drawCircle(canvas, x, y, rayon, col, col, col, 255);
    }


    /* ================================
    DRAW LINE
    ================================ */

    //
    public static void drawLine(Canvas canvas, float x1, float y1, float x2, float y2, int r, int g, int b) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(r, g, b));

        canvas.drawLine(x1, y1, x2, y2, paint);
    }

    //
    public static void drawLine(Canvas canvas, float x1, float y1, float x2, float y2, int col) {
        drawLine(canvas, x1, y1, x2, y2, col, col, col);
    }

    //
    public static void drawLine(Canvas canvas, float x1, float y1, float x2, float y2) {
        drawLine(canvas, x1, y1, x2, y2, 0, 0, 0);
    }


    /* ================================
    DRAW TEXT
    ================================ */

    //
    public static void drawText(Canvas canvas, String str, float x, float y, int textSize, int r, int g, int b, int strokeSize, int rS, int gS, int bS) {
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeSize);
        paint.setColor(Color.rgb(rS, gS, bS));

        canvas.drawText(str, x, y+((float) textSize /3), paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(r, g, b));

        canvas.drawText(str, x, y+((float) textSize /3), paint);
    }

    //
    public static void drawText(Canvas canvas, String str, float x, float y, int textSize) {
        drawText(canvas, str, x, y, textSize, 255, 255, 255, 10, 0, 0, 0);
    }

    //
    public static void drawText(Canvas canvas, String str, Vect2 position, int textSize) {
        drawText(canvas, str, position.x, position.y, textSize, 255, 255, 255, 10, 0, 0, 0);
    }


    /* ================================
    DRAW BITMAP
    ================================ */

    //
    public static void drawBitmap(Canvas canvas, float x, float y, Bitmap sprite) {
        canvas.drawBitmap(sprite, x, y, null);
    }

    //
    public static void drawBitmap(Canvas canvas, Vect2 position, Bitmap sprite) {
        canvas.drawBitmap(sprite, position.x, position.y, null);
    }

    //
    public static void drawBitmapFrame(Canvas canvas, Vect2 position, Bitmap sprite, Vect2 frame, Vect2 frameSize) {
        Rect src = new Rect((int)frame.x, (int)frame.y, (int)(frame.x+frameSize.x), (int)(frame.y+frameSize.y));
        Rect dst = new Rect((int)position.x, (int)position.y, (int)(position.x+frameSize.x), (int)(position.y+frameSize.y));

        canvas.drawBitmap(sprite, src, dst, null);
    }
}

