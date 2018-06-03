package com.pacman.game.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pacman.game.model.Ghost;
import com.pacman.game.model.Ghost1;
import com.pacman.game.model.Ghost2;
import com.pacman.game.model.Intersection;
import com.pacman.game.model.IntersectionPellet;
import com.pacman.game.model.Maison;
import com.pacman.game.model.Pacman;
import com.pacman.game.model.Pellet;
import com.pacman.game.model.Super;
import com.pacman.game.model.Vide;
import com.pacman.game.model.Block;
import com.pacman.game.model.World;
import com.pacman.game.model.GameElement;
import com.pacman.game.screen.EndScreen;
import com.pacman.game.screen.GameScreen;
import com.pacman.game.view.TextureFactory;
import com.pacman.game.view.TextureGhost1;
import com.pacman.game.view.TextureGhost2;
import com.pacman.game.view.TexturePacman;
import com.pacman.game.view.TextureSuper;

public class WorldRenderer {

    private World world;
    private Game game;
    private SpriteBatch spriteBatch;
    private BitmapFont font;
    private float ppuX, ppuY;
    private int score;
    float time=0;
    float escapeTime = 0;
    long startTime;
    long globalEscapeTime;

    public WorldRenderer(World world, Game game) {
        this.world = world;
        this.game = game;
        this.spriteBatch = new SpriteBatch();
        this.font = new BitmapFont();
        this.score=0;
        startTime = System.currentTimeMillis();
        globalEscapeTime = System.nanoTime();

    }

    public void render(float delta) {

        long time = ((System.currentTimeMillis() - startTime) / 1000);
        if(barrieres(delta)){
            this.world.getMaze().set(12,13,new Vide(new Vector2(12,13),world));
            this.world.getMaze().set(12,14,new Vide(new Vector2(12,14),world));
        }

        this.world.getPacman().deplacement();
        Vector2 pos=this.world.getPacman().getPosition();
        if(pos.x%1==0&&pos.y%1==0) {
            GameElement ge=this.world.getMaze().get((int)pos.x, (int)pos.y);
            if(ge instanceof Super) {
                this.world.getMaze().set((int)pos.x, (int)pos.y,new Intersection(new Vector2((int)pos.x,(int)pos.y),this.world));
                this.score+=10;
                this.world.getGhost1().setEscape(true);
                this.world.getGhost2().setEscape(true);
                this.world.getGhost3().setEscape(true);
                this.world.getGhost4().setEscape(true);
                escapeTime = 0;

            }
            else if(ge instanceof Pellet) {
                this.world.getMaze().set((int)pos.x, (int)pos.y,new Vide(new Vector2((int)pos.x,(int)pos.y),this.world));
                this.score+=10;
            }
            else if(ge instanceof IntersectionPellet) {
                this.world.getMaze().set((int)pos.x, (int)pos.y,new Intersection(new Vector2((int)pos.x,(int)pos.y),this.world));
                this.score+=10;
            }
        }


        if(this.world.getGhost1().getEscape()|| this.world.getGhost2().getEscape() || this.world.getGhost3().getEscape() || this.world.getGhost4().getEscape()){
            if(this.world.getPacman().getRectangle().overlaps(this.world.getGhost1().getRectangle())){
                System.out.println("ghost1");
                this.world.getGhost1().setEscape(false);
                this.world.getGhost1().setDead(true);
            }
            if(this.world.getPacman().getRectangle().overlaps(this.world.getGhost2().getRectangle())){
                this.world.getGhost2().setEscape(false);
                this.world.getGhost2().setDead(true);
                System.out.println("ghost2");
            }
            if(this.world.getPacman().getRectangle().overlaps(this.world.getGhost3().getRectangle())){
                this.world.getGhost3().setEscape(false);
                this.world.getGhost3().setDead(true);
                System.out.println("ghost3");
            }
            if(this.world.getPacman().getRectangle().overlaps(this.world.getGhost4().getRectangle())){
                this.world.getGhost4().setEscape(false);
                this.world.getGhost4().setDead(true);
                System.out.println("ghost4");
            }
            setEscape(delta);
            }



        this.world.getGhost1().deplacement();
        this.world.getGhost2().deplacement();
        this.world.getGhost3().deplacement();
        this.world.getGhost4().deplacement();
        TexturePacman texturePacman = (TexturePacman) TextureFactory.getInstance(world).getTexturable(Pacman.class);
        texturePacman.render(delta);




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
        font.draw(spriteBatch,"Score:"+ Integer.toString(score), 0, (this.world.getHeight()+1)*ppuY);
        font.draw(spriteBatch,"Temps écoulé = " + time +'s', (this.world.getWidth()-6)*ppuX, (this.world.getHeight()+1)*ppuY);

        //Collision fantôme/pacman (un peu longe ptet)
        if((this.world.getPacman().getRectangle().overlaps(this.world.getGhost2().getRectangle()) && !this.world.getGhost1().getEscape()) || (this.world.getPacman().getRectangle().overlaps(this.world.getGhost1().getRectangle())&& !this.world.getGhost1().getEscape()) || (this.world.getPacman().getRectangle().overlaps(this.world.getGhost3().getRectangle())&& !this.world.getGhost1().getEscape()) || (this.world.getPacman().getRectangle().overlaps(this.world.getGhost4().getRectangle())&& !this.world.getGhost1().getEscape())){
            game.setScreen(new EndScreen(game, score, time, world, ppuX, ppuY));
        }





        this.spriteBatch.end();
    }

    public boolean barrieres(float deltaTime){
        time += deltaTime;
        if(time >= 5){
            return true;
        }
        return false;
    }

    public boolean setEscape(float deltaTime){
        escapeTime += deltaTime;
        if(escapeTime >= 7){
            this.world.getGhost1().setEscape(false);
            this.world.getGhost2().setEscape(false);
            this.world.getGhost3().setEscape(false);
            this.world.getGhost4().setEscape(false);

        }
        return false;
    }

    public void setPpuX(float ppuX) {
        this.ppuX = ppuX;
    }

    public void setPpuY(float ppuY) {
        this.ppuY = ppuY;
    }
}