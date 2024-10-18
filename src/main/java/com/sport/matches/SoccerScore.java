package com.sport.matches;

import com.sport.exceptions.InvalidInputException;

public class SoccerScore implements Score {
    private int homeScore;
    private int awayScore;

    public SoccerScore(int homeScore, int awayScore) throws InvalidInputException {
        setHomeScore(homeScore);
        setAwayScore(awayScore);
    }

    public SoccerScore() {
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) throws InvalidInputException {
        if (homeScore < 0) throw new InvalidInputException();
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) throws InvalidInputException {
        if (awayScore < 0) throw new InvalidInputException();
        this.awayScore = awayScore;
    }
}
