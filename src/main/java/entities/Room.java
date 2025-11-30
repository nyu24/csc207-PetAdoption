package entities;

public class Room {
    private int food = 100;
    private int water = 100;
    private int cleanliness = 100;
    private int happiness = 100;
    private String roomType = "_room_basic.jpg";
    private String petType;
    private Pet currPet;

    public void applyFoodAction() {
        setFood(Math.min(100, getFood() + 15));
        setHappiness(Math.min(100, getHappiness() + 5));
        roomType = petType + "_room_food.jpg";


    }

    public void applyWaterAction() {
        setWater(Math.min(100, getWater() + 15));
        setHappiness(Math.min(100, getHappiness() + 5));
        roomType = petType + "_room_water.jpg";

    }

    public void applyCleanlinessAction() {
        setCleanliness(Math.min(100, getCleanliness() + 15));
        setHappiness(Math.min(100, getHappiness() + 3));
        roomType = petType + "_room_clean.jpg";

    }

    public void applyHappinessAction() {
        setHappiness(Math.min(100, getHappiness() + 15));
        roomType = petType + "_room_happy.jpg";

    }

    public void tick(){
        setFood(Math.max(0, getFood() - 1));
        setWater(Math.max(0, getWater() - 1));
        setCleanliness(Math.max(0, getCleanliness() - 1));
        if (getFood() < 30 && getWater() < 30 && getCleanliness() < 30){
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
