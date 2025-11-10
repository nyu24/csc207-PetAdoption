package use_case.save_game;

import entities.Pet;

/**
 * The Input Data for the SaveGame Use case.
 * This is all the data that is saved to be loaded for LoadGame.
 */

public class SaveGameInputData {

    private final int timeLeft;
    private final String petInformation; // String to be replaced with pet object.

    public SaveGameInputData(int timeLeft, String petInformation) {
        this.timeLeft = timeLeft;
        this.petInformation = petInformation;
    }

    int getTimeLeft() {return timeLeft;}

    String getPetInformation() {return petInformation;}

}
