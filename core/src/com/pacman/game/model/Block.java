package com.pacman.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pacman.game.view.TextureFactory;

public class Block extends GameElement{


    public Block(Vector2 position, World world){
        super(position,world);
        _bounds = new Rectangle(_pos.x, _pos.y, getWidth(), getHeight());
    }

    public Rectangle getBounds(){
        return _bounds;
    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public String toString() {
        return "Block [position=" + _pos + ", world=" + _world + "]";
    }

    @Override
    public Vector2 getPosition() {
        return _pos;
    }

    @Override
    public Vector2 getVelocity() {
        return null;
    }

    @Override
    public void setVelocity(Vector2 v) {

    }

    @Override
    public void setPosition(Vector2 v) {
        _pos = v;
        _bounds.setX(v.x);
        _bounds.setY(v.y);
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }


}