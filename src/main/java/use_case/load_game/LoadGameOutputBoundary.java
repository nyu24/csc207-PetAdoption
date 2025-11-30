package use_case.load_game;

public interface LoadGameOutputBoundary {
    void prepareSuccessLoadView(LoadGameOutputData loadGameOutputData);

    void prepareFailView(String errorMessage);

    void switchToTitleView();
}
