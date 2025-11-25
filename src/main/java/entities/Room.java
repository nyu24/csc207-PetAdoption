package entities;

public class Room {
    private int food;
    private int water;
    private int cleanliness;
    private int happiness;

    public void applyFoodAction() {
        food = Math.min(100, food + 15);
        happiness = Math.min(100, happiness + 5);

    }

    public void applyWaterAction() {
        water = Math.min(100, water + 15);
        happiness = Math.min(100, happiness + 5);

    }

    public void applyCleanlinessAction() {
        cleanliness = Math.min(100, cleanliness + 15);
        happiness = Math.min(100, happiness + 3);

    }

    public void tick(){
        food = Math.max(0, food - 1);
        water = Math.max(0, food - 1);
        cleanliness = Math.max(0, food - 1);
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
}
