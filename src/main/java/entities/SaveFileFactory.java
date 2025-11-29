package entities;

public class SaveFileFactory {

    public SaveFile create(int timeLeft, int currScore, Pet currPet, APIPet apiPet) {
        return new SaveFile(timeLeft, currScore, currPet, apiPet);
    }
}
