package com.pacman.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.pacman.game.view.TextureFactory;

public class Pacman extends GameElement
{

    public Pacman(Vector2 position, World world)
    {
        super(position, world);
    }

    public Vector2 getPosition() {
        return _pos;
    }

    @Override
    public void setPosition(Vector2 position) {
        this._pos = position;
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    public World getWorld() {
        return _world;
    }

    public void setWorld(World world) {
        this._world = world;
    }

    @Override
    public String toString() {
        return "Pacman [position=" + _pos + ", world=" + _world + "]";
    }





}