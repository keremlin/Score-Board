package com.sport.matches;

import com.sport.exceptions.InvalidInputException;

import java.time.LocalDateTime;

public class SoccerMatch extends Match {

    private final String homeTeam;
    private final String awayTeam;
    private SoccerScore score;

    public SoccerMatch(LocalDateTime startTime, String homeTeam, String awayTeam) throws InvalidInputException {
        super(startTime);
        if (startTime==null || homeTeam == null || awayTeam == null || homeTeam.isEmpty() || awayTeam.isEmpty()) {
            throw new InvalidInputException();
        }
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        score = new SoccerScore();
    }

    public String getHomeTeam() { return homeTeam; }
    public String getAwayTeam() { return awayTeam; }
    public int getHomeScore() { return score.getHomeScore(); }
    public int getAwayScore() { return score.getAwayScore(); }

    @Override
    public void setScore(Score score) {
        this.score= (SoccerScore) score;
    }

    @Override
    public Score getScore() {
        return this.score;
    }

    @Override
    public int getTotalScore() {
        return score.getHomeScore() + score.getAwayScore();
    }

    @Override
    public String toString() {
        return homeTeam + " " + score.getHomeScore() + " - " + awayTeam + " " + score.getAwayScore();
    }
}
