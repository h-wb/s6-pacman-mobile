package com.pacman.game.model.COR;

public class MazeCOR {

    private static MazeCORinterface c1;

    public static MazeCORinterface getCOR() {

        c1 = new MazeCORMur();
        MazeCORinterface c2 = new MazeCORIntersectionPellet();
        MazeCORinterface c3 = new MazeCORPellet();
        MazeCORinterface c4 = new MazeCORBarriere();
        MazeCORinterface c5 = new MazeCORSuper();
        MazeCORinterface c6 = new MazeCORMaison();
        MazeCORinterface c7 = new MazeCORVide();
        MazeCORinterface c8 = new MazeCORIntersection();

        c1.setNext(c2);
        c2.setNext(c3);
        c3.setNext(c4);
        c4.setNext(c5);
        c5.setNext(c6);
        c6.setNext(c7);
        c7.setNext(c8);

        return c1;

    }
}
