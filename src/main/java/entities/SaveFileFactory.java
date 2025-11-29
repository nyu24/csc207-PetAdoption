package entities;

public class SaveFileFactory {

    public SaveFile create(int timeLeft,
                           String name,
                           String spritePath,
                           int hunger,
                           int thirst,
                           int cleanliness,
                           int happiness) {
        return new SaveFile(timeLeft, name, spritePath, hunger, thirst, cleanliness, happiness);
    }
}
