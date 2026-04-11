package com.example.engine.nodes;

import android.util.Log;

import com.example.engine.components.CollisionComponent;
import com.example.engine.dataTypes.Vect2;
import com.example.engine.shapes.Shape2D;

public class CollisionObject2D extends Node2D {

    /* ================================
    VARIABLES
    ================================ */

    public CollisionComponent collider;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public CollisionObject2D(Vect2 position, Shape2D shape) {
        super(position);
        collider = new CollisionComponent(shape);
        addComponent(collider);
    }


    /* ================================
    COLLISIONS
    ================================ */

    //
    public boolean collideWithTree(Node2D node) {
        if(node == null) return false;
        if(node instanceof CollisionObject2D && node != this) {
            CollisionComponent other = ((CollisionObject2D) node).collider;
            if(other != null && collider.overlap(other)) {
                return true;
            }
        }
        for(Node2D child : node.children) {
            if(collideWithTree(child)) return true;
        }
        return false;
    }
}
