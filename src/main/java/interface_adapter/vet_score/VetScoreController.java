package interface_adapter.vet_score;

import use_case.Vet.VetInputBoundary;
import use_case.Vet.VetOutputBoundary;
import use_case.Vet.VetOutputData;

public class VetScoreController {
    private final VetInputBoundary vetUsecaseInteractor;

    public VetScoreController(VetInputBoundary VetUsecaseInteractor) {
        this.vetUsecaseInteractor = VetUsecaseInteractor;
    }

    public void switchToScoreView(int score){

        VetOutputData vetOutputData = new VetOutputData(null, score);
        vetUsecaseInteractor.switchToScoreView(vetOutputData);
    }
}
