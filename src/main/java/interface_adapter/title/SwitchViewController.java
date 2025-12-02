package interface_adapter.title;

import use_case.switch_view.SwitchViewInputBoundary;

public class SwitchViewController {

    private SwitchViewInputBoundary switchViewUseCaseInteractor;

    public SwitchViewController(SwitchViewInputBoundary switchViewUseCaseInteractor) {
        this.switchViewUseCaseInteractor = switchViewUseCaseInteractor;
    }

    /**
     * This switches the TitleView to the SetParamView.
     */
    public void switchToSetParamView() {
        switchViewUseCaseInteractor.switchToSetParamView();
    }

    /**
     * This switches the TitleView to the LoadGameView.
     */
    public void switchToLoadGameView() {
        switchViewUseCaseInteractor.switchToLoadGameView();
    }

    /**
     * This switches the TitleView to HighScoreView.
     */
    public void switchToHighScoreView() {
        switchViewUseCaseInteractor.switchToHighScoreView();
    }
}