package com.pacman.game.model;

import com.badlogic.gdx.math.Vector2;

public class Ghost4 extends Ghost {

    public Ghost4(Vector2 pos, World w, Boolean escape) {
        super(pos, w);
        _escape = escape;
    }

    @Override
    public void deplacement() {

        GameElement ge = _world.getMaze().get((int) _pos.x, (int) _pos.y);
        if (_escape) {
            deplacementFuite();
        } else {
            if ((_pos.x % 1 == 0 && _pos.y % 1 == 0) && (ge instanceof Maison || ge instanceof Super)) {
                sortirMaison();
            } else if ((_pos.x % 1 == 0 && _pos.y % 1 == 0) && (ge instanceof Intersection || ge instanceof Super)) {
                deplacementMinim();
            } else {
                _pos.x = (float) Math.round((_pos.x + _vel.x) * 10) / 10;
                _pos.y = (float) Math.round((_pos.y + _vel.y) * 10) / 10;
            }
        }
    }
}

