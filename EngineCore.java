package com.example.engine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import com.example.engine.components.SoundPlayerComponent;
import com.example.engine.dataTypes.Vect2;
import com.example.engine.nodes.Node2D;

public class EngineCore {

    /* ================================
    VARIABLES
    ================================ */

    // VARIABLES ENGINE
    private Graphics graphics = new Graphics();
    public Context context;

    // SCENES
    private Node2D[] scenesList;
    public int actualScene = 0;

    // OTHER
    //


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    EngineCore(Context context) {
        this.context = context;
        setup();
    }


    /* ================================
    METHODES
    ================================ */

    //
    private void setup() {

        // SCENES SETUP
        scenesList = new Node2D[1]; // <- Nb of scenes
        for(int i=0; i<scenesList.length; i++) scenesList[i] = new Node2D(this);

        // OTHER
        //
    }

    //
    public void changeSceneToId(int id) {if(id<scenesList.length) actualScene = id;}


    /* ================================
    EVENT
    ================================ */

    //
    public void onTouchEvent(MotionEvent event) {
        scenesList[actualScene].onTouchEvent(event);
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


    /* ================================
    CLEAN TREE
    ================================ */

    //
    public void cleanTree() {
        scenesList[actualScene].cleanTree();
    }
}