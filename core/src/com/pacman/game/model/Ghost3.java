package com.pacman.game.model;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Ghost3 extends Ghost {

    public Ghost3(Vector2 pos, World w) {
        super(pos, w);
    }

    @Override
    public void deplacement() {
        GameElement ge=_world.getMaze().get((int) _pos.x,(int) _pos.y);
        Random myRand = new Random();
        if((_pos.x%1==0&&_pos.y%1==0) && ( ge instanceof Maison || ge instanceof Super)) {
            sortirMaison();
        }
        else if((_pos.x%1==0&&_pos.y%1==0) && (ge instanceof Intersection || ge instanceof Super)) {
            if (myRand.nextBoolean()) {
                System.out.println("oui");
                deplacementMinim();
            } else {
                System.out.println("non");
                deplacementAlea();
            }
        }
        else{
            _pos.x = (float) Math.round((_pos.x + _vel.x) * 10) / 10;
            _pos.y = (float) Math.round((_pos.y + _vel.y) * 10) / 10;
        }
    }
}
