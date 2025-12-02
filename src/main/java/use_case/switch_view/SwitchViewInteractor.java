package use_case.switch_view;

public class SwitchViewInteractor implements SwitchViewInputBoundary {
    private SwitchViewOutputBoundary switchViewPresenter;

    public SwitchViewInteractor(SwitchViewOutputBoundary switchViewPresenter) {
        this.switchViewPresenter = switchViewPresenter;
    }

    @Override
    public void switchToSetParamView() {
        switchViewPresenter.switchToSetParamView();
    }

    @Override
    public void switchToLoadGameView() {
        switchViewPresenter.switchToLoadGameView();
    }

    @Override
    public void switchToHighScoreView() {
        switchViewPresenter.switchToHighScoreView();
    }

}