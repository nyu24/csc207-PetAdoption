package interface_adapter.save_game;

import interface_adapter.ViewModel;

public class SaveGameViewModel extends ViewModel<SaveGameState> {
    public SaveGameViewModel() {
        super("save game");
        setState(new SaveGameState());
    }
}
