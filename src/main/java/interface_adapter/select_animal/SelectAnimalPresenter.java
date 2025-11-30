package interface_adapter.select_animal;

import interface_adapter.PetRoom.PetRoomState;
import interface_adapter.PetRoom.PetRoomViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.high_score.HighScoreState;
import interface_adapter.high_score.HighScoreViewModel;
import interface_adapter.set_parameters.SetParamState;
import interface_adapter.set_parameters.SetParamViewModel;
import use_case.select_animal.SelectAnimalOutputBoundary;
import use_case.select_animal.SelectAnimalOutputData;
import view.SetParamView;

public class SelectAnimalPresenter implements SelectAnimalOutputBoundary {

    private final SelectAnimalViewModel selectAnimalViewModel;
    //TODO: for georgia, use your section view model and refactor it (line 13)
    private final PetRoomViewModel petRoomViewModel;
    private final SetParamViewModel setParamViewModel;
    private final ViewManagerModel viewManagerModel;

    // TODO: replace HighScoreViewModel (in the parameters) to your one
    public SelectAnimalPresenter(SelectAnimalViewModel selectAnimalViewModel, PetRoomViewModel petRoomViewModel,
                             SetParamViewModel setParamViewModel,
                             ViewManagerModel viewManagerModel) {
        this.selectAnimalViewModel = selectAnimalViewModel;
        this.petRoomViewModel = petRoomViewModel;
        this.setParamViewModel = setParamViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    //TODO: Georgia, it wont function right now until you create these files/or if they r names wrong
    //links the view change from selectAnimalView to petRoomView
    @Override
    public void prepareSuccessView(SelectAnimalOutputData response) {
        //TODO: arrives here
        System.out.println("some pet vars: " + response.getPet().getCleanliness() + " " +
                response.getPet().getApiPet().getName() +
                " " + response.getPet().getApiPet().getType() + " "
                + response.getPet().getApiPet().getGender());

        //TODO: Georgia this should create the pet you use and switch the view, so uncomment it

        PetRoomState petRoomState = petRoomViewModel.getState();
        petRoomState.setPetType(response.getPet().getApiPet().getType());

        //TODO: delete, this is for testing purposes
        // replace 'HighScoreState' with your proper state
        this.petRoomViewModel.setState(petRoomState);
        this.petRoomViewModel.firePropertyChange("timer_start");

        this.viewManagerModel.setState(petRoomViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessViewBack() {
        SetParamState setParamState = setParamViewModel.getState();
        this.setParamViewModel.setState(setParamState);
        this.setParamViewModel.firePropertyChanged();
        this.viewManagerModel.setState(setParamViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

}
