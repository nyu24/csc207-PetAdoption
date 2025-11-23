package interface_adapter.high_score;

import use_case.high_score.HighScoreInputBoundary;
import use_case.high_score.HighScoreInputData;

public class HighScoreController {
    private final HighScoreInputBoundary highScoreUseCaseInteractor;

    public HighScoreController(HighScoreInputBoundary loginUseCaseInteractor) {
        this.highScoreUseCaseInteractor = loginUseCaseInteractor;
    }

    /**
     * Executes the High Score Case.
     */
    public void execute() {
        final HighScoreInputData highScoreData = new HighScoreInputData();

        highScoreUseCaseInteractor.execute(highScoreData);
    }
}
