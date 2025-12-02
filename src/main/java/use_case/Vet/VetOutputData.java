package use_case.Vet;

import java.util.List;

import entities.Pet;

public class VetOutputData {
    private final int score;
    private Pet currPet;

    private final List<List<String>> requirements;

    public VetOutputData(List<List<String>> requirements, int score) {
        this.requirements = requirements;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public List<List<String>> getRequirements() {
        return requirements;
    }

    public void setPet(Pet currPet) {
        this.currPet = currPet;
    }

    public Pet getCurrPet() {
        return currPet;
    }
}
