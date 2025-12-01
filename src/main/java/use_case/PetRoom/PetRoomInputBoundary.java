package use_case.PetRoom;

import entities.Pet;

public interface PetRoomInputBoundary {
    /**
     * Execute input data.
     * @param petRoomInputData input data.
     */
    void execute(PetRoomInputData petRoomInputData);

    /**
     * Set the room parameters.
     * @param food food level.
     * @param water water level.
     * @param cleanliness clean lvevel.
     * @param happiness joy level.
     * @param currPet current Pet.
     */

    void setRoomParameters(int food, int water, int cleanliness, int happiness, Pet currPet);

    /**
     * Switch the game to save view.
     */
    void switchToSaveGameView();

}
