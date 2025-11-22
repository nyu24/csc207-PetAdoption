package use_case.buttons;
import entities.Pet;
/**
 * The input Data for the buttons Use case
 */

public class buttons_inputData {

    private double hunger; //add final perchance??
    private double thirst;
    private double cleanliness;
    private double hapiness;
    private Pet pet;

    public buttons_inputData(Pet pet) {
        this.pet = pet; //TODO: ASK IF THIS CAN BE HERE
        this.hunger = pet.getHunger();
        this.thirst = pet.getThirst();
        this.cleanliness = pet.getCleanliness();
        this.hapiness = pet.getHappiness();
    }
    double getHunger() { return hunger; }

    double getThirst() { return thirst; }

    double getCleanliness() {return cleanliness ;}

    double getHapiness() {return hapiness ;}

    Pet getpet() {return pet; }
}
