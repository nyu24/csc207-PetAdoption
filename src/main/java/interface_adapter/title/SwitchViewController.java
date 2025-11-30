package interface_adapter.title;

import use_case.switch_view.SwitchViewInputBoundary;

public class SwitchViewController {

    private SwitchViewInputBoundary switchViewUseCaseInteractor;

    public SwitchViewController(SwitchViewInputBoundary switchViewUseCaseInteractor) {
        this.switchViewUseCaseInteractor = switchViewUseCaseInteractor;
    }

    public void switchToSetParamView() { switchViewUseCaseInteractor.switchToSetParamView(); }

    public void switchToLoadGameView() { switchViewUseCaseInteractor.switchToLoadGameView(); }

    public void switchToHighScoreView() { switchViewUseCaseInteractor.switchToHighScoreView(); }
}
