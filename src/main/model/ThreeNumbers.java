package model;

import java.util.Arrays;
import java.util.List;

public class ThreeNumbers {
    int n1;
    int n2;
    int n3;

    // EFFECTS: Instantiate field with consumed numbers.
    public ThreeNumbers() {

    }

    // EFFECTS: Return all possible numbers that can be computed from two consumed numbers using only basic arithmetics.
    // For example, if the two consumed numbers are  3 and 6, the method should return the list: <9, 18, -3, 3, 2, 0.5>
    public List<Float> allPossible(float n1, float n2) {
        List<Float> lof = Arrays.asList(n1 + n2, n1 * n2, n1 - n2, n2 - n1, n1 / n2, n2 / n1);
        return lof;
    }
}
