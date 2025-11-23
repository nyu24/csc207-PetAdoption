package use_case.Vet;

import java.util.List;

public class VetOutputData {
    private boolean gameWin;

    private List<List<String>> requirements;

    public VetOutputData(boolean gameWin) {
        this.gameWin = gameWin;
    }

    public VetOutputData(List<List<String>> requirements) {
        this.requirements = requirements;
    }

    public boolean isGameWin() {
        return gameWin;
    }

    public List<List<String>> getRequirements() {
        return requirements;
    }
}
