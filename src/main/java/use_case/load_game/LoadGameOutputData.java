package use_case.load_game;

public class LoadGameOutputData {

    private final boolean saveDataExist;

    public LoadGameOutputData(boolean savaDataExist) {
        this.saveDataExist = savaDataExist;
    }

    public boolean isSaveDataExist() {
        return saveDataExist;
    }
}
