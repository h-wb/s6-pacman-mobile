package com.pacman.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pacman.game.PacmanGame;

public class StartScreen implements Screen {

    SpriteBatch batch;
    private PacmanGame game;
    private float elapsed;


    public StartScreen(PacmanGame game) {
        this.game = game;
    }

    @Override
    public void render(float v) {
        batch.begin();
        elapsed += v;
        batch.draw(new Texture(Gdx.files.internal("pacman-logo.png")), 70, 100);
        if (elapsed > 2.0) {
            game.setScreen(new GameScreen(game));
        }
        batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {
        batch = new SpriteBatch();
    }

}