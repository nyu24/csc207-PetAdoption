package use_case.load_game;

public interface LoadGameInputBoundary {

    /**
     * Executes the load game use case.
     */
    void execute();

    /**
     * Executes the switch to title view use case.
     */
    void switchToTitleView();
}