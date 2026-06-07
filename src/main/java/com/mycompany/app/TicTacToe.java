package com.mycompany.app;

public class TicTacToe {
    private Field field;
    private Symbol activeSymbol;

    public TicTacToe() {
        field = new Field();
        activeSymbol = Symbol.PLAYER_X;
    }

    public void restart() {
        field.reset();
        activeSymbol = Symbol.PLAYER_X;
    }

    public Symbol getActiveSymbol() {
        return activeSymbol;
    }

    public Symbol getSymbolAt(int row, int col) {
        return field.getSymbol(row, col);
    }

    public boolean executeMove(int row, int col) {
        if (!field.placeSymbol(row, col, activeSymbol)) {
            return false;
        }
        togglePlayer();
        return true;
    }

    private void togglePlayer() {
        activeSymbol = activeSymbol.getOpposite();
    }

    public Symbol determineWinner() {
        for (int row = 0; row < 3; row++) {
            if (field.getSymbol(row, 0) != Symbol.BLANK &&
                field.getSymbol(row, 0) == field.getSymbol(row, 1) &&
                field.getSymbol(row, 1) == field.getSymbol(row, 2)) {
                return field.getSymbol(row, 0);
            }
        }

        for (int col = 0; col < 3; col++) {
            if (field.getSymbol(0, col) != Symbol.BLANK &&
                field.getSymbol(0, col) == field.getSymbol(1, col) &&
                field.getSymbol(1, col) == field.getSymbol(2, col)) {
                return field.getSymbol(0, col);
            }
        }

        if (field.getSymbol(0, 0) != Symbol.BLANK &&
            field.getSymbol(0, 0) == field.getSymbol(1, 1) &&
            field.getSymbol(1, 1) == field.getSymbol(2, 2)) {
            return field.getSymbol(0, 0);
        }

        if (field.getSymbol(0, 2) != Symbol.BLANK &&
            field.getSymbol(0, 2) == field.getSymbol(1, 1) &&
            field.getSymbol(1, 1) == field.getSymbol(2, 0)) {
            return field.getSymbol(0, 2);
        }

        return Symbol.BLANK;
    }

    public boolean isFieldComplete() {
        return !field.hasAvailableSpaces();
    }

    public GameStatus getCurrentStatus() {
        Symbol winner = determineWinner();
        if (winner == Symbol.PLAYER_X) {
            return GameStatus.VICTORY_X;
        }
        if (winner == Symbol.PLAYER_O) {
            return GameStatus.VICTORY_O;
        }
        if (isFieldComplete()) {
            return GameStatus.STALEMATE;
        }
        return GameStatus.ONGOING;
    }

    public int evaluatePosition(int depth, boolean maximizing) {
        GameStatus status = getCurrentStatus();
        
        if (status == GameStatus.VICTORY_X) {
            return 10 - depth;
        }
        if (status == GameStatus.VICTORY_O) {
            return depth - 10;
        }
        if (status == GameStatus.STALEMATE) {
            return 0;
        }

        if (maximizing) {
            int bestValue = Integer.MIN_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (field.getSymbol(row, col) == Symbol.BLANK) {
                        field.placeSymbol(row, col, Symbol.PLAYER_X);
                        int value = evaluatePosition(depth + 1, false);
                        field.clearSymbol(row, col);
                        bestValue = Math.max(bestValue, value);
                    }
                }
            }
            return bestValue;
        } else {
            int worstValue = Integer.MAX_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (field.getSymbol(row, col) == Symbol.BLANK) {
                        field.placeSymbol(row, col, Symbol.PLAYER_O);
                        int value = evaluatePosition(depth + 1, true);
                        field.clearSymbol(row, col);
                        worstValue = Math.min(worstValue, value);
                    }
                }
            }
            return worstValue;
        }
    }

    public int[] calculateBestMove() {
        int topScore = Integer.MIN_VALUE;
        int[] optimalPosition = {-1, -1};

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (field.getSymbol(row, col) == Symbol.BLANK) {
                    field.placeSymbol(row, col, Symbol.PLAYER_X);
                    int score = evaluatePosition(0, false);
                    field.clearSymbol(row, col);
                    
                    if (score > topScore) {
                        topScore = score;
                        optimalPosition[0] = row;
                        optimalPosition[1] = col;
                    }
                }
            }
        }
        return optimalPosition;
    }
}