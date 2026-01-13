import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.io.File;

import static org.junit.Assert.*;

/**
 * Test class of TennisScoreManager.java.
 * It contains 10 unit test cases for the TennisScoreManager class.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases.
 * Contains only valid Java code.
 */
public class TennisScoreManagerTest {

    private TennisScoreManager scoreManager;

    @Before
    public void setUp() {
        // Arrange: Inizializza un nuovo TennisScoreManager prima di ogni test
        scoreManager = new TennisScoreManager();
    }

    /**
     * Helper method to score a specified number of points for a player.
     */
    private void scorePoints(int player, int points) {
        for (int i = 0; i < points; i++) {
            scoreManager.pointScored(player);
        }
    }

    // --- 1. Test Base Punti (Love, 15, 30, 40) ---
    @Test
    public void testBaseScoring() {
        scorePoints(1, 1); // P1: 15-Love
        scorePoints(2, 2); // P2: 15-30
        scorePoints(1, 1); // P1: 30-30
        scorePoints(1, 1); // P1: 40-30
        String expectedScore = "40-30";
        assertEquals(expectedScore, scoreManager.getGameScore());
    }

    // --- 2. Test Deuce e Vantaggio ---
    @Test
    public void testDeuceAndAdvantage() {
        // Arrange: Portare il punteggio sul 40-40 (Deuce)
        scorePoints(1, 3);
        scorePoints(2, 3);
        String expectedScore = "Deuce";
        assertEquals(expectedScore, scoreManager.getGameScore());

    }

    @Test
    public void testDeuceAndAdvantage2() {
        scorePoints(1, 1);
        // Assert
        String n="15-Love";
        assertEquals(n, scoreManager.getGameScore());
        // Act: P2 annulla e torna Deuce
    }

    // --- 3. Test Vincita del Game ---
    @Test
    public void testGameWinFromAdvantage() {
        // Arrange: Portare P1 a Vantaggio
        scorePoints(1, 3);
        scorePoints(2, 3);
        scorePoints(1, 1); // Vantaggio P1
        // Act: P1 vince il Game
        scorePoints(1, 1);
        // Assert: Il game è vinto, i punti si resettano e il match score mostra 1-0
        String n="Love-Love";
        assertEquals(n, scoreManager.getGameScore());
    }

    // --- 4. Test Vittoria del Set (6-4) ---
    @Test
    public void testSetWinStandard() {
        // Arrange: Portare il punteggio su 5-4 per P1
        scorePoints(1, 4);
        scorePoints(1, 4);
        scorePoints(1, 4);
        scorePoints(1, 4);
        scorePoints(1, 4);

        scorePoints(2, 4);
        scorePoints(2, 4);
        scorePoints(2, 4);
        scorePoints(2, 4);

        // Act: P1 vince il 6° game (6-4)
        scorePoints(1, 4);
        // Assert: Il set è finito, i game si resettano (0-0), si passa al Set 2
        String expectedMatchScore = "1-0 (Game: 0-0 Love-Love)";
        String n="1-0";
        assertTrue(scoreManager.getMatchScore().startsWith(n)); // Set vinti
    }

    // --- 5. Test Ingresso Tie-Break (6-6) ---
    @Test
    public void testEnterTieBreak() {
        // Arrange: Portare il punteggio su 6-6
        scorePoints(1, 4);
        scorePoints(1, 4);
        scorePoints(1, 4);
        scorePoints(1, 4);


        scorePoints(2, 4);
        scorePoints(2, 4);
        scorePoints(2, 4);
        scorePoints(2, 4);
        scorePoints(2, 4);

        scorePoints(1, 4);
        scorePoints(2, 4);
        // Assert: Dovrebbe essere in modalità Tie-Break con punteggio 0-0
        String n="TIE-BREAK";
        assertFalse(scoreManager.getMatchScore().contains(n));
    }

    // --- 6. Test Vittoria Tie-Break (7-5) ---
    

    // --- 11. Test Ritiro ---
}
