package com.pacman.game.model;

import com.badlogic.gdx.math.Vector2;

public class Ghost2 extends Ghost {

    public Ghost2(Vector2 pos, World w) {
        super(pos, w);
    }

    @Override
    public void deplacement() {
        GameElement ge=_world.getMaze().get((int) _pos.x,(int) _pos.y);
        if((_pos.x%1==0&&_pos.y%1==0) && (ge instanceof Super||ge instanceof Intersection || ge instanceof Maison)) {
            deplacementMinim();
        }
        else{
            _pos.x = (float) Math.round((_pos.x + _vel.x) * 10) / 10;
            _pos.y = (float) Math.round((_pos.y + _vel.y) * 10) / 10;
        }
    }
}

