package com.pacman.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Intersection extends GameElement {

    public Intersection(Vector2 pos, World w) {
        super(pos, w);
    }

    @Override
    public World getWorld() {
        return _world;
    }

    @Override
    public Vector2 getPosition() {
        return _pos;
    }

    @Override
    public void setPosition(Vector2 v) {
        _pos=v;
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(_pos.x,_pos.y,getWidth(),getHeight());
    }

    @Override
    public String toString() {
        return "Intersection["+_pos+"]";
    }
}
