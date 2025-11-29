package entities;

public class Room {
    private int food;
    private int water;
    private int cleanliness;
    private int happiness;

    private String roomType = "_room_basic.jpg";
    private String petType;

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

    public void tick(){
        food = Math.max(0, food - 1);
        water = Math.max(0, water - 1);
        cleanliness = Math.max(0, cleanliness - 1);
        if (food < 30 && water < 30 && cleanliness < 30){
            happiness = Math.max(0, happiness - 2);
        }
        else{
            happiness = Math.max(0, happiness - 1);
        }
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
}
