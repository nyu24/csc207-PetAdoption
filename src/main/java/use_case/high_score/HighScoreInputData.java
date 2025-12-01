package use_case.high_score;

public class HighScoreInputData {
    private final int currentScore;
    private final boolean isToSave;

    public HighScoreInputData(int currentScore, boolean isToSave) {
        this.currentScore = currentScore;
        this.isToSave = isToSave;
    }
    public int getCurrentScore() {
        return currentScore;
    }

    public boolean isToSave() {
        return isToSave;
    }
}
