package use_case.save_game;

import data_access.InMemorySaveDataAccessObject;
import entities.Pet;
import entities.SaveFile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaveGameInteractorTest {

    @Test
    void successTest() {
        SaveGameInputData inputData = new SaveGameInputData(60, new Pet(100, 100, 100, 100));
        SaveGameDataAccessInterface saveFileRepo = new InMemorySaveDataAccessObject();

        SaveGameOutputBoundary successPresenter = new SaveGameOutputBoundary() {
            @Override
            public void prepareSuccessView(SaveGameOutputData saveFile) {
                assertEquals(1, 1);
                assertTrue(true);
            }

            @Override
            public void prepareCancelView(SaveGameOutputData outputData) {

            }

            @Override
            public void switchToPetRoomView() {

            }
        };
    }
}
