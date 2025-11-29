package use_case.load_game;

import entities.SaveFile;

public interface LoadGameDataAccessInterface {
    boolean saveDataExists();

    void load(SaveFile saveFile);
}
