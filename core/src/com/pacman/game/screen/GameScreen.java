package com.pacman.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.pacman.game.PacmanGame;
import com.pacman.game.controller.DirectionListener;
import com.pacman.game.controller.Listener;
import com.pacman.game.model.World;
import com.pacman.game.view.TextureFactory;
import com.pacman.game.controller.WorldRenderer;
import com.badlogic.gdx.ApplicationListener;

public class GameScreen implements Screen {

    private World _world;
    private WorldRenderer _worldRenderer;
    private Game _game;
   private  GestureDetector.GestureListener listener;

    public GameScreen(PacmanGame game) {
        this._game = game;
        this._world = new World();
        this._worldRenderer = new WorldRenderer(this._world, this._game);
        Gdx.input.setInputProcessor(new Listener(this._world));
    }

    public GameScreen() {
        this._world = new World();
        this._worldRenderer = new WorldRenderer(this._world, this._game);
        Gdx.input.setInputProcessor(new Listener(this._world));
        Gdx.input.setInputProcessor(new DirectionListener(listener,        this._world));
    }


    public World getWorld() { return this._world; }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        this._worldRenderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        this._worldRenderer.setPpuX(width/(float)this._world.getWidth());
        this._worldRenderer.setPpuY((height/(float)this._world.getHeight())-1);
    }

    @Override public void show() { TextureFactory.reset(); }

    @Override public void hide() {  }

    @Override public void pause() {  }

    @Override public void resume() {  }

    @Override public void dispose() { }

}
