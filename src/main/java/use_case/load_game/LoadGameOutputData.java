package use_case.load_game;

import entities.SaveFile;

public class LoadGameOutputData {

    private final boolean saveDataExist;
    private final SaveFile saveFile;

    public LoadGameOutputData(boolean savaDataExist, SaveFile saveFile) {
        this.saveDataExist = savaDataExist;
        this.saveFile = saveFile;
    }

    public boolean isSaveDataExist() {
        return saveDataExist;
    }
}
