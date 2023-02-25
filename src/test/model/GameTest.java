package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GameTest {

    Game g;

    @BeforeEach
    private void setup() {
        g = new Game();
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
}