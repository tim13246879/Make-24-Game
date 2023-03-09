package persistence;

import model.GameHistory;
import model.Game;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            GameHistory gh = new GameHistory();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            GameHistory gh = new GameHistory();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGameHistory.json");
            writer.open();
            writer.write(gh);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGameHistory.json");
            gh = reader.read();
            assertEquals(0, gh.getGames().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            GameHistory gh = new GameHistory();
            Game g1 = new Game(1, 2, 3, 4, 10.0, "s1", LocalDateTime.of(3333, 3, 3, 3, 3, 3));
            Game g2 = new Game(5, 6, 7, 8, 20.0, "s2", LocalDateTime.of(4444, 4, 4, 4, 4, 4));
            gh.addGame(g1);
            gh.addGame(g2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGameHistory.json");
            writer.open();
            writer.write(gh);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGameHistory.json");
            gh = reader.read();
            List<Game> games = gh.getGames();
            assertEquals(2, games.size());
            assertTrue(games.get(0).equals(g1));
            assertTrue(games.get(1).equals(g2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}