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
    public void execute(int currentScore, boolean isToSave) {
        final HighScoreInputData highScoreData = new HighScoreInputData(currentScore, isToSave);

        highScoreUseCaseInteractor.execute(highScoreData);
    }

    public void switchToTitleView(){
        highScoreUseCaseInteractor.switchToTitleView();
    }

}
