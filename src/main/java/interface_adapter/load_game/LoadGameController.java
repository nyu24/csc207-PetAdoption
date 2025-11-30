package interface_adapter.load_game;

import use_case.load_game.LoadGameInputBoundary;

public class LoadGameController {

    private final LoadGameInputBoundary loadGameUseCaseInteractor;

    public LoadGameController(LoadGameInputBoundary loadGameUseCaseInteractor) {
        this.loadGameUseCaseInteractor = loadGameUseCaseInteractor;
    }

    public void execute() {
        loadGameUseCaseInteractor.execute();
    }

    public void switchToTitleScreen() { loadGameUseCaseInteractor.switchToTitleView(); }
}

