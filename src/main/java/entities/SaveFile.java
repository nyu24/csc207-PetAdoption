package entities;

/**
 * This class holds information required for a save file for the game.
 */

public class SaveFile {
    private final int timeLeft;
    private final String name;
    private final String spritePath;
    private final int hunger;
    private final int thirst;
    private final int cleanliness;
    private final int happiness;

    public SaveFile(int timeLeft, String name, String spritePath, int hunger, int thirst, int cleanliness,
                    int happiness) {
        this.timeLeft = timeLeft;
        this.name = name;
        this.spritePath = spritePath;
        this.hunger = hunger;
        this.thirst = thirst;
        this.cleanliness = cleanliness;
        this.happiness = happiness;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public String getName() {
        return name;
    }

    public String getSpritePath() {
        return spritePath;
    }

    public int getHunger() {
        return hunger;
    }

    public int getThirst() {
        return thirst;
    }

    public int getCleanliness() {
        return cleanliness;
    }

    public int getHappiness() { return happiness; }
}
