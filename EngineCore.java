package com.example.engine;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.engine.components.VisualShapeComponent;
import com.example.engine.dataTypes.PolygonShape2D;
import com.example.engine.dataTypes.Vect2;
import com.example.engine.nodes.Node2D;

public class EngineCore {

    /* ================================
    VARIABLES
    ================================ */

    // VARIABLES ENGINE
    public Graphics graphics = new Graphics();

    // OTHER
    //

    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    EngineCore() {
        setup();
    }


    /* ================================
    METHODES
    ================================ */

    //
    private void setup() {
        //
    }


    /* ================================
    EVENT
    ================================ */

    //
    public void onTouchEvent(MotionEvent event) {
        //
    }


    /* ================================
    UPDATE
    ================================ */

    //
    public void update(float delta) {
        //
    }


    /* ================================
    RENDER
    ================================ */

    //
    public void render(Canvas canvas) {
        graphics.background(canvas, 255);
        //
    }
}