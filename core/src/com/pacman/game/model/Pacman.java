package com.pacman.game.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pacman.game.view.TextureFactory;

public class Pacman extends GameElement
{

    public Pacman(Vector2 position, World world)
    {
        super(position, world);
        _vel=new Vector2(0,0);
        _bounds= new Rectangle(0,0,0,0);
    }



    public Rectangle getBounds(){
        return _bounds;
    }

    public Vector2 getPosition() {
        return _pos;
    }

    @Override
    public Vector2 getVelocity() { return _vel; }

    @Override
    public void setPosition(Vector2 position) {

        this._pos = position;

        //_bounds.setX(position.x);
        //_bounds.setY(position.y);
    }

    @Override
    public void setVelocity(Vector2 velocity) {
        this._vel = velocity;
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    public World getWorld() {
        return _world;
    }

    public void setWorld(World world) {
        this._world = world;
    }

    @Override
    public String toString() {
        return "Pacman [position=" + _pos + ", world=" + _world + "]";
    }

    public void Deplacement(){
        /*float x=_pos.x + _vel.x;
        float y=_pos.y + _vel.y;
        Rectangle pacman=new Rectangle(x,y,getWidth(),getHeight());
        if(_vel.x>0){
            _world.getMaze()[][_pos.y]
        }*/
        _pos.x=_pos.x + _vel.x;
        _pos.y=_pos.y + _vel.y;
    }


    public void Collision(){
        _bounds.x=_pos.x;
        _bounds.y=_pos.y;
        _bounds.width = 1;
        _bounds.height = 1;
    }



}