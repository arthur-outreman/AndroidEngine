package com.example.androidengine;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    public static Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    private int targetFPS = 60;

    public MainThread(SurfaceHolder surfaceHolder, GameView gameView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;

    }

    public void setRunning(boolean isRunning) {
        running = isRunning;
    }

    @Override
    public void run() {
        long targetTime = 1_000_000_000 / targetFPS;

        while (running) {
            long startTime = System.nanoTime();
            Canvas canvas = null;

            try {
                canvas = surfaceHolder.lockCanvas();
                if (canvas != null) {
                    synchronized (surfaceHolder) {
                        gameView.draw(canvas);
                    }
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            long elapsed = System.nanoTime() - startTime;
            long waitTime = targetTime - elapsed;

            if (waitTime > 0) {
                try {
                    Thread.sleep(waitTime / 1_000_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}