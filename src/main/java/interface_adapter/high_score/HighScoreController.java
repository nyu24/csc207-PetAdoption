package interface_adapter.high_score;

import use_case.high_score.HighScoreInputBoundary;
import use_case.high_score.HighScoreInputData;

public class HighScoreController {
    private final HighScoreInputBoundary highScoreUseCaseInteractor;

    public HighScoreController(HighScoreInputBoundary highScoreUseCaseInteractor) {
        this.highScoreUseCaseInteractor = highScoreUseCaseInteractor;
    }

    /**
     * Executes the High Score Case.
     */
    public void execute() {
        final HighScoreInputData highScoreData = new HighScoreInputData();

        highScoreUseCaseInteractor.execute(highScoreData);
    }

    /**
     * Executes the "switch to SetParamView" Use Case.
     */
    public void switchToSetParamView() {
        highScoreUseCaseInteractor.switchToSetParamView();
    }
}
