package entities;

public class SaveFileFactory {

    public SaveFile create(double timeLeft,
                           String name,
                           String spritePath,
                           double hunger,
                           double thirst,
                           double cleanliness,
                           double happiness) {
        return new SaveFile(timeLeft, name, spritePath, hunger, thirst, cleanliness, happiness);
    }
}
