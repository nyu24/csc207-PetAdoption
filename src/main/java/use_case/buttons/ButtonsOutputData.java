package use_case.buttons;

/**
 * Output Data for the Buttons use case.
 */

public class ButtonsOutputData {
    private double hunger;
    private double thirst;
    private double cleanliness;
    private double happiness;

    public ButtonsOutputData(double Hunger, double Thirst, double Cleanliness, double Happiness) {
        this.hunger = Hunger;
        this.cleanliness = Cleanliness;
        this.thirst = Thirst;
        this.happiness = Happiness;

    }

    public double getHunger() {
        return hunger;
    }

    public double getThirst() {
        return thirst;
    }

    public double getCleanliness() {
        return cleanliness;
    }

    public double getHapiness() {
        return happiness;
    }
}
