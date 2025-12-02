package interface_adapter.select_animal;

import interface_adapter.PetRoom.PetRoomState;
import interface_adapter.PetRoom.PetRoomViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.set_parameters.SetParamState;
import interface_adapter.set_parameters.SetParamViewModel;
import use_case.select_animal.SelectAnimalOutputBoundary;
import use_case.select_animal.SelectAnimalOutputData;

/**
 * Presenter for select animal use case.
 */
public class SelectAnimalPresenter implements SelectAnimalOutputBoundary {

    private final SelectAnimalViewModel selectAnimalViewModel;
    private final PetRoomViewModel petRoomViewModel;
    private final SetParamViewModel setParamViewModel;
    private final ViewManagerModel viewManagerModel;

    public SelectAnimalPresenter(SelectAnimalViewModel selectAnimalViewModel, PetRoomViewModel petRoomViewModel,
                             SetParamViewModel setParamViewModel,
                             ViewManagerModel viewManagerModel) {
        this.selectAnimalViewModel = selectAnimalViewModel;
        this.petRoomViewModel = petRoomViewModel;
        this.setParamViewModel = setParamViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    // links the view change from selectAnimalView to petRoomView
    @Override
    public void prepareSuccessView(SelectAnimalOutputData response) {
        System.out.println("some pet vars: " + response.getPet().getCleanliness() + " "
                + response.getPet().getApiPet().getName()
                + " " + response.getPet().getApiPet().getType() + " "
                + response.getPet().getApiPet().getGender());

        final PetRoomState petRoomState = petRoomViewModel.getState();
        petRoomState.setPetType(response.getPet().getApiPet().getType());
        petRoomState.setCurrPet(response.getPet());

        this.petRoomViewModel.firePropertyChange("timer_start");
        this.petRoomViewModel.setState(petRoomState);

        this.viewManagerModel.setState(petRoomViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessViewBack() {
        final SetParamState setParamState = setParamViewModel.getState();
        this.setParamViewModel.setState(setParamState);
        this.setParamViewModel.firePropertyChanged();
        this.viewManagerModel.setState(setParamViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

}
