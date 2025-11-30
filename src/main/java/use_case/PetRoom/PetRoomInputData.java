package use_case.PetRoom;

public class PetRoomInputData {
    private String action;
    private int score = -1;
    private String petType;
    public PetRoomInputData(String action, int score, String petType) {
        this.action = action;
        this.score = score;
        this.petType = petType;
    }
    public PetRoomInputData(int score) {
        this.score = score;
    }
    public int  getScore() {
        return score;
    }
    public void setScore(int score) {}
    public String getAction() {return action;}

    public void setPetType(String petType) {
        this.petType = petType;
    }
    public String getPetType() {return petType;}
}
