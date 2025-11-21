package use_case.save_game;

public class SaveGameOutputData {

    private final boolean saveDataexist;

    public SaveGameOutputData(boolean saveDataexist) {
        this.saveDataexist = saveDataexist;
    }

    public boolean isSaveDataexist() {
        return saveDataexist;
    }
}
