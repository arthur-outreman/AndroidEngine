package com.example.engine.nodes;

import com.example.engine.components.VisualComponent;
import com.example.engine.dataTypes.Vect2;
import com.example.engine.shapes.Shape2D;

public class CharacterBody2D extends CollisionObject2D {

    /* ================================
    VARIABLES
    ================================ */

    public Vect2 velocity = Vect2.ZERO();


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public CharacterBody2D(Vect2 position, Shape2D shape) {
        super(position, shape);
    }


    /* ================================
    METHODES
    ================================ */

    //
    public void moveAndCollide() {
        transform.position = transform.position.add(velocity);
        if(collideWithTree(getTree())) transform.position = transform.position.sub(velocity);
    }
}
