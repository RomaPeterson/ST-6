package com.mycompany.app;

public class Program {
    public static void main(String[] args) {
        GameBoard b = new GameBoard();
        Solver s = new Solver(b);

        b.set(0, Player.FIRST);
        b.set(4, Player.SECOND);
        b.set(1, Player.FIRST);
        b.set(5, Player.SECOND);

        int move = s.findBest();
        s.apply(move);

        System.out.println("Game engine ready");
        System.out.println("Best move: " + move);
        System.out.println("Outcome: " + s.evaluate());
    }
}