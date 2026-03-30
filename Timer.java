package com.example.androidengine;

import android.graphics.Canvas;

public class Timer extends Node2D {

    /* ================================
    VARIABLES
    ================================ */

    public int time;
    public boolean started;

    // Parameters
    public int maxTime;
    public boolean oneShot;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    Timer(Node2D parent, int maxTime, boolean autoStart, boolean oneShot) {
        super(parent, 0, 0, 0, 0);

        time = maxTime;
        started = autoStart;

        this.maxTime = maxTime;
        this.oneShot = oneShot;
    }

    //
    Timer(Node2D parent, String name, int maxTime, boolean autoStart, boolean oneShot) {
        super(parent, name, 0, 0, 0, 0);

        time = maxTime;
        started = autoStart;

        this.maxTime = maxTime;
        this.oneShot = oneShot;
    }


    /* ================================
    METHODES
    ================================ */

    //
    public void start() {
        started = true;
        time = maxTime;
    }

    //
    @Override
    public String getInfo() {
        return "Timer";
    }

    //
    @Override
    public void update() {
        if(started) {
            if(--time <= 0) {
                started = !oneShot;
                if(parent!=null) parent.signal("TimerTimeOut", name);
            }
        }
    }
}
