package com.pacman.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.pacman.game.model.Ghost2;
import com.pacman.game.model.Ghost4;
import com.pacman.game.model.direction;

public class TextureGhost4 implements iTexturable
{
    private Ghost4 _ghost4;
    private Texture _textureBASE/*,_textureLEFT,_textureLEFT2, _textureRIGHT,_textureRIGHT2, _textureUP, _textureUP2, _textureDOWN,_textureDOWN2*/ ;
    private double _deltaT;
    private double _seuil;

    public TextureGhost4(Ghost4 ghost, double seuil) {
        _ghost4 = ghost;
        _deltaT = 0.0;
        _seuil = seuil;
        _textureBASE = new Texture(Gdx.files.internal("ghost4.png"));
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
        /*if (_ghost1.direction() == direction.LEFT)
            if (_deltaT < (_seuil / 4.0))
                return _textureBASE;
            else if(_deltaT < ((_seuil*2) / 4.0))
                return _textureLEFT;
            else if(_deltaT < ((_seuil*3) / 4.0))
                return _textureLEFT2;
            else
                return _textureLEFT;
        else if(_ghost1.direction() == direction.RIGHT)
            if (_deltaT < (_seuil / 4.0))
                return _textureBASE;
            else if(_deltaT < ((_seuil*2) / 4.0))
                return _textureRIGHT;
            else if(_deltaT < ((_seuil*3) / 4.0))
                return _textureRIGHT2;
            else
                return _textureRIGHT;
        else if(_ghost1.direction() == direction.DOWN)
            if (_deltaT < (_seuil / 4.0))
                return _textureBASE;
            else if(_deltaT < ((_seuil*2) / 4.0))
                return _textureDOWN;
            else if(_deltaT < ((_seuil*3) / 4.0))
                return _textureDOWN2;
            else
                return _textureDOWN;
        else if(_ghost1.direction() == direction.UP)
            if (_deltaT < (_seuil / 4.0))
                return _textureBASE;
            else if(_deltaT < ((_seuil*2) / 4.0))
                return _textureUP;
            else if(_deltaT < ((_seuil*3) / 4.0))
                return _textureUP2;
            else
                return _textureUP;*/
        return _textureBASE;
    }
}