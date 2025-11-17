package entities;

/**
 * This class holds information required for a save file for the game.
 */

public class Savefile {
    private final int timeLeft;
    private final Pet petInformation;
    public Savefile(int timeLeft, Pet petInformation) {
        this.timeLeft = timeLeft;
        this.petInformation = petInformation;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public Pet getPetInformation() {
        return petInformation;
    }
}
