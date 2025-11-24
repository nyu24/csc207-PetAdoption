package entities;

public class Room {
    private int food = 100;
    private int water = 100;
    private int cleanliness = 100;
    private int happiness = 100;
    private int tempFoodTicks = 0;
    private int tempWaterTicks = 0;
    private int tempCleanTicks = 0;
    private String backgroundMode = "basic";

    public void applyFoodAction() {
        food = food + 15;
        if (food > 100) {
            food = 100;
        }
        tempFoodTicks = 3;
        updateBackgroundMode();
    }

    public void applyWaterAction() {
        water = water + 15;
        if (water > 100) {
            water = 100;
        }
        tempWaterTicks = 3;
        updateBackgroundMode();
    }

    public void applyCleanlinessAction() {
        cleanliness = cleanliness + 15;
        if (cleanliness > 100) {
            cleanliness = 100;
        }
        tempCleanTicks = 3;
        updateBackgroundMode();
    }

    public void sad() {
        if (food < 30 && water < 30 && cleanliness < 30) {
            happiness = happiness - 1;
            if (happiness < 0) {
                happiness = 0;
                updateBackgroundMode();
            }
        }
    }
    public void tick(){
        if (food > 0) food = food - 1;
        if (water > 0) water = water - 1;
        if (cleanliness > 0) cleanliness = cleanliness - 1;
        if (tempFoodTicks > 0) tempFoodTicks =  tempFoodTicks + 1;
        if (tempWaterTicks > 0) tempWaterTicks =   tempWaterTicks + 1;
        if (tempCleanTicks > 0) tempCleanTicks =  tempCleanTicks + 1;

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
