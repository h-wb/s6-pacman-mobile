package com.pacman.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.pacman.game.view.TextureFactory;

public class Pacman extends GameElement
{

    Vector2 position;
    World world;

    public Pacman(Vector2 position, World world)
    {
        super(position, world);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Texture getTexture() {
        return TextureFactory.getTextureInstance().getTexturePacman();
    }

    @Override
    public String toString() {
        return "Pacman [position=" + position + ", world=" + world + "]";
    }




}