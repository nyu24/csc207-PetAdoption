package use_case.high_score;

public interface HighScoreOutputBoundary {
    /**
     * Prepares the success view for the Set Param Use case
     * @param outputData the output data
     */
    void prepareSuccessView(HighScoreOutputData outputData);

    /**
     * Prepares the failure view for the Set Param Use case.
     * @param errorMessage the explanation for failure
     */
    void prepareFailView(String errorMessage);
}
