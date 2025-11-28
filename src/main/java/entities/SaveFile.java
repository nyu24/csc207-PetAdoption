package entities;

/**
 * This class holds information required for a save file for the game.
 */

public class SaveFile {
    private final double timeLeft;
    private final String name;
    private final String spritePath;
    private final double hunger;
    private final double thirst;
    private final double cleanliness;
    private final double happiness;

    public SaveFile(double timeLeft, String name, String spritePath, double hunger, double thirst, double cleanliness,
                    double happiness) {
        this.timeLeft = timeLeft;
        this.name = name;
        this.spritePath = spritePath;
        this.hunger = hunger;
        this.thirst = thirst;
        this.cleanliness = cleanliness;
        this.happiness = happiness;
    }

    public double getTimeLeft() {
        return timeLeft;
    }

    public String getName() {
        return name;
    }

    public String getSpritePath() {
        return spritePath;
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

    public double getHappiness() { return happiness; }
}
