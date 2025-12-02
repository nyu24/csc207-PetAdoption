package use_case.PetRoom;

public interface PetRoomOutputBoundary {
    /**
     * Update values.
     * @param petRoomOutputData output data.
     */
    void updateValues(PetRoomOutputData petRoomOutputData);

    /**
     * Prepare fail.
     * @param message fail view message.
     */
    void prepareFailView(String message);

    /**
     * Switch view.
     */
    void switchToSaveGameView();
}
