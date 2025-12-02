package use_case.load_game;

import data_access.InMemorySaveDataAccessObject;
import entities.ApiPet;
import entities.Pet;
import entities.SaveFile;
import entities.SaveFileFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoadGameInteractorTest {

    @Test
    void successTest() {
        LoadGameDataAccessInterface saveFileRepo = new InMemorySaveDataAccessObject();

        SaveFileFactory factory = new SaveFileFactory();
        Pet testPet = new Pet(100, 100, 100, 100);
        testPet.setName("testPet");
        ApiPet testApiPet = new ApiPet();
        testApiPet.setName("testPet");
        testApiPet.setUrl("petfinder.com");
        testPet.setApiPet(testApiPet);
        SaveFile saveFile = factory.create(40, 10, testPet, testApiPet);
        saveFileRepo.save(saveFile);

        LoadGameOutputBoundary successPresenter = new LoadGameOutputBoundary() {
            @Override
            public void prepareSuccessLoadView(LoadGameOutputData loadGameOutputData) {
                assertEquals(saveFile.getTimeLeft(), loadGameOutputData.getSaveFile().getTimeLeft());
                assertEquals(saveFile.getCurrScore(), loadGameOutputData.getSaveFile().getCurrScore());
                assertEquals(testPet.getName(), saveFileRepo.load().getCurrPet().getName());
                assertEquals(testApiPet.getName(), saveFileRepo.load().getApiPet().getName());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToTitleView() {
                fail("This view switch is unexpected.");
            }
        };

        LoadGameInputBoundary interactor = new LoadGameInteractor(saveFileRepo, successPresenter);
        interactor.execute();

    }

    @Test
    void noSaveDataTest() {
        LoadGameDataAccessInterface saveFileRepo = new InMemorySaveDataAccessObject();

        saveFileRepo.save(null);

        LoadGameOutputBoundary noSaveDataPresenter = new LoadGameOutputBoundary() {
            @Override
            public void prepareSuccessLoadView(LoadGameOutputData loadGameOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("There is no save data to be loaded.", errorMessage);
            }

            @Override
            public void switchToTitleView() {
                fail("This view switch is unexpected.");
            }
        };

        LoadGameInputBoundary interactor = new LoadGameInteractor(saveFileRepo, noSaveDataPresenter);
        interactor.execute();

    }

    @Test
    void switchToTitleTest() {
        LoadGameDataAccessInterface saveFileRepo = new InMemorySaveDataAccessObject();

        saveFileRepo.save(null);

        LoadGameOutputBoundary noSaveDataPresenter = new LoadGameOutputBoundary() {
            @Override
            public void prepareSuccessLoadView(LoadGameOutputData loadGameOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToTitleView() {
                // This is expected.
            }
        };

        LoadGameInputBoundary interactor = new LoadGameInteractor(saveFileRepo, noSaveDataPresenter);
        interactor.switchToTitleView();

    }

}