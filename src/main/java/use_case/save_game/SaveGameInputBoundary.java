package use_case.save_game;

public interface SaveGameInputBoundary {
    void execute(SaveGameInputData saveGameInputData);

    void switchToSaveGameView();
}
