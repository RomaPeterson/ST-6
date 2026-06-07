package com.mycompany.app;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestSuite {

    private TicTacToe game;

    @Before
    public void initialize() {
        game = new TicTacToe();
    }

    @Test
    public void symbolDisplayValues() {
        assertEquals('-', Symbol.BLANK.display());
        assertEquals('X', Symbol.PLAYER_X.display());
        assertEquals('O', Symbol.PLAYER_O.display());
    }

    @Test
    public void symbolParsing() {
        assertEquals(Symbol.PLAYER_X, Symbol.fromCharacter('X'));
        assertEquals(Symbol.PLAYER_O, Symbol.fromCharacter('O'));
        assertEquals(Symbol.BLANK, Symbol.fromCharacter('-'));
        assertEquals(Symbol.BLANK, Symbol.fromCharacter('?'));
    }

    @Test
    public void symbolOpposite() {
        assertEquals(Symbol.PLAYER_O, Symbol.PLAYER_X.getOpposite());
        assertEquals(Symbol.PLAYER_X, Symbol.PLAYER_O.getOpposite());
        assertEquals(Symbol.BLANK, Symbol.BLANK.getOpposite());
    }

    @Test
    public void symbolEnumeration() {
        Symbol[] symbols = Symbol.values();
        assertEquals(3, symbols.length);
    }

    @Test
    public void gameStatusValues() {
        assertNotNull(GameStatus.ONGOING);
        assertNotNull(GameStatus.VICTORY_X);
        assertNotNull(GameStatus.VICTORY_O);
        assertNotNull(GameStatus.STALEMATE);
    }

    @Test
    public void gameStatusValueOf() {
        assertEquals(GameStatus.ONGOING, GameStatus.valueOf("ONGOING"));
        assertEquals(GameStatus.STALEMATE, GameStatus.valueOf("STALEMATE"));
    }

    @Test
    public void gameStatusEnumCount() {
        GameStatus[] statuses = GameStatus.values();
        assertEquals(4, statuses.length);
    }

    @Test
    public void fieldInitialization() {
        Field f = new Field();
        assertEquals(Symbol.BLANK, f.getSymbol(0, 0));
        assertEquals(Symbol.BLANK, f.getSymbol(2, 2));
        assertEquals(Symbol.BLANK, f.getSymbol(1, 1));
    }

    @Test
    public void fieldPlaceSymbol() {
        Field f = new Field();
        assertTrue(f.placeSymbol(0, 0, Symbol.PLAYER_X));
        assertEquals(Symbol.PLAYER_X, f.getSymbol(0, 0));
    }

    @Test
    public void fieldOutOfBoundsNegative() {
        Field f = new Field();
        assertFalse(f.isWithinBounds(-1, 0));
        assertFalse(f.isWithinBounds(0, -1));
    }

    @Test
    public void fieldOutOfBoundsLarge() {
        Field f = new Field();
        assertFalse(f.isWithinBounds(3, 0));
        assertFalse(f.isWithinBounds(0, 3));
    }

    @Test
    public void fieldValidBounds() {
        Field f = new Field();
        assertTrue(f.isWithinBounds(0, 0));
        assertTrue(f.isWithinBounds(2, 2));
        assertTrue(f.isWithinBounds(1, 1));
    }

    @Test
    public void fieldCannotOverwrite() {
        Field f = new Field();
        f.placeSymbol(1, 1, Symbol.PLAYER_X);
        assertFalse(f.placeSymbol(1, 1, Symbol.PLAYER_O));
    }

    @Test
    public void fieldClearSymbol() {
        Field f = new Field();
        f.placeSymbol(0, 0, Symbol.PLAYER_X);
        f.clearSymbol(0, 0);
        assertEquals(Symbol.BLANK, f.getSymbol(0, 0));
    }

    @Test
    public void fieldHasSpacesInitially() {
        Field f = new Field();
        assertTrue(f.hasAvailableSpaces());
    }

    @Test
    public void fieldNoSpacesWhenFull() {
        Field f = new Field();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                f.placeSymbol(i, j, Symbol.PLAYER_X);
            }
        }
        assertFalse(f.hasAvailableSpaces());
    }

    @Test
    public void fieldGetDimension() {
        Field f = new Field();
        assertEquals(3, f.getDimension());
    }

    @Test
    public void fieldReset() {
        Field f = new Field();
        f.placeSymbol(0, 0, Symbol.PLAYER_X);
        f.reset();
        assertEquals(Symbol.BLANK, f.getSymbol(0, 0));
    }

    @Test
    public void fieldInvalidGet() {
        Field f = new Field();
        assertEquals(Symbol.BLANK, f.getSymbol(-1, -1));
        assertEquals(Symbol.BLANK, f.getSymbol(3, 3));
    }

    @Test
    public void fieldInvalidPlace() {
        Field f = new Field();
        assertFalse(f.placeSymbol(-1, 0, Symbol.PLAYER_X));
        assertFalse(f.placeSymbol(0, 3, Symbol.PLAYER_X));
    }

    @Test
    public void fieldClearInvalid() {
        Field f = new Field();
        f.clearSymbol(-1, -1);
        f.clearSymbol(3, 3);
        assertTrue(true);
    }

    @Test
    public void gameInitialPlayer() {
        assertEquals(Symbol.PLAYER_X, game.getActiveSymbol());
    }

    @Test
    public void gameInitialBoardEmpty() {
        assertEquals(Symbol.BLANK, game.getSymbolAt(0, 0));
        assertEquals(Symbol.BLANK, game.getSymbolAt(1, 1));
        assertEquals(Symbol.BLANK, game.getSymbolAt(2, 2));
    }

    @Test
    public void gameExecuteValidMove() {
        assertTrue(game.executeMove(0, 0));
        assertEquals(Symbol.PLAYER_X, game.getSymbolAt(0, 0));
    }

    @Test
    public void gamePlayerToggle() {
        game.executeMove(0, 0);
        assertEquals(Symbol.PLAYER_O, game.getActiveSymbol());
    }

    @Test
    public void gameInvalidMoveOutOfBounds() {
        assertFalse(game.executeMove(-1, 0));
        assertFalse(game.executeMove(3, 0));
    }

    @Test
    public void gameInvalidMoveOccupied() {
        game.executeMove(1, 1);
        assertFalse(game.executeMove(1, 1));
    }

    @Test
    public void gameRestart() {
        game.executeMove(0, 0);
        game.restart();
        assertEquals(Symbol.BLANK, game.getSymbolAt(0, 0));
        assertEquals(Symbol.PLAYER_X, game.getActiveSymbol());
    }

    @Test
    public void gameWinnerRow0() {
        game.executeMove(0, 0); game.executeMove(1, 0);
        game.executeMove(0, 1); game.executeMove(1, 1);
        game.executeMove(0, 2);
        assertEquals(Symbol.PLAYER_X, game.determineWinner());
    }

    @Test
    public void gameWinnerRow1() {
        game.executeMove(1, 0); game.executeMove(0, 0);
        game.executeMove(1, 1); game.executeMove(0, 1);
        game.executeMove(1, 2);
        assertEquals(Symbol.PLAYER_X, game.determineWinner());
    }

    @Test
    public void gameWinnerRow2() {
        game.executeMove(2, 0); game.executeMove(0, 0);
        game.executeMove(2, 1); game.executeMove(0, 1);
        game.executeMove(2, 2);
        assertEquals(Symbol.PLAYER_X, game.determineWinner());
    }

    @Test
    public void gameWinnerCol0() {
        game.executeMove(0, 0); game.executeMove(0, 1);
        game.executeMove(1, 0); game.executeMove(0, 2);
        game.executeMove(2, 0);
        assertEquals(Symbol.PLAYER_X, game.determineWinner());
    }

    @Test
    public void gameWinnerCol1() {
        game.executeMove(0, 1); game.executeMove(0, 0);
        game.executeMove(1, 1); game.executeMove(0, 2);
        game.executeMove(2, 1);
        assertEquals(Symbol.PLAYER_X, game.determineWinner());
    }

    @Test
    public void gameWinnerCol2() {
        game.executeMove(0, 2); game.executeMove(0, 0);
        game.executeMove(1, 2); game.executeMove(0, 1);
        game.executeMove(2, 2);
        assertEquals(Symbol.PLAYER_X, game.determineWinner());
    }

    @Test
    public void gameWinnerMainDiagonal() {
        game.executeMove(0, 0); game.executeMove(0, 1);
        game.executeMove(1, 1); game.executeMove(0, 2);
        game.executeMove(2, 2);
        assertEquals(Symbol.PLAYER_X, game.determineWinner());
    }

    @Test
    public void gameWinnerAntiDiagonal() {
        game.executeMove(0, 2); game.executeMove(0, 0);
        game.executeMove(1, 1); game.executeMove(0, 1);
        game.executeMove(2, 0);
        assertEquals(Symbol.PLAYER_X, game.determineWinner());
    }

    @Test
    public void gamePlayerOWinsRow() {
        game.executeMove(2, 0); game.executeMove(0, 0);
        game.executeMove(2, 1); game.executeMove(0, 1);
        game.executeMove(1, 0); game.executeMove(0, 2);
        assertEquals(Symbol.PLAYER_O, game.determineWinner());
    }

    @Test
    public void gamePlayerOWinsCol() {
        game.executeMove(0, 0); game.executeMove(0, 1);
        game.executeMove(1, 0); game.executeMove(1, 1);
        game.executeMove(2, 2); game.executeMove(2, 1);
        assertEquals(Symbol.PLAYER_O, game.determineWinner());
    }

    @Test
    public void gameNoWinnerInitial() {
        assertEquals(Symbol.BLANK, game.determineWinner());
    }

    @Test
    public void gameFieldNotCompleteInitial() {
        assertFalse(game.isFieldComplete());
    }

    @Test
    public void gameFieldComplete() {
        game.executeMove(0, 0); game.executeMove(0, 1); game.executeMove(0, 2);
        game.executeMove(1, 0); game.executeMove(1, 1); game.executeMove(1, 2);
        game.executeMove(2, 0); game.executeMove(2, 1); game.executeMove(2, 2);
        assertTrue(game.isFieldComplete());
    }

    @Test
    public void gameStatusOngoing() {
        assertEquals(GameStatus.ONGOING, game.getCurrentStatus());
    }

    @Test
    public void gameStatusVictoryX() {
        game.executeMove(0, 0); game.executeMove(1, 0);
        game.executeMove(0, 1); game.executeMove(1, 1);
        game.executeMove(0, 2);
        assertEquals(GameStatus.VICTORY_X, game.getCurrentStatus());
    }

    @Test
    public void gameStatusVictoryO() {
        game.executeMove(2, 0); game.executeMove(0, 0);
        game.executeMove(2, 1); game.executeMove(0, 1);
        game.executeMove(1, 0); game.executeMove(0, 2);
        assertEquals(GameStatus.VICTORY_O, game.getCurrentStatus());
    }

    @Test
    public void gameStatusStalemate() {
        game.executeMove(0, 0); game.executeMove(0, 1); game.executeMove(0, 2);
        game.executeMove(1, 1); game.executeMove(1, 0); game.executeMove(1, 2);
        game.executeMove(2, 1); game.executeMove(2, 0); game.executeMove(2, 2);
        assertEquals(GameStatus.STALEMATE, game.getCurrentStatus());
    }

    @Test
    public void gameEvaluateXWin() {
        game.executeMove(0, 0); game.executeMove(1, 0);
        game.executeMove(0, 1); game.executeMove(1, 1);
        game.executeMove(0, 2);
        assertTrue(game.evaluatePosition(0, true) > 0);
    }

    @Test
    public void gameEvaluateOWin() {
        game.executeMove(2, 0); game.executeMove(0, 0);
        game.executeMove(2, 1); game.executeMove(0, 1);
        game.executeMove(1, 0); game.executeMove(0, 2);
        assertTrue(game.evaluatePosition(0, false) < 0);
    }

    @Test
    public void gameEvaluateDraw() {
        game.executeMove(0, 0); game.executeMove(0, 1); game.executeMove(0, 2);
        game.executeMove(1, 1); game.executeMove(1, 0); game.executeMove(1, 2);
        game.executeMove(2, 1); game.executeMove(2, 0); game.executeMove(2, 2);
        assertEquals(0, game.evaluatePosition(0, true));
    }

    @Test
    public void gameCalculateBestMoveValid() {
        int[] move = game.calculateBestMove();
        assertNotNull(move);
        assertEquals(2, move.length);
        assertTrue(move[0] >= 0 && move[0] <= 2);
        assertTrue(move[1] >= 0 && move[1] <= 2);
    }

    @Test
    public void gameCalculateBestMoveWinning() {
        game.executeMove(0, 0); game.executeMove(1, 0);
        game.executeMove(0, 1); game.executeMove(1, 1);
        int[] move = game.calculateBestMove();
        assertEquals(0, move[0]);
        assertEquals(2, move[1]);
    }

    @Test
    public void gameCalculateBestMoveBlocking() {
        game.executeMove(0, 0); game.executeMove(1, 0);
        game.executeMove(2, 2); game.executeMove(1, 1);
        int[] move = game.calculateBestMove();
        assertTrue(move[0] >= 0 && move[0] <= 2);
        assertEquals(Symbol.BLANK, game.getSymbolAt(move[0], move[1]));
    }

    @Test
    public void programMainExecutes() {
        Program.main(new String[]{});
    }

    @Test
    public void gameMultipleMoves() {
        game.executeMove(0, 0); game.executeMove(0, 1);
        game.executeMove(1, 0); game.executeMove(1, 1);
        assertEquals(Symbol.PLAYER_X, game.getSymbolAt(0, 0));
        assertEquals(Symbol.PLAYER_O, game.getSymbolAt(0, 1));
    }

    @Test
    public void gameNoMoveWhenFull() {
        game.executeMove(0, 0); game.executeMove(0, 1); game.executeMove(0, 2);
        game.executeMove(1, 0); game.executeMove(1, 1); game.executeMove(1, 2);
        game.executeMove(2, 0); game.executeMove(2, 1); game.executeMove(2, 2);
        assertFalse(game.executeMove(0, 0));
    }

    @Test
    public void gameSequentialRestarts() {
        game.executeMove(0, 0);
        game.restart();
        game.executeMove(1, 1);
        assertEquals(Symbol.PLAYER_X, game.getSymbolAt(1, 1));
        assertEquals(Symbol.BLANK, game.getSymbolAt(0, 0));
    }

    @Test
    public void gameEvaluateEmpty() {
        int val = game.evaluatePosition(0, true);
        assertTrue(val >= -10 && val <= 10);
    }

    @Test
    public void fieldMultipleResets() {
        Field f = new Field();
        f.placeSymbol(0, 0, Symbol.PLAYER_X);
        f.reset();
        f.placeSymbol(1, 1, Symbol.PLAYER_O);
        assertEquals(Symbol.PLAYER_O, f.getSymbol(1, 1));
        assertEquals(Symbol.BLANK, f.getSymbol(0, 0));
    }
}