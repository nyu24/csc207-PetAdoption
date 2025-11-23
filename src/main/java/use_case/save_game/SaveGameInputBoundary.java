package use_case.save_game;

/**
 *  Input Boundary for actions the revolve around saving the game.
 */

public interface SaveGameInputBoundary {
    void execute(SaveGameInputData saveGameInputData);

    void switchToSaveGameView();
}
