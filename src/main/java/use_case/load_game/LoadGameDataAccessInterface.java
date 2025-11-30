package use_case.load_game;

import entities.SaveFile;

public interface LoadGameDataAccessInterface {
    boolean saveDataExists();

    SaveFile load();

    void save(SaveFile saveFile);
}
