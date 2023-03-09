package persistence;

import model.Game;
import model.GameHistory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

// Represents a reader that reads game History from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads game history from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GameHistory read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameHistory(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses game history from JSON object and returns it
    private GameHistory parseGameHistory(JSONObject jsonObject) {
        GameHistory gh = new GameHistory();
        addGames(gh, jsonObject);
        return gh;
    }

    // MODIFIES: gh
    // EFFECTS: parses games from JSON object and adds them to game History
    private void addGames(GameHistory gh, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("games");
        for (Object json : jsonArray) {
            JSONObject nextGame = (JSONObject) json;
            addGame(gh, nextGame);
        }
    }

    // MODIFIES: gh
    // EFFECTS: parses game from JSON object and adds it to game history
    private void addGame(GameHistory gh, JSONObject jsonObject) {
        int n1 = jsonObject.getInt("n1");
        int n2 = jsonObject.getInt("n2");
        int n3 = jsonObject.getInt("n3");
        int n4 = jsonObject.getInt("n4");
        double timeToSolve = jsonObject.getDouble("timeToSolve");
        String solution = jsonObject.getString("solution");
        String stringDateTime = jsonObject.getString("dateTime");
        LocalDateTime dateTime = LocalDateTime.parse(stringDateTime, DateTimeFormatter.ISO_DATE_TIME);

        Game game = new Game(n1, n2, n3, n4, timeToSolve, solution, dateTime);
        gh.addGame(game);
    }
}
