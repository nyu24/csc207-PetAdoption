package use_case.high_score;

/**
 * Input data for the high score use case.
 */
public class HighScoreInputData {
    private final int currentScore;
    private final boolean isToSave;

    public HighScoreInputData(int currentScore, boolean isToSave) {
        this.currentScore = currentScore;
        this.isToSave = isToSave;
    }

    /**
     * Gets the current score from the input data.
     * @return current score the user achieved at the end of the game
     */
    public int getCurrentScore() {
        return currentScore;
    }

    /**
     * Gets whether the score should be saved.
     * @return whether the score should be saved.
     */
    public boolean isToSave() {
        return isToSave;
    }
}
