
package use_case.PetRoom;

import entities.Pet;

public class PetRoomOutputData {
    private final int finalFood;
    private final int finalWater;
    private final int finalHappiness;
    private final int finalCleanliness;
    private int score;
    private final String petType;
    private final String roomType;
    private final Pet currPet;

    public PetRoomOutputData(int finalFood, int finalWater, int finalCleanliness, int finalHappiness,
                             int score, String petType, String roomType, Pet currPet) {
        this.finalFood = finalFood;
        this.finalWater = finalWater;
        this.finalHappiness = finalHappiness;
        this.finalCleanliness = finalCleanliness;
        this.score = score;
        this.petType = petType;
        this.roomType = roomType;
        this.currPet = currPet;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFinalFood() {
        return finalFood;
    }

    public int getFinalWater() {
        return finalWater;
    }

    public int getFinalHappiness() {
        return finalHappiness;
    }

    public int getFinalCleanliness() {
        return finalCleanliness;
    }

    public String getPetType() {
        return petType;
    }

    public String getRoomType() {
        return roomType;
    }

    public Pet getCurrPet() {
        return currPet;
    }
}