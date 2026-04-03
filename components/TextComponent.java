package com.example.engine.components;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.engine.Graphics;
import com.example.engine.dataTypes.Vect2;

public class TextComponent extends Component {

    /* ================================
    VARIABLES
    ================================ */

    Vect2 position;
    String text;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    public TextComponent(Vect2 position, String text) {
        this.position = position;
        this.text = text;
    }


    /* ================================
    RENDER
    ================================ */

    //
    @Override
    public void render(Canvas canvas, Graphics graphics) {
        if(parent==null) return;
        graphics.drawText(
                canvas, text,
                Vect2.SUM(position, parent.getGlobalTransform().position),
                32, Color.WHITE, Color.BLACK);
    }
}
