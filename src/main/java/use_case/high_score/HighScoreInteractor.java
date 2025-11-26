package use_case.high_score;

public class HighScoreInteractor implements HighScoreInputBoundary{

    private final HighScoreOutputBoundary highScorePresenter;

    public HighScoreInteractor(HighScoreOutputBoundary outputBoundary) {
        this.highScorePresenter = outputBoundary;
    }
    public void execute(HighScoreInputData highScoreInputData) {

    }

    @Override
    public void switchToSetParamView() {
        highScorePresenter.switchToSetParamView();
    }
}
