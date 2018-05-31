package com.pacman.game.model;

import com.badlogic.gdx.math.Vector2;

import java.util.Iterator;

public class World implements Iterable<GameElement>
{
    private Pacman _pacman;
    private Maze _maze;
    private Ghost1 _ghost1;
    private Ghost2 _ghost2;

    public World() {
        this._pacman = new Pacman(new Vector2(1,1), this);
        this._ghost1 = new Ghost1(new Vector2(13,14),this);
        this._ghost2 = new Ghost2(new Vector2(13,14),this);

        this._maze = new Maze(this);
    }

    public Ghost1 getGhost1() { return _ghost1; }

    public Ghost2 getGhost2() { return _ghost2; }

    public Maze getMaze() { return this._maze; }

    public int getWidth() { return this._maze.getWidth(); }

    public int getHeight() { return this._maze.getHeight(); }

    public Pacman getPacman() { return this._pacman; }

    @Override
    public Iterator<GameElement> iterator() {
        return new WorldIterator(this);
    }
}