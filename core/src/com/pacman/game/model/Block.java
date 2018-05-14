package com.pacman.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.pacman.game.view.TextureFactory;

public class Block extends GameElement{
    private Vector2 _position;
    private enumGameElement _type;

    public Block(Vector2 position){
        super(position);
        _position = position;
        _type = enumGameElement.bloc;
    }

    public Texture getTexture() {
        return TextureFactory.getTextureInstance().getTexturePacman();
    }



    @Override
    public String toString() {
        return "Block [position=" + _position + ", world=" + _world + "]";
    }

    @Override
    public Vector2 getPosition() {
        return _position;
    }

    @Override
    public void setPosition(Vector2 v) {
        _position = v;
    }


}