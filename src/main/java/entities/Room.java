package entities;

public class Room {
    private int food;
    private int water;
    private int cleanliness;
    private int happiness;
    private String roomType = "_room_basic.jpg";
    private String petType;
    private Pet currPet;

    public void applyFoodAction() {
        setFood(Math.min(Constants.MAX_STAT, getFood() + Constants.MAIN_GAIN));
        setHappiness(Math.min(Constants.MAX_STAT, getHappiness() + Constants.SUB_GAIN));
        roomType = petType + "_room_food.jpg";


    }

    public void applyWaterAction() {
        setWater(Math.min(Constants.MAX_STAT, getWater() + Constants.MAIN_GAIN));
        setHappiness(Math.min(Constants.MAX_STAT, getHappiness() + Constants.SUB_GAIN));
        roomType = petType + "_room_water.jpg";

    }

    public void applyCleanlinessAction() {
        setCleanliness(Math.min(Constants.MAX_STAT, getCleanliness() + Constants.MAIN_GAIN));
        setHappiness(Math.min(Constants.MAX_STAT, getHappiness() + Constants.SMALL_GAIN));
        roomType = petType + "_room_clean.jpg";

    }

    public void applyHappinessAction() {
        setHappiness(Math.min(Constants.MAX_STAT, getHappiness() + Constants.MAIN_GAIN));
        roomType = petType + "_room_happy.jpg";

    }

    public void tick(){
        setFood(Math.max(0, getFood() - 1));
        setWater(Math.max(0, getWater() - 1));
        setCleanliness(Math.max(0, getCleanliness() - 1));
        if (getFood() < Constants.SAD_THRESHOLD && getWater() < Constants.SAD_THRESHOLD && getCleanliness() <
                Constants.SAD_THRESHOLD){
            setHappiness(Math.max(0, getHappiness() - 2));
        }
        else{
            setHappiness(Math.max(0, getHappiness() - 1));
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

    public void setFood(int food) {
        this.food = food;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public void setCleanliness(int cleanliness) {
        this.cleanliness = cleanliness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public Pet getCurrPet() {
        return currPet;
    }

    public void setCurrPet(Pet currPet) {
        this.currPet = currPet;
    }
}
