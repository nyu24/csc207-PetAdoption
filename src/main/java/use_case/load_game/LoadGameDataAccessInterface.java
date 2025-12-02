package use_case.load_game;

import entities.SaveFile;

public interface LoadGameDataAccessInterface {
    /**
     * Checks whether save data exist in ButtonsDataAccessObject.
     * @return whether save data exist in ButtonsDataAccessObject.
     */
    boolean saveDataExists();

    /**
     * Load the save file in ButtonsDataAccessObject.
     * @return a save file loaded from the ButtonsDataAccessObject.
     */
    SaveFile load();

    /**
     * Save the save file in ButtonsDataAccessObject.
     * @param saveFile a save file from the game to be saved.
     */
    void save(SaveFile saveFile);
}