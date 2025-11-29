package use_case.save_game;

public interface SaveGameOutputBoundary {
    /**
     * Prepares the success view for the SaveGame Use Case when there is no save data.
     */
    void prepareSaveView();

    /**
     * Prepares the success view for the SaveGame Use Case when there is a save data.
     */
    void prepareWarningView();

    /**
     * Switches to Save Game View.
     */
    void switchToSaveGameView();

    /**
     * Switches to Warning View.
     */
    void switchToWarningView();
}
