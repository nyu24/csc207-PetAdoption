package use_case.load_game;

public interface LoadGameOutputBoundary {
    void prepareLoadView();

    void prepareFailView();

    void switchToLoadGameView();

    void switchToFailView();

}
