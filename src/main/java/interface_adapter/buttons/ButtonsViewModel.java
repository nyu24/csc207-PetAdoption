package interface_adapter.buttons;

import interface_adapter.ViewModel;

/**
 * The view model for buttons view
 */

public class ButtonsViewModel extends ViewModel<ButtonsState> {


    public ButtonsViewModel() {
        super("buttons");
        setState(new ButtonsState());
    }

}
