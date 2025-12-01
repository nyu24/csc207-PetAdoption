package use_case.save_game;

public interface SaveGameOutputBoundary {
    /**
     * Prepares the Pet Room View with saving data into file, this is the main game view.
     */
    void prepareSuccessView(SaveGameOutputData outputData);

    /**
     * Switches to Pet Room View.
     */
    void switchToPetRoomView();
}
