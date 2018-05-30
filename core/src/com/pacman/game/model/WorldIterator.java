package com.pacman.game.model;

import java.util.Iterator;

public class WorldIterator implements Iterator<GameElement>
{
    private World _world;
    private Iterator<GameElement> _mazeIterator;
    int _i;

    public WorldIterator(World world) {
        this._world = world;
        this._mazeIterator = this._world.getMaze().iterator();
        this._i = 0; /* 0 = maze, 1 = pacman */
    }

    @Override
    public boolean hasNext() {
        return (this._i < 2);
    }

    @Override
    public GameElement next() {
        if(_i == 0){
            if (!this._mazeIterator.hasNext())
                _i = 1; // on passe à Pacman
        }
        else _i++;

        GameElement res;
        switch(this._i) {
            case 0 : return this._mazeIterator.next();
            case 1 : return this._world.getPacman();
            case 2 : return this._world.getGhost1();
            default : return null;  /* erreur */
        }
    }

    @Override
    public void remove() {

    }
}