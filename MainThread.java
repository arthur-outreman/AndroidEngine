package com.example.engine;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    /* ================================
    VARIABLES
    ================================ */

    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;

    private final int targetFPS = 60;
    private final double targetTime = 1_000_000_000.0 / targetFPS;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public MainThread(SurfaceHolder surfaceHolder, GameView gameView) {
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }


    /* ================================
    METHODES
    ================================ */

    //
    public void setRunning(boolean running) {
        this.running = running;
    }

    //
    @Override
    public void run() {
        long lastTime = System.nanoTime();

        while (running) {

            long now = System.nanoTime();
            float deltaTime = (now - lastTime) / 1_000_000_000f;
            lastTime = now;

            Canvas canvas = null;

            try {
                canvas = surfaceHolder.lockCanvas();

                synchronized (surfaceHolder) {
                    gameView.update(deltaTime);

                    if (canvas != null) {
                        gameView.render(canvas);
                    }

                    gameView.cleanTree();
                }

            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            long frameTime = System.nanoTime() - now;
            long waitTime = (long) targetTime - frameTime;

            if (waitTime > 0) {
                try {
                    sleep(waitTime / 1_000_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}