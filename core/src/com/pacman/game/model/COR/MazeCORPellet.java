package com.pacman.game.model.COR;

import com.badlogic.gdx.math.Vector2;
import com.pacman.game.model.Block;
import com.pacman.game.model.GameElement;
import com.pacman.game.model.Pellet;
import com.pacman.game.model.Vide;
import com.pacman.game.model.World;

public class MazeCORPellet implements MazeCORinterface{

    private MazeCORinterface next;

    @Override
    public void setNext(MazeCORinterface next) {
        this.next=next;
    }

    @Override
    public GameElement build(World w, int elementType, int x, int y) {
        if(elementType == 1){
            return new Pellet(new Vector2(x,y),w);
        }
        else{
            return this.next.build(w, elementType, x, y);
        }
    }
}