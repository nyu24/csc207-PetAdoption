package use_case.switch_view;

public interface SwitchViewInputBoundary {

    /**
     * Executes the switch to load view use case.
     */
    void switchToLoadGameView();

    /**
     * Executes the switch to high score view use case.
     */
    void switchToHighScoreView();

    /**
     * Executes the switch to set parameters view use case.
     */
    void switchToSetParamView();

}
