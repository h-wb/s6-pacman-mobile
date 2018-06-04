package com.pacman.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.pacman.game.model.World;

public class DirectionListener extends GestureDetector {


    private World _world;

        public DirectionListener(GestureListener listener, World world) {
            super(listener);
            _world = world;
        }


    private void update(Vector2 vel){
            _world.getPacman().setNextVelocity(vel);
        }


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




    }

