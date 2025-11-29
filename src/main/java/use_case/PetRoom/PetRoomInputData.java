package use_case.PetRoom;

public class PetRoomInputData {
    private String action;
    private int score = -1;
    public PetRoomInputData(String action, int score) {
        this.action = action;
        this.score = score;
    }
    public PetRoomInputData(int score) {
        this.score = score;
    }
    public int  getScore() {
        return score;
    }
    public void setScore(int score) {}
    public String getAction() {return action;}
}
