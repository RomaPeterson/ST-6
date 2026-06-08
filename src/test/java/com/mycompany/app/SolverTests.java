package com.mycompany.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolverTests {

    @Test
    public void testInitialCurrent() {
        GameBoard b = new GameBoard();
        Solver s = new Solver(b);
        assertEquals(Player.FIRST, s.getCurrent());
    }

    @Test
    public void testApply() {
        GameBoard b = new GameBoard();
        Solver s = new Solver(b);
        assertTrue(s.apply(0));
        assertEquals(Player.FIRST, b.get(0));
        assertEquals(Player.SECOND, s.getCurrent());
    }

    @Test
    public void testApplyOccupiedFails() {
        GameBoard b = new GameBoard();
        Solver s = new Solver(b);
        s.apply(0);
        assertFalse(s.apply(0));
    }

    @Test
    public void testSetCurrent() {
        GameBoard b = new GameBoard();
        Solver s = new Solver(b);
        s.setCurrent(Player.SECOND);
        assertEquals(Player.SECOND, s.getCurrent());
    }

    @Test
    public void testEvaluateActive() {
        GameBoard b = new GameBoard();
        Solver s = new Solver(b);
        assertEquals(Outcome.ACTIVE, s.evaluate());
    }

    @Test
    public void testEvaluateWin() {
        GameBoard b = new GameBoard();
        b.set(0, Player.FIRST);
        b.set(1, Player.FIRST);
        b.set(2, Player.FIRST);
        Solver s = new Solver(b);
        assertEquals(Outcome.WIN_FIRST, s.evaluate());
    }

    @Test
    public void testEvaluateDraw() {
        GameBoard b = new GameBoard();
        b.set(0, Player.FIRST); b.set(1, Player.SECOND); b.set(2, Player.FIRST);
        b.set(3, Player.FIRST); b.set(4, Player.FIRST); b.set(5, Player.SECOND);
        b.set(6, Player.SECOND); b.set(7, Player.FIRST); b.set(8, Player.SECOND);
        Solver s = new Solver(b);
        assertEquals(Outcome.DRAW, s.evaluate());
    }

    @Test
    public void testAnalyzeWin() {
        GameBoard b = new GameBoard();
        b.set(0, Player.FIRST); b.set(1, Player.FIRST); b.set(2, Player.FIRST);
        Solver s = new Solver(b);
        int score = s.analyze(0, true);
        assertTrue(score > 0);
    }

    @Test
    public void testAnalyzeLose() {
        GameBoard b = new GameBoard();
        b.set(3, Player.SECOND); b.set(4, Player.SECOND); b.set(5, Player.SECOND);
        Solver s = new Solver(b);
        int score = s.analyze(0, false);
        assertTrue(score < 0);
    }

    @Test
    public void testAnalyzeDraw() {
        GameBoard b = new GameBoard();
        b.set(0, Player.FIRST); b.set(1, Player.SECOND); b.set(2, Player.FIRST);
        b.set(3, Player.FIRST); b.set(4, Player.FIRST); b.set(5, Player.SECOND);
        b.set(6, Player.SECOND); b.set(7, Player.FIRST); b.set(8, Player.SECOND);
        Solver s = new Solver(b);
        assertEquals(0, s.analyze(0, true));
    }

    @Test
    public void testFindBestReturnsValidIndex() {
        GameBoard b = new GameBoard();
        Solver s = new Solver(b);
        int move = s.findBest();
        assertTrue(move >= 0 && move < 9);
    }

    @Test
    public void testFindBestWinningMove() {
        GameBoard b = new GameBoard();
        b.set(0, Player.FIRST);
        b.set(1, Player.FIRST);
        Solver s = new Solver(b);
        assertEquals(2, s.findBest());
    }

    @Test
    public void testFindBestBlocksOpponent() {
        GameBoard b = new GameBoard();
        b.set(0, Player.SECOND);
        b.set(3, Player.SECOND);
        b.set(1, Player.FIRST);
        b.set(2, Player.FIRST);
        Solver s = new Solver(b);
        int move = s.findBest();
        assertEquals(6, move);
    }

    @Test
    public void testReset() {
        GameBoard b = new GameBoard();
        Solver s = new Solver(b);
        s.apply(4);
        s.apply(5);
        s.reset();
        assertEquals(Player.EMPTY, b.get(4));
        assertEquals(Player.EMPTY, b.get(5));
        assertEquals(Player.FIRST, s.getCurrent());
    }
}