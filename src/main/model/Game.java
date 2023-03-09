package model;

// Represents a game of make 24 with the 4 numbers, amount of time taken by user to compute 24, the solution
// that the user uses to solve the problem, and the date and time of completion.

import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

public class Game implements Writable {
    private int n1;
    private int n2;
    private int n3;
    private int n4;
    private double timeToSolve;
    private String solution;
    private LocalDateTime dateTime;


    // MODIFIES: this
    // EFFECTS: generate the 4 random numbers. Numbers should be between [1, 13]. The four numbers together should also
    // be solvable. This means there needs to exist a way to compute 24 using the 4 numbers and basic arithmetics. If
    // there is no way to do so, a new set of 4 numbers should be generated.
    // !!! Note that since the isSolvable() method is incomplete, it is possible for the constructor to instantiate
    // an unsolvable game. (An example would be if it generates 1, 1, 1, 1).
    public Game() {
        Random random = new Random();
        while (!isSolvable()) {
            n1 = random.nextInt(12) + 1;
            n2 = random.nextInt(12) + 1;
            n3 = random.nextInt(12) + 1;
            n4 = random.nextInt(12) + 1;
        }
    }

    // EFFECTS: instantiates game with given fields
    public Game(int n1, int n2, int n3, int n4, double timeToSolve, String solution, LocalDateTime dateTime) {
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        this.n4 = n4;
        this.timeToSolve = timeToSolve;
        this.solution = solution;
        this.dateTime = dateTime;

    }

    // EFFECTS: Produce true if the 4 numbers can be computed to 24 using the 4 basic arithmetics (+ - / *). Each number
    // must be used exactly once.
    // !!! Note that this method is incomplete. A stub is in place to ensure the program runs.
    private boolean isSolvable() {
        if (n1 == 0) {
            return false; // STUB
        }
        return true; //STUB
    }

    // REQUIRES: n must be one of 1, 2, 3 or 4
    // EFFECTS: get nth number. For example, if 1 is consumed, return n1, if 2 is consumed, return n2, and so on.
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
    public void setTimeToSolve(double timeToSolve) {
        this.timeToSolve = timeToSolve;
    }

    // EFFECTS: return amount of time required by user to solve problem
    public double getTimeToSolve() {
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

    // EFFECTS: Compare two Game objects. Produce true if all their fields are the same.
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Game game = (Game) o;
        return n1 == game.n1 && n2 == game.n2 && n3 == game.n3 && n4 == game.n4
                && Double.compare(game.timeToSolve, timeToSolve) == 0 && Objects.equals(solution, game.solution)
                && Objects.equals(dateTime, game.dateTime);
    }

    // EFFECTS: Override hashcode to ensure program runs as expected.
    @Override
    public int hashCode() {
        return Objects.hash(n1, n2, n3, n4, timeToSolve, solution, dateTime);
    }

    // EFFECTS: Convert this game object to a Json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("n1", n1);
        json.put("n2", n2);
        json.put("n3", n3);
        json.put("n4", n4);
        json.put("timeToSolve", timeToSolve);
        json.put("solution", solution);
        json.put("dateTime", dateTime);
        return json;
    }


}
