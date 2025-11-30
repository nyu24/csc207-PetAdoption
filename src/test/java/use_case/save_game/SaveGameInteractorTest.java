package use_case.save_game;

import data_access.FileSaveDataAccessObject;
import data_access.InMemorySaveDataAccessObject;
import entities.APIPet;
import entities.Pet;
import entities.SaveFile;
import entities.SaveFileFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaveGameInteractorTest {

    @Test
    void successTest() {
        Pet testPet = new Pet(100, 100, 100, 100);
        testPet.setName("testName");
        testPet.setApiPet(new APIPet());
        testPet.getApiPet().setName("testName");
        SaveGameInputData inputData = new SaveGameInputData(60, 60, testPet);
        SaveGameDataAccessInterface saveFileRepo = new InMemorySaveDataAccessObject();

        SaveGameOutputBoundary successPresenter = new SaveGameOutputBoundary() {
            @Override
            public void prepareSuccessView(SaveGameOutputData saveFile) {
                assertEquals("testName", saveFileRepo.load().getCurrPet().getName());
                assertTrue(saveFile.isGameSaved());
                assertTrue(saveFileRepo.saveDataExists());
            }

            @Override
            public void switchToPetRoomView() {
                fail("This view switch is unexpected.");
            }
        };

        SaveGameInputBoundary interactor = new SaveGameInteractor(saveFileRepo, successPresenter, new SaveFileFactory());
        interactor.execute(inputData);
    }

    @Test
    void switchToPetRoomViewTest() {
        SaveGameDataAccessInterface saveFileRepo = new InMemorySaveDataAccessObject();

        SaveGameOutputBoundary successPresenter = new SaveGameOutputBoundary() {
            @Override
            public void prepareSuccessView(SaveGameOutputData saveFile) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void switchToPetRoomView() {
                // This is expected.
            }
        };

        SaveGameInputBoundary interactor = new SaveGameInteractor(saveFileRepo, successPresenter, new SaveFileFactory());
        interactor.switchToPetRoomView();
    }

    @Test
    void successFileTest() {
        Pet testPet = new Pet(100, 100, 100, 100);
        testPet.setName("testName");
        testPet.setApiPet(new APIPet());
        testPet.getApiPet().setName("testName");
        SaveGameInputData inputData = new SaveGameInputData(30, 8, testPet);
        SaveGameDataAccessInterface saveFileRepo = new
                FileSaveDataAccessObject("src/test/java/use_case/save_game/savedata.json");

        SaveGameOutputBoundary successFilePresenter = new SaveGameOutputBoundary() {
            @Override
            public void prepareSuccessView(SaveGameOutputData saveFile) {
                assertEquals("testName", saveFileRepo.load().getCurrPet().getName());
                assertTrue(saveFile.isGameSaved());
                assertTrue(saveFileRepo.saveDataExists());
            }

            @Override
            public void switchToPetRoomView() {
                fail("This view switch is unexpected.");
            }
        };

        SaveGameInputBoundary interactor = new SaveGameInteractor(saveFileRepo, successFilePresenter, new SaveFileFactory());
        interactor.execute(inputData);
    }

}
