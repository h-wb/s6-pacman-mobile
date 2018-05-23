package com.pacman.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.pacman.game.view.TextureFactory;

public abstract class GameElement {
    protected Vector2 _pos;
    protected Vector2 _vel;
    protected World _world;

    public GameElement(Vector2 pos, World w){
        _pos = pos;
        _world = w;
    }

    public abstract World getWorld();

    public abstract Vector2 getPosition();

    public abstract Vector2 getVelocity();

    public abstract Sprite getSprite();

    public abstract void setVelocity(Vector2 v);

    public abstract void setPosition(Vector2 v);

    public abstract int getWidth();

    public abstract int getHeight();

   // public abstract Texture getTexture();

    public abstract String toString();
}