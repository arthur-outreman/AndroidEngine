package com.example.engine.components;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

import com.example.engine.Graphics;
import com.example.engine.nodes.Node2D;

public class Component {

    /* ================================
    VARIABLES
    ================================ */

    public @Nullable Node2D parent = null;
    public String name = "";


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    Component() {}

    //
    public void onAttach() {}

    /* ================================
    SIGNAL
    ================================ */

    //
    public void signal(String signal) {
        if(parent != null) parent.signal(signal, name);
    }


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
    public void render(Canvas canvas, Graphics graphics) {}
}
