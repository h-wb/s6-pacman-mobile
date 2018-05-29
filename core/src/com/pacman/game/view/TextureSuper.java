package com.pacman.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.pacman.game.model.Super;
import com.pacman.game.model.direction;

public class TextureSuper implements iTexturable
{
    private Super _super;
    private Texture _texture1,_texture2;
    private double _deltaT;
    private double _seuil;

    public TextureSuper (Super Super, double seuil) {
        _super = Super;
        _deltaT = 0.0;
        _seuil = seuil;
        _texture1 = new Texture(Gdx.files.internal("superpellet.png"));
        _texture2 = new Texture(Gdx.files.internal("superpellet-2.png"));

    }

    public void render (double delta) {
        _deltaT += delta;
        if (_deltaT > _seuil)
            _deltaT = 0.0;
    }

    public Texture getTexture () {
        if (_deltaT < (_seuil / 2.0))
            return _texture1;
        else
            return _texture2;
    }
}