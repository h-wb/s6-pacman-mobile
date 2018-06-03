package com.pacman.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.pacman.game.model.Ghost;
import com.pacman.game.model.Ghost1;
import com.pacman.game.model.direction;

public class TextureGhost1 implements iTexturable
{
    private Ghost1 _ghost1;
    private Ghost _ghost;
    private Texture _textureBASE, _textureESCAPING, _textureDEAD;
    private double _deltaT;
    private double _seuil;


    public TextureGhost1(Ghost1 ghost, double seuil) {
        _ghost1 = ghost;
        _deltaT = 0.0;
        _seuil = seuil;
        _textureBASE = new Texture(Gdx.files.internal("ghost1.png"));
        _textureESCAPING = new Texture(Gdx.files.internal("ghostEscaping.png"));
        _textureDEAD = new Texture(Gdx.files.internal("ghostDead.png"));
        /*_textureLEFT = new Texture(Gdx.files.internal("ghost1.png"));
        _textureLEFT2 = new Texture(Gdx.files.internal("ghost1.png"));
        _textureRIGHT = new Texture(Gdx.files.internal("ghost1.png"));
        _textureRIGHT2 = new Texture(Gdx.files.internal("ghost1.png"));
        _textureUP = new Texture(Gdx.files.internal("ghost1.png"));
        _textureUP2 = new Texture(Gdx.files.internal("ghost1.png"));
        _textureDOWN = new Texture(Gdx.files.internal("ghost1.png"));
        _textureDOWN2 = new Texture(Gdx.files.internal("ghost1.png"));*/

    }

    public void render (double delta) {
        _deltaT += delta;
        if (_deltaT > _seuil)
            _deltaT = 0.0;
    }

    public Texture getTexture () {
        if(_ghost1.getEscape()){
            return _textureESCAPING;
        }
        else if(_ghost1.getDead()){
            return _textureDEAD;
        }
        else{
            return _textureBASE;

        }
    }
}