package com.sport.scoreboard;

import com.sport.matches.Match;
import com.sport.matches.Score;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class ScoreBoardImpl implements ScoreBoard {

    private final SortedSet<Match> matches;


    public ScoreBoardImpl() {
        matches = Collections.synchronizedSortedSet(new TreeSet<Match>());
    }

    @Override
    public void startMatch(Match match) {
        matches.add(match);
    }

    @Override
    public void updateScore(Match match, Score score) {

    }

    @Override
    public void finishMatch(Match match) {

    }

    @Override
    public SortedSet<Match> getSummary() {
        return matches;
    }
}
