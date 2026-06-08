package com.mycompany.app;

public class Solver {
    private GameBoard board;
    private Player current;

    public Solver(GameBoard board) {
        this.board = board;
        this.current = Player.FIRST;
    }

    public Player getCurrent() {
        return current;
    }

    public void setCurrent(Player p) {
        this.current = p;
    }

    public boolean apply(int i) {
        if (!board.set(i, current)) return false;
        current = current.next();
        return true;
    }

    public Outcome evaluate() {
        Player w = board.checkWin();
        if (w == Player.FIRST) return Outcome.WIN_FIRST;
        if (w == Player.SECOND) return Outcome.WIN_SECOND;
        if (!board.hasSpace()) return Outcome.DRAW;
        return Outcome.ACTIVE;
    }

    // минимаксный перебор
    public int analyze(int depth, boolean max) {
        Outcome r = evaluate();
        if (r == Outcome.WIN_FIRST) return 10 - depth;
        if (r == Outcome.WIN_SECOND) return depth - 10;
        if (r == Outcome.DRAW) return 0;

        int best;
        if (max) {
            best = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board.isFree(i)) {
                    board.set(i, Player.FIRST);
                    int val = analyze(depth + 1, false);
                    board.unset(i);
                    if (val > best) best = val;
                }
            }
        } else {
            best = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board.isFree(i)) {
                    board.set(i, Player.SECOND);
                    int val = analyze(depth + 1, true);
                    board.unset(i);
                    if (val < best) best = val;
                }
            }
        }
        return best;
    }

    // поиск лучшего хода для текущего игрока
    public int findBest() {
        int bestVal = Integer.MIN_VALUE;
        int bestIdx = -1;
        for (int i = 0; i < 9; i++) {
            if (board.isFree(i)) {
                board.set(i, Player.FIRST);
                int val = analyze(0, false);
                board.unset(i);
                if (val > bestVal) {
                    bestVal = val;
                    bestIdx = i;
                }
            }
        }
        return bestIdx;
    }

    public void reset() {
        board.clear();
        current = Player.FIRST;
    }
}