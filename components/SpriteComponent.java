package com.example.engine.components;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.engine.Graphics;
import com.example.engine.dataTypes.Vect2;

public class SpriteComponent extends Component {

    /* ================================
    VARIABLES
    ================================ */

    public Bitmap[][] spriteFrames;
    private Vect2 frameCoord;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public SpriteComponent(Bitmap sprite, Vect2 framesNb) {

        frameCoord = Vect2.ZERO();

        int cols = (int) framesNb.x;
        int rows = (int) framesNb.y;

        spriteFrames = new Bitmap[rows][cols];

        int frameWidth = sprite.getWidth() / cols;
        int frameHeight = sprite.getHeight() / rows;

        for(int y = 0; y < rows; y++) {
            for(int x = 0; x < cols; x++) {
                int px = x * frameWidth;
                int py = y * frameHeight;

                spriteFrames[y][x] = Bitmap.createBitmap(sprite, px, py, frameWidth, frameHeight);
            }
        }
    }


    /* ================================
    RENDER
    ================================ */

    //
    @Override
    public void render(Canvas canvas, Graphics graphics) {
        if(parent != null) {
            graphics.drawSprite(canvas, spriteFrames[(int)frameCoord.x][(int)frameCoord.y], parent.getGlobalTransform().position);
        }
    }
}
