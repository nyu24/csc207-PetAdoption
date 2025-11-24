package interface_adapter.PetRoom;
import entities.Pet;
import use_case.save_game.PetRoom.PetRoomInputData;
import use_case.save_game.PetRoom.PetRoomInputBoundary;

public class PetRoomController {
    private final PetRoomInputBoundary petRoomInteractor;
    public PetRoomController(PetRoomInputBoundary petRoomInteractor){
        this.petRoomInteractor = petRoomInteractor;
    }
    public void execute(String petName, String petType, int food, int water, int joy, int clean){
        final PetRoomInputData petRoomInputData = new PetRoomInputData(petName, petType,food, water, joy, clean);
        petRoomInteractor.execute(petRoomInputData);

    }

}
