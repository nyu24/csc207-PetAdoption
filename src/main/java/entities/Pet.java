package entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// pet class

public class Pet {

    // name and image
    private String name;
    private BufferedImage petSprite;

    // statistics
    private double hunger; // fullness
    private double thirst;
    private double cleanliness;
    private double happiness;
    
    // maxima
    private double maxHunger; // fullness
    private double maxThirst;
    private double maxCleanliness;
    private double maxHappiness;
    
    // set maximum values and set current values to maximum
    public Pet(double maxHunger, double maxThirst, double maxCleanliness, double maxHappiness){
        this.maxHunger = maxHunger;
        this.maxThirst = maxThirst;
        this.maxCleanliness = maxCleanliness;
        this.maxHappiness = maxHappiness;
        this.hunger = maxHunger;
        this.thirst = maxThirst;
        this.cleanliness = maxCleanliness;
        this.happiness = maxHappiness;
    }
    
    public double getMaxHunger() {
        return maxHunger;
    }

    public void setMaxHunger(double maxHunger) {
        this.maxHunger = maxHunger;
    }

    public double getMaxThirst() {
        return maxThirst;
    }

    public void setMaxThirst(double maxThirst) {
        this.maxThirst = maxThirst;
    }

    public double getMaxCleanliness() {
        return maxCleanliness;
    }

    public void setMaxCleanliness(double maxCleanliness) {
        this.maxCleanliness = maxCleanliness;
    }

    public double getMaxHappiness() {
        return maxHappiness;
    }

    public void setMaxHappiness(double maxHappiness) {
        this.maxHappiness = maxHappiness;
    }

    //for later use
    private APIPet apiPet;

    private Map<String, Float> petStats;

    public Pet(float hunger, float thirst, float cleanliness, float happiness) {
        petStats = new HashMap<String, Float>();
        petStats.put("Hunger", hunger);
        petStats.put("Thirst", thirst);
        petStats.put("Cleanliness", cleanliness);
        petStats.put("Happiness", happiness);
    }

    public Map<String, Float> getPetStats() {
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

    public double getHunger() {
        return hunger;
    }

    public void setHunger(double hunger) {
        this.hunger = hunger;
    }

    public double getThirst() {
        return thirst;
    }

    public void setThirst(double thirst) {
        this.thirst = thirst;
    }

    public double getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(double cleanliness) {
        this.cleanliness = cleanliness;
    }

    public double getHappiness() {
        return happiness;
    }

    public void setHappiness(double happiness) {
        this.happiness = happiness;
    }

    //TODO: remember to actually store this when creating Pet entity
    public APIPet getApiPet() {
        return apiPet;
    }
    public void setApiPet(APIPet apiPet) {
        this.apiPet = apiPet;
    }
}
