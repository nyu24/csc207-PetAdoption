package use_case.high_score;

public class HighScoreInteractor implements HighScoreInputBoundary{

    private final HighScoreOutputBoundary highScorePresenter;
    private final HighScoreDataAccessInterface highScoreDataAccessObject;

    public HighScoreInteractor(HighScoreDataAccessInterface highScoreDataAccessInterface,HighScoreOutputBoundary outputBoundary) {
        this.highScoreDataAccessObject = highScoreDataAccessInterface;
        this.highScorePresenter = outputBoundary;
    }
    public void execute(HighScoreInputData highScoreInputData) {
        final HighScoreOutputData highScoreOutputData = new HighScoreOutputData();
        highScorePresenter.prepareSuccessView(highScoreOutputData);

    }
}
