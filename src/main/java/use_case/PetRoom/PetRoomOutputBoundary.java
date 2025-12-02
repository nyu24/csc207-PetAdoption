package use_case.PetRoom;

public interface PetRoomOutputBoundary {
    void updateValues(PetRoomOutputData petRoomOutputData);
    void prepareFailView(String message);
    void switchToSaveGameView();
//    void prepareSuccessView();
//    void switchToVetView();
}
