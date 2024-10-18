package com.sport.scoreboard;

import com.sport.matches.Match;
import com.sport.matches.Score;
import com.sport.matches.SoccerMatch;

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
        SoccerMatch SoccerMatchItem = (SoccerMatch) match;
        for (Match matchItem : matches) {
            SoccerMatch soccerMatch = (SoccerMatch) matchItem;
            if (soccerMatch.getHomeTeam().equals(SoccerMatchItem.getHomeTeam()) &&
                    soccerMatch.getAwayTeam().equals(SoccerMatchItem.getAwayTeam())) {
                matches.remove(match);  // Remove old version
                match.setScore(score);
                matches.add(match);     // Add updated version
                break;
            }
        }
    }

    @Override
    public void finishMatch(Match baseMatch) {
        SoccerMatch SoccerMatchItem = (SoccerMatch) baseMatch;
        matches.removeIf(match -> ((SoccerMatch) match).getHomeTeam().equals(SoccerMatchItem.getHomeTeam())
                && ((SoccerMatch) match).getAwayTeam().equals(SoccerMatchItem.getAwayTeam()));
    }

    @Override
    public SortedSet<Match> getSummary() {
        return matches;
    }

    @Override
    public String getStringSummary() {
        StringBuilder temp = new StringBuilder();
        synchronized (matches) {
            for (Match match : matches) {
                temp.append(match);
                temp.append(" ");
            }
        }
        return temp.toString();
    }
}
