package use_case.save_game;

import entities.Pet;

/**
 * The Input Data for the SaveGame Use case.
 * This is all the data that is saved to be loaded for LoadGame.
 */

public class SaveGameInputData {

    private final int timeLeft;
    private final Pet currentPet;

    public SaveGameInputData(int timeLeft, Pet currentPet) {
        this.timeLeft = timeLeft;
        this.currentPet = currentPet;
    }

    int getTimeLeft() {return timeLeft;}

    Pet getcurrentPet() {return currentPet;}

}
