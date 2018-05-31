package com.pacman.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pacman.game.controller.Listener;
import com.pacman.game.screen.GameScreen;

public class PacmanGame extends Game {

	@Override
	public void create() {
	this.setScreen(new GameScreen(this));
	}

}