package com.example.androidengine;

import android.graphics.Canvas;

import androidx.annotation.Nullable;

public class CollisionObject2D extends Node2D {

    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    CollisionObject2D(@Nullable Node2D parent, float x, float y, float sX, float sY) {
        super(parent, x, y, sX, sY);
    }

    //
    CollisionObject2D(@Nullable Node2D parent, String name, float x, float y, float sX, float sY) {
        super(parent, name, x, y, sX, sY);
    }


    /* ================================
    METHODES
    ================================ */

    //
    public boolean collide(Node2D node) {
        if (node == null || node == this) return false;

        Vect2 localPos = getLocalPosition();
        Vect2 nodeLocalPos = node.getLocalPosition();

        if (localPos == null || nodeLocalPos == null) return false;
        if (node.size == null || size == null) return false;

        if (!(localPos.x < nodeLocalPos.x + node.size.x)) return false;
        if (!(localPos.x + size.x > nodeLocalPos.x)) return false;
        if (!(localPos.y < nodeLocalPos.y + node.size.y)) return false;
        return localPos.y + size.y > nodeLocalPos.y;
    }

    //
    private boolean collideWithTree(Node2D node) {
        if(node instanceof CollisionObject2D) if(((CollisionObject2D) node).collide(this)) return true;
        for(int i=0; i< node.children.size(); i++) {
            if(collideWithTree(node.getChild(i))) return true;
        }
        return false;
    }

    //
    @Override
    public String getInfo() {
        return "CollisionObject2D";
    }

    //
    public void moveAndCollide(Vect2 velocity) {
        position.add(velocity);
        if(collideWithTree(getTree())) {
            position.add(new Vect2(-velocity.x, -velocity.y));
        }
    }
}
