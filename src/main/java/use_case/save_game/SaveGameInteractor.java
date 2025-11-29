package use_case.save_game;

import entities.Pet;
import entities.SaveFile;
import entities.SaveFileFactory;

public class SaveGameInteractor implements SaveGameInputBoundary {
    private final SaveGameDataAccessInterface saveGameDataAccessObject;
    private final SaveGameOutputBoundary saveGamePresenter;
    private final SaveFileFactory saveFileFactory;

    public SaveGameInteractor(SaveGameDataAccessInterface saveGameDataAccessObject,
                              SaveGameOutputBoundary saveGameOutputBoundary,
                              SaveFileFactory saveFileFactory) {
        this.saveGameDataAccessObject = saveGameDataAccessObject;
        this.saveGamePresenter = saveGameOutputBoundary;
        this.saveFileFactory = saveFileFactory;
    }

    @Override
    public void execute() {
        final SaveGameOutputData saveGameOutputData = new SaveGameOutputData(true);
        saveGamePresenter.prepareCancelView(saveGameOutputData);
    }

    @Override
    public void execute(SaveGameInputData saveGameInputData) {
        final Pet currPet = saveGameInputData.getcurrentPet();
        final SaveFile savefile = saveFileFactory.create(saveGameInputData.getTimeLeft(),
                currPet.getName(),
                currPet.getPetSpritePath(),
                currPet.getHunger(),
                currPet.getThirst(),
                currPet.getCleanliness(),
                currPet.getHappiness());

        saveGameDataAccessObject.save(savefile);

        final SaveGameOutputData saveGameOutputData = new SaveGameOutputData(true);
        saveGamePresenter.prepareSuccessView(saveGameOutputData);
    }

    @Override
    public void switchToPetRoomView() { saveGamePresenter.switchToPetRoomView(); }
}
