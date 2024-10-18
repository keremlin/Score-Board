package com.sport.scoreboard;

import com.sport.matches.Match;
import com.sport.matches.Score;

import java.util.SortedSet;

public interface ScoreBoard {

    public void startMatch(Match match);

    public void updateScore(Match match, Score score);

    public void finishMatch(Match match);

    public SortedSet<Match> getSummary();

    String getStringSummary();
}
