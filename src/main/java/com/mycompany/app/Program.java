package com.mycompany.app;

public class Program {

    public static final char NONE = '.';
    public static final char X = 'X';
    public static final char O = 'O';

    private char[][] grid;
    private char turn;

    public Program() {
        grid = new char[3][3];
        turn = X;
        clearGrid();
    }

    public void clearGrid() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                grid[r][c] = NONE;
            }
        }
        turn = X;
    }

    public char getTurn() {
        return turn;
    }

    public char getCell(int r, int c) {
        return grid[r][c];
    }

    public boolean place(int r, int c) {
        if (r < 0 || r > 2 || c < 0 || c > 2) {
            return false;
        }
        if (grid[r][c] != NONE) {
            return false;
        }
        grid[r][c] = turn;
        turn = (turn == X) ? O : X;
        return true;
    }

    public char findWinner() {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] != NONE &&
                grid[i][0] == grid[i][1] &&
                grid[i][1] == grid[i][2]) {
                return grid[i][0];
            }
            if (grid[0][i] != NONE &&
                grid[0][i] == grid[1][i] &&
                grid[1][i] == grid[2][i]) {
                return grid[0][i];
            }
        }
        if (grid[0][0] != NONE &&
            grid[0][0] == grid[1][1] &&
            grid[1][1] == grid[2][2]) {
            return grid[0][0];
        }
        if (grid[0][2] != NONE &&
            grid[0][2] == grid[1][1] &&
            grid[1][1] == grid[2][0]) {
            return grid[0][2];
        }
        return NONE;
    }

    public boolean isFull() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (grid[r][c] == NONE) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isEnded() {
        return findWinner() != NONE || isFull();
    }

    public int minimax(int depth, boolean maximizing) {
        char w = findWinner();
        if (w == X) return 10 - depth;
        if (w == O) return depth - 10;
        if (isFull()) return 0;

        if (maximizing) {
            int best = Integer.MIN_VALUE;
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (grid[r][c] == NONE) {
                        grid[r][c] = X;
                        best = Math.max(best, minimax(depth + 1, false));
                        grid[r][c] = NONE;
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (grid[r][c] == NONE) {
                        grid[r][c] = O;
                        best = Math.min(best, minimax(depth + 1, true));
                        grid[r][c] = NONE;
                    }
                }
            }
            return best;
        }
    }

    public int[] bestMove() {
        int bestVal = Integer.MIN_VALUE;
        int[] move = {-1, -1};

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (grid[r][c] == NONE) {
                    grid[r][c] = X;
                    int val = minimax(0, false);
                    grid[r][c] = NONE;
                    if (val > bestVal) {
                        bestVal = val;
                        move[0] = r;
                        move[1] = c;
                    }
                }
            }
        }
        return move;
    }

    public static void main(String[] args) {
        Program p = new Program();
        System.out.println("TicTacToe minimax ready");
        System.out.println("Cell 0,0: " + p.getCell(0, 0));
    }
}
