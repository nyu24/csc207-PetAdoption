package interface_adapter.high_score;

import entities.HighScoreList;

/**
 * State for HighScoreViewModel
 */


public class HighScoreState {

    private HighScoreList highScoreList;
    private int currentScore;

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public HighScoreList getHighScoreList() {
        return highScoreList;
    }

    public void setHighScoreList(HighScoreList highScoreList) {
        this.highScoreList = highScoreList;
    }

    @Override
    public String toString() {
        return "HighScoreState{" +
                "highScoreList=" + highScoreList +
                ", currentScore=" + currentScore +
                '}';
    }
}
