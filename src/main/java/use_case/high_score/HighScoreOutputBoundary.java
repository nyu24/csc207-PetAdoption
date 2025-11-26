package use_case.high_score;

import use_case.set_parameters.SetParamOutputData;

public interface HighScoreOutputBoundary {
    /**
     * Prepares the success view for the Set Param Use case
     * @param outputData the output data
     */
    void prepareSuccessView(SetParamOutputData outputData);

    /**
     * Prepares the failure view for the Set Param Use case.
     * @param errorMessage the explanation for failure
     */
    void prepareFailView(String errorMessage);

    void switchToSetParamView();
}
