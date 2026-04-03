package com.example.engine.components;

import com.example.engine.dataTypes.Path2D;
import com.example.engine.dataTypes.Vect2;

public class PathFollowerComponent extends Component {

    /* ================================
    VARIABLES
    ================================ */

    public Path2D path;
    public float speed;
    public int index = 0;
    public boolean loop;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public PathFollowerComponent(Path2D path, float speed, boolean loop) {
        this.path = path;
        this.speed = speed;
        this.loop = loop;
    }


    /* ================================
    UPDATE
    ================================ */

    @Override
    public void update(float deltaTime) {

        if(parent == null || path == null) return;
        if(path.points == null || path.points.length == 0) return;

        Vect2 pos = parent.getGlobalTransform().position;
        Vect2 target = path.points[index];

        Vect2 dir = Vect2.TRANSLATION(pos, target);
        float dist = dir.length();

        float moveStep = speed * deltaTime;

        if(dist <= moveStep) {
            index++;

            if(loop) {
                index %= path.points.length;
            } else if(index >= path.points.length) {
                signal("PathFollowerEnded");
                return;
            }

            target = path.points[index];
            dir = Vect2.TRANSLATION(pos, target);
        }

        dir.normalize();

        parent.transform.position = parent.transform.position.add(
                new Vect2(dir.x * moveStep, dir.y * moveStep)
        );
    }
}
