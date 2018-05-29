package com.pacman.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.pacman.game.model.Pacman;
import com.pacman.game.model.direction;

public class TexturePacman implements iTexturable
{
    private Pacman _pacman;
    private Texture _textureBASE,_textureLEFT,_textureLEFT2, _textureRIGHT,_textureRIGHT2, _textureUP, _textureUP2, _textureDOWN,_textureDOWN2 ;
    private double _deltaT;
    private double _seuil;

    public TexturePacman (Pacman pacman,double seuil) {
        _pacman = pacman;
        _deltaT = 0.0;
        _seuil = seuil;
        _textureBASE = new Texture(Gdx.files.internal("pacman-3.png"));
        _textureLEFT = new Texture(Gdx.files.internal("pacmanLeft.png"));
        _textureLEFT2 = new Texture(Gdx.files.internal("pacmanLeft-2.png"));
        _textureRIGHT = new Texture(Gdx.files.internal("pacmanRight.png"));
        _textureRIGHT2 = new Texture(Gdx.files.internal("pacmanRight-2.png"));
        _textureUP = new Texture(Gdx.files.internal("pacmanUp.png"));
        _textureUP2 = new Texture(Gdx.files.internal("pacmanUp-2.png"));
        _textureDOWN = new Texture(Gdx.files.internal("pacmanDown.png"));
        _textureDOWN2 = new Texture(Gdx.files.internal("pacmanDown-2.png"));

    }

    public void render (double delta) {
        _deltaT += delta;
        if (_deltaT > _seuil)
            _deltaT = 0.0;
    }

    public Texture getTexture () {
        if (_pacman.direction() == direction.LEFT)
            if (_deltaT < (_seuil / 4.0))
                return _textureBASE;
            else if(_deltaT < ((_seuil*2) / 4.0))
                return _textureLEFT;
            else if(_deltaT < ((_seuil*3) / 4.0))
                return _textureLEFT2;
            else
                return _textureLEFT;
        else if(_pacman.direction() == direction.RIGHT)
            if (_deltaT < (_seuil / 4.0))
                return _textureBASE;
            else if(_deltaT < ((_seuil*2) / 4.0))
                return _textureRIGHT;
            else if(_deltaT < ((_seuil*3) / 4.0))
                return _textureRIGHT2;
            else
                return _textureRIGHT;
        else if(_pacman.direction() == direction.DOWN)
            if (_deltaT < (_seuil / 4.0))
                return _textureBASE;
            else if(_deltaT < ((_seuil*2) / 4.0))
                return _textureDOWN;
            else if(_deltaT < ((_seuil*3) / 4.0))
                return _textureDOWN2;
            else
                return _textureDOWN;
        else if(_pacman.direction() == direction.UP)
            if (_deltaT < (_seuil / 4.0))
                return _textureBASE;
            else if(_deltaT < ((_seuil*2) / 4.0))
                return _textureUP;
            else if(_deltaT < ((_seuil*3) / 4.0))
                return _textureUP2;
            else
                return _textureUP;
        return _textureBASE;
    }
}