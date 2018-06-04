package com.pacman.game.model;

import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;

public class Ghost4 extends Ghost {

    public Ghost4(Vector2 pos, World w, Boolean escape, Boolean dead) {
        super(pos, w);
        _escape = escape;
        _dead = dead;

    }

    @Override
    public void deplacement() {
        GameElement ge = _world.getMaze().get((int) _pos.x, (int) _pos.y);
        if (_pos.x % 1 == 0 && _pos.y % 1 == 0) {
            if (_dead) {
                deplacementMaison();
            } else if (_escape) {
                deplacementFuite();
            } else if (doitSortir) {
                sortirMaison();
            } else if (!(ge instanceof Block || ge instanceof Barriere)) {
                deplacementLargeur();
            }
        } else {
            _pos.x = (float) Math.round((_pos.x + _vel.x) * 100) / 100;
            _pos.y = (float) Math.round((_pos.y + _vel.y) * 100) / 100;
        }

    }
}

