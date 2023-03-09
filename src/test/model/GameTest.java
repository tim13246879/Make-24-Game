package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Game g;
    Game g1;
    Game g2;
    Game g3;
    Game g4;
    Game g5;
    Game g6;
    Game g7;
    Game g8;
    Game g9;
    Game g10;



    @BeforeEach
    private void setup() {
        g = new Game();
        g1 = new Game(1, 2, 3, 4, 10, "s1", LocalDateTime.now());
        g2 = new Game(1, 2, 3, 4, 10, "s1", LocalDateTime.now());
        g3 = new Game(2, 2, 3, 4, 10, "s1", LocalDateTime.now());
        g4 = new Game(1, 5, 3, 4, 10, "s1", LocalDateTime.now());
        g5 = new Game(1, 2, 9, 4, 10, "s1", LocalDateTime.now());
        g6 = new Game(1, 2, 3, 2, 10, "s1", LocalDateTime.now());
        g7 = new Game(1, 2, 3, 4, 10.1, "s1", LocalDateTime.now());
        g8 = new Game(1, 2, 3, 4, 10, "ss1", LocalDateTime.now());
        g9 = new Game(1, 2, 3, 4, 10, "s1", LocalDateTime.of(1, 1, 1, 1, 1));
        g10 = new Game(10, 4, 1, 9, 18, "s22", LocalDateTime.of(22, 3, 1, 11, 10));
    }

    // EFFECTS: return true if all numbers in consumed list are in range [1, 13]
    public Boolean isWithinRange(List<Integer> loi) {
        for (int i: loi) {
            if (i < 1 || i > 13) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testConstructor() {
        int n1 = g.get(1);
        int n2 = g.get(2);
        int n3 = g.get(3);
        int n4 = g.get(4);
        List<Integer> loi = Arrays.asList(n1, n2, n3, n4);
        assertTrue(isWithinRange(loi));
    }

    @Test
    public void testEquals() {
        assertTrue(g1.equals(g1));
        assertTrue(g1.equals(g2));
        assertFalse(g1.equals(g3));
        assertFalse(g1.equals(g4));
        assertFalse(g1.equals(g5));
        assertFalse(g1.equals(g6));
        assertFalse(g1.equals(g7));
        assertFalse(g1.equals(g8));
        assertFalse(g1.equals(g9));
        assertFalse(g1.equals(g10));
        assertFalse(g10.equals(g3));
        assertFalse(g1.equals(null));
        assertFalse(g1.equals("Obviously Wrong"));
        assertFalse(g1.equals(new Game()));
    }

    @Test
    public void testHashCode() {
        assertEquals(g1.hashCode(), g1.hashCode());
        assertEquals(g1.hashCode(), g2.hashCode());
    }
}