package use_case.PetRoom;

import entities.Pet;

public interface PetRoomInputBoundary {
    /**
     * Execute method.
     * @param petRoomInputData pet data.
     */
    void execute(PetRoomInputData petRoomInputData);

    /**
     * Set room.
     * @param food level
     * @param water level
     * @param cleanliness level
     * @param happiness level
     * @param currPet level
     */
    void setRoomParameters(int food, int water, int cleanliness, int happiness, Pet currPet);

    /**
     * Switch view.
     */
    void switchToSaveGameView();

}
