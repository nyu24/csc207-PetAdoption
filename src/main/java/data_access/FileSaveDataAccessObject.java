package data_access;

import entities.Pet;
import entities.User;
import use_case.save_game.SaveGameUserDataAccessInterface;

import java.io.*;

public class FileSaveDataAccessObject implements SaveGameUserDataAccessInterface {
    private final File file;

    public FileSaveDataAccessObject(String path) {
        file = new File(path);
    }
}
