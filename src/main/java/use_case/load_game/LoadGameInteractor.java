package use_case.load_game;

import entities.SaveFile;
import use_case.save_game.SaveGameOutputData;

public class LoadGameInteractor implements LoadGameInputBoundary {
    private final LoadGameDataAccessInterface loadGameDataAccessObject;
    private final LoadGameOutputBoundary loadGamePresenter;

    public LoadGameInteractor(LoadGameDataAccessInterface loadGameDataAccessObject,
                              LoadGameOutputBoundary loadGamePresenter) {
        this.loadGameDataAccessObject = loadGameDataAccessObject;
        this.loadGamePresenter = loadGamePresenter;
    }

    @Override
    public void execute() {
        if (loadGameDataAccessObject.saveDataExists()) {
            final SaveFile savefile = loadGameDataAccessObject.load();
            final LoadGameOutputData loadGameOutputData = new LoadGameOutputData(true, savefile);
            loadGamePresenter.prepareSuccessLoadView(loadGameOutputData);
        } else {
            loadGamePresenter.prepareFailView("There is no save data to be loaded.");
        }
    }

    public void switchToPetRoomView() { loadGamePresenter.switchToPetRoomView(); }

    public void switchToTitleView() { loadGamePresenter.switchToPetRoomView();  }
}
