package com.pacman.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pacman.game.PacmanGame;
import com.pacman.game.controller.DirectionListener;
import com.pacman.game.controller.Listener;
import com.pacman.game.model.World;

public class StartScreen implements Screen {

    private SpriteBatch batch;
    private PacmanGame _game;
    private float elapsed;
    private TextButton button, button2;
    private TextButton.TextButtonStyle textButtonStyle;
    private Stage stage;
    private BitmapFont font;
    private World _world;
    private InputMultiplexer im;

    public StartScreen(PacmanGame game) {
        this._game = game;
        this._world = new World();
        this.im= new InputMultiplexer();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;

        button = new TextButton("Cliquez ici pour jouer au clavier", textButtonStyle);
        button2 = new TextButton("Cliquez ici pour jouer à la souris", textButtonStyle);
        button.setPosition(70,70);
        button2.setPosition(350,70);
        stage.addActor(button);
        stage.addActor(button2);


        button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                im.addProcessor(new Listener(_world));
                Gdx.input.setInputProcessor(im);
                _game.setScreen(new GameScreen(_game, im, _world));
            }
        } );

        button2.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                im.addProcessor(new GestureDetector(new DirectionListener(_world)));
                Gdx.input.setInputProcessor(im);
                _game.setScreen(new GameScreen(_game, im, _world));
            }
        } );


    }


    @Override
    public void render(float v) {
        elapsed += v;
        stage.getBatch().begin();
        stage.getBatch().draw(new Texture(Gdx.files.internal("pacman-logo.png")), 70, 130);
        stage.getBatch().end();
        stage.draw();
        if (elapsed > 10.0) {
            im.addProcessor(new Listener(_world));
            im.addProcessor(new GestureDetector(new DirectionListener(_world)));
            Gdx.input.setInputProcessor(im);
            _game.setScreen(new GameScreen(_game, im, _world));
        }




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