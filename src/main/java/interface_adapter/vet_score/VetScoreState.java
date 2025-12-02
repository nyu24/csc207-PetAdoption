package interface_adapter.vet_score;

import java.util.ArrayList;
import java.util.List;

import entities.Pet;

public class VetScoreState {

    private int score;

    private List<List<String>> requirements = new ArrayList<>();

    private Pet currPet;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setMetRequirements(List<List<String>> requirements) {

        this.requirements = requirements;
    }

    public List<List<String>> getRequirements() {
        return this.requirements;
    }

    public Pet getCurrPet() {
        return currPet;
    }

    public void setCurrPet(Pet currPet) {
        this.currPet = currPet;
    }
}
