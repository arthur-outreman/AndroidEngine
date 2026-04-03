package com.example.engine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    /* ================================
    VARIABLES
    ================================ */

    private MainThread thread;
    private EngineCore game;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);
        setFocusable(true);

        game = new EngineCore(context);
    }


    /* ================================
    METHODES
    ================================ */

    //
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    //
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        boolean retry = true;

        thread.setRunning(false);

        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {}

    //
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        game.onTouchEvent(event);
        performClick();
        return true;
    }


    /* ================================
    UPDATE
    ================================ */

    //
    public void update(float deltaTime) {
        game.update(deltaTime);
    }


    /* ================================
    RENDER
    ================================ */

    //
    public void render(Canvas canvas) {
        game.render(canvas);
    }


    /* ================================
    CLEAN TREE
    ================================ */

    //
    public void cleanTree() {
        game.cleanTree();
    }
}