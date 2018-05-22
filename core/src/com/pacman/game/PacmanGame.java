package com.pacman.game;

import com.badlogic.gdx.Game;
import com.pacman.game.screen.GameScreen;

public class PacmanGame extends Game {

	@Override
	public void create() {
	this.setScreen(new GameScreen());
	}

}