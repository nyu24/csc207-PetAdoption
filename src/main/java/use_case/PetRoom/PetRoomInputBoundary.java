package use_case.PetRoom;

public interface PetRoomInputBoundary {
    void execute(PetRoomInputData petRoomInputData);

    void setRoomParameters(int food, int water, int cleanliness, int happiness);

    void switchToSaveGameView();

//    void switchToVetView();
}
