package use_case.load_game;

import entities.SaveFile;

public interface LoadGameDataAccessInterface {
    /**
     * Checks whether save data exist in DAO.
     * @return whether save data exist in DAO.
     */
    boolean saveDataExists();

    /**
     * Load the save file in DAO.
     * @return a save file loaded from the DAO.
     */
    SaveFile load();

    /**
     * Save the save file in DAO.
     * @param saveFile a save file from the game to be saved.
     */
    void save(SaveFile saveFile);
}
