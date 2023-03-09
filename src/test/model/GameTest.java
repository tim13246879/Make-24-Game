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

    @BeforeEach
    private void setup() {
        g = new Game();
        g1 = new Game(1, 2, 3, 4, 10, "s1", LocalDateTime.now());
        g2 = new Game(1, 2, 3, 4, 10, "s1", LocalDateTime.now());
        g3 = new Game(2, 2, 3, 4, 10, "s1", LocalDateTime.now());
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
        assertFalse(g1.equals(null));
        assertFalse(g1.equals("Obviously Wrong"));
    }

    @Test
    public void testHashCode() {
        assertEquals(g1.hashCode(), g1.hashCode());
        assertEquals(g1.hashCode(), g2.hashCode());
    }
}