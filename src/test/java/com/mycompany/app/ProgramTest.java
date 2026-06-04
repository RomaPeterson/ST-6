package com.mycompany.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProgramTest {

    @Test
    public void cellEnumValues() {
        assertEquals('.', TicTacToeCell.EMPTY.toChar());
        assertEquals('X', TicTacToeCell.X.toChar());
        assertEquals('O', TicTacToeCell.O.toChar());
    }

    @Test
    public void cellFromChar() {
        assertEquals(TicTacToeCell.EMPTY, TicTacToeCell.fromChar('.'));
        assertEquals(TicTacToeCell.X, TicTacToeCell.fromChar('X'));
        assertEquals(TicTacToeCell.O, TicTacToeCell.fromChar('O'));
        assertEquals(TicTacToeCell.EMPTY, TicTacToeCell.fromChar('?'));
    }

    @Test
    public void gameStateValues() {
        assertEquals(GameState.PLAYING, GameState.valueOf("PLAYING"));
        assertEquals(GameState.X_WINS, GameState.valueOf("X_WINS"));
        assertEquals(GameState.O_WINS, GameState.valueOf("O_WINS"));
        assertEquals(GameState.DRAW, GameState.valueOf("DRAW"));
    }

    @Test
    public void gridStartsEmpty() {
        TicTacToeLogic g = new TicTacToeLogic();
        assertEquals(TicTacToeCell.EMPTY, g.cellAt(0, 0));
        assertEquals(TicTacToeCell.EMPTY, g.cellAt(1, 2));
        assertEquals(TicTacToeCell.EMPTY, g.cellAt(2, 1));
    }

    @Test
    public void xMovesFirst() {
        TicTacToeLogic g = new TicTacToeLogic();
        assertEquals(TicTacToeCell.X, g.getActive());
    }

    @Test
    public void validMoveWorks() {
        TicTacToeLogic g = new TicTacToeLogic();
        assertTrue(g.place(0, 0));
        assertEquals(TicTacToeCell.X, g.cellAt(0, 0));
        assertEquals(TicTacToeCell.O, g.getActive());
    }

    @Test
    public void invalidRowLow() {
        TicTacToeLogic g = new TicTacToeLogic();
        assertFalse(g.place(-1, 0));
    }

    @Test
    public void invalidRowHigh() {
        TicTacToeLogic g = new TicTacToeLogic();
        assertFalse(g.place(3, 0));
    }

    @Test
    public void invalidColLow() {
        TicTacToeLogic g = new TicTacToeLogic();
        assertFalse(g.place(0, -1));
    }

    @Test
    public void invalidColHigh() {
        TicTacToeLogic g = new TicTacToeLogic();
        assertFalse(g.place(0, 3));
    }

    @Test
    public void occupiedCell() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(1, 1);
        assertFalse(g.place(1, 1));
    }

    @Test
    public void turnSwitching() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(0, 0);
        assertEquals(TicTacToeCell.O, g.getActive());
        g.place(0, 1);
        assertEquals(TicTacToeCell.X, g.getActive());
        g.place(0, 2);
        assertEquals(TicTacToeCell.O, g.getActive());
    }

    @Test
    public void clearResetsAll() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(0, 0);
        g.place(1, 1);
        g.clear();
        assertEquals(TicTacToeCell.EMPTY, g.cellAt(0, 0));
        assertEquals(TicTacToeCell.EMPTY, g.cellAt(1, 1));
        assertEquals(TicTacToeCell.X, g.getActive());
    }

    @Test
    public void winRow0() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(0, 0); g.place(1, 0);
        g.place(0, 1); g.place(1, 1);
        g.place(0, 2);
        assertEquals(TicTacToeCell.X, g.checkWinner());
    }

    @Test
    public void winRow1() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(1, 0); g.place(0, 0);
        g.place(1, 1); g.place(0, 1);
        g.place(1, 2);
        assertEquals(TicTacToeCell.X, g.checkWinner());
    }

    @Test
    public void winRow2() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(2, 0); g.place(0, 0);
        g.place(2, 1); g.place(0, 1);
        g.place(2, 2);
        assertEquals(TicTacToeCell.X, g.checkWinner());
    }

    @Test
    public void winCol0() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(0, 0); g.place(0, 1);
        g.place(1, 0); g.place(1, 1);
        g.place(2, 0);
        assertEquals(TicTacToeCell.X, g.checkWinner());
    }

    @Test
    public void winCol1() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(0, 1); g.place(0, 0);
        g.place(1, 1); g.place(1, 0);
        g.place(2, 1);
        assertEquals(TicTacToeCell.X, g.checkWinner());
    }

    @Test
    public void winCol2() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(0, 2); g.place(0, 0);
        g.place(1, 2); g.place(1, 0);
        g.place(2, 2);
        assertEquals(TicTacToeCell.X, g.checkWinner());
    }

    @Test
    public void winDiagMain() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(0, 0); g.place(0, 1);
        g.place(1, 1); g.place(0, 2);
        g.place(2, 2);
        assertEquals(TicTacToeCell.X, g.checkWinner());
    }

    @Test
    public void winDiagSide() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(0, 2); g.place(0, 0);
        g.place(1, 1); g.place(0, 1);
        g.place(2, 0);
        assertEquals(TicTacToeCell.X, g.checkWinner());
    }

    @Test
    public void oWinsRow() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(2, 2); g.place(0, 0);
        g.place(2, 0); g.place(0, 1);
        g.place(1, 0); g.place(0, 2);
        assertEquals(TicTacToeCell.O, g.checkWinner());
    }

    @Test
    public void oWinsCol() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(2, 2); g.place(0, 0);
        g.place(1, 2); g.place(1, 0);
        g.place(2, 1); g.place(2, 0);
        assertEquals(TicTacToeCell.O, g.checkWinner());
    }

    @Test
    public void oWinsDiag() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(0, 1); g.place(0, 0);
        g.place(0, 2); g.place(1, 1);
        g.place(2, 0); g.place(2, 2);
        assertEquals(TicTacToeCell.O, g.checkWinner());
    }

    @Test
    public void noWinnerEmpty() {
        TicTacToeLogic g = new TicTacToeLogic();
        assertEquals(TicTacToeCell.EMPTY, g.checkWinner());
    }

    @Test
    public void noWinnerMid() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(0, 0);
        g.place(1, 1);
        assertEquals(TicTacToeCell.EMPTY, g.checkWinner());
    }

    @Test
    public void notFullStart() {
        TicTacToeLogic g = new TicTacToeLogic();
        assertFalse(g.isFull());
    }

    @Test
    public void notFullMid() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(0, 0);
        assertFalse(g.isFull());
    }

    @Test
    public void fullBoard() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(0, 0); g.place(0, 1); g.place(0, 2);
        g.place(1, 0); g.place(1, 1); g.place(1, 2);
        g.place(2, 0); g.place(2, 1); g.place(2, 2);
        assertTrue(g.isFull());
    }

    @Test
    public void statePlaying() {
        TicTacToeLogic g = new TicTacToeLogic();
        assertEquals(GameState.PLAYING, g.getState());
    }

    @Test
    public void stateXWins() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(0, 0); g.place(1, 0);
        g.place(0, 1); g.place(1, 1);
        g.place(0, 2);
        assertEquals(GameState.X_WINS, g.getState());
    }

    @Test
    public void stateOWins() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(2, 2); g.place(0, 0);
        g.place(1, 2); g.place(0, 1);
        g.place(2, 0); g.place(0, 2);
        assertEquals(GameState.O_WINS, g.getState());
    }

    @Test
    public void stateDraw() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(0, 0); g.place(0, 1); g.place(0, 2);
        g.place(1, 1); g.place(1, 0); g.place(1, 2);
        g.place(2, 1); g.place(2, 0); g.place(2, 2);
        assertEquals(GameState.DRAW, g.getState());
    }

    @Test
    public void minimaxXWin() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(0, 0); g.place(1, 0);
        g.place(0, 1); g.place(1, 1);
        g.place(0, 2);
        int v = g.minimax(0, true);
        assertTrue(v > 0);
    }

    @Test
    public void minimaxOWin() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(0, 0); g.place(1, 0);
        g.place(2, 2); g.place(1, 1);
        g.place(2, 1); g.place(1, 2);
        int v = g.minimax(0, false);
        assertTrue(v < 0);
    }

    @Test
    public void minimaxDraw() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(0, 0); g.place(0, 1); g.place(0, 2);
        g.place(1, 1); g.place(1, 0); g.place(1, 2);
        g.place(2, 1); g.place(2, 0); g.place(2, 2);
        assertEquals(0, g.minimax(0, true));
    }

    @Test
    public void minimaxEmptyMax() {
        TicTacToeLogic g = new TicTacToeLogic();
        int v = g.minimax(0, true);
        assertTrue(v >= -10 && v <= 10);
    }

    @Test
    public void minimaxEmptyMin() {
        TicTacToeLogic g = new TicTacToeLogic();
        int v = g.minimax(0, false);
        assertTrue(v >= -10 && v <= 10);
    }

    @Test
    public void bestMoveValid() {
        TicTacToeLogic g = new TicTacToeLogic();
        int[] m = g.bestMove();
        assertNotNull(m);
        assertEquals(2, m.length);
        assertTrue(m[0] >= 0 && m[0] <= 2);
        assertTrue(m[1] >= 0 && m[1] <= 2);
    }

    @Test
    public void bestMoveBlocks() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(0, 0); g.place(0, 1);
        g.place(1, 0); g.place(0, 2);
        int[] m = g.bestMove();
        assertTrue(m[0] >= 0 && m[0] <= 2);
        assertTrue(m[1] >= 0 && m[1] <= 2);
        assertEquals(TicTacToeCell.EMPTY, g.cellAt(m[0], m[1]));
    }

    @Test
    public void bestMoveTakesWin() {
        TicTacToeLogic g = new TicTacToeLogic();
        g.place(0, 0); g.place(1, 0);
        g.place(0, 1); g.place(1, 1);
        int[] m = g.bestMove();
        assertEquals(0, m[0]);
        assertEquals(2, m[1]);
    }

    @Test
    public void mainRuns() {
        Program.main(new String[]{});
    }
}