package main.classes.use_case.buttons;

import main.classes.entities.*;

/**
 * Output Data for the Buttons use case
 */

public class buttons_OutputData {
    private double hunger;
    private double thirst;
    private double cleanliness;
    private double happiness;


    public buttons_OutputData(double Hunger, double Thirst, double Cleanliness, double Hapiness) {
        this.hunger = Hunger;
        this.cleanliness = Cleanliness;
        this.thirst = Thirst;
        this.happiness = Hapiness;

    }
    public double getHunger() { return hunger; }

    public double getThirst() { return thirst; }

    public double getCleanliness() {return cleanliness ;}

    public double getHapiness() {return happiness ;}


}
