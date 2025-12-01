package use_case.load_game;

public interface LoadGameOutputBoundary {
    /**
     * Prepares the success view for the Load game use case.
     * @param loadGameOutputData the output data.
     */
    void prepareSuccessLoadView(LoadGameOutputData loadGameOutputData);

    /**
     * Prepares the failure view for the Load Game Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Title View.
     */
    void switchToTitleView();
}
