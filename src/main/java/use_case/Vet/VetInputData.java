package use_case.Vet;

import java.util.Map;

import entities.Pet;

public class VetInputData {
    private final Pet pet;
    private Map<String, Integer> stats;
    private int score = -1;

    public VetInputData(Map<String, Integer> stats, int score, Pet pet) {
        this.stats = stats;
        this.score = score;
        this.pet = pet;
    }

    public Pet getPet() {

        return pet;
    }

    public Map<String, Integer> getStats() {

        return this.stats;
    }

    public int getScore() {
        return this.score;
    }

}
