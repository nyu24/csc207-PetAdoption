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
    public void execute(String action, int score, String petType){
        PetRoomInputData petInputData = new PetRoomInputData(action, score, petType);
        petRoomInteractor.execute(petInputData);
    }

    public void sendPetData(Map<String, Integer> stats, int score){
        VetInputData vetInputData = new VetInputData(stats, score);
        this.vetUseCaseInteractor.execute(vetInputData);
    }

    public void setRoomParameters(int food, int water, int cleanliness, int happiness) {
        petRoomInteractor.setRoomParameters(food, water, cleanliness, happiness);
    }

//    public void switchToVetView(){
//        this.petRoomInteractor.switchToVetView();
//    }
}