package interface_adapter.vet_score;

import use_case.Vet.VetInputBoundary;
import use_case.Vet.VetOutputData;

public class VetScoreController {
    private final VetInputBoundary vetUsecaseInteractor;


    public VetScoreController(VetInputBoundary VetUsecaseInteractor) {
        this.vetUsecaseInteractor = VetUsecaseInteractor;
    }

    /**
     * Switch to the score view with the given score.
     * @param score the score to display.
     */
    public void switchToScoreView(int score) {
        VetOutputData vetOutputData = new VetOutputData(null, score);
        vetUsecaseInteractor.switchToScoreView(vetOutputData);
    }
}
