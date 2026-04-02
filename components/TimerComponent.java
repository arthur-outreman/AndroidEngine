package com.example.engine.components;

public class TimerComponent extends Component {

    /* ================================
    VARIABLES
    ================================ */

    private float time, maxTime;
    private boolean running, oneShot;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    TimerComponent(float maxTime, boolean autoStart, boolean oneShot) {
        this.time = maxTime;
        this.maxTime = maxTime;
        this.running = autoStart;
        this.oneShot = oneShot;
    }


    /* ================================
    METHODES
    ================================ */

    //
    public void start() {running = true;}

    //
    public boolean is_started() {return running;}

    //
    public boolean is_stopped() {return !running;}


    /* ================================
    UPDATE
    ================================ */

    //
    public void update(float deltaTime) {
        if(running) time-=deltaTime;
        if(time <= 0f) {
            signal("TimerTimeOut");
            time = maxTime;
            running = oneShot;
        }
    }
}
