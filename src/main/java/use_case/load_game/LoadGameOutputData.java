package use_case.load_game;

import entities.SaveFile;

public class LoadGameOutputData {

    private final SaveFile saveFile;

    public LoadGameOutputData(SaveFile saveFile) {
        this.saveFile = saveFile;
    }

    public SaveFile getSaveFile() {
        return saveFile;
    }
}
