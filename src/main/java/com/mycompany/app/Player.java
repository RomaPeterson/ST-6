package com.mycompany.app;

public enum Player {
    EMPTY('·'),
    FIRST('X'),
    SECOND('O');

    private char sym;

    Player(char sym) {
        this.sym = sym;
    }

    public char getValue() {
        return sym;
    }

    public static Player from(char c) {
        if (c == 'X') return FIRST;
        if (c == 'O') return SECOND;
        return EMPTY;
    }

    public Player next() {
        if (this == FIRST) return SECOND;
        if (this == SECOND) return FIRST;
        return EMPTY;
    }
}