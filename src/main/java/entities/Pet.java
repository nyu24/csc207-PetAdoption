package entities;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

// pet class

public class Pet {

    // name and image
    private String name;
    private BufferedImage petSprite;
    private String petSpritePath;

    // statistics
    private int hunger;
    private int thirst;
    private int cleanliness;
    private int happiness;
    
    // maxima
    private int maxHunger;
    private int maxThirst;
    private int maxCleanliness;
    private int maxHappiness;

    // for easier access
    private ApiPet apiPet;
    
    // set maximum values and set current values to maximum
    public Pet(int maxHunger, int maxThirst, int maxCleanliness, int maxHappiness) {
        this.maxHunger = maxHunger;
        this.maxThirst = maxThirst;
        this.maxCleanliness = maxCleanliness;
        this.maxHappiness = maxHappiness;
        this.hunger = maxHunger;
        this.thirst = maxThirst;
        this.cleanliness = maxCleanliness;
        this.happiness = maxHappiness;
    }

    public int getMaxHunger() {
        return maxHunger;
    }

    public void setMaxHunger(int maxHunger) {
        this.maxHunger = maxHunger;
    }

    public int getMaxThirst() {
        return maxThirst;
    }

    public void setMaxThirst(int maxThirst) {
        this.maxThirst = maxThirst;
    }

    public int getMaxCleanliness() {
        return maxCleanliness;
    }

    public void setMaxCleanliness(int maxCleanliness) {
        this.maxCleanliness = maxCleanliness;
    }

    public int getMaxHappiness() {
        return maxHappiness;
    }

    public void setMaxHappiness(int maxHappiness) {
        this.maxHappiness = maxHappiness;
    }

    /**
     * Gets the pet stats.
     * @return a map containing the pet stats.
     */

    public Map<String, Integer> getPetStats() {
        final Map<String, Integer> petStats = new HashMap<>();
        petStats.put("Hunger", hunger);
        petStats.put("Thirst", thirst);
        petStats.put("Cleanliness", cleanliness);
        petStats.put("Happiness", happiness);
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

    public String getPetSpritePath() {
        return petSpritePath;
    }

    public void setPetSpritePath(String petSpritePath) {
        this.petSpritePath = petSpritePath;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getThirst() {
        return thirst;
    }

    public void setThirst(int thirst) {
        this.thirst = thirst;
    }

    public int getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(int cleanliness) {
        this.cleanliness = cleanliness;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public ApiPet getApiPet() {
        return apiPet;
    }

    public void setApiPet(ApiPet apiPet) {
        this.apiPet = apiPet;
    }

}
