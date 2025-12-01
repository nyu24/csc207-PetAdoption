package interface_adapter.vet_score;

import java.util.ArrayList;
import java.util.List;

public class VetScoreState {

    private int score;

    private List<List<String>> requirements = new ArrayList<>();

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
}
