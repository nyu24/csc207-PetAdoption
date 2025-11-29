package use_case.save_game;

public class SaveGameOutputData {

    private final boolean saveDataExist;

    public SaveGameOutputData(boolean saveDataExist) {
        this.saveDataExist = saveDataExist;
    }

    public boolean isSaveDataExist() {
        return saveDataExist;
    }
}
