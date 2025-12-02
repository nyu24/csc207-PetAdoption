package interface_adapter.PetRoom;

import java.util.Map;

import entities.Pet;
import use_case.PetRoom.PetRoomInputBoundary;
import use_case.PetRoom.PetRoomInputData;
import use_case.Vet.VetInputBoundary;
import use_case.Vet.VetInputData;

public class PetRoomController {
    private final PetRoomInputBoundary petRoomInteractor;
    private final VetInputBoundary vetUseCaseInteractor;
    private PetRoomInputData petInputData;
    private VetInputData vetInputData;

    public PetRoomController(PetRoomInputBoundary petRoomInteractor, VetInputBoundary vetUseCaseInteractor) {
        this.petRoomInteractor = petRoomInteractor;
        this.vetUseCaseInteractor = vetUseCaseInteractor;
    }

    /**
     * Execute the interactor.
     * @param action which button is clicked
     * @param score current score
     * @param petType current pet type
     */

    public void execute(String action, int score, String petType) {
        petInputData = new PetRoomInputData(action, score, petType);
        petRoomInteractor.execute(petInputData);
    }

    /**
     * Send the pet data.
     * @param stats stats map
     * @param score current score
     * @param currPet current pet object
     */
    public void sendPetData(Map<String, Integer> stats, int score, Pet currPet) {
        vetInputData = new VetInputData(stats, score, currPet);
        this.vetUseCaseInteractor.execute(vetInputData);
    }

    /**
     * Set room.
     * @param food level
     * @param water level
     * @param cleanliness level
     * @param happiness level
     * @param currPet level
     */
    public void setRoomParameters(int food, int water, int cleanliness, int happiness, Pet currPet) {
        petRoomInteractor.setRoomParameters(food, water, cleanliness, happiness, currPet);
    }

    /**
     * Switch to save game.
     */
    public void switchToSaveGameView() {
        petRoomInteractor.switchToSaveGameView();
    }

}