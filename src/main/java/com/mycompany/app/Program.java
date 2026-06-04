package com.mycompany.app;

public class Program {
    public static void main(String[] args) {
        TicTacToeLogic game = new TicTacToeLogic();
        System.out.println("TicTacToe Minimax Ready");
        System.out.println("Cell(0,0) = " + game.cellAt(0, 0).toChar());
    }
}