package interface_adapter.load_game;

import use_case.load_game.LoadGameInputBoundary;

public class LoadGameController {

    private final LoadGameInputBoundary loadGameUseCaseInteractor;

    public LoadGameController(LoadGameInputBoundary loadGameUseCaseInteractor) {
        this.loadGameUseCaseInteractor = loadGameUseCaseInteractor;
    }

    /**
     * Executes the Load Game Use Case.
     */
    public void execute() {
        loadGameUseCaseInteractor.execute();
    }

    /**
     * Executes the switch to Title Screen Use Case.
     */
    public void switchToTitleScreen() {
        loadGameUseCaseInteractor.switchToTitleView();
    }
}

