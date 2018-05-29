package com.pacman.game.model;

import com.badlogic.gdx.math.Vector2;

import java.util.Iterator;

public class World implements Iterable<GameElement>
{
    private Pacman _pacman;
    private Maze _maze;

    public World() {
        this._pacman = new Pacman(new Vector2(1,1), this);
        this._maze = new Maze(this);

    }

    public Maze getMaze() { return this._maze; }

    public int getWidth() { return this._maze.getWidth(); }

    public int getHeight() { return this._maze.getHeight(); }

    public Pacman getPacman() { return this._pacman; }

    @Override
    public Iterator<GameElement> iterator() {
        return new WorldIterator(this);
    }
}