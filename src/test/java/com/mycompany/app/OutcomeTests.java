package com.mycompany.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class OutcomeTests {

    @Test
    public void testEnumValues() {
        Outcome[] vals = Outcome.values();
        assertEquals(4, vals.length);
    }

    @Test
    public void testValueOf() {
        assertEquals(Outcome.ACTIVE, Outcome.valueOf("ACTIVE"));
        assertEquals(Outcome.WIN_FIRST, Outcome.valueOf("WIN_FIRST"));
        assertEquals(Outcome.WIN_SECOND, Outcome.valueOf("WIN_SECOND"));
        assertEquals(Outcome.DRAW, Outcome.valueOf("DRAW"));
    }
}