import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void test_initial_player_is_player1() {
        Game game = new Game(3, 4, 3);

        String expected = "" +
                "····\n" +
                "····\n" +
                "····\n";

        assertEquals(Status.PLAYER1_PLAYS, game.getStatus());
        assertEquals(expected, game.toString());
        assertFalse(game.isFinished());
    }

    @Test
    public void test_second_player_is_player2() {
        Game game = new Game(3, 4, 3);

        String expected = "" +
                "····\n" +
                "····\n" +
                "·1··\n";

        game.play(1);

        assertEquals(Status.PLAYER2_PLAYS, game.getStatus());
        assertEquals(expected, game.toString());
        assertFalse(game.isFinished());
    }

    @Test
    public void test_third_player_is_player1() {
        Game game = new Game(3, 4, 3);

        String expected = "" +
                "····\n" +
                "····\n" +
                "·1·2\n";

        game.play(1);
        game.play(3);

        assertEquals(Status.PLAYER1_PLAYS, game.getStatus());
        assertEquals(expected, game.toString());
        assertFalse(game.isFinished());
    }

    @Test
    public void test_canPlay() {
        Game game = new Game(3, 4, 3);

        String initial = "" +
                "212·\n" +
                "1211\n" +
                "1212\n";

        game.loadGame(initial);

        assertFalse(game.canPlayColumn(0));
        assertTrue(game.canPlayColumn(3));
    }

    @Test
    public void test_player1_moves_and_wins() {
        Game game = new Game(3, 4, 3);

        String initial = "" +
                "····\n" +
                "·12·\n" +
                "1212\n";

        String expected = "" +
                "··1·\n" +
                "·12·\n" +
                "1212\n";

        game.loadGame(initial);
        game.play(2);

        assertEquals(Status.PLAYER1_WINS, game.getStatus());
        assertEquals(expected, game.toString());
        assertTrue(game.isFinished());
    }


    @Test
    public void test_player2_moves_and_wins() {
        Game game = new Game(3, 4, 3);

        String initial = "" +
                "····\n" +
                "·211\n" +
                "1212\n";

        String expected = "" +
                "·2··\n" +
                "·211\n" +
                "1212\n";

        game.loadGame(initial);
        game.play(1);

        assertEquals(Status.PLAYER2_WINS, game.getStatus());
        assertEquals(expected, game.toString());
        assertTrue(game.isFinished());
    }

    @Test
    public void test_draw() {
        Game game = new Game(3, 4, 3);

        String initial = "" +
                "212·\n" +
                "1211\n" +
                "1212\n";

        String expected = "" +
                "2122\n" +
                "1211\n" +
                "1212\n";

        game.loadGame(initial);
        game.play(3);

        assertEquals(Status.DRAW, game.getStatus());
        assertEquals(expected, game.toString());
        assertTrue(game.isFinished());
    }
}