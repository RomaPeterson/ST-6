package com.mycompany.app;

public class TicTacToeLogic {

    private TicTacToeCell[][] grid;
    private TicTacToeCell active;

    public TicTacToeLogic() {
        grid = new TicTacToeCell[3][3];
        active = TicTacToeCell.X;
        clear();
    }

    public void clear() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = TicTacToeCell.EMPTY;
            }
        }
        active = TicTacToeCell.X;
    }

    public TicTacToeCell getActive() {
        return active;
    }

    public TicTacToeCell cellAt(int i, int j) {
        return grid[i][j];
    }

    public boolean place(int i, int j) {
        if (i < 0 || i > 2 || j < 0 || j > 2) return false;
        if (grid[i][j] != TicTacToeCell.EMPTY) return false;
        grid[i][j] = active;
        active = (active == TicTacToeCell.X) ? TicTacToeCell.O : TicTacToeCell.X;
        return true;
    }

    public TicTacToeCell checkWinner() {
        for (int k = 0; k < 3; k++) {
            if (grid[k][0] != TicTacToeCell.EMPTY && grid[k][0] == grid[k][1] && grid[k][1] == grid[k][2]) return grid[k][0];
            if (grid[0][k] != TicTacToeCell.EMPTY && grid[0][k] == grid[1][k] && grid[1][k] == grid[2][k]) return grid[0][k];
        }
        if (grid[0][0] != TicTacToeCell.EMPTY && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) return grid[0][0];
        if (grid[0][2] != TicTacToeCell.EMPTY && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) return grid[0][2];
        return TicTacToeCell.EMPTY;
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == TicTacToeCell.EMPTY) return false;
            }
        }
        return true;
    }

    public GameState getState() {
        TicTacToeCell w = checkWinner();
        if (w == TicTacToeCell.X) return GameState.X_WINS;
        if (w == TicTacToeCell.O) return GameState.O_WINS;
        if (isFull()) return GameState.DRAW;
        return GameState.PLAYING;
    }

    public int minimax(int depth, boolean maximize) {
        GameState s = getState();
        if (s == GameState.X_WINS) return 10 - depth;
        if (s == GameState.O_WINS) return depth - 10;
        if (s == GameState.DRAW) return 0;

        if (maximize) {
            int top = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid[i][j] == TicTacToeCell.EMPTY) {
                        grid[i][j] = TicTacToeCell.X;
                        int val = minimax(depth + 1, false);
                        grid[i][j] = TicTacToeCell.EMPTY;
                        if (val > top) top = val;
                    }
                }
            }
            return top;
        } else {
            int low = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid[i][j] == TicTacToeCell.EMPTY) {
                        grid[i][j] = TicTacToeCell.O;
                        int val = minimax(depth + 1, true);
                        grid[i][j] = TicTacToeCell.EMPTY;
                        if (val < low) low = val;
                    }
                }
            }
            return low;
        }
    }

    public int[] bestMove() {
        int best = Integer.MIN_VALUE;
        int[] move = {-1, -1};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == TicTacToeCell.EMPTY) {
                    grid[i][j] = TicTacToeCell.X;
                    int grade = minimax(0, false);
                    grid[i][j] = TicTacToeCell.EMPTY;
                    if (grade > best) {
                        best = grade;
                        move[0] = i;
                        move[1] = j;
                    }
                }
            }
        }
        return move;
    }
}