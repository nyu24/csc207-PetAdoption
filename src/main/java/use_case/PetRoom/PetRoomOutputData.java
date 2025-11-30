package use_case.PetRoom;

public class PetRoomOutputData {
    private final int finalFood;
    private final int finalWater;
    private final int finalHappiness;
    private final int finalCleanliness;
    private final int score;
    private final String petType;
    private final String roomType;

    public PetRoomOutputData(int finalFood, int finalWater, int finalHappiness, int finalCleanliness, int score, String petType, String roomType) {
        this.finalFood = finalFood;
        this.finalWater = finalWater;
        this.finalHappiness = finalHappiness;
        this.finalCleanliness = finalCleanliness;
        this.score = score;
        this.petType = petType;
        this.roomType = roomType;
    }


    public int getScore() {
        return score;
    }
    public void setScore(int score) {}
    public int getFinalFood() {return finalFood;}
    public int getFinalWater() {return finalWater;}
    public int getFinalHappiness() {return finalHappiness;}
    public int getFinalCleanliness() {return finalCleanliness;}

    public String getPetType() {return petType;}
    public String getRoomType() {return roomType;}
}