package interface_adapter.set_parameters;

import interface_adapter.ViewModel;

public class SetParamViewModel extends ViewModel<SetParamState> {

    public SetParamViewModel() {
        super("Set Param");
        setState(new SetParamState());
    }
}
