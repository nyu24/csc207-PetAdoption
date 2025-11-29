package use_case.PetRoom;

public class PetRoomInputData {
    private String action;
    private int score = -1;
    private String petType;
    private int food;
    private int water;
    private int clean;
    private int happy;
    public PetRoomInputData(String action, int score, String petType, int food, int water, int clean, int happy) {
        this.action = action;
        this.score = score;
        this.petType = petType;
        this.food = food;
        this.water = water;
        this.clean = clean;
        this.happy = happy;

    }
    public PetRoomInputData(int score) {
        this.score = score;
    }
    public int  getScore() {
        return score;
    }
    public void setScore(int score) {}
    public String getAction() {return action;}

    public int getFood() {
        return food;
    }

    public int getWater() {
        return water;
    }
    public int getClean() {
        return clean;
    }
    public int getHappy() {
        return happy;
    }

    public String getPetType() {
        return petType;
    }

    public void setAction(String petType) {this.petType = petType;}
}
