package model;

// Represents a game of make 24 with the 4 numbers, amount of time taken by user to compute 24, the solution
// that the user uses to solve the problem, and the date and time of completion.

import java.time.LocalDateTime;
import java.util.Random;

public class Game {
    private int n1;
    private int n2;
    private int n3;
    private int n4;
    private float timeToSolve;
    private String solution;
    private LocalDateTime dateTime;


    // EFFECTS: generate the 4 random numbers. Numbers should be between 1 and 13, inclusive, and needs to be solvable.
    public Game() {
        Random random = new Random();
        n1 = random.nextInt(12) + 1;
        n2 = random.nextInt(12) + 1;
        n3 = random.nextInt(12) + 1;
        n4 = random.nextInt(12) + 1;
        if (!isSolvable()) {
            new Game();
        }
    }

    // REQUIRES:
    // EFFECTS: Produce true if the 4 numbers can be computed to 24 using the 4 basic arithmetics (+ - / *). Each number
    // must be used exactly once.
    private boolean isSolvable() {
        return true; //STUB
    }

    // REQUIRES: n must be between 1 and 4, inclusive
    // EFFECTS: get nth number. For example, if 1 is consumed, return n1, if 2 is consumed, return n2, and so on
    public int get(int n) {
        if (n == 1) {
            return n1;
        } else if (n == 2) {
            return n2;
        } else if (n == 3) {
            return n3;
        } else {
            return n4;
        }

    }

    // REQUIRES: time must be positive
    // MODIFIES: this
    // EFFECTS: set amount of time required by user to solve problem
    public void setTimeToSolve(float timeToSolve) {
        this.timeToSolve = timeToSolve;
    }

    // EFFECTS: return amount of time required by user to solve problem
    public float getTimeToSolve() {
        return timeToSolve;
    }

    // MODIFIES: this
    // EFFECTS: set solution inputted by user
    public void setSolution(String solution) {
        this.solution = solution;
    }

    // EFFECTS: get solution
    public String getSolution() {
        return solution;
    }

    // MODIFIES: this
    // EFFECTS: set the date the game was played
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    // EFFECTS: get date the game was played
    public LocalDateTime getDateTime() {
        return dateTime;
    }


}
