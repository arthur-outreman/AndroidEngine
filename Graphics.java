package com.example.engine;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.example.engine.dataTypes.Vect2;

public class Graphics {

    /* ================================
    VARIABLES
    ================================ */

    public static int desired_width = 1600;
    public static int desired_height = 720;

    public static int width = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static int height = Resources.getSystem().getDisplayMetrics().heightPixels;

    public static float width_ratio = (float)(width) / (float)(desired_width);
    public static float height_ratio = (float)(height) / (float)(desired_height);
    public static float ratio = (width_ratio + height_ratio)/2;

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
    public void drawLine(Canvas canvas, Vect2 p1, Vect2 p2, int size, int col) {
        paint.setColor(col);
        paint.setStrokeWidth(size * ratio);
        canvas.drawLine(p1.x * width_ratio, p1.y * height_ratio, p2.x * width_ratio, p2.y * height_ratio, paint);
    }


    /* ================================
    DRAW PATH
    ================================ */

    //
    public void drawPolygon(Canvas canvas, Vect2[] points, int col) {
        paint.setColor(col);

        Path path = new Path();
        path.moveTo(points[0].x * width_ratio, points[0].y * height_ratio);

        for (int i=1; i< points.length; i++) {
            path.lineTo(points[i].x * width_ratio, points[i].y * height_ratio);
        }
        path.close();

        canvas.drawPath(path, paint);
    }


    /* ================================
    DRAW CIRCLE
    ================================ */

    //
    public void drawCircle(Canvas canvas, Vect2 position, float rayon, int col) {
        paint.setColor(col);
        canvas.drawCircle(position.x * width_ratio, position.y * width_ratio, rayon * ratio, paint);
    }


    /* ================================
    DRAW TEXT
    ================================ */

    //
    public void drawText(Canvas canvas, String str, Vect2 position, float textSize, int baseCol, int strokeCol) {
        paint.setTextSize(textSize);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(strokeCol);

        canvas.drawText(str, position.x * width_ratio, (position.y+(textSize/3)) * height_ratio, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(baseCol);

        canvas.drawText(str, position.x * width_ratio, (position.y+(textSize/3)) * height_ratio, paint);
    }


    /* ================================
    DRAW BITMAP
    ================================ */

    //
    public void drawSprite(Canvas canvas, Bitmap sprite, Vect2 position) {
        canvas.drawBitmap(sprite, position.x * width_ratio, position.y * height_ratio, paint);
    }


    /* ================================
    DRAW PATH2D
    ================================ */

    //
    public void drawPath2D(Canvas canvas, Vect2[] points, int size, int col) {
        for(int i=1; i< points.length; i++) {
            drawLine(canvas, points[i-1], points[i], size, col);
        }
    }
}