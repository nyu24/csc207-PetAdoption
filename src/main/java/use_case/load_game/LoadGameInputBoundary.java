package use_case.load_game;

public interface LoadGameInputBoundary {
    void execute(LoadGameInputData saveGameInputData);

    void switchToLoadGameView();
}
