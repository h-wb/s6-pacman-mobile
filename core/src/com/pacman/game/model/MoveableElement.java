package com.pacman.game.model;

import com.badlogic.gdx.math.Vector2;

public abstract class MoveableElement extends GameElement{

    public MoveableElement(Vector2 pos, World w) {
		super(pos, w);
		// TODO Auto-generated constructor stub
	}

	protected Vector2 _vel;
    
    public abstract Vector2 getVelocity();

    public abstract void setVelocity(Vector2 v);
	
    public abstract void deplacement();
}
