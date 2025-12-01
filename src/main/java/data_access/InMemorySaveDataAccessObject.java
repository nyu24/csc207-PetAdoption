package data_access;

import entities.SaveFile;
import use_case.load_game.LoadGameDataAccessInterface;
import use_case.save_game.SaveGameDataAccessInterface;

/**
 * In-memory implementation of the ButtonsDataAcessObject for storing save game data. This implementation does
 * not persist data between runs of the program and is solely used for testing purpose.
 */

public class InMemorySaveDataAccessObject implements SaveGameDataAccessInterface, LoadGameDataAccessInterface {
    private SaveFile saveFile;

    public void save(SaveFile saveFile) {
        this.saveFile = saveFile;
    }

    public SaveFile load() {
        return this.saveFile;
    }

    @Override
    public boolean saveDataExists() {
        return saveFile != null;
    }
}
