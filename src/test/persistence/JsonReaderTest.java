package persistence;

import model.Game;
import model.GameHistory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            GameHistory gh = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGameHistory.json");
        try {
            GameHistory gh = reader.read();
            assertEquals(0, gh.getGames().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGameHistory.json");
        try {
            GameHistory gh = reader.read();
            List<Game> games = gh.getGames();
            assertEquals(2, games.size());
            assertTrue(games.get(0).equals(new Game(4, 4, 3, 5, 11, "ss1", LocalDateTime.of(1111, 1, 1, 1, 1, 1))));
            assertTrue(games.get(1).equals(new Game(2, 5, 1, 9, 15, "ss2", LocalDateTime.of(2222, 2, 2, 2, 2, 2))));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}