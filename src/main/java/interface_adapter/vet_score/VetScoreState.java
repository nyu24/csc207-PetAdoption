package interface_adapter.vet_score;

import entities.Pet;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class VetScoreState {
    private User user;

    private int score = 0;

    private List<List<String>> requirements =  new ArrayList<>();

    private Pet currPet;

    public VetScoreState(VetScoreState copy) {
        this.user = copy.user;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public VetScoreState() {
    }

    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}



    public void SetUserWin(boolean win) {
        this.user.setMetRequirements(win);
    }

    public void setMetRequirements(List<List<String>> requirements) {
        this.requirements = requirements;
    }

    public List<List<String>> getRequirements() {return this.requirements;}

    public Pet getCurrPet() { return currPet; }

    public void setCurrPet(Pet currPet) { this.currPet = currPet; }
}
