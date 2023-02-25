package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameHistoryTest {
    GameHistory gh1;
    GameHistory gh2;
    GameHistory gh3;
    Game g1;
    Game g2;
    Game g3;

    @BeforeEach
    public void setup() {
        gh1 = new GameHistory();
        gh2 = new GameHistory();
        gh3 = new GameHistory();

        g1 = new Game();
        LocalDateTime d1 = LocalDateTime.of(1111, 1, 1, 1, 1, 1);
        g1.setDateTime(d1);
        g1.setTimeToSolve(1);
        g1.setSolution("s1");

        g2 = new Game();
        LocalDateTime d2 = LocalDateTime.of(2222, 2, 2, 2, 2, 2);
        g2.setDateTime(d2);
        g2.setTimeToSolve(3);
        g2.setSolution("s2");

        g3 = new Game();
        LocalDateTime d3 = LocalDateTime.of(3333, 3, 3, 3, 3, 3);
        g3.setDateTime(d3);
        g3.setTimeToSolve(2);
        g3.setSolution("s3");

        gh2.addGame(g3);
        gh2.addGame(g2);
        gh2.addGame(g1);

        gh3.addGame(g2);
        gh3.addGame(g3);
        gh3.addGame(g1);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, gh1.getGames().size());
    }

    @Test
    public void testAddGame() {
        gh1.addGame(g1);
        assertEquals(1, gh1.getGames().size());
        Game first = gh1.getGames().get(0);
        assertEquals(1, first.getTimeToSolve());

        gh1.addGame(g2);
        assertEquals(2, gh1.getGames().size());
        Game second = gh1.getGames().get(1);
        assertEquals("s2", second.getSolution());

        gh1.addGame(g3);
        assertEquals(3, gh1.getGames().size());
        Game third = gh1.getGames().get(2);
        LocalDateTime d3 = LocalDateTime.of(3333, 3, 3, 3, 3, 3);
        assertEquals(d3, third.getDateTime());

    }

    @Test
    public void testSortHistoryByDateReversed() {
        assertEquals(0, gh1.getGames().size());
        gh1.addGame(g1);
        gh1.addGame(g2);
        gh1.addGame(g3);
        gh1.sortHistoryByDate();
        assertEquals(gh2.getGames(), gh1.getGames());
    }

    @Test
    public void testSortHistoryByDateAlreadySorted() {
        gh1.addGame(g3);
        gh1.addGame(g2);
        gh1.addGame(g1);
        gh1.sortHistoryByDate();
        assertEquals(gh2.getGames(), gh1.getGames());
    }

    @Test
    public void testSortHistoryByDateHalfSorted() {
        gh1.addGame(g3);
        gh1.addGame(g1);
        gh1.addGame(g2);
        gh1.sortHistoryByDate();
        assertEquals(gh2.getGames(), gh1.getGames());
    }

    @Test
    public void testSortHistoryByTimeToSolveReversed() {
        gh1.addGame(g1);
        gh1.addGame(g3);
        gh1.addGame(g2);
        gh1.sortHistoryByTimeToSolve();
        assertEquals(gh3.getGames(), gh1.getGames());
    }

    @Test
    public void testSortHistoryByTimeToSolveAlreadySorted() {
        gh1.addGame(g2);
        gh1.addGame(g3);
        gh1.addGame(g1);
        gh1.sortHistoryByTimeToSolve();
        assertEquals(gh3.getGames(), gh1.getGames());
    }

    @Test
    public void testSortHistoryByTimeToSolveHalfSorted() {
        gh1.addGame(g3);
        gh1.addGame(g2);
        gh1.addGame(g1);
        gh1.sortHistoryByTimeToSolve();
        assertEquals(gh3.getGames(), gh1.getGames());
    }
}
