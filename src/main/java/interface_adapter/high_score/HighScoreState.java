package interface_adapter.high_score;

import entities.HighScoreList;

/**
 * State for HighScoreViewModel
 */


public class HighScoreState {

    private HighScoreList highScoreList;
    private int currentScore;
    private String highScoreError;

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

    public String getHighScoreError() {
        return highScoreError;
    }

    public void setHighScoreError(String highScoreError) {
        this.highScoreError = highScoreError;
    }

    @Override
    public String toString() {
        return "HighScoreState{" +
                "highScoreList=" + highScoreList +
                ", currentScore=" + currentScore +
                '}';
    }
}
