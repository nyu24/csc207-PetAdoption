package interface_adapter.save_game;

import entities.Pet;

public class SaveGameState {
    private int timeLeft = 0;
    private Pet currentPet = null;
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

    public Pet getCurrentPet() {
        return currentPet;
    }

    public String getPetError() { return petError; }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public void setCurrentPet(Pet currentPet) {
        this.currentPet = currentPet;
    }

    public void setPetError(String petError) { this.petError = petError; }
}
