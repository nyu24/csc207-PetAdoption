package use_case.load_game;

public class LoadGameInteractor implements LoadGameInputBoundary {
    private final LoadGameDataAccessInterface loadGameDataAccessObject;
    private final LoadGameOutputBoundary loadGamePresenter;

    public LoadGameInteractor(LoadGameDataAccessInterface loadGameDataAccessObject,
                              LoadGameOutputBoundary loadGamePresenter) {
        this.loadGameDataAccessObject = loadGameDataAccessObject;
        this.loadGamePresenter = loadGamePresenter;
    }

    @Override
    public void execute(LoadGameInputData loadGameInputData) {
        if (loadGameDataAccessObject.saveDataExists()) {
            loadGamePresenter.prepareLoadView();
        } else {
            loadGamePresenter.prepareFailView();
        }
    }

    public void switchToLoadGameView() { loadGamePresenter.switchToLoadGameView(); }

    public void switchToFailView() { loadGamePresenter.switchToFailView(); }
}
