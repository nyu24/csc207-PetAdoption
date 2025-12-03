package use_case.high_score;

public interface HighScoreOutputBoundary {

    /**
     * Prepares the success view for the high score use case.
     * @param outputData the output data.
     */
    void prepareSuccessView(HighScoreOutputData outputData);

    /**
     * Prepares the failure view for the high score use case.
     * @param errorMessage the explanation for failure.
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches from high score view to title view.
     */
    void switchToTitleView();
}
