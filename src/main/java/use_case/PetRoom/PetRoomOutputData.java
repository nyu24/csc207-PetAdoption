package use_case.PetRoom;

public class PetRoomOutputData {
    private final int finalFood;
    private final int finalWater;
    private final int finalHappiness;
    private final int finalCleanliness;
    private final String roomType;
    private final String petType;


    public PetRoomOutputData(int finalFood, int finalWater, int finalHappiness, int finalCleanliness, String  roomType, String petType) {
        this.finalFood = finalFood;
        this.finalWater = finalWater;
        this.finalHappiness = finalHappiness;
        this.finalCleanliness = finalCleanliness;
        this.roomType = roomType;
        this.petType = petType;
    }
    public int getFinalFood() {return finalFood;}
    public int getFinalWater() {return finalWater;}
    public int getFinalHappiness() {return finalHappiness;}
    public int getFinalCleanliness() {return finalCleanliness;}

    public String getPetType() {
        return petType;
    }
    public String getRoomType() {
        return roomType;
    }
}