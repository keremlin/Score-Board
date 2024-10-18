package com.sport.matches;

import java.time.LocalDateTime;

public abstract class Match implements Comparable<Match> {

    private final LocalDateTime startTime;

    public Match(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public final LocalDateTime getStartTime() {
        return startTime;
    }

    public abstract void setScore(Score score);

    public abstract Score getScore();

    public abstract int getTotalScore();

    @Override
    public String toString() {
        return getScore().toString();
    }

    @Override
    public int compareTo(Match other) {
        int scoreDifference = other.getTotalScore() - this.getTotalScore();
        if (scoreDifference == 0) {
            return other.getStartTime().compareTo(this.startTime);
        }
        return scoreDifference;
    }

}
