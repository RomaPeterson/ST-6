package com.mycompany.app;

public class Field {
    private Symbol[][] board;
    private static final int DIMENSION = 3;

    public Field() {
        board = new Symbol[DIMENSION][DIMENSION];
        reset();
    }

    public void reset() {
        for (int row = 0; row < DIMENSION; row++) {
            for (int col = 0; col < DIMENSION; col++) {
                board[row][col] = Symbol.BLANK;
            }
        }
    }

    public Symbol getSymbol(int row, int col) {
        if (!isWithinBounds(row, col)) {
            return Symbol.BLANK;
        }
        return board[row][col];
    }

    public boolean placeSymbol(int row, int col, Symbol symbol) {
        if (!isWithinBounds(row, col)) {
            return false;
        }
        if (board[row][col] != Symbol.BLANK) {
            return false;
        }
        board[row][col] = symbol;
        return true;
    }

    public void clearSymbol(int row, int col) {
        if (isWithinBounds(row, col)) {
            board[row][col] = Symbol.BLANK;
        }
    }

    public boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < DIMENSION && col >= 0 && col < DIMENSION;
    }

    public boolean hasAvailableSpaces() {
        for (int row = 0; row < DIMENSION; row++) {
            for (int col = 0; col < DIMENSION; col++) {
                if (board[row][col] == Symbol.BLANK) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getDimension() {
        return DIMENSION;
    }
}