package entities;

/**
 * Factory for creating save file objects.
 */

public class SaveFileFactory {

    public SaveFile create(int timeLeft, int currScore, Pet currPet, ApiPet apiPet) {
        return new SaveFile(timeLeft, currScore, currPet, apiPet);
    }
}
