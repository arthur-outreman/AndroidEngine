package com.example.androidengine;

import android.view.MotionEvent;

public class ClickDetector2D extends Node2D {

    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    ClickDetector2D(Node2D parent, float x, float y, float sX, float sY) {
        super(parent, x, y, sX, sY);
    }

    //
    ClickDetector2D(Node2D parent, String name, float x, float y, float sX, float sY) {
        super(parent, name, x, y, sX, sY);
    }


    /* ================================
    METHODES
    ================================ */

    //
    public void onClicked() {}

    //
    public boolean isClicked(float x, float y) {
        Vect2 localPos = getLocalPosition();

        if(!(localPos.x < x)) return false;
        if(!(localPos.x + size.x > x)) return false;
        if(!(localPos.y < y)) return false;
        return localPos.y + size.y > y;
    }

    //
    @Override
    public String getInfo() {
        return "ClickDetector2D";
    }


    /* ================================
    EVENT
    ================================ */

    //
    public boolean handleEvent(MotionEvent event) {
        if(isClicked(event.getX(), event.getY())) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                if(parent!=null) parent.signal("ClickDetector2DClicked", name);
                onClicked();
                return true;
            }
        } else {
            for (int i = 0; i < children.size(); i++) {
                if (children.get(i).handleEvent(event)) return true;
            }
        }
        return false;
    }
}
