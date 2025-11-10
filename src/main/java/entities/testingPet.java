package entities;

public class testingPet extends Pet {
    public testingPet(double maxHunger, double maxThirst, double maxCleanliness, double maxHappiness) {
        super(maxHunger, maxThirst, maxCleanliness, maxHappiness);
    }

    @Override
    public void loadImage(String imagePath) {
        return;
    }
    public void drawImage() {
        return;
    }
}
