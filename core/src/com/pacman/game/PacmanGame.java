package com.pacman.game;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.pacman.game.model.Block;
import com.pacman.game.model.GameElement;
import com.pacman.game.model.Maze;
import com.pacman.game.model.Pacman;
import com.pacman.game.model.World;
import com.pacman.game.screen.GameScreen;
import com.pacman.game.view.TextureFactory;
import com.pacman.game.view.WorldRenderer;

public class PacmanGame extends Game {

	@Override
	public void create() {
		this.setScreen(new GameScreen());
	}

}