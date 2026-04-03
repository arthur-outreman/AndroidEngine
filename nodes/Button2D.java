package com.example.engine.nodes;

import android.graphics.Color;

import com.example.engine.components.ClickDetectorComponent;
import com.example.engine.components.TextComponent;
import com.example.engine.components.TimerComponent;
import com.example.engine.components.VisualComponent;
import com.example.engine.dataTypes.Vect2;
import com.example.engine.shapes.Shape2D;

public class Button2D extends Node2D {

    /* ================================
    VARIABLES
    ================================ */

    public Vect2 size;
    public String text;
    TimerComponent clickTimer;
    VisualComponent visual;
    public int baseColor = Color.GRAY;
    public int clickColor = Color.LTGRAY;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public Button2D(Vect2 position, Vect2 size, String text) {
        super(position);

        this.size = size;
        this.text = text;

        Shape2D shape = Shape2D.RECT(size);

        clickTimer = new TimerComponent(0.3f, false, true);
        visual = new VisualComponent(shape, baseColor);

        addComponent(clickTimer);
        addComponent(visual);
        addComponent(new TextComponent(new Vect2(size.x/2, size.y/2), text));
        addComponent(new ClickDetectorComponent(shape));
    }


    /* ================================
    SIGNAL
    ================================ */

    //
    @Override
    public void signal(String signal, String name) {
        if(signal.equals("TimerTimeOut")) {
            visual.col = baseColor;
            sendSignal("ButtonClicked");
        } else if(signal.equals("ClickDetectorClicked")) {
            visual.col = clickColor;
            clickTimer.start();
        }
    }
}
