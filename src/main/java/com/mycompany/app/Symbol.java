package com.mycompany.app;

public enum Symbol {
    BLANK('-'),
    PLAYER_X('X'),
    PLAYER_O('O');

    private final char mark;

    Symbol(char mark) {
        this.mark = mark;
    }

    public char display() {
        return mark;
    }

    public static Symbol fromCharacter(char ch) {
        for (Symbol s : values()) {
            if (s.mark == ch) return s;
        }
        return BLANK;
    }

    public Symbol getOpposite() {
        if (this == PLAYER_X) return PLAYER_O;
        if (this == PLAYER_O) return PLAYER_X;
        return BLANK;
    }
}