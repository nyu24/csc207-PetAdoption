package interface_adapter.save_game;

import entities.APIPet;
import entities.Pet;

public class SaveGameState {
    private int timeLeft;
    private int currScore;
    private Pet currentPet;
    private APIPet apiPet;
    private String petError;

    // placeholder until can get actual state from pet room view.
    public SaveGameState() {
        currentPet = new Pet(100, 100, 100, 100);
        currentPet.setPetSpritePath("");
        currentPet.setName("dog");
        currentPet.setPetSpritePath("src/main/java/resources/dog_room_basic.jpg");
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public int getCurrScore() { return currScore; }

    public Pet getCurrentPet() {
        return currentPet;
    }

    public APIPet getapiPet() { return apiPet; }

    public String getPetError() { return petError; }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public void setCurrScore(int currScore) { this.currScore = currScore; }

    public void setCurrentPet(Pet currentPet) {
        this.currentPet = currentPet;
    }

    public void setapiPet(APIPet apiPet) { this.apiPet = apiPet; }

    public void setPetError(String petError) { this.petError = petError; }
}
