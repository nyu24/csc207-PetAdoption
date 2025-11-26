package interface_adapter.vet_score;

import use_case.Vet.VetInputBoundary;

public class VetScoreController {
    private final VetInputBoundary vetUsecaseInteractor;

    public VetScoreController(VetInputBoundary VetUsecaseInteractor) {
        this.vetUsecaseInteractor = VetUsecaseInteractor;
    }

    public void switchToScoreView(){
        vetUsecaseInteractor.switchToScoreView();
    }
}
