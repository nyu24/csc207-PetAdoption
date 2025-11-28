package interface_adapter.save_game;

import interface_adapter.ViewModel;

public class SaveGameViewModel extends ViewModel<SaveGameState> {
    public static final String SAVE_LABEL = "Save game?";
    public static final String WARNING_LABEL = "Warning: Will override current save if it exists.";

    public static final String YES_BUTTON_LABEL = "Yes";
    public static final String NO_BUTTON_LABEL = "No";

    public SaveGameViewModel() {
        super("save game");
        setState(new SaveGameState());
    }
}
