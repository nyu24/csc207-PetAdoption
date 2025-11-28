package use_case.save_game;

public interface SaveGameOutputBoundary {
    /**
     * Prepares the save view.
     */
    void prepareSaveView();

    /**
     * Prepares the Pet Room View, this is the main game view.
     */
    void preparePetRoomView();

    /**
     * Switches to Save Game View.
     */
    void switchToSaveGameView();

    /**
     * Switches to Pet Room View.
     */
    void switchToPetRoomView();
}
