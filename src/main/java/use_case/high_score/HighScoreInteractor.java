package use_case.high_score;

/**
 * Interactor for the high score use case.
 */

public class HighScoreInteractor implements HighScoreInputBoundary {

    private final HighScoreOutputBoundary highScorePresenter;
    private final HighScoreDataAccessInterface highScoreDataAccessObject;

    public HighScoreInteractor(HighScoreDataAccessInterface highScoreDataAccessInterface,
                               HighScoreOutputBoundary outputBoundary) {
        this.highScoreDataAccessObject = highScoreDataAccessInterface;
        this.highScorePresenter = outputBoundary;
    }

    /**
     * Takes in input data and hands it to presenter to be displayed by high score view.
     * @param highScoreInputData data for the high score use case.
     */
    public void execute(HighScoreInputData highScoreInputData) {
        if (highScoreInputData.isToSave()) {
            highScoreDataAccessObject.save(highScoreInputData.getCurrentScore());
        }
        final HighScoreOutputData highScoreOutputData = new HighScoreOutputData(highScoreDataAccessObject.get());
        highScorePresenter.prepareSuccessView(highScoreOutputData);

    }

    @Override
    public void switchToTitleView() {
        highScorePresenter.switchToTitleView();
    }
}
