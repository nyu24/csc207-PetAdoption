package use_case.save_game;

import entities.SaveFile;

/**
 * ButtonsDataAccessObject interface for the SaveGame Use Case.
 */

public interface SaveGameDataAccessInterface {
    /**
     * Save the save file in ButtonsDataAccessObject.
     * @param saveFile a save file from the game to be saved.
     */
    void save(SaveFile saveFile);

    /**
     * Load the save file in ButtonsDataAccessObject.
     * @return a save file loaded from the ButtonsDataAccessObject.
     */
    SaveFile load();

    /**
     * Checks whether save data exist in ButtonsDataAccessObject.
     * @return whether save data exist in ButtonsDataAccessObject.
     */
    boolean saveDataExists();
}