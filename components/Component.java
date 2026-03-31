package com.example.engine.components;

import android.graphics.Canvas;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

import com.example.engine.nodes.Node2D;

public class Component {

    /* ================================
    VARIABLES
    ================================ */

    public @Nullable Node2D parent = null;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    Component() {}


    /* ================================
    EVENT
    ================================ */

    //
    public boolean onTouchEvent(MotionEvent event) {return false;}


    /* ================================
    UPDATE
    ================================ */

    //
    public void update(float deltaTime) {}


    /* ================================
    RENDER
    ================================ */

    //
    public void render(Canvas canvas) {}
}
