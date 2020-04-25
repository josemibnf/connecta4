import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {

    @Test
    public void test_can_get_row_and_column() {
        Position position = new Position(2, 3);
        assertEquals(2, position.getRow());
        assertEquals(3, position.getColumn());
    }

    @Test
    public void test_equal_positions() {
        Position pos1 = new Position(2, 3);
        Position pos2 = new Position(2, 3);
        assertTrue(pos1.isEqualTo(pos2));
    }

    @Test
    public void test_nonequal_positions() {
        Position pos1 = new Position(2, 3);
        Position pos2 = new Position(3, 2);
        assertFalse(pos1.isEqualTo(pos2));
        assertFalse(pos2.isEqualTo(pos1));
    }

    @Test
    public void test_move() {
        Position position = new Position(2, 3);
        Position moved = position.move(Direction.DOWN);
        assertTrue(moved.isEqualTo(new Position(3, 3)));
    }

    @Test
    public void test_same_pos_path_length() {
        Position pos = new Position(2, 4);
        assertEquals(1, Position.pathLength(pos, pos));
    }

    @Test
    public void test_horizontal_path_length() {
        Position pos1 = new Position(4, 2);
        Position pos2 = new Position(4, 5);
        assertEquals(4, Position.pathLength(pos1, pos2));
        assertEquals(4, Position.pathLength(pos2, pos1));
    }

    @Test
    public void test_vertical_path_length() {
        Position pos1 = new Position(2, 4);
        Position pos2 = new Position(5, 4);
        assertEquals(4, Position.pathLength(pos1, pos2));
        assertEquals(4, Position.pathLength(pos2, pos1));
    }

    @Test
    public void test_main_diagonal_path_length() {
        Position pos1 = new Position(2, 4);
        Position pos2 = new Position(7, 9);
        assertEquals(6, Position.pathLength(pos1, pos2));
        assertEquals(6, Position.pathLength(pos2, pos1));
    }

    @Test
    public void test_contra_diagonal_path_length() {
        Position pos1 = new Position(7, 4);
        Position pos2 = new Position(3, 8);
        assertEquals(5, Position.pathLength(pos1, pos2));
        assertEquals(5, Position.pathLength(pos2, pos1));
    }
}