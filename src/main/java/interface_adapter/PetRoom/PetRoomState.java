package interface_adapter.PetRoom;

public class PetRoomState {
    private int food;
    private int water;
    private int happiness;
    private int cleanliness;
    private String backgroundImageName = "dog_room_basic.jpg";
    private boolean buttonsEnabled = true;

    public int getFood() {return food;}
    public void setFood(int food) {this.food = food;}
    public int getWater() {return water;}
    public void setWater(int water) {this.water = water;}
    public int getHappiness() {return happiness;}
    public void setHappiness(int happiness) {this.happiness = happiness;}
    public int getCleanliness() {return cleanliness;}
    public void setCleanliness(int cleanliness) {this.cleanliness = cleanliness;}
    public String getBackgroundImageName() {return backgroundImageName;}
    public void setBackgroundImageName(String backgroundImageName) {this.backgroundImageName = getBackgroundImageName();}
    public boolean getButtonsEnabled() {return buttonsEnabled;}
    public void setButtonsEnabled(boolean buttonsEnabled) {this.buttonsEnabled = buttonsEnabled;}

}
