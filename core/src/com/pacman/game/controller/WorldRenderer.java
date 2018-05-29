package com.pacman.game.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pacman.game.model.Pacman;
import com.pacman.game.model.Block;
import com.pacman.game.model.Vide;
import com.pacman.game.model.World;
import com.pacman.game.model.GameElement;
import com.pacman.game.view.TextureFactory;

public class WorldRenderer {

    private World world;
    private SpriteBatch spriteBatch;
    private float ppuX, ppuY;
    private GameElement test;


    public WorldRenderer(World world) {
        this.world = world;
        this.spriteBatch = new SpriteBatch();
    }

    public void render(float delta) {
        this.spriteBatch.begin();

        GameElement previous = this.world()
        for (GameElement element : this.world) {
            this.spriteBatch.draw(
                    TextureFactory.getInstance().getTexture(element.getClass()),
                    element.getPosition().x * ppuX,
                    element.getPosition().y * ppuY,
                    element.getWidth() * ppuX,
                    element.getHeight() * ppuY
            );


            //this.world.getPacman().getBounds();
            if(element instanceof Block) {
                //System.out.println(element.getBounds());
                if(this.world.getPacman().getBounds().overlaps(element.getBounds())){
                    System.out.println("ça touche");
                    this.world.getPacman().setVelocity(new Vector2(0,0));
                    //Vector2 test = new Vector2((int)this.world.getPacman().getPosition().x, (int)this.world.getPacman().getPosition().y);
                   Vector2 test2 = test.getPosition();
                    this.world.getPacman().setPosition(test2);
                }
            }
        }


    /*for(int i = this.world.getMaze().getBlocks().size() -1; i >0; i--){
            Block block = this.world.getMaze().getBlocks().get(i);
        //System.out.println(block.getBounds());
        if(this.world.getPacman().getBounds().overlaps(block.getBounds())){
            System.out.println("ça touche");
            this.world.getPacman().setVelocity(new Vector2(0,0));
        }
    }*/
        this.spriteBatch.end();
        this.world.getPacman().Deplacement();
        this.world.getPacman().Collision();
       // System.out.println(this.world.getPacman().getBounds());

    }

    public void setPpuX(float ppuX) {
        this.ppuX = ppuX;
    }

    public void setPpuY(float ppuY) {
        this.ppuY = ppuY;
    }
}