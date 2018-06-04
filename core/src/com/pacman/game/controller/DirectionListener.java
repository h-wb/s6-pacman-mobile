package com.pacman.game.controller;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.pacman.game.model.World;

public class DirectionListener implements  GestureListener {

    private World _world;
    private String message = "No gesture performed yet";

    public DirectionListener( World world) {
        _world = world;
    }


    private void update(Vector2 vel){
        _world.getPacman().setNextVelocity(vel);
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        float moveAmount = 0.1f;
        if(Math.abs(velocityX)>Math.abs(velocityY)){
            if(velocityX>0){
                update(new Vector2(+moveAmount,0));
            }else{
                update(new Vector2(+moveAmount,0));
            }
        }else{
            if(velocityY>0){
                update(new Vector2(0,-moveAmount));
            }else{

                update(new Vector2(0,+moveAmount));
            }
        }
        return true;
    }



    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        message = "Tap performed, finger" + Integer.toString(button);
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        message = "Long press performed";
        return true;
    }

     @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        message = "Pan performed, delta:" + Float.toString(deltaX) +
                "," + Float.toString(deltaY);
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        message = "Zoom performed, initial Distance:" + Float.toString(initialDistance) +
                " Distance: " + Float.toString(distance);
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
                         Vector2 pointer1, Vector2 pointer2) {
        message = "Pinch performed";
        return true;
    }

    @Override
    public void pinchStop() {

    }

}