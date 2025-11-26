package interface_adapter.buttons;

public class buttons_State {
    private double hunger;
    private double thirst;
    private double cleanliness;
    private double happiness;



    public double getHunger() { return hunger; }
    public void setHunger(double Hunger) {
        this.hunger  = Hunger;
    }
    public double getThirst() { return thirst; }
    public void setThirst(double thirst) {
        this.thirst = thirst;
    }

    public void setCleanliness(double cleanliness) {
        this.cleanliness = cleanliness;
    }
    public double getCleanliness() {return cleanliness ;}

    public double getHapiness() {return happiness ;}
    public void setHappiness(double Happiness) {
        this.happiness = Happiness;
    }}
