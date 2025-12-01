package use_case.Vet;

import java.util.List;
import java.util.Map;

import entities.Vet;

public class VetUseCaseInteractor implements VetInputBoundary {

    private final Vet vet;
    private final VetOutputBoundary vetPresenter;

    public VetUseCaseInteractor(Vet vet, VetOutputBoundary vetPresenter) {
        this.vet = vet;
        this.vetPresenter = vetPresenter;
    }

    /**
     * Execute the vet use case.
     * @param vetInputData the input data containing the pet's stats and score.
     */
    public void execute(VetInputData vetInputData) {
        final Map<String, Integer> stats = vetInputData.getStats();
        final List<List<String>> requirements = this.vet.checkRequirements(stats);
        final VetOutputData vetOutputData = new VetOutputData(requirements, vetInputData.getScore());
        vetOutputData.setPet(vetInputData.getPet());
        this.vetPresenter.prepareSuccessView(vetOutputData);
    }

    /**
     * Switch to the score view.
     * @param vetOutputData the output data containing the result and score information.
     */
    public void switchToScoreView(VetOutputData vetOutputData) {
        this.vetPresenter.switchToScoreView(vetOutputData);
    }
}
