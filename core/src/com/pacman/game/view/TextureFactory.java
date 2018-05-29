package com.pacman.game.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pacman.game.model.GameElement;
import com.pacman.game.model.Maison;
import com.pacman.game.model.Pacman;
import com.pacman.game.model.Pellet;
import com.pacman.game.model.Barriere;
import com.pacman.game.model.Block;
import com.pacman.game.model.Super;
import com.pacman.game.model.Vide;
import com.pacman.game.model.World;

import java.util.HashMap;

public class TextureFactory
{
    private static World _world;
    static private TextureFactory _instance = null;
    private HashMap<Class<?>, iTexturable> _textures;

    private TextureFactory(World world)
    {
        _world=world;
        _textures = new HashMap<Class<?>, iTexturable>();
        _textures.put(Pacman.class,new TexturePacman(_world.getPacman (), 1.0));
        _textures.put(Super.class, new TextureSuper();
        _textures.put(Block.class, new TextureUnique(new Texture(Gdx.files.internal("bloc.png"))));
        _textures.put(Barriere.class, new TextureUnique(new Texture(Gdx.files.internal("barriere.png"))));
        _textures.put(Vide.class, new TextureUnique(new Texture(Gdx.files.internal("dark.png"))));
        _textures.put(Pellet.class, new TextureUnique(new Texture(Gdx.files.internal("pellet.png"))));
        _textures.put(Maison.class, new TextureUnique(new Texture(Gdx.files.internal("dark.png"))));
    }

    static public void reset()
    {
        _instance = null;
    }

    static public TextureFactory getInstance(World world)
    {
        if (_instance == null)
            _instance = new TextureFactory(world);
        return _instance;
    }

    public Texture getTexture (Class <?> c)
    {
        return _textures.get(c).getTexture ();
    }

    public iTexturable getTexturable (Class <?> c)
    {
        return _textures.get(c);
    }
}