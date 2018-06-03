package com.pacman.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.pacman.game.model.Ghost2;
import com.pacman.game.model.Ghost4;
import com.pacman.game.model.direction;

public class TextureGhost4 implements iTexturable
{
    private Ghost4 _ghost4;
    private Texture _textureBASE, _textureESCAPING;
    private double _deltaT;
    private double _seuil;

    public TextureGhost4(Ghost4 ghost, double seuil) {
        _ghost4 = ghost;
        _deltaT = 0.0;
        _seuil = seuil;
        _textureBASE = new Texture(Gdx.files.internal("ghost4.png"));
        _textureESCAPING = new Texture(Gdx.files.internal("ghostEscaping.png"));
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
        if(_ghost4.getEscape()){
            return _textureESCAPING;
        }
        else{
            return _textureBASE;

        }
    }
}