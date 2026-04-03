package com.example.engine.nodes;

import androidx.annotation.Nullable;

import com.example.engine.components.CollisionComponent;
import com.example.engine.dataTypes.Vect2;
import com.example.engine.shapes.Shape2D;

import java.util.ArrayList;

public class Area2D extends Node2D {

    /* ================================
    VARIABLES
    ================================ */

    private CollisionComponent collider;
    private ArrayList<CollisionObject2D> bodyInArea = new ArrayList<>();
    private ArrayList<Area2D> shapeInArea = new ArrayList<>();


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public Area2D(Shape2D shape) {
        super();
        collider = new CollisionComponent(shape);
        addComponent(collider);
    }

    //
    public Area2D(Vect2 position, Shape2D shape) {
        super(position);
        collider = new CollisionComponent(shape);
        addComponent(collider);
    }


    /* ================================
    METHODES
    ================================ */

    // Body

    //
    public boolean containBody(CollisionObject2D node) {
        return bodyInArea.contains(node);
    }

    //
    public @Nullable CollisionObject2D firstBodyInGroup(String groupName) {
        for(int i=0; i<bodyInArea.size(); i++) {
            if(bodyInArea.get(i).isInGroup(groupName)) return bodyInArea.get(i);
        }
        return null;
    }


    /* ================================
    COLLISIONS
    ================================ */

    //
    public void collideWithTree(Node2D node) {
        if(node == null) return;
        if(node instanceof CollisionObject2D && !containBody((CollisionObject2D) node)) {
            CollisionComponent other = ((CollisionObject2D) node).collider;
            if(other != null && collider.overlap(other)) {
                sendSignal("AreaBodyEntered");
                bodyInArea.add((CollisionObject2D) node);
            }
        } else if(node instanceof Area2D && node != this) {
            CollisionComponent other = ((Area2D) node).collider;
            if(other != null && collider.overlap(other)) {
                sendSignal("CollidedWithArea", node);
            }
        }
        for(int i = 0; i < node.children.size(); i++) {
            collideWithTree(node.children.get(i));
        }
    }


    /* ================================
    UPDATE
    ================================ */

    //
    @Override
    public void updateSelf(float delta) {
        for(int i=0; i<bodyInArea.size(); i++) {
            if(!collider.overlap(bodyInArea.get(i).collider)) {
                sendSignal("AreaShapeExited");
                bodyInArea.remove(i--);
            }
        }
    }
}
