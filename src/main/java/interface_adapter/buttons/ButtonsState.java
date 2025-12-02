package interface_adapter.buttons;

public class ButtonsState {
    private double hunger;
    private double thirst;
    private double cleanliness;
    private double happiness;



    public double getHunger() { return hunger; }
    public void setHunger(double hunger) {
        this.hunger  = hunger;
    }
    public double getThirst() { return thirst; }
    public void setThirst(double thirst) {
        this.thirst = thirst;
    }

    public void setCleanliness(double cleanliness) {
        this.cleanliness = cleanliness;
    }
    public double getCleanliness() {return cleanliness ;}

    public double getHappiness() {return happiness ;}
    public void setHappiness(double happiness) {
        this.happiness = happiness;
    }}
