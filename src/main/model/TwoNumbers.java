package model;

public class TwoNumbers {
    double n1;
    double n2;

    // EFFECTS: instantiate with two consumed numbers
    public TwoNumbers(double n1, double n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    // EFFECTS: produce true if it's possible to compute 24 with the two numbers, using only basic arithmetics (+ - / *)
    public Boolean isSolvable() {
        return (n1 + n2 == 24 || n1 - n2 == 24 || n2 - n1 == 24 || n1 * n2 == 24 || n1 / n2 == 24 || n2 / n1 == 24);
    }

    // EFFECTS: set n1
    public void setN1(double n1) {
        this.n1 = n1;
    }

    // EFFECTS: set n2
    public void setN2(double n2) {
        this.n2 = n2;
    }

    // EFFECTS: get n1
    public double getN1() {
        return n1;
    }

    // EFFECTS: get n2
    public double getN2() {
        return n2;
    }
}
