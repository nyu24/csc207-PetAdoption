package use_case.PetRoom;

public interface PetRoomOutputBoundary {
    void updateValues(PetRoomOutputData petRoomOutputData);
    void prepareFailView(String message);
//    void prepareSuccessView();
//    void switchToVetView();
}
