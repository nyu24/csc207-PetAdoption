package use_case.PetRoom;

public class PetRoomOutputData {
    private final int finalFood;
    private final int finalWater;
    private final int finalHappiness;
    private final int finalCleanliness;
    private final int score;

    public PetRoomOutputData(int finalFood, int finalWater, int finalHappiness, int finalCleanliness, int score) {
        this.finalFood = finalFood;
        this.finalWater = finalWater;
        this.finalHappiness = finalHappiness;
        this.finalCleanliness = finalCleanliness;
        this.score = score;
    }


    public int getScore() {
        return score;
    }
    public void setScore(int score) {}
    public int getFinalFood() {return finalFood;}
    public int getFinalWater() {return finalWater;}
    public int getFinalHappiness() {return finalHappiness;}
    public int getFinalCleanliness() {return finalCleanliness;}
}