package use_case.Vet;

public interface VetOutputBoundary {

    /**
     * Prepare the success view with the given VetOutputData.
     * @param vetOutputData the output data to be displayed in the success view.
     */
    void prepareSuccessView(VetOutputData vetOutputData);

    /**
     * Switch to the score view with the given VetOutputData.
     * @param vetOutputData the output data to be displayed in the score view.
     */
    void switchToScoreView(VetOutputData vetOutputData);
}
