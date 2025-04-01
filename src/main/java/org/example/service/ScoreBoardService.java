package org.example.service;

import org.example.model.Match;

import java.util.*;

public class ScoreBoardService implements IScoreBoardService {
    //private final Set<Match> activeMatches;
    private final Map<String, Match> matches = new HashMap<>();

    private String generateKey(String homeTeam, String awayTeam) {
        return homeTeam + "-" + awayTeam;
    }

    @Override
    public void startMatch(String homeTeam, String awayTeam) {
        String key = generateKey(homeTeam, awayTeam);
        if(!matches.containsKey(key)){
            matches.put(key, new Match(homeTeam, awayTeam));
        }else {
            throw new IllegalArgumentException("Match between this teams already exists.");
        }
    }

    @Override
    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        String key = generateKey(homeTeam, awayTeam);
        Match match = matches.get(key);
        if(match != null){
            match.updateScore(homeScore, awayScore);
        }else{
            throw new IllegalArgumentException("Match not found.");
        }

    }

    @Override
    public void finishMatch(String homeTeam, String awayTeam) {
        String key = generateKey(homeTeam, awayTeam);
        matches.remove(key);

    }

    @Override
    public List<Match> getSummary() {
        return matches.values().stream()
                .sorted(Comparator.comparingInt(Match::getTotalScore).reversed()
                        .thenComparing(Comparator.comparing(Match::getStartTime).reversed()))
                .toList();
    }




//    public ScoreBoardService() {
//        this.activeMatches = new HashSet<>();
//    }

}
