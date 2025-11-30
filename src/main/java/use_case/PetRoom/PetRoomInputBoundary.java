package use_case.PetRoom;

import entities.Pet;

public interface PetRoomInputBoundary {
    void execute(PetRoomInputData petRoomInputData);

    void setRoomParameters(int food, int water, int cleanliness, int happiness, Pet currPet);

    void switchToSaveGameView();

//    void switchToVetView();
}
