package interface_adapter.load_game;

import interface_adapter.ViewModel;

public class LoadGameViewModel extends ViewModel<LoadGameState> {
    public static final String LOAD_LABEL = "Load game?";
    public static final String WARNING_LABEL = "Warning: Current game data will be lost.";

    public static final String YES_BUTTON_LABEL = "Yes";
    public static final String NO_BUTTON_LABEL = "No";

    public LoadGameViewModel() {
        super("load game");
        setState(new LoadGameState());
    }
}
