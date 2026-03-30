package com.example.androidengine;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Sprite2D extends Node2D {

    /* ================================
    VARIABLES
    ================================ */

    private Bitmap sprite;
    private boolean spriteSheet;
    private Vect2 frameSize, frame;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    Sprite2D(Node2D parent, float x, float y, float sX, float sY, Bitmap sprite) {
        super(parent, x, y, sX, sY);
        this.sprite = Bitmap.createScaledBitmap(sprite, (int)sX, (int)sY, false);

        spriteSheet = false;
    }

    //
    Sprite2D(Node2D parent, float x, float y, float sX, float sY, Bitmap sprite, Vect2 frameSize) {
        super(parent, x, y, sX, sY);
        this.sprite = Bitmap.createScaledBitmap(sprite, (int)sX, (int)sY, false);

        spriteSheet = true;
        frame = Vect2.ZERO();
        this.frameSize = frameSize;
    }


    /* ================================
    DRAW
    ================================ */

    //
    @Override
    public void drawSelf(Canvas canvas) {
        if(spriteSheet) {
            Graphics.drawBitmapFrame(canvas, getLocalPosition(), sprite, frame, frameSize);
        } else {
            Graphics.drawBitmap(canvas, getLocalPosition(), sprite);
        }
        drawChildren(canvas);
    }
}
