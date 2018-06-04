package com.pacman.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.pacman.game.model.Ghost2;
import com.pacman.game.model.direction;

public class TextureGhost2 implements iTexturable
{
    private Ghost2 _ghost2;
    private Texture _textureBASE,_textureLEFT,_textureRIGHT,_textureUP, _textureESCAPING, _textureDEAD, _textureCLIGNOTE;
    private double _deltaT;
    private double _seuil;

    public TextureGhost2(Ghost2 ghost, double seuil) {
        _ghost2 = ghost;
        _deltaT = 0.0;
        _seuil = seuil;
        _textureBASE = new Texture(Gdx.files.internal("ghost2.png"));
        _textureESCAPING = new Texture(Gdx.files.internal("ghostEscaping.png"));
        _textureDEAD = new Texture(Gdx.files.internal("ghostDead.png"));
        _textureLEFT = new Texture(Gdx.files.internal("ghost2-left.png"));
        _textureRIGHT = new Texture(Gdx.files.internal("ghost2-right.png"));
        _textureUP = new Texture(Gdx.files.internal("ghost2-up.png"));
    }

    public void render (double delta) {
        _deltaT += delta;
        if (_deltaT > _seuil)
            _deltaT = 0.0;
    }

    public Texture getTexture () {
        if(_ghost2.getEscape()){
            return _textureESCAPING;
        }
        else if(_ghost2.getDead()){
            return _textureDEAD;
        }
        else{
            if (_ghost2.direction() == direction.DOWN)
                return _textureBASE;
            if (_ghost2.direction() == direction.LEFT)
                return _textureLEFT;
            if (_ghost2.direction() == direction.RIGHT)
                return _textureRIGHT;
            if (_ghost2.direction() == direction.UP)
                return _textureUP;
            return _textureBASE;
        }
    }
}