package com.mycompany.app;

public enum TicTacToeCell {
    EMPTY('.'),
    X('X'),
    O('O');

    private final char symbol;

    TicTacToeCell(char symbol) {
        this.symbol = symbol;
    }

    public char toChar() {
        return symbol;
    }

    public static TicTacToeCell fromChar(char c) {
        for (TicTacToeCell v : values()) {
            if (v.symbol == c) return v;
        }
        return EMPTY;
    }
}