package com.pacman.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.pacman.game.model.Ghost4;
import com.pacman.game.model.direction;

public class TextureGhost4 implements iTexturable
{
    private Ghost4 _ghost4;
    private Texture _textureBASE,_textureLEFT,_textureRIGHT,_textureUP, _textureESCAPING, _textureDEAD, _textureCLIGNOTE;
    private double _deltaT;
    private double _seuil;

    public TextureGhost4(Ghost4 ghost, double seuil) {
        _ghost4 = ghost;
        _deltaT = 0.0;
        _seuil = seuil;
        _textureBASE = new Texture(Gdx.files.internal("ghost4.png"));
        _textureESCAPING = new Texture(Gdx.files.internal("ghostEscaping.png"));
        _textureCLIGNOTE = new Texture(Gdx.files.internal("ghostClignote.png"));
        _textureDEAD = new Texture(Gdx.files.internal("ghostDead.png"));
        _textureLEFT = new Texture(Gdx.files.internal("ghost4-right.png"));
        _textureRIGHT = new Texture(Gdx.files.internal("ghost4-left.png"));
        _textureUP = new Texture(Gdx.files.internal("ghost4-up.png"));


    }

    public void render (double delta) {
        _deltaT += delta;
        if (_deltaT > _seuil)
            _deltaT = 0.0;
    }

    public Texture getTexture () {
        if(_ghost4.getEscape()){
            if (_ghost4.isClignote() && _deltaT < (_seuil / 2.0))
                return _textureCLIGNOTE;
            return _textureESCAPING;
        }
        else if(_ghost4.getDead()){
            return _textureDEAD;
        }
        else{
            if (_ghost4.direction() == direction.DOWN)
                return _textureBASE;
            if (_ghost4.direction() == direction.LEFT)
                return _textureLEFT;
            if (_ghost4.direction() == direction.RIGHT)
                return _textureRIGHT;
            if (_ghost4.direction() == direction.UP)
                return _textureUP;
            return _textureBASE;
        }
    }
}