package interface_adapter.vet_score;

import entities.Pet;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class VetScoreState {

    private int score;

    private Pet currPet;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setMetRequirements(List<List<String>> petRequirements) {
        this.requirements = petRequirements;
    }

    public List<List<String>> getRequirements() {
        return this.requirements;
    }

    public List<List<String>> getRequirements() {return this.requirements;}

    public Pet getCurrPet() { return currPet; }

    public void setCurrPet(Pet currPet) { this.currPet = currPet; }
}
