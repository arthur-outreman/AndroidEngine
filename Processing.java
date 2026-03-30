package com.example.androidengine;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class Processing {

    /* ================================
    VARIABLES
    ================================ */

    private Node2D[] scenes;
    private int scene_actual;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    Processing() {
        setup();
    }


    /* ================================
    METHODES
    ================================ */

    //
    private void setup() {

        // SCENES SETUP
        scenes = new Node2D[1];
        scenes[0] = new Node2D(null);
        scene_actual = 0;

        // ACTUAL SETUP
    }

    //
    public void setScene(int sceneId) {
        scene_actual = sceneId;
    }


    /* ================================
    EVENT
    ================================ */

    //
    public void onTouchEvent(MotionEvent event) {
        scenes[scene_actual].handleEvent(event);
    }


    /* ================================
    DRAW
    ================================ */

    //
    public void draw(Canvas canvas) {
        Graphics.background(canvas, 0);
        scenes[scene_actual].drawSelf(canvas);
    }
}