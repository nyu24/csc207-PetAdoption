package use_case.PetRoom;

public interface PetRoomOutputBoundary {
    /**
     * Update the Values.
     * @param petRoomOutputData the output data
     */
    void updateValues(PetRoomOutputData petRoomOutputData);

    /**
     * Prepare the fail view.
     * @param message message for prepare fail view
     */
    void prepareFailView(String message);

    /**
     * Switch to the saved game.
     */
    void switchToSaveGameView();
}
