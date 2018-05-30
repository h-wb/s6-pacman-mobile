package com.pacman.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ghost1 extends Ghost {

    public Ghost1(Vector2 pos, World w) {
        super(pos, w);
    }

    @Override
    public void deplacement() {
        deplacementAlea();
    }

    public Object direction() {
        if(_vel.x>0) {
            return direction.RIGHT;
        }
        else if(_vel.x<0) {
            return direction.LEFT;
        }
        else if(_vel.y>0) {
            return direction.UP;
        }
        else if(_vel.y<0) {
            return direction.DOWN;
        }
        return direction.NONE;
    }
}
