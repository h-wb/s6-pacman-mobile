package com.pacman.game.model;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Ghost3 extends Ghost {

    public Ghost3(Vector2 pos, World w, Boolean escape, Boolean dead) {
        super(pos, w);
        _escape = escape;
        _dead = dead;

    }

    @Override
    public void deplacement() {
        GameElement ge = _world.getMaze().get((int) _pos.x, (int) _pos.y);
        Random myRand = new Random();
        if(_dead) {
            deplacementMaison();
        }else if (_escape) {
            deplacementFuite();
        }
        else  if((_pos.x%1==0&&_pos.y%1==0) && (ge instanceof Maison) && doitSortir) {
            sortirMaison();
        }
        else if((_pos.x%1==0&&_pos.y%1==0) && (ge instanceof Intersection || ge instanceof Maison || ge instanceof Super)) {
            if (myRand.nextBoolean()) {
                deplacementMinim();
            } else {
                deplacementAlea();
            }
        }
    }
}

