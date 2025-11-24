package use_case.save_game.PetRoom;

public class PetRoomOutputData {
    private final String petName;
    private final int finalFood;
    private final int finalWater;
    private final int finalJoy;
    private final int finalClean;

    public PetRoomOutputData(String petName, int finalFood, int finalWater, int finalJoy, int finalClean) {
        this.petName = petName;
        this.finalFood = finalFood;
        this.finalWater = finalWater;
        this.finalJoy = finalJoy;
        this.finalClean = finalClean;
    }
    public String getPetName() {
        return petName;
    }

    public int getFinalFood() {
        return finalFood;
    }

    public int getFinalWater() {
        return finalWater;
    }

    public int getFinalJoy() {
        return finalJoy;
    }

    public int getFinalClean() {
        return finalClean;
    }
}
