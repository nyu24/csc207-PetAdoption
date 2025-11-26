package use_case.Vet;

import entities.Pet;
import entities.Vet;

import java.util.List;
import java.util.Map;

public class VetUseCaseInteractor implements VetInputBoundary{

    private final Vet vet;
    private final VetOutputBoundary vetPresenter;
    public VetUseCaseInteractor(Vet vet, VetOutputBoundary vetPresenter) {
        this.vet = vet;
        this.vetPresenter = vetPresenter;
    }

    public void execute(VetInputData vetInputData) {
        Map<String, Integer> stats = vetInputData.getStats();
        List<List<String>> requirements = this.vet.checkRequirements(stats);
        final VetOutputData vetOutputData = new VetOutputData(requirements);

        this.vetPresenter.prepareSuccessView(vetOutputData);
    }

    public void switchToScoreView(){
        this.vetPresenter.switchToScoreView();
    }
}
