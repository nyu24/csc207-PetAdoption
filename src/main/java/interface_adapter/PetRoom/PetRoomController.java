
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
     * Execute method.
     * @param action what button is pressed.
     * @param score the score of the current game.
     * @param petType the pet type selected by user.
     */
    public void execute(String action, int score, String petType) {
        petInputData = new PetRoomInputData(action, score, petType);
        petRoomInteractor.execute(petInputData);
    }

    /**
     * Send the pet data to vet use case.
     * @param stats map of each string to value
     * @param score total overall score
     * @param currPet cerrent pet selected
     */
    public void sendPetData(Map<String, Integer> stats, int score, Pet currPet) {
        vetInputData = new VetInputData(stats, score, currPet);
        this.vetUseCaseInteractor.execute(vetInputData);
    }

    /**
     * Set the room parameters for the room.
     * @param food current food level
     * @param water current water level
     * @param cleanliness current clean level
     * @param happiness current joy level
     * @param currPet current pet chosen
     */
    public void setRoomParameters(int food, int water, int cleanliness, int happiness, Pet currPet) {
        petRoomInteractor.setRoomParameters(food, water, cleanliness, happiness, currPet);
    }

    /**
     * Switch view to save game.
     */
    public void switchToSaveGameView() {
        petRoomInteractor.switchToSaveGameView();
    }
}