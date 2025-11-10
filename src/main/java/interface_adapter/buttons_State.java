package interface_adapter;
import entities.Pet;

/**
 * The State Information representing the buttons.
 */


public class buttons_State {
    private Pet pet;
    private double hunger; //TODO: equate to georgias value
    private double thirst;
    private double hapiness;
    private double cleanliness;

    public double getHunger(Pet p) {
      pet = p ;
      hunger = p.getHunger();
      return hunger;
    }

    public double getThirst(Pet p) {
        pet = p;
        thirst = p.getThirst();
        return thirst;
    }

    public double getHapiness(Pet p) {
        pet = p;
        hapiness = p.getHappiness();
        return hapiness;
    }

    public double getCleanliness(Pet p) {
        pet = p;
        cleanliness = p.getCleanliness();
        return cleanliness;
    } //TODO: Do i even need these methods? genuinely idk

    public Pet getPet() {
        return pet;
    }


    public void setHunger(double hunger) {
        this.hunger = hunger;
    }

    public void setCleanliness(double cleanliness) {
        this.cleanliness = cleanliness;
    }

    public void setThirst(double thirst) {
        this.thirst = thirst;
    }

    public void setHapiness(double Hapiness) {
        this.hapiness = Hapiness;
    }
}
