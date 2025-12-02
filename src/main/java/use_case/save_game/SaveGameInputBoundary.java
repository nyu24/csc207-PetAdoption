package use_case.save_game;

public interface SaveGameInputBoundary {

    /**
     * Executes the save game use case.
     * @param saveGameInputData input data.
     */
    void execute(SaveGameInputData saveGameInputData);

    /**
     * Executes the switch to pet room view use case.
     */
    void switchToPetRoomView();
}