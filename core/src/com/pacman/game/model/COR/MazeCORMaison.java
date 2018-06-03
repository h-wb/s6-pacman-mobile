package com.pacman.game.model.COR;

import com.badlogic.gdx.math.Vector2;
import com.pacman.game.model.GameElement;
import com.pacman.game.model.IntersectionPellet;
import com.pacman.game.model.Maison;
import com.pacman.game.model.World;

public class MazeCORMaison implements MazeCORinterface {

    private MazeCORinterface next;

    @Override
    public void setNext(MazeCORinterface next) {
        this.next=next;
    }

    @Override
    public GameElement build(World w, int elementType, int x, int y) {
        if(elementType == 6){
            return new Maison(new Vector2(x,y),w);
        }
        else{
            return this.next.build(w, elementType, x, y);
        }
    }
}
