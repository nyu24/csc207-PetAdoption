package use_case.save_game;

public class SaveGameOutputData {

    private final boolean gameSaved;

    public SaveGameOutputData(boolean saveDataExist) {
        this.gameSaved = saveDataExist;
    }

    public boolean isGameSaved() {
        return gameSaved;
    }
}
