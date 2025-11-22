package use_case.buttons;

import entities.Pet;

/**
 * Output Data for the Buttons use case
 */

public class buttons_OutputData {
    private double hunger;
    private double thirst;
    private double cleanliness;
    private double hapiness;
    private Pet pet;

    public buttons_OutputData(Pet pet) {
        this.pet = pet;
        this.cleanliness = pet.getCleanliness();
        this.hapiness = pet.getHappiness();
        this.hunger = pet.getHunger();
        this.thirst = pet.getThirst();
    }
    public double getHunger() { return hunger; }

    public double getThirst() { return thirst; }

    public double getCleanliness() {return cleanliness ;}

    public double getHapiness() {return hapiness ;}

    public Pet getpet() {return pet;}

}
