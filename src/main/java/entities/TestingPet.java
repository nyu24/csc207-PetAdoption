package entities;

public class TestingPet extends Pet {
    public TestingPet(double maxHunger, double maxThirst, double maxCleanliness, double maxHappiness) {
        super(maxHunger, maxThirst, maxCleanliness, maxHappiness);
    }

    @Override
    public void loadImage(String imagePath) {
    }
    public void drawImage() {
    }
}
