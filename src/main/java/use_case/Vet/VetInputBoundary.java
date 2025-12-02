package use_case.Vet;

public interface VetInputBoundary {
    /**
     * Execute the Vet use case with the given input data.
     * @param vetInputData the input data for the Vet use case.
     */
    void execute(VetInputData vetInputData);

    /**
     * Switch to the score view with the given output data.
     * @param vetOutputData the output data containing the score to display.
     */
    void switchToScoreView(VetOutputData vetOutputData);
}
