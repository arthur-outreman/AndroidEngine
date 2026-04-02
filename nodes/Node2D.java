package com.example.engine.nodes;

import android.graphics.Canvas;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

import com.example.engine.Graphics;
import com.example.engine.components.Component;
import com.example.engine.dataTypes.Angle;
import com.example.engine.dataTypes.Transform;
import com.example.engine.dataTypes.Vect2;

import java.util.ArrayList;

public class Node2D {

    /* ================================
    VARIABLES
    ================================ */

    private boolean inQueueToDeletion = false;
    public @Nullable Node2D parent = null;
    public ArrayList<Node2D> children;
    public ArrayList<Component> components;
    public ArrayList<String> groups;
    public Transform transform;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public Node2D() {
        children = new ArrayList<>();
        components = new ArrayList<>();
        groups = new ArrayList<>();

        transform = Transform.ZERO();

    }

    //
    public Node2D(Vect2 position) {
        children = new ArrayList<>();
        components = new ArrayList<>();
        groups = new ArrayList<>();

        transform = new Transform(position, Angle.ZERO());
    }


    /* ================================
    METHODES
    ================================ */

    //
    public void queueFree() {
        inQueueToDeletion = true;
    }

    // TRANSFORM

    //
    public Transform getGlobalTransform() {
        if(parent != null) return Transform.SUM(transform, parent.getGlobalTransform());
        return transform;
    }


    // CHILDREN

    //
    public void addChild(Node2D child) {
        if(child.parent == null) {
            children.add(child);
            child.parent = this;
        }
    }

    //
    public void removeChild(Node2D child) {
        if(children.remove(child)) child.parent = null;
    }

    // COMPONENT

    //
    public void addComponent(Component component) {
        if(component.parent == null) {
            components.add(component);
            component.parent = this;
        }
    }

    // GROUPS

    //
    public boolean isInGroup(String groupName) {
        for(int i=0; i< groups.size(); i++) {
            if(groups.get(i).equals(groupName)) return true;
        }
        return false;
    }

    //
    public void putInGroup(String groupName) {
        if(!isInGroup(groupName)) groups.add(groupName);
    }

    //
    public void removeFromGroup(String groupName) {
        groups.remove(groupName);
    }


    /* ================================
    EVENT
    ================================ */

    //
    public boolean onTouchEvent(MotionEvent event) {
        for(int i=0; i<components.size(); i++) {
            if(components.get(i).onTouchEvent(event)) return true;
        }
        for(int i=0; i<children.size(); i++) {
            if(children.get(i).onTouchEvent(event)) return true;
        }
        return false;
    }


    /* ================================
    UPDATE
    ================================ */

    //
    public void updateChildren(float deltaTime) {
        for(int i=0; i<children.size(); i++) {
            children.get(i).update(deltaTime);
        }
    }

    //
    public void updateSelf(float deltaTime) {
        for(int i=0; i<components.size(); i++) {
            components.get(i).update(deltaTime);
        }
    }

    //
    public void update(float deltaTime) {
        updateSelf(deltaTime);
        updateChildren(deltaTime);
    }


    /* ================================
    RENDER
    ================================ */

    //
    public void renderChildren(Canvas canvas, Graphics graphics) {
        for(int i=0; i<children.size(); i++) {
            children.get(i).render(canvas, graphics);
        }
    }

    //
    public void renderSelf(Canvas canvas, Graphics graphics) {
        for(int i=0; i<components.size(); i++) {
            components.get(i).render(canvas, graphics);
        }
    }

    //
    public void render(Canvas canvas, Graphics graphics) {
        renderSelf(canvas, graphics);
        renderChildren(canvas, graphics);
    }


    /* ================================
    CLEAN TREE
    ================================ */

    //
    public void cleanTree() {
        if(inQueueToDeletion) {
            if(parent != null) parent.removeChild(this);
            parent = null;
            return;
        }
        for(int i=0; i< children.size(); i++) children.get(i).cleanTree();
    }
}
