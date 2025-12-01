package interface_adapter.high_score;

import use_case.high_score.HighScoreInputBoundary;
import use_case.high_score.HighScoreInputData;

/**
 * Controller for the High Score use case.
 */
public class HighScoreController {
    /**
     * Interactor for High Score needed for controller to work.
     */
    private final HighScoreInputBoundary highScoreUseCaseInteractor;

    public HighScoreController(HighScoreInputBoundary highScoreUseCaseInteractor) {
        this.highScoreUseCaseInteractor = highScoreUseCaseInteractor;
    }

    /**
     * Executes the High Score Case.
     * @param currentScore current final score of the user when they finish the game. -1 if invalid.
     * @param isToSave dictates whether the current score should be saved.
     */

    public void execute(int currentScore, boolean isToSave) {
        final HighScoreInputData highScoreData = new HighScoreInputData(currentScore, isToSave);

        highScoreUseCaseInteractor.execute(highScoreData);
    }

    /**
     * Switches to the title screen.
     */

    public void switchToTitleView() {
        highScoreUseCaseInteractor.switchToTitleView();
    }

}
