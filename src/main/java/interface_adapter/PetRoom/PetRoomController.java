package interface_adapter.PetRoom;
import use_case.PetRoom.PetRoomInputData;
import use_case.PetRoom.PetRoomInputBoundary;

public class PetRoomController {
    private final PetRoomInputBoundary petRoomInteractor;

    public PetRoomController(PetRoomInputBoundary petRoomInteractor) {
        this.petRoomInteractor = petRoomInteractor;
    }
    public void execute(String action){
        PetRoomInputData inputData = new PetRoomInputData(action);
        petRoomInteractor.execute(inputData);
    }
}