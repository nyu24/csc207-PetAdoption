package use_case.high_score;

import entities.HighScoreList;

/**
 * Output data for the high score use case.
 */
public class HighScoreOutputData {
    private final HighScoreList highScoreList;

    public HighScoreOutputData(HighScoreList highScoreList) {
        this.highScoreList = highScoreList;
    }

    public HighScoreList getHighScoreList() {
        return highScoreList;
    }
}
