package com.mycompany.app;

public class GameBoard {
    private Player[] cells;

    public GameBoard() {
        cells = new Player[9];
        clear();
    }

    public void clear() {
        for (int i = 0; i < 9; i++) {
            cells[i] = Player.EMPTY;
        }
    }

    public Player get(int i) {
        if (i < 0 || i >= 9) return Player.EMPTY;
        return cells[i];
    }

    public boolean set(int i, Player p) {
        if (i < 0 || i >= 9) return false;
        if (cells[i] != Player.EMPTY) return false;
        cells[i] = p;
        return true;
    }

    public void unset(int i) {
        if (i >= 0 && i < 9) cells[i] = Player.EMPTY;
    }

    public boolean isFree(int i) {
        return i >= 0 && i < 9 && cells[i] == Player.EMPTY;
    }

    public boolean hasSpace() {
        for (int i = 0; i < 9; i++) {
            if (cells[i] == Player.EMPTY) return true;
        }
        return false;
    }

    // провека побды по всем линиям
    public Player checkWin() {
        for (int i = 0; i < 3; i++) {
            if (cells[i*3] != Player.EMPTY && cells[i*3] == cells[i*3+1] && cells[i*3+1] == cells[i*3+2])
                return cells[i*3];
            if (cells[i] != Player.EMPTY && cells[i] == cells[i+3] && cells[i+3] == cells[i+6])
                return cells[i];
        }
        if (cells[0] != Player.EMPTY && cells[0] == cells[4] && cells[4] == cells[8])
            return cells[0];
        if (cells[2] != Player.EMPTY && cells[2] == cells[4] && cells[4] == cells[6])
            return cells[2];
        return Player.EMPTY;
    }

    public int getSize() {
        return 9;
    }
}