import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void equals_and_null() {
        Player player = Player.player1();
        assertFalse(player.isEqualTo(null));
    }

    @Test
    public void equals_different_players() {
        Player player1 = Player.player1();
        Player player2 = Player.player2();
        assertFalse(player1.isEqualTo(player2));
        assertFalse(player2.isEqualTo(player1));
    }

    @Test
    public void equals_same_players() {
        Player playerA = Player.player1();
        Player playerB = Player.player1();
        assertTrue(playerA.isEqualTo(playerB));
    }

    @Test
    public void is_player() {
        assertTrue(Player.player1().isPlayer1());
        assertFalse(Player.player2().isPlayer1());

        assertFalse(Player.player1().isPlayer2());
        assertTrue(Player.player2().isPlayer2());
    }

    @Test
    public void to_string() {
        Player player = Player.player1();
        assertEquals("1", player.toString());
    }

    @Test
    public void from_char() {
        Player player1 = Player.fromChar('1');
        Player player2 = Player.fromChar('2');
        Player player3 = Player.fromChar('·');
        assertTrue(player1.isPlayer1());
        assertTrue(player2.isPlayer2());
        assertNull(player3);
    }
}