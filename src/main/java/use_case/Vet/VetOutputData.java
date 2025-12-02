package use_case.Vet;

import entities.Pet;

import java.util.List;

public class VetOutputData {
    private boolean gameWin;
    private int score = -1;
    private Pet currPet;


    private List<List<String>> requirements;

    public VetOutputData(boolean gameWin) {
        this.gameWin = gameWin;
    }

    public VetOutputData(List<List<String>> requirements, int score) {
        this.requirements = requirements;
        this.score = score;
    }

    public int getScore() {
        return score;
    }
    public boolean isGameWin() {
        return gameWin;
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
