package com.example.androidengine;

import android.graphics.Canvas;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Area2D extends CollisionObject2D {

    /* ================================
    VARIABLES
    ================================ */

    private ArrayList<Node2D> bodyInArea;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    Area2D(Node2D parent, float x, float y, float sX, float sY) {
        super(parent, x, y, sX, sY);

        bodyInArea = new ArrayList<>();
    }

    //
    Area2D(Node2D parent, String name, float x, float y, float sX, float sY) {
        super(parent, name, x, y, sX, sY);

        bodyInArea = new ArrayList<>();
    }


    /* ================================
    METHODES
    ================================ */

    //
    public ArrayList<Node2D> getBodyInArea() {
        return bodyInArea;
    }

    //
    public @Nullable Node2D getBody(int index) {
        if(index>=0 && index<bodyInArea.size()) return bodyInArea.get(index);
        return null;
    }

    //
    public @Nullable Node2D getFirstBodyInGroup(String groupName) {
        for(int i=0; i<bodyInArea.size(); i++) {
            if(bodyInArea.get(i).isInGroup(groupName)) return bodyInArea.get(i);
        }
        return null;
    }

    //
    private boolean contain(Node2D node) {
        for(int i=0; i<bodyInArea.size(); i++) {
            if(bodyInArea.get(i) == node) return true;
        }
        return false;
    }

    //
    @Override
    public boolean collide(Node2D node) {
        if(super.collide(node)) {
            if(!contain(node)) {
                if(parent!=null) parent.signal("Area2DBodyEntered", name, node);
                bodyInArea.add(node);
            }
        }
        return false;
    }


    //
    @Override
    public void update() {
        for(int i=0; i<bodyInArea.size(); i++) {
            Node2D node = bodyInArea.get(i);

            if(node == null || node.isQueuedForDeletion || !super.collide(node)) {
                if(parent != null) parent.signal("Area2DBodyExited", name, node);
                bodyInArea.remove(i--);
            }
        }
    }


    /* ================================
    DRAW
    ================================ */

    //
    @Override
    public void drawSelf(Canvas canvas) {
        update();

        if(Graphics.debug) {
            Graphics.drawRect(canvas, getLocalPosition(), size, 180, 180, 255, 30);
        }

        drawChildren(canvas);
    }
}
