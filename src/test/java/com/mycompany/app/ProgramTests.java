package com.mycompany.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProgramTests {

    @Test
    public void testMainDoesNotCrash() {
        Program.main(new String[]{});
    }

    @Test
    public void testGameFlow() {
        GameBoard b = new GameBoard();
        Solver s = new Solver(b);
        s.apply(0);
        s.apply(4);
        s.apply(1);
        s.apply(5);
        assertEquals(Player.FIRST, b.get(0));
        assertEquals(Player.SECOND, b.get(4));
        assertEquals(Player.FIRST, b.get(1));
        assertEquals(Player.SECOND, b.get(5));
        assertTrue(s.evaluate() == Outcome.ACTIVE || s.evaluate() == Outcome.WIN_FIRST);
    }
}