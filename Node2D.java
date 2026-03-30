package com.example.androidengine;

import android.graphics.Canvas;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Node2D {

    /* ================================
    VARIABLES
    ================================ */

    public Vect2 position, size;
    public @Nullable Node2D parent;
    public ArrayList<Node2D> children;
    private ArrayList<String> groups;
    public String name;
    public boolean isQueuedForDeletion = false;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    Node2D(@Nullable Node2D parent) {
        position = Vect2.ZERO();
        size = Vect2.ZERO();

        this.parent = parent;
        children = new ArrayList<>();

        groups = new ArrayList<>();
        name = "";
    }

    //
    Node2D(@Nullable Node2D parent, float x, float y, float sX, float sY) {
        position = new Vect2(x, y);
        size = new Vect2(sX, sY);

        this.parent = parent;
        children = new ArrayList<>();

        groups = new ArrayList<>();
        name = "";
    }

    //
    Node2D(@Nullable Node2D parent, String name, float x, float y, float sX, float sY) {
        position = new Vect2(x, y);
        size = new Vect2(sX, sY);

        this.parent = parent;
        children = new ArrayList<>();

        groups = new ArrayList<>();
        this.name = name;
    }


    /* ================================
    METHODES
    ================================ */

    //
    public Vect2 getLocalPosition() {
        Vect2 localPos = new Vect2(position.x, position.y);
        Node2D node = this;
        while(node.parent != null) {
            node = node.parent;
            localPos.x += node.position.x;
            localPos.y += node.position.y;
        }
        return localPos;
    }

    //
    public Node2D getTree() {
        Node2D root = this;
        while(root.parent != null) {
            root = root.parent;
        }
        return root;
    }

    //
    public void addChild(Node2D child) {
        child.parent = this;
        children.add(child);
    }

    //
    public Node2D getChild(int i) {
        if(children.size() > i) {
            return children.get(i);
        } else return null;
    }

    //
    public void putInGroup(String groupName) {
        for(int i=0; i< groups.size(); i++) {
            if(groups.get(i).equals(groupName)) return;
        }
        groups.add(groupName);
    }

    //
    public boolean isInGroup(String groupName) {
        for(int i=0; i< groups.size(); i++) {
            if(groups.get(i).equals(groupName)) return true;
        }
        return false;
    }

    //
    public void removeChild(Node2D child) {
        for(int i=0; i< children.size(); i++) {
            if(children.get(i) == child) {
                children.remove(i);
                return;
            }
        }
    }

    //
    public void queueFree() {
        isQueuedForDeletion = true;
        if(parent != null) {
            parent.removeChild(this);
        }
        this.parent = null;
    }

    //
    public void moveToward(Node2D node, Vect2 speed) {
        Vect2 velocity = Vect2.diff(getLocalPosition(), node.getLocalPosition());
        velocity.clampMagnitude(speed);
        this.position.add(velocity);
    }

    //
    public void moveAndSlide(Vect2 velocity) {
        this.position.add(velocity);
    }

    //
    public int getChildPosition(Node2D node) {
        for(int i=0; i<children.size(); i++) {
            if(children.get(i) == node) return i;
        }
        return 0;
    }

    //
    public int getPositionInTree() {
        if(parent==null) return 0;
        return parent.getChildPosition(this);
    }

    //
    public String getInfo() {
        return "Node2D";
    }

    //
    public void update() {}


    /* ================================
    HANDLER & SIGNAL
    ================================ */

    //
    public void signal(String signal, String nodeName) {}

    //
    public void signal(String signal, String nodeName, Node2D node) {}

    //
    public boolean handleEvent(MotionEvent event) {
        for(int i=0; i<children.size(); i++) {
            if(children.get(i).handleEvent(event)) return true;
        }
        return false;
    }


    /* ================================
    DRAW
    ================================ */

    //
    public void drawChildren(Canvas canvas) {
        for(int i=0; i<children.size(); i++) {
            children.get(i).drawSelf(canvas);
        }
    }

    //
    public void drawSelf(Canvas canvas) {
        update();

        if(Graphics.debug) {
            Vect2 localPositionInTree = getLocalPosition();
            localPositionInTree.add(new Vect2(0, 25*getPositionInTree()));
            Graphics.drawText(canvas, this.getInfo(), localPositionInTree, 20);
        }

        drawChildren(canvas);
    }
}
