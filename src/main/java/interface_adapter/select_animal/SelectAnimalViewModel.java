package interface_adapter.select_animal;

import interface_adapter.ViewModel;

public class SelectAnimalViewModel extends ViewModel<SelectAnimalState> {

    public SelectAnimalViewModel() {
        super("Select Animal");
        setState(new SelectAnimalState());
    }

}
