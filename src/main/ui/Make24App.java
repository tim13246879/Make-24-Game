package ui;

import model.Game;
import model.GameHistory;

import java.util.Scanner;

public class Make24App {

    private Game game;
    private GameHistory gameHistory;
    private Scanner input;


    public Make24App() {
        runApp();
    }

    private void runApp() {
    }




    // EFFECTS: For each game in game history, print out: (a) the 4 numbers, (b) the solution used by user to arrive at
    // 24 (for example, if the 4 numbers are 3, 6, 7, 10, one solution could be "6 / 3 * 7 + 10"), (c) amount of time it
    // took the user to arrive at 24, (d) date of game, (e) time of game.

    // EFFECTS: display exact same information as displayHistoryByDate(), but instead of sorting by date, sort by amount
    // of time required to solve problem.
}
