package model;

// Represents the game history with a list of games.

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GameHistory implements Writable {
    ArrayList<Game> games;

    // EFFECTS: creates an empty list of games.
    public GameHistory() {
        games = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Add game to game history
    public void addGame(Game g) {
        games.add(g);
        EventLog.getInstance().logEvent(new Event("Game added to game history"));
    }

    // MODIFIES: this
    // EFFECTS: Arrange all games in the history so that the most recent games are at the front of the list and the
    // oldest games at the end.
    public void sortHistoryByDate() {
        games.sort(Comparator.comparing(Game::getDateTime));
        Collections.reverse(games);
        EventLog.getInstance().logEvent(new Event("Game history sorted by date"));
    }

    // MODIFIES: this
    // EFFECTS: Arrange all games in the history so that games that took longest to solve are at the front of the list
    // and games that was quickest at the end.
    public void sortHistoryByTimeToSolve() {
        games.sort(Comparator.comparing(Game::getTimeToSolve));
        Collections.reverse(games);
        EventLog.getInstance().logEvent(new Event("Game history sorted by time to solve"));
    }

    // EFFECTS: getter for list of games in game history
    public List<Game> getGames() {
        return games;
    }

    // EFFECTS: convert this game history object to a Json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("games", gamesToJson());
        return json;
    }

    // EFFECTS: returns games in this game History as a JSON array
    private JSONArray gamesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Game g : games) {
            jsonArray.put(g.toJson());
        }

        return jsonArray;
    }
}
