package interface_adapter.save_game;

import entities.ApiPet;
import entities.Pet;

public class SaveGameState {
    private int timeLeft;
    private int currScore;
    private Pet currentPet;
    private ApiPet apiPet;
    private String petError;

    public int getTimeLeft() {
        return timeLeft;
    }

    public int getCurrScore() { return currScore; }

    public Pet getCurrPet() {
        return currentPet;
    }

    public ApiPet getapiPet() { return apiPet; }

    public String getPetError() { return petError; }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public void setCurrScore(int currScore) { this.currScore = currScore; }

    public void setCurrPet(Pet currentPet) {
        this.currentPet = currentPet;
    }

    public void setAPIPet(ApiPet apiPet) { this.apiPet = apiPet; }

    public void setPetError(String petError) { this.petError = petError; }
}
