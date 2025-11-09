package use_case.save_game;

/**
 * The Input Data for the SaveGame Use case.
 * This is all the data that is saved to be loaded for LoadGame.
 */

public class SaveGameInputData {

    private final int currentScore;
    private final int timeLeft;
    private final String petInformation; // String to be replaced with pet object.

    public SaveGameInputData(int currentScore, int timeLeft, String petInformation) {
        this.currentScore = currentScore;
        this.timeLeft = timeLeft;
        this.petInformation = petInformation;
    }

    int getCurrentScore() {return currentScore;}

    int getTimeLeft() {return timeLeft;}

    String getPetInformation() {return petInformation;}

}
