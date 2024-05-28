package spw4.connectfour;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ConnectFourImplTest {
    private ConnectFourImpl game;

    @BeforeEach
    void setUp() {
        game = new ConnectFourImpl(Player.red);
    }

    @Test
    void testInitialState() {
        assertEquals(Player.red, game.getPlayerOnTurn());
        assertFalse(game.isGameOver());
        assertEquals(Player.none, game.getWinner());
        for (int i = 0; i < ConnectFourImpl.ROWS; i++)
            for (int j = 0; j < ConnectFourImpl.COLUMNS; j++)
                assertEquals(Player.none, game.getPlayerAt(i, j));
    }

    @Test
    void testDrop() {
        game.drop(0);
        assertEquals(Player.red, game.getPlayerAt(5, 0));
        assertEquals(Player.yellow, game.getPlayerOnTurn());
    }

    @Test
    void testColumnFullPlayerGetsToTryAgain() {
        for (int i = 0; i < ConnectFourImpl.ROWS; i++) {
            game.drop(0);
        }
        Player currentPlayer = game.getPlayerOnTurn();
        game.drop(0);
        assertEquals(currentPlayer, game.getPlayerOnTurn());
    }

    @Test
    void testHorizontalWin() {
        for (int i = 0; i < 4; i++) {
            game.drop(i);
            game.drop(i);
        }
        assertTrue(game.isGameOver());
    }

    @Test
    void testVerticalWin() {
        for (int i = 0; i < 4; i++) {
            game.drop(0);
            game.drop(1);
        }
        assertTrue(game.isGameOver());
    }

    @Test
    void testDiagonalDownRightWin() {
        /*
            | .  .  .  .  .  .  .  |
            | .  .  .  .  .  .  .  |
            | .  .  .  r  .  .  .  |
            | .  .  r  r  .  .  .  |
            | .  r  r  y  .  .  .  |
            | r  y  y  y  .  y  .  |
         */
        game.drop(0); // Red
        game.drop(1); // Yellow
        game.drop(1); // Red
        game.drop(2); // Yellow
        game.drop(2); // Red
        game.drop(3); // Yellow
        game.drop(2); // Red
        game.drop(3); // Yellow
        game.drop(3); // Red
        game.drop(5); // Yellow
        game.drop(3); // Red
        assertTrue(game.isGameOver());
    }

    @Test
    void testResetGame() {
        game.drop(0);
        game.reset(Player.yellow);
        assertEquals(Player.yellow, game.getPlayerOnTurn());
        for (int i = 0; i < ConnectFourImpl.ROWS; i++)
            for (int j = 0; j < ConnectFourImpl.COLUMNS; j++)
                assertEquals(Player.none, game.getPlayerAt(i, j));
    }
}
