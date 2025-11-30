package interface_adapter.PetRoom;

public class PetRoomState {
    private int food;
    private int water;
    private int happiness;
    private int cleanliness;
    private String roomType;
    private String petType;
    private int timer;
    private int score = 0;

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public int getFood() {return food;}
    public void setFood(int food) {this.food = food;}

    public int getWater() {return water;}
    public void setWater(int water) {this.water = water;}

    public int getHappiness() {return happiness;}
    public void setHappiness(int happiness) {this.happiness = happiness;}

    public int getCleanliness() {return cleanliness;}
    public void setCleanliness(int cleanliness) {this.cleanliness = cleanliness;}



    public String getRoomType() {return roomType;}
    public void setRoomType(String roomType) {this.roomType = roomType;}
    public String getPetType() {return petType;}
    public void setPetType(String petType) {this.petType = petType;}
    public int getTimer() {return timer;}
    public void setTimer(int timer) {this.timer = timer;}

}
