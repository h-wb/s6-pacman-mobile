package com.pacman.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pacman.game.model.World;

public class EndScreen implements Screen {

    int score;
    long time;
    SpriteBatch batch;
    BitmapFont font1, font2, font3;
    Game game;
    World world;
    private float ppuX, ppuY;

    public EndScreen(Game game, int score, long time, World world, float ppuX, float ppuY) {
        this.score = score;
        this.game = game;
        this.world = world;
        this.ppuX = ppuX;
        this.ppuY = ppuY;
        this.time = time;
    }

    @Override
    public void render(float v) {
        batch.begin();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 0);

        font1.setColor(Color.RED);

        font1.getData().setScale(5f);
        font2.getData().setScale(3f);

        final GlyphLayout layout1 = new GlyphLayout(font1, "GAME OVER");
        final GlyphLayout layout2 = new GlyphLayout(font2, "SCORE FINAL:" + Integer.toString(score*(int)time));
        final GlyphLayout layout3 = new GlyphLayout(font3, "Appuez sur entrée pour recommencer");

        final float fontX1=  (this.world.getWidth()*ppuX- layout1.width) / 2;
        final float fontY1 = (this.world.getHeight()*ppuY + layout1.height)-100;
        final float fontX2 =  (this.world.getWidth()*ppuX- layout2.width) / 2;
        final float fontY2 = (this.world.getHeight()*ppuY + layout2.height) / 2;
        final float fontX3=  (this.world.getWidth()*ppuX- layout3.width) / 2;
        final float fontY3 = (this.world.getHeight()*ppuY + layout3.height) / 4;


        font1.draw(batch, layout1, fontX1, fontY1);
        font2.draw(batch, layout2, fontX2, fontY2);
      // font3.draw(batch, layout3, fontX3, fontY3);




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
        font1 = new BitmapFont();
        font2 = new BitmapFont();
        font3 = new BitmapFont();
        //font = new BitmapFont(Gdx.files.internal("font.fnt"));
        // stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),true);
    }

}