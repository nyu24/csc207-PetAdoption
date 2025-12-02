package use_case.save_game;

import entities.SaveFile;

/**
 * DAO interface for the SaveGame Use Case.
 */

public interface SaveGameDataAccessInterface {
    /**
     * Save the save file in DAO.
     * @param saveFile a save file from the game to be saved.
     */
    void save(SaveFile saveFile);

    /**
     * Load the save file in DAO.
     * @return a save file loaded from the DAO.
     */
    SaveFile load();

    /**
     * Checks whether save data exist in DAO.
     * @return whether save data exist in DAO.
     */
    boolean saveDataExists();
}