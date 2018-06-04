package com.pacman.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.pacman.game.model.Ghost;
import com.pacman.game.model.Ghost1;
import com.pacman.game.model.direction;

public class TextureGhost1 implements iTexturable {
    private Ghost1 _ghost1;
    private Texture _textureBASE, _textureLEFT, _textureRIGHT, _textureUP, _textureESCAPING, _textureDEAD, _textureCLIGNOTE;
    private double _deltaT;
    private double _seuil;


    public TextureGhost1(Ghost1 ghost, double seuil) {
        _ghost1 = ghost;
        _deltaT = 0.0;
        _seuil = seuil;
        _textureBASE = new Texture(Gdx.files.internal("ghost1.png"));
        _textureESCAPING = new Texture(Gdx.files.internal("ghostEscaping.png"));
        _textureCLIGNOTE = new Texture(Gdx.files.internal("ghostClignote.png"));
        _textureDEAD = new Texture(Gdx.files.internal("ghostDead.png"));
        _textureLEFT = new Texture(Gdx.files.internal("ghost1-left.png"));
        _textureRIGHT = new Texture(Gdx.files.internal("ghost1-right.png"));
        _textureUP = new Texture(Gdx.files.internal("ghost1-up.png"));
    }

    public void render(double delta) {
        _deltaT += delta;
        if (_deltaT > _seuil)
            _deltaT = 0.0;
    }

    public Texture getTexture() {
        if (_ghost1.getEscape()) {
            if (_ghost1.isClignote() && _deltaT < (_seuil / 2.0))
                return _textureCLIGNOTE;
            else
                return _textureESCAPING;
        } else if (_ghost1.getDead()) {
            return _textureDEAD;
        } else {
            if (_ghost1.direction() == direction.DOWN)
                return _textureBASE;
            if (_ghost1.direction() == direction.LEFT)
                return _textureLEFT;
            if (_ghost1.direction() == direction.RIGHT)
                return _textureRIGHT;
            if (_ghost1.direction() == direction.UP)
                return _textureUP;
            return _textureBASE;
        }
    }
}