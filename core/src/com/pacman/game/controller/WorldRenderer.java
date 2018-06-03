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
import com.pacman.game.model.Barriere;
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

    public WorldRenderer(World world, Game game) {
        this.world = world;
        this.game = game;
        this.spriteBatch = new SpriteBatch();
        this.font = new BitmapFont();
        this.score=0;
    }

    public void render(float delta) {
        if(barrieres(delta)){
            this.world.getMaze().enleveBarriere();
            this.world.getGhost1().setDoitSortir(true);
            this.world.getGhost2().setDoitSortir(true);
            this.world.getGhost3().setDoitSortir(true);
        }

        this.world.getPacman().deplacement();
        Vector2 pos=this.world.getPacman().getPosition();
        if(pos.x%1==0&&pos.y%1==0) {
            GameElement ge=this.world.getMaze().get((int)pos.x, (int)pos.y);
            if(ge instanceof Super || ge instanceof IntersectionPellet) {
                this.world.getMaze().set((int)pos.x, (int)pos.y,new Intersection(new Vector2((int)pos.x,(int)pos.y),this.world));
                this.score+=10;
            }
            else if(ge instanceof Pellet) {
                this.world.getMaze().set((int)pos.x, (int)pos.y,new Vide(new Vector2((int)pos.x,(int)pos.y),this.world));
                this.score+=10;
            }
        }

        this.world.getGhost1().deplacement();
        this.world.getGhost2().deplacement();
        this.world.getGhost3().deplacement();
        this.world.getGhost4().deplacement();
        TexturePacman texturePacman = (TexturePacman) TextureFactory.getInstance(world).getTexturable(Pacman.class);
        //TextureSuper textureSuper = (TextureSuper) TextureFactory.getInstance(world).getTexturable(Super.class);
        //TextureGhost1 textureGhost1 = (TextureGhost1) TextureFactory.getInstance(world).getTexturable(Ghost1.class);
       // TextureGhost2 textureGhost2 = (TextureGhost2) TextureFactory.getInstance(world).getTexturable(Ghost2.class);
        texturePacman.render(delta);
        //textureSuper.render(delta);
       // textureGhost1.render(delta);
       // textureGhost2.render(delta);



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

        //Collision fantôme/pacman
        if(this.world.getPacman().getRectangle().overlaps(this.world.getGhost2().getRectangle()) || this.world.getPacman().getRectangle().overlaps(this.world.getGhost1().getRectangle()) ){
            System.out.println("fant");
            game.setScreen(new EndScreen(game, score, this.world, ppuX, ppuY));
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

    public void setPpuX(float ppuX) {
        this.ppuX = ppuX;
    }

    public void setPpuY(float ppuY) {
        this.ppuY = ppuY;
    }
}