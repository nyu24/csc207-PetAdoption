package interface_adapter.save_game;

import entities.Pet;

public class SaveGameState {
    private Double timeLeft = 0.0;
    private Pet currentPet = null;
    private String petError;

    public Double getTimeLeft() {
        return timeLeft;
    }

    public Pet getCurrentPet() {
        return currentPet;
    }

    public String getPetError() { return petError; }

    public void setTimeLeft(double timeLeft) {
        this.timeLeft = timeLeft;
    }

    public void setCurrentPet(Pet currentPet) {
        this.currentPet = currentPet;
    }

    public void setPetError(String petError) { this.petError = petError; }
}
