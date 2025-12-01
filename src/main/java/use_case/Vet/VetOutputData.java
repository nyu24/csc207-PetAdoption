package use_case.Vet;

import java.util.List;

public class VetOutputData {
    private final int score;

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

}
