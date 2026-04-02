package com.example.engine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import com.example.engine.dataTypes.Vect2;
import com.example.engine.nodes.Node2D;

public class EngineCore {

    /* ================================
    VARIABLES
    ================================ */

    // VARIABLES ENGINE
    public Graphics graphics = new Graphics();

    // SCENES
    private Node2D[] scenesList;
    public int actualScene = 0;

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

        // SCENES SETUP
        scenesList = new Node2D[1]; // <- Nb of scenes
        for(int i=0; i<scenesList.length; i++) scenesList[i] = new Node2D(new Vect2(200f, 400f));

        // OTHER
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
        scenesList[actualScene].update(delta);
        //
    }


    /* ================================
    RENDER
    ================================ */

    //
    public void render(Canvas canvas) {
        graphics.background(canvas, Color.WHITE);
        scenesList[actualScene].render(canvas, graphics);
        //
    }
}