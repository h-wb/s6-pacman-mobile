package com.pacman.game.model;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.pacman.game.model.COR.MazeCOR;

public class Maze implements Iterable<GameElement>{
    /* 0 : mur, 1 : vide, 2 : intersection, 3 : barriere fantomes */
    private World _world;
    private int _height;
    private int _width;
    private Barriere[] _barrieres;
    //les int sont final code prof
    private GameElement[][] _laby2;
    private int[][] _laby1 = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 0, 0, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 0},
            {0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
            {0, 4, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 4, 0},
            {0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
            {0, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 0},
            {0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0},
            {0, 2, 1, 1, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 1, 1, 2, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 8, 7, 7, 8, 8, 8, 8, 7, 7, 8, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 7, 0, 0, 0, 3, 3, 0, 0, 0, 7, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 7, 0, 6, 6, 6, 6, 6, 6, 0, 7, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 2, 1, 1, 1, 1, 2, 1, 1, 8, 0, 6, 6, 6, 6, 6, 6, 0, 8, 1, 1, 2, 1, 1, 1, 1, 2, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 8, 7, 7, 7, 7, 7, 7, 7, 7, 8, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 0},
            {0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
            {0, 4, 1, 2, 0, 0, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 0, 0, 2, 1, 4, 0},
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0},
            {0, 2, 1, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 1, 2, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };



    public Maze(World w)
    {
        _world = w;

        this.init ();
    }

    @Override
    public Iterator<GameElement> iterator() {
        return new MazeIterator(this);
    }

    private void init ()
    {
        this._width = _laby1.length;
        this._height  = _laby1[0].length;
        this._laby2 = new GameElement[this._width][this._height];
        this._barrieres = new Barriere[2];

        int x = 0,y = 0,b=0;
        for(int[] t : _laby1) {
            for(int elementType : t) {
                GameElement element = MazeCOR.getCOR ().build (
                        this._world,
                        elementType,
                        x,
                        y);
                this._laby2[x][y] = element;
                if(element instanceof Barriere){
                    this._barrieres[b]=(Barriere) element;
                    b++;
                }
                y = (++y % this._height);
            }
            x++;
        }

        this._laby2[1][1]=new Intersection(new Vector2(1,1),_world);
        System.out.print(this.iterator().next());
    }

    public GameElement get(int x, int y) { return this._laby2[x][y]; }

    public Barriere[] getBarierres(){
        return _barrieres;
    }

    public void enleveBarriere(){
        for(Barriere barriere:_barrieres){
            _laby2[(int)barriere.getPosition().x][(int)barriere.getPosition().y]=new Vide(new Vector2(barriere.getPosition().x,barriere.getPosition().y),this._world);
        }
    }

    public int getHeight() { return _height; }

    public int getWidth()  { return _width; }

    public void set(int x, int y, GameElement ge) {
        this._laby2[x][y]=ge;
    }

}