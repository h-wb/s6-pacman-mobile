package com.pacman.game.model.COR;

public class MazeCOR {

    private static MazeCORinterface c1;

    public static MazeCORinterface getCOR()  {

            c1 = new MazeCORMur();
            MazeCORinterface c2 = new MazeCORIntersection();
            MazeCORinterface c3 = new MazeCORVide();
            MazeCORinterface c4 = new MazeCORBarriere();

            c1.setNext(c2);
            c2.setNext(c3);
            c3.setNext(c4);

            return c1;

    }
}
