package org.example.service;

import org.example.model.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScoreBoardServiceTest {
    private IScoreBoardService scoreBoard;

    @BeforeEach
    void setUp() {
        scoreBoard = new ScoreBoardService();
    }

    @Test
    void startMatch_ShouldCreateNewMatch() {
        scoreBoard.startMatch("Team A", "Team B");
        List<Match> summary = scoreBoard.getSummary();
        assertEquals(1, summary.size());
        assertEquals("Team A", summary.get(0).getHomeTeam());
    }

    @Test
    void updateScore_ShouldChangeMatchScore() {
        scoreBoard.startMatch("Team A", "Team B");
        scoreBoard.updateScore("Team A", "Team B", 2, 1);
        List<Match> summary = scoreBoard.getSummary();
        assertEquals(2, summary.get(0).getHomeScore());
        assertEquals(1, summary.get(0).getAwayScore());
    }

    @Test
    void finishMatch_ShouldRemoveMatch() {
        scoreBoard.startMatch("Team A", "Team B");
        scoreBoard.finishMatch("Team A", "Team B");
        List<Match> summary = scoreBoard.getSummary();
        assertTrue(summary.isEmpty());
    }

    @Test
    void getSummary_ShouldReturnMatchesSorted() {
        scoreBoard.startMatch("Team A", "Team B");
        scoreBoard.startMatch("Team C", "Team D");
        scoreBoard.updateScore("Team A", "Team B", 2, 1);
        scoreBoard.updateScore("Team C", "Team D", 3, 3);
        List<Match> summary = scoreBoard.getSummary();
        assertEquals("Team C", summary.get(0).getHomeTeam()); // 3+3=6
        assertEquals("Team A", summary.get(1).getHomeTeam()); // 2+1=3
    }
}
