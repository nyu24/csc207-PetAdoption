package use_case.save_game;

public class SaveGameOutputData {

    private final boolean gameSaved;

    public SaveGameOutputData(boolean saveDataExist) {
        this.gameSaved = saveDataExist;
    }

    // Unused currently, but left here as it is useful in extending the program in the future.
    public boolean isGameSaved() {
        return gameSaved;
    }
}
