package com.pacman.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Super extends Pellet {

    public Super(Vector2 position, World world){
        super(position,world);
    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public Vector2 getPosition() {
        return _pos;
    }

    @Override
    public void setPosition(Vector2 v) {
        _pos = v;
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
        return new Rectangle(_pos.x,_pos.y,getHeight(),getWidth());
    }

    @Override
    public String toString() {
        return "Super [position=" + _pos + ", world=" + _world + "]";
    }


}
