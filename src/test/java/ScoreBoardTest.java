import static org.junit.Assert.*;

import com.sport.matches.Match;
import org.junit.Before;
import org.junit.Test;


public class ScoreBoardTest {

    private  Match match;
    @Before
    public void setUp() {
        match=null;
    }
    // Main business unit tests
    @Test
    public void testStartMatch() {
        assertNotNull(match);
    }
    @Test
    public void testUpdateScore() {
        assertNotNull(match);
    }
    @Test
    public void testFinishMatch() {
        assertNotNull(match);
    }
    @Test
    public void testGetSummaryOrderedByScore(){
        assertNotNull(match);
    }
    // edge test cases
    @Test
    public void testCreateNewMatch(){
        assertNotNull(match);
    }

    @Test
    public void testGetTotalScore(){
        assertNotNull(match);
    }
    @Test
    public void testCompareToOfMatch(){
        assertNotNull(match);
    }

}
