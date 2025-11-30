package entities;

public class Room {
    private int food = 100;
    private int water = 100;
    private int cleanliness = 100;
    private int happiness = 50;
    private String roomType = "_room_basic.jpg";
    private String petType;

    public void applyFoodAction() {
        food = Math.min(100, food + 15);
        happiness = Math.min(100, happiness + 5);
        roomType = petType + "_room_food.jpg";


    }

    public void applyWaterAction() {
        water = Math.min(100, water + 15);
        happiness = Math.min(100, happiness + 5);
        roomType = petType + "_room_water.jpg";

    }

    public void applyCleanlinessAction() {
        cleanliness = Math.min(100, cleanliness + 15);
        happiness = Math.min(100, happiness + 3);
        roomType = petType + "_room_clean.jpg";

    }

    public void applyHappinessAction() {
        happiness = Math.min(100, happiness + 15);
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
