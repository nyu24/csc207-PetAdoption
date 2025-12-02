package use_case.Vet;

public interface VetOutputBoundary {

    /**
     * Prepare the success view with the given output data.
     * @param vetOutputData the output data for the success view.
     */
    void prepareSuccessView(VetOutputData vetOutputData);

    /**
     * Switch to the score view with the given output data.
     * @param vetOutputData the output data containing the score to display.
     */
    void switchToScoreView(VetOutputData vetOutputData);
}
