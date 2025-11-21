package use_case.save_game;

public class SaveGameInteractor implements SaveGameInputBoundary {
    private final SaveGameDataAccessInterface saveGameDataAccessObject;
    private final SaveGameOutputBoundary saveGamePresenter;

    public SaveGameInteractor(SaveGameDataAccessInterface saveGameDataAccessObject,
                              SaveGameOutputBoundary saveGameOutputBoundary) {
        this.saveGameDataAccessObject = saveGameDataAccessObject;
        this.saveGamePresenter = saveGameOutputBoundary;
    }

    @Override
    public void execute(SaveGameInputData saveGameInputData) {
        if (saveGameDataAccessObject.saveDataExists()) {
            saveGamePresenter.prepareWarningView();
        } else {
            saveGamePresenter.prepareSuccessView();
        }
    }

    public void switchToSaveGameView() { saveGamePresenter.switchToSaveGameView(); }
}
