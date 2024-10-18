import static org.junit.Assert.*;

import com.sport.exceptions.InvalidInputException;
import com.sport.matches.Match;
import com.sport.matches.SoccerMatch;
import com.sport.matches.SoccerScore;
import com.sport.scoreboard.ScoreBoardImpl;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;


public class ScoreBoardTest {

    private Match match;
    private Match matchSecond;
    private Match matchThird;
    private ScoreBoardImpl scoreBoard;

    @Before
    public void setUp() throws InterruptedException, InvalidInputException {
        match = new SoccerMatch(LocalDateTime.now(), "Austria", "Germany");
        Thread.sleep(10);
        matchSecond = new SoccerMatch(LocalDateTime.now(), "Spain", "England");
        Thread.sleep(10);
        matchThird = new SoccerMatch(LocalDateTime.now(), "France", "Italy");
        scoreBoard = new ScoreBoardImpl();
    }

    // Main business unit tests
    @Test
    public void testStartMatch() {
        assertNotNull(match);
        assertEquals(0, match.getTotalScore());

        scoreBoard.startMatch(match);
        assertEquals(1, scoreBoard.getSummary().size());

    }

    @Test
    public void testUpdateScore() throws InvalidInputException {
        assertNotNull(scoreBoard);
        scoreBoard.startMatch(match);
        SoccerMatch soccerMatch = new SoccerMatch(LocalDateTime.now(), "Austria", "Germany");
        SoccerScore soccerScore = new SoccerScore(2, 1);
        scoreBoard.updateScore(soccerMatch, soccerScore);
        SoccerMatch match = (SoccerMatch) scoreBoard.getSummary().first();
        assertEquals(2, match.getHomeScore());
        assertEquals(1, match.getAwayScore());
        assertEquals(3, match.getTotalScore());
    }

    @Test
    public void testFinishMatch() {
        scoreBoard.startMatch(match);
        scoreBoard.startMatch(matchSecond);
        scoreBoard.finishMatch(match);
        assertEquals(1, scoreBoard.getSummary().size());
    }

    @Test
    public void testGetSummaryOrderedByScore() throws InvalidInputException {
        System.out.println(match);
        scoreBoard.startMatch(match);
        scoreBoard.startMatch(matchSecond);
        scoreBoard.startMatch(matchThird);
        scoreBoard.updateScore(match, new SoccerScore(2, 1));
        scoreBoard.updateScore(matchSecond, new SoccerScore(3, 4));

        SoccerMatch firstMatch = (SoccerMatch) scoreBoard.getSummary().first();
        assertEquals("Spain", firstMatch.getHomeTeam());
        assertEquals("England", firstMatch.getAwayTeam());
    }

    // edge test cases
    @Test
    public void testCreateNewMatch() throws InvalidInputException {
        SoccerMatch testMatch =
                new SoccerMatch(LocalDateTime.now(), "Austria", "Germany");
        assertNotNull(testMatch);
        assertEquals(0, testMatch.getTotalScore());
        assertEquals(0, testMatch.getHomeScore());
        assertEquals(0, testMatch.getAwayScore());
        assertEquals("Austria", testMatch.getHomeTeam());
        assertEquals("Germany", testMatch.getAwayTeam());
    }

    @Test
    public void testCreateNewMatchValues() {
        assertThrows(InvalidInputException.class,
                () -> new SoccerMatch(LocalDateTime.now(), "", "Germany"));
        assertThrows(InvalidInputException.class,
                () -> new SoccerMatch(LocalDateTime.now(), "Austria", ""));
        assertThrows(InvalidInputException.class,
                () -> new SoccerMatch(null, "Austria", "Germany"));
        assertThrows(InvalidInputException.class,
                () -> new SoccerMatch(LocalDateTime.now(), null, "Germany"));

    }

    @Test
    public void testGetTotalScore() throws InvalidInputException {
        SoccerMatch testMatch =
                new SoccerMatch(LocalDateTime.now(), "Austria", "Germany");
        assertNotNull(testMatch);
        assertEquals(0, testMatch.getTotalScore());
        SoccerScore soccerScore = new SoccerScore(2, 1);
        testMatch.setScore(soccerScore);
        assertEquals(3, testMatch.getTotalScore());
    }

    @Test
    public void testCompareToOfMatch() throws InvalidInputException {
        SoccerMatch testMatch1 =
                new SoccerMatch(LocalDateTime.now(), "Austria", "Germany");
        SoccerMatch testMatch2 =
                new SoccerMatch(LocalDateTime.now(), "France", "Italy");
        assertEquals(0, testMatch2.compareTo(testMatch1));

        testMatch1.setScore(new SoccerScore(2, 1));
        assertEquals(3, testMatch2.compareTo(testMatch1));

        testMatch2.setScore(new SoccerScore(1, 2));
        assertEquals(0, testMatch2.compareTo(testMatch1));

        testMatch2.setScore(new SoccerScore(1, 3));
        assertEquals(-1, testMatch2.compareTo(testMatch1));

    }

    @Test
    public void testAddRepeatedNewMatch() throws InvalidInputException {
        SoccerMatch testMatch1 =
                new SoccerMatch(LocalDateTime.now(), "Austria", "Germany");
        SoccerMatch testMatch2 =
                new SoccerMatch(LocalDateTime.now(), "Austria", "Germany");
        scoreBoard.startMatch(testMatch1);
        scoreBoard.startMatch(testMatch2);
        assertEquals(1, scoreBoard.getSummary().size());
    }

    @Test
    public void testScoreRangeGreaterThanZero() {
        assertThrows(InvalidInputException.class, () -> new SoccerScore(-1, 2));
        assertThrows(InvalidInputException.class, () -> new SoccerScore(-1, -2));
        assertThrows(InvalidInputException.class, () -> new SoccerScore(1, -2));
    }

    @Test
    public void testGetStringSummary() throws InvalidInputException {
        scoreBoard.startMatch(match);
        scoreBoard.startMatch(matchSecond);
        scoreBoard.updateScore(matchSecond, new SoccerScore(5, 5));
        System.out.println(scoreBoard.getStringSummary());
        assertEquals("Spain 5 - England 5 "+"Austria 0 - Germany 0 ", scoreBoard.getStringSummary());
    }
}
