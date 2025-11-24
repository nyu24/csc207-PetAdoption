package entities;

public class Room {
    private int food = 100;
    private int water = 100;
    private int cleanliness = 100;
    private int happiness = 100;
    private int tempFoodTicks = 0;
    private int tempWaterTicks = 0;
    private String backgroundMode = "basic";


    public void applyFoodAction() {
        food = Math.min(100, food + 15);
        tempFoodTicks = 3;
    }

    public void applyWaterAction() {
        water = Math.min(100, water + 15);
        tempWaterTicks = 3;
    }

    public void applyCleanlinessAction() {
        cleanliness = Math.min(100, cleanliness + 15);
    }

    public void sad() {
        if (food < 30 && water < 30 && cleanliness < 30) {
            happiness = Math.max(0, happiness - 1);
        }
    }
    public void tick(){
        sad();
        updateBackgroundMode();
    }
    public void updateBackgroundMode(){
        if (happiness < 30){
            backgroundMode = "sleepy";
            return;
        }
        if (tempFoodTicks > 0){
            backgroundMode = "food";
            return;
        }
        if (tempWaterTicks > 0){
            backgroundMode = "water";
            return;
        }
        backgroundMode = "basic";
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
    public String getBackgroundMode() {
        return backgroundMode;
    }
    public boolean isBasic(){
        return backgroundMode.equals("basic");
    }
}
