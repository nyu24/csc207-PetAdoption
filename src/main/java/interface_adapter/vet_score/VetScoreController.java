package interface_adapter.vet_score;

import use_case.Vet.VetInputBoundary;
import use_case.Vet.VetOutputData;

public class VetScoreController {
    private final VetInputBoundary vetUsecaseInteractor;

    public VetScoreController(VetInputBoundary VetUsecaseInteractor) {
        this.vetUsecaseInteractor = VetUsecaseInteractor;
    }

    /**
     * Switches to the Score View.
     * @param score the score to be displayed.
     */
    public void switchToScoreView(int score) {
        final VetOutputData vetOutputData = new VetOutputData(null, score);
        vetUsecaseInteractor.switchToScoreView(vetOutputData);
    }
}
