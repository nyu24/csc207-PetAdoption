package entities;

/**
 * This class holds information required for a save file for the game.
 */

public class SaveFile {
    private final int timeLeft;
    private final int currScore;
    private final Pet currPet;
    private final APIPet apiPet;

    public SaveFile(int timeLeft, int currScore, Pet currPet, APIPet apiPet) {
        this.timeLeft = timeLeft;
        this.currScore = currScore;
        this.currPet = currPet;
        this.apiPet = apiPet;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public int getCurrScore() { return currScore; }

    public Pet getCurrPet() { return currPet; }

    public APIPet getApiPet() {
        return apiPet; }
}
