package interface_adapter.PetRoom;
import entities.Pet;

public class PetRoomState {
    private int food;
    private int water;
    private int happiness;
    private int cleanliness;
    private String roomType;
    private String petType;

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
    public String getPetType() {return this.petType;}
    public void setPetType(String petType) {this.petType = petType;}
}
