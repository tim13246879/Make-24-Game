package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TwoNumbersTest {

    @Test
    public void testAddition() {
        TwoNumbers set;
        set = new TwoNumbers(23, 1);
        assertTrue(set.isSolvable());
        set = new TwoNumbers(9.5, 14.5);
        assertTrue(set.isSolvable());
        set = new TwoNumbers(-10, 34);
        assertTrue(set.isSolvable());
        set = new TwoNumbers(2, 1);
        assertFalse(set.isSolvable());
    }

    @Test
    public void testSubtraction() {
        TwoNumbers set;
        set = new TwoNumbers(29, 5);
        assertTrue(set.isSolvable());
        set = new TwoNumbers(5, 29);
        assertTrue(set.isSolvable());
        set = new TwoNumbers(-25, -1);
        assertTrue(set.isSolvable());
        set = new TwoNumbers(29, 4);
        assertFalse(set.isSolvable());
    }

    @Test
    public void testMultiplication() {
        TwoNumbers set;
        set = new TwoNumbers(6, 4);
        assertTrue(set.isSolvable());
        set = new TwoNumbers(25, (double) 24/25);
        assertTrue(set.isSolvable());
        set = new TwoNumbers(4, 6);
        assertTrue(set.isSolvable());
        set = new TwoNumbers(0.5, 48);
        assertTrue(set.isSolvable());
        set = new TwoNumbers(-10, -2.4);
        assertTrue(set.isSolvable());
        set = new TwoNumbers(0.3, 80);
        assertTrue(set.isSolvable());
        set = new TwoNumbers(3, 4);
        assertFalse(set.isSolvable());
    }

    @Test
    public void testDivision() {
        TwoNumbers set;
        set = new TwoNumbers(48, 2);
        assertTrue(set.isSolvable());
        set = new TwoNumbers(2, 48);
        assertTrue(set.isSolvable());
        set = new TwoNumbers(12, 0.5);
        assertTrue(set.isSolvable());
        set = new TwoNumbers((double) 12/13, (double) 1/26);
        assertTrue(set.isSolvable());
        set = new TwoNumbers(15, 0.5);
        assertFalse(set.isSolvable());
    }


}
