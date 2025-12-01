package use_case.high_score;

public interface HighScoreInputBoundary {
    /**
     * Input data for the high score use case.
     * @param highScoreInputData data for the high score use case.
     */
    void execute(HighScoreInputData highScoreInputData);

    /**
     * Switches the high score view to title view.
     */
    void switchToTitleView();
}
