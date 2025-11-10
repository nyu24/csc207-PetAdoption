package entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// pet class

public abstract class Pet {

    // name and image
    private String name;
    private BufferedImage petSprite;

    // statistics
    private float hunger; // fullness
    private float thirst;
    private float cleanliness;
    private float happiness;

    private List<Float> petStats;

    public Pet(float hunger, float thirst, float cleanliness, float happiness) {
        petStats = new ArrayList<>();
        petStats.add(hunger);
        petStats.add(thirst);
        petStats.add(cleanliness);
        petStats.add(happiness);
    }

    public List<Float> getPetStats() {
        return petStats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getPetSprite() {
        return petSprite;
    }

    public void setPetSprite(BufferedImage petSprite) {
        this.petSprite = petSprite;
    }

    public float getHunger() {
        return hunger;
    }

    public void setHunger(float hunger) {
        this.hunger = hunger;
    }

    public float getThirst() {
        return thirst;
    }

    public void setThirst(float thirst) {
        this.thirst = thirst;
    }

    public float getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(float cleanliness) {
        this.cleanliness = cleanliness;
    }

    public float getHappiness() {
        return happiness;
    }

    public void setHappiness(float happiness) {
        this.happiness = happiness;
    }

    public abstract void loadImage(String imagePath);

    public abstract void drawImage();
}
