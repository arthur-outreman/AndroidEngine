package com.example.engine.nodes;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

import com.example.engine.EngineCore;
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

    public EngineCore root;
    public boolean inQueueToDeletion = false;
    public @Nullable Node2D parent = null;
    public Node2D[] children;
    public Component[] components;
    public ArrayList<String> groups;
    public Transform transform;
    public String name = "";


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public Node2D(Vect2 position) {
        children = new Node2D[0];
        components = new Component[0];
        groups = new ArrayList<>();

        transform = new Transform(position, Angle.ZERO());
    }

    //
    public Node2D() {
        this(Vect2.ZERO());
    }

    // Constructeur des Scenes
    public Node2D(EngineCore root) {
        this();
        this.root = root;
    }


    /* ================================
    METHODES
    ================================ */

    //
    public Node2D getTree() {
        return (parent==null)? this:parent.getTree();
    }

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
            Node2D[] newChildren = new Node2D[children.length+1];

            System.arraycopy(children, 0, newChildren, 0, children.length);
            newChildren[children.length] = child;
            children = newChildren;

            child.parent = this;
            child.root = root;
        }
    }

    //
    public void removeChild(Node2D child) {
        for(int i=0; i<children.length; i++) {
            if(!children[i].equals(child)) {
                continue;
            }
            for(int j=i; j<children.length-1; j++) {
                children[j] = children[j+1];
            }
            Node2D[] newChildren = new Node2D[children.length-1];
            System.arraycopy(children, 0, newChildren, 0, children.length-1);
            children = newChildren;
            return;
        }
    }

    // COMPONENT

    //
    public void addComponent(Component component) {
        if(component.parent == null) {
            Component[] newComps = new Component[components.length+1];

            System.arraycopy(components, 0, newComps, 0, components.length);
            newComps[components.length] = component;
            components = newComps;

            component.parent = this;
            component.onAttach();
        }
    }

    // GROUPS

    //
    public boolean isInGroup(String groupName) {
        return groups.contains(groupName);
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
    SIGNAL
    ================================ */

    //
    public void signal(String signal, String name) {}

    //
    public void sendSignal(String signal) {
        if(parent==null) return;
        parent.signal(signal, name);
    }

    //
    public void signal(String signal, String name, Node2D arg) {}

    //
    public void sendSignal(String signal, Node2D arg) {
        if(parent==null) return;
        parent.signal(signal, name, arg);
    }


    /* ================================
    EVENT
    ================================ */

    //
    public boolean onTouchEvent(MotionEvent event) {
        for(Component component : components) {
            if (component.onTouchEvent(event)) return true;
        }
        for(Node2D child : children) {
            if (child.onTouchEvent(event)) return true;
        }
        return false;
    }


    /* ================================
    UPDATE
    ================================ */

    //
    public void updateChildren(float deltaTime) {
        for(Node2D child : children) {
            child.update(deltaTime);
        }
    }

    //
    public void updateComponents(float deltaTime) {
        for(Component component : components) {
            component.update(deltaTime);
        }
    }

    //
    public void updateSelf(float deltaTime) {}

    //
    public void update(float deltaTime) {
        updateSelf(deltaTime);
        updateComponents(deltaTime);
        updateChildren(deltaTime);
    }


    /* ================================
    RENDER
    ================================ */

    //
    public void renderChildren(Canvas canvas, Graphics graphics) {
        for(Node2D child : children) {
            child.render(canvas, graphics);
        }
    }

    //
    public void renderComponents(Canvas canvas, Graphics graphics) {
        for(Component component : components) {
            component.render(canvas, graphics);
        }
    }

    //
    public void render(Canvas canvas, Graphics graphics) {
        renderComponents(canvas, graphics);
        renderChildren(canvas, graphics);
    }


    /* ================================
    CLEAN TREE
    ================================ */

    //
    public void cleanTree() {
        for (Node2D child : children) child.cleanTree();
        if(inQueueToDeletion) {
            if(parent != null) parent.removeChild(this);
            parent = null;
        }
    }
}
