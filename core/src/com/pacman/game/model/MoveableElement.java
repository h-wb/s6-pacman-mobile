package com.pacman.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class MoveableElement extends GameElement{

    public MoveableElement(Vector2 pos, World w) {
        super(pos, w);
        // TODO Auto-generated constructor stub
    }

    protected Vector2 _vel;

    protected Boolean _escape;

    protected Boolean _dead;

    public abstract Vector2 getVelocity();

    public abstract void setVelocity(Vector2 v);

    public abstract boolean getEscape();

    public abstract void setEscape(Boolean escape);

    public abstract boolean getDead();

    public abstract void setDead(Boolean dead);

    public abstract void deplacement();

}