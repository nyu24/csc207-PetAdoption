package interface_adapter.save_game;

import entities.APIPet;
import entities.Pet;

public class SaveGameState {
    private int timeLeft;
    private int currScore;
    private Pet currentPet;
    private APIPet apiPet;
    private String petError;

    public int getTimeLeft() {
        return timeLeft;
    }

    public int getCurrScore() { return currScore; }

    public Pet getCurrPet() {
        return currentPet;
    }

    public APIPet getapiPet() { return apiPet; }

    public String getPetError() { return petError; }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public void setCurrScore(int currScore) { this.currScore = currScore; }

    public void setCurrPet(Pet currentPet) {
        this.currentPet = currentPet;
    }

    public void setAPIPet(APIPet apiPet) { this.apiPet = apiPet; }

    public void setPetError(String petError) { this.petError = petError; }
}
