package com.pacman.game.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pacman.game.model.Pacman;
import com.pacman.game.model.Pellet;
import com.pacman.game.model.Vide;
import com.pacman.game.model.Block;
import com.pacman.game.model.World;
import com.pacman.game.model.GameElement;
import com.pacman.game.view.TextureFactory;
import com.pacman.game.view.TexturePacman;

public class WorldRenderer {

    private World world;
    private SpriteBatch spriteBatch;
    private BitmapFont font;
    private float ppuX, ppuY;
    private int score;

    public WorldRenderer(World world) {
        this.world = world;
        this.spriteBatch = new SpriteBatch();
        this.font = new BitmapFont();
        this.score=0;
    }

    public void render(float delta) {
        TexturePacman texturePacman =
                (TexturePacman) TextureFactory.getInstance(world).getTexturable(Pacman.class);
        texturePacman.render (delta);

        this.world.getPacman().deplacement();
        Vector2 pos=this.world.getPacman().getPosition();
        if(pos.x%1==0&&pos.y%1==0) {
            GameElement ge=this.world.getMaze().get((int)pos.x, (int)pos.y);
            if(ge instanceof Pellet) {
                this.world.getMaze().set((int)pos.x, (int)pos.y,new Vide(pos,this.world));
                this.score+=10;
            }
        }

        this.spriteBatch.begin();
        for (GameElement element : this.world) {
            this.spriteBatch.draw(
                    TextureFactory.getInstance(world).getTexture(element.getClass()),
                    element.getPosition().x * ppuX,
                    element.getPosition().y * ppuY,
                    element.getWidth() * ppuX,
                    element.getHeight() * ppuY
            );
        }
        font.draw(spriteBatch,"Score:"+ Integer.toString(score), 0, this.world.getHeight()*ppuY);
        this.spriteBatch.end();
    }

    public void setPpuX(float ppuX) {
        this.ppuX = ppuX;
    }

    public void setPpuY(float ppuY) {
        this.ppuY = ppuY;
    }
}