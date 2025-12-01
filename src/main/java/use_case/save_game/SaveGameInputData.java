package use_case.save_game;

import entities.ApiPet;
import entities.Pet;

/**
 * The Input Data for the SaveGame Use case.
 * This is all the data that is saved to be loaded for LoadGame.
 */

public class SaveGameInputData {

    private final int timeLeft;
    private final int currScore;
    private final Pet currPet;
    private final ApiPet apiPet;

    public SaveGameInputData(int timeLeft, int currScore, Pet currPet) {
        this.timeLeft = timeLeft;
        this.currScore = currScore;
        this.currPet = currPet;
        this.apiPet = currPet.getApiPet();
    }

    int getTimeLeft() {
        return timeLeft;
    }

    public int getCurrScore() {
        return currScore;
    }

    public Pet getCurrPet() {
        return currPet;
    }

    public ApiPet getApiPet() {
        return apiPet;
    }
}
