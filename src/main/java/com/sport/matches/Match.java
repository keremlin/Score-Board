package com.sport.matches;

import java.time.LocalDateTime;

public abstract class Match implements Comparable<Match> {

    private LocalDateTime startTime;
    private Score score;

    public Match(Score score, LocalDateTime startTime) {
        this.startTime = startTime;
        this.score = score;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void updateScore(Score score) {
        this.score = score;
    }

    public abstract int getTotalScore();

    @Override
    public String toString() {
        return score.toString();
    }

    @Override
    public int compareTo(Match other) {
        int scoreDifference = other.getTotalScore() - this.getTotalScore();
        if (scoreDifference == 0) {
            return other.getStartTime().compareTo(this.startTime);  // Most recently started match first
        }
        return scoreDifference;
    }

}
