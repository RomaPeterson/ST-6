package com.mycompany.app;

public class Program {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        System.out.println("Tic-Tac-Toe AI Engine Initialized");
        System.out.println("Position [0,0] contains: " + game.getSymbolAt(0, 0).display());
    }
}