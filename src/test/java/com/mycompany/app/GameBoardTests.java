package com.mycompany.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameBoardTests {

    @Test
    public void testNewBoardIsEmpty() {
        GameBoard b = new GameBoard();
        for (int i = 0; i < 9; i++) {
            assertEquals(Player.EMPTY, b.get(i));
        }
    }

    @Test
    public void testSetAndGet() {
        GameBoard b = new GameBoard();
        assertTrue(b.set(0, Player.FIRST));
        assertEquals(Player.FIRST, b.get(0));
    }

    @Test
    public void testSetOccupiedCellFails() {
        GameBoard b = new GameBoard();
        b.set(3, Player.FIRST);
        assertFalse(b.set(3, Player.SECOND));
    }

    @Test
    public void testSetOutOfBoundsFails() {
        GameBoard b = new GameBoard();
        assertFalse(b.set(-1, Player.FIRST));
        assertFalse(b.set(9, Player.FIRST));
    }

    @Test
    public void testGetOutOfBoundsReturnsEmpty() {
        GameBoard b = new GameBoard();
        assertEquals(Player.EMPTY, b.get(-1));
        assertEquals(Player.EMPTY, b.get(10));
    }

    @Test
    public void testUnset() {
        GameBoard b = new GameBoard();
        b.set(5, Player.SECOND);
        b.unset(5);
        assertEquals(Player.EMPTY, b.get(5));
    }

    @Test
    public void testIsFree() {
        GameBoard b = new GameBoard();
        assertTrue(b.isFree(0));
        b.set(0, Player.FIRST);
        assertFalse(b.isFree(0));
        assertFalse(b.isFree(-1));
        assertFalse(b.isFree(9));
    }

    @Test
    public void testHasSpace() {
        GameBoard b = new GameBoard();
        assertTrue(b.hasSpace());
        for (int i = 0; i < 9; i++) b.set(i, Player.FIRST);
        assertFalse(b.hasSpace());
    }

    @Test
    public void testClear() {
        GameBoard b = new GameBoard();
        b.set(0, Player.FIRST);
        b.set(4, Player.SECOND);
        b.clear();
        for (int i = 0; i < 9; i++) {
            assertEquals(Player.EMPTY, b.get(i));
        }
    }

    @Test
    public void testWinRow() {
        GameBoard b = new GameBoard();
        b.set(0, Player.FIRST);
        b.set(1, Player.FIRST);
        b.set(2, Player.FIRST);
        assertEquals(Player.FIRST, b.checkWin());
    }

    @Test
    public void testWinColumn() {
        GameBoard b = new GameBoard();
        b.set(1, Player.SECOND);
        b.set(4, Player.SECOND);
        b.set(7, Player.SECOND);
        assertEquals(Player.SECOND, b.checkWin());
    }

    @Test
    public void testWinDiagonal() {
        GameBoard b = new GameBoard();
        b.set(0, Player.FIRST);
        b.set(4, Player.FIRST);
        b.set(8, Player.FIRST);
        assertEquals(Player.FIRST, b.checkWin());
    }

    @Test
    public void testWinAntiDiagonal() {
        GameBoard b = new GameBoard();
        b.set(2, Player.SECOND);
        b.set(4, Player.SECOND);
        b.set(6, Player.SECOND);
        assertEquals(Player.SECOND, b.checkWin());
    }

    @Test
    public void testNoWinner() {
        GameBoard b = new GameBoard();
        b.set(0, Player.FIRST);
        b.set(1, Player.SECOND);
        b.set(2, Player.FIRST);
        assertEquals(Player.EMPTY, b.checkWin());
    }

    @Test
    public void testGetSize() {
        GameBoard b = new GameBoard();
        assertEquals(9, b.getSize());
    }
}