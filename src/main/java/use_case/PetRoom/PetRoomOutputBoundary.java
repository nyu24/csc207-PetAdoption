package use_case.PetRoom;

public interface PetRoomOutputBoundary {
    void prepareSuccessView(PetRoomOutputData petRoomOutputData);
    void prepareFailView(String message);
}
