package com.example.androidengine;

public class SceneChangerButton2D extends Button2D {

    /* ================================
    VARIABLES
    ================================ */

    public int sceneId;
    Processing gameInstance;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    SceneChangerButton2D(Processing gameInstance, Node2D parent, float x, float y, float sX, float sY, String text, int sceneId) {
        super(parent, x, y, sX, sY, text);
        this.gameInstance = gameInstance;
        this.sceneId = sceneId;
    }


    /* ================================
    METHODES
    ================================ */

    //
    @Override
    public void onClicked() {
        clickTimer = 15;
        gameInstance.setScene(sceneId);
    }
}
