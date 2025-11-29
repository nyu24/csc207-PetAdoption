package interface_adapter.PetRoom;
import use_case.PetRoom.PetRoomInputData;
import use_case.PetRoom.PetRoomInputBoundary;
import use_case.Vet.VetInputBoundary;
import use_case.Vet.VetInputData;
import use_case.Vet.VetUseCaseInteractor;

import java.util.Map;

public class PetRoomController {
    private final PetRoomInputBoundary petRoomInteractor;
    private final VetInputBoundary vetUseCaseInteractor;

    public PetRoomController(PetRoomInputBoundary petRoomInteractor, VetInputBoundary vetUseCaseInteractor) {
        this.petRoomInteractor = petRoomInteractor;
        this.vetUseCaseInteractor = vetUseCaseInteractor;
    }
    public void execute(String action){
        PetRoomInputData petInputData = new PetRoomInputData(action);
        petRoomInteractor.execute(petInputData);
    }

    public void sendPetData(Map<String, Integer> stats){
        VetInputData vetInputData = new VetInputData(stats);
        this.vetUseCaseInteractor.execute(vetInputData);
    }

//    public void switchToVetView(){
//        this.petRoomInteractor.switchToVetView();
//    }
}