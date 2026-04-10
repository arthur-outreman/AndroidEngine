package com.example.engine.nodes;

import com.example.engine.dataTypes.Vect2;

public class SceneChangerButton2D extends Button2D {

    /* ================================
    VARIABLES
    ================================ */

    public int linkedSceneId;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public SceneChangerButton2D(Vect2 position, Vect2 size, String text, int linkedSceneId) {
        super(position, size, text);
        this.linkedSceneId = linkedSceneId;
    }


    /* ================================
    METHODES
    ================================ */

    //
    @Override
    public void onClicked() {
        root.changeSceneToId(linkedSceneId);
    }
}
