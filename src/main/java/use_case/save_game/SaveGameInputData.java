package use_case.save_game;

import entities.Pet;

/**
 * The Input Data for the SaveGame Use case.
 * This is all the data that is saved to be loaded for LoadGame.
 */

public class SaveGameInputData {

    private final Double timeLeft;
    private final Pet currentPet;

    public SaveGameInputData(Double timeLeft, Pet currentPet) {
        this.timeLeft = timeLeft;
        this.currentPet = currentPet;
    }

    Double getTimeLeft() {return timeLeft;}

    Pet getcurrentPet() {return currentPet;}

}
