package com.pacman.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.pacman.game.model.COR.MazeCOR;

public class Maze implements Iterable<GameElement>{
    /* 0 : mur, 1 : vide, 2 : intersection, 3 : barriere fantomes */
    private World _world;
    private int _height;
    private int _width;
    private int nbPellet;
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
            {1, 1, 1, 1, 1, 1, 2, 1, 1, 8, 0, 6, 6, 6, 6, 6, 6, 0, 8, 1, 1, 2, 1, 1, 1, 1, 1, 1},
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
        this.nbPellet=0;
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
                if(element instanceof Pellet || element instanceof IntersectionPellet){
                    nbPellet=nbPellet+1;
                }
                y = (++y % this._height);
            }
            x++;
        }

        int startx=(int)_world.getPacman()._pos.x;
        int starty=(int)_world.getPacman()._pos.y;
        this._laby2[startx][starty]=new Intersection(new Vector2(startx,starty),_world);
        nbPellet=nbPellet-1;
    }

    public void mange(GameElement ge){

        if(ge instanceof Super || ge instanceof IntersectionPellet)
            set((int) ge._pos.x, (int) ge._pos.y, new Intersection(new Vector2((int) ge._pos.x, (int) ge._pos.y), _world));
        else if(ge instanceof Pellet)
            set((int) ge._pos.x, (int) ge._pos.y, new Vide(new Vector2((int) ge._pos.x, (int) ge._pos.y), _world));
        nbPellet=nbPellet-1;
    }

    public int getNbPellet() {
        return nbPellet;
    }

    public void setNbPellet(int nbPellet) {
        this.nbPellet = nbPellet;
    }

    public GameElement get(int x, int y) { return this._laby2[x][y]; }

    public Barriere[] getBarierres(){
        return _barrieres;
    }

    public void enleveBarriere(){
        for(Barriere barriere:_barrieres){
            _laby2[(int)barriere.getPosition().x][(int)barriere.getPosition().y]=new BarriereOpen(new Vector2(barriere.getPosition().x,barriere.getPosition().y),this._world);
        }
    }

    public int getHeight() { return _height; }

    public int getWidth()  { return _width; }

    public void set(int x, int y, GameElement ge) {
        this._laby2[x][y]=ge;
    }

    public void marquerTousLesSommets(int marque) {
        Iterator it = iterator();
        while (it.hasNext())
            ((GameElement) it.next()).marquer(marque);
    }

    public LinkedList parcoursEnLargeur(GameElement depart,GameElement arrivee,Boolean avecBarriere) {
        marquerTousLesSommets(0);
        LinkedList file = new LinkedList();
        LinkedList chemin = new LinkedList();
        HashMap<GameElement,GameElement> liste=new  HashMap<GameElement,GameElement>();
        liste.put(depart,null);
        depart.marquer(1);
        GameElement elementArrivee=get((int)arrivee._pos.x,(int)arrivee._pos.y);
        file.addLast(depart);
        while (file.size() > 0) {
            GameElement u = (GameElement) file.removeFirst();
            ArrayList<GameElement> v;
            if(avecBarriere){
                v = sommetsVoisinsBarriere(u);
            }
            else{
                v = sommetsVoisins(u);
            }
            for (int i=0;i<v.size();i++){
                if (v.get(i).marque == 0) {
                    liste.put(v.get(i),u);
                    v.get(i).marquer(1);
                    file.addLast(v.get(i));
                }
            }
            u.marquer(2);
        }
        while (liste.get(elementArrivee)!=null){
            elementArrivee=liste.get(elementArrivee);
            chemin.addFirst(elementArrivee);
        }
        chemin.addLast(arrivee);
        return chemin;
    }

    public ArrayList<GameElement> sommetsVoisins(GameElement s) {
        ArrayList<GameElement> liste=new ArrayList<GameElement>();
        GameElement geUp = null,geDown=null,geLeft=null,geRight=null;
        if(s._pos.y + 1<getHeight()){
             geUp = get((int)s._pos.x, (int) s._pos.y + 1);
        }
        if(s._pos.y - 1>=0){
            geDown = get((int) s._pos.x, (int) s._pos.y - 1);
        }
        if(s._pos.x + 1<getWidth()){
            geRight = get((int) s._pos.x + 1, (int) s._pos.y);
        }
        if(s._pos.y - 1>=0){
            geLeft = get((int) s._pos.x - 1, (int) s._pos.y);
        }

        if(geUp!=null && !(geUp instanceof Block || geUp instanceof Barriere)){
            liste.add(geUp);
        }
        if(geDown!=null &&!(geDown instanceof Block || geDown instanceof Barriere)){
            liste.add(geDown);
        }
        if(geLeft!=null &&!(geLeft instanceof Block || geLeft instanceof Barriere)){
            liste.add(geLeft);
        }
        if(geRight!=null &&!(geRight instanceof Block || geRight instanceof Barriere)){
            liste.add(geRight);
        }

        return liste;
    }

    public ArrayList<GameElement> sommetsVoisinsBarriere(GameElement s) {
        ArrayList<GameElement> liste=new ArrayList<GameElement>();
        GameElement geUp = null,geDown=null,geLeft=null,geRight=null;
        if(s._pos.y + 1<getHeight()){
            geUp = get((int)s._pos.x, (int) s._pos.y + 1);
        }
        if(s._pos.y - 1>=0){
            geDown = get((int) s._pos.x, (int) s._pos.y - 1);
        }
        if(s._pos.x + 1<getWidth()){
            geRight = get((int) s._pos.x + 1, (int) s._pos.y);
        }
        if(s._pos.y - 1>=0){
            geLeft = get((int) s._pos.x - 1, (int) s._pos.y);
        }

        if(geUp!=null && !(geUp instanceof Block)){
            liste.add(geUp);
        }
        if(geDown!=null &&!(geDown instanceof Block)){
            liste.add(geDown);
        }
        if(geLeft!=null &&!(geLeft instanceof Block)){
            liste.add(geLeft);
        }
        if(geRight!=null &&!(geRight instanceof Block)){
            liste.add(geRight);
        }

        return liste;
    }

}