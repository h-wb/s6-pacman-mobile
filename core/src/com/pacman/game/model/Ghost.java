package com.pacman.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.List;

import java.util.ArrayList;

public abstract class Ghost extends MoveableElement {

    public Ghost(Vector2 pos, World w) {
        super(pos, w);
        _vel=new Vector2(0,0);
    }

    @Override
    public World getWorld() {
        return _world;
    }

    @Override
    public Vector2 getPosition() {
        return _pos;
    }

    @Override
    public void setPosition(Vector2 v) {
        _pos=v;
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(_pos.x,_pos.y,getWidth(),getHeight());
    }

    @Override
    public String toString() {
        return "Ghost [position=" + _pos + ", world=" + _world + "]";
    }

    @Override
    public Vector2 getVelocity() {
        return _vel;
    }

    @Override
    public void setVelocity(Vector2 v) {
        _vel=v;
    }

    public abstract void deplacement();

    protected void deplacementAlea(){
        Rectangle rectGe=new Rectangle();
        if(_pos.x%1==0&&_pos.y%1==0) {
            GameElement geUp,geDown,geRight,geLeft;
            ArrayList<Vector2> velocityPossible=new ArrayList<Vector2>();
            float vel=0.1f;
            if(((int)_pos.y+1)<_world.getMaze().getHeight()) {
                geUp = _world.getMaze().get((int) _pos.x, (int) _pos.y + 1);
                if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                    velocityPossible.add(new Vector2(0, vel));
                }
            }
            if((int)_pos.y-1>=0) {
                geDown = _world.getMaze().get((int) _pos.x, (int) _pos.y - 1);
                if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                    velocityPossible.add(new Vector2(0, -vel));
                }
            }
            if(((int)_pos.x+1)<_world.getMaze().getWidth()) {
                geRight = _world.getMaze().get((int) _pos.x + 1, (int) _pos.y);
                if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                    velocityPossible.add(new Vector2(vel, 0));
                }
            }
            if(((int)_pos.x-1)>=0) {
                geLeft = _world.getMaze().get((int) _pos.x - 1, (int) _pos.y);
                if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                    velocityPossible.add(new Vector2(-vel, 0));
                }
            }

            int direction=(int)(Math.random() * (velocityPossible.size()));
                _vel=velocityPossible.get(direction);
                _pos.x = (float) Math.round((_pos.x + _vel.x) * 10) / 10;
                _pos.y = (float) Math.round((_pos.y + _vel.y) * 10) / 10;
        }
        else{
            _pos.x = (float) Math.round((_pos.x + _vel.x) * 10) / 10;
            _pos.y = (float) Math.round((_pos.y + _vel.y) * 10) / 10;
        }
    }
}
