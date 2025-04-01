package org.example;

import org.example.model.Match;
import org.example.service.IScoreBoardService;
import org.example.service.ScoreBoardService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        IScoreBoardService scoreBoard = new ScoreBoardService();

        // Dodajemo utakmice i rezultate
        scoreBoard.startMatch("Mexico", "Canada");
        scoreBoard.startMatch("Spain", "Brazil");
        scoreBoard.startMatch("Germany", "France");
        scoreBoard.startMatch("Uruguay", "Italy");
        scoreBoard.startMatch("Argentina", "Australia");


        scoreBoard.updateScore("Mexico", "Canada", 0, 5);
        scoreBoard.updateScore("Spain", "Brazil", 10, 2);
        scoreBoard.updateScore("Germany", "France", 2, 2);
        scoreBoard.updateScore("Uruguay", "Italy", 6, 6);
        scoreBoard.updateScore("Argentina", "Australia", 3, 1);
        System.out.println("Hello world!");

        // Dohvatamo sortiranu listu
        List<Match> summary = scoreBoard.getSummary();

        System.out.println("Match Summary:");
        for (Match match : summary) {
            System.out.println(match.getHomeTeam() + " " + match.getHomeScore() + " - "
                    + match.getAwayScore() + " " + match.getAwayTeam());
        }
    }
}