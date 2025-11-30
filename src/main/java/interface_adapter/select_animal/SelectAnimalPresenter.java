package interface_adapter.select_animal;

import interface_adapter.PetRoom.PetRoomState;
import interface_adapter.PetRoom.PetRoomViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.high_score.HighScoreState;
import interface_adapter.high_score.HighScoreViewModel;
import use_case.select_animal.SelectAnimalOutputBoundary;
import use_case.select_animal.SelectAnimalOutputData;

public class SelectAnimalPresenter implements SelectAnimalOutputBoundary {

    private final SelectAnimalViewModel selectAnimalViewModel;
    //TODO: for georgia, use your section view model and refactor it (line 13)
    private final PetRoomViewModel petRoomViewModel;
    private final ViewManagerModel viewManagerModel;

    // TODO: replace HighScoreViewModel (in the parameters) to your one
    public SelectAnimalPresenter(SelectAnimalViewModel selectAnimalViewModel, PetRoomViewModel petRoomViewModel,
                             ViewManagerModel viewManagerModel) {
        this.selectAnimalViewModel = selectAnimalViewModel;
        this.petRoomViewModel = petRoomViewModel;
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

        final PetRoomState petRoomState = petRoomViewModel.getState();
        petRoomState.setPetType(response.getPet().getApiPet().getType());
        petRoomState.setCurrPet(response.getPet());

        //TODO: delete, this is for testing purposes
        // replace 'HighScoreState' with your proper state
        this.petRoomViewModel.firePropertyChange("timer_start");
        this.petRoomViewModel.setState(petRoomState);

        this.viewManagerModel.setState(petRoomViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

}
