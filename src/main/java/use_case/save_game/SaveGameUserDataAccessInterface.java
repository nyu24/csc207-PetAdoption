package use_case.save_game;

import entities.SaveFile;

/**
 * DAO interface for the SaveGame Use Case.
 */

public interface SaveGameUserDataAccessInterface {
    boolean saveDataExists();

    void save(SaveFile saveFile);

}
