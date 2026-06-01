package com.mycompany.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProgramTest {

    @Test
    public void gridStartsEmpty() {
        Program p = new Program();
        assertEquals(Program.NONE, p.getCell(0, 0));
        assertEquals(Program.NONE, p.getCell(1, 1));
        assertEquals(Program.NONE, p.getCell(2, 2));
    }

    @Test
    public void firstTurnIsX() {
        Program p = new Program();
        assertEquals(Program.X, p.getTurn());
    }

    @Test
    public void placeValidMove() {
        Program p = new Program();
        assertTrue(p.place(0, 0));
        assertEquals(Program.X, p.getCell(0, 0));
        assertEquals(Program.O, p.getTurn());
    }

    @Test
    public void placeInvalidRowLow() {
        Program p = new Program();
        assertFalse(p.place(-1, 0));
    }

    @Test
    public void placeInvalidRowHigh() {
        Program p = new Program();
        assertFalse(p.place(3, 0));
    }

    @Test
    public void placeInvalidColLow() {
        Program p = new Program();
        assertFalse(p.place(0, -1));
    }

    @Test
    public void placeInvalidColHigh() {
        Program p = new Program();
        assertFalse(p.place(0, 3));
    }

    @Test
    public void placeOccupiedCell() {
        Program p = new Program();
        p.place(1, 1);
        assertFalse(p.place(1, 1));
    }

    @Test
    public void turnSwitchesAfterMove() {
        Program p = new Program();
        p.place(0, 0);
        assertEquals(Program.O, p.getTurn());
        p.place(0, 1);
        assertEquals(Program.X, p.getTurn());
    }

    @Test
    public void clearGridResetsEverything() {
        Program p = new Program();
        p.place(0, 0);
        p.place(1, 1);
        p.clearGrid();
        assertEquals(Program.NONE, p.getCell(0, 0));
        assertEquals(Program.NONE, p.getCell(1, 1));
        assertEquals(Program.X, p.getTurn());
    }

    @Test
    public void findWinnerRowX() {
        Program p = new Program();
        p.place(0, 0); p.place(1, 0);
        p.place(0, 1); p.place(1, 1);
        p.place(0, 2);
        assertEquals(Program.X, p.findWinner());
    }

    @Test
    public void findWinnerColO() {
        Program p = new Program();
        p.place(0, 0); p.place(1, 0);
        p.place(0, 1); p.place(1, 1);
        p.place(2, 2); p.place(1, 2);
        assertEquals(Program.O, p.findWinner());
    }

    @Test
    public void findWinnerDiag() {
        Program p = new Program();
        p.place(0, 0); p.place(0, 1);
        p.place(1, 1); p.place(0, 2);
        p.place(2, 2);
        assertEquals(Program.X, p.findWinner());
    }

    @Test
    public void findWinnerAntiDiag() {
        Program p = new Program();
        p.place(0, 2); p.place(0, 0);
        p.place(1, 1); p.place(0, 1);
        p.place(2, 0);
        assertEquals(Program.X, p.findWinner());
    }

    @Test
    public void noWinnerOnEmpty() {
        Program p = new Program();
        assertEquals(Program.NONE, p.findWinner());
    }

    @Test
    public void isFullOnEmpty() {
        Program p = new Program();
        assertFalse(p.isFull());
    }

    @Test
    public void isFullAfterAllMoves() {
        Program p = new Program();
        p.place(0, 0); p.place(0, 1); p.place(0, 2);
        p.place(1, 0); p.place(1, 1); p.place(1, 2);
        p.place(2, 0); p.place(2, 1); p.place(2, 2);
        assertTrue(p.isFull());
    }

    @Test
    public void isEndedFalseAtStart() {
        Program p = new Program();
        assertFalse(p.isEnded());
    }

    @Test
    public void isEndedAfterWin() {
        Program p = new Program();
        p.place(0, 0); p.place(1, 0);
        p.place(0, 1); p.place(1, 1);
        p.place(0, 2);
        assertTrue(p.isEnded());
    }

    @Test
    public void minimaxWinX() {
        Program p = new Program();
        p.place(0, 0); p.place(1, 0);
        p.place(0, 1); p.place(1, 1);
        p.place(0, 2);
        int v = p.minimax(0, true);
        assertTrue(v > 0);
    }

    @Test
    public void minimaxWinO() {
        Program p = new Program();
        p.place(0, 0); p.place(1, 0);
        p.place(2, 2); p.place(1, 1);
        p.place(2, 1); p.place(1, 2);
        int v = p.minimax(0, false);
        assertTrue(v < 0);
    }

    @Test
    public void minimaxDraw() {
        Program p = new Program();
        p.place(0, 0); p.place(0, 1); p.place(0, 2);
        p.place(1, 1); p.place(1, 0); p.place(1, 2);
        p.place(2, 1); p.place(2, 0); p.place(2, 2);
        assertEquals(0, p.minimax(0, true));
    }

    @Test
    public void bestMoveReturnsValid() {
        Program p = new Program();
        int[] m = p.bestMove();
        assertNotNull(m);
        assertEquals(2, m.length);
        assertTrue(m[0] >= 0 && m[0] < 3);
        assertTrue(m[1] >= 0 && m[1] < 3);
    }

    @Test
    public void bestMoveBlocksOpponent() {
        Program p = new Program();
        p.place(0, 0); p.place(1, 0);
        p.place(0, 1); p.place(1, 1);
        int[] m = p.bestMove();
        assertEquals(0, m[0]);
        assertEquals(2, m[1]);
    }

    @Test
    public void getCellAfterPlace() {
        Program p = new Program();
        p.place(2, 2);
        assertEquals(Program.X, p.getCell(2, 2));
        assertEquals(Program.NONE, p.getCell(0, 0));
    }

    @Test
    public void isEndedFalseMidGame() {
        Program p = new Program();
        p.place(0, 0);
        assertFalse(p.isEnded());
    }
}
