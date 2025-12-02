package entities;

/**
 * Factory for creating save file objects.
 */

public class SaveFileFactory {

    /**
     * Creates a save file object given the parameters.
     * @param timeLeft how much time is left before the game ends
     * @param currScore current score of the game state
     * @param currPet current pet associated with the game state
     * @param apiPet current api pet associated with the game state
     * @return a save file
     */

    public SaveFile create(int timeLeft, int currScore, Pet currPet, ApiPet apiPet) {
        return new SaveFile(timeLeft, currScore, currPet, apiPet);
    }
}