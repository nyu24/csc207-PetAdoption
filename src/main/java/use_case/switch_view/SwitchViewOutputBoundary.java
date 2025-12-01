package use_case.switch_view;

public interface SwitchViewOutputBoundary {

    /**
     * Switches to the Load Game View.
     */
    void switchToLoadGameView();

    /**
     * Switches to the Set Param View.
     */
    void switchToSetParamView();

    /**
     * Switches to the High Score View.
     */
    void switchToHighScoreView();
}
