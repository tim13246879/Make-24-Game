package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GameHistory {
    ArrayList<Game> games;

    public GameHistory() {
        games = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Add game to game history
    public void addGame(Game g) {
        games.add(g);
    }

    // EFFECTS: Arrange all games in the history so that the most recent games are at the front of the list and the
    // oldest games at the end.
    public void sortHistoryByDate() {
        games.sort(Comparator.comparing(Game::getDateTime));
        Collections.reverse(games);
    }

    // EFFECTS: Arrange all games in the history so that games that took longest to solve are at the front of the list
    // and games that was quickest at the end.
    public void sortHistoryByTimeToSolve() {
        games.sort(Comparator.comparing(Game::getTimeToSolve));
        Collections.reverse(games);
    }

    // EFFECTS: getter for list of games in game history
    public List<Game> getGames() {
        return games;
    }
}
