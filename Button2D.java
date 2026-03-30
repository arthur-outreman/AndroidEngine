package com.example.androidengine;

import android.graphics.Canvas;

public class Button2D extends ClickDetector2D {

    /* ================================
    VARIABLES
    ================================ */

    public String text;
    public int clickTimer;

    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    Button2D(Node2D parent, float x, float y, float sX, float sY, String text) {
        super(parent, x, y, sX, sY);
        this.text = text;
        clickTimer = 0;
    }

    //
    Button2D(Node2D parent, String name, float x, float y, float sX, float sY, String text) {
        super(parent, name, x, y, sX, sY);
        this.text = text;
        clickTimer = 0;
    }


    /* ================================
    METHODES
    ================================ */

    //
    @Override
    public void onClicked() {
        clickTimer = 15;
    }


    /* ================================
    DRAW
    ================================ */

    //
    @Override
    public void drawSelf(Canvas canvas) {
        if(clickTimer>0) clickTimer--;

        Vect2 localPos = getLocalPosition();
        Graphics.drawRect(canvas, localPos, size, (clickTimer>0)?100:30);
        Graphics.drawText(canvas, text, localPos.x+(size.x/2), localPos.y+(size.y/2), (int)(size.y/2));

        drawChildren(canvas);
    }
}
