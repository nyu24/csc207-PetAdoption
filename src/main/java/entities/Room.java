package entities;

public class Room {
    private int food;
    private int water;
    private int cleanliness;
    private int happiness;

    private String roomType = "_room_basic.jpg";
    private String petType;
    private int score;

    public void applyFoodAction() {
        roomType = petType + "_room_food.jpg";

    }

    public void applyWaterAction() {
        roomType = petType + "_room_water.jpg";
    }

    public void applyCleanlinessAction() {
        roomType = petType + "_room_clean.jpg";

    }

    public void applyHappinessAction() {
        roomType = petType + "_room_happy.jpg";

    }

    public void tick(int currFood, int currWater, int currHappiness, int currClean){
        food = Math.max(0, currFood - 1);
        water = Math.max(0, currWater - 1);
        cleanliness = Math.max(0, currClean - 1);
        happiness = Math.max(0, currHappiness - 1);
    }
    public int getFood() {
        return food;
    }
    public int getWater() {
        return water;
    }
    public int getCleanliness() {
        return cleanliness;
    }
    public int getHappiness() {
        return happiness;
    }
    public String getRoomType() {return roomType;}
    public String getPetType() {return petType;}
    public void setPetType(String petType) {this.petType = petType;}
    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}
}
