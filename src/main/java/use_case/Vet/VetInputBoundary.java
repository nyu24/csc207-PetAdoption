package use_case.Vet;

public interface VetInputBoundary {
    /**
     * Executes the Vet Use Case.
     * @param vetInputData the input data containing the pet's stats and score.
     */
    void execute(VetInputData vetInputData);

    /**
     * Switches to the Score View.
     * @param vetOutputData the output data containing the result and score information.
     */
    void switchToScoreView(VetOutputData vetOutputData);
}
