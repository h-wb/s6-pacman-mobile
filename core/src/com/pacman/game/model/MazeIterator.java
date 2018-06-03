package com.pacman.game.model;

import java.util.Iterator;
import java.util.NoSuchElementException;
import com.pacman.game.model.Maze;

public class MazeIterator implements Iterator<GameElement> {

    private Maze _maze;
    int _i, _j;

    public MazeIterator(Maze maze) { this._maze = maze; _i = _j = 0; }

    @Override
    public boolean hasNext() { return (_i < this._maze.getHeight()) && (_j < this._maze.getWidth()); }

    @Override
    public GameElement next() {
        if(!this.hasNext()) throw new NoSuchElementException("No more game elements");
        GameElement gameElement;
        do {
            gameElement = this._maze.get(_j,_i);
            _i = (_i + 1) % this._maze.getHeight();
            if(_i == 0)
                _j++;
        } while(gameElement == null
                && this.hasNext());
        return gameElement;
    }

    @Override public void remove() {

    }

}