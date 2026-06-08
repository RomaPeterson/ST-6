package com.mycompany.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTests {

    @Test
    public void testGetValue() {
        assertEquals('·', Player.EMPTY.getValue());
        assertEquals('X', Player.FIRST.getValue());
        assertEquals('O', Player.SECOND.getValue());
    }

    @Test
    public void testFrom() {
        assertEquals(Player.FIRST, Player.from('X'));
        assertEquals(Player.SECOND, Player.from('O'));
        assertEquals(Player.EMPTY, Player.from('Z'));
    }

    @Test
    public void testNext() {
        assertEquals(Player.SECOND, Player.FIRST.next());
        assertEquals(Player.FIRST, Player.SECOND.next());
        assertEquals(Player.EMPTY, Player.EMPTY.next());
    }
}