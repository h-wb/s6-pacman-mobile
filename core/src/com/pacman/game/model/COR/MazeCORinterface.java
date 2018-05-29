package com.pacman.game.model.COR;

import com.pacman.game.model.Block;
import com.pacman.game.model.GameElement;
import com.pacman.game.model.World;

import java.util.List;

public interface MazeCORinterface {

    void setNext(MazeCORinterface next);

    GameElement build(World w, int elementType, int x, int y);
}
